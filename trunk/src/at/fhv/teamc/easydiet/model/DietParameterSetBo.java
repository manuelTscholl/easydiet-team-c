package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.DietParameterSet;
import java.util.HashSet;
import java.util.Set;

/**
 * DietParameterSetBo generated by hbm2java
 */
public class DietParameterSetBo  implements java.io.Serializable {


     private Set dietParameterTemplates = new HashSet(0);

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
        return _dietParameterSet.getDietParameterSetId();
    }
    
    public void setDietParameterSetId(long dietParameterSetId) {
        _dietParameterSet.setDietParameterSetId(dietParameterSetId);
    }
    public String getName() {
        return _dietParameterSet.getName();
    }
    
    public void setName(String name) {
        _dietParameterSet.setName(name);
    }
    public Set getDietParameterTemplates() {
        return _dietParameterSet.getDietParameterTemplates();
    }
    
    public void setDietParameterTemplates(Set dietParameterTemplates) {
        _dietParameterSet.setDietParameterTemplates(dietParameterTemplates);
    }

    /**
     * @param dietParameterSet the _dietParameterSet to set
     */
    public void setDietParameterSet(DietParameterSet dietParameterSet) {
        this._dietParameterSet = dietParameterSet;
    }




}


