package at.easydiet.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a TimeSpan
 */
public class TimeSpan  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -4519193996264066334L;
    private long _timeSpanId;
    private Date _start;
    private int _duration;
    private Set<DietParameter> _dietParameters = new HashSet<DietParameter>(0);
    private Set<Meal> _meals = new HashSet<Meal>(0);

    /**
     * Initializes a new instance of the {@link TimeSpan} class.
     */
    public TimeSpan() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link TimeSpan} class.
     * @param start the start to set for this instance
     * @param duration the duration to set for this instance
     */
    public TimeSpan(Date start, int duration) 
    {
        _start = start;
        _duration = duration;
    }

    /**
     * Initializes a new instance of the {@link TimeSpan} class.
     * @param start the start to set for this instance
     * @param duration the duration to set for this instance
     * @param dietParameters the dietParameters to set for this instance
     * @param meals the meals to set for this instance
     */
    public TimeSpan(Date start, int duration, Set<DietParameter> dietParameters, Set<Meal> meals) 
    {
       _start = start;
       _duration = duration;
       _dietParameters = dietParameters;
       _meals = meals;
    }
   
    /**       
     * Gets the timeSpanId of this instance. 
     * @return the timeSpanId currently set for this instance.
     */
    public long getTimeSpanId() 
    {
        return _timeSpanId;
    }
    
    /**       
     * Sets the timeSpanId of this instance. 
     * @param timeSpanId the new timeSpanId of this instance.
     */    
    public void setTimeSpanId(long timeSpanId) 
    {
        _timeSpanId = timeSpanId;
    }
    
    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _start;
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _start = start;
    }
    
    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
        return _duration;
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
        _duration = duration;
    }
    
    /**       
     * Gets the dietParameters of this instance. 
     * @return the dietParameters currently set for this instance.
     */
    public Set<DietParameter> getDietParameters() 
    {
        return _dietParameters;
    }
    
    /**       
     * Sets the dietParameters of this instance. 
     * @param dietParameters the new dietParameters of this instance.
     */    
    public void setDietParameters(Set<DietParameter> dietParameters) 
    {
        _dietParameters = dietParameters;
    }
    
    /**       
     * Gets the meals of this instance. 
     * @return the meals currently set for this instance.
     */
    public Set<Meal> getMeals() 
    {
        return _meals;
    }
    
    /**       
     * Sets the meals of this instance. 
     * @param meals the new meals of this instance.
     */    
    public void setMeals(Set<Meal> meals) 
    {
        _meals = meals;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();

        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
