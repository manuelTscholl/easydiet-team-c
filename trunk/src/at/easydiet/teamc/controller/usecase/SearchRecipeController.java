/**
 * This File is part of Easy Diet
 * created on: 18.04.2011
 * created by: Friedrich B�sch
 * file: SearchRecipeController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.List;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.RecipeBo;

/**
 * Controller for searching recipe specific data.
 * @author Stephan Svoboda
 */
public class SearchRecipeController {

	// class Variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(BusinessLogicDelegationController.class);
	private static volatile SearchRecipeController _searchRecipeController = null;

	/**
	 * Singelton
	 * 
	 * @return Will return the existing Instance or if no exists a new Instance
	 *         of {@link SearchRecipeController}
	 */
	public static SearchRecipeController getInstance() {
		if (_searchRecipeController == null) {
			_searchRecipeController = new SearchRecipeController();
		}
		return _searchRecipeController;
	}

	/**
	 * Returns a list of Recipe-Categoreis
	 * 
	 * @return
	 */
	public List<RecipeBo> getRecipeMainCategories() {
		List<RecipeBo> mainCategories = DatabaseController.getInstance()
				.getRecipeMainCategories();
		return mainCategories;
	}

	/**
	 * Returns a list of Recipe
	 * 
	 * @return
	 */
	public List<RecipeBo> searchRecipe(String mainCategory, String search) {
		List<RecipeBo> recipes = DatabaseController.getInstance().searchRecipe(
				mainCategory, search);

		return recipes;
	}

	/**
	 * Searches the next higher categorie of a recipe
	 * 
	 * @param recipe the categorie of this receipt will be returned
	 * @return
	 */
	public RecipeBo getCategorieOfRecipe(RecipeBo recipe) {
		// FIXME 2 mal implementiert???? Diese Funktion wird nicht verwendet!!
		String blsCode = recipe.getBlsCode();

		blsCode = blsCode.substring(0, 1);
		blsCode += "00000";
		List<RecipeBo> recipes = searchRecipe(blsCode, null);

		if (recipes.size() >= 1) {
			return (RecipeBo) recipes.toArray()[0];
		} else {
			return null;
		}
	}
}
