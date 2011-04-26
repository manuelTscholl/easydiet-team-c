package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.ParameterDefinition;
import at.easydiet.model.ParameterDefinitionUnit;






/**
 * ParameterDefinitionBo generated by hbm2java
 */
public class ParameterDefinitionBo  implements java.io.Serializable, Saveable {

     private ParameterDefinition _ParameterDefinition;

    public ParameterDefinitionBo() {
    }

    public ParameterDefinitionBo(ParameterDefinition parameterDefinition){
        this._ParameterDefinition=parameterDefinition;
    }

    public ParameterDefinitionBo(String name) {
        this(new ParameterDefinition(name));
    }

    public ParameterDefinitionBo(String name, Set<ParameterDefinitionUnitBo> unitsBo, String checkPattern, ParameterDefinitionDataTypeBo type) {
        this(name);
        this._ParameterDefinition.setName(name);
        for (ParameterDefinitionUnitBo parameterDefinitionUnitBo : unitsBo) {
            this._ParameterDefinition.getUnits().add(parameterDefinitionUnitBo.getParameterDefinitionUnit());
        }
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

    public Set<ParameterDefinitionUnitBo> getUnit() {
        Set<ParameterDefinitionUnitBo> temp = new HashSet<ParameterDefinitionUnitBo>(this._ParameterDefinition.getUnits().size());
        for (ParameterDefinitionUnit parameterDefinitionUnit : this._ParameterDefinition.getUnits()) {
            temp.add(new ParameterDefinitionUnitBo(parameterDefinitionUnit));
        }
        return temp;
    }
    
    public void setUnit(Set<ParameterDefinitionUnitBo> unit) {
        for (ParameterDefinitionUnitBo parameterDefinitionUnitBo : unit) {
            this._ParameterDefinition.getUnits().add(parameterDefinitionUnitBo.getParameterDefinitionUnit());
        }
    }
    public String getCheckPattern() {
        return this.getParameterDefinition().getCheckPattern();
    }
    
    public void setCheckPattern(String checkPattern) {
        this.getParameterDefinition().setCheckPattern(checkPattern);
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

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


