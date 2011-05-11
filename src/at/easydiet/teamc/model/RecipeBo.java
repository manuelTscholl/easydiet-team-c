package at.easydiet.teamc.model;

// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DietParameterUnitController;
import at.easydiet.teamc.model.data.NutrimentParameterData;
import java.sql.Clob;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.model.Recipe;
import at.easydiet.model.RecipeIngredient;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.RecipeIngredientData;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import sun.io.Converters;




/**
 * RecipeBo generated by hbm2java
 */
public class RecipeBo implements java.io.Serializable, Saveable, RecipeData
{
    
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
    .getLogger(RecipeBo.class);

    private Recipe _Recipe;

    private RecipeBo()
    {}

    public RecipeBo(Recipe recipe)
    {
        this._Recipe = recipe;
    }

    public RecipeBo(Recipe recipe, String name, int difficulty)
    {
        this(recipe);
        this._Recipe.setName(name);
        this._Recipe.setDifficulty(difficulty);
    }

    public RecipeBo(Recipe recipe, String name, String blsCode, int difficulty,
            Clob description, Clob benefits, Clob cookInstructions,
            Set<RecipeIngredientBo> recipesIngredientsBo,
            Set<NutrimentParameterBo> nutrimentParametersBo)
    {
        this(recipe, name, difficulty);
        this._Recipe.setBlsCode(blsCode);
        this._Recipe.setDescription(description);
        this._Recipe.setBenefits(benefits);
        this._Recipe.setCookInstructions(cookInstructions);
        for (RecipeIngredientBo recipeIngredientBo : recipesIngredientsBo)
        {
            this._Recipe.getIngredients().add(
                    recipeIngredientBo.getRecipeIngredient());
        }

        for (NutrimentParameterBo nutrimentParameterBo : nutrimentParametersBo)
        {
            this._Recipe.getNutrimentParameters().add(
                    nutrimentParameterBo.getNutrimentParameter());
        }

    }

    /**
     * Calculates the values for all nutriment parameter.
     */
    public void calculateNutrimentParameter()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getRecipeId()
    {
        return this.getRecipe().getRecipeId();
    }

    public void setRecipeId(long recipeId)
    {
        this.getRecipe().setRecipeId(recipeId);
    }

    public String getName()
    {
        return this.getRecipe().getName();
    }

    public void setName(String name)
    {
        this.getRecipe().setName(name);
    }

    public String getBlsCode()
    {
        return this.getRecipe().getBlsCode();
    }

    public void setBlsCode(String blsCode)
    {
        this.getRecipe().setBlsCode(blsCode);
    }

    public int getDifficulty()
    {
        return this.getRecipe().getDifficulty();
    }

    public void setDifficulty(int difficulty)
    {
        this.getRecipe().setDifficulty(difficulty);
    }

    public Clob getDescription()
    {
        return this.getRecipe().getDescription();
    }

    public void setDescription(Clob description)
    {
        this.getRecipe().setDescription(description);
    }

    public Clob getBenefits()
    {
        return this.getRecipe().getBenefits();
    }

    public void setBenefits(Clob benefits)
    {
        this.getRecipe().setBenefits(benefits);
    }

    public Clob getCookInstructions()
    {
        return this.getRecipe().getCookInstructions();
    }

    public void setCookInstructions(Clob cookInstructions)
    {
        this.getRecipe().setCookInstructions(cookInstructions);
    }

    public Set<RecipeIngredientBo> getRecipeIngredientsBo()
    {
        Set<RecipeIngredientBo> temp = new HashSet<RecipeIngredientBo>(
                this._Recipe.getIngredients().size());
        for (RecipeIngredient recipeIngredient : this._Recipe.getIngredients())
        {
            temp.add(new RecipeIngredientBo(recipeIngredient));
        }
        return temp;
    }

    public void setRecipeIngredientsBo(
            Set<RecipeIngredientBo> recipesIngredientsBo)
    {
        for (RecipeIngredientBo recipeIngredientBo : recipesIngredientsBo)
        {
            this._Recipe.getIngredients().add(
                    recipeIngredientBo.getRecipeIngredient());
        }
    }

    public Set<NutrimentParameterBo> getNutrimentParameters()
    {
        Set<NutrimentParameterBo> temp = new HashSet<NutrimentParameterBo>(
                this._Recipe.getNutrimentParameters().size());
        for (NutrimentParameter nutrimentParameter : this._Recipe
                .getNutrimentParameters())
        {
            temp.add(new NutrimentParameterBo(nutrimentParameter));
        }
        return temp;
    }

    public void setNutrimentParameters(
            Set<NutrimentParameterBo> nutrimentParametersBo)
    {
        for (NutrimentParameterBo nutrimentParameterBo : nutrimentParametersBo)
        {
            this._Recipe.getNutrimentParameters().add(
                    nutrimentParameterBo.getNutrimentParameter());
        }
    }

    /**
     * @return the _Recipe
     */
    protected Recipe getRecipe()
    {
        return _Recipe;
    }

    /**
     * @param Recipe
     *            the _Recipe to set
     */
    public void setRecipe(Recipe Recipe)
    {
        this._Recipe = Recipe;
    }

