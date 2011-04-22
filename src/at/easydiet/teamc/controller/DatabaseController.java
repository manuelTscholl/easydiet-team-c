/**
 * This file is part of Easy Diet.
 *   created on: 11.04.2011
 *   created by: Manuel
 *   file: DatabaseController.java
 */
package at.easydiet.teamc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.MealDAO;
import at.easydiet.dao.PatientDAO;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.model.Meal;
import at.easydiet.model.Patient;
import at.easydiet.model.Recipe;
import at.easydiet.teamc.dao.MealCodeDao;
import at.easydiet.teamc.model.DietParameterBo;
import at.easydiet.teamc.model.MealBo;
import at.easydiet.teamc.model.MealCodeBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.RecipeBo;

/** 
 * Will get all data from database via the different class dow's
 * @author Manuel
 *
 */
public class DatabaseController {

    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DatabaseController.class);
    private static DatabaseController _databaseController;

    /** 
     * Initializes a new instance of the {@link DatabaseController} class. 
     */
    private DatabaseController() {
    }

    /**
     * Searches in the Database for the special Patient 
     * @param name1
     * @param name
     * @param svn the insurance Number of the Patient
     * @param birthday
     * @return all Patients with the wanted data as a HashSet
     */
    public Set<PatientBo> getPatients(String name1, String name2, String svn, Date birthday) {

        PatientDAO patientDao = DAOFactory.getInstance().getPatientDAO();
        List<Patient> patients = new ArrayList<Patient>();
        Set<PatientBo> patientsBo = new HashSet<PatientBo>();

        patients = patientDao.findBySpecialProperties(name1, name2, svn, birthday);

        // create Bo list
        for(Patient p:patients){
            patientsBo.add(new PatientBo(p));
        }

        return patientsBo;
    }


    /**
     * Singelton crates a new instance of this class or returns the existing
     * @return
     */
    public static DatabaseController getInstance()
    {
        if(_databaseController==null)
            _databaseController = new DatabaseController();
        
        return _databaseController;
    }

	public Set<DietParameterBo> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Looks in database for all saved mealCodes, uses the MealDao for loading
	 * @return a Set of meals loaded from database via hibernate
	 */
    public List<MealCodeBo> getAllMealCodes()
    {
        MealCodeDao mealCodeDao = new MealCodeDao();
        
        List<MealCodeBo> mealCodes = mealCodeDao.findAll();
        
        return mealCodes;
    }
    
    /**
     * Looks in database for all recipe categories bls looks like (B00000)
     * @return a list of categories
     */
    public List<RecipeBo> getRecipeMainCategories()
    {
        RecipeDAO recipeDao = new RecipeDAO();
        List<RecipeBo> recipesBo = new ArrayList<RecipeBo>();
        List<Recipe> recipes = recipeDao.getRecipeMainCategories();
        
        if(recipes!=null)
        for (Recipe item : recipes)
        {
            recipesBo.add(new RecipeBo(item));
        }
        
        return recipesBo;
    }
   
    /**
     * {@link RecipeDAO#searchRecipe(String categorie,String name)}
     */
    public List<RecipeBo> searchRecipe(String categorie, String name)
    {
        RecipeDAO recipeDao = DAOFactory.getInstance().getRecipeDAO();
        List<RecipeBo> recipesBo = new ArrayList<RecipeBo>();
        List<Recipe> recipes = recipeDao.searchRecipe(categorie, name);
        
        if(recipes!=null)
        for (Recipe recipe : recipes)
        {
            recipesBo.add(new RecipeBo(recipe));
        }
        return recipesBo;
    }
    
    
}
