package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.teamb.domain.IRecipeIngredient;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.RecipeIngredient;

/**
 * Represents a RecipeIngredient in the domain layer
 */
public class RecipeIngredientDO extends AbstractDO<RecipeIngredient> implements IRecipeIngredient {
	private static Logger LOGGER = Logger.getLogger(RecipeIngredientDO.class);

	private RecipeIngredient _recipeIngredient;

	/**
	 * Instantiates a new recipeIngredient.
	 * 
	 * @param recipeIngredient
	 *            the recipeIngredient
	 */
	public RecipeIngredientDO(RecipeIngredient recipeIngredient) {
		if (recipeIngredient == null) {
			LOGGER.debug("RecipeIngredient is null");
		}

		_recipeIngredient = recipeIngredient;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#getRecipeIngredientId()
	 */
	@Override
	public long getRecipeIngredientId() {
		return _recipeIngredient.getRecipeIngredientId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#setRecipeIngredientId(long)
	 */
	@Override
	public void setRecipeIngredientId(long recipeIngredientId) {
		_recipeIngredient.setRecipeIngredientId(recipeIngredientId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#getAmount()
	 */
	@Override
	public float getAmount() {
		return _recipeIngredient.getAmount();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#setAmount(float)
	 */
	@Override
	public void setAmount(float amount) {
		_recipeIngredient.setAmount(amount);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#getIngredient()
	 */
	@Override
	public RecipeViewable getIngredient() {
		return new RecipeDO(_recipeIngredient.getIngredient());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#setIngredient(at.easydiet.application.viewobject.RecipeViewable)
	 */
	@Override
	public void setIngredient(RecipeViewable ingredient) {
		RecipeDO recipeDO = (RecipeDO) ingredient;
		_recipeIngredient.setIngredient(recipeDO.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#getRecipe()
	 */
	@Override
	public RecipeViewable getRecipe() {
		return new RecipeDO(_recipeIngredient.getRecipe());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#setRecipe(at.easydiet.application.viewobject.RecipeViewable)
	 */
	@Override
	public void setRecipe(RecipeViewable Recipe) {
		RecipeDO d = (RecipeDO) Recipe;
		_recipeIngredient.setRecipe(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IRecipeIngredient#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_recipeIngredient == null);
	}

	@Override
	public RecipeIngredient getModel() {
		return _recipeIngredient;
	}
}
