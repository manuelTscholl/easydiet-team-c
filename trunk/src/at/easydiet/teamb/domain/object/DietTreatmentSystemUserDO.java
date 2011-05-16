package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.SystemUserFunctionViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.IDietTreatmentSystemUser;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.DietTreatmentSystemUser;

/**
 * Represents a DietTreatmentSystemUser in the domain layer
 */
public class DietTreatmentSystemUserDO extends AbstractDO<DietTreatmentSystemUser> implements IDietTreatmentSystemUser {
	private static Logger LOGGER = Logger.getLogger(DietTreatmentSystemUserDO.class);

	private DietTreatmentSystemUser _dietTreatmentSystemUser;

	/**
	 * Instantiates a new diet treatment system user.
	 * 
	 * @param dietTreatmentSystemUser
	 *            the diet treatment system user
	 */
	public DietTreatmentSystemUserDO(DietTreatmentSystemUser dietTreatmentSystemUser) {
		if (dietTreatmentSystemUser == null) {
			LOGGER.debug("DietTreatmentSystemUser is null");
		}

		_dietTreatmentSystemUser = dietTreatmentSystemUser;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatmentSystemUser#setDietTreatmentSystemUserId(int)
	 */
	@Override
	public void setDietTreatmentSystemUserId(int dietTreatmentSystemUserId) {
		_dietTreatmentSystemUser.setDietTreatmentSystemUserId(dietTreatmentSystemUserId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatmentSystemUser#getSystemUser()
	 */
	@Override
	public SystemUserViewable getSystemUser() {
		return new SystemUserDO(_dietTreatmentSystemUser.getSystemUser());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatmentSystemUser#setSystemUser(at.easydiet.application.viewobject.SystemUserViewable)
	 */
	@Override
	public void setSystemUser(SystemUserViewable systemUser) {
		SystemUserDO d = (SystemUserDO) systemUser;
		_dietTreatmentSystemUser.setSystemUser(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatmentSystemUser#getFunction()
	 */
	@Override
	public SystemUserFunctionViewable getFunction() {
		return new SystemUserFunctionDO(_dietTreatmentSystemUser.getFunction());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatmentSystemUser#setFunction(at.easydiet.application.viewobject.SystemUserFunctionViewable)
	 */
	@Override
	public void setFunction(SystemUserFunctionViewable function) {
		SystemUserFunctionDO d = (SystemUserFunctionDO) function;
		_dietTreatmentSystemUser.setFunction(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatmentSystemUser#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_dietTreatmentSystemUser == null);
	}

	@Override
	public DietTreatmentSystemUser getModel() {
		return _dietTreatmentSystemUser;
	}
}
