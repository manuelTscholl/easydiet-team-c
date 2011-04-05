package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.Date;
import java.util.Set;

/**
 * NutritionProtocol generated by hbm2java
 */
public class NutritionProtocol extends at.easydiet.model.DietPlan implements java.io.Serializable {


     private Date date;
     private String contact;
     private Clob notice;

    public NutritionProtocol() {
    }

	
    public NutritionProtocol(String name, Date createdOn, PlanType planType, SystemUser creator, Date date) {
        super(name, createdOn, planType, creator);        
        this.date = date;
    }
    public NutritionProtocol(String name, Date createdOn, PlanType planType, Set dietParameters, SystemUser creator, Set timeSpans, Date date, String contact, Clob notice) {
        super(name, createdOn, planType, dietParameters, creator, timeSpans);        
       this.date = date;
       this.contact = contact;
       this.notice = notice;
    }
   
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    public Clob getNotice() {
        return this.notice;
    }
    
    public void setNotice(Clob notice) {
        this.notice = notice;
    }




}


