package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.model.UserRight;




/**
 * UserRightBo generated by hbm2java
 */
public class UserRightBo  implements java.io.Serializable, Saveable {

     private UserRight _UserRight;

    private UserRightBo() {
    }

    public UserRightBo(UserRight userRight){
        this._UserRight=userRight;
    }

    public UserRightBo(String name) {
        this(new UserRight(name));
    }
   
    public String getName() {
        return this.getUserRight().getName();
    }
    
    public void setName(String name) {
        this.getUserRight().setName(name);
    }

    /**
     * @return the _UserRight
     */
    protected UserRight getUserRight() {
        return _UserRight;
    }

    /**
     * @param UserRight the _UserRight to set
     */
    public void setUserRight(UserRight UserRight) {
        this._UserRight = UserRight;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}

