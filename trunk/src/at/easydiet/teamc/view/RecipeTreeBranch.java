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

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RecipeTreeBranch.class);
    // instance variables
    private RecipeData _recipeData;
    private boolean _isMainCategory;

    /**
     * Create new Treebranch
     * @param r Recipe
     */
    public RecipeTreeBranch(RecipeData r, boolean isMainCategory) {
        _recipeData = r;
        _isMainCategory = isMainCategory;
        setText(_recipeData.getName());

    }

    public RecipeData getRecipeData() {
        return _recipeData;
    }

    public boolean isMainCategory() {
        return _isMainCategory;
    }
}
