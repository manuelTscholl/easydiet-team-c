package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.MealLineViewable;
import at.easydiet.teamb.application.viewobject.TimeSpanViewable;
import at.easydiet.teamb.domain.IDietParameter;
import at.easydiet.teamb.domain.IMeal;
import at.easydiet.teamb.domain.IMealLine;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.DietParameter;
import at.easydiet.model.Meal;
import at.easydiet.model.MealLine;

/**
 * Represents a Meal in the domain layer
 */
public class MealDO extends AbstractDO<Meal> implements IMeal {
	private static Logger LOGGER = Logger.getLogger(MealDO.class);

	private Meal _meal;

	/**
	 * Instantiates a new meal.
	 * 
	 * @param meal
	 *            the meal
	 */
	public MealDO(Meal meal) {
		if (meal == null) {
			LOGGER.debug("Meal is null");
		}

		_meal = meal;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#getMealId()
	 */
	@Override
	public long getMealId() {
		return _meal.getMealId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#setMealId(long)
	 */
	@Override
	public void setMealId(long mealId) {
		_meal.setMealId(mealId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#getCode()
	 */
	@Override
	public String getCode() {
		return _meal.getCode();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#setCode(java.lang.String)
	 */
	@Override
	public void setCode(String code) {
		_meal.setCode(code);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#getName()
	 */
	@Override
	public String getName() {
		return _meal.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_meal.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#getDietParameters()
	 */
	@Override
	public DietParameterViewable[] getDietParameters() {
		return ListConverter.toDOArray(_meal.getDietParameters(), new ListConverter.ModelToDO<DietParameterDO, DietParameter>() {
			@Override
			public DietParameterDO convert(DietParameter model) {
				return new DietParameterDO(model);
			}
		}).toArray(new DietParameterViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#addDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void addDietParameter(IDietParameter dietParameter) {
		_meal.getDietParameters().add(((DietParameterDO)dietParameter).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#removeDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void removeDietParameter(IDietParameter dietParameter) {
		_meal.getDietParameters().remove(((DietParameterDO)dietParameter).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#getMealLines()
	 */
	@Override
	public MealLineViewable[] getMealLines() {
		return ListConverter.toDOArray(_meal.getMealLines(), new ListConverter.ModelToDO<MealLineDO, MealLine>() {
			@Override
			public MealLineDO convert(MealLine model) {
				return new MealLineDO(model);
			}
		}).toArray(new MealLineViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#addMealLine(at.easydiet.domain.object.MealLineDO)
	 */
	@Override
	public void addMealLine(IMealLine mealLine) {
		_meal.getMealLines().add(((MealLineDO)mealLine).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#removeMealLine(at.easydiet.domain.object.MealLineDO)
	 */
	@Override
	public void removeMealLine(IMealLine mealLine) {
		_meal.getMealLines().remove(((MealLineDO)mealLine).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#getTimeSpan()
	 */
	@Override
	public TimeSpanViewable getTimeSpan() {
		return new TimeSpanDO(_meal.getTimeSpan());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#setTimeSpan(at.easydiet.application.viewobject.TimeSpanViewable)
	 */
	@Override
	public void setTimeSpan(TimeSpanViewable timespan) {
		TimeSpanDO d = (TimeSpanDO) timespan;
		_meal.setTimeSpan(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IMeal#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_meal == null);
	}

	@Override
	public Meal getModel() {
		return _meal;
	}
}
