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

	private Map<String, NutrimentRuleBo> _parameters;
	private Map<String, NutrimentRuleBo> _alreadyAddedParams;

	public NutrimentRulesBo() {
		_parameters = new HashMap<String, NutrimentRuleBo>();
		_alreadyAddedParams = new HashMap<String, NutrimentRuleBo>();
	}

	public void addParameter(ParameterDefinitionBo parameterdefintion,
			CheckOperatorBo checkOperatorBo, double value, int row,
			ParameterDefinitionUnitBo pdu) {
		try{
		if (!_parameters.containsKey(parameterdefintion.getName())) {
			_parameters.put(parameterdefintion.getName(), new NutrimentRuleBo(
					parameterdefintion, checkOperatorBo, value, row, pdu));
		} else {
			// parameter already added..check if checkoperator is the same
			NutrimentRuleBo toCheck = _parameters.get(parameterdefintion
					.getName());
			if (checkOperatorBo.getName().equals(
					toCheck.getCheckOperatorBo().getName())) {
				throw new ParameterAddingException();
			} else if (_alreadyAddedParams.containsKey(parameterdefintion
					.getName())) {
				throw new ParameterAddingException();
			} else {
				// add parameter to the list which keeps already defined
				_alreadyAddedParams.put(parameterdefintion.getName(),
						new NutrimentRuleBo(parameterdefintion,
								checkOperatorBo, value, row, pdu));
			}
		}
		}catch(Exception ex){
			//bla
		}
		
	}

	public void changeParameter(NutrimentParameterRuleData nprv, double value,
			CheckOperatorBo checkOpBo, int row, ParameterDefinitionUnitBo pdu) {
		NutrimentRuleBo nrbo = (NutrimentRuleBo) nprv;
		// search for the object in the two hashmaps

	}

	public List<NutrimentRuleBo> checkRecipe(RecipeBo recipe) {
		List<NutrimentRuleBo> currParams = new ArrayList<NutrimentRuleBo>();
		Set<NutrimentParameterBo> paramsOfRecipe = recipe
				.getNutrimentParameters();

		// if there are no parameters availible, add them without
		// comparing
		if (recipe.getNutrimentParameters().size() == 0) {
			for (NutrimentRuleBo rb : this._parameters.values()) {
				currParams.add(rb);
			}
			return currParams;
		}

		for (NutrimentParameterBo npbo : recipe.getNutrimentParameters()) {

			NutrimentRuleBo currentParam = _parameters.get(npbo
					.getParameterDefinition().getName());
			// try to get a parameter which is already added
			NutrimentRuleBo alreadyAddedParam = _alreadyAddedParams.get(npbo
					.getParameterDefinition().getName());

			if (currentParam != null) {
				this.doCheck(currentParam, npbo);
				currParams.add(currentParam);
			}
			if (alreadyAddedParam != null) {
				this.doCheck(alreadyAddedParam, npbo);
				currParams.add(alreadyAddedParam);
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
