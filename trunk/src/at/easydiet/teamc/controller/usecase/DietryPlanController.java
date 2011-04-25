/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.easydiet.teamc.controller.usecase;

import java.util.Date;
import java.util.Set;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.CheckOperatorBo;
import at.easydiet.teamc.model.DietParameterBo;
import at.easydiet.teamc.model.DietPlanBo;
import at.easydiet.teamc.model.DietTreatmentBo;
import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealCodeBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.SystemUserBo;
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
    private SearchRecipeController _searchRecipeController;
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
    /**
     * Constant facotr for millisecond/day converting
     */
    private final int MILLISECONDS_TO_DAY_FACTOR = 86400000;

    private DietryPlanController(Object sender) {
        super(sender);
        _dbController = DatabaseController.getInstance();
    }

    /**
     * Singelton
     * @return Will return the existing Instance or if no exists a new Instance of {@link DietryPlanController}
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
            List<Double> parameterMaxValues, List<Double> parameterMinValues, PatientBo activePatient,
            SystemUserBo activeUser) {

        //TODO combine max and min parameters with values!
        //TODO NULL check!!

        if (activePatient == null) {
            return null;
        }

        if (startdate == null || enddate == null) {
            return null;
        }

        //List<DietParameterData> dptd can be null

        Set<DietParameterBo> dpb = new HashSet<DietParameterBo>();
        long duration;
        TimeSpanBo timespanbo;
        DietParameterBo dpbo;
        DietParameterBo dpbo2;

        //cache activePatiet
        _activePatient = activePatient;

        //check time intersections
        if (checkForTimeIntersections(startdate, enddate)) {

            //initiates Diatplan with needed values
            //TODO Testplan??
            _dietPlanBo = new DietPlanBo("", startdate, new PlanTypeBo("Testplan"), activeUser);

            //cast DietParameterData to DietParameterBo
            if (dptd != null) {
                for (int i = 0; i < dptd.size(); i++) {
                    dpbo = (DietParameterBo) dptd.get(i);
                    dpbo.setValue(parameterMinValues.get(i).toString());
                    dpbo.setCheckOperatorBo(new CheckOperatorBo(">="));
                    dpbo2 = new DietParameterBo(new CheckOperatorBo("<="), dpbo.getDietParameterType(), dpbo.getParameterDefinition());
                    dpbo2.setDietParameterTemplateId(dpbo.getDietParameterTemplateId());
                    dpbo2.setValue(parameterMaxValues.get(i).toString());
                }
            }

            //Setting the Parameters for Diatplan
            _dietPlanBo.setDietParameters(dpb);

            duration = 1+((enddate.getTime() - startdate.getTime()) / MILLISECONDS_TO_DAY_FACTOR);
            timespanbo = new TimeSpanBo(startdate, (int) duration);

            //TODO activate save methods and treatment
            //Save diatplanobject in database
            //_dietPlanBo.save();

            _dietPlanBo.addTimeSpan(timespanbo);
            searchTreatmentForDietplan(timespanbo).addDietPlan(_dietPlanBo);
            
            //_activePatient.save();

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

    /**
     * Checks a given timespan for intersections with existing timespans on dietplan level
     *
     * @param startdate
     * @param enddate
     * @return
     */
    private boolean checkForTimeIntersections(Date startdate, Date enddate) {


        Date tempenddate;

        if (enddate.before(startdate)) {
            return false;
        } else {
            for (DietTreatmentBo tsb : _activePatient.getTreatments()) {
                if (tsb.getStart().equals(startdate) || tsb.getStart().before(startdate)) {
                    for (DietPlanBo dpb : tsb.getDietPlans()) {
                        for (TimeSpanBo timesb : dpb.getTimeSpans()) {
                            tempenddate = new Date(timesb.getStart().getTime() + (timesb.getDuration() * MILLISECONDS_TO_DAY_FACTOR));
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

    /**
     * Searches the right treatment for a given timespan of a DietTreatment.
     *
     * @param tsb
     * @return
     */
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

    /**
     * Adds MealBo to the diatplan in progress represtented by _dietplanBo.
     *
     * @param mcd MealCode to add
     * @param day Defines exact day in Timespan
     */
    public void addMealCode(MealCodeData mcd, int day) {
        System.out.println(mcd.getName());
        _tempMeal = _dietPlanBo.addMealCode(mcd, day);
    }

    /**
     * Adds MealLineBo to the Meal in progress.
     *
     * @return Index of the new MealLine
     */
    public int addMealLine() {
        return _dietPlanBo.addMealLine(this._tempMeal);
    }

    /**
     *
     * @return Main Categories of Recipes from BLS
     */
    public Set<RecipeData> getRecipeMainCategories() {
        _searchRecipeController = SearchRecipeController.getInstance();
        Set<RecipeData> temp = new HashSet<RecipeData>();
        for (RecipeBo rb : _searchRecipeController.getRecipeMainCategories()) {
            temp.add((RecipeData) rb);
        }
        return temp;
    }

    /**
     *
     * @param mainCategory the bls Code of the main category in which search string is searched for
     * @param search Search string
     * @return Set of Recipes which are checked corresponding to the active parameters
     */
    public Set<CheckedRecipeVo> searchRecipe(String mainCategory, String search) {

        Set<CheckedRecipeVo> temp = new HashSet<CheckedRecipeVo>();
        Set<RecipeBo> recipes = _searchRecipeController.searchRecipe(mainCategory, search);

        if (recipes != null) {
            for (RecipeBo rb : recipes) {
                temp.add(new CheckedRecipeVo((RecipeData) rb, _dietPlanBo.checkRecipeWithParameters(rb)));
            }
        }
        return temp;
    }

    /**
     * Adds a Recipe to the Meal in progress.
     *
     * @param rd Recipe which will be added.
     * @param quantity amount of Recipe in gramm.
     * @param mealLineID MealLine to which the Recipe belongs.
     * @return
     */
    public MealData addRecipeToMeal(RecipeData rd, double quantity, int mealLineID) {
        _dietPlanBo.addRecipe((RecipeBo) rd, quantity, mealLineID);
        return (MealData) _tempMeal;
    }

    /**
     * Saves DiatPlan in progress in Database.
     */
    public void saveDietryPlan() {
        _dietPlanBo.save();
    }

    /**
     * Returns dietplan in progress.
     *
     * @return
     */
    public DietryPlanData getDietryPlan() {
        return (DietryPlanData) _dietPlanBo;
    }
}
