package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DietPlan generated by hbm2java
 */
public class DietPlan  implements java.io.Serializable {


     private long dietPlanId;
     private String name;
     private Date createdOn;
     private PlanType planType;
     private Set dietParameters = new HashSet(0);
     private SystemUserBo creator;
     private Set timeSpans = new HashSet(0);

    public DietPlan() {
    }

	
    public DietPlan(String name, Date createdOn, PlanType planType, SystemUserBo creator) {
        this.name = name;
        this.createdOn = createdOn;
        this.planType = planType;
        this.creator = creator;
    }
    public DietPlan(String name, Date createdOn, PlanType planType, Set dietParameters, SystemUserBo creator, Set timeSpans) {
       this.name = name;
       this.createdOn = createdOn;
       this.planType = planType;
       this.dietParameters = dietParameters;
       this.creator = creator;
       this.timeSpans = timeSpans;
    }
   
    public long getDietPlanId() {
        return this.dietPlanId;
    }
    
    public void setDietPlanId(long dietPlanId) {
        this.dietPlanId = dietPlanId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    public PlanType getPlanType() {
        return this.planType;
    }
    
    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }
    public Set getDietParameters() {
        return this.dietParameters;
    }
    
    public void setDietParameters(Set dietParameters) {
        this.dietParameters = dietParameters;
    }
    public SystemUserBo getCreator() {
        return this.creator;
    }
    
    public void setCreator(SystemUserBo creator) {
        this.creator = creator;
    }
    public Set getTimeSpans() {
        return this.timeSpans;
    }
    
    public void setTimeSpans(Set timeSpans) {
        this.timeSpans = timeSpans;
    }




}


