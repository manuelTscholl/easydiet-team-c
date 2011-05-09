/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model.data;

/**
 *
 * @author Stephan Svoboda
 */
public interface RecipeIngredientData {

    /**
     * Amount as an ingredient in g.
     * @return
     */
    public float getIngredientAmount();

    /**
     * Base recipe of the ingredient.
     * @return
     */
    public RecipeData getRecipeData();

}
