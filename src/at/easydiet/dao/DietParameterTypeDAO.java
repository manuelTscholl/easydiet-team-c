package at.easydiet.dao;


import java.util.ArrayList;
import java.util.List;

import at.easydiet.teamb.application.viewobject.DietParameterTypeViewable;
import at.easydiet.teamb.domain.object.DietParameterTypeDO;
import at.easydiet.model.DietParameterType;

/**
 * A DAO implementation for DietParameterType objects.
 */
public class DietParameterTypeDAO 
        extends GenericHibernateDAO<DietParameterType, Long>
{
    /**
     * Gets all Parameter Types from the database
     * @return a List of a {@link DietParameterTypeViewable}
     */
    public DietParameterTypeViewable[] getParameterTypes(){
        List<DietParameterType> types = findAll();
        
        List<DietParameterTypeViewable> typesDO = new ArrayList<DietParameterTypeViewable>(types.size());
        for (DietParameterType dietParameter : types) {
            typesDO.add(new DietParameterTypeDO(dietParameter));
        }
        
        return typesDO.toArray(new DietParameterTypeViewable[0]);
    }
}