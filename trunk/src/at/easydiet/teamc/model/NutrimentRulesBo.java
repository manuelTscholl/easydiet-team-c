/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: NutrimentRulesBo
 */
package at.easydiet.teamc.model;

import at.easydiet.teamc.exception.NutrimentRuleException;
import at.easydiet.teamc.exception.ParameterAddingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a ruleset of NutrimentRules for creating new recipes
 * with specified goals.
 *
 * @author Friedrich Bösch
 */
public class NutrimentRulesBo {

    private Map<String, HashMap<String, NutrimentRuleBo>> _parameters;

    /**
     * Initiates the set.
     */
    public NutrimentRulesBo() {
        _parameters = new HashMap<String, HashMap<String, NutrimentRuleBo>>();
    }

    /**
     * Creates a new NutrimentRule and adds it to the set.
     *
     * @param parameterdefintion ParameterDefintion of the new NutrimentRule.
     * @param checkOperatorBo CheckOperator of the new NutrimentRule.
     * @param value Value of the new NutrimentRule.
     * @param pdu Unit of the new NutrimentRule.
     * @throws NutrimentRuleException if the new NutrimentRule contradicts the
     * existing NutrimentRules of the set.
     */
    public void addParameter(ParameterDefinitionBo parameterdefintion,
            CheckOperatorBo checkOperatorBo, double value,
            ParameterDefinitionUnitBo pdu) throws NutrimentRuleException {
        // if the parameter has not been added yet
        if (!_parameters.containsKey(parameterdefintion.getName())) {
            _parameters.put(parameterdefintion.getName(),
                    new HashMap<String, NutrimentRuleBo>());
            HashMap<String, NutrimentRuleBo> currMap = _parameters.get(parameterdefintion.getName());
            currMap.put(checkOperatorBo.getName(), new NutrimentRuleBo(
                    parameterdefintion, checkOperatorBo, value, pdu));

        } else {// parameter already added. put the parameter with a new
            // checkoperator into the map
            HashMap<String, NutrimentRuleBo> currMap = _parameters.get(parameterdefintion.getName());
            // check if checkoperator already contained
            if (currMap.containsKey(checkOperatorBo.getName())) {
                throw new NutrimentRuleException(
                        "Parameter already exists with that checkOperator!");
            } else {
                currMap.put(checkOperatorBo.getName(), new NutrimentRuleBo(
                        parameterdefintion, checkOperatorBo, value, pdu));
            }
        }
    }

    /**
     * Checks whether a given NutrimentRule is violated or not and sets this
     * information.
     *
     * @param currRule NutrimentRule to check.
     * @throws NutrimentRuleException if the new NutrimentRule contradicts the
     * existing NutrimentRules of the set.
     */
    private void parameterCheck(NutrimentRuleBo currRule) throws NutrimentRuleException {
        // check if values and checkoperators are legal
        HashMap<String, NutrimentRuleBo> currParams = _parameters.get(currRule.getParameterDefintionBo().getName());

        for (NutrimentRuleBo nrb : currParams.values()) {

            if (nrb != null) {
                // if the value is equal
                if (currRule.getValue() == nrb.getValue()) {
                    if (currRule.getCheckOperatorBo().getName().equals("=")
                            && nrb.getCheckOperatorBo().getName().equals("!=")) {
                        currRule.setIsViolated(true);
                        throw new NutrimentRuleException("Operators are not valid");
                    } else if (currRule.getCheckOperatorBo().getName().equals("!=")
                            && nrb.getCheckOperatorBo().getName().equals("=")) {
                        currRule.setIsViolated(true);
                        throw new NutrimentRuleException("Operators are not valid");
                    } else if (currRule.getCheckOperatorBo().getName().equals(">")
                            && nrb.getCheckOperatorBo().getName().equals("<")) {
                        currRule.setIsViolated(true);
                        throw new NutrimentRuleException("Operators are not valid");
                    } else if (currRule.getCheckOperatorBo().getName().equals("<")
                            && nrb.getCheckOperatorBo().getName().equals(">")) {
                        throw new NutrimentRuleException("Operators are not valid");
                    }
                } else { // value is not the same
                    if (currRule.getCheckOperatorBo().getName().equals(">")
                            && nrb.getCheckOperatorBo().getName().equals("<")) {
                        if (currRule.getValue() > nrb.getValue()
                                || currRule.getValue() >= nrb.getValue()) {
                            currRule.setIsViolated(true);
                            throw new NutrimentRuleException("Operators are not valid");
                        }
                    } else if (currRule.getCheckOperatorBo().getName().equals("<")
                            && nrb.getCheckOperatorBo().getName().equals(">")) {
                        if (currRule.getValue() < nrb.getValue()) {
                            currRule.setIsViolated(true);
                            throw new NutrimentRuleException("Operators are not valid");
                        }
                    } else if (currRule.getCheckOperatorBo().getName().equals("<=")
                            && nrb.getCheckOperatorBo().getName().equals(">=")) {
                        if (currRule.getValue() <= nrb.getValue()) {
                            currRule.setIsViolated(true);
                            throw new NutrimentRuleException("Operators are not valid");
                        }
                        currRule.setIsViolated(true);
                    } else if (currRule.getCheckOperatorBo().getName().equals(">=")
                            && nrb.getCheckOperatorBo().getName().equals("<=")) {
                        if (currRule.getValue() >= nrb.getValue()) {
                            currRule.setIsViolated(true);
                            throw new NutrimentRuleException("Operators are not valid");
                        }

                    }
                }
            }
        }
    }

