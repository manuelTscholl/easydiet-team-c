package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.Meal;
import java.util.HashSet;
import java.util.Set;

/**
 * MealBo generated by hbm2java
 */
public class MealBo  implements java.io.Serializable {


     private long mealId;
     private String code;
     private String name;
     private Set dietParameters = new HashSet(0);
     private Set mealLines = new HashSet(0);

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
        return this._Meal.getMealId();
    }
    
    public void setMealId(long mealId) {
        this._Meal.setMealId(mealId);
    }
    public String getCode() {
        return this._Meal.getCode();
    }
    
    public void setCode(String code) {
        this._Meal.setCode(code);
    }
    public String getName() {
        return this._Meal.getName();
    }
    
    public void setName(String name) {
        this._Meal.setName(name);
    }
    public Set getDietParameters() {
        return this._Meal.getDietParameters();
    }
    
    public void setDietParameters(Set dietParameters) {
        this._Meal.setDietParameters(dietParameters);
    }
    public Set getMealLines() {
        return this._Meal.getMealLines();
    }
    
    public void setMealLines(Set mealLines) {
        this._Meal.setMealLines(mealLines);
    }




}


