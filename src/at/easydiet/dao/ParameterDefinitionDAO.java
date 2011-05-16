package at.easydiet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import at.easydiet.model.ParameterDefinition;

/**
 * A DAO implementation for ParameterDefinition objects.
 */
public class ParameterDefinitionDAO extends GenericHibernateDAO<ParameterDefinition, Long> {

    /**
     * Search for {@link ParameterDefinition}s.
     * 
     * @param parameterDefinition
     *            Example of the ParameterDefinition
     * @param maxResults
     *            the maximum result of the List of the {@link ParameterDefinition}s
     * @return the found {@link ParameterDefinition}s
     */
    public List<ParameterDefinition> searchParameterDefinitions(ParameterDefinition parameterDefinition, int maxResults) {
        Example ex = Example.create(parameterDefinition);
        ex.ignoreCase();
        ex.enableLike(MatchMode.ANYWHERE);
        
        Session session = HibernateUtil.currentSession();
        
        @SuppressWarnings("unchecked")
        List<ParameterDefinition> parameterDefinitions = session.createCriteria(ParameterDefinition.class).add(ex).setMaxResults(maxResults).addOrder(Order.asc("name")).list();
        
        return parameterDefinitions;
    }
    // implementation in parent class
}