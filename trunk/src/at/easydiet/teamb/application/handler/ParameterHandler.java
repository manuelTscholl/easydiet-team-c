package at.easydiet.teamb.application.handler;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.util.ValidatorArgs.ParameterErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.DietParameterTypeViewable;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.NutrimentParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.IDietParameter;
import at.easydiet.teamb.domain.IDietParameterTemplate;
import at.easydiet.teamb.domain.IRecipe;
import at.easydiet.teamb.domain.object.DietParameterDO;
import at.easydiet.teamb.domain.object.DietParameterTemplateDO;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.teamb.domain.util.ParameterDefinitionDataTypeEnum;
import at.easydiet.model.DietParameter;
import at.easydiet.model.DietParameterTemplate;
import at.easydiet.dao.DAOFactory;
import at.easydiet.teamb.util.StringUtil;
import at.easydiet.teamb.util.converter.UnitConverter;
import at.easydiet.teamb.util.exception.UnknownUnitStringException;

/**
 * The Class ParameterHandler.
 */
public class ParameterHandler extends AbstractHandler<ParameterErrorField> {

	private static Logger LOGGER = Logger.getLogger(ParameterHandler.class);

	private IDietParameter _dietParameterDO;
	
	private boolean _valid;

	/**
	 * Instantiates a new parameter handler.
	 */
	public ParameterHandler() {
		this(new DietParameterDO(new DietParameter()));
	}

	/**
	 * Instantiates a new parameter handler.
	 *
	 * @param dietParameter the diet parameter
	 */
	public ParameterHandler(IDietParameter dietParameter) {
		_dietParameterDO = dietParameter;

		_valid = false;
		validate();
	}
	
	/**
	 * Gets the diet parameter.
	 *
	 * @return the diet parameter
	 */
	public DietParameterViewable getDietParameter(){
		return _dietParameterDO;
	}

	/**
	 * Calculate parameter.
	 * 
	 * @param parameter
	 *            the parameter
	 * @param recipe
	 *            the recipe
	 */
	public void calculateParameter(IDietParameter parameter, IRecipe recipe) {
		if (_errorFields.isEmpty()) {
			//no calculation when parameter isn't valide
			return;
		}
		
		ParameterDefinitionUnitViewable[] paraUnits = parameter.getParameterDefinition().getUnits();

		NutrimentParameterViewable[] recipeNutriments = recipe.getNutrimentParameters();

		// search through all nutriments
		for (NutrimentParameterViewable recipeNutrient : recipeNutriments) {
			// Wenn eine ParameterDefinition vom Diätparameter übereinstimmt könnte es sein das diese für die Berechnung wichtig ist
			if (parameter.getParameterDefinition() == recipeNutrient.getParameterDefinition()) {
				// search through all units of the parameters
				for (ParameterDefinitionUnitViewable paraDefUnit : paraUnits) {
					// search through all units of the recipenutriments
					for (ParameterDefinitionUnitViewable recipeNutParaDefUnit : recipeNutrient.getParameterDefinition().getUnits()) {
						// the units matches
						if (paraDefUnit == recipeNutParaDefUnit) {
							if (paraDefUnit.getType() == ParameterDefinitionDataTypeEnum.NUMBERS)
								calculateParameterNumber(parameter, recipe, recipeNutrient, paraDefUnit);
						}
						checkParamterState(parameter, paraDefUnit.getType());
					}
				}
			}
		}

	}

	/**
	 * Calculate parameter number.
	 *
	 * @param parameter the parameter
	 * @param recipe the recipe
	 * @param recipeNutrient the recipe nutrient
	 * @param paraDefUnit the para def unit
	 */
	private void calculateParameterNumber(IDietParameter parameter, IRecipe recipe, NutrimentParameterViewable recipeNutrient,
			ParameterDefinitionUnitViewable paraDefUnit) {
		// Convert the unit to the unit of the parameter
		StringBuilder sb = new StringBuilder();

		sb.append(paraDefUnit.getName());
		sb.append("to");
		sb.append(parameter.getParameterDefinitionUnit().getName());

		LOGGER.debug(sb.toString());

		try {
			// Convert the nutrimentvalue into the needed unit for the parameter
			double nutimentvalue = UnitConverter.convert(sb.toString(), Double.parseDouble(recipeNutrient.getValue()));
			parameter.setActual(Double.toString((Double.parseDouble(parameter.getActual()) + nutimentvalue)));

		} catch (NumberFormatException ex) {
			LOGGER.warn(ex);
		} catch (UnknownUnitStringException ex) {
			LOGGER.warn(ex);
		}
	}

	/**
	 * Calculates the parameters from the Recipes.
	 *
	 * @param parameters the parameters
	 * @param recipes the recipes
	 * @return the list
	 */
	public List<IDietParameter> calulateParameters(List<IDietParameter> parameters, List<IRecipe> recipes) {

		for (IDietParameter dietParameter : parameters) {
			for (IRecipe recipe : recipes) {
				calculateParameter(dietParameter, recipe);
			}
		}

		return null;
	}

	/**
	 * Check paramter state.
	 *
	 * @param parameter the parameter
	 * @param type the type
	 */
	private void checkParamterState(IDietParameter parameter, ParameterDefinitionDataTypeEnum type) {

		switch (parameter.getCheckOperator()) {
			case EQUALS:
				parameter.setPossible(parameter.getValue().equalsIgnoreCase(parameter.getActual()));
				break;
			case NOT_EQUALS:
				parameter.setPossible(!parameter.getValue().equalsIgnoreCase(parameter.getActual()));
				break;
		}

		if (type == ParameterDefinitionDataTypeEnum.NUMBERS) {
			double value = Double.parseDouble(parameter.getValue());
			double actual = Double.parseDouble(parameter.getActual());

			switch (parameter.getCheckOperator()) {
				case BIGGER:
					parameter.setPossible(actual > value);
					break;
				case BIGGER_EQUALS:
					parameter.setPossible(actual >= value);
					break;
				case LESSER:
					parameter.setPossible(actual <= value);
					break;
				case LESSER_EQUALS:
					parameter.setPossible(actual <= value);
					break;
			}
		}
	}

