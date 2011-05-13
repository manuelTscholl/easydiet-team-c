/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model.data;

import java.sql.Clob;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Stephan
 */
public interface RecipeData {
    /**
     * Get the name of the recipe 
     * @return
     */
    public String getName();
    /**
     * gets the Bls code of the recipe
     * @return
     */
    public String getBlsCode();
    /**
     * Get the Benefits of the recipe
     * @return
     */
    public Clob getBenefits();
    /**
     * Description of the recipe
     * @return
     */
    public Clob getDescription();
    /**
     * Complexity of the recipe.
     * @return
     */
    public int getDifficulty();
    /**
     * Describes how to cook the recipe.
     * @return
     */
    public Clob getCookInstructions();
    /**
     * Ingredients of the recipe.
     * @return
     */
    public List<RecipeIngredientData> getRecipeIngredientsData();
    /**
     * List of all BLS parameter calculated for the whole recipe
     * @return
     */
    public List<NutrimentParameterData> getNutrimentParametersData();

    /**
     * Map to NutrimentParameterData
     * @return
     */
    public NutrimentParameterData getNutrimentParameterData(ParameterDefinitionData pdd);

    /**
     * Total amount of the recipe.
     * @return
     */
    public float getTotalAmount();
}
