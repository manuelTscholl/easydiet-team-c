package at.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Patient generated by hbm2java
 */
public class Patient  implements java.io.Serializable {


     private long patientId;
     private String insuranceNumber;
     private String forename;
     private String lastname;
     private String title;
     private String street;
     private String zip;
     private String place;
     private String country;
     private Date birthday;
     private String job;
     private String religion;
     private String[] illnesses;
     private String regime;
     private Clob notice;
     private Gender gender;
     private Set familyanamnesis = new HashSet(0);
     private Set patientstate = new HashSet(0);
     private Set laborReports = new HashSet(0);
     private Set treatments = new HashSet(0);
     private Set disfavors = new HashSet(0);

    public Patient() {
    }

	
    public Patient(String insuranceNumber, String forename, String lastname, String title, String street, String zip, String place, String country, Date birthday, Gender gender) {
        this.insuranceNumber = insuranceNumber;
        this.forename = forename;
        this.lastname = lastname;
        this.title = title;
        this.street = street;
        this.zip = zip;
        this.place = place;
        this.country = country;
        this.birthday = birthday;
        this.gender = gender;
    }
    public Patient(String insuranceNumber, String forename, String lastname, String title, String street, String zip, String place, String country, Date birthday, String job, String religion, String[] illnesses, String regime, Clob notice, Gender gender, Set familyanamnesis, Set patientstate, Set laborReports, Set treatments, Set disfavors) {
       this.insuranceNumber = insuranceNumber;
       this.forename = forename;
       this.lastname = lastname;
       this.title = title;
       this.street = street;
       this.zip = zip;
       this.place = place;
       this.country = country;
       this.birthday = birthday;
       this.job = job;
       this.religion = religion;
       this.illnesses = illnesses;
       this.regime = regime;
       this.notice = notice;
       this.gender = gender;
       this.familyanamnesis = familyanamnesis;
       this.patientstate = patientstate;
       this.laborReports = laborReports;
       this.treatments = treatments;
       this.disfavors = disfavors;
    }
   
    public long getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
    public String getInsuranceNumber() {
        return this.insuranceNumber;
    }
    
    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
    public String getForename() {
        return this.forename;
    }
    
    public void setForename(String forename) {
        this.forename = forename;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStreet() {
        return this.street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    public String getZip() {
        return this.zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    public String getReligion() {
        return this.religion;
    }
    
    public void setReligion(String religion) {
        this.religion = religion;
    }
    public String[] getIllnesses() {
        return this.illnesses;
    }
    
    public void setIllnesses(String[] illnesses) {
        this.illnesses = illnesses;
    }
    public String getRegime() {
        return this.regime;
    }
    
    public void setRegime(String regime) {
        this.regime = regime;
    }
    public Clob getNotice() {
        return this.notice;
    }
    
    public void setNotice(Clob notice) {
        this.notice = notice;
    }
    public Gender getGender() {
        return this.gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Set getFamilyanamnesis() {
        return this.familyanamnesis;
    }
    
    public void setFamilyanamnesis(Set familyanamnesis) {
        this.familyanamnesis = familyanamnesis;
    }
    public Set getPatientstate() {
        return this.patientstate;
    }
    
    public void setPatientstate(Set patientstate) {
        this.patientstate = patientstate;
    }
    public Set getLaborReports() {
        return this.laborReports;
    }
    
    public void setLaborReports(Set laborReports) {
        this.laborReports = laborReports;
    }
    public Set getTreatments() {
        return this.treatments;
    }
    
    public void setTreatments(Set treatments) {
        this.treatments = treatments;
    }
    public Set getDisfavors() {
        return this.disfavors;
    }
    
    public void setDisfavors(Set disfavors) {
        this.disfavors = disfavors;
    }




}


