/**
 * This file is part of Easy Diet.
 *   created on: 01.04.2011
 *   created by: Manuel
 *   file: UserRightsBo.java
 */
package at.fhv.teamc.easydiet.model;

import at.easydiet.model.UserRights;

/**
 * @author Manuel
 *
 */
public class UserRightsBo
{
    
    private UserRights _UserRights;

    private UserRightsBo(){

    }

    private UserRightsBo(UserRights userRights){
        this._UserRights=userRights;
    }

    /** 
     * Initializes a new instance of the {@link UserRightsBo} class.
     * @param userRightId
     * @param name
     */
    public UserRightsBo(Long userRightId, String name)
    {
        this(new UserRights(userRightId, name));
    }
    /**
     * Gets the userRightId.
     * @return the userRightId
     */
    public Long getUserRightId()
    {
        return this.getUserRights().getUserRightId();
    }
    /**
     * Sets the userRightId.
     * @param userRightId the userRightId to set
     */
    public void setUserRightId(Long userRightId)
    {
        this.getUserRights().setUserRightId(userRightId);
    }
    /**
     * Gets the name.
     * @return the name
     */
    public String getName()
    {
        return this.getUserRights().getName();
    }
    /**
     * Sets the name.
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.getUserRights().setName(name);
    }

    /**
     * @return the _UserRights
     */
    protected UserRights getUserRights() {
        return _UserRights;
    }

    /**
     * @param UserRights the _UserRights to set
     */
    public void setUserRights(UserRights UserRights) {
        this._UserRights = UserRights;
    }
}
