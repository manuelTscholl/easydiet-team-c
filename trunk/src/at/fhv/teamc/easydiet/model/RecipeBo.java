package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.Recipe;
import java.sql.Clob;
import java.util.Set;

/**
 * RecipeBo generated by hbm2java
 */
public class RecipeBo  implements java.io.Serializable {

     private Recipe _Recipe;

    public RecipeBo() {
    }

    public RecipeBo(Recipe recipe){
        this._Recipe=recipe;
    }

	
    public RecipeBo(Recipe recipe, String name, int difficulty) {
        this(recipe);
        this._Recipe.setName(name);
        this._Recipe.setDifficulty(difficulty);
    }
    public RecipeBo(Recipe recipe, String name, String blsCode, int difficulty, Clob description, Clob benefits, Clob cookInstructions, Set recipes, Set nutrimentParameters) {
       this(recipe, name, difficulty);
       this._Recipe.setBlsCode(blsCode);
       this._Recipe.setDescription(description);
       this._Recipe.setBenefits(benefits);
       this._Recipe.setCookInstructions(cookInstructions);
       this._Recipe.setIngredients(recipes);
       this._Recipe.setNutrimentParameters(nutrimentParameters);
    }
   
    public long getRecipeId() {
        return this.getRecipe().getRecipeId();
    }
    
    public void setRecipeId(long recipeId) {
        this.getRecipe().setRecipeId(recipeId);
    }
    public String getName() {
        return this.getRecipe().getName();
    }
    
    public void setName(String name) {
        this.getRecipe().setName(name);
    }
    public String getBlsCode() {
        return this.getRecipe().getBlsCode();
    }
    
    public void setBlsCode(String blsCode) {
        this.getRecipe().setBlsCode(blsCode);
    }
    public int getDifficulty() {
        return this.getRecipe().getDifficulty();
    }
    
    public void setDifficulty(int difficulty) {
        this.getRecipe().setDifficulty(difficulty);
    }
    public Clob getDescription() {
        return this.getRecipe().getDescription();
    }
    
    public void setDescription(Clob description) {
        this.getRecipe().setDescription(description);
    }
    public Clob getBenefits() {
        return this.getRecipe().getBenefits();
    }
    
    public void setBenefits(Clob benefits) {
        this.getRecipe().setBenefits(benefits);
    }
    public Clob getCookInstructions() {
        return this.getRecipe().getCookInstructions();
    }
    
    public void setCookInstructions(Clob cookInstructions) {
        this.getRecipe().setCookInstructions(cookInstructions);
    }
    public Set getRecipes() {
        return this.getRecipe().getIngredients();
    }
    
    public void setRecipes(Set recipes) {
        this.getRecipe().setIngredients(recipes);
    }
    public Set getNutrimentParameters() {
        return this.getRecipe().getNutrimentParameters();
    }
    
    public void setNutrimentParameters(Set nutrimentParameters) {
        this.getRecipe().setNutrimentParameters(nutrimentParameters);
    }

    /**
     * @return the _Recipe
     */
    protected Recipe getRecipe() {
        return _Recipe;
    }

    /**
     * @param Recipe the _Recipe to set
     */
    public void setRecipe(Recipe Recipe) {
        this._Recipe = Recipe;
    }




}


