package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.IContactType;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.ContactType;

/**
 * Represents a ContactTypeDomainObject in the domain layer
 */
public class ContactTypeDO extends AbstractDO<ContactType> implements IContactType {
	private static Logger LOGGER = Logger.getLogger(ContactTypeDO.class);

	private ContactType _contactType;

	/**
	 * Instantiates a new contact type.
	 * 
	 * @param contactType
	 *            the contact type
	 */
	public ContactTypeDO(ContactType contactType) {
		if (contactType == null) {
			LOGGER.debug("ContactType is null");
		}

		_contactType = contactType;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactType#getName()
	 */
	@Override
	public String getName() {
		return _contactType.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactType#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_contactType.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IContactType#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_contactType == null);
	}

	@Override
	public ContactType getModel() {
		return _contactType;
	}
}
