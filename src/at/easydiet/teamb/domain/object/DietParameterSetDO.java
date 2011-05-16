package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.DietParameterTemplateViewable;
import at.easydiet.teamb.domain.IDietParameterSet;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.DietParameterSet;
import at.easydiet.model.DietParameterTemplate;

/**
 * Represents a DietParameterSetDomainObject in the domain layer
 */
public class DietParameterSetDO extends AbstractDO<DietParameterSet> implements IDietParameterSet {
	private static Logger LOGGER = Logger.getLogger(DietParameterSetDO.class);

	private DietParameterSet _dietParameterSet;

	/**
	 * Instantiates a new diet parameter set do.
	 * 
	 * @param dietParameterSet
	 *            the diet parameter set
	 */
	public DietParameterSetDO(DietParameterSet dietParameterSet) {
		if (dietParameterSet == null) {
			LOGGER.debug("dietParameterSet is null");
		}

		_dietParameterSet = dietParameterSet;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#getDietParameterSetId()
	 */
	@Override
	public long getDietParameterSetId() {
		return _dietParameterSet.getDietParameterSetId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#setDietParameterSetId(long)
	 */
	@Override
	public void setDietParameterSetId(long dietParameterSetId) {
		_dietParameterSet.setDietParameterSetId(dietParameterSetId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#getName()
	 */
	@Override
	public String getName() {
		return _dietParameterSet.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_dietParameterSet.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#getDietParameterTemplates()
	 */
	@Override
	public DietParameterTemplateViewable[] getDietParameterTemplates() {
		return ListConverter.toDOArray(_dietParameterSet.getDietParameterTemplates(),
				new ListConverter.ModelToDO<DietParameterTemplateDO, DietParameterTemplate>() {
					public DietParameterTemplateDO convert(DietParameterTemplate model) {
						return new DietParameterTemplateDO(model);
					}
				}).toArray(new DietParameterTemplateViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#addDietParameterTemplate(at.easydiet.domain.object.DietParameterTemplateDO)
	 */
	@Override
	public void addDietParameterTemplate(DietParameterTemplateDO dietParameterTemplate) {
		_dietParameterSet.getDietParameterTemplates().add(dietParameterTemplate.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#removeDietParameterTemplate(at.easydiet.domain.object.DietParameterTemplateDO)
	 */
	@Override
	public void removeDietParameterTemplate(DietParameterTemplateDO dietParameterTemplate) {
		_dietParameterSet.getDietParameterTemplates().remove(dietParameterTemplate.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterSet#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_dietParameterSet == null);
	}

	@Override
	public DietParameterSet getModel() {
		return _dietParameterSet;
	}
}
