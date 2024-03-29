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
import at.easydiet.dao.DietParameterDAO;
import at.easydiet.dao.PatientDAO;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.model.CheckOperator;
import at.easydiet.model.DietParameter;
import at.easydiet.model.ParameterDefinition;
import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.model.Patient;
import at.easydiet.model.PlanType;
import at.easydiet.model.Recipe;
import at.easydiet.teamc.dao.MealCodeDao;
import at.easydiet.teamc.model.CheckOperatorBo;
import at.easydiet.teamc.model.DietParameterBo;
import at.easydiet.teamc.model.MealCodeBo;
import at.easydiet.teamc.model.ParameterDefinitionBo;
import at.easydiet.teamc.model.ParameterDefinitionUnitBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.RecipeBo;

/**
 * Will get all data from database via the different class dow's
 * 
 * @author Manuel
 * 
 */
public class DatabaseController {

	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(DatabaseController.class);
	private static DatabaseController _databaseController;

	/**
	 * Initializes a new instance of the {@link DatabaseController} class.
	 */
	private DatabaseController() {
	}

	/**
	 * Searches in the Database for the special Patient
	 * 
	 * @param name1
	 * @param name
	 * @param svn the insurance Number of the Patient
	 * @param birthday
	 * @return all Patients with the wanted data as a HashSet
	 */
	public Set<PatientBo> getPatients(String name1, String name2, String svn,
			Date birthday) {

		PatientDAO patientDao = DAOFactory.getInstance().getPatientDAO();
		List<Patient> patients = new ArrayList<Patient>();
		Set<PatientBo> patientsBo = new HashSet<PatientBo>();

		patients = patientDao.findBySpecialProperties(name1, name2, svn,
				birthday);

		// create Bo list
		for (Patient p : patients) {
			patientsBo.add(new PatientBo(p));
		}

		return patientsBo;
	}

	/**
	 * Singelton creates a new instance of this class or returns the existing
	 * 
	 * @return
	 */
	public static DatabaseController getInstance() {
		if (_databaseController == null)
			_databaseController = new DatabaseController();

		return _databaseController;
	}

	/**
	 * Finds all parameters in the database
	 * 
	 * @return
	 */
	public List<DietParameterBo> getAllParameters() {

		DietParameterDAO dietParameterDao = DAOFactory.getInstance()
				.getDietParameterDAO();
		List<DietParameterBo> parametersBo = new ArrayList<DietParameterBo>();
		List<DietParameter> parameters = dietParameterDao.findAll();

		// creating Bos
		if (parameters != null)
			for (DietParameter item : parameters) {
				parametersBo.add(new DietParameterBo(item));
			}

		return parametersBo;
	}

	/**
	 * Looks in database for all saved mealCodes, uses the MealDao for loading
	 * 
	 * @return a Set of meals loaded from database via hibernate
	 */
	public List<MealCodeBo> getAllMealCodes() {
		MealCodeDao mealCodeDao = new MealCodeDao();

		List<MealCodeBo> mealCodes = mealCodeDao.findAll();

		return mealCodes;
	}

	/**
	 * Gets all Bls categories out of the db
	 * 
	 * @return a list of categories
	 */
	public List<RecipeBo> getRecipeMainCategories() {
		List<Recipe> recipes = DAOFactory.getInstance().getRecipeDAO()
				.getCategories();
		List<RecipeBo> recipesBo = new ArrayList<RecipeBo>();

		if (recipes != null)
			for (Recipe item : recipes) {
				recipesBo.add(new RecipeBo(item));
			}
		return recipesBo;
	}

	/**
	 * {@link RecipeDAO#searchRecipe(String blsCategorie,String name)}
	 */
	public List<RecipeBo> searchRecipe(String blsCategorie, String name) {
		RecipeDAO recipeDao = DAOFactory.getInstance().getRecipeDAO();
		List<RecipeBo> recipesBo = new ArrayList<RecipeBo>();
		List<Recipe> recipes = recipeDao.searchRecipe(blsCategorie, name);

		// creating Bos
		if (recipes != null)
			for (Recipe recipe : recipes) {
				recipesBo.add(new RecipeBo(recipe));
			}
		return recipesBo;
	}

	/**
	 * Gets all Parameter definitions from the database (Alanyn, kj....)
	 * @return
	 */
	public List<ParameterDefinitionBo> getAllParameterDefinitions() {
		List<ParameterDefinition> datas = DAOFactory.getInstance()
				.getParameterDefinitionDAO().findAll();
		List<ParameterDefinitionBo> dataBos = new ArrayList<ParameterDefinitionBo>();
		if (datas != null)
			for (ParameterDefinition parameterDefinition : datas) {
				dataBos.add(new ParameterDefinitionBo(parameterDefinition));
			}

		return dataBos;
	}

	/**
	 * 
	 * @return
	 */
	public List<ParameterDefinitionUnitBo> getAllParameterDefinitionUnits() {
		List<ParameterDefinitionUnit> datas = DAOFactory.getInstance()
				.getParameterDefinitionUnitDAO().findAll();
		List<ParameterDefinitionUnitBo> dataBos = new ArrayList<ParameterDefinitionUnitBo>();
		if (datas != null)
			for (ParameterDefinitionUnit parameterDefinition : datas) {
				dataBos.add(new ParameterDefinitionUnitBo(parameterDefinition));
			}

		return dataBos;
	}

	/**
	 * Get All Checkoperators
	 * @return
	 */
	public List<CheckOperatorBo> getAllCheckoperators() {
		List<CheckOperator> datas = DAOFactory.getInstance()
				.getCheckOperatorDAO().findAll();
		List<CheckOperatorBo> dataBos = new ArrayList<CheckOperatorBo>();
		if (datas != null)
			for (CheckOperator item : datas) {
				dataBos.add(new CheckOperatorBo(item));
			}

		return dataBos;
	}

	/**
	 * Get All Paramaeter Definition unit
	 * @return
	 */
	public List<ParameterDefinitionUnitBo> getAllParameterDefinitionUnit() {
		List<ParameterDefinitionUnit> datas = DAOFactory.getInstance()
				.getParameterDefinitionUnitDAO().findAll();
		List<ParameterDefinitionUnitBo> dataBos = new ArrayList<ParameterDefinitionUnitBo>();
		if (datas != null)
			for (ParameterDefinitionUnit item : datas) {
				dataBos.add(new ParameterDefinitionUnitBo(item));
			}

		return dataBos;
	}

	/**
	 * Get a patient by his unique username
	 * @param username to search for
	 * @return patient with this username
	 */
	public PatientBo getPatientByUsername(String username) {
		return new PatientBo(DAOFactory.getInstance().getPatientDAO()
				.findByUsername(username));
	}

	/**
	 * Get all plan types
	 * @return plan types
	 */
	public List<PlanTypeBo> getAllPlanTypes() {
		List<PlanType> planTypes = DAOFactory.getInstance().getPlanTypeDAO()
				.findAll();
		List<PlanTypeBo> plans = new ArrayList<PlanTypeBo>();

		for (PlanType p : planTypes) {
			plans.add(new PlanTypeBo(p));
		}
		return plans;
	}
}
