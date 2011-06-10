package at.easydiet.dao;

import at.easydiet.model.NutritionProtocol;

/**
 * A DAO implementation for NutritionProtocol objects.
 */
public class NutritionProtocolDAO 
        extends GenericHibernateDAO<NutritionProtocol, Long>
{
	@Override
	public NutritionProtocol makePersistent(NutritionProtocol entity) {
		if(entity.getDietPlanId()==0){
		return (NutritionProtocol) getSession().merge(entity);
	}else{
		getSession().saveOrUpdate(entity);
		return entity;
	}
	}
	// implementation in parent class
}