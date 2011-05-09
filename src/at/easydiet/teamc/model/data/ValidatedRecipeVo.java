package at.easydiet.teamc.model.data;

import java.util.List;

public class ValidatedRecipeVo {
	
	private RecipeData _recipe;
	private List<NutrimentParameterRuleData> _nutrimentParameterRulesData;
	
	private ValidatedRecipeVo() {
	}

	public ValidatedRecipeVo(RecipeData recipe,
			List<NutrimentParameterRuleData> nutrimentParameterRulesData) {
		
		_recipe = recipe;
		_nutrimentParameterRulesData = nutrimentParameterRulesData;
	}
	
	public RecipeData getRecipe() {
		return _recipe;
	}

	public List<NutrimentParameterRuleData> getNutrimentParameterRulesData() {
		return _nutrimentParameterRulesData;
	}
	
	

	

}
