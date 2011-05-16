package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.ContactTypeViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.IContactJournal;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.model.ContactJournal;

/**
 * Represents a ContactJournalViewObjects in the domain layer
 */
public class ContactJournalDO extends AbstractDO<ContactJournal> implements IContactJournal{
	private static Logger LOGGER = Logger.getLogger(ContactJournalDO.class);

	private ContactJournal _contactJournal;

	/**
	 * Instantiates a new contact journal do.
	 * 
	 * @param contactJournal
	 *            the contact journal
	 */
	public ContactJournalDO(ContactJournal contactJournal) {
		if (contactJournal == null) {
			LOGGER.debug("ContactJournal is null");
		}

		_contactJournal = contactJournal;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getContactJournalId()
	 */
	@Override
	public long getContactJournalId() {
		return _contactJournal.getContactJournalId();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#setContactJournalId(long)
	 */
	@Override
	public void setContactJournalId(long contactJournalId) {
		_contactJournal.setContactJournalId(contactJournalId);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getDate()
	 */
	@Override
	public GregorianCalendar getDate() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_contactJournal.getDate());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#setDate(java.util.GregorianCalendar)
	 */
	@Override
	public void setDate(GregorianCalendar cal) {
		_contactJournal.setDate(CalendarUtil.ConvertGregorianCalendarToDate(cal));
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getDescription()
	 */
	@Override
	public String getDescription() {
		return ClobConverter.ClobToString(_contactJournal.getDescription());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		_contactJournal.setDescription(ClobConverter.StringToClob(description));
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getCreatedBy()
	 */
	@Override
	public SystemUserViewable getCreatedBy() {
		return new SystemUserDO(_contactJournal.getCreatedBy());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#setCreatedBy(at.easydiet.application.viewobject.SystemUserViewable)
	 */
	@Override
	public void setCreatedBy(SystemUserViewable createdBy) {
		SystemUserDO d = (SystemUserDO)createdBy;
		_contactJournal.setCreatedBy(d.getModel());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getContactType()
	 */
	@Override
	public ContactTypeViewable getContactType() {
		return new ContactTypeDO(_contactJournal.getContactType());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#setContactType(at.easydiet.application.viewobject.ContactTypeViewable)
	 */
	@Override
	public void setContactType(ContactTypeViewable contactType) {
		ContactTypeDO d = (ContactTypeDO)contactType;
		_contactJournal.setContactType(d.getModel());
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getDietTreatment()
	 */
	@Override
	public DietTreatmentViewable getDietTreatment() 
    {
        return new DietTreatmentDO(_contactJournal.getDietTreatment());
    }
    
    /* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#setDietTreatment(at.easydiet.application.viewobject.DietTreatmentViewable)
	 */    
    @Override
	public void setDietTreatment(DietTreatmentViewable dietTreatment) 
    {
    	DietTreatmentDO d = (DietTreatmentDO)dietTreatment;
        _contactJournal.setDietTreatment(d.getModel());
    }
    
    /* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#isSet()
	 */
    @Override
	public boolean isSet(){
		return !(_contactJournal==null);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactJournal#getModel()
	 */
	@Override
	public ContactJournal getModel() {
		return _contactJournal;
	}
}
