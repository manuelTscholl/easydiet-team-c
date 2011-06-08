package at.easydiet.teamc.model;

// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.model.DietParameter;
import at.easydiet.model.Meal;
import at.easydiet.model.TimeSpan;

/**
 * TimeSpanBo generated by hbm2java
 */
@ManagedBean
@SessionScoped
public class TimeSpanBo implements java.io.Serializable, Saveable,
        Comparable<TimeSpanBo>
{

    private TimeSpan _TimeSpan;

    private TimeSpanBo()
    {}

    public TimeSpanBo(TimeSpan timeSpan)
    {
        this._TimeSpan = timeSpan;
    }

    public TimeSpanBo(Date start, int duration, DietPlanBo dietPlanBo)
    {
        this(new TimeSpan(start, duration, dietPlanBo.getDietPlan()));
    }

    public TimeSpanBo(Date start, int duration,
            Set<DietParameterBo> dietParametersBo, Set<MealBo> mealsBo,
            DietPlanBo dietPlanBo)
    {
        this(start, duration, dietPlanBo);
        for (MealBo mealBo : mealsBo)
        {
            this._TimeSpan.getMeals().add(mealBo.getMeal());
        }

        for (DietParameterBo dietParameterBo : dietParametersBo)
        {
            this._TimeSpan.getDietParameters().add(
                    dietParameterBo.getDietParameter());
        }
    }

    public long getTimeSpanId()
    {
        return this.getTimeSpan().getTimeSpanId();
    }

    public void setTimeSpanId(long timeSpanId)
    {
        this.getTimeSpan().setTimeSpanId(timeSpanId);
    }

    public Date getStart()
    {
        return this.getTimeSpan().getStart();
    }

    public void setStart(Date start)
    {
        this.getTimeSpan().setStart(start);
    }

    public int getDuration()
    {
        return this.getTimeSpan().getDuration();
    }

    public void setDuration(int duration)
    {
        this.getTimeSpan().setDuration(duration);
    }

    public Set<DietParameterBo> getDietParameters()
    {
        Set<DietParameterBo> temp = new HashSet<DietParameterBo>(this._TimeSpan
                .getDietParameters().size());
        for (DietParameter dietParameter : this._TimeSpan.getDietParameters())
        {
            temp.add(new DietParameterBo(dietParameter));
        }
        return temp;
    }

    public void setDietParameters(Set<DietParameterBo> dietParametersBo)
    {
        for (DietParameterBo dietParameterBo : dietParametersBo)
        {
            this._TimeSpan.getDietParameters().add(
                    dietParameterBo.getDietParameter());
        }
    }

    public Set<MealBo> getMeals()
    {
        Set<MealBo> temp = new HashSet<MealBo>(this._TimeSpan.getMeals().size());
        for (Meal meal : this._TimeSpan.getMeals())
        {
            temp.add(new MealBo(meal));
        }
        return temp;
    }
    
    public List<MealBo> getMealsAsList()
    {
        List<MealBo> temp = new ArrayList<MealBo>();
        for (Meal meal : this._TimeSpan.getMeals())
        {
            temp.add(new MealBo(meal));
        }
        return temp;
    }

    public void setMeals(Set<MealBo> mealsBo)
    {
        for (MealBo mealBo : mealsBo)
        {
            this._TimeSpan.getMeals().add(mealBo.getMeal());
        }
    }

    public void setMeal(MealBo mealBo)
    {
        this._TimeSpan.getMeals().add(mealBo.getMeal());

    }

    void addMeal(MealBo mBo)
    {
        this._TimeSpan.getMeals().add(mBo.getMeal());
    }

    /**
     * @return the _TimeSpan
     */
    protected TimeSpan getTimeSpan()
    {
        return _TimeSpan;
    }

    /**
     * @param TimeSpan
     *            the _TimeSpan to set
     */
    public void setTimeSpan(TimeSpan TimeSpan)
    {
        this._TimeSpan = TimeSpan;
    }

    @Override
    public boolean save()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * compares the startdate
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TimeSpanBo o)
    {
        if (o == null) return 1;
        if (o.getStart().equals(getStart())) return 0;
        if (o.getStart().after(getStart())) return -1;
        if (getStart().after(o.getStart())) return +1;
        return 0;
    }

}
