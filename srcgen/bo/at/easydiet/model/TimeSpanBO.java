package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a TimeSpan instance.
 */
public class TimeSpanBO 
{
	private TimeSpan _model;
	
    /**
     * Initializes a new instance of the {@link TimeSpanBO} class.
     */
	public TimeSpanBO()
	{
		// TODO: add default values
		this(new TimeSpan());
	}
	
    /**
     * Initializes a new instance of the {@link TimeSpanBO} class.
     * @param model the original model object
     */
	public TimeSpanBO(TimeSpan model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link TimeSpan} object.
	 */
 	public TimeSpan getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the timeSpanId of this instance. 
     * @return the timeSpanId currently set for this instance.
     */
    public long getTimeSpanId() 
    {
        return _model.getTimeSpanId();
    }
    
    /**       
     * Sets the timeSpanId of this instance. 
     * @param timeSpanId the new timeSpanId of this instance.
     */    
    public void setTimeSpanId(long timeSpanId) 
    {
        _model.setTimeSpanId(timeSpanId);
    }

    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _model.getStart();
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _model.setStart(start);
    }

    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
        return _model.getDuration();
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
        _model.setDuration(duration);
    }

	
    private DietPlanBO _dietPlan;
    
    /**
     * Gets the currently referenced DietPlan of this instance.
     * @return the DietPlan currently referenced in this TimeSpan. 
     */
    public DietPlanBO getDietPlan()
    {
        if(_dietPlan == null)
        {
            _dietPlan = new DietPlanBO(_model.getDietPlan());
        }
        return _dietPlan;
    }
    
    /**
     * Sets the DietPlan to be referenced in this instance
     * @param dietPlan the DietPlan to reference in this TimeSpan. 
     */
    public void setDietPlan(DietPlanBO dietPlan)
    {
        _dietPlan = dietPlan;
        _model.setDietPlan(dietPlan.getModel());
    }

	private List<DietParameterBO> _dietParameters;
	
    /**
     * Gets a list of referenced DietParameters of this instance.
     * This list is cached, use {@link TimeSpan#updateDietParametersCache()) to update this cache.
     * @return a cached list of referenced DietParameters wrapped into the correct businessobject. 
     */
    public List<DietParameterBO> getDietParameters()
    {
        if(_dietParameters == null) 
        {
            _dietParameters = new ArrayList<DietParameterBO>();
            for(DietParameter dietParameters : _model.getDietParameters())
            {
                _dietParameters.add(new DietParameterBO(dietParameters));
            }
        }
        return _dietParameters;
    }
	
    /**
     * Adds a new DietParameter to the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the DietParameter to add. 
     */
    public void addDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().add(dietParameters);
        _model.getDietParameters().add(dietParameters.getModel());
    }
    
        
    /**
     * Removes the given DietParameter from the list of referenced dietParameters.
     * The cache will updated
     * @param dietParameters the timespan to add. 
     */
    public void removeDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().remove(dietParameters);
        _model.getDietParameters().remove(dietParameters.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced dietParameters.
     */
    public void updateDietParametersCache()
    {
        _dietParameters = null;
        getDietParameters();
    }


	private List<MealBO> _meals;
	
    /**
     * Gets a list of referenced Meals of this instance.
     * This list is cached, use {@link TimeSpan#updateMealsCache()) to update this cache.
     * @return a cached list of referenced Meals wrapped into the correct businessobject. 
     */
    public List<MealBO> getMeals()
    {
        if(_meals == null) 
        {
            _meals = new ArrayList<MealBO>();
            for(Meal meals : _model.getMeals())
            {
                _meals.add(new MealBO(meals));
            }
        }
        return _meals;
    }
	
    /**
     * Adds a new Meal to the list of referenced meals.
     * The cache will updated
     * @param meals the Meal to add. 
     */
    public void addMeals(MealBO meals)
    {
        getMeals().add(meals);
        _model.getMeals().add(meals.getModel());
    }
    
        
    /**
     * Removes the given Meal from the list of referenced meals.
     * The cache will updated
     * @param meals the timespan to add. 
     */
    public void removeMeals(MealBO meals)
    {
        getMeals().remove(meals);
        _model.getMeals().remove(meals.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced meals.
     */
    public void updateMealsCache()
    {
        _meals = null;
        getMeals();
    }

}