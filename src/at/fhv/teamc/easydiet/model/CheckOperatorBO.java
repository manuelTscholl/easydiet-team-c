package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.CheckOperator;

/**
 * CheckOperator generated by hbm2java
 */
public class CheckOperatorBo  implements java.io.Serializable {

    private CheckOperator _checkoperator;

    public CheckOperatorBo() {
    }

    public CheckOperatorBo(CheckOperator checkoperator) {
        setCheckOperator(checkoperator);
    }

    public CheckOperatorBo(CheckOperator checkoperator, String name) {
        this(checkoperator);
       _checkoperator.setName(name);
    }

    public void setCheckOperator(CheckOperator checkOperator){
        _checkoperator=checkOperator;
    }
   
    public String getName() {
        return _checkoperator.getName();
    }
    
    public void setName(String name) {
        _checkoperator.setName(name);
    }




}

