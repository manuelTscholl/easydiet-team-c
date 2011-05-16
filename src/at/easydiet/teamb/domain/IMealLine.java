/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.MealLineViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.model.MealLine;

/**
 * The Interface IMealLine.
 */
public interface IMealLine extends MealLineViewable {

	/**
	 * Gets the mealLineId of this instance.
	 * 
	 * @return the mealLineId currently set for this instance.
	 */
	public abstract long getMealLineId();

	/**
	 * Sets the mealLineId of this instance.
	 * 
	 * @param mealLineId
	 *            the new mealLineId of this instance.
	 */
	public abstract void setMealLineId(long mealLineId);

	/**
	 * Gets the quantity of this instance.
	 * 
	 * @return the quantity currently set for this instance.
	 */
	public abstract float getQuantity();

	/**
	 * Sets the quantity of this instance.
	 * 
	 * @param quantity
	 *            the new quantity of this instance.
	 */
	public abstract void setQuantity(float quantity);

	/**
	 * Gets the info of this instance.
	 * 
	 * @return the info currently set for this instance.
	 */
	public abstract String getInfo();

	/**
	 * Sets the info of this instance.
	 * 
	 * @param info
	 *            the new info of this instance.
	 */
	public abstract void setInfo(String info);

	/**
	 * Gets the mealLines of this instance.
	 * 
	 * @return the mealLines currently set for this instance.
	 */
	public abstract MealLineViewable[] getAlternatives();

	/**
	 * Adds the alternative.
	 *
	 * @param alternative the alternative
	 */
	public abstract void addAlternative(IMealLine alternative);

	/**
	 * Removes the alternative.
	 *
	 * @param alternative the alternative
	 */
	public abstract void removeAlternative(IMealLine alternative);

	/**
	 * Gets the recipe of this instance.
	 * 
	 * @return the recipe currently set for this instance.
	 */
	public abstract RecipeViewable getRecipe();

	/**
	 * Sets the recipe of this instance.
	 * 
	 * @param recipe
	 *            the new recipe of this instance.
	 */
	public abstract void setRecipe(RecipeViewable recipe);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#getMeal()
	 */
	public abstract MealViewable getMeal();

	/**
	 * Sets the meal.
	 *
	 * @param Meal the new meal
	 */
	public abstract void setMeal(MealViewable Meal);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#isAlternative()
	 */
	public abstract boolean isAlternative();

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#getParent()
	 */
	public abstract MealLineViewable getParent();

	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public abstract void setParent(MealLineViewable parent);

	/**
	 * Removes the parent.
	 */
	public abstract void removeParent();

	/**
	 * Checks if the {@link MealLine} is set.
	 *
	 * @return true, if the {@link MealLine} is set
	 */
	public abstract boolean isSet();

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#getUnit()
	 */
	public abstract ParameterDefinitionUnitViewable getUnit();

	/**       
	 * Sets the unit of this instance. 
	 * @param unit the new unit of this instance.
	 */
	public abstract void setUnit(ParameterDefinitionUnitViewable unit);

}