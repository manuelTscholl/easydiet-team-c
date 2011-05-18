package at.easydiet.teamc.model.data;

/**
 * Encapsulation of a RecipeIngredientBo.
 * 
 * @author Stephan Svoboda
 */
public interface RecipeIngredientData {

	/**
	 * Amount as an ingredient in g.
	 * 
	 * @return the ingredient amount
	 */
	public float getIngredientAmount();

	/**
	 * Base recipe of the ingredient.
	 * 
	 * @return the recipe data
	 */
	public RecipeData getRecipeData();

}
