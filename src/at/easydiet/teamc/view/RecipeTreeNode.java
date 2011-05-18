/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: RecipeTreeNode.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.wtk.content.TreeNode;

import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * Represents a custom tree node for recipes
 * 
 * @author Michael Sieber
 */
public class RecipeTreeNode extends TreeNode {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(RecipeTreeNode.class);
	// instance variables
	private CheckedRecipeVo _recipe;
	private RecipeData _recipeData;
	private float _amount;

	/**
	 * Create new tree node
	 * 
	 * @param r CheckedRecipe for this node
	 */
	public RecipeTreeNode(CheckedRecipeVo r) {
		_recipe = r;
		setText(_recipe.getRecipeData().getName());
	}

	/**
	 * Create new tree node
	 * 
	 * @param r RecipeData for this node
	 */
	public RecipeTreeNode(RecipeData r) {
		_recipeData = r;
		setText(_recipeData.getName());
	}

	/**
	 * Gets the recipe data.
	 * 
	 * @return the recipe data
	 */
	public RecipeData getRecipeData() {

		// check if a checked recipe is available
		if (_recipe == null) {
			return _recipeData;
		}

		return _recipe.getRecipeData();
	}

	/**
	 * Gets the amount.
	 * 
	 * @return the amount
	 */
	public float getAmount() {
		return _amount;
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount the new amount
	 */
	public void setAmount(float amount) {
		_amount = amount;
	}
}
