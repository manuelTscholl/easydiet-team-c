package at.easydiet.teamc.model.data;

import java.sql.Clob;
import java.util.List;

/**
 * Encapsulation of a RecipeBo.
 * 
 * @author Stephan
 */
public interface RecipeData {

	/**
	 * Get the name of the recipe.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * gets the Bls code of the recipe.
	 * 
	 * @return the bls code
	 */
	public String getBlsCode();

	/**
	 * Get the Benefits of the recipe.
	 * 
	 * @return the benefits
	 */
	public Clob getBenefits();

	/**
	 * Description of the recipe.
	 * 
	 * @return the description
	 */
	public Clob getDescription();

	/**
	 * Complexity of the recipe.
	 * 
	 * @return the difficulty
	 */
	public int getDifficulty();

	/**
	 * Describes how to cook the recipe.
	 * 
	 * @return the cook instructions
	 */
	public Clob getCookInstructions();

	/**
	 * Ingredients of the recipe.
	 * 
	 * @return the recipe ingredients data
	 */
	public List<RecipeIngredientData> getRecipeIngredientsData();

	/**
	 * List of all BLS parameter calculated for the whole recipe.
	 * 
	 * @return the nutriment parameters data
	 */
	public List<NutrimentParameterData> getNutrimentParametersData();

	/**
	 * Map to NutrimentParameterData.
	 * 
	 * @param pdd the pdd
	 * @return the nutriment parameter data
	 */
	public NutrimentParameterData getNutrimentParameterData(
			ParameterDefinitionData pdd);

	/**
	 * Total amount of the recipe.
	 * 
	 * @return the total amount
	 */
	public float getTotalAmount();
}
