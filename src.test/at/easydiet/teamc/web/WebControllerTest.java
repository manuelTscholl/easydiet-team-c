/**
 * This File is part of EasyDiet
 * Created on: 27.05.2011
 * Created by: Michael
 * File: WebControllerTest.java
 */
package at.easydiet.teamc.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.controller.LoginController;
import at.easydiet.teamc.controller.usecase.CreateNutritionProtocolController;
import at.easydiet.teamc.controller.usecase.SearchRecipeController;
import at.easydiet.teamc.exception.LoginFailedException;
import at.easydiet.teamc.exception.NoDietPlanException;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * 
 * @author Michael
 */
public class WebControllerTest extends TestCase {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(WebControllerTest.class);

	// instance variables
	private WebController _webCtrl;
	private CreateNutritionProtocolController _protocolCtrl;
	private PatientData _activePatient;
	private String _recipeSearchQuery;
	private RecipeBo _recipe;

	protected void setUp() throws Exception {
		_webCtrl = new WebController();
		_protocolCtrl = new CreateNutritionProtocolController();
		_activePatient = LoginController.getInstance().loginPatient(
				"tgeiger", "tgeiger");
		_recipeSearchQuery = "Vollkornbrot mit Soja";
		_recipe = SearchRecipeController.getInstance().searchRecipe(null, "Vollkornbrot mit Soja").get(0);
	}

	/**
	 * Test method for
	 * {@link at.easydiet.teamc.web.WebController#loginPatient()}.
	 * @throws LoginFailedException
	 */
	@Test
	public void testLoginPatient() {
		String result = "Thomas Geiger";

		// correct login
		
		try {
			_activePatient = LoginController.getInstance().loginPatient(
					"tgeiger", "tgeiger");
			assertEquals(
					_activePatient.getForename() + " "
							+ _activePatient.getLastname(), result);
		} catch (LoginFailedException e) {
			assertTrue(false);
		}

		// incorrect login
		PatientData fail = null;
		try {
			fail = LoginController.getInstance()
					.loginPatient("tgeiger", "fail");
			assertTrue(true);
		} catch (LoginFailedException e) {
			assertNull(fail);
		}
	}

	/**
	 * Test method for
	 * {@link at.easydiet.teamc.web.WebController#createNewProtocol()}.
	 */
	@Test
	public void testCreateNewProtocol() {
		assertNotNull(_protocolCtrl);
		assertNotNull(_protocolCtrl.getActualProtocol());
		assertNotNull(_protocolCtrl.getMealCodes());
		assertNotNull(_protocolCtrl.getPlanTypes());
		assertNotNull(_protocolCtrl.getRecipes());
//		RecipeBo recipe=SearchRecipeController.getInstance().searchRecipe(null, "Vollkornbrot mit Soja").get(0);
//		Map<RecipeData, Float> recipes=new HashMap<RecipeData, Float>();
//		recipes.put((RecipeData) recipe, 150F);
//		_protocolCtrl.addRecipesToProtocoll(recipes);
		_protocolCtrl.getActualProtocol().addTimeSpan(new TimeSpanBo(new Date(), 2, _protocolCtrl.getActualProtocol()));
		assertEquals(1, _protocolCtrl.getActualProtocol().getTimeSpans().size());
		_protocolCtrl.getActualProtocol().addMealCode(DatabaseController.getInstance().getAllMealCodes().get(0), 1);
		assertEquals(1, _protocolCtrl.getActualProtocol().getAllMeals().size());
	}
	
	@Test
	public void testGetAllDietryPlans(){
		try {
			assertNotNull(_protocolCtrl.getAllDietryPlans(_activePatient));
		} catch (NoDietPlanException e) {
			assertTrue(true);
		} 
	}
	
	@Test
	public void testCompleteRecipeSearch(){
		assertEquals(_recipeSearchQuery, _webCtrl.completeRecipeSearch(_recipeSearchQuery).get(0));
		assertNotNull(_webCtrl.completeRecipeSearch(null));
	}
	
	@Test
	public void testSearchRecipe(){
		assertEquals(_recipe, _webCtrl.searchRecipes(_recipeSearchQuery).get(0));
		assertNotNull(_webCtrl.searchRecipes(null));
	}
}
