package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.teamc.model.data.Saveable;
import at.easydiet.model.ContactType;



/**
 * ContactType generated by hbm2java
 */
public class ContactTypeBo  implements java.io.Serializable, Saveable {


    private ContactType _contactType;

    private ContactTypeBo() {
    }

    public ContactTypeBo(ContactType contactType){
        this._contactType=contactType;
    }

    public ContactTypeBo(String name) {
        this(new ContactType(name));
    }
   
    public String getName() {
        return getContactType().getName();
    }
    
    public void setName(String name) {
        getContactType().setName(name);
    }

    /**
     * @param contactType the _contactType to set
     */
    public void setContactType(ContactType contactType) {
        this._contactType=contactType;
    }

    /**
     * @return the _contactType
     */
    protected  ContactType getContactType() {
        return _contactType;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}


