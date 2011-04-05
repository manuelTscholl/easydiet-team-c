package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LaborReport generated by hbm2java
 */
public class LaborReport  implements java.io.Serializable {


     private long laborReportId;
     private Date date;
     private Clob notice;
     private SystemUser creator;
     private Set dietParameters = new HashSet(0);

    public LaborReport() {
    }

	
    public LaborReport(Date date, SystemUser creator) {
        this.date = date;
        this.creator = creator;
    }
    public LaborReport(Date date, Clob notice, SystemUser creator, Set dietParameters) {
       this.date = date;
       this.notice = notice;
       this.creator = creator;
       this.dietParameters = dietParameters;
    }
   
    public long getLaborReportId() {
        return this.laborReportId;
    }
    
    public void setLaborReportId(long laborReportId) {
        this.laborReportId = laborReportId;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Clob getNotice() {
        return this.notice;
    }
    
    public void setNotice(Clob notice) {
        this.notice = notice;
    }
    public SystemUser getCreator() {
        return this.creator;
    }
    
    public void setCreator(SystemUser creator) {
        this.creator = creator;
    }
    public Set getDietParameters() {
        return this.dietParameters;
    }
    
    public void setDietParameters(Set dietParameters) {
        this.dietParameters = dietParameters;
    }




}


