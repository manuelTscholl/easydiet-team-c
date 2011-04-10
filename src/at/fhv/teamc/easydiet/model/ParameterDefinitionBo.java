package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.ParameterDefinition;




/**
 * ParameterDefinitionBo generated by hbm2java
 */
public class ParameterDefinitionBo  implements java.io.Serializable {


     private ParameterDefinitionDataTypeBo _type;
     private ParameterDefinition _ParameterDefinition;

    public ParameterDefinitionBo() {
    }

    public ParameterDefinitionBo(ParameterDefinition parameterDefinition, ParameterDefinitionDataTypeBo type) {
        this._type=type;
    }

    public ParameterDefinitionBo(ParameterDefinition parameterDefinition, String name, String unit, String checkPattern, ParameterDefinitionDataTypeBo type) {
        this(parameterDefinition, type);
        this._ParameterDefinition.setName(name);
        this._ParameterDefinition.setUnit(unit);
        this._ParameterDefinition.setCheckPattern(checkPattern);
    }
   
    public long getParameterDefinitionId() {
        return getParameterDefinition().getParameterDefinitionId();
    }
    
    public void setParameterDefinitionId(long parameterDefinitionId) {
        this.getParameterDefinition().setParameterDefinitionId(parameterDefinitionId);
    }
    public String getName() {
        return this.getParameterDefinition().getName();
    }
    
    public void setName(String name) {
        this.getParameterDefinition().setName(name);
    }
    public String getUnit() {
        return this.getParameterDefinition().getUnit();
    }
    
    public void setUnit(String unit) {
        this.getParameterDefinition().setUnit(unit);
    }
    public String getCheckPattern() {
        return this.getParameterDefinition().getCheckPattern();
    }
    
    public void setCheckPattern(String checkPattern) {
        this.getParameterDefinition().setCheckPattern(checkPattern);
    }
    public ParameterDefinitionDataTypeBo getType() {
        return this._type;
    }
    
    public void setType(ParameterDefinitionDataTypeBo type) {
        this._ParameterDefinition.setType(type.getParameterDefinitionDataType());
        this._type = type;
    }

    /**
     * @return the _ParameterDefinition
     */
    protected ParameterDefinition getParameterDefinition() {
        return _ParameterDefinition;
    }

    /**
     * @param ParameterDefinition the _ParameterDefinition to set
     */
    public void setParameterDefinition(ParameterDefinition ParameterDefinition) {
        this._ParameterDefinition = ParameterDefinition;
    }




}

