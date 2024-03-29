package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.SystemUser;
import at.easydiet.model.UserRight;


/**
 * SystemUser generated by hbm2java
 */
public class SystemUserBo  implements java.io.Serializable, Saveable {

     private SystemUser _SystemUser;

    private SystemUserBo() {
    }

    public SystemUserBo(SystemUser systemUser){
        this._SystemUser=systemUser;
    }

	
    public SystemUserBo(String username, String password, String name, String email) {
        this(new SystemUser(username, password, name, email));
    }
    public SystemUserBo(String username, String password, String name, String email, String directDial, String department, String job, Set<UserRightBo> rightsBo) {
       this(username, password, name, email);
       this._SystemUser.setDirectDial(directDial);
       this._SystemUser.setDepartment(department);
       this._SystemUser.setJob(job);
        for (UserRightBo userRightBo : rightsBo) {
            this._SystemUser.getRights().add(userRightBo.getUserRight());
        }
    }
   
    public long getSystemUserId() {
        return this._SystemUser.getSystemUserId();
    }
    
    public void setSystemUserId(long systemUserId) {
        this._SystemUser.setSystemUserId(systemUserId);
    }
    public String getUsername() {
        return this._SystemUser.getUsername();
    }
    
    public void setUsername(String username) {
        this._SystemUser.setUsername(username);
    }
    public String getPassword() {
        return this._SystemUser.getPassword();
    }
    
    public void setPassword(String password) {
        this._SystemUser.setPassword(password);
    }
    public String getName() {
        return this._SystemUser.getName();
    }
    
    public void setName(String name) {
        this._SystemUser.setName(name);
    }
    public String getEmail() {
        return this._SystemUser.getEmail();
    }
    
    public void setEmail(String email) {
        this._SystemUser.setEmail(email);
    }
    public String getDirectDial() {
        return this._SystemUser.getDirectDial();
    }
    
    public void setDirectDial(String directDial) {
        this._SystemUser.setDirectDial(directDial);
    }
    public String getDepartment() {
        return this._SystemUser.getDepartment();
    }
    
    public void setDepartment(String department) {
        this._SystemUser.setDepartment(department);
    }
    public String getJob() {
        return this._SystemUser.getJob();
    }
    
    public void setJob(String job) {
        this._SystemUser.setJob(job);
    }
    public Set<UserRightBo> getRights() {
        Set<UserRightBo> temp = new HashSet<UserRightBo>(this._SystemUser.getRights().size());
        for (UserRight userRight : this._SystemUser.getRights()) {
            temp.add(new UserRightBo(userRight));
        }
        return temp;
    }
    
    public void setRights(Set<UserRightBo> rightsBo) {
        for (UserRightBo userRightBo : rightsBo) {
            this._SystemUser.getRights().add(userRightBo.getUserRight());
        }
    }
    
    /**
     * returns the system user
     * @return
     */
    public SystemUser getModel()
    {
        //XXX needed because of team B code snippets, violates our design
        return getSystemUser();
    }

    /**
     * @return the _SystemUser
     */
    protected SystemUser getSystemUser() {
        return _SystemUser;
    }

    /**
     * @param SystemUser the _SystemUser to set
     */
    public void setSystemUser(SystemUser SystemUser) {
        this._SystemUser = SystemUser;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


