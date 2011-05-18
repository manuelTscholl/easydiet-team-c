package at.easydiet.teamb.application.viewobject;

import at.easydiet.model.SystemUser;

/**
 * Represents a SystemUser
 */
public interface SystemUserViewable {

	/**
	 * Gets the systemUserId of this instance.
	 * 
	 * @return the systemUserId currently set for this instance.
	 */
	public long getSystemUserId();

	/**
	 * Gets the username of this instance.
	 * 
	 * @return the username currently set for this instance.
	 */
	public String getUsername();

	/**
	 * Gets the password of this instance.
	 * 
	 * @return the password currently set for this instance.
	 */
	public String getPassword();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the email of this instance.
	 * 
	 * @return the email currently set for this instance.
	 */
	public String getEmail();

	/**
	 * Gets the directDial of this instance.
	 * 
	 * @return the directDial currently set for this instance.
	 */
	public String getDirectDial();

	/**
	 * Gets the department of this instance.
	 * 
	 * @return the department currently set for this instance.
	 */
	public String getDepartment();

	/**
	 * Gets the job of this instance.
	 * 
	 * @return the job currently set for this instance.
	 */
	public String getJob();
	
	/**
	 * 
	 * @return
	 */
	public SystemUser getModel();

	/**
	 * Gets the rights of this instance.
	 * 
	 * @return the rights currently set for this instance.
	 */
	public UserRightViewable[] getRights();
}
