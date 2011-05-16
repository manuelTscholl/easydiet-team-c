package at.easydiet.dao;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.teamb.application.viewobject.PatientStateTypeViewable;
import at.easydiet.teamb.domain.object.PatientStateTypeDO;
import at.easydiet.model.PatientStateType;

/**
 * A DAO implementation for PatientStateType objects.
 */
public class PatientStateTypeDAO 
        extends GenericHibernateDAO<PatientStateType, Long>
{
    
    /**
     * Gets the all patient state types.
     *
     * @return the all patient state types
     */
    public PatientStateTypeViewable[] getAllPatientStateTypes(){
        List<PatientStateType> types = findAll();
        List<PatientStateTypeDO> typesDO = new ArrayList<PatientStateTypeDO>();
        for(PatientStateType type : types){
            typesDO.add(new PatientStateTypeDO(type));
        }
        return typesDO.toArray(new PatientStateTypeViewable[0]);
    }
}