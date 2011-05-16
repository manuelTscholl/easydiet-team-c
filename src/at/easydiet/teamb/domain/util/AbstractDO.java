/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	23.04.2011
 */

package at.easydiet.teamb.domain.util;


/**
 * @param <I> The Object
 * @author TeamB
 */
public abstract class AbstractDO<I> {
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			return (((AbstractDO<?>)obj).getModel() == getModel());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (getModel() != null) ? getModel().hashCode() : super.hashCode();
	}
	
	/**
	 * Returns the model object of a domain object
	 * @return a model object
	 */
	public abstract I getModel();	
}
