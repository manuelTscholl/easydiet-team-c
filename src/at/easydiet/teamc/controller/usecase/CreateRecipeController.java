/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: CreateRecipeController
 */
package at.easydiet.teamc.controller.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PrePersist;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.model.Recipe;
import at.easydiet.teamc.exception.NutrimentRuleException;
import at.easydiet.teamc.model.CheckOperatorBo;
import at.easydiet.teamc.model.NutrimentRuleBo;
import at.easydiet.teamc.model.NutrimentRulesBo;
import at.easydiet.teamc.model.ParameterDefinitionBo;
import at.easydiet.teamc.model.ParameterDefinitionUnitBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.ValidatedRecipeVo;
import at.easydiet.teamc.util.ClobConverter;

/**
 * This controller creates recipes and evaluates the nutrimentparameters of the
 * recipe, while in progress the controller uses NutrimentRules to evaluate
 * wether the goal of the recipe is achieved with the current ingredients.
 *
 * @author Stephan Svoboda
 */
public class CreateRecipeController
{

    // the recipe, which will be created and edited by this instance of the
    // controller
    private RecipeBo         _currentRecipe;
    private NutrimentRulesBo _currentRules;

    /**
     * Constructor
     */
    public CreateRecipeController()
    {
        _currentRecipe = new RecipeBo(new Recipe());
        _currentRules = new NutrimentRulesBo();
    }

    /**
     * Adds a NutrimentRule to NutrimentRules and evaluates the recipe including
     * the new rule. Returns the validated recipe.
     *
     * @param pdd ParameterDefinition for the new NutrimentRule.
     * @param cod CheckOperator for the new NutrimentRule.
     * @param value Boundary value to check by the new NutrimentRule.
     * @param pdud ParameterDefintionUnit which is corespondending to the
     * choosen value.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     * @throws NutrimentRuleException is thrown if the new NutrimentRule
     * contradicts the existing NutrimentRules.
     */
    public ValidatedRecipeVo addParameter(ParameterDefinitionData pdd,
            CheckOperatorData cod, double value,
            ParameterDefinitionUnitData pdud) throws NutrimentRuleException
    {
        _currentRules.addParameter((ParameterDefinitionBo) pdd,
                (CheckOperatorBo) cod, value, (ParameterDefinitionUnitBo) pdud);
        return checkRecipe();
    }

    /**
     * Adds a recipe as an ingredient to the recipe and evaluates the recipe
     * with all NutrimentRules and calculates the new values for all
     * NutrimentParameters.
     *
     * @param d recipe which is included as an ingridient.
     * @param unit Unit of weight.
     * @param amount Value of recipe weight.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     */
    public ValidatedRecipeVo addRecipeIngredient(RecipeData d,
            ParameterDefinitionUnitData unit, float amount)
    {

        _currentRecipe.addRecipeIngredient((RecipeBo) d, amount,
                (ParameterDefinitionUnitBo) unit);

        _currentRecipe.calcParameters();

        return this.checkRecipe();
    }

    /**
     * Changes an existing NutrimentRule to NutrimentRules and evaluates the recipe including
     * the new rule. Returns the validated recipe.
     *
     * @param pdd ParameterDefinition for the changed NutrimentRule.
     * @param cod CheckOperator for the changed NutrimentRule.
     * @param value Boundary value to check by the changed NutrimentRule.
     * @param pdud ParameterDefintionUnit which is corespondending to the
     * choosen value.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     * @throws NutrimentRuleException is thrown if the new NutrimentRule
     * contradicts the existing NutrimentRules.
     */
    public ValidatedRecipeVo changeParameter(NutrimentParameterRuleData ndr,
            CheckOperatorData checkOperator, double value,
            ParameterDefinitionUnitData pdud, ParameterDefinitionData pdd)
            throws NutrimentRuleException
    {

        _currentRules.changeParameter((NutrimentRuleBo) ndr,
                (CheckOperatorBo) checkOperator, value,
                (ParameterDefinitionUnitBo) pdud, (ParameterDefinitionBo) pdd);
        return checkRecipe();
    }

    /**
     * Changes a recipe which is an ingredient of the recipe and evaluates the
     * recipe with all NutrimentRules and calculates the new values for all
     * NutrimentParameters.
     *
     * @param d recipe which is changed which is an ingridient.
     * @param unit Unit of weight.
     * @param amount Value of recipe weight.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     */
    public ValidatedRecipeVo changeRecipeIngredient(float amount,
            RecipeData rd, ParameterDefinitionUnitData pdu)
    {

        _currentRecipe.changeRecipeIngredient(amount, (RecipeBo) rd,
                (ParameterDefinitionUnitBo) pdu);

        _currentRecipe.calcParameters();

        return checkRecipe();
    }

    /**
     * Checks the recipe with all NutrimentRules and calulates all recipe values
     * including all NutrimentParameters.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     */
    private ValidatedRecipeVo checkRecipe()
    {
        List<NutrimentParameterRuleData> nutrimentParams = new ArrayList<NutrimentParameterRuleData>();
        for (NutrimentRuleBo nrbo : this._currentRules
                .checkRecipe(this._currentRecipe))
        {
            nutrimentParams.add(nrbo);
        }
        ValidatedRecipeVo validatedRecipe = new ValidatedRecipeVo(
                this._currentRecipe, nutrimentParams);
        return validatedRecipe;
    }

    /**
     * Removes an existing NutrimentRule from the NutrimentRuleSet.
     *
     * @param param Rule to remove.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     */
    public ValidatedRecipeVo removeParameter(NutrimentParameterRuleData param)
    {
        _currentRules.removeParameter((NutrimentRuleBo) param);
        return checkRecipe();
    }

    /**
     * Removes an ingredient from the recipe and calculates all new recipe
     * specific values.
     *
     * @param rd Ingredient to remove.
     * @return ValidatedRecipeVo which has the information of the ingredients,
     * all NutrimentParmeters, weight and information about the NutrimentRules.
     */
    public ValidatedRecipeVo removeRecipeIngredient(RecipeData rd)
    {
        _currentRecipe.removeRecipeIngredient((RecipeBo) rd);
        _currentRecipe.calcParameters();
        return checkRecipe();
    }


    /**
     * Saves the recipe into database.
     * 
     * @param recipeName Name of the recipe.
     * @param preparation Cooking instruction of the recipe.
     * @param description Description of the recipe.
     * @param benefits Benefits of the recipe.
     */
    public void save(String recipeName, String preparation, String description,String benefits,
            double preperationTime, int difficulty)
    {
        _currentRecipe.setName(recipeName);
        _currentRecipe.setCookInstructions(ClobConverter.StringToClob(preparation+"\n Zubereitungszeit:"+preperationTime+" Stunden"));
        _currentRecipe.setDescription(ClobConverter.StringToClob(description));
        _currentRecipe.setDifficulty(difficulty);
        _currentRecipe.setBlsCode("Z100000");        
        _currentRecipe.setBenefits(ClobConverter.StringToClob(benefits));        
//        //default unit
//        _currentRecipe.setUnit(DAOFactory.getInstance().getParameterDefinitionUnitDAO().findById(6l,false));
//        
        HibernateUtil.currentSession().beginTransaction();
        _currentRecipe.save();
        HibernateUtil.currentSession().getTransaction().commit();
    }
}
