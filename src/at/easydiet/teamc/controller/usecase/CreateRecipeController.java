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
import at.easydiet.teamc.exception.NutrimentRuleException;
import at.easydiet.teamc.model.CheckOperatorBo;
import at.easydiet.teamc.model.NutrimentRuleBo;
import at.easydiet.teamc.model.NutrimentRulesBo;
import at.easydiet.teamc.model.ParameterDefinitionBo;
import at.easydiet.teamc.model.ParameterDefinitionUnitBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.ValidatedRecipeVo;

public class CreateRecipeController {

	// the recipe, which will be created and edited by this instance of the
	// controller
	private RecipeBo _currentRecipe;
	private NutrimentRulesBo _currentRules;

	/**
	 * Constructor
	 */
	public CreateRecipeController() {

		_currentRecipe = new RecipeBo(new Recipe());
		_currentRules = new NutrimentRulesBo();

	}

	public ValidatedRecipeVo addParameter(ParameterDefinitionData pdd,
			CheckOperatorData cod, double value,
			ParameterDefinitionUnitData pdud) throws NutrimentRuleException{
		_currentRules.addParameter((ParameterDefinitionBo) pdd,
				(CheckOperatorBo) cod, value, (ParameterDefinitionUnitBo) pdud);
		return checkRecipe();
	}

	public ValidatedRecipeVo addRecipeIngredient(RecipeData d,
			ParameterDefinitionUnitData unit, float amount) {

		_currentRecipe.addRecipeIngredient((RecipeBo) d, amount,
				(ParameterDefinitionUnitBo) unit);

		return this.checkRecipe();
	}

	public ValidatedRecipeVo changeParameter(NutrimentParameterRuleData ndr,
			CheckOperatorData checkOperator, double value,
			ParameterDefinitionUnitData pdud, ParameterDefinitionData pdd) throws NutrimentRuleException {

		_currentRules.changeParameter((NutrimentRuleBo) ndr,
				(CheckOperatorBo) checkOperator, value,
				(ParameterDefinitionUnitBo) pdud, (ParameterDefinitionBo) pdd);
		return checkRecipe();
	}

	public ValidatedRecipeVo changeRecipeIngredient(float amount,
			RecipeData rd, ParameterDefinitionUnitData pdu) {

		_currentRecipe.changeRecipeIngredient(amount, (RecipeBo) rd,
				(ParameterDefinitionUnitBo) pdu);

		return checkRecipe();
	}

	private ValidatedRecipeVo checkRecipe() {
		List<NutrimentParameterRuleData> nutrimentParams = new ArrayList<NutrimentParameterRuleData>();
		for (NutrimentRuleBo nrbo : this._currentRules
				.checkRecipe(this._currentRecipe)) {
			nutrimentParams.add(nrbo);
		}
		ValidatedRecipeVo validatedRecipe = new ValidatedRecipeVo(
				this._currentRecipe, nutrimentParams);
		return validatedRecipe;
	}

         /*
         * Remove a parameter
         *
         * @param param
         * @return
         */
	public ValidatedRecipeVo removeParameter(NutrimentParameterRuleData param) {
		_currentRules.removeParameter((NutrimentRuleBo) param);
                return checkRecipe();
	}

         /*
         * Remove a Recipe
         *
         * @param param
         * @return
         */
	public ValidatedRecipeVo removeRecipeIngredient(RecipeData rd) {
                _currentRecipe.removeRecipeIngredient((RecipeBo) rd);
		return checkRecipe();
	}

        public void save(String recipeName, String preparation, String description, double preaparationTime, int difficulty) {
		HibernateUtil.currentSession().beginTransaction();
		_currentRecipe.save();
		HibernateUtil.currentSession().getTransaction().commit();
	}
}
