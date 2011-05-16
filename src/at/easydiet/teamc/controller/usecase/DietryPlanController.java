/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.easydiet.teamc.controller.usecase;

import java.util.Date;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.model.PlanType;
import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.exception.NoDateException;
import at.easydiet.teamc.exception.NoDietTreatmentException;
import at.easydiet.teamc.exception.NoPatientException;
import at.easydiet.teamc.exception.TimeIntersectionException;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Controller for generating a new Diatplan.
 * 
 * @author Stephan Svoboda
 */
public class DietryPlanController extends Event<EventArgs>
{

    // class variables
    /**
     *
     */
    public static final org.apache.log4j.Logger  LOGGER                     = org.apache.log4j.Logger
                                                                                    .getLogger(BusinessLogicDelegationController.class);
    private static volatile DietryPlanController _dietryPlanController      = null;
    // instance variables
    private DatabaseController                   _dbController;
    private SearchRecipeController               _searchRecipeController;
    /**
     * Patient which was predefined as patient to use.
     */
    private PatientBo                            _activePatient;
    /**
     * Dietplan which is generated at the moment but not submitted to a patient.
     */
    private DietPlanBo                           _dietPlanBo;
    /**
     * Meal which is generated at the moment but not submitted to a timespan.
     */
    private MealBo                               _tempMeal;
    /**
     * Timespan which is generated at the moment but not submitted to a
     * dietplan.
     */
    private TimeSpanBo                           _tempTimeSpanBo;
    /**
     * Constant factor for millisecond/day converting
     */
    private static final long                    MILLISECONDS_TO_DAY_FACTOR = 86400000;

    /**
     * 
     * @param sender
     */
    private DietryPlanController(Object sender)
    {
        super(sender);
        _dbController = DatabaseController.getInstance();
    }

    /**
     * Singelton
     * 
     * @return Will return the existing Instance or if no exists a new Instance
     *         of {@link DietryPlanController}
     */
    public static DietryPlanController getInstance()
    {
        if (_dietryPlanController == null)
        {
            _dietryPlanController = new DietryPlanController(null);
        }
        return _dietryPlanController;

    }