    /**
     * Changes a NutrimentRule and checks whether the changed NutrimentRule would
     * contradict the other existings NutrimentRules.
     *
     * @param parameterdefintion ParameterDefintion of the changed NutrimentRule.
     * @param checkOperatorBo CheckOperator of the changed NutrimentRule.
     * @param value Value of the changed NutrimentRule.
     * @param pdu Unit of the changed NutrimentRule.
     * @throws NutrimentRuleException if the new NutrimentRule contradicts the
     * existing NutrimentRules of the set.
     */
    public void changeParameter(NutrimentRuleBo nrbo,
            CheckOperatorBo checkOpBo, double value,
            ParameterDefinitionUnitBo pdu, ParameterDefinitionBo pdb)
            throws NutrimentRuleException {

        HashMap<String, NutrimentRuleBo> currMap = _parameters.get(nrbo.getName());

        NutrimentRuleBo currRule = currMap.get(nrbo.getCheckOperatorBo().getName());
        NutrimentRuleBo temprule = new NutrimentRuleBo(pdb, checkOpBo, value,
                pdu);

        ParameterDefinitionBo oldpdb = (ParameterDefinitionBo) nrbo.getParameterDefinitionData();

        if (currRule != null) {

            currMap.remove(nrbo.getCheckOperatorBo().getName());

            if (validate(temprule)) {
                if (temprule.IsViolated()) {
                    nrbo.setIsViolated(true);
                } else {
                    nrbo.setIsViolated(false);
                }
                nrbo.setCheckOperatorBo(checkOpBo);
                nrbo.setValue(value);
                nrbo.setParameterdefinitionUnit(pdu);
                nrbo.setParameterDefintionBo(pdb);
                if (currRule.getParameterDefintionBo().getName().equalsIgnoreCase(oldpdb.getName())) {
                    currMap.put(nrbo.getCheckOperatorBo().getName(), nrbo);

                } else {
                    if (_parameters.containsKey(nrbo.getParameterDefintionBo().getName())) {
                        currMap = _parameters.get(nrbo.getParameterDefintionBo().getName());
                        currMap.put(nrbo.getCheckOperatorBo().getName(), nrbo);

                    } else {
                        _parameters.put(nrbo.getParameterDefintionBo().getName(),
                                new HashMap<String, NutrimentRuleBo>());
                        currMap = _parameters.get(nrbo.getParameterDefintionBo().getName());
                        currMap.put(nrbo.getCheckOperatorBo().getName(), nrbo);
                    }
                }
            } else {
                currMap.put(nrbo.getCheckOperatorBo().getName(), nrbo);
                throw new NutrimentRuleException("Parameterchanges not valid!");
            }

        } else {
            //parameter which should be changed is not in the map -->
            addParameter(nrbo.getParameterDefintionBo(), checkOpBo, value, pdu);
        }

    }

