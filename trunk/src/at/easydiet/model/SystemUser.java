package at.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

/**
 * SystemUser generated by hbm2java
 */
public class SystemUser  implements java.io.Serializable {


     private long systemUserId;
     private String username;
     private String password;
     private String name;
     private String email;
     private String directDial;
     private String department;
     private String job;
     private Set rights = new HashSet(0);

    public SystemUser() {
    }

	
    public SystemUser(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    public SystemUser(String username, String password, String name, String email, String directDial, String department, String job, Set rights) {
       this.username = username;
       this.password = password;
       this.name = name;
       this.email = email;
       this.directDial = directDial;
       this.department = department;
       this.job = job;
       this.rights = rights;
    }
   
    public long getSystemUserId() {
        return this.systemUserId;
    }
    
    public void setSystemUserId(long systemUserId) {
        this.systemUserId = systemUserId;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDirectDial() {
        return this.directDial;
    }
    
    public void setDirectDial(String directDial) {
        this.directDial = directDial;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    public Set getRights() {
        return this.rights;
    }
    
    public void setRights(Set rights) {
        this.rights = rights;
    }




}


