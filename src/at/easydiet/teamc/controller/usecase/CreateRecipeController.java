/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: CreateRecipeController
 */
package at.easydiet.teamc.controller.usecase;

import at.easydiet.model.Recipe;
import at.easydiet.teamc.model.NutrimentRulesBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.NutrimentParameterData;
import at.easydiet.teamc.model.data.RecipeData;

public class CreateRecipeController {

	// the recipe, which will be created and edited by this instance of the
	// controller
	private RecipeBo _currentRecipe;

	NutrimentRulesBo _currentRules;

	public void create() {

		_currentRecipe = new RecipeBo(new Recipe());

	}

	public void addParameter(NutrimentParameterData np) {

	}

	public CheckedRecipeVo addRecipeIngredient(RecipeData d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
