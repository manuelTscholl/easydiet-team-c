/**
 * This file is part of Easy Diet.
 *   created on: 18.05.2011
 *   created by: Manuel
 *   file: UserRightAdapter.java
 */
package at.easydiet.teamc.adapter;

import java.util.Set;

import at.easydiet.teamb.application.viewobject.UserRightViewable;
import at.easydiet.teamc.model.UserRightBo;

/**
 * @author Manuel
 *
 */
public class UserRightAdapter implements UserRightViewable
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(UserRightAdapter.class);

    UserRightBo _right;
    /** 
     * Initializes a new instance of the {@link UserRightAdapter} class. 
     * @param rights
     */
    public UserRightAdapter(UserRightBo right)
    {
        _right = right;        
    }

    /** 
     * Initializes a new instance of the {@link UserRightAdapter} class. 
     */
    public UserRightAdapter()
    {}

    /**
     * @see at.easydiet.teamb.application.viewobject.UserRightViewable#getName()
     */
    @Override
    public String getName()
    {
        return _right.getName();
    }
    
    /**
     * Creates an Array of adapter Instances
     * @param rights
     * @return
     */
    public static UserRightAdapter[] createArrayInstance(Set<UserRightBo> rights)
    {
        UserRightAdapter[] adapter = new UserRightAdapter[rights.size()];
        int i = 0;
        for (UserRightBo userRightBo : rights)
        {
            adapter[i] = new UserRightAdapter(userRightBo);
            i++;
        }
        
        return adapter;
    }
    
}
