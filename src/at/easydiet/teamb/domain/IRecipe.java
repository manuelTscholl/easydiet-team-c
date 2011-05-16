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

import at.easydiet.teamb.application.viewobject.NutrimentParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.RecipeIngredientViewable;
import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.teamb.domain.object.NutrimentParameterDO;
import at.easydiet.teamb.domain.object.RecipeIngredientDO;

/**
 * The Interface IRecipe.
 */
public interface IRecipe extends RecipeViewable {

	/**
	 * Gets the recipeId of this instance.
	 * 
	 * @return the recipeId currently set for this instance.
	 */
	public abstract long getRecipeId();

	/**
	 * Sets the recipeId of this instance.
	 * 
	 * @param recipeId
	 *            the new recipeId of this instance.
	 */
	public abstract void setRecipeId(long recipeId);

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Gets the blsCode of this instance.
	 * 
	 * @return the blsCode currently set for this instance.
	 */
	public abstract String getBlsCode();

	/**
	 * Sets the blsCode of this instance.
	 * 
	 * @param blsCode
	 *            the new blsCode of this instance.
	 */
	public abstract void setBlsCode(String blsCode);

	/**
	 * Gets the difficulty of this instance.
	 * 
	 * @return the difficulty currently set for this instance.
	 */
	public abstract int getDifficulty();

	/**
	 * Sets the difficulty of this instance.
	 * 
	 * @param difficulty
	 *            the new difficulty of this instance.
	 */
	public abstract void setDifficulty(int difficulty);

	/**
	 * Gets the description of this instance.
	 * 
	 * @return the description currently set for this instance.
	 */
	public abstract String getDescription();

	/**
	 * Sets the description of this instance.
	 * 
	 * @param description
	 *            the new description of this instance.
	 */
	public abstract void setDescription(String description);

	/**
	 * Gets the benefits of this instance.
	 * 
	 * @return the benefits currently set for this instance.
	 */
	public abstract String getBenefits();

	/**
	 * Sets the benefits of this instance.
	 * 
	 * @param benefits
	 *            the new benefits of this instance.
	 */
	public abstract void setBenefits(String benefits);

	/**
	 * Gets the cookInstructions of this instance.
	 * 
	 * @return the cookInstructions currently set for this instance.
	 */
	public abstract String getCookInstructions();

	/**
	 * Sets the cookInstructions of this instance.
	 * 
	 * @param cookInstructions
	 *            the new cookInstructions of this instance.
	 */
	public abstract void setCookInstructions(String cookInstructions);

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
	 * Gets the unit of this instance.
	 * 
	 * @return the unit currently set for this instance.
	 */
	public abstract ParameterDefinitionUnitViewable getUnit();

	/**
	 * Sets the unit of this instance.
	 * 
	 * @param unit
	 *            the new unit of this instance.
	 */
	public abstract void setUnit(ParameterDefinitionUnitViewable unit);

	/**
	 * Gets the ingredients of this instance.
	 * 
	 * @return the ingredients currently set for this instance.
	 */
	public abstract RecipeIngredientViewable[] getIngredients();

	/**
	 * Adds the ingredient.
	 *
	 * @param ingredient the ingredient
	 */
	public abstract void addIngredient(RecipeIngredientDO ingredient);

	/**
	 * Removes the ingredient.
	 *
	 * @param ingredient the ingredient
	 */
	public abstract void removeIngredient(RecipeIngredientDO ingredient);

	/**
	 * Gets the nutrimentParameters of this instance.
	 * 
	 * @return the nutrimentParameters currently set for this instance.
	 */
	public abstract NutrimentParameterViewable[] getNutrimentParameters();

	/**
	 * Adds the nutriment parameter.
	 *
	 * @param nutrimentParameter the nutriment parameter
	 */
	public abstract void addNutrimentParameter(NutrimentParameterDO nutrimentParameter);

	/**
	 * Removes the nutriment parameter.
	 *
	 * @param nutrimentParameter the nutriment parameter
	 */
	public abstract void removeNutrimentParameter(NutrimentParameterDO nutrimentParameter);
}