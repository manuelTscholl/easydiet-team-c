package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.NutrimentParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.RecipeIngredientViewable;
import at.easydiet.teamb.domain.IRecipe;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.Recipe;
import at.easydiet.model.RecipeIngredient;

/**
 * Represents a Recipe in the domain layer
 */
public class RecipeDO extends AbstractDO<Recipe> implements IRecipe {
	private static Logger LOGGER = Logger.getLogger(RecipeDO.class);

	private Recipe _recipe;
	private boolean _disfavor;

	/**
	 * Instantiates a new recipe.
	 * 
	 * @param recipe
	 *            the recipe
	 */
	public RecipeDO(Recipe recipe) {
		if (recipe == null) {
			LOGGER.debug("Recipe is null");
		}

		_recipe = recipe;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getRecipeId()
	 */
	@Override
	public long getRecipeId() {
		return _recipe.getRecipeId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setRecipeId(long)
	 */
	@Override
	public void setRecipeId(long recipeId) {
		_recipe.setRecipeId(recipeId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getName()
	 */
	@Override
	public String getName() {
		return _recipe.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_recipe.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getBlsCode()
	 */
	@Override
	public String getBlsCode() {
		return _recipe.getBlsCode();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setBlsCode(java.lang.String)
	 */
	@Override
	public void setBlsCode(String blsCode) {
		_recipe.setBlsCode(blsCode);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getDifficulty()
	 */
	@Override
	public int getDifficulty() {
		return _recipe.getDifficulty();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setDifficulty(int)
	 */
	@Override
	public void setDifficulty(int difficulty) {
		_recipe.setDifficulty(difficulty);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getDescription()
	 */
	@Override
	public String getDescription() {
		return ClobConverter.ClobToString(_recipe.getDescription());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		_recipe.setDescription(ClobConverter.StringToClob(description));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getBenefits()
	 */
	@Override
	public String getBenefits() {
		return ClobConverter.ClobToString(_recipe.getBenefits());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setBenefits(java.lang.String)
	 */
	@Override
	public void setBenefits(String benefits) {
		_recipe.setBenefits(ClobConverter.StringToClob(benefits));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getCookInstructions()
	 */
	@Override
	public String getCookInstructions() {
		return ClobConverter.ClobToString(_recipe.getCookInstructions());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setCookInstructions(java.lang.String)
	 */
	@Override
	public void setCookInstructions(String cookInstructions) {
		_recipe.setCookInstructions(ClobConverter.StringToClob(cookInstructions));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getAmount()
	 */
	@Override
	public float getAmount() {
		return _recipe.getAmount();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setAmount(float)
	 */
	@Override
	public void setAmount(float amount) {
		_recipe.setAmount(amount);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getUnit()
	 */
	@Override
	public ParameterDefinitionUnitViewable getUnit() {
		return new ParameterDefinitionUnitDO(_recipe.getUnit());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#setUnit(at.easydiet.application.viewobject.ParameterDefinitionUnitViewable)
	 */
	@Override
	public void setUnit(ParameterDefinitionUnitViewable unit) {
		ParameterDefinitionUnitDO d = (ParameterDefinitionUnitDO) unit;
		_recipe.setUnit(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getIngredients()
	 */
	@Override
	public RecipeIngredientViewable[] getIngredients() {
		return ListConverter.toDOArray(_recipe.getIngredients(), new ListConverter.ModelToDO<RecipeIngredientDO, RecipeIngredient>() {
			@Override
			public RecipeIngredientDO convert(RecipeIngredient model) {
				return new RecipeIngredientDO(model);
			}
		}).toArray(new RecipeIngredientViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#addIngredient(at.easydiet.domain.object.RecipeIngredientDO)
	 */
	@Override
	public void addIngredient(RecipeIngredientDO ingredient) {
		_recipe.getIngredients().add(ingredient.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#removeIngredient(at.easydiet.domain.object.RecipeIngredientDO)
	 */
	@Override
	public void removeIngredient(RecipeIngredientDO ingredient) {
		_recipe.getIngredients().remove(ingredient.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#getNutrimentParameters()
	 */
	@Override
	public NutrimentParameterViewable[] getNutrimentParameters() {
		return ListConverter.toDOArray(_recipe.getNutrimentParameters(), new ListConverter.ModelToDO<NutrimentParameterDO, NutrimentParameter>() {
			@Override
			public NutrimentParameterDO convert(NutrimentParameter model) {
				return new NutrimentParameterDO(model);
			}
		}).toArray(new NutrimentParameterViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#addNutrimentParameter(at.easydiet.domain.object.NutrimentParameterDO)
	 */
	@Override
	public void addNutrimentParameter(NutrimentParameterDO nutrimentParameter) {
		_recipe.getNutrimentParameters().add(nutrimentParameter.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#removeNutrimentParameter(at.easydiet.domain.object.NutrimentParameterDO)
	 */
	@Override
	public void removeNutrimentParameter(NutrimentParameterDO nutrimentParameter) {
		_recipe.getNutrimentParameters().remove(nutrimentParameter.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipe#isChecked()
	 */
	@Override
	public boolean isDisfavor() {
		return _disfavor;
	}

	@Override
	public Recipe getModel() {
		return _recipe;
	}
}
