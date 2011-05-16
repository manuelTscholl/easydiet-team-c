package at.easydiet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
//import org.hibernate.criterion.Example.CombiningMode;
//TODO nothing imported
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import at.easydiet.model.Illness;

/**
 * A DAO implementation for Illness objects.
 */
public class IllnessDAO extends GenericHibernateDAO<Illness, Long> {
	/**
	 * Search for {@link Illness}es which are in the Database by different examples
	 * 
	 * @param examplePatients
	 *            Examples of the Illness
	 * @param maxResults
	 *            the maximum result of the List of the {@link Illness}es
	 * @return the founded {@link Illness}es
	 */
	public List<Illness> searchIllnesses(List<Illness> examplePatients, int maxResults) {
		Disjunction criterion = Restrictions.disjunction();

		for (Illness illness : examplePatients) {
			Example ex = Example.create(illness);
			ex.ignoreCase().enableLike(MatchMode.ANYWHERE);
			//ex.setCombiningMode(CombiningMode.Disjunction);
			//TODO problem couse there is no lib
			criterion.add(ex);
		}

		Session session = HibernateUtil.currentSession();

		@SuppressWarnings("unchecked")
		List<Illness> illnessList = session.createCriteria(Illness.class).add(criterion).setMaxResults(maxResults).list();

		return illnessList;
	}
}