package at.easydiet.teamb.application.viewobject;

/**
 * Represents a MealLine
 */
public interface MealLineViewable {

	/**
	 * Gets the mealLineId of this instance.
	 * 
	 * @return the mealLineId currently set for this instance.
	 */
	public long getMealLineId();

	/**
	 * Gets the quantity of this instance.
	 * 
	 * @return the quantity currently set for this instance.
	 */
	public float getQuantity();

	/**
	 * Gets the info of this instance.
	 * 
	 * @return the info currently set for this instance.
	 */
	public String getInfo();

	/**
	 * Gets the mealLines of this instance.
	 * 
	 * @return the mealLines currently set for this instance.
	 */
	public MealLineViewable[] getAlternatives();

	/**
	 * Gets the recipe of this instance.
	 * 
	 * @return the recipe currently set for this instance.
	 */
	public RecipeViewable getRecipe();

	/**
	 * Gets the meal.
	 *
	 * @return the meal
	 */
	public MealViewable getMeal();

	/**
	 * Checks if mealLine is a alternative
	 *
	 * @return true, if is alternative
	 */
	public boolean isAlternative();

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public MealLineViewable getParent();

	/**
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public ParameterDefinitionUnitViewable getUnit();
}
