package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.INutrimentParameter;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * Represents a NutrimentParameter in the domain layer
 */
public class NutrimentParameterDO extends AbstractDO<NutrimentParameter> implements INutrimentParameter {
	private static Logger LOGGER = Logger.getLogger(NutrimentParameterDO.class);

	private NutrimentParameter _nutrimentParameter;

	/**
	 * Instantiates a new nutriment parameter do.
	 * 
	 * @param nutrimentPrarameter
	 *            the nutriment prarameter
	 */
	public NutrimentParameterDO(NutrimentParameter nutrimentPrarameter) {
		if (nutrimentPrarameter == null) {
			LOGGER.debug("NutrimentParameter is null");
		}

		_nutrimentParameter = nutrimentPrarameter;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#getNutrimentParameterId()
	 */
	@Override
	public long getNutrimentParameterId() {
		return _nutrimentParameter.getNutrimentParameterId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#setNutrimentParameterId(long)
	 */
	@Override
	public void setNutrimentParameterId(long nutrimentParameterId) {
		_nutrimentParameter.setNutrimentParameterId(nutrimentParameterId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#getValue()
	 */
	@Override
	public String getValue() {
		return _nutrimentParameter.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		_nutrimentParameter.setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#getParameterDefinition()
	 */
	@Override
	public ParameterDefinitionViewable getParameterDefinition() {
		return new ParameterDefinitionDO(_nutrimentParameter.getParameterDefinition());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#setParameterDefinition(at.easydiet.application.viewobject.ParameterDefinitionViewable)
	 */
	@Override
	public void setParameterDefinition(ParameterDefinitionViewable parameterDefinition) {
		ParameterDefinitionDO d = (ParameterDefinitionDO) parameterDefinition;
		_nutrimentParameter.setParameterDefinition(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#getUnit()
	 */
	@Override
	public ParameterDefinitionUnitViewable getUnit() {
		return new ParameterDefinitionUnitDO(_nutrimentParameter.getUnit());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#setUnit(at.easydiet.model.ParameterDefinitionUnit)
	 */
	@Override
	public void setUnit(ParameterDefinitionUnit unit) {
		_nutrimentParameter.setUnit(unit);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutrimentParameter#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_nutrimentParameter == null);
	}

	@Override
	public NutrimentParameter getModel() {
		return _nutrimentParameter;
	}
}
