package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.SystemUserFunction;




/**
 * SystemUserFunctionBo generated by hbm2java
 */
public class SystemUserFunctionBo  implements java.io.Serializable, Saveable {

    private SystemUserFunction _SystemUserFunction;

    private SystemUserFunctionBo() {
    }

    public SystemUserFunctionBo(SystemUserFunction systemUserFunction) {
        this._SystemUserFunction = systemUserFunction;
    }

    public SystemUserFunctionBo(String name) {
        this(new SystemUserFunction(name));
    }
   
    public String getName() {
        return this.getSystemUserFunction().getName();
    }
    
    public void setName(String name) {
        this.getSystemUserFunction().setName(name);
    }

    /**
     * @return the _SystemUserFunction
     */
    protected SystemUserFunction getSystemUserFunction() {
        return _SystemUserFunction;
    }

    /**
     * @param SystemUserFunction the _SystemUserFunction to set
     */
    public void setSystemUserFunction(SystemUserFunction SystemUserFunction) {
        this._SystemUserFunction = SystemUserFunction;
    }

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


