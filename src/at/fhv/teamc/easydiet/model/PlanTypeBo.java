package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.PlanType;




/**
 * PlanTypeBo generated by hbm2java
 */
public class PlanTypeBo  implements java.io.Serializable {


     private PlanType _PlanType;

    public PlanTypeBo() {
    }

    public PlanTypeBo(PlanType planType) {
        this._PlanType=planType;
    }

    public PlanTypeBo(PlanType planType, String name) {
        this(planType);
       this._PlanType.setName(name);
    }
   
    public String getName() {
        return this.getPlanType().getName();
    }
    
    public void setName(String name) {
        this.getPlanType().setName(name);
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
    public PlanType getPlanType() {
        return _PlanType;
    }




}


