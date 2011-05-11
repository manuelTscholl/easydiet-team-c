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

import at.easydiet.teamc.exception.ParameterAddingException;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;

public class NutrimentRulesBo {

	private Map<String, HashMap<CheckOperatorBo, NutrimentRuleBo>> _parameters;
	private Map<String, NutrimentRuleBo> _alreadyAddedParams;

	public NutrimentRulesBo() {
		_parameters = new HashMap<String, HashMap<CheckOperatorBo, NutrimentRuleBo>>();
		_alreadyAddedParams = new HashMap<String, NutrimentRuleBo>();
	}

	public void addParameter(ParameterDefinitionBo parameterdefintion,
			CheckOperatorBo checkOperatorBo, double value,
			ParameterDefinitionUnitBo pdu) {

		if (!_parameters.containsKey(parameterdefintion.getName())) {
			_parameters.put(parameterdefintion.getName(),
					new HashMap<CheckOperatorBo, NutrimentRuleBo>());
			HashMap<CheckOperatorBo, NutrimentRuleBo> sdf = _parameters
					.get(parameterdefintion.getName());
			sdf.put(checkOperatorBo, new NutrimentRuleBo(parameterdefintion,
					checkOperatorBo, value, pdu));

		} else {
			HashMap<CheckOperatorBo, NutrimentRuleBo> sdf = _parameters
					.get(parameterdefintion.getName());
			sdf.put(checkOperatorBo, new NutrimentRuleBo(parameterdefintion,
					checkOperatorBo, value, pdu));
		}
	}

	public void changeParameter(NutrimentParameterRuleData nprv, double value,
			CheckOperatorBo checkOpBo, ParameterDefinitionUnitBo pdu) {
		NutrimentRuleBo nrbo = (NutrimentRuleBo) nprv;

		HashMap<CheckOperatorBo, NutrimentRuleBo> currMap = _parameters
				.get(nrbo.getName());

		NutrimentRuleBo currRule = currMap.get(checkOpBo);

		if (currRule != null) {
			currRule.setCheckOperatorBo(checkOpBo);
			currRule.setParameterDefintionBo(nrbo.getParameterDefintionBo());
			currRule.setValue(value);
			currRule.setParameterdefinition(pdu);
		} else {
			currRule = new NutrimentRuleBo(nrbo.getParameterDefintionBo(),
					checkOpBo, value, pdu);
			currMap.put(checkOpBo, currRule);
		}

	}

	public List<NutrimentRuleBo> checkRecipe(RecipeBo recipe) {
		List<NutrimentRuleBo> currParams = new ArrayList<NutrimentRuleBo>();
		Set<NutrimentParameterBo> paramsOfRecipe = recipe
				.getNutrimentParameters();

		// if there are no parameters availible, add them without
		// comparing
		if (recipe.getNutrimentParameters().size() == 0) {
			for (HashMap<CheckOperatorBo, NutrimentRuleBo> rb : this._parameters
					.values()) {
				for (NutrimentRuleBo nrbo : rb.values()) {
					currParams.add(nrbo);
				}
			}
			return currParams;
		}
		for (NutrimentParameterBo npbo : recipe.getNutrimentParameters()) {
			for (HashMap<CheckOperatorBo, NutrimentRuleBo> rb : this._parameters
					.values()) {
				for (NutrimentRuleBo nrbo : rb.values()) {
					// check if parameter is valid
					doCheck(nrbo, npbo);
					currParams.add(nrbo);
				}
			}
		}

		return currParams;
	}

	private void doCheck(NutrimentRuleBo currentParam, NutrimentParameterBo npbo) {
		double currValue = currentParam.getValue();
		if (currentParam.getCheckOperator().equals("<=")) {
			if (currValue > Double.parseDouble(npbo.getValue())) {
				currentParam.setIsViolated(true);
			} else {
				currentParam.setIsViolated(false);
			}
		} else if (currentParam.getCheckOperator().equals("!=")) {
			if (currValue == Double.parseDouble(npbo.getValue())) {
				currentParam.setIsViolated(true);
			} else {
				currentParam.setIsViolated(false);
			}
		} else if (currentParam.getCheckOperator().equals(">=")) {
			if (currValue < Double.parseDouble(npbo.getValue())) {
				currentParam.setIsViolated(true);
			} else {
				currentParam.setIsViolated(false);
			}
		} else if (currentParam.getCheckOperator().equals("=")) {
			if (currValue != Double.parseDouble(npbo.getValue())) {
				currentParam.setIsViolated(true);
			} else {
				currentParam.setIsViolated(false);
			}
		} else if (currentParam.getCheckOperator().equals(">")) {
			if (currValue <= Double.parseDouble(npbo.getValue())) {
				currentParam.setIsViolated(true);
			} else {
				currentParam.setIsViolated(false);
			}
		} else if (currentParam.getCheckOperator().equals("<")) {
			if (currValue >= Double.parseDouble(npbo.getValue())) {
				currentParam.setIsViolated(true);
			} else {
				currentParam.setIsViolated(false);
			}
		}

	}
}
