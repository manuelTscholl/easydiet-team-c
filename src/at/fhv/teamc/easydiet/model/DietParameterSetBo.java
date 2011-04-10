package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.DietParameterSet;
import java.util.HashSet;
import java.util.Set;

/**
 * DietParameterSetBo generated by hbm2java
 */
public class DietParameterSetBo  implements java.io.Serializable {

     private DietParameterSet _dietParameterSet;

    public DietParameterSetBo() {
    }

    public DietParameterSetBo(DietParameterSet dietParameterSet) {
        this._dietParameterSet=dietParameterSet;
    }

	
    public DietParameterSetBo(DietParameterSet dietParameterSet, String name) {
        this(dietParameterSet);
        setName(name);
    }
    public DietParameterSetBo(DietParameterSet dietParameterSet, String name, Set dietParameterTemplates) {
        this(dietParameterSet,name);
       setDietParameterTemplates(dietParameterTemplates);
    }
   
    public long getDietParameterSetId() {
        return getDietParameterSet().getDietParameterSetId();
    }
    
    public void setDietParameterSetId(long dietParameterSetId) {
        getDietParameterSet().setDietParameterSetId(dietParameterSetId);
    }
    public String getName() {
        return getDietParameterSet().getName();
    }
    
    public void setName(String name) {
        getDietParameterSet().setName(name);
    }
    public Set getDietParameterTemplates() {
        return getDietParameterSet().getDietParameterTemplates();
    }
    
    public void setDietParameterTemplates(Set dietParameterTemplates) {
        getDietParameterSet().setDietParameterTemplates(dietParameterTemplates);
    }

    /**
     * @param dietParameterSet the _dietParameterSet to set
     */
    public void setDietParameterSet(DietParameterSet dietParameterSet) {
        this._dietParameterSet = dietParameterSet;
    }

    /**
     * @return the _dietParameterSet
     */
    protected DietParameterSet getDietParameterSet() {
        return _dietParameterSet;
    }




}

