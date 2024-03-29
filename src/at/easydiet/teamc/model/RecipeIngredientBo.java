package at.easydiet.teamc.model;

import at.easydiet.model.RecipeIngredient;
import at.easydiet.teamc.model.data.NutrimentParameterData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.RecipeIngredientData;
import java.sql.Clob;
import java.util.List;


/**
 * Represents a RecipeIngredient
 */
public class RecipeIngredientBo  implements java.io.Serializable, Saveable, RecipeIngredientData
{

    
    private RecipeBo _recipe;
    private RecipeIngredient _RecipeIngredient;
    private ParameterDefinitionUnitBo _UnitBo;

    /**
     * Initializes a new instance of the {@link RecipeIngredient} class.
     */
    private RecipeIngredientBo()
    {
        // no initialization
    }

    public RecipeIngredientBo(RecipeIngredient recipeIngredient){
        this._RecipeIngredient=recipeIngredient;
        this._recipe=new RecipeBo(_RecipeIngredient.getRecipe());
    }

    /**
     * Initializes a new instance of the {@link RecipeIngredient} class.
     * @param amount the amount to set for this instance
     * @param ingredientBo the ingredient to set for this instance
     */
    public RecipeIngredientBo(float amount, RecipeBo ingredientBo, RecipeBo recipeBo)
    {
        RecipeIngredient temp;
        if(ingredientBo!=null){
            temp = new RecipeIngredient(amount, ingredientBo.getRecipe(), recipeBo.getRecipe());
            this._recipe=recipeBo;
        }else{
            temp = new RecipeIngredient(amount, null, recipeBo.getRecipe());
            this._recipe=recipeBo;
        }
        this._RecipeIngredient=temp;
    }

    public RecipeIngredientBo(float amount, RecipeBo ingredientBo, RecipeBo recipeBo, ParameterDefinitionUnitBo pdub)
    {
        this(amount, ingredientBo, recipeBo);

        if(pdub!=null){
            _UnitBo=pdub;
        }
    }
   
    /**       
     * Gets the recipeIngredientId of this instance. 
     * @return the recipeIngredientId currently set for this instance.
     */
    public long getRecipeIngredientId() 
    {
        return this.getRecipeIngredient().getRecipeIngredientId();
    }
    
    /**       
     * Sets the recipeIngredientId of this instance. 
     * @param recipeIngredientId the new recipeIngredientId of this instance.
     */    
    public void setRecipeIngredientId(long recipeIngredientId) 
    {
        this.getRecipeIngredient().setRecipeIngredientId(recipeIngredientId);
    }
    
    /**       
     * Gets the amount of this instance. 
     * @return the amount currently set for this instance.
     */
    public float getIngredientAmount()
    {
        return this.getRecipeIngredient().getAmount();
    }
    
    /**       
     * Sets the amount of this instance. 
     * @param amount the new amount of this instance.
     */    
    public void setAmount(float amount) 
    {
        this.getRecipeIngredient().setAmount(amount);
    }
    
    /**       
     * Gets the ingredient of this instance. 
     * @return the ingredient currently set for this instance.
     */
    public RecipeBo getIngredient()
    {
        return this.getRecipe();
    }
    
    /**       
     * Sets the ingredient of this instance. 
     * @param ingredientBo the new ingredient of this instance.
     */    
    public void setIngredient(RecipeBo ingredientBo)
    {
        this.getRecipeIngredient().setIngredient(ingredientBo.getRecipe());
        this._recipe=ingredientBo;

    }

    /**
     * @return the _RecipeIngredient
     */
    protected RecipeIngredient getRecipeIngredient() {
        return _RecipeIngredient;
    }

    /**
     * @param RecipeIngredient the _RecipeIngredient to set
     */
    public void setRecipeIngredient(RecipeIngredient RecipeIngredient) {
        this._RecipeIngredient = RecipeIngredient;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RecipeData getRecipeData() {
        return (RecipeData) this.getRecipe();
    }

    public void setUnit(ParameterDefinitionUnitBo pdub){
        this._UnitBo=pdub;
    }

    /**
     * @return the _recipe
     */
    public RecipeBo getRecipe() {
        return _recipe;
    }

}
