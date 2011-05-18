/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model.data;

/**
 * ValueObject which encapsulates a RecipeData and a ParameterCheckResult.
 *
 * @author Stephan Svoboda
 */
public class CheckedRecipeVo {

    // instance variables
    private RecipeData _recipeData;
    private ParameterCheckResult _checkResult;
    private boolean _disfavour;

    /**
     * Creates the valueobject.
     *
     * @param recipeData RecipeData to encapsulate.
     * @param checkRecipeWithParameters ParameterCheckresult to encapsulate.
     */
    public CheckedRecipeVo(RecipeData recipeData, ParameterCheckResult checkRecipeWithParameters) {
        _disfavour=false;
        _recipeData = recipeData;
        _checkResult = checkRecipeWithParameters;
    }

    /**
     * Gets encapsulated ParameterCheckResult.
     * @return
     */
    public ParameterCheckResult getCheckResult() {
        return _checkResult;
    }

    /**
     * Gets encapsulated RecipeData.
     * @return
     */
    public RecipeData getRecipeData() {
        return _recipeData;
    }

    /**
     * @return the _disfavour
     */
    public boolean isDisfavour() {
        return _disfavour;
    }

    /**
     * @param disfavour the _disfavour to set
     */
    public void setDisfavour(boolean disfavour) {
        this._disfavour = disfavour;
    }

}
