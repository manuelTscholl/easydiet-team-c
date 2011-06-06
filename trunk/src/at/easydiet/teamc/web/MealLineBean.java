/**
 * This file is part of EasyDiet.
 *   created on: 04.06.2011
 *   created by: Manuel
 *   file: MealLineBean.java
 */
package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealLineBo;
import at.easydiet.teamc.model.TimeSpanBo;

/**
 * @author Manuel
 * 
 */
@SessionScoped
@ManagedBean
public class MealLineBean {

	private MealLineBo _mealLine = new MealLineBo();

	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(MealLineBean.class);

	private List<MealLineBo> _mealLines = new ArrayList<MealLineBo>();

	private String reinit() {
		_mealLine = new MealLineBo();
		return null;
	}

	/**
	 * Gets the mealLine.
	 * @return the mealLine
	 */
	public MealLineBo getMealLine() {
		return _mealLine;
	}

	/**
	 * Sets the mealLine.
	 * @param mealLine the mealLine to set
	 */
	public void setMealLine(MealLineBo mealLine) {
		_mealLine = mealLine;
	}

	public String getName() {
		return getMealBo().getName();
	}

	public void setName(String name) {
		getMealBo().setName(name);
	}

	public float getQuantity() {
		return _mealLine.getQuantity();
	}

	public void setQuantity(float quantity) {
		_mealLine.setQuantity(quantity);
	}

	public String getCode() {
		return getMealBo().getCode();
	}

	public void setCode(String code) {
		getMealBo().setCode(code);
	}

	private MealBo getMealBo() {
		if (_mealLine.getMealBo() == null) {
			_mealLine.setMealBo(new MealBo("", "", new TimeSpanBo(null)));
		}

		return _mealLine.getMealBo();
	}

	public List<MealLineBo> getMealLines() {
		return _mealLines;

	}

	public void setMealLines(List<MealLineBo> lines) {
		_mealLines = lines;
	}

}
