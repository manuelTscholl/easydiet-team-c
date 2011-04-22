/**
 * This File is part of Easy Diet
 * created on: 18.04.2011
 * created by: Friedrich Bösch
 * file: SearchRecipeController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.easydiet.dao.RecipeDAO;
import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.RecipeData;


public class SearchRecipeController {

    //class Variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicDelegationController.class);
    private static volatile  SearchRecipeController _searchRecipeController = null;
    //instance variables
    private DatabaseController _dbController;
    private Set<PatientData> _lastSearchResult;


    /**
     * Singelton
     * @return Will return the existing Instance or if no exists a new Instance of {@link SearchRecipeController}
     */
    
     public static SearchRecipeController getInstance() {
            if(_searchRecipeController==null){
                    _searchRecipeController=new SearchRecipeController();
             }
             return _searchRecipeController;
        }


	public Set<RecipeBo> getRecipeMainCategories() {
		List<RecipeBo> mainCategories =_dbController.getRecipeMainCategories();
		Set<RecipeBo> setOfMainCategories=new HashSet<RecipeBo>(mainCategories);
		return setOfMainCategories;
	}
	
	public Set<RecipeBo> searchRecipe(String mainCategory,String search){
		List<RecipeBo> recipes =_dbController.searchRecipe(mainCategory,search);
		Set<RecipeBo> setOfSearchRecipe=new HashSet<RecipeBo>(recipes);
		return setOfSearchRecipe;
	}
}
