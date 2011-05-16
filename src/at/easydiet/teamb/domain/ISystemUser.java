/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.application.viewobject.UserRightViewable;
import at.easydiet.model.SystemUser;

/**
 * The Interface ISystemUser.
 */
public interface ISystemUser extends SystemUserViewable {

	/**
	 * Gets the systemUserId of this instance.
	 * 
	 * @return the systemUserId currently set for this instance.
	 */
	public abstract long getSystemUserId();

	/**
	 * Sets the systemUserId of this instance.
	 * 
	 * @param systemUserId
	 *            the new systemUserId of this instance.
	 */
	public abstract void setSystemUserId(long systemUserId);

	/**
	 * Gets the username of this instance.
	 * 
	 * @return the username currently set for this instance.
	 */
	public abstract String getUsername();

	/**
	 * Sets the username of this instance.
	 * 
	 * @param username
	 *            the new username of this instance.
	 */
	public abstract void setUsername(String username);

	/**
	 * Gets the password of this instance.
	 * 
	 * @return the password currently set for this instance.
	 */
	public abstract String getPassword();

	/**
	 * Sets the password of this instance.
	 * 
	 * @param password
	 *            the new password of this instance.
	 */
	public abstract void setPassword(String password);

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Gets the email of this instance.
	 * 
	 * @return the email currently set for this instance.
	 */
	public abstract String getEmail();

	/**
	 * Sets the email of this instance.
	 * 
	 * @param email
	 *            the new email of this instance.
	 */
	public abstract void setEmail(String email);

	/**
	 * Gets the directDial of this instance.
	 * 
	 * @return the directDial currently set for this instance.
	 */
	public abstract String getDirectDial();

	/**
	 * Sets the directDial of this instance.
	 * 
	 * @param directDial
	 *            the new directDial of this instance.
	 */
	public abstract void setDirectDial(String directDial);

	/**
	 * Gets the department of this instance.
	 * 
	 * @return the department currently set for this instance.
	 */
	public abstract String getDepartment();

	/**
	 * Sets the department of this instance.
	 * 
	 * @param department
	 *            the new department of this instance.
	 */
	public abstract void setDepartment(String department);

	/**
	 * Gets the job of this instance.
	 * 
	 * @return the job currently set for this instance.
	 */
	public abstract String getJob();

	/**
	 * Sets the job of this instance.
	 * 
	 * @param job
	 *            the new job of this instance.
	 */
	public abstract void setJob(String job);

	/**
	 * Gets the rights of this instance.
	 * 
	 * @return the rights currently set for this instance.
	 */
	public abstract UserRightViewable[] getRights();

	/**
	 * Adds the right.
	 *
	 * @param right the right
	 */
	public abstract void addRight(IUserRight right);

	/**
	 * Removes the right.
	 *
	 * @param right the right
	 */
	public abstract void removeRight(IUserRight right);

	/**
	 * Checks if the {@link SystemUser} is set.
	 *
	 * @return true, if the {@link SystemUser} is set
	 */
	public abstract boolean isSet();

}