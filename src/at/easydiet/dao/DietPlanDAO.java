package at.easydiet.dao;

import at.easydiet.model.DietPlan;

/**
 * A DAO implementation for DietPlan objects.
 */
public class DietPlanDAO 
        extends GenericHibernateDAO<DietPlan, Long>
{
	// implementation in parent class
	@Override
	public DietPlan makePersistent(DietPlan entity) {
		if(entity.getDietPlanId()==0){
			return (DietPlan) getSession().merge(entity);
		}else{
			getSession().saveOrUpdate(entity);
			return entity;
		}
	}
}