package at.easydiet.teamb.application.viewobject;

/**
 * Represents a Recipe from the BLS Database
 */
public interface RecipeViewable {

	/**
	 * Checks if is checked.
	 * 
	 * @return true, if is checked
	 */
	public boolean isDisfavor();

	/**
	 * Gets the recipeId of this instance.
	 * 
	 * @return the recipeId currently set for this instance.
	 */
	public long getRecipeId();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the blsCode of this instance.
	 * 
	 * @return the blsCode currently set for this instance.
	 */
	public String getBlsCode();

	/**
	 * Gets the difficulty of this instance.
	 * 
	 * @return the difficulty currently set for this instance.
	 */
	public int getDifficulty();

	/**
	 * Gets the description of this instance.
	 * 
	 * @return the description currently set for this instance.
	 */
	public String getDescription();

	/**
	 * Gets the benefits of this instance.
	 * 
	 * @return the benefits currently set for this instance.
	 */
	public String getBenefits();

	/**
	 * Gets the cookInstructions of this instance.
	 * 
	 * @return the cookInstructions currently set for this instance.
	 */
	public String getCookInstructions();

	/**
	 * Gets the amount of this instance.
	 * 
	 * @return the amount currently set for this instance.
	 */
	public float getAmount();

	/**
	 * Gets the unit of this instance.
	 * 
	 * @return the unit currently set for this instance.
	 */
	public ParameterDefinitionUnitViewable getUnit();

	/**
	 * Gets the ingredients of this instance.
	 * 
	 * @return the ingredients currently set for this instance.
	 */
	public RecipeIngredientViewable[] getIngredients();

	/**
	 * Gets the nutrimentParameters of this instance.
	 * 
	 * @return the nutrimentParameters currently set for this instance.
	 */
	public NutrimentParameterViewable[] getNutrimentParameters();
}
