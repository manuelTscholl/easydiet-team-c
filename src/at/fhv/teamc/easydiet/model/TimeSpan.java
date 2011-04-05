package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TimeSpan generated by hbm2java
 */
public class TimeSpan  implements java.io.Serializable {


     private long timeSpanId;
     private Date start;
     private int duration;
     private Set dietParameters = new HashSet(0);
     private Set meals = new HashSet(0);

    public TimeSpan() {
    }

	
    public TimeSpan(Date start, int duration) {
        this.start = start;
        this.duration = duration;
    }
    public TimeSpan(Date start, int duration, Set dietParameters, Set meals) {
       this.start = start;
       this.duration = duration;
       this.dietParameters = dietParameters;
       this.meals = meals;
    }
   
    public long getTimeSpanId() {
        return this.timeSpanId;
    }
    
    public void setTimeSpanId(long timeSpanId) {
        this.timeSpanId = timeSpanId;
    }
    public Date getStart() {
        return this.start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public Set getDietParameters() {
        return this.dietParameters;
    }
    
    public void setDietParameters(Set dietParameters) {
        this.dietParameters = dietParameters;
    }
    public Set getMeals() {
        return this.meals;
    }
    
    public void setMeals(Set meals) {
        this.meals = meals;
    }




}


