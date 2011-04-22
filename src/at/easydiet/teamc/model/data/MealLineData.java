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

    long getMealLineId();
    float getQuantity();
    Set<MealLineData> getMealLineData();
    RecipeData getRecipeData();

}
