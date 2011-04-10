package at.fhv.teamc.easydiet.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import at.easydiet.model.MealLine;
import java.sql.Clob;
import java.util.Set;

/**
 * MealLineBo generated by hbm2java
 */
public class MealLineBo  implements java.io.Serializable {

     private RecipeBo _recipe;

     private MealLine _MealLine;



    public MealLineBo() {
    }

    public MealLineBo(MealLine mealLine) {
        this._MealLine = mealLine;
    }

	
    public MealLineBo(MealLine mealLine, float quantity, boolean add, RecipeBo recipeBo) {
        this(mealLine);
        this._MealLine.setQuantity(quantity);
        this._MealLine.setAdd(add);
        this._recipe = recipeBo;
    }
    public MealLineBo(MealLine mealLine, float quantity, Clob info, boolean add, Set mealLines, RecipeBo recipeBo) {
        this(mealLine, quantity, add, recipeBo);
       this._MealLine.setInfo(info);
       this._MealLine.setMealLines(mealLines);
    }
   
    public long getMealLineId() {
        return this.getMealLine().getMealLineId();
    }
    
    public void setMealLineId(long mealLineId) {
        this.getMealLine().setMealLineId(mealLineId);
    }
    public float getQuantity() {
        return this.getMealLine().getQuantity();
    }
    
    public void setQuantity(float quantity) {
        this.getMealLine().setQuantity(quantity);
    }
    public Clob getInfo() {
        return this.getMealLine().getInfo();
    }
    
    public void setInfo(Clob info) {
        this.getMealLine().setInfo(info);
    }
    public boolean isAdd() {
        return this.getMealLine().isAdd();
    }
    
    public void setAdd(boolean add) {
        this.getMealLine().setAdd(add);
    }
    public Set getMealLines() {
        return this.getMealLine().getMealLines();
    }
    
    public void setMealLines(Set mealLines) {
        this.getMealLine().setMealLines(mealLines);
    }
    public RecipeBo getRecipe() {
        return this._recipe;
    }
    
    public void setRecipe(RecipeBo recipe) {
        this._MealLine.setRecipe(recipe.getRecipe());
        this._recipe = recipe;
    }

    /**
     * @return the _MealLine
     */
    protected MealLine getMealLine() {
        return _MealLine;
    }

    /**
     * @param MealLine the _MealLine to set
     */
    public void setMealLine(MealLine MealLine) {
        this._MealLine = MealLine;
    }




}


