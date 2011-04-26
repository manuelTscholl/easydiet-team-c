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
import at.easydiet.teamc.model.TimeSpanBo;

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
//    public List<MealCodeBo> findAll()
//    {
//        List<MealCodeBo> codes = new ArrayList<MealCodeBo>();
//        List<String> mealNames= HibernateUtil.currentSession().createCriteria(Meal.class)
//        .setProjection(Projections.groupProperty("name")).list();
//        List<String> mealCodes= HibernateUtil.currentSession().createCriteria(Meal.class)
//        .setProjection(Projections.groupProperty("code")).list();
//
//        //add the 2 columns into the MealCodeBo object
//        //attention there is no reference to the database values because they are grouped!
//        for (int i = 0; i < mealNames.size(); i++)
//        {
//            codes.add(new MealCodeBo(mealCodes.get(i),mealNames.get(i), null));
//
//        }
//
//        return codes;
//    }
    public List<MealCodeBo> findAll(){
        List<MealCodeBo> temp = new ArrayList<MealCodeBo>();
        temp.add(new MealCodeBo("1", "Frühstück"));
        temp.add(new MealCodeBo("2", "Mittagessen"));
        temp.add(new MealCodeBo("3", "Abendmahl"));
        temp.add(new MealCodeBo("4", "Zwischenmahl"));
        return temp;
    }
}
