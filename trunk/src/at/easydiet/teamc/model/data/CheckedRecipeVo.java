/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model.data;

/**
 *
 * @author Stephan Svoboda
 */
public class CheckedRecipeVo {

    // instance variables
    private RecipeData _recipeData;
    private ParameterCheckResult _checkResult;

    public CheckedRecipeVo(RecipeData recipeData, ParameterCheckResult checkRecipeWithParameters) {
        _recipeData = recipeData;
        _checkResult = checkRecipeWithParameters;
    }

    public ParameterCheckResult getCheckResult() {
        return _checkResult;
    }

    public RecipeData getRecipeData() {
        return _recipeData;
    }

}
