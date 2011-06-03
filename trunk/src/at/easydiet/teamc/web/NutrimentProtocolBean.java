package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sun.xml.internal.ws.api.PropertySet.Property;

import at.easydiet.model.NutritionProtocol;
import at.easydiet.teamc.controller.usecase.SearchRecipeController;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.RecipeData;

@ManagedBean
@SessionScoped
public class NutrimentProtocolBean extends NutritionProtocolBo{

	private Date _startDate;
	private Date _endDate;
	
	private String _chosenRecipe;
	
	private TimeSpanBo _timeSpan;
	
	/**
	 * Folgende zwei Felder dienen nur zum Test
	 */
	private TimeSpanBo _currTimeSpan;	
	private List<Integer> _timeSpanInDays;
	

	public String getChosenRecipe() {
		return _chosenRecipe;
	}

	public void setChosenRecipe(String chosenRecipe) {
		_chosenRecipe=chosenRecipe;
	}
	
	
    /**
     * Gets the startDate.
     * @return the startDate
     */  
    public Date getStartDate()
    {    	
        return _startDate;
    }
    
    /**
     * Sets the startDate.
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate)
    {
        _startDate = startDate;
    }
    /**
     * Gets the endDate.
     * @return the endDate
     */
    public Date getEndDate()
    {
        return _endDate;
    }
    /**
     * Sets the endDate.
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate)
    {
        _endDate = endDate;
    }
    

    public List<TimeSpanBo> getTimespans()
    {
        List<TimeSpanBo> bos = new ArrayList<TimeSpanBo>();
       
        if(super.getTimeSpans()!=null)
        for (TimeSpanBo timeSpanBo : super.getTimeSpans())
        {
            bos.add(timeSpanBo);
        }
        
        Collections.sort(bos);
        
        
        return bos;
    }
    
    /**
     * Completes the search for the autoComplete method
     * @param query
     * @return
     */
    public List<String> completeRecipeSearch(String query){
		ArrayList<String> s=new ArrayList<String>();
		for(RecipeData rd:searchRecipes(query)){
			s.add(rd.getName());
		}
		return s;
		
	}

	private List<RecipeData> searchRecipes(String query) {
		SearchRecipeController src=SearchRecipeController.getInstance();
		ArrayList<RecipeData> recipes=new ArrayList<RecipeData>();
		for(RecipeBo b: src.searchRecipe(null, query)){
			recipes.add((RecipeData)b);
		}
		return recipes;
	}
	/**
	 * Testmethode für Anzeige der Timespan
	 */
	public void generateTimespan(){
		System.out.println("TEST");
		int duration=(int)(_endDate.getTime()-_startDate.getTime())*1000*60*60*24;
		//calc duration
		ArrayList<Integer> days=new ArrayList<Integer>();
		for(int i=0;i<duration;i++){
			days.add(i);
		}
		_timeSpanInDays=days;
		System.out.println(days.size());
		_currTimeSpan=new TimeSpanBo(_startDate, duration, null);
		
	}
	
	public List<Integer> getTimespanDays(){
		return _timeSpanInDays;
	}
	public void setTimespanDays(List<Integer> days){
		_timeSpanInDays=days;
	}
	
}