    @Override
    public boolean save()
    {
        if (DAOFactory.getInstance().getRecipeDAO().makePersistent(getRecipe()) != null)
        {
            return true;
        }
        return false;
    }

    protected double getNutrimentParameterValue(NutrimentParameterBo npb)
    {
        for (NutrimentParameterBo nutrimentParameterBo : this
                .getNutrimentParameters())
        {
            if (nutrimentParameterBo.getParameterDefinition().getName()
                    .equals(npb.getParameterDefinition().getName()))
            {
                return Double.parseDouble(nutrimentParameterBo.getValue());
            }
        }
        return 0;
    }

    public String toString()
    {
        return _Recipe.getName();
    }

    public List<RecipeIngredientData> getRecipeIngredientsData()
    {
        List<RecipeIngredientData> temp = new ArrayList<RecipeIngredientData>(
                this.getRecipeIngredientsBo().size());

        if (this.getRecipeIngredientsBo().size() <= 0)
        {
            temp.add((RecipeIngredientData) this);
            return temp;
        }

        for (RecipeIngredientBo rib : this.getRecipeIngredientsBo())
        {
            temp.add((RecipeIngredientData) rib.getIngredient());
        }

        return temp;
    }

    public List<NutrimentParameterData> getNutrimentParametersData()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getTotalAmount()
    {
        return this._Recipe.getAmount();
    }

    public void calcParameters()
    {
        //mapping of the special parameter to its sum value class
        Map<Long, ValidationSumValue> summedIngredientParameter = new HashMap<Long, RecipeBo.ValidationSumValue>();
        Map<Long, NutrimentParameter> idParameterMapping = new HashMap<Long, NutrimentParameter>();
        
        
        for (RecipeIngredientBo recipeIngredient : getRecipeIngredientsBo())
        {//all ingredients
            for (NutrimentParameter parameter : recipeIngredient
                    .getIngredient().getRecipe().getNutrimentParameters())
            {//for each parameter in the engredient
                if (summedIngredientParameter.containsKey(parameter
                        .getNutrimentParameterId()))
                {//if parameter already exists
                    ValidationSumValue sum = summedIngredientParameter
                            .get(parameter.getNutrimentParameterId());
                    ParameterDefinitionUnitBo target = sum.getUnitBo();
                    ParameterDefinitionUnitBo source = new ParameterDefinitionUnitBo(
                            parameter.getUnit());

                    float sumValue;
                    try
                    {//converting the type and then sum it
                        sumValue = DietParameterUnitController.getInstance()
                                .convert(source, target,
                                        Float.parseFloat(parameter.getValue()));
                        sum.setSum(sum.getSum() + sumValue);
                    }
                    catch (NumberFormatException e)
                    {
                        LOGGER.debug(e);
                    }
                    catch (OperationNotSupportedException e)
                    {
                        LOGGER.debug(e);
                    }
                    
                }
                else
                {
                    ValidationSumValue validSum = new ValidationSumValue(
                            new ParameterDefinitionUnitBo(parameter.getUnit()));
                    validSum.setSum(Float.parseFloat(parameter.getValue()));
                    summedIngredientParameter.put(
                            parameter.getNutrimentParameterId(), validSum);
                    idParameterMapping.put(parameter.getNutrimentParameterId(), parameter);
                }

            }
        }
        
        //all recipes with the correct sum and unit will be added to the recipe
        for (Entry<Long, ValidationSumValue> item : summedIngredientParameter.entrySet())
        {
            NutrimentParameter tempValue = idParameterMapping.get(item.getKey());
            
            tempValue.setUnit(item.getValue().getUnit());
            tempValue.setValue(String.valueOf(item.getValue().getSum()));            
        }
    }

    public void addRecipeIngredient(RecipeBo recipeBo, float amount)
    {
        RecipeIngredientBo recipeIngredientBo=new RecipeIngredientBo(amount, null, recipeBo);
        this.getRecipeIngredientsBo().add(recipeIngredientBo);
        recipeBo.getRecipeIngredientsBo().add(recipeIngredientBo);
        this.calcParameters();
    }

    
    private static class ValidationSumValue
    {
        private float                     _sum;
        private ParameterDefinitionUnitBo _unit;

        /**
         * Gets the sum.
         * 
         * @return the sum
         */
        public float getSum()
        {
            return _sum;
        }

        /**
         * Sets the sum.
         * 
         * @param sum
         *            the sum to set
         */
        public void setSum(float sum)
        {
            _sum = sum;
        }

        /**
         * Gets the unit.
         * 
         * @return the unit
         */
        public ParameterDefinitionUnitBo getUnitBo()
        {
            return _unit;
        }
        
        /**
         * Gets the unit.
         * 
         * @return the unit
         */
        public ParameterDefinitionUnit getUnit()
        {
            return _unit.getParameterDefinitionUnit();
        }

        /**
         * Initializes a new instance of the {@link ValidationSumValue} class.
         * 
         * @param sum
         * @param unit
         */
        private ValidationSumValue(ParameterDefinitionUnitBo unit)
        {
            super();
            _sum = 0;
            _unit = unit;
        }
    }

}
