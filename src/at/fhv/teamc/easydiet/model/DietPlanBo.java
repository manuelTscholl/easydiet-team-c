package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.DietPlan;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DietPlanBo generated by hbm2java
 */
public class DietPlanBo  implements java.io.Serializable {


    
    private PlanTypeBo _planType;
    private SystemUserBo _creator;
     
    private DietPlan _DietPlan;

    public DietPlanBo() {
    }

    public DietPlanBo(DietPlan dietPlan){
        this._DietPlan=dietPlan;
    }

	
    public DietPlanBo(DietPlan dietPlan, String name, Date createdOn, PlanTypeBo planTypeBo, SystemUserBo creatorBo) {
        this(dietPlan);
        this._planType = planTypeBo;
        this._creator = creatorBo;
        this._DietPlan.setName(name);
        this._DietPlan.setCreatedOn(createdOn);

        this._DietPlan.setPlanType(planTypeBo.getPlanType());
        this._DietPlan.setCreator(creatorBo.getSystemUser());
    }
    public DietPlanBo(DietPlan dietPlan, String name, Date createdOn, PlanTypeBo planType, Set dietParameters, SystemUserBo creator, Set timeSpans) {
       this(dietPlan, name, createdOn, planType, creator);
       this._DietPlan.setDietParameters(dietParameters);
       this._DietPlan.setTimeSpans(timeSpans);
    }
   
    public long getDietPlanId() {
        return this.getDietPlan().getDietPlanId();
    }
    
    public void setDietPlanId(long dietPlanId) {
        this.getDietPlan().setDietPlanId(dietPlanId);
    }
    public String getName() {
        return this.getDietPlan().getName();
    }
    
    public void setName(String name) {
        this.getDietPlan().setName(name);
    }
    public Date getCreatedOn() {
        return this.getDietPlan().getCreatedOn();
    }
    
    public void setCreatedOn(Date createdOn) {
        this.getDietPlan().setCreatedOn(createdOn);
    }
    public PlanTypeBo getPlanType() {
        return this._planType;
    }
    
    public void setPlanType(PlanTypeBo planType) {
        this.getDietPlan().setPlanType(planType.getPlanType());
        this._planType = planType;
    }
    public Set getDietParameters() {
        return this.getDietPlan().getDietParameters();
    }
    
    public void setDietParameters(Set dietParameters) {
        this.getDietPlan().setDietParameters(dietParameters);
    }
    public SystemUserBo getCreator() {
        return this._creator;
    }
    
    public void setCreator(SystemUserBo creator) {
        this.getDietPlan().setCreator(creator.getSystemUser());
        this._creator = creator;
    }
    public Set getTimeSpans() {
        return this.getDietPlan().getTimeSpans();
    }
    
    public void setTimeSpans(Set timeSpans) {
        this.getDietPlan().setTimeSpans(timeSpans);
    }

    /**
     * @return the _DietPlan
     */
    protected DietPlan getDietPlan() {
        return _DietPlan;
    }

    /**
     * @param DietPlan the _DietPlan to set
     */
    public void setDietPlan(DietPlan DietPlan) {
        this._DietPlan = DietPlan;
    }




}

