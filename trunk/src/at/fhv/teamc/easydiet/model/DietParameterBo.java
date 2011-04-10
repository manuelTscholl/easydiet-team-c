package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.DietParameter;
import java.util.Date;

/**
 * DietParameter generated by hbm2java
 */
public class DietParameterBo extends at.fhv.teamc.easydiet.model.DietParameterTemplateBo implements java.io.Serializable {


     private DietParameter _dieDietParameter;

    public DietParameterBo() {
    }

    public DietParameterBo(DietParameter dietparameter){
        this._dieDietParameter=dietparameter;
    }
	
    public DietParameterBo(CheckOperatorBo checkOperator, DietParameterTypeBo dietParameterType, ParameterDefinitionBo parameterDefinition) {
        super(checkOperator, dietParameterType, parameterDefinition);        
    }
    public DietParameterBo(CheckOperatorBo checkOperator, int duration, String value, DietParameterTypeBo dietParameterType, ParameterDefinitionBo parameterDefinition, Date start) {
        super(checkOperator, duration, value, dietParameterType, parameterDefinition);        
        this._dieDietParameter.setStart(start);
    }
   
    public Date getStart() {
        return getDieDietParameter().getStart();
    }
    
    public void setStart(Date start) {
        getDieDietParameter().setStart(start);
    }

    /**
     * @param dieDietParameter the _dieDietParameter to set
     */
    public void setDieDietParameter(DietParameter dieDietParameter) {
        this._dieDietParameter = dieDietParameter;
    }

    /**
     * @return the _dieDietParameter
     */
    protected DietParameter getDieDietParameter() {
        return _dieDietParameter;
    }




}


