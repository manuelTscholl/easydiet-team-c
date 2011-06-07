package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import at.easydiet.teamc.controller.usecase.SearchRecipeController;
import at.easydiet.teamc.model.MealLineBo;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.RecipeData;

@ManagedBean
@SessionScoped
public class NutrimentProtocolBean extends NutritionProtocolBo {

	private Date _startDate;
	private Date _endDate;

	private String _chosenRecipe;
	private TimeSpanBo _currentTimespan;

	private TimeSpanBo _timeSpan;
	private List<MealLineBo> _mealLines=new ArrayList<MealLineBo>();

	/**
	 * Gets the mealLines.
	 * 
	 * @return the mealLines
	 */
	public List<MealLineBo> getMealLines() {

		return _mealLines;
	}

	/**
	 * Sets the mealLines.
	 * 
	 * @param mealLines
	 *            the mealLines to set
	 */
	public void setMealLines(List<MealLineBo> mealLines) {
		_mealLines = mealLines;
	}

	public String getChosenRecipe() {
		return _chosenRecipe;
	}

	public void setChosenRecipe(String chosenRecipe) {
		_chosenRecipe = chosenRecipe;
	}

	/**
	 * Gets the startDate.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return _startDate;
	}

	/**
	 * Sets the startDate.
	 * 
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	/**
	 * Gets the endDate.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return _endDate;
	}

	/**
	 * Sets the endDate.
	 * 
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public List<TimeSpanBo> getTimespans() {
		List<TimeSpanBo> bos = new ArrayList<TimeSpanBo>();

		if (super.getTimeSpans() != null)
			for (TimeSpanBo timeSpanBo : super.getTimeSpans()) {
				bos.add(timeSpanBo);
			}

		Collections.sort(bos);

		return bos;
	}

	/**
	 * Completes the search for the autoComplete method
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeRecipeSearch(String query) {
		ArrayList<String> s = new ArrayList<String>();
		for (RecipeData rd : searchRecipes(query)) {
			s.add(rd.getName());
		}
		return s;

	}

	private List<RecipeData> searchRecipes(String query) {
		SearchRecipeController src = SearchRecipeController.getInstance();
		ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
		for (RecipeBo b : src.searchRecipe(null, query)) {
			recipes.add(b);
		}
		return recipes;
	}

	/**
	 * Gets the currentTimespan.
	 * 
	 * @return the currentTimespan
	 */
	public TimeSpanBo getCurrentTimespan() {
		return _currentTimespan;
	}

	/**
     * 
     */
	public void addRecipe(ActionEvent e) {

		FacesContext context = FacesContext.getCurrentInstance();
		MealLineBean mealLineBean = context.getApplication()
				.evaluateExpressionGet(context, "#{mealLineBean}",
						MealLineBean.class);
		context = FacesContext.getCurrentInstance();
		WebController wc = context.getApplication()
				.evaluateExpressionGet(context, "#{webController}",
						WebController.class);
		
		
		SearchRecipeController src = SearchRecipeController.getInstance();
		
		List<RecipeBo> recipes = src.searchRecipe("", wc.getChosenRecipe());
		if (recipes.size() > 0) {
			mealLineBean.getMealLine().setRecipe(recipes.get(0));
			mealLineBean.setName(recipes.get(0).getName());
			context = FacesContext.getCurrentInstance();

		}

	}

	/**
	 * @param myAttribute
	 */
	public void setCurrentTimespan(TimeSpanBo myAttribute) {
		_currentTimespan = myAttribute;
	}

	/**
	 * sets the current time span which have to be used for adding meals
	 * 
	 * @param e
	 */
	public void setCurrentTimespan(ActionEvent e) {
		TimeSpanBo myAttribute = (TimeSpanBo) e.getComponent().getAttributes()
				.get("timespan");
		setCurrentTimespan(myAttribute);
	}

}
