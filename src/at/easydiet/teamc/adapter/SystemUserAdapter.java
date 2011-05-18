/**
 * This file is part of Easy Diet.
 *   created on: 18.05.2011
 *   created by: Manuel
 *   file: SystemUserAdapter.java
 */
package at.easydiet.teamc.adapter;

import at.easydiet.model.SystemUser;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.application.viewobject.UserRightViewable;
import at.easydiet.teamc.model.SystemUserBo;

/**
 * @author Manuel
 *
 */
public class SystemUserAdapter implements SystemUserViewable
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(SystemUserAdapter.class);

    SystemUserBo _actualUser;
    
    /**
     * Gets the actualUser.
     * @return the actualUser
     */
    public SystemUserBo getActualUser()
    {
        return _actualUser;
    }

    /**
     * Sets the actualUser.
     * @param actualUser the actualUser to set
     */
    public void setActualUser(SystemUserBo actualUser)
    {
        _actualUser = actualUser;
    }

    /** 
     * Initializes a new instance of the {@link SystemUserAdapter} class. 
     * @param actualUser
     */
    public SystemUserAdapter(SystemUserBo actualUser)
    {
        _actualUser = actualUser;        
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getSystemUserId()
     */
    @Override
    public long getSystemUserId()
    {
        return getActualUser().getSystemUserId();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getUsername()
     */
    @Override
    public String getUsername()
    {
        return getActualUser().getUsername();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getPassword()
     */
    @Override
    public String getPassword()
    {
        return getActualUser().getPassword();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getName()
     */
    @Override
    public String getName()
    {
        return getActualUser().getName();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getEmail()
     */
    @Override
    public String getEmail()
    {
        return getActualUser().getEmail();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getDirectDial()
     */
    @Override
    public String getDirectDial()
    {
        return getActualUser().getDirectDial();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getDepartment()
     */
    @Override
    public String getDepartment()
    {
        return getActualUser().getDepartment();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getJob()
     */
    @Override
    public String getJob()
    {
        return getActualUser().getJob();
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getRights()
     */
    @Override
    public UserRightViewable[] getRights()
    {
        return UserRightAdapter.createArrayInstance(getActualUser().getRights());
    }

    /**
     * @see at.easydiet.teamb.application.viewobject.SystemUserViewable#getModel()
     */
    @Override
    public SystemUser getModel()
    {
        return _actualUser.getModel();
    }
}
