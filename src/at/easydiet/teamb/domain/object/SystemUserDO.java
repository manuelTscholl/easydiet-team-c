package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.UserRightViewable;
import at.easydiet.teamb.domain.ISystemUser;
import at.easydiet.teamb.domain.IUserRight;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.SystemUser;
import at.easydiet.model.UserRight;

/**
 * Represents a SystemUser in the domain layer
 */
public class SystemUserDO extends AbstractDO<SystemUser> implements ISystemUser {
	private static Logger LOGGER = Logger.getLogger(SystemUserDO.class);

	private SystemUser _systemuser;

	/**
	 * Instantiates a new systemuser
	 * 
	 * @param systemuser
	 *            the systemuser
	 */
	public SystemUserDO(SystemUser systemuser) {
		if (systemuser == null) {
			LOGGER.debug("SystemUser is null");
		}

		_systemuser = systemuser;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getSystemUserId()
	 */
	@Override
	public long getSystemUserId() {
		return _systemuser.getSystemUserId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setSystemUserId(long)
	 */
	@Override
	public void setSystemUserId(long systemUserId) {
		_systemuser.setSystemUserId(systemUserId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getUsername()
	 */
	@Override
	public String getUsername() {
		return _systemuser.getUsername();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		_systemuser.setUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return _systemuser.getPassword();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		_systemuser.setPassword(password);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getName()
	 */
	@Override
	public String getName() {
		return _systemuser.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_systemuser.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getEmail()
	 */
	@Override
	public String getEmail() {
		return _systemuser.getEmail();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		_systemuser.setEmail(email);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getDirectDial()
	 */
	@Override
	public String getDirectDial() {
		return _systemuser.getDirectDial();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setDirectDial(java.lang.String)
	 */
	@Override
	public void setDirectDial(String directDial) {
		_systemuser.setDirectDial(directDial);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getDepartment()
	 */
	@Override
	public String getDepartment() {
		return _systemuser.getDepartment();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setDepartment(java.lang.String)
	 */
	@Override
	public void setDepartment(String department) {
		_systemuser.setDepartment(department);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getJob()
	 */
	@Override
	public String getJob() {
		return _systemuser.getJob();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#setJob(java.lang.String)
	 */
	@Override
	public void setJob(String job) {
		_systemuser.setJob(job);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#getRights()
	 */
	@Override
	public UserRightViewable[] getRights() {
		return ListConverter.toDOArray(_systemuser.getRights(), new ListConverter.ModelToDO<UserRightDO, UserRight>() {
			@Override
			public UserRightDO convert(UserRight model) {
				return new UserRightDO(model);
			}
		}).toArray(new UserRightViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#addRight(at.easydiet.domain.object.IUserRight)
	 */
	@Override
	public void addRight(IUserRight right) {
		_systemuser.getRights().add(((UserRightDO) right).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#removeRight(at.easydiet.domain.object.IUserRight)
	 */
	@Override
	public void removeRight(IUserRight right) {
		_systemuser.getRights().remove(((UserRightDO) right).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ISystemUser#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_systemuser == null);
	}

	@Override
	public SystemUser getModel() {
		return _systemuser;
	}
}
