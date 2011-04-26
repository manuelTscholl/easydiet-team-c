/**
 * This File is part of EasyDiet
 * Created on: 26.04.2011
 * Created by: Michael Sieber
 * File: MealLineBoxPane.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.model.data.RecipeData;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.ListView;

/**
 *
 * @author Michael Sieber
 */
public class MealLineBoxPane extends BoxPane {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MealLineBoxPane.class);
    // instance variables
    private ListView _recipeListView;
    private List<RecipeData> _recipes;
    private int _mealLineIndex;
    
    public MealLineBoxPane(){
        
        // init data
        _recipeListView = new ListView();
        _recipeListView.getStyles().put("backgroundColor", "#EBEDEF");
        _recipes = new ArrayList<RecipeData>();
        _recipeListView.setListData(_recipes);
        add(_recipeListView);
    }
    
    /**
     * Add a recipe
     * @param r 
     */
    public void addRecipe(RecipeData r){
        _recipes.add(r);
    }
    
    /**
     * Get the selected recipe
     * @return 
     */
    public RecipeData getSelectedRecipe(){
        return (RecipeData)_recipeListView.getSelectedItem();
    }
    
    /**
     * Remove a recipe
     * @param r 
     */
    public void removeRecipe(RecipeData r){
        _recipes.remove(r);
    }
    
    public int getMealLineIndex(){
        return _mealLineIndex;
    }
    
    public void setMealLineIndex(int index){
        _mealLineIndex = index;
    }
}
