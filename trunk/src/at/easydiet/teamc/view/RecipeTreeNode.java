/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: RecipeTreeNode.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.model.data.CheckedRecipeVo;
import org.apache.pivot.wtk.content.TreeNode;

/**
 *
 * @author Michael Sieber
 */
public class RecipeTreeNode extends TreeNode {

    // instance variables
    private CheckedRecipeVo _recipe;

    /**
     * Create new tree node
     * @param r RecipeData
     */
    public RecipeTreeNode(CheckedRecipeVo r){
        _recipe = r;
        setText(_recipe.getRecipeData().getName());
    }
}
