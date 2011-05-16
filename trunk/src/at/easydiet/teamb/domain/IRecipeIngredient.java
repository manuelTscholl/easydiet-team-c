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

import at.easydiet.teamb.application.viewobject.RecipeIngredientViewable;
import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.model.RecipeIngredient;

/**
 * The Interface IRecipeIngredient.
 */
public interface IRecipeIngredient extends RecipeIngredientViewable {

	/**
	 * Gets the recipeIngredientId of this instance.
	 * 
	 * @return the recipeIngredientId currently set for this instance.
	 */
	public abstract long getRecipeIngredientId();

	/**
	 * Sets the recipeIngredientId of this instance.
	 * 
	 * @param recipeIngredientId
	 *            the new recipeIngredientId of this instance.
	 */
	public abstract void setRecipeIngredientId(long recipeIngredientId);

	/**
	 * Gets the amount of this instance.
	 * 
	 * @return the amount currently set for this instance.
	 */
	public abstract float getAmount();

	/**
	 * Sets the amount of this instance.
	 * 
	 * @param amount
	 *            the new amount of this instance.
	 */
	public abstract void setAmount(float amount);

	/**
	 * Gets the ingredient of this instance.
	 * 
	 * @return the ingredient currently set for this instance.
	 */
	public abstract RecipeViewable getIngredient();

	/**
	 * Sets the ingredient of this instance.
	 * 
	 * @param ingredient
	 *            the new ingredient of this instance.
	 */
	public abstract void setIngredient(RecipeViewable ingredient);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.RecipeIngredientViewable#getRecipe()
	 */
	public abstract RecipeViewable getRecipe();

	/**
	 * Sets the recipe.
	 * 
	 * @param Recipe
	 *            the new recipe
	 */
	public abstract void setRecipe(RecipeViewable Recipe);

	/**
	 * Checks if the {@link RecipeIngredient} is set.
	 *
	 * @return true, if the {@link RecipeIngredient} is set
	 */
	public abstract boolean isSet();

}