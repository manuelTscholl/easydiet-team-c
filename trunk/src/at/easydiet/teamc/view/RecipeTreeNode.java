/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: RecipeTreeNode.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.RecipeData;
import org.apache.pivot.wtk.content.TreeNode;

/**
 *
 * @author Michael Sieber
 */
public class RecipeTreeNode extends TreeNode {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RecipeTreeNode.class);
    // instance variables
    private CheckedRecipeVo _recipe;
    private RecipeData _recipeData;

    /**
     * Create new tree node
     * @param r RecipeData
     */
    public RecipeTreeNode(CheckedRecipeVo r) {
        _recipe = r;
        setText(_recipe.getRecipeData().getName());
    }

    public RecipeTreeNode(RecipeData r) {
        _recipeData = r;
        setText(_recipeData.getName());
    }
    
    public RecipeData getRecipeData(){
        
        // check if a checked recipe is available
        if(_recipe == null){
            return _recipeData;
        }
        
        return _recipe.getRecipeData();
    }
}