    /**
     * Create a new dietry plan
     * 
     * @param startdate
     *            Startdate for the new Plan
     * @param dptd
     *            List of DietparameterData
     * @param enddate
     *            Enddate for the new Plan
     * @param parameterMaxValues
     *            Max Values for the chosen parameters
     * @param parameterMinValues
     *            Min Values for the chosen parameters
     * @param activePatient
     *            Current active patient
     * @param activeUser
     * @return the new generated and validated dietplan
     * @throws NoPatientException
     *             if no active patient exists
     * @throws TimeIntersectionException
     *             if illegal timespan is defined by start and enddate
     * @throws NoDateException
     *             if start or enddate is null
     * @throws NoDietTreatmentException
     *             if active patient has no treatment
     */
    public DietryPlanData newDietryPlan(Date startdate, Date enddate,
            List<DietParameterData> dptd, List<Double> parameterMaxValues,
            List<Double> parameterMinValues, PatientBo activePatient,
            SystemUserBo activeUser) throws NoPatientException,
            NoDateException, TimeIntersectionException,
            NoDietTreatmentException
    {

        if (activePatient == null)
        {
            throw new NoPatientException();
        }

        if (startdate == null || enddate == null)
        {
            throw new NoDateException();
        }

        // List<DietParameterData> dptd can be null

        Set<DietParameterBo> dpb = new HashSet<DietParameterBo>();
        long duration;
        TimeSpanBo timespanbo;
        DietParameterBo dpbo;
        DietParameterBo dpbo2;

        // cache activePatiet
        _activePatient = activePatient;

        // check time intersections
        if (checkForTimeIntersections(startdate, enddate))
        {

            // +1 because if enddate==startdae duration is 1 day not 0
            duration = 1 + ((enddate.getTime() - startdate.getTime()) / MILLISECONDS_TO_DAY_FACTOR);

            //TODO PlanType TestPlan eingefügt als Plantyp
            List<PlanType> types = DAOFactory.getInstance().getPlanTypeDAO()
                    .findAll();
            PlanType toSet = null;
            if (types != null) for (PlanType planType : types)
            {
                if (planType.getName().equals("TestPlanC"))
                {
                    toSet = planType;
                }

            }
            // initiates Diatplan with needed values
            _dietPlanBo = new DietPlanBo("", startdate, new PlanTypeBo(toSet), activeUser,
                    activePatient.searchDietTreatmentBo(startdate, enddate));

            // cast DietParameterData to DietParameterBo
            if (dptd != null)
            {
                for (int i = 0; i < dptd.size(); i++)
                {
                    dpbo = (DietParameterBo) dptd.get(i);
                    dpbo.setValue(parameterMinValues.get(i).toString());
                    dpbo.setCheckOperatorBo(new CheckOperatorBo(">="));
                    dpbo2 = new DietParameterBo(new CheckOperatorBo("<="),
                            dpbo.getDietParameterType(),
                            dpbo.getParameterDefinition());
                    dpbo2.setDietParameterTemplateId(dpbo
                            .getDietParameterTemplateId());
                    dpbo2.setValue(parameterMaxValues.get(i).toString());
                }
            }

            // Setting the Parameters for Diatplan
            _dietPlanBo.setDietParameters(dpb);

            timespanbo = new TimeSpanBo(startdate, (int) duration, _dietPlanBo);

            // TODO activate save methods and treatment
            // Save diatplanobject in database

            List<PlanType> planTypes = DAOFactory.getInstance().getPlanTypeDAO()
                    .findAll();
            for (PlanType planType : types)
            {
                if (planType.getName().equalsIgnoreCase("Di�tplanung"))
                {
                    _dietPlanBo.setPlanType(new PlanTypeBo(planType));
                }
            }
            // FIX nix saven do
            //_dietPlanBo.save();

            for (int i = 0; i < timespanbo.getDuration(); i++)
            {
                
                _dietPlanBo.addTimeSpan(new TimeSpanBo(startdate, 1, _dietPlanBo));
                startdate = new Date(startdate.getTime()
                        + MILLISECONDS_TO_DAY_FACTOR);
            }

            if (activePatient.getTreatments().size() > 0)
            {
                searchTreatmentForDietplan(timespanbo).addDietPlan(_dietPlanBo);
                _tempTimeSpanBo = timespanbo;
            }
            else
            {
                throw new NoDietTreatmentException();
            }

            // _activePatient.save();

            return (DietryPlanData) _dietPlanBo;
        }
        else
        {
            throw new TimeIntersectionException();
        }
    }

    /**
     * 
     * @return All existing mealcodes
     */
    public Set<MealCodeData> getAllMealCodes()
    {
        Set<MealCodeData> temp = new HashSet<MealCodeData>();
        for (MealCodeBo mcb : _dbController.getAllMealCodes())
        {
            temp.add((MealCodeData) mcb);
        }

        return temp;
    }

