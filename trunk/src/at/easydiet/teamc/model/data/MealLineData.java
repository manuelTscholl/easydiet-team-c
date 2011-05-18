/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model.data;

import java.util.Set;

/**
 *
 * @author Stephan Svoboda
 */
public interface MealLineData {

    /**
     *
     * @return
     */
    long getMealLineId();
    /**
     *
     * @return
     */
    float getQuantity();
    /**
     *
     * @return
     */
    Set<MealLineData> getMealLineData();
    /**
     *
     * @return
     */
    RecipeData getRecipeData();

}
