/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	25.04.2011
 */

package at.easydiet.teamb.application.handler;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.util.ValidatorArgs.MealLineErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.MealLineViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.teamb.domain.IMealLine;
import at.easydiet.teamb.domain.object.MealLineDO;
import at.easydiet.teamb.domain.object.ParameterDefinitionUnitDO;
import at.easydiet.teamb.domain.object.RecipeDO;
import at.easydiet.dao.DAOFactory;

/**
 * A MealLineHandler
 * 
 * @author TeamB
 */
public class MealLineHandler extends AbstractHandler<MealLineErrorField> {
	private static Logger LOGGER = Logger.getLogger(MealLineHandler.class);

	/**
	 * The MealLine which is handled by this class
	 */
	private IMealLine _mealline;

	/**
	 * Instantiates a new MealLineHandler
	 * 
	 * @param mealLine
	 *            the MealLine which has to be handled
	 */
	public MealLineHandler(MealLineViewable mealLine) {
		if (mealLine == null) {
			LOGGER.warn("MealLine is null");
			throw new NullPointerException("MealLine is null");
		}
		_mealline = (IMealLine) mealLine;

		// TODO can make it better? - default unit: gramm
		_mealline.setUnit(new ParameterDefinitionUnitDO(DAOFactory.getInstance().getParameterDefinitionUnitDAO().findById((long) 6, false)));

		validateMealLine();
	}

	/**
	 * Gets the MealLine
	 * 
	 * @return the mealLine
	 */
	public MealLineViewable getMealLine() {
		return _mealline;
	}

	/**
	 * Sets the meal of the MealLine
	 * 
	 * @param meal
	 *            the meal
	 */
	public void setMeal(MealViewable meal) {
		_mealline.setMeal(meal);
	}

	/**
	 * Sets the quantity
	 * 
	 * @param quantity
	 *            the quantity
	 */
	public void setQuantity(float quantity) {
		_mealline.setQuantity(quantity);

		validateMealLine();
	}

	/**
	 * Sets the unit
	 * 
	 * @param unit
	 *            the unit
	 */
	public void setUnit(ParameterDefinitionUnitViewable unit) {
		_mealline.setUnit(unit);

		validateMealLine();
	}

	/**
	 * Sets a recipe of a mealline
	 * 
	 * @param recipe
	 *            the recipe
	 */
	public void setRecipe(RecipeViewable recipe) {
		_mealline.setRecipe(recipe);

		validateMealLine();
	}

	/**
	 * Sets the MealLine as a alternative of parent
	 * 
	 * @param parent
	 *            the parentMealLine
	 */
	public void setMealLineAsAlternative(MealLineHandler parent) {
		// Add the MealLine to his parent
		IMealLine parentDO = ((IMealLine) parent.getMealLine());

		while (parentDO.isAlternative()) {
			parentDO = (IMealLine) parentDO.getParent();
		}

		parentDO.addAlternative(_mealline);
		// Set the parentMealLine
		_mealline.setParent(parent.getMealLine());

		for (MealLineViewable line : _mealline.getAlternatives()) {
			IMealLine lineDO = (IMealLine) line;
			parentDO.addAlternative(lineDO);
			lineDO.setParent(parentDO);
			_mealline.removeAlternative(lineDO);
		}
	}

	/**
	 * Sets the MealLine as a non alternative MealLine
	 */
	public void removeMealLineAlternative() {
		if (_mealline.isAlternative()) {
			// Remove the MealLine from the parentMealLine
			((IMealLine) _mealline.getParent()).removeAlternative(_mealline);
			// Set the MealLine as not an alternative
			_mealline.removeParent();
		}
	}

	/**
	 * Validate meal line.
	 */
	private void validateMealLine() {
		_errorFields.clear();

		if (_mealline.getQuantity() <= 0f) {
			_errorFields.add(MealLineErrorField.QUANTITY);
		}

		if (((ParameterDefinitionUnitDO) _mealline.getUnit()).getModel() == null) {
			_errorFields.add(MealLineErrorField.UNIT);
		}

		if (((RecipeDO) _mealline.getRecipe()).getModel() == null) {
			_errorFields.add(MealLineErrorField.RECIPE);
		}

		_validaded.fireEvent(new ValidatorArgs<MealLineErrorField>(_errorFields));
	}

	/**
	 * Perform remove.
	 */
	public void performRemove() {
		if (_mealline.isAlternative()){
			((IMealLine) _mealline.getParent()).removeAlternative(_mealline);
		} else {
			MealLineViewable[] alternatives = _mealline.getAlternatives();
	
			if (alternatives != null && alternatives.length > 0) {
				IMealLine newParent = (IMealLine) alternatives[0];
				newParent.removeParent();
	
				for (int i = 1; i < alternatives.length; i++) {
					IMealLine line = (MealLineDO) alternatives[i];
					line.setParent(newParent);
					newParent.addAlternative(line);
				}
			}
		}
	}
}
