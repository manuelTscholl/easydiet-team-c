/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: NutrimentRulesBo
 */
package at.easydiet.teamc.model;

import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutrimentRulesBo {

    private Map<String,NutrimentRuleBo> _parameters;

    public NutrimentRulesBo() {
        _parameters=new HashMap<String, NutrimentRuleBo>();
    }

    public void addParameter(ParameterDefinitionBo parameterdefintion, CheckOperatorBo checkOperatorBo){
        _parameters.put(parameterdefintion.getName(), new NutrimentRuleBo(parameterdefintion, checkOperatorBo));
    }

    public void changeParameter(NutrimentParameterRuleData nprv, double value){
        //TODO setParameter Value
    }

    public List<NutrimentParameterRuleData> checkRecipe(RecipeBo recipe) {

        throw new UnsupportedOperationException();
    }

}
