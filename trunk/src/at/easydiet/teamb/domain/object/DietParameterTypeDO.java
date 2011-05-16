package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.IDietParameterType;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.DietParameterType;

/**
 * Represents a DietParameterTypeDomainObject
 */
public class DietParameterTypeDO extends AbstractDO<DietParameterType> implements IDietParameterType {
	private static Logger LOGGER = Logger.getLogger(DietParameterTypeDO.class);

	private DietParameterType _dietParameterType;

	/**
	 * Instantiates a new diet parameter type do.
	 * 
	 * @param dietParameterType
	 *            the diet parameter type
	 */
	public DietParameterTypeDO(DietParameterType dietParameterType) {
		if (dietParameterType == null) {
			LOGGER.debug("DietParameterType is null");
		}

		_dietParameterType = dietParameterType;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterType#getName()
	 */
	@Override
	public String getName() {
		return _dietParameterType.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterType#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_dietParameterType.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterType#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_dietParameterType == null);
	}

	@Override
	public DietParameterType getModel() {
		return _dietParameterType;
	}
}
