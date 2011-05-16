package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.ITreatmentState;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.TreatmentState;

/**
 * Represents a TreatmentState in the domain layer
 */
public class TreatmentStateDO extends AbstractDO<TreatmentState> implements ITreatmentState{
	private static Logger LOGGER = Logger.getLogger(TreatmentStateDO.class);

	private TreatmentState _treatmentState;

	/**
	 * Instantiates a new treatmentState.
	 *
	 * @param treatmentState the treatmentstate
	 */
	public TreatmentStateDO(TreatmentState treatmentState) {
		if (treatmentState == null) {
			LOGGER.debug("TreatmentState is null");
		}

		_treatmentState = treatmentState;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITreatmentState#getName()
	 */
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getName()
	 */
	@Override
	public String getName() {
		return _treatmentState.getName();
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITreatmentState#setName(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_treatmentState.setName(name);
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITreatmentState#isSet()
	 */
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#isSet()
	 */
	@Override
	public boolean isSet(){
		return !(_treatmentState==null);
	}

	@Override
	public TreatmentState getModel() {
		return _treatmentState;
	}
}
