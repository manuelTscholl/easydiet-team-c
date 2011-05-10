/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: NutrimentRulesBo
 */
package at.easydiet.teamc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.easydiet.teamc.model.data.NutrimentParameterRuleData;

public class NutrimentRulesBo {

	private Map<String, NutrimentRuleBo> _parameters;

	public NutrimentRulesBo() {
		_parameters = new HashMap<String, NutrimentRuleBo>();
	}

	public void addParameter(ParameterDefinitionBo parameterdefintion,
			CheckOperatorBo checkOperatorBo, double value, int row) {
		if (!_parameters.containsKey(parameterdefintion.getName())) {
			_parameters.put(parameterdefintion.getName(), new NutrimentRuleBo(
					parameterdefintion, checkOperatorBo, value, row));
		} else {
			// TODO what to do if already added? call changeParameter or throw
			// error message?
		}
	}

	public void changeParameter(NutrimentParameterRuleData nprv, double value) {
		// TODO setParameter Value
	}

	public List<NutrimentRuleBo> checkRecipe(RecipeBo recipe) {
		List<NutrimentRuleBo> currParams = new ArrayList<NutrimentRuleBo>();
		Set<NutrimentParameterBo> paramsOfRecipe = recipe
				.getNutrimentParameters();
		// if there are no parameters availible of the recipe, add them without
		// comparing
		if (recipe.getNutrimentParameters().size() == 0) {
			for (NutrimentRuleBo rb : this._parameters.values()) {
				currParams.add(rb);
			}
			return currParams;
		}

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
				} else if (currentPar.getCheckOperator().equals("!=")) {
					if (currValue == Double.parseDouble(npbo.getValue())) {
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
				} else if (currentPar.getCheckOperator().equals("=")) {
					if (currValue != Double.parseDouble(npbo.getValue())) {
						currentPar.setIsViolated(true);
					} else {
						currentPar.setIsViolated(false);
					}
				} else if (currentPar.getCheckOperator().equals(">")) {
					if (currValue <= Double.parseDouble(npbo.getValue())) {
						currentPar.setIsViolated(true);
					} else {
						currentPar.setIsViolated(false);
					}
				} else if (currentPar.getCheckOperator().equals("<")) {
					if (currValue >= Double.parseDouble(npbo.getValue())) {
						currentPar.setIsViolated(true);
					} else {
						currentPar.setIsViolated(false);
					}
				}

			}// TODO implement missing checkoperators
			currParams.add(currentPar);
		}

		return currParams;
	}
}
