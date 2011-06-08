package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealLineBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.MealCodeData;

@ManagedBean
@SessionScoped
public class MealLineBean
{

    private MealLineBo                           _mealline  = new MealLineBo();
    private List<MealLineBo>                     _meallines = new ArrayList<MealLineBo>();
    private NutrimentProtocolBean                _nutrimentProtocolBean;
    private static final org.apache.log4j.Logger LOGGER     = org.apache.log4j.Logger
                                                                    .getLogger(NutrimentProtocolBean.class);

    /**
     * Gets the timespan.
     * 
     * @return the timespan
     */
    public TimeSpanBo getActiveTimespan()
    {
        return getNutrimentProtocolBean().getCurrentTimespan();
    }

    /**
     * Sets the timespan.
     * 
     * @param timespan
     *            the timespan to set
     */
    public void setActiveTimespan(TimeSpanBo timespan)
    {
        getNutrimentProtocolBean().setCurrentTimespan(timespan);
    }

    /**
     * Gets the nutrimentProtocolBean.
     * 
     * @return the nutrimentProtocolBean
     */
    public NutrimentProtocolBean getNutrimentProtocolBean()
    {
        if (_nutrimentProtocolBean == null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            _nutrimentProtocolBean = context.getApplication()
                    .evaluateExpressionGet(context, "#{nutrimentProtocolBean}",
                            NutrimentProtocolBean.class);
        }
        return _nutrimentProtocolBean;
    }

    public void getDataFromActiveTimespan()
    {
        _meallines.clear();
        for (MealBo meals : getActiveTimespan().getMealsAsList())
        {
            _meallines.addAll(meals.getMealLinesAsList());
        }
    }

    public String reinit()
    {
        boolean added = false;
        if (getActiveTimespan().getMeals() != null)
        {
            for (MealBo meal : getActiveTimespan().getMeals())
            {
                if (meal.getCode().equals(getCode()))
                {
                    added = true;
                    meal.setMealLine(_mealline);
                }
            }
        }
        if (added == false)
        {
            MealBo temp = new MealBo(getCode(), getCode(), getActiveTimespan());
            temp.setMealLine(_mealline);
            getActiveTimespan().setMeal(temp);
        }
        added = false;

        _mealline = new MealLineBo();
        return null;
    }

    public MealLineBo getMealLine()
    {
        return _mealline;
    }

    public List<MealLineBo> getMealLines()
    {
        return _meallines;
    }

    public void removeMealLine(ActionEvent e)
    {
        LOGGER.info("start deleting message");
        MealLineBo mealLineBo = (MealLineBo) e.getComponent().getAttributes()
                .get("mealLine");

        for (MealBo meal : getActiveTimespan().getMeals())
        {
            LOGGER.info("before delete:" + meal.getMealLines().size());
            meal.removeMealLine(mealLineBo);
            LOGGER.info("after delete:" + meal.getMealLines().size());
            LOGGER.info("Number of meallines in Meal:"+ meal.getMealLines().size());
            if(meal.getMealLines().size()==0)
            {
                getActiveTimespan().removeMeal(meal);
            }
        }
        
        
    }

    private MealBo getMealBo()
    {
        if (_mealline.getMealBo() == null)
        {
            _mealline.setMealBo(new MealBo("", "", new TimeSpanBo(null)));
        }

        return _mealline.getMealBo();
    }

    public String getCode()
    {
        return getMealBo().getCode();
    }

    public void setCode(String code)
    {
        getMealBo().setCode(code);
    }

    public String getName()
    {
        return getMealBo().getName();
    }

    public void setName(String name)
    {
        getMealBo().setName(name);
    }

    public float getQuantity()
    {
        return _mealline.getQuantity();
    }

    public void setQuantity(float quantity)
    {
        _mealline.setQuantity(quantity);
    }

    public List<MealCodeData> getAllMeals()
    {
        BusinessLogicDelegationController s = BusinessLogicDelegationController
                .getInstance();
        List<MealCodeData> mealCodes = new ArrayList<MealCodeData>();
        for (MealCodeData md : s.getAllMealCodes())
        {
            mealCodes.add(md);
        }
        return mealCodes;
    }

}
