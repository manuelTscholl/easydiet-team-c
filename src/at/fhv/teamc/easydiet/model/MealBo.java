package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.Meal;
import java.util.HashSet;
import java.util.Set;

/**
 * MealBo generated by hbm2java
 */
public class MealBo  implements java.io.Serializable {

     private  Meal _Meal;

    public MealBo() {
    }

    public MealBo(Meal meal){
        this._Meal = meal;
    }

    public MealBo(Meal meal, String code, String name) {
        this(meal);
        this._Meal.setCode(code);
        this._Meal.setName(name);
    }
    public MealBo(Meal meal, String code, String name, Set dietParameters, Set mealLines) {
       this(meal, code, name);
       this._Meal.setDietParameters(dietParameters);
       this._Meal.setMealLines(mealLines);
    }
   
    public long getMealId() {
        return this.getMeal().getMealId();
    }
    
    public void setMealId(long mealId) {
        this.getMeal().setMealId(mealId);
    }
    public String getCode() {
        return this.getMeal().getCode();
    }
    
    public void setCode(String code) {
        this.getMeal().setCode(code);
    }
    public String getName() {
        return this.getMeal().getName();
    }
    
    public void setName(String name) {
        this.getMeal().setName(name);
    }
    public Set getDietParameters() {
        return this.getMeal().getDietParameters();
    }
    
    public void setDietParameters(Set dietParameters) {
        this.getMeal().setDietParameters(dietParameters);
    }
    public Set getMealLines() {
        return this.getMeal().getMealLines();
    }
    
    public void setMealLines(Set mealLines) {
        this.getMeal().setMealLines(mealLines);
    }

    /**
     * @return the _Meal
     */
    protected Meal getMeal() {
        return _Meal;
    }

    /**
     * @param Meal the _Meal to set
     */
    public void setMeal(Meal Meal) {
        this._Meal = Meal;
    }




}