	/**
	 * Creates the diet parameter.
	 * 
	 * @return the diet parameter do
	 */
	public IDietParameter createDietParameter() {
		return new DietParameterDO(new DietParameter());
	}

	/**
	 * Creates the diet parameter.
	 * 
	 * @param checkOperator
	 *            the check operator
	 * @param dietParameterType
	 *            the diet parameter type
	 * @param duration
	 *            the duration
	 * @param parameterDefinition
	 *            the parameter definition
	 * @param parameterDefinitionUnit
	 *            the parameter definition unit
	 * @param start
	 *            the start
	 * @param value
	 *            the value
	 * @return the diet parameter do
	 */
	public IDietParameter createDietParameter(CheckOperatorEnum checkOperator, DietParameterTypeViewable dietParameterType, int duration,
			ParameterDefinitionViewable parameterDefinition, ParameterDefinitionUnitViewable parameterDefinitionUnit, GregorianCalendar start, String value) {
		IDietParameter dp = new DietParameterDO(new DietParameter());

		dp.setCheckOperator(checkOperator);
		dp.setDietParameterType(dietParameterType);
		dp.setDuration(duration);
		dp.setParameterDefinition(parameterDefinition);
		dp.setParameterDefinitionUnit(parameterDefinitionUnit);
		dp.setStart(start);
		dp.setValue(value);

		return dp;
	}

	/**
	 * Creates the parameter from template.
	 * 
	 * @param template
	 *            the template
	 * @param value
	 *            the value
	 * @param duration
	 *            the duration
	 * @param startDate
	 *            the start date
	 * @param unit
	 *            the units
	 * @return the new {@link DietParameterDO} from the Template
	 */
	public IDietParameter createParameterFromTemplate(DietParameterTemplateDO template, String value, int duration, GregorianCalendar startDate,
			ParameterDefinitionUnitViewable unit) {
		DietParameterTemplate t = template.getModel();
		IDietParameter dp = new DietParameterDO(new DietParameter());
		IDietParameterTemplate templateDo = new DietParameterTemplateDO(t);

		dp.setCheckOperator(template.getCheckOperator());
		dp.setDietParameterType(templateDo.getDietParameterType());
		dp.setDuration(duration);
		dp.setParameterDefinition(templateDo.getParameterDefinition());

		dp.setParameterDefinitionUnit(unit);
		dp.setStart(startDate);
		dp.setValue(value);

		return dp;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(GregorianCalendar start) {
		_dietParameterDO.setStart(start);
		validate();
	}

	/**
	 * Sets the check operator.
	 *
	 * @param checkOperator the new check operator
	 */
	public void setCheckOperator(CheckOperatorEnum checkOperator) {
		_dietParameterDO.setCheckOperator(checkOperator);
		validate();
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		_dietParameterDO.setDuration(duration);
		validate();
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		_dietParameterDO.setValue(value);
		validate();
	}

	/**
	 * Sets the diet parameter type.
	 *
	 * @param dietParameterType the new diet parameter type
	 */
	public void setDietParameterType(DietParameterTypeViewable dietParameterType) {
		_dietParameterDO.setDietParameterType(dietParameterType);
		validate();
	}

	/**
	 * Sets the parameter definition.
	 *
	 * @param parameterDefinition the new parameter definition
	 */
	public void setParameterDefinition(ParameterDefinitionViewable parameterDefinition) {
		_dietParameterDO.setParameterDefinition(parameterDefinition);
		validate();
	}

	/**
	 * Sets the parameter definition unit.
	 *
	 * @param parameterDefinitionUnit the new parameter definition unit
	 */
	public void setParameterDefinitionUnit(ParameterDefinitionUnitViewable parameterDefinitionUnit) {
		_dietParameterDO.setParameterDefinitionUnit(parameterDefinitionUnit);
		validate();
	}

	/**
	 * Validate.
	 */
	private void validate() {		
		_errorFields.clear();
		
		if (_dietParameterDO.getDietParameterType() == null) {
			_errorFields.add(ParameterErrorField.TYPE);
		}
		if (_dietParameterDO.getParameterDefinition() == null) {
			_errorFields.add(ParameterErrorField.DEFINITION);
		}
		if (_dietParameterDO.getCheckOperator() == null) {
			_errorFields.add(ParameterErrorField.CHECKOPERATOR);
		}
		if (StringUtil.isEmpty(_dietParameterDO.getValue())) {
			_errorFields.add(ParameterErrorField.VALUE);
		}
		if (_dietParameterDO.getParameterDefinitionUnit() == null) {
			_errorFields.add(ParameterErrorField.UNIT);
		} else {
			ParameterDefinitionUnitViewable[] units = _dietParameterDO.getParameterDefinition().getUnits();
			if (units == null || !Arrays.asList(units).contains(_dietParameterDO.getParameterDefinitionUnit())) {
				_errorFields.add(ParameterErrorField.UNIT);
			}
		}
		
		_validaded.fireEvent(new ValidatorArgs<ParameterErrorField>(_errorFields));
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return _valid;
	}

	/**
	 * Gets the parameter types.
	 *
	 * @return the parameter types
	 */
	public DietParameterTypeViewable[] getParameterTypes() {
		return DAOFactory.getInstance().getDietParameterTypeDAO().getParameterTypes();
	}
}
