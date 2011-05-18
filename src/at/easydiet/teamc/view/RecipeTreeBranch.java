/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: RecipeTreeBranch.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.wtk.content.TreeBranch;

import at.easydiet.teamc.model.data.RecipeData;

/**
 * A custom tree branch for recipes
 * 
 * @author Michael Sieber
 */
public class RecipeTreeBranch extends TreeBranch {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(RecipeTreeBranch.class);
	// instance variables
	private RecipeData _recipeData;
	private boolean _isMainCategory;

	/**
	 * Create new Treebranch
	 * 
	 * @param r Recipe to add in this branch
	 * @param isMainCategory True if this branch represents a BLS Maincategory
	 */
	public RecipeTreeBranch(RecipeData r, boolean isMainCategory) {
		_recipeData = r;
		_isMainCategory = isMainCategory;
		setText(_recipeData.getName());

	}

	/**
	 * Get the recipe data of this branch
	 * 
	 * @return RecipeData of this branch
	 */
	public RecipeData getRecipeData() {
		return _recipeData;
	}

	/**
	 * Checks if is main category.
	 * 
	 * @return true, if is main category
	 */
	public boolean isMainCategory() {
		return _isMainCategory;
	}
}
