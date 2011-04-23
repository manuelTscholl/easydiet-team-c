/**
 * This file is part of Easy Diet.
 *   created on: 22.04.2011
 *   created by: Manuel
 *   file: MealCodeDao.java
 */
package at.easydiet.teamc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.MealDAO;
import at.easydiet.model.Meal;
import at.easydiet.model.Patient;
import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealCodeBo;

/**
 * @author Manuel
 * A Dao to filter all MealCodes out of the Meal table
 */
public class MealCodeDao
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(MealCodeDao.class);

    private Meal                                _meal;

    /**
     * Gets all MealCodes which where used
     * @return
     */
    public List<MealCodeBo> findAll()
    {
        List<MealCodeBo> codes = new ArrayList<MealCodeBo>();
        List<Meal> meals = HibernateUtil.currentSession().createCriteria(Meal.class)
        .setProjection(Projections.groupProperty("code")).list();

        for (Meal item : meals)
        {
            codes.add(new MealCodeBo(item));
        }
        
        return codes;        
    }
}
