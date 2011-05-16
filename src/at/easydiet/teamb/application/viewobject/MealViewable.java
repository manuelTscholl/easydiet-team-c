package at.easydiet.teamb.application.viewobject;

/**
 * Represents a Meal
 */
public interface MealViewable {

	/**
	 * Gets the mealId of this instance.
	 * 
	 * @return the mealId currently set for this instance.
	 */
	public long getMealId();

	/**
	 * Gets the code of this instance.
	 * 
	 * @return the code currently set for this instance.
	 */
	public String getCode();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public DietParameterViewable[] getDietParameters();

	/**
	 * Gets the mealLines of this instance.
	 * 
	 * @return the mealLines currently set for this instance.
	 */
	public MealLineViewable[] getMealLines();

	/**
	 * Gets the time span.
	 * 
	 * @return the time span
	 */
	public TimeSpanViewable getTimeSpan();
}
