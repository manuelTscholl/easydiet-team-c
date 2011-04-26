package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.Date;

import at.easydiet.model.ContactJournal;
import at.easydiet.model.ContactType;
import at.easydiet.model.SystemUser;



/**
 * ContactJournal generated by hbm2java
 */
public class ContactJournalBo  implements java.io.Serializable, Saveable {


     private ContactJournal _contactjournal;

    private ContactJournalBo() {
    }

    public ContactJournalBo(ContactJournal contactjournal){
        this._contactjournal=contactjournal;
    }

	
    public ContactJournalBo(Date date, SystemUserBo creatorBo, ContactTypeBo contactTypeBo, DietTreatmentBo dietTreatmentBo) {
        this(new ContactJournal(date, creatorBo.getSystemUser(), contactTypeBo.getContactType(), dietTreatmentBo.getDietTreatment()));
    }
    public ContactJournalBo(Date date, Clob description, SystemUserBo creatorBo, ContactTypeBo contactTypeBo, DietTreatmentBo dietTreatmentBo) {
        this(new ContactJournal(date, description, creatorBo.getSystemUser(), contactTypeBo.getContactType(), dietTreatmentBo.getDietTreatment()));
    }
   
    public long getContactJournalId() {
        return getContactjournal().getContactJournalId();
    }
    
    public void setContactJournalId(long contactJournalId) {
        getContactjournal().setContactJournalId(contactJournalId);
    }
    public Date getDate() {
        return getContactjournal().getDate();

    }
    
    public void setDate(Date date) {
        getContactjournal().setDate(date);
    }
    public Clob getDescription() {
        return getContactjournal().getDescription();
    }
    
    public void setDescription(Clob description) {
        getContactjournal().setDescription(description);
    }
    public SystemUser getCreatedBy() {
        return getContactjournal().getCreatedBy();
    }
    
    public void setCreatedBy(SystemUser createdBy) {
        getContactjournal().setCreatedBy(createdBy);
    }
    public ContactType getContactType() {
        return getContactjournal().getContactType();
    }
    
    public void setContactType(ContactType contactType) {
        getContactjournal().setContactType(contactType);
    }

    /**
     * @param contactjournal the _contactjournal to set
     */
    public void setContactjournal(ContactJournal contactjournal) {
        this._contactjournal = contactjournal;
    }

    /**
     * @return the _contactjournal
     */
    protected ContactJournal getContactjournal() {
        return _contactjournal;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}


