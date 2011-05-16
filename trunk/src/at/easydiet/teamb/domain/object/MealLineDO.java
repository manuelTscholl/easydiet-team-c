/*
 * 
 */
package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.MealLineViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.teamb.domain.IMealLine;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.MealLine;

/**
 * Represents a MealLine in the domain layer
 */
public class MealLineDO extends AbstractDO<MealLine> implements IMealLine {
	private static Logger LOGGER = Logger.getLogger(MealLineDO.class);

	private MealLine _mealLine;

	/**
	 * Instantiates a new meal line.
	 * 
	 * @param mealLine
	 *            the meal line
	 */
	public MealLineDO(MealLine mealLine) {
		if (mealLine == null) {
			LOGGER.debug("MealLine is null");
		}

		_mealLine = mealLine;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getMealLineId()
	 */
	@Override
	public long getMealLineId() {
		return _mealLine.getMealLineId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setMealLineId(long)
	 */
	@Override
	public void setMealLineId(long mealLineId) {
		_mealLine.setMealLineId(mealLineId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getQuantity()
	 */
	@Override
	public float getQuantity() {
		return _mealLine.getQuantity();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setQuantity(float)
	 */
	@Override
	public void setQuantity(float quantity) {
		_mealLine.setQuantity(quantity);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getInfo()
	 */
	@Override
	public String getInfo() {
		return ClobConverter.ClobToString(_mealLine.getInfo());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setInfo(java.lang.String)
	 */
	@Override
	public void setInfo(String info) {
		_mealLine.setInfo(ClobConverter.StringToClob(info));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getAlternatives()
	 */
	@Override
	public MealLineViewable[] getAlternatives() {
		return ListConverter.toDOArray(_mealLine.getAlternatives(), new ListConverter.ModelToDO<MealLineDO, MealLine>() {
			@Override
			public MealLineDO convert(MealLine model) {
				return new MealLineDO(model);
			}
		}).toArray(new MealLineViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#addAlternative(at.easydiet.domain.object.MealLineDO)
	 */
	public void addAlternative(IMealLine alternative) {
		_mealLine.getAlternatives().add(((MealLineDO)alternative).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#removeAlternative(at.easydiet.domain.object.MealLineDO)
	 */
	@Override
	public void removeAlternative(IMealLine alternative) {
		_mealLine.getAlternatives().remove(((MealLineDO)alternative).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getRecipe()
	 */
	@Override
	public RecipeViewable getRecipe() {
		return new RecipeDO(_mealLine.getRecipe());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setRecipe(at.easydiet.application.viewobject.RecipeViewable)
	 */
	@Override
	public void setRecipe(RecipeViewable recipe) {
		_mealLine.setRecipe((recipe != null) ? ((RecipeDO) recipe).getModel() : null);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#getMeal()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getMeal()
	 */
	@Override
	public MealViewable getMeal() {
		return new MealDO(_mealLine.getMeal());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setMeal(at.easydiet.application.viewobject.MealViewable)
	 */
	@Override
	public void setMeal(MealViewable Meal) {
		MealDO d = (MealDO) Meal;
		_mealLine.setMeal(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#isAlternative()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#isAlternative()
	 */
	@Override
	public boolean isAlternative() {
		return !(_mealLine.getParent() == null);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#getParent()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getParent()
	 */
	@Override
	public MealLineViewable getParent() {
		return new MealLineDO(_mealLine.getParent());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setParent(at.easydiet.application.viewobject.MealLineViewable)
	 */
	@Override
	public void setParent(MealLineViewable parent) {
		MealLineDO p = (MealLineDO) parent;
		_mealLine.setParent(p.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#removeParent()
	 */
	@Override
	public void removeParent() {
		_mealLine.setParent(null);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_mealLine == null);
	}

	@Override
	public MealLine getModel() {
		return _mealLine;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealLineViewable#getUnit()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#getUnit()
	 */
	@Override
	public ParameterDefinitionUnitViewable getUnit() {
		return new ParameterDefinitionUnitDO(_mealLine.getUnit());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMealLine#setUnit(at.easydiet.application.viewobject.ParameterDefinitionUnitViewable)
	 */
	@Override
	public void setUnit(ParameterDefinitionUnitViewable unit) {
		_mealLine.setUnit(((ParameterDefinitionUnitDO) unit).getModel());
	}
}
