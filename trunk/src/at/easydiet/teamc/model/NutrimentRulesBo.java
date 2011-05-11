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


public class NutrimentRulesBo {

	private Map<String, HashMap<String, NutrimentRuleBo>> _parameters;
	private Map<String, NutrimentRuleBo> _alreadyAddedParams;

	public NutrimentRulesBo() {
		_parameters = new HashMap<String, HashMap<String, NutrimentRuleBo>>();
		_alreadyAddedParams = new HashMap<String, NutrimentRuleBo>();
	}

	public void addParameter(ParameterDefinitionBo parameterdefintion,
			CheckOperatorBo checkOperatorBo, double value,
			ParameterDefinitionUnitBo pdu) {
		// if the parameter has not been added yet
		if (!_parameters.containsKey(parameterdefintion.getName())) {
			_parameters.put(parameterdefintion.getName(),
					new HashMap<String, NutrimentRuleBo>());
			HashMap<String, NutrimentRuleBo> currMap = _parameters
					.get(parameterdefintion.getName());
			currMap.put(checkOperatorBo.getName(), new NutrimentRuleBo(
					parameterdefintion, checkOperatorBo, value, pdu));

		} else {// parameter already added. put the parameter with a new
				// checkoperator into the map
			HashMap<String, NutrimentRuleBo> currMap = _parameters
					.get(parameterdefintion.getName());
			// check if checkoperator already contained
			if (currMap.containsKey(checkOperatorBo.getName())) {
				try {
					throw new Exception(
							"Parameter already added with this parameter");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				currMap.put(checkOperatorBo.getName(), new NutrimentRuleBo(
						parameterdefintion, checkOperatorBo, value, pdu));
			}
		}
	}

	public void changeParameter(NutrimentRuleBo nrbo,
			CheckOperatorBo checkOpBo, double value, ParameterDefinitionUnitBo pdu) {

		HashMap<String, NutrimentRuleBo> currMap = _parameters
				.get(nrbo.getName());

		NutrimentRuleBo currRule = currMap.get(nrbo.getCheckOperatorBo().getName());

		if (currRule != null) {
                        currMap.remove(nrbo.getCheckOperatorBo().getName());
                        //change Rule
			currRule.setCheckOperatorBo(checkOpBo);
			currRule.setParameterDefintionBo(nrbo.getParameterDefintionBo());
			currRule.setValue(value);
			currRule.setParameterdefinition(pdu);

                        currMap.put(nrbo.getCheckOperatorBo().getName(), currRule);
		} else {
			//TODO 
		}

	}

	public List<NutrimentRuleBo> checkRecipe(RecipeBo recipe) {
		List<NutrimentRuleBo> currParams = new ArrayList<NutrimentRuleBo>();
		Set<NutrimentParameterBo> paramsOfRecipe = recipe
				.getNutrimentParameters();

		// if there are no parameters availible, add them without
		// comparing
		if (recipe.getNutrimentParameters().size() == 0) {
			for (HashMap<String, NutrimentRuleBo> rb : this._parameters
					.values()) {
				for (NutrimentRuleBo nrbo : rb.values()) {
					currParams.add(nrbo);
				}
			}
			return currParams;
		}
		for (NutrimentParameterBo npbo : recipe.getNutrimentParameters()) {
			for (HashMap<String, NutrimentRuleBo> rb : this._parameters
					.values()) {
				//if the parameter name matches with the parameter in the map
				if (rb.containsKey(npbo.getParameterDefinition().getName())) {
					for (NutrimentRuleBo nrbo : rb.values()) {
						// check if parameter is valid
						doCheck(nrbo, npbo);
						currParams.add(nrbo);
					}
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