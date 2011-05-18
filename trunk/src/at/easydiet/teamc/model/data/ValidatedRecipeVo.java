package at.easydiet.teamc.model.data;

import java.util.List;

/**
 * Represents the class for validated recipes.
 * 
 * @author Michael
 */
public class ValidatedRecipeVo {

	private RecipeData _recipe;
	private List<NutrimentParameterRuleData> _nutrimentParameterRulesData;

	/**
	 * Instantiates a new validated recipe vo.
	 */
	private ValidatedRecipeVo() {
	}

	/**
	 * Instantiates a new validated recipe vo.
	 * 
	 * @param recipe the recipe
	 * @param nutrimentParameterRulesData the nutriment parameter rules data
	 */
	public ValidatedRecipeVo(RecipeData recipe,
			List<NutrimentParameterRuleData> nutrimentParameterRulesData) {

		_recipe = recipe;
		_nutrimentParameterRulesData = nutrimentParameterRulesData;
	}

	/**
	 * Gets the recipe.
	 * 
	 * @return the recipe
	 */
	public RecipeData getRecipe() {
		return _recipe;
	}

	/**
	 * Gets the nutriment parameter rules data.
	 * 
	 * @return the nutriment parameter rules data
	 */
	public List<NutrimentParameterRuleData> getNutrimentParameterRulesData() {
		return _nutrimentParameterRulesData;
	}

}
