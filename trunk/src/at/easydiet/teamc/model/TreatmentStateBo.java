package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.TreatmentState;

/**
 * TreatmentStateBo generated by hbm2java
 */
public class TreatmentStateBo  implements java.io.Serializable, Saveable {


    private TreatmentState _TreatmentState;

    private TreatmentStateBo() {
    }

    public TreatmentStateBo(TreatmentState treatmentState)
    {
        this._TreatmentState = treatmentState;
    }

    public TreatmentStateBo(String name) {
       this(new TreatmentState(name));
    }
   
    public String getName() {
        return this.getTreatmentState().getName();
    }
    
    public void setName(String name) {
        this.getTreatmentState().setName(name);
    }

    /**
     * @return the _TreatmentState
     */
    protected TreatmentState getTreatmentState() {
        return _TreatmentState;
    }

    /**
     * @param TreatmentState the _TreatmentState to set
     */
    public void setTreatmentState(TreatmentState TreatmentState) {
        this._TreatmentState = TreatmentState;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


