package at.easydiet.teamb.application.viewobject;

/**
 * Represents a RecipeIngredient
 */
public interface RecipeIngredientViewable {

	/**
	 * Gets the recipeIngredientId of this instance.
	 * 
	 * @return the recipeIngredientId currently set for this instance.
	 */
	public long getRecipeIngredientId();

	/**
	 * Gets the amount of this instance.
	 * 
	 * @return the amount currently set for this instance.
	 */
	public float getAmount();

	/**
	 * Gets the ingredient of this instance.
	 * 
	 * @return the ingredient currently set for this instance.
	 */
	public RecipeViewable getIngredient();

	/**
	 * Gets the recipe.
	 *
	 * @return the recipe
	 */
	public RecipeViewable getRecipe();
}