    /**
     * Checks a given recipe with all NutrimentRules of this NutrimentRuleSet
     * and returns them in a List.
     *
     * @param recipe Recipe to check.
     * @return List<NutrimentRuleBo> which contain all checked NutrimentRules
     */
    public List<NutrimentRuleBo> checkRecipe(RecipeBo recipe) {
        List<NutrimentRuleBo> currParams = new ArrayList<NutrimentRuleBo>();
        //HashMap<Long,NutrimentParameterBo>paramsOfRecipe = (HashMap<Long, NutrimentParameterBo>) recipe.getNutrimentParametersMap();
        Set<NutrimentParameterBo> paramsOfRecipe=recipe.getNutrimentParameters();
        // if there are no recipe parameters availible, add them without
        // comparing
        if (paramsOfRecipe.size() == 0 || recipe==null) {
            for (HashMap<String, NutrimentRuleBo> rb : this._parameters.values()) {
                for (NutrimentRuleBo nrbo : rb.values()) {
                    currParams.add(nrbo);
                }
            }
            return currParams;
        }
        //TODO don't iterate all recipe.nutrimentparameters but all nutrimentrule.parameters
        for (NutrimentParameterBo npbo : paramsOfRecipe) {
            if (_parameters.containsKey(npbo.getParameterDefinition().getName())) {
                HashMap<String, NutrimentRuleBo> currMap = _parameters.get(npbo.getParameterDefinition().getName());
                for (NutrimentRuleBo nrbo : currMap.values()) {
                    // check if parameter is valid
                    doCheck(nrbo, npbo);
                    currParams.add(nrbo);
                }
            }
        }
       
        return currParams;
    }

    /**
     * Checks NutrimentRule with a given NutrimentParameter.
     *
     * @param currentParam NutrimentRule to check.
     * @param npbo NutrimentParameter to check.
     */
    private void doCheck(NutrimentRuleBo currentParam, NutrimentParameterBo npbo) {
        double currValue = currentParam.getValue();
        if (currentParam.getCheckOperatorBo().getCheckoperator().getName().equals("<=")) {
            if (currValue <Double.parseDouble(npbo.getValue())) {
                currentParam.setIsViolated(true);
            } else {
                currentParam.setIsViolated(false);
            }
        } else if (currentParam.getCheckOperatorBo().getCheckoperator().getName().equals("!=")) {
            if (currValue == Double.parseDouble(npbo.getValue())) {
                currentParam.setIsViolated(true);
            } else {
                currentParam.setIsViolated(false);
            }
        } else if (currentParam.getCheckOperatorBo().getCheckoperator().getName().equals(">=")) {
            if (currValue > Double.parseDouble(npbo.getValue())) {
                currentParam.setIsViolated(true);
            } else {
                currentParam.setIsViolated(false);
            }
        } else if (currentParam.getCheckOperatorBo().getCheckoperator().getName().equals("=")) {
            if (currValue != Double.parseDouble(npbo.getValue())) {
                currentParam.setIsViolated(true);
            } else {
                currentParam.setIsViolated(false);
            }
        } else if (currentParam.getCheckOperatorBo().getCheckoperator().getName().equals(">")) {
            if (currValue >= Double.parseDouble(npbo.getValue())) {
                currentParam.setIsViolated(true);
            } else {
                currentParam.setIsViolated(false);
            }
        } else if (currentParam.getCheckOperatorBo().getCheckoperator().getName().equals("<")) {
            if (currValue <= Double.parseDouble(npbo.getValue())) {
                currentParam.setIsViolated(true);
            } else {
                currentParam.setIsViolated(false);
            }
        }

    }

    /**
     * Validates if the NutrimentRule contradticts the existing NutrimentRuleSet.
     *
     * @param currRule Rule to validate.
     * @return true if the rule is valid and not a duplicate.
     * @throws NutrimentRuleException if the new NutrimentRule contradicts the
     * existing NutrimentRules of the set.
     */
    private boolean validate(NutrimentRuleBo currRule) throws NutrimentRuleException {
        if (_parameters.containsKey(currRule.getName())) {
            HashMap<String, NutrimentRuleBo> currMap = _parameters.get(currRule.getName());
            parameterCheck(currRule);
            if (currMap.containsKey(currRule.getCheckOperatorBo().getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes a NutrimentParameterRule from the existing NutrimentRuleSet.
     *
     * @param param NutrimentRule to remove.
     */
    public void removeParameter(NutrimentRuleBo param) {
        if (_parameters.containsKey(param.getName())) {
            HashMap<String, NutrimentRuleBo> currMap = _parameters.get(param.getName());
            if (currMap.containsKey(param.getCheckOperatorBo().getName())) {
                currMap.remove(param.getCheckOperatorBo().getName());
            }
            if (currMap.size() <= 0) {
                _parameters.remove(param.getParameterDefintionBo().getName());
            }
        }
    }
}
