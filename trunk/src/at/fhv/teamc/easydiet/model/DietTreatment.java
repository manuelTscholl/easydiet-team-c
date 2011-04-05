package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DietTreatment generated by hbm2java
 */
public class DietTreatment  implements java.io.Serializable {


     private long dietTreatmentId;
     private Date start;
     private int duration;
     private String name;
     private Set nutritionProtocols = new HashSet(0);
     private Set dietPlans = new HashSet(0);
     private Set dietParameters = new HashSet(0);
     private Set patientStates = new HashSet(0);
     private Set systemUsers = new HashSet(0);
     private Set contactJournals = new HashSet(0);
     private TreatmentState treatmentState;

    public DietTreatment() {
    }

	
    public DietTreatment(Date start, int duration, String name, TreatmentState treatmentState) {
        this.start = start;
        this.duration = duration;
        this.name = name;
        this.treatmentState = treatmentState;
    }
    public DietTreatment(Date start, int duration, String name, Set nutritionProtocols, Set dietPlans, Set dietParameters, Set patientStates, Set systemUsers, Set contactJournals, TreatmentState treatmentState) {
       this.start = start;
       this.duration = duration;
       this.name = name;
       this.nutritionProtocols = nutritionProtocols;
       this.dietPlans = dietPlans;
       this.dietParameters = dietParameters;
       this.patientStates = patientStates;
       this.systemUsers = systemUsers;
       this.contactJournals = contactJournals;
       this.treatmentState = treatmentState;
    }
   
    public long getDietTreatmentId() {
        return this.dietTreatmentId;
    }
    
    public void setDietTreatmentId(long dietTreatmentId) {
        this.dietTreatmentId = dietTreatmentId;
    }
    public Date getStart() {
        return this.start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getNutritionProtocols() {
        return this.nutritionProtocols;
    }
    
    public void setNutritionProtocols(Set nutritionProtocols) {
        this.nutritionProtocols = nutritionProtocols;
    }
    public Set getDietPlans() {
        return this.dietPlans;
    }
    
    public void setDietPlans(Set dietPlans) {
        this.dietPlans = dietPlans;
    }
    public Set getDietParameters() {
        return this.dietParameters;
    }
    
    public void setDietParameters(Set dietParameters) {
        this.dietParameters = dietParameters;
    }
    public Set getPatientStates() {
        return this.patientStates;
    }
    
    public void setPatientStates(Set patientStates) {
        this.patientStates = patientStates;
    }
    public Set getSystemUsers() {
        return this.systemUsers;
    }
    
    public void setSystemUsers(Set systemUsers) {
        this.systemUsers = systemUsers;
    }
    public Set getContactJournals() {
        return this.contactJournals;
    }
    
    public void setContactJournals(Set contactJournals) {
        this.contactJournals = contactJournals;
    }
    public TreatmentState getTreatmentState() {
        return this.treatmentState;
    }
    
    public void setTreatmentState(TreatmentState treatmentState) {
        this.treatmentState = treatmentState;
    }




}


