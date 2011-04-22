package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.DietParameter;
import at.easydiet.model.LaborReport;



/**
 * LaborReportBo generated by hbm2java
 */
public class LaborReportBo  implements java.io.Serializable, Saveable {

     private SystemUserBo _creator;

     private LaborReport _LaborReport;

    private LaborReportBo() {
    }

    public LaborReportBo(LaborReport laborReport){
        this._LaborReport = laborReport;
    }

	

    public LaborReportBo(Date date, SystemUserBo creatorBo) {
        this(new LaborReport(date, creatorBo.getSystemUser()));
        this._creator=creatorBo;
    }
    public LaborReportBo(Date date, Clob notice, SystemUserBo creatorBo, Set<DietParameterBo> dietParametersBo) {
       this(date, creatorBo);
       this._LaborReport.setNotice(notice);

        for (DietParameterBo dietParameterBo : dietParametersBo) {
            this._LaborReport.getDietParameters().add(dietParameterBo.getDietParameter());
        }
       
    }
   
    public long getLaborReportId() {
        return this.getLaborReport().getLaborReportId();
    }
    
    public void setLaborReportId(long laborReportId) {
        this.getLaborReport().setLaborReportId(laborReportId);
    }
    public Date getDate() {
        return this.getLaborReport().getDate();
    }
    
    public void setDate(Date date) {
        this.getLaborReport().setDate(date);
    }
    public Clob getNotice() {
        return this.getLaborReport().getNotice();
    }
    
    public void setNotice(Clob notice) {
        this.getLaborReport().setNotice(notice);
    }
    public SystemUserBo getCreator() {
        return this._creator;
    }
    
    public void setCreator(SystemUserBo creatorBo) {
        this.getLaborReport().setCreator(creatorBo.getSystemUser());
        this._creator = creatorBo;
    }
    public Set<DietParameterBo> getDietParameters() {
        Set<DietParameterBo> temp = new HashSet<DietParameterBo>(this._LaborReport.getDietParameters().size());
        for (DietParameter dietParameter : this._LaborReport.getDietParameters()) {
            temp.add(new DietParameterBo(dietParameter));
        }
        return temp;
    }
    
    public void setDietParameters(Set<DietParameterBo> dietParametersBo) {
         for (DietParameterBo dietParameterBo : dietParametersBo) {
            this._LaborReport.getDietParameters().add(dietParameterBo.getDietParameter());
        }
    }

    /**
     * @return the _LaborReport
     */
    protected LaborReport getLaborReport() {
        return _LaborReport;
    }

    /**
     * @param LaborReport the _LaborReport to set
     */
    public void setLaborReport(LaborReport LaborReport) {
        this._LaborReport = LaborReport;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


