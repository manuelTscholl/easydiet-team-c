package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.PatientStateType;




/**
 * PatientStateTypeBo generated by hbm2java
 */
public class PatientStateTypeBo  implements java.io.Serializable, Saveable {

     private PatientStateType _PatientStateType;

    private PatientStateTypeBo() {
    }

    public PatientStateTypeBo(PatientStateType patientStateType){
        this._PatientStateType=patientStateType;
    }


    public PatientStateTypeBo(String name) {
        this(new PatientStateType(name));
    }
   
    public String getName() {
        return this._PatientStateType.getName();
    }
    
    public void setName(String name) {
        this._PatientStateType.setName(name);
    }

    /**
     * @return the _PatientStateType
     */
    protected PatientStateType getPatientStateType() {
        return _PatientStateType;
    }

    /**
     * @param PatientStateType the _PatientStateType to set
     */
    public void setPatientStateType(PatientStateType PatientStateType) {
        this._PatientStateType = PatientStateType;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


