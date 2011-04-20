/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.controller.usecase;

import at.easydiet.teamc.model.data.DietParameterTemplateData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealData;
import at.easydiet.teamc.model.data.RecipeData;
import java.util.Date;
import java.util.Set;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Stephan
 */
public class DietryPlanController {

    private DietryPlanController() {
    }

    public static DietryPlanController getInstance() {
        return DietryPlanControllerHolder.INSTANCE;
    }

    private static class DietryPlanControllerHolder {
        private static final DietryPlanController INSTANCE = new DietryPlanController();
    }

    public DietryPlanData newDietryPlan(Date startdate, Date enddate, Set<DietParameterTemplateData> dptd){
        //TODO Implement the whole method
        throw new NotImplementedException();
    }

    public Set<MealData> GetAllMeals (){
        //TODO Implement the whole method
        throw new NotImplementedException();
    }

    public void AddMeal(Set<Set<RecipeData>> selected, int day, MealData md ){
        //TODO Implement the whole method
        throw new NotImplementedException();
    }

    public Set<RecipeData> GetRecipeMainCategories (){
        //TODO Implement the whole method
        throw new NotImplementedException();
    }


 }
