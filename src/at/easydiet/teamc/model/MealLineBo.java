package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.MealLine;



/**
 * MealLineBo generated by hbm2java
 */
public class MealLineBo  implements java.io.Serializable, Saveable {

     private RecipeBo _recipe;

     private MealLine _MealLine;



    private MealLineBo() {
    }

    public MealLineBo(MealLine mealLine) {
        this._MealLine = mealLine;
    }

	
    public MealLineBo(float quantity, RecipeBo recipeBo) {
        this(new MealLine(quantity, recipeBo.getRecipe()));
    }
    public MealLineBo(float quantity, Clob info, Set<MealLineBo> mealLinesBo, RecipeBo recipeBo) {
        this(quantity, recipeBo);
       this._MealLine.setInfo(info);
        for (MealLineBo mealLineBo : mealLinesBo) {
            this._MealLine.getMealLines().add(mealLineBo.getMealLine());
        }
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
    
    public Set<MealLineBo> getMealLines() {
        Set<MealLineBo> temp = new HashSet<MealLineBo>(this._MealLine.getMealLines().size());
        for (MealLine mealLine : this._MealLine.getMealLines()) {
            temp.add(new MealLineBo(mealLine));
        }
        return temp;
    }
    
    public void setMealLines(Set<MealLineBo> mealLinesBo) {
        for (MealLineBo mealLineBo : mealLinesBo) {
            this._MealLine.getMealLines().add(mealLineBo.getMealLine());
        }
    }
    public RecipeBo getRecipe() {
        return this._recipe;
    }
    
    public void setRecipe(RecipeBo recipeBo) {
        this._MealLine.setRecipe(recipeBo.getRecipe());
        this._recipe = recipeBo;
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

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}

