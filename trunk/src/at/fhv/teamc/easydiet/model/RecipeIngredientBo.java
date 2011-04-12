package at.fhv.teamc.easydiet.model;

import at.easydiet.model.RecipeIngredient;


/**
 * Represents a RecipeIngredient
 */
public class RecipeIngredientBo  implements java.io.Serializable
{

    
    private RecipeBo _ingredient;
    private RecipeIngredient _RecipeIngredient;

    /**
     * Initializes a new instance of the {@link RecipeIngredient} class.
     */
    private RecipeIngredientBo()
    {
        // no initialization
    }

    public RecipeIngredientBo(RecipeIngredient recipeIngredient){
        this._RecipeIngredient=recipeIngredient;
    }

    /**
     * Initializes a new instance of the {@link RecipeIngredient} class.
     * @param amount the amount to set for this instance
     * @param ingredientBo the ingredient to set for this instance
     */
    public RecipeIngredientBo(float amount, RecipeBo ingredientBo)
    {
        this(new RecipeIngredient(amount, ingredientBo.getRecipe()));
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
    public float getAmount() 
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
        return this._ingredient;
    }
    
    /**       
     * Sets the ingredient of this instance. 
     * @param ingredientBo the new ingredient of this instance.
     */    
    public void setIngredient(RecipeBo ingredientBo)
    {
        this.getRecipeIngredient().setIngredient(ingredientBo.getRecipe());
        this._ingredient=ingredientBo;

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
    
    
}
