package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.DietParameterSet;
import at.easydiet.model.DietParameterTemplate;
import java.util.HashSet;
import java.util.Set;

/**
 * DietParameterSetBo generated by hbm2java
 */
public class DietParameterSetBo  implements java.io.Serializable, Saveable {

     private DietParameterSet _dietParameterSet;

    public DietParameterSetBo() {
    }

    public DietParameterSetBo(DietParameterSet dietParameterSet) {
        this._dietParameterSet=dietParameterSet;
    }

	
    public DietParameterSetBo(String name) {
        this(new DietParameterSet(name));
    }

    public DietParameterSetBo(String name, Set<DietParameterTemplateBo> dietParameterTemplatesBo) {
        this(name);
        //extract DB-Objects from dietParameterTemplatesBo and include them to DietParameterSet
        for(DietParameterTemplateBo dptb: dietParameterTemplatesBo){
            this._dietParameterSet.getDietParameterTemplates().add(dptb.getDietParameterTemplate());
        }
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
    public Set<DietParameterTemplateBo> getDietParameterTemplates() {

        Set<DietParameterTemplateBo> temp = new HashSet<DietParameterTemplateBo>(getDietParameterSet().getDietParameterTemplates().size());

        for(DietParameterTemplate dpt: getDietParameterSet().getDietParameterTemplates()){
            temp.add(new DietParameterTemplateBo(dpt));
        }

        return temp;
    }
    
    public void setDietParameterTemplates(Set<DietParameterTemplateBo> dietParameterTemplates) {
        
        for(DietParameterTemplateBo dptb: dietParameterTemplates){
            this._dietParameterSet.getDietParameterTemplates().add(dptb.getDietParameterTemplate());
        }

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

    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}

