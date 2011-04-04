/**
 * This file is part of Easy Diet.
 *   created on: 01.04.2011
 *   created by: Manuel
 *   file: UserRights.java
 */
package at.easydiet.model;

/**
 * @author Manuel
 *
 */
public class UserRights
{
    private Long _userRightId;
    private String _name;
    /** 
     * Initializes a new instance of the {@link UserRights} class. 
     * @param userRightId
     * @param name
     */
    public UserRights(Long userRightId, String name)
    {
        super();
        _userRightId = userRightId;
        _name = name;
    }
    /**
     * Gets the userRightId.
     * @return the userRightId
     */
    public Long getUserRightId()
    {
        return _userRightId;
    }
    /**
     * Sets the userRightId.
     * @param userRightId the userRightId to set
     */
    public void setUserRightId(Long userRightId)
    {
        _userRightId = userRightId;
    }
    /**
     * Gets the name.
     * @return the name
     */
    public String getName()
    {
        return _name;
    }
    /**
     * Sets the name.
     * @param name the name to set
     */
    public void setName(String name)
    {
        _name = name;
    }
}
