package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.IUserRight;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.UserRight;

/**
 * Represents a UserRight in the domain layer
 */
public class UserRightDO extends AbstractDO<UserRight> implements IUserRight {
	private static Logger LOGGER = Logger.getLogger(UserRightDO.class);

	private UserRight _userRight;

	/**
	 * Instantiates a new userright.
	 *
	 * @param userRight the userright
	 */
	public UserRightDO(UserRight userRight) {
		if (userRight == null) {
			LOGGER.debug("UserRight is null");
		}

		_userRight = userRight;
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IUserRight#getName()
	 */
	@Override
	public String getName() {
		return _userRight.getName();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IUserRight#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_userRight.setName(name);
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IUserRight#isSet()
	 */
	@Override
	public boolean isSet(){
		return !(_userRight==null);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IUserRight#getModel()
	 */
	@Override
	public UserRight getModel() {
		return _userRight;
	}
}
