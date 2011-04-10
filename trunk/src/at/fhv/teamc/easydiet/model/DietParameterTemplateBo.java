package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.DietParameterTemplate;




/**
 * DietParameterTemplate generated by hbm2java
 */
public class DietParameterTemplateBo  implements java.io.Serializable {

    private DietParameterTemplate _dietParameterTemplate;
     private CheckOperatorBo _checkOperatorBo;
     private DietParameterTypeBo _dietParameterTypeBo;
     private ParameterDefinitionBo _parameterDefinitionBo;

    public DietParameterTemplateBo() {
    }

    public DietParameterTemplateBo(DietParameterTemplate dietParameterTemplate){
        this._dietParameterTemplate=dietParameterTemplate;
    }

	
    public DietParameterTemplateBo(CheckOperatorBo checkOperatorBo, DietParameterTypeBo dietParameterType, ParameterDefinitionBo parameterDefinition) {
        this._checkOperatorBo=checkOperatorBo;
        this._dietParameterTypeBo = dietParameterType;
        this._parameterDefinitionBo = parameterDefinition;
    }
    public DietParameterTemplateBo(CheckOperatorBo checkOperator, int duration, String value, DietParameterTypeBo dietParameterType, ParameterDefinitionBo parameterDefinition) {
       this(checkOperator, dietParameterType, parameterDefinition);
       _dietParameterTemplate.setDuration(duration);
       _dietParameterTemplate.setValue(value);
    }
   
    public long getDietParameterTemplateId() {
        return getDietParameterTemplate().getDietParameterTemplateId();
    }
    
    public void setDietParameterTemplateId(long dietParameterTemplateId) {
        this.getDietParameterTemplate().setDietParameterTemplateId(dietParameterTemplateId);
    }
    public CheckOperatorBo getCheckOperatorBo() {
        return this._checkOperatorBo;
    }
    
    public void setCheckOperatorBo(CheckOperatorBo checkOperator) {
        this.getDietParameterTemplate().setCheckOperator(checkOperator.getCheckoperator());
        this._checkOperatorBo = checkOperator;
    }
    public int getDuration() {
        return getDietParameterTemplate().getDuration();
    }
    
    public void setDuration(int duration) {
        this.getDietParameterTemplate().setDuration(duration);
    }
    public String getValue() {
        return getDietParameterTemplate().getValue();
    }
    
    public void setValue(String value) {
        this.getDietParameterTemplate().setValue(value);
    }
    public DietParameterTypeBo getDietParameterType() {
        return this._dietParameterTypeBo;
    }
    
    public void setDietParameterType(DietParameterTypeBo dietParameterType) {
        this.getDietParameterTemplate().setDietParameterType(dietParameterType.getDietParameterType());
        this._dietParameterTypeBo = dietParameterType;
    }
    public ParameterDefinitionBo getParameterDefinition() {
        return this._parameterDefinitionBo;
    }
    
    public void setParameterDefinition(ParameterDefinitionBo parameterDefinition) {
        this.getDietParameterTemplate().setParameterDefinition(parameterDefinition.getParameterDefinition());
        this._parameterDefinitionBo = parameterDefinition;
    }

    /**
     * @param dietParameterTemplate the _dietParameterTemplate to set
     */
    public void setDietParameterTemplate(DietParameterTemplate dietParameterTemplate) {
        this._dietParameterTemplate = dietParameterTemplate;
    }

    /**
     * @return the _dietParameterTemplate
     */
    protected DietParameterTemplate getDietParameterTemplate() {
        return _dietParameterTemplate;
    }




}


