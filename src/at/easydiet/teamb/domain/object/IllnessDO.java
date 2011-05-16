/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain.object;

import at.easydiet.teamb.domain.IIllness;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.Illness;

/**
 * The Class IllnessDO.
 */
public class IllnessDO extends AbstractDO<Illness> implements IIllness {
	private Illness _illness;
	
	/**
	 * Instantiates a new illness do.
	 *
	 * @param illness the illness
	 */
	public IllnessDO(Illness illness){
		_illness = illness;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IIllness#setName(java.lang.String)
	 */
	@Override
	public void setName(String name){
		_illness.setName(name);
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IllnessViewable#getName()
	 */
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IIllness#getName()
	 */
	@Override
	public String getName(){
		return _illness.getName();
	}

	@Override
	public Illness getModel() {
		return _illness;
	}
}
