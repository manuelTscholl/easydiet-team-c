package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.DietParameter;
import at.easydiet.model.Meal;
import at.easydiet.model.TimeSpan;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TimeSpanBo generated by hbm2java
 */
public class TimeSpanBo  implements java.io.Serializable, Saveable {

     private TimeSpan _TimeSpan;

    private TimeSpanBo() {
    }

    public TimeSpanBo(TimeSpan timeSpan){
        this._TimeSpan=timeSpan;
    }

	
    public TimeSpanBo(Date start, int duration) {
        this(new TimeSpan(start, duration));
    }
    public TimeSpanBo(Date start, int duration, Set<DietParameterBo> dietParametersBo, Set<MealBo> mealsBo) {
       this(start, duration);
        for (MealBo mealBo : mealsBo) {
            this._TimeSpan.getMeals().add(mealBo.getMeal());
        }
       
        for (DietParameterBo dietParameterBo : dietParametersBo) {
            this._TimeSpan.getDietParameters().add(dietParameterBo.getDieDietParameter());
        }
    }
   
    public long getTimeSpanId() {
        return this.getTimeSpan().getTimeSpanId();
    }
    
    public void setTimeSpanId(long timeSpanId) {
        this.getTimeSpan().setTimeSpanId(timeSpanId);
    }
    public Date getStart() {
        return this.getTimeSpan().getStart();
    }
    
    public void setStart(Date start) {
        this.getTimeSpan().setStart(start);
    }
    public int getDuration() {
        return this.getTimeSpan().getDuration();
    }
    
    public void setDuration(int duration) {
        this.getTimeSpan().setDuration(duration);
    }
    public Set<DietParameterBo> getDietParameters() {
        Set<DietParameterBo> temp = new HashSet<DietParameterBo>(this._TimeSpan.getDietParameters().size());
        for (DietParameter dietParameter : this._TimeSpan.getDietParameters()) {
            temp.add(new DietParameterBo(dietParameter));
        }
        return temp;
    }
    
    public void setDietParameters(Set<DietParameterBo> dietParametersBo) {
        for (DietParameterBo dietParameterBo : dietParametersBo) {
            this._TimeSpan.getDietParameters().add(dietParameterBo.getDieDietParameter());
        }
    }

    public Set<MealBo> getMeals() {
        Set<MealBo> temp = new HashSet<MealBo>(this._TimeSpan.getMeals().size());
        for (Meal meal : this._TimeSpan.getMeals()) {
            temp.add(new MealBo(meal));
        }
        return temp;
    }
    
    public void setMeals(Set<MealBo> mealsBo) {
         for (MealBo mealBo : mealsBo) {
            this._TimeSpan.getMeals().add(mealBo.getMeal());
        }
    }

    /**
     * @return the _TimeSpan
     */
    protected TimeSpan getTimeSpan() {
        return _TimeSpan;
    }

    /**
     * @param TimeSpan the _TimeSpan to set
     */
    public void setTimeSpan(TimeSpan TimeSpan) {
        this._TimeSpan = TimeSpan;
    }

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


