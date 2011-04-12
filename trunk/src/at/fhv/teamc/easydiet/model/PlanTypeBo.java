package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.PlanType;




/**
 * PlanTypeBo generated by hbm2java
 */
public class PlanTypeBo  implements java.io.Serializable, Saveable {


     private PlanType _PlanType;

    private PlanTypeBo() {
    }

    public PlanTypeBo(PlanType planType) {
        this._PlanType=planType;
    }

    public PlanTypeBo(String name) {
        this(new PlanType(name));
    }
   
    public String getName() {
        return this._PlanType.getName();
    }
    
    public void setName(String name) {
        this._PlanType.setName(name);
    }

    /**
     * @param PlanType the _PlanType to set
     */
    public void setPlanType(PlanType PlanType) {
        this._PlanType = PlanType;
    }

    /**
     * @return the _PlanType
     */
    protected PlanType getPlanType() {
        return _PlanType;
    }

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


