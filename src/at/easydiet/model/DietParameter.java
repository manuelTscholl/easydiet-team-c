package at.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.util.Date;

/**
 * DietParameter generated by hbm2java
 */
public class DietParameter extends at.easydiet.model.DietParameterTemplate implements java.io.Serializable {


     private Date start;

    public DietParameter() {
    }

	
    public DietParameter(CheckOperator checkOperator, DietParameterType dietParameterType, ParameterDefinition parameterDefinition) {
        super(checkOperator, dietParameterType, parameterDefinition);        
    }
    public DietParameter(CheckOperator checkOperator, int duration, String value, DietParameterType dietParameterType, ParameterDefinition parameterDefinition, Date start) {
        super(checkOperator, duration, value, dietParameterType, parameterDefinition);        
       this.start = start;
    }
   
    public Date getStart() {
        return this.start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }




}


