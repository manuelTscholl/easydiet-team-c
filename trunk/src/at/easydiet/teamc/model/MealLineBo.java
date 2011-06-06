package at.easydiet.teamc.model;

// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import java.sql.Clob;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.MealLine;
import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.teamc.model.data.MealLineData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.util.ClobConverter;

/**
 * MealLineBo generated by hbm2java
 */
public class MealLineBo implements java.io.Serializable, Saveable, MealLineData {

	private RecipeBo _recipe;

	private MealLine _MealLine;

	public MealLineBo() {

	}

	public MealLineBo(MealLine mealLine) {
		this._MealLine = mealLine;
		setUnitDefaultValue();
		this._recipe = new RecipeBo(mealLine.getRecipe());
	}

	public MealLineBo(float quantity, RecipeBo recipeBo,
			ParameterDefinitionUnitBo parameterDefinitionUnitBo,
			MealLineBo parentbo, MealBo mealBo) {
		this(new MealLine(quantity, ClobConverter.StringToClob(""), null,
				recipeBo.getRecipe(), null, parentbo.getMealLine(),
				mealBo.getMeal()));
		setUnitDefaultValue();
	}

	public MealLineBo(float quantity, Clob info, Set<MealLineBo> mealLinesBo,
			RecipeBo recipeBo,
			ParameterDefinitionUnitBo parameterDefinitionUnitBo,
			MealLineBo parentBo, MealBo mealBo) {
		this(quantity, recipeBo, parameterDefinitionUnitBo, parentBo, mealBo);
		this._MealLine.setInfo(info);
		for (MealLineBo mealLineBo : mealLinesBo) {
			this._MealLine.getAlternatives().add(mealLineBo.getMealLine());
		}
		setUnitDefaultValue();
	}

	@Override
	public long getMealLineId() {
		return this.getMealLine().getMealLineId();
	}

	public void setMealLineId(long mealLineId) {
		this.getMealLine().setMealLineId(mealLineId);
	}

	@Override
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
		Set<MealLineBo> temp = new HashSet<MealLineBo>(this._MealLine
				.getAlternatives().size());
		for (MealLine mealLine : this._MealLine.getAlternatives()) {
			temp.add(new MealLineBo(mealLine));
		}
		return temp;
	}

	public void setMealLines(Set<MealLineBo> mealLinesBo) {
		for (MealLineBo mealLineBo : mealLinesBo) {
			this._MealLine.getAlternatives().add(mealLineBo.getMealLine());
		}
	}

	public void addMealLine(MealLineBo mlb) {
		this._MealLine.getAlternatives().add(mlb.getMealLine());
	}

	public RecipeBo getRecipe() {
		if (this._recipe.getRecipe() == null) {
			return null;
		}
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

	@Override
	public Set<MealLineData> getMealLineData() {
		Set<MealLineData> mld = new HashSet<MealLineData>();
		Set<MealLineBo> mlb = this.getMealLines();
		for (MealLineBo mealline : mlb) {
			mld.add(mealline);
		}
		return mld;
	}

	@Override
	public RecipeData getRecipeData() {
		return this.getRecipe();
	}

	/**
    *
    */
	public void setUnitDefaultValue() {// FIX quick and dirty

		if (_MealLine.getUnit() != null)
			return;
		List<ParameterDefinitionUnit> definitions = DAOFactory.getInstance()
				.getParameterDefinitionUnitDAO().findAll();
		if (definitions != null)
			for (ParameterDefinitionUnit parameterDefinitionUnit : definitions) {
				if (parameterDefinitionUnit.getName().equals("mg/100g")) {
					_MealLine.setUnit(parameterDefinitionUnit);
					return;

				}
			}
	}

	public MealBo getMealBo() {
		if (_MealLine.getMeal() != null)
			return new MealBo(_MealLine.getMeal());
		return null;
	}

	public void setMealBo(MealBo meal) {
		_MealLine.setMeal(meal.getMeal());
	}

}
