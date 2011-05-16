package at.easydiet.teamb.domain.object;

import java.util.Set;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.domain.IFamilyAnamnesis;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.Illness;

/**
 * Represents a FamilyAnamnesisDomainObject in the domain layer
 */
public class FamilyAnamnesisDO extends AbstractDO<FamilyAnamnesis> implements IFamilyAnamnesis {
	private static Logger LOGGER = Logger.getLogger(FamilyAnamnesisDO.class);

	private FamilyAnamnesis _familyAnamnesis;

	/**
	 * Instantiates a new family anamnesis do.
	 * 
	 * @param familyAnamnesis
	 *            the family anamnesis
	 */
	public FamilyAnamnesisDO(FamilyAnamnesis familyAnamnesis) {
		if (familyAnamnesis == null) {
			LOGGER.debug("FamilyAnamnesis is null");
		}

		_familyAnamnesis = familyAnamnesis;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#getFamilyAnamnesisId()
	 */
	@Override
	public long getFamilyAnamnesisId() {
		return _familyAnamnesis.getFamilyAnamnesisId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#setFamilyAnamnesisId(long)
	 */
	@Override
	public void setFamilyAnamnesisId(long familyAnamnesisId) {
		_familyAnamnesis.setFamilyAnamnesisId(familyAnamnesisId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#getPerson()
	 */
	@Override
	public String getPerson() {
		return _familyAnamnesis.getPerson();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#setPerson(java.lang.String)
	 */
	@Override
	public void setPerson(String person) {
		_familyAnamnesis.setPerson(person);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#getIllnesses()
	 */
	@Override
	public IllnessViewable[] getIllnesses() {
		return _familyAnamnesis.getIllnesses().toArray(new IllnessViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#setIllnesses(java.util.Set)
	 */
	@Override
	public void setIllnesses(Set<Illness> illnesses) {
		_familyAnamnesis.setIllnesses(illnesses);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IFamilyAnamnesis#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_familyAnamnesis == null);
	}

	@Override
	public FamilyAnamnesis getModel() {
		return _familyAnamnesis;
	}
}
