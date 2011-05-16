package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.DietParameterTypeViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.IDietParameterTemplate;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.model.CheckOperator;
import at.easydiet.model.DietParameterTemplate;

/**
 * Represents a DietParameterTemplateDomainObject in the domain layer
 */
public class DietParameterTemplateDO extends AbstractDO<DietParameterTemplate> implements IDietParameterTemplate {
	private static Logger LOGGER = Logger.getLogger(DietParameterTemplateDO.class);

	private DietParameterTemplate _dietParameterTemplate;

	/**
	 * Instantiates a new diet parameter template do.
	 */
	public DietParameterTemplateDO() {

	}

	/**
	 * Instantiates a new dietparametertemplate.
	 * 
	 * @param dietParameterTemplate
	 *            the diet parameter template
	 */
	public DietParameterTemplateDO(DietParameterTemplate dietParameterTemplate) {
		if (dietParameterTemplate == null) {
			LOGGER.debug("dietParameterTemplate is null");
		}

		_dietParameterTemplate = dietParameterTemplate;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getDietParameterTemplateId()
	 */
	@Override
	public long getDietParameterTemplateId() {
		return _dietParameterTemplate.getDietParameterTemplateId();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setDietParameterTemplateId(long)
	 */
	@Override
	public void setDietParameterTemplateId(long dietParameterTemplateId) {
		_dietParameterTemplate.setDietParameterTemplateId(dietParameterTemplateId);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getCheckOperator()
	 */
	@Override
	public CheckOperatorEnum getCheckOperator() {
		return CheckOperatorEnum.getCheckOperatorDO(_dietParameterTemplate.getCheckOperator());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setCheckOperator(at.easydiet.domain.util.CheckOperatorEnum)
	 */
	@Override
	public void setCheckOperator(CheckOperatorEnum checkOperator) {
		_dietParameterTemplate.setCheckOperator(new CheckOperator(checkOperator.getName()));
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getDuration()
	 */
	@Override
	public int getDuration() {
		return _dietParameterTemplate.getDuration();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setDuration(int)
	 */
	@Override
	public void setDuration(int duration) {
		_dietParameterTemplate.setDuration(duration);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getValue()
	 */
	@Override
	public String getValue() {
		return _dietParameterTemplate.getValue();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		_dietParameterTemplate.setValue(value);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getDietParameterType()
	 */
	@Override
	public DietParameterTypeViewable getDietParameterType() {
		return new DietParameterTypeDO(_dietParameterTemplate.getDietParameterType());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setDietParameterType(at.easydiet.application.viewobject.DietParameterTypeViewable)
	 */
	@Override
	public void setDietParameterType(DietParameterTypeViewable dietParameterType) {
		DietParameterTypeDO d = (DietParameterTypeDO) dietParameterType;
		_dietParameterTemplate.setDietParameterType(d.getModel());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getParameterDefinition()
	 */
	@Override
	public ParameterDefinitionViewable getParameterDefinition() {
		return new ParameterDefinitionDO(_dietParameterTemplate.getParameterDefinition());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setParameterDefinition(at.easydiet.application.viewobject.ParameterDefinitionViewable)
	 */
	@Override
	public void setParameterDefinition(ParameterDefinitionViewable parameterDefinition) {
		ParameterDefinitionDO d = (ParameterDefinitionDO) parameterDefinition;
		_dietParameterTemplate.setParameterDefinition((d != null) ? d.getModel() : null);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#getParameterDefinitionUnit()
	 */
	@Override
	public ParameterDefinitionUnitViewable getParameterDefinitionUnit() {
		return new ParameterDefinitionUnitDO(_dietParameterTemplate.getParameterDefinitionUnit());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#setParameterDefinitionUnit(at.easydiet.application.viewobject.ParameterDefinitionUnitViewable)
	 */
	@Override
	public void setParameterDefinitionUnit(ParameterDefinitionUnitViewable parameterDefinitionUnit) {
		ParameterDefinitionUnitDO d = (ParameterDefinitionUnitDO) parameterDefinitionUnit;
		_dietParameterTemplate.setParameterDefinitionUnit(d.getModel());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameterTemplate#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_dietParameterTemplate == null);
	}

	@Override
	public DietParameterTemplate getModel() {
		return _dietParameterTemplate;
	}
}
