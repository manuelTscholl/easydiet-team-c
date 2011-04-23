/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.easydiet.teamc.controller.usecase;

import java.util.Date;
import java.util.Set;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.DietParameterBo;
import at.easydiet.teamc.model.DietPlanBo;
import at.easydiet.teamc.model.DietTreatmentBo;
import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealCodeBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.DietParameterData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.MealData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.util.Event;
import at.easydiet.teamc.util.EventArgs;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Stephan Svoboda
 */
public class DietryPlanController extends Event<EventArgs> {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicDelegationController.class);
    private static volatile DietryPlanController _dietryPlanController = null;
    // instance variables
    private DatabaseController _dbController;
    /**
     * Patient which was predefined as patient to use.
     */
    private PatientBo _activePatient;
    /**
     * Dietplan which is generated at the moment but not submitted to a patient.
     */
    private DietPlanBo _dietPlanBo;
    /**
     * Meal which is generated at the moment but not submitted to a timespan.
     */
    private MealBo _tempMeal;
    /**
     * Timespan which is generated at the moment but not submitted to a dietplan.
     */
    private TimeSpanBo _tempTimeSpanBo;

    private DietryPlanController(Object sender) {
        super(sender);
        _dbController = DatabaseController.getInstance();
    }

    /**
     * Singelton
     * @return Will return the existing Instance or if no exists a new Instance of {@link SearchPatientController}
     */
    public static DietryPlanController getInstance() {
        if (_dietryPlanController == null) {
            _dietryPlanController = new DietryPlanController(null);
        }
        return _dietryPlanController;

    }

    /**
     * Create a new dietry plan
     * @param startDate Plan start date
     * @param endDate Plan end date
     * @param params List of parameters for this plan
     * @param parameterMaxValues Max Values for the chosen parameters
     * @param parameterMinValues Min Values for the chosen parameters
     * @param activePatient Current active patient
     * @return 
     */
    public DietryPlanData newDietryPlan(Date startdate, Date enddate, List<DietParameterData> dptd,
            List<Double> parameterMaxValues, List<Double> parameterMinValues, PatientBo activePatient) {
        
        //TODO combine max and min parameters with values!
        //TODO NULL check!!

        Set<DietParameterBo> dpb = new HashSet<DietParameterBo>();
        long duration;
        TimeSpanBo timespanbo;

        //cache activePatiet
        _activePatient = activePatient;

        //check time intersections
        if (checkForTimeIntersections(startdate, enddate)) {

            //initiates Diatplan with needed values
            _dietPlanBo = new DietPlanBo("", startdate, new PlanTypeBo(""), null);

            //cast DietParameterData to DietParameterBo
            for (DietParameterData dpd : dptd) {
                dpb.add((DietParameterBo) dpd);
            }

            //Setting the Parameters for Diatplan
            _dietPlanBo.setDietParameters(dpb);

            duration = (enddate.getTime() - startdate.getTime()) / 1000 / 60 / 60 / 24;
            timespanbo = new TimeSpanBo(startdate, (int) duration);


            //Save diatplanobject in database
            _dietPlanBo.save();

            searchTreatmentForDietplan(timespanbo).getDietPlans().add(_dietPlanBo);
            _activePatient.save();

            return (DietryPlanData) _dietPlanBo;
        } else {
            return null;
        }
    }

    public Set<MealCodeData> getAllMealCodes() {
        Set<MealCodeData> temp = new HashSet<MealCodeData>();
        for (MealCodeBo mcb : _dbController.getAllMealCodes()) {
            temp.add((MealCodeData) mcb);
        }

        return temp;
    }

    private boolean checkForTimeIntersections(Date startdate, Date enddate) {


        Date tempenddate;

        if (startdate.before(enddate)) {
            return false;
        } else {
            for (DietTreatmentBo tsb : _activePatient.getTreatments()) {
                if (tsb.getStart().equals(startdate) || tsb.getStart().before(startdate)) {
                    for (DietPlanBo dpb : tsb.getDietPlans()) {
                        for (TimeSpanBo timesb : dpb.getTimeSpans()) {
                            tempenddate = new Date(timesb.getStart().getTime() + (timesb.getDuration() * 1000 * 60 * 60 * 24));
                            //check wether intersection not possible because of logically not overlapping startdate and enddate
                            if (enddate.before(timesb.getStart()) || startdate.after(tempenddate)) {
                                continue;
                            }

                            //check wether intersection because of at least one datepoint possible
                            if ((startdate.before(tempenddate) || startdate.equals(tempenddate))
                                    && (enddate.equals(timesb.getStart()) || enddate.after(timesb.getStart()))) {
                                return false;
                            }
                            if ((enddate.after(timesb.getStart()) || enddate.equals(timesb.getStart()))
                                    && (startdate.equals(timesb.getStart()) || startdate.before(timesb.getStart()))) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private DietTreatmentBo searchTreatmentForDietplan(TimeSpanBo tsb) {
        Date tempenddate;
        DietTreatmentBo temp = null;
        for (DietTreatmentBo dietTreatmentBo : _activePatient.getTreatments()) {
            tempenddate = new Date(dietTreatmentBo.getStart().getTime() + (dietTreatmentBo.getDuration() * 1000 * 60 * 60 * 24));
            if (dietTreatmentBo.getStart().getTime() <= tsb.getStart().getTime() && tempenddate.getTime() >= tsb.getDuration() * 1000 * 60 * 60 * 24) {
                return dietTreatmentBo;
            }
        }
        return temp;
    }

    public void addMealCode(MealCodeData mcd, int day) {
    }

    public int addMealLine() {
        throw (new UnsupportedOperationException("Not supported yet!"));
    }

    public Set<RecipeData> getRecipeMainCategories() {
        throw (new UnsupportedOperationException("Not supported yet!"));
    }

    public Set<CheckedRecipeVo> searchRecipe(String mainCategory, String search) {
        throw (new UnsupportedOperationException("Not supported yet!"));
    }

    public MealData addRecipeToMeal(RecipeData rd, double quantity, int mealLineID) {
        throw (new UnsupportedOperationException("Not supported yet!"));
    }

    public void saveDietryPlan() {
        throw (new UnsupportedOperationException("Not supported yet!"));
    }

    public DietryPlanData getDietryPlan() {
        throw (new UnsupportedOperationException("Not supported yet!"));
    }
}