/**
 * This file is part of Easy Diet.
 *   created on: 25.04.2011
 *   created by: Manuel
 *   file: LoginController.java
 */
package at.easydiet.teamc.controller;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.SystemUserDAO;
import at.easydiet.model.SystemUser;
import at.easydiet.teamc.model.SystemUserBo;

/**
 * Handels the logins of the systemusers
 * 
 * @author Manuel
 * 
 */
public class LoginController
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(LoginController.class);

    private static LoginController              _loginController;
    private SystemUserBo                          _actualUser;

    /**
     * Singelton: Constructor
     * 
     * @return
     */
    public static LoginController getInstance()
    {
        if (_loginController == null)
        {
            _loginController = new LoginController();
        }

        return _loginController;
    }
    
    /**
     * Gets the actualUser or null if no one exists
     * @return the actualUser
     */
    public SystemUserBo getActualUser()
    {
        //FIXME only for testing there is no user logined in the system
        SystemUserDAO getUserDao = DAOFactory.getInstance().getSystemUserDAO();
        List<SystemUser> users = getUserDao.findAll();
        if(users.size()>=1)
        {
            return new SystemUserBo(users.get(0));
        }
        return null;
        //return _actualUser;
    }

    /**
     * Sets the actualUser.
     * @param actualUser the actualUser to set
     */
    public void setActualUser(SystemUserBo actualUser)
    {
        _actualUser = actualUser;
    }

}
