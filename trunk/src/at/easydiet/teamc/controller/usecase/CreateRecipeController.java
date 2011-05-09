/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: CreateRecipeController
 */
package at.easydiet.teamc.controller.usecase;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.dao.HibernateUtil;
import at.easydiet.model.Recipe;
import at.easydiet.teamc.model.CheckOperatorBo;
import at.easydiet.teamc.model.NutrimentRuleBo;
import at.easydiet.teamc.model.NutrimentRulesBo;
import at.easydiet.teamc.model.ParameterDefinitionBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.ValidatedRecipeVo;

public class CreateRecipeController {

	// the recipe, which will be created and edited by this instance of the
	// controller
	private RecipeBo _currentRecipe;

	private NutrimentRulesBo _currentRules;

	public void create() {

		_currentRecipe = new RecipeBo(new Recipe());
		_currentRules = new NutrimentRulesBo();

	}

	public void addParameter(ParameterDefinitionData pdd,
			CheckOperatorData cod, double value) {
		_currentRules.addParameter((ParameterDefinitionBo) pdd,
				(CheckOperatorBo) cod, value);
	}

	public CheckedRecipeVo addRecipeIngredient(RecipeData d, float amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public ValidatedRecipeVo checkRecipe() {
		List<NutrimentParameterRuleData> nutrimentParams = new ArrayList<NutrimentParameterRuleData>();
		for (NutrimentRuleBo nrbo : this._currentRules
				.checkRecipe(this._currentRecipe)) {
			nutrimentParams.add((NutrimentParameterRuleData) nrbo);
		}
		ValidatedRecipeVo validatedRecipe = new ValidatedRecipeVo(
				(RecipeData) this._currentRecipe, nutrimentParams);
		return validatedRecipe;
	}

	public void save() {
		HibernateUtil.currentSession().beginTransaction();
		_currentRecipe.save();
		HibernateUtil.currentSession().getTransaction().commit();
	}

}
