/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: RecipeTreeBranch.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.model.data.RecipeData;
import org.apache.pivot.wtk.content.TreeBranch;

/**
 *
 * @author Michael Sieber
 */
public class RecipeTreeBranch extends TreeBranch {

    // instance variables
    private RecipeData _recipeData;
    
    /**
     * Create new Treebranch
     * @param r Recipe
     */
    public RecipeTreeBranch(RecipeData r){
        _recipeData = r;
        setText(_recipeData.getName());
        
    }

    public RecipeData getRecipeData(){
        return _recipeData;
    }
}