    /**
     * Checks a given timespan for intersections with existing timespans on
     * dietplan level
     * 
     * @param startdate
     * @param enddate
     * @return
     */
    private boolean checkForTimeIntersections(Date startdate, Date enddate)
    {

        Date tempenddate;

        if (enddate.before(startdate))
        {
            return false;
        }
        else
        {
            for (DietTreatmentBo tsb : _activePatient.getTreatments())
            {
                if (tsb.getStart().equals(startdate)
                        || tsb.getStart().before(startdate))
                {
                    for (DietPlanBo dpb : tsb.getDietPlans())
                    {
                        for (TimeSpanBo timesb : dpb.getTimeSpans())
                        {
                            // -1 because duration starts with 1 instead of 0

                            tempenddate = new Date(
                                    (timesb.getDuration() * MILLISECONDS_TO_DAY_FACTOR)
                                            - 1 + (timesb.getStart().getTime()));
                            // check wether intersection not possible because of
                            // logically not overlapping startdate and enddate
                            if (enddate.before(timesb.getStart())
                                    || startdate.after(tempenddate))
                            {

                                continue;
                            }

                            // check wether intersection because of at least one
                            // datepoint possible
                            if ((startdate.before(tempenddate) || startdate
                                    .equals(tempenddate))
                                    && (enddate.equals(timesb.getStart()) || enddate
                                            .after(timesb.getStart())))
                            {
                                return false;
                            }
                            if ((enddate.after(timesb.getStart()) || enddate
                                    .equals(timesb.getStart()))
                                    && (startdate.equals(timesb.getStart()) || startdate
                                            .before(timesb.getStart())))
                            {
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
    private DietTreatmentBo searchTreatmentForDietplan(TimeSpanBo tsb)
    {
        Date tempenddate;
        DietTreatmentBo temp = null;
        for (DietTreatmentBo dietTreatmentBo : _activePatient.getTreatments())
        {
            tempenddate = new Date(
                    dietTreatmentBo.getStart().getTime()
                            + (dietTreatmentBo.getDuration() * MILLISECONDS_TO_DAY_FACTOR));
            if (dietTreatmentBo.getStart().getTime() <= tsb.getStart()
                    .getTime()
                    && tempenddate.getTime() >= tsb.getDuration() * 1000 * 60
                            * 60 * 24)
            {
                return dietTreatmentBo;
            }
        }
        return temp;
    }

    /**
     * Adds MealBo to the diatplan in progress represtented by _dietplanBo.
     * 
     * @param mcd
     *            MealCode to add
     * @param day
     *            Defines exact day in Timespan
     */
    public void addMealCode(MealCodeData mcd, int day)
    {
        _tempMeal = _dietPlanBo.addMealCode(mcd, day);
    }

    /**
     * Adds MealLineBo to the Meal in progress.
     * 
     * @return Index of the new MealLine
     */
    public int addMealLine()
    {
        return _dietPlanBo.addMealLine(this._tempMeal);
    }

    /**
     * 
     * @return Main Categories of Recipes from BLS
     */
    public List<RecipeData> getRecipeMainCategories()
    {
        _searchRecipeController = SearchRecipeController.getInstance();
        List<RecipeData> temp = new ArrayList<RecipeData>();
        for (RecipeBo rb : _searchRecipeController.getRecipeMainCategories())
        {
            temp.add((RecipeData) rb);
        }
        return temp;
    }

    /**
     * 
     * @param mainCategory
     *            the bls Code of the main category in which search string is
     *            searched for
     * @param search
     *            Search string
     * @return Set of Recipes which are checked corresponding to the active
     *         parameters
     */
    public Set<CheckedRecipeVo> searchRecipe(String mainCategory, String search)
    {

        Set<CheckedRecipeVo> temps = new HashSet<CheckedRecipeVo>();
        CheckedRecipeVo temp;
        List<RecipeBo> recipes = _searchRecipeController.searchRecipe(
                mainCategory, search);

        if (recipes != null)
        {
            for (RecipeBo rb : recipes)
            {
                temp = new CheckedRecipeVo((RecipeData) rb,
                        _dietPlanBo.checkRecipeWithParameters(rb,
                                searchTreatmentForDietplan(_tempTimeSpanBo),
                                _dietPlanBo));
                temps.add(temp);
                for (RecipeBo disfav : _activePatient.getDisfavors())
                {
                    if (rb.getName().equals(disfav.getName()))
                    {
                        temp.setDisfavour(true);
                    }
                }
            }
        }
        return temps;
    }

    /**
     * Adds a Recipe to the Meal in progress.
     * 
     * @param rd
     *            Recipe which will be added.
     * @param quantity
     *            amount of Recipe in gramm.
     * @param mealLineID
     *            MealLine to which the Recipe belongs.
     * @return
     */
    public MealData addRecipeToMeal(RecipeData rd, int day, float quantity,
            int mealLineID, MealCodeData mcd)
    {
        return _dietPlanBo.addRecipe((RecipeBo) rd, day, quantity, mealLineID, mcd);
    }

    /**
     * Saves DiatPlan in progress in Database.
     */
    public void saveDietryPlan()
    {
        try
        {
            HibernateUtil.currentSession().beginTransaction();
            _dietPlanBo.save();
            HibernateUtil.currentSession().getTransaction().commit();
        }
        catch (Exception e)
        {
            HibernateUtil.currentSession().getTransaction().rollback();
        }
        
    }

    /**
     * Returns dietplan in progress.
     * 
     * @return
     */
    public DietryPlanData getDietryPlan()
    {
        return (DietryPlanData) _dietPlanBo;
    }
}
