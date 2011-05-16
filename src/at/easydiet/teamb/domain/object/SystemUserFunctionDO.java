package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.ISystemUserFunction;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.SystemUserFunction;

/**
 * Represents a SystemUserFunction in the domain layer
 */
public class SystemUserFunctionDO extends AbstractDO<SystemUserFunction> implements ISystemUserFunction {
	private static Logger LOGGER = Logger.getLogger(SystemUserFunctionDO.class);

	private SystemUserFunction _systemUserFunction;

	/**
	 * Instantiates a new systemuser function
	 * 
	 * @param systemUserFunction
	 *            the systemuser function
	 */
	public SystemUserFunctionDO(SystemUserFunction systemUserFunction) {
		if (systemUserFunction == null) {
			LOGGER.debug("SystemUserFunction is null");
		}

		_systemUserFunction = systemUserFunction;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUserFunction#getName()
	 */
	@Override
	public String getName() {
		return _systemUserFunction.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUserFunction#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_systemUserFunction.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUserFunction#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_systemUserFunction == null);
	}

	@Override
	public SystemUserFunction getModel() {
		return _systemUserFunction;
	}
}
