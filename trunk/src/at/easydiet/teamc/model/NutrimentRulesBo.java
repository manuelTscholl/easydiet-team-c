/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: NutrimentRulesBo
 */
package at.easydiet.teamc.model;

import at.easydiet.teamc.model.data.NutrimentParameterRuleData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NutrimentRulesBo {

	private Map<String, NutrimentRuleBo> _parameters;

	public NutrimentRulesBo() {
		_parameters = new HashMap<String, NutrimentRuleBo>();
	}

	public void addParameter(ParameterDefinitionBo parameterdefintion,
			CheckOperatorBo checkOperatorBo, double value) {
		_parameters.put(parameterdefintion.getName(), new NutrimentRuleBo(
				parameterdefintion, checkOperatorBo,value));
	}

	public void changeParameter(NutrimentParameterRuleData nprv, double value) {
		// TODO setParameter Value
	}

	public List<NutrimentRuleBo> checkRecipe(RecipeBo recipe) {
		List<NutrimentRuleBo> currParams = new ArrayList<NutrimentRuleBo>();
		Set<NutrimentParameterBo> paramsOfRecipe = recipe
				.getNutrimentParameters();

		for (NutrimentParameterBo npbo : recipe.getNutrimentParameters()) {
			NutrimentRuleBo currentPar = _parameters.get(npbo
					.getParameterDefinition().getName());
			if (currentPar != null) {
				double currValue = currentPar.getValue();
				if (currentPar.getCheckOperator().equals("<=")) {
					if (currValue > Double.parseDouble(npbo.getValue())) {
						currentPar.setIsViolated(true);

					} else {
						currentPar.setIsViolated(false);
					}
				} else if (currentPar.getCheckOperator().equals(">=")) {
					if (currValue < Double.parseDouble(npbo.getValue())) {
						currentPar.setIsViolated(true);
					} else {
						currentPar.setIsViolated(false);
					}
				}//TODO implement missing checkoperators
				currParams.add(currentPar);
			}

		}
		return currParams;
	}

}
