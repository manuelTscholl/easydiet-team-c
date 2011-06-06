package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealLineBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.MealCodeData;

@ManagedBean
@SessionScoped
public class MealLineBean {

	private MealLineBo mealline=new MealLineBo();
	private List<MealLineBo> meallines = new ArrayList<MealLineBo>();	
	
	
	public String reinit() {
		mealline=new MealLineBo();
		
		return null;
	}

	public MealLineBo getMealLine() {
		return mealline;
	}
	public List<MealLineBo> getMealLines() {
		return meallines;
	}
	private MealBo getMealBo() {
		if (mealline.getMealBo() == null) {
			mealline.setMealBo(new MealBo("", "", new TimeSpanBo(null)));
		}

		return mealline.getMealBo();
	}
	public String getCode() {
		return getMealBo().getCode();
	}

	public void setCode(String code) {
		getMealBo().setCode(code);
	}
	public String getName() {
		return getMealBo().getName();
	}

	public void setName(String name) {
		getMealBo().setName(name);
	}

	public float getQuantity() {
		return mealline.getQuantity();
	}

	public void setQuantity(float quantity) {
		mealline.setQuantity(quantity);
	}
	
	public List<MealCodeData> getAllMeals(){
		BusinessLogicDelegationController s=BusinessLogicDelegationController.getInstance();
		List<MealCodeData> mealCodes=new ArrayList<MealCodeData>();
		for(MealCodeData md:s.getAllMealCodes()){
			mealCodes.add(md);
		}
		return mealCodes;
	}

	
}	
					