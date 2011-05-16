package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.domain.IParameterDefinition;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.ParameterDefinition;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * Represents a ParameterDefinition in the domain layer
 */
public class ParameterDefinitionDO extends AbstractDO<ParameterDefinition> implements IParameterDefinition {
	private static Logger LOGGER = Logger.getLogger(ParameterDefinitionDO.class);

	private ParameterDefinition _parameterDefinition;

	/**
	 * Instantiates a new parameter definition.
	 * 
	 * @param parameterDefinition
	 *            the parameter definition
	 */
	public ParameterDefinitionDO(ParameterDefinition parameterDefinition) {
		if (parameterDefinition == null) {
			LOGGER.debug("ParameterDefinition is null");
		}

		_parameterDefinition = parameterDefinition;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#getParameterDefinitionId()
	 */
	@Override
	public long getParameterDefinitionId() {
		return _parameterDefinition.getParameterDefinitionId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#setParameterDefinitionId(long)
	 */
	@Override
	public void setParameterDefinitionId(long parameterDefinitionId) {
		_parameterDefinition.setParameterDefinitionId(parameterDefinitionId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#getName()
	 */
	@Override
	public String getName() {
		return _parameterDefinition.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_parameterDefinition.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#getCheckPattern()
	 */
	@Override
	public String getCheckPattern() {
		return _parameterDefinition.getCheckPattern();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#setCheckPattern(java.lang.String)
	 */
	@Override
	public void setCheckPattern(String checkPattern) {
		_parameterDefinition.setCheckPattern(checkPattern);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#getUnits()
	 */
	@Override
	public ParameterDefinitionUnitViewable[] getUnits() {
		if (_parameterDefinition != null) {
			return ListConverter.toDOArray(_parameterDefinition.getUnits(), new ListConverter.ModelToDO<ParameterDefinitionUnitDO, ParameterDefinitionUnit>() {
				@Override
				public ParameterDefinitionUnitDO convert(ParameterDefinitionUnit model) {
					return new ParameterDefinitionUnitDO(model);
				}
			}).toArray(new ParameterDefinitionUnitViewable[0]);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#addParameterDefinitionUnit(at.easydiet.domain.object.ParameterDefinitionUnitDO)
	 */
	@Override
	public void addParameterDefinitionUnit(ParameterDefinitionUnitDO unit) {
		_parameterDefinition.getUnits().add(unit.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#removeParameterDefinitionUnit(at.easydiet.domain.object.ParameterDefinitionUnitDO)
	 */
	@Override
	public void removeParameterDefinitionUnit(ParameterDefinitionUnitDO unit) {
		_parameterDefinition.getUnits().remove(unit.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinition#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_parameterDefinition == null);
	}

	@Override
	public ParameterDefinition getModel() {
		return _parameterDefinition;
	}
}
