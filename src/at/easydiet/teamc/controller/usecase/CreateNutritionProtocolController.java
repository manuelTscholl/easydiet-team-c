/**
 * This File is part of EasyDiet
 * Created on: 26.05.2011
 * Created by: Michael
 * File: CreateNutritionProtocolController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.model.DietPlan;
import at.easydiet.model.PlanType;
import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.LoginController;
import at.easydiet.teamc.exception.NoDietPlanException;
import at.easydiet.teamc.model.DietPlanBo;
import at.easydiet.teamc.model.DietTreatmentBo;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.TreatmentStateBo;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.PlanTypeData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.web.NutrimentProtocolBean;

/**
 * Controller for processing nutrition protocols Every user gets his own
 * controller.
 * 
 * @author Michael
 */
public class CreateNutritionProtocolController {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(CreateNutritionProtocolController.class);
	// instance variables
	private NutritionProtocolBo _actualProtocol;
	private List<PlanTypeData> _planTypes;
	private Set<MealCodeData> _mealCodes;
	private List<RecipeData> _recipes;
	private PlanTypeData _selectedPlanType;
	private DietPlanBo _dietPlanBo;
	private static final String NUTRIMENTBEANNAME = "nutrimentProtocolBean";
	private boolean _dateAlreadySelected = false;

	/**
	 * Instantiates a new creates the nutrition protocol controller.
	 */
	public CreateNutritionProtocolController() {
		if(_actualProtocol==null){
		_actualProtocol = new NutritionProtocolBo();
		}

		// load necessary protocol data
		_planTypes = BusinessLogicDelegationController.getInstance()
				.getAllPlanTypes();
		_mealCodes = BusinessLogicDelegationController.getInstance()
				.getAllMealCodes();
		_recipes = BusinessLogicDelegationController.getInstance()
				.recipeSearch(null, null);
		_selectedPlanType = null;
	}

	// TODO wie bekommen wir rezepte und mengen von jsf vereint
	public void addRecipesToProtocoll(Map<RecipeData, Float> recipes) {

	}

	/**
	 * Gets the actual protocol.
	 * 
	 * @return the actual protocol
	 */
	public NutritionProtocolBo getActualProtocol() {
		return _actualProtocol;
	}

	/**
	 * 
	 * @param protocol
	 */
	public void setActualProtocol(NutritionProtocolBo protocol) {
		_actualProtocol = protocol;
	}

	/**
	 * Gets the plan types.
	 * 
	 * @return the plan types
	 */
	public List<PlanTypeData> getPlanTypes() {
		return _planTypes;
	}

	/**
	 * Gets the meal codes.
	 * 
	 * @return the meal codes
	 */
	public Set<MealCodeData> getMealCodes() {
		return _mealCodes;
	}

	/**
	 * Gets the recipes.
	 * 
	 * @return the recipes
	 */
	public List<RecipeData> getRecipes() {
		return _recipes;
	}

	/**
	 * Save the protocol
	 */
	public void save(PatientData patient) {

		// set the treatment
		DietTreatmentBo treatment = new DietTreatmentBo(
				_actualProtocol.getCreatedOn(), _actualProtocol.getDuration(),
				"Ernährungsprotokoll", new TreatmentStateBo("Laufend"),
				(PatientBo) patient);
		
		_actualProtocol.setDate(new Date());
		HibernateUtil.currentSession().beginTransaction();
		DAOFactory.getInstance().getDietTreatmentDAO().makePersistent(treatment.getDietTreatment());
		HibernateUtil.currentSession().getTransaction().commit();
		_actualProtocol.setDietTreatment(treatment);
		HibernateUtil.currentSession().beginTransaction();
		DAOFactory.getInstance().getNutritionProtocolDAO()
				.makePersistent(_actualProtocol.getModel());
		HibernateUtil.currentSession().getTransaction().commit();
		
	}

	/**
	 * Sets the plan types.
	 * 
	 * @param planTypes the new plan types
	 */
	public void setPlanType(PlanTypeData planTypes) {
		_selectedPlanType = planTypes;
		_actualProtocol.setPlanType((PlanTypeBo) _selectedPlanType);
	}

	/**
	 * Sets the meal codes.
	 * 
	 * @param mealCodes the new meal codes
	 */
	public void setMealCodes(Set<MealCodeData> mealCodes) {
		_mealCodes = mealCodes;
	}

	/**
	 * Sets the recipes.
	 * 
	 * @param recipes the new recipes
	 */
	public void setRecipes(List<RecipeData> recipes) {
		_recipes = recipes;
	}

	/**
	 * Get all dietry plans from patient
	 * 
	 * @param patient
	 * @return
	 * @throws NoDietPlanException is thrown if patient has no DietPlans
	 */
	public List<DietryPlanData> getAllDietryPlans(PatientData patient)
			throws NoDietPlanException {
		PatientBo p = (PatientBo) patient;
		List<DietryPlanData> plans = new ArrayList<DietryPlanData>();
		if (p.getAllDietPlans().size() <= 0) {
			throw new NoDietPlanException("This patient has no DietPlan.");
		}
		for (DietPlanBo d : p.getAllDietPlans()) {
			plans.add(d);
		}

		return plans;
	}

	/**
     * 
     */
	public void nutrimentProtocolDateSelect() {
		FacesContext context = FacesContext.getCurrentInstance();
		NutrimentProtocolBean bean = context.getApplication()
				.evaluateExpressionGet(context, "#{" + NUTRIMENTBEANNAME + "}",
						NutrimentProtocolBean.class);
//		_actualProtocol=bean;
		if (bean == null) {
			LOGGER.info("context is null");
			return;
		}
		if (bean.getStartDate() != null && bean.getEndDate() != null
				&& bean.getStartDate().before(bean.getEndDate())
				) {
			_dateAlreadySelected = true;
			Date current = bean.getStartDate();
			DietPlanBo planBo = null;
			if (bean.getDietPlan() == null) {
				DietPlan plan = new DietPlan("new", new Date(), new PlanType(
						"Ernährungsprotokoll"), null, LoginController
						.getInstance().getActualUser().getModel());
				planBo = new DietPlanBo(plan);
				bean.setDietPlan(plan);
			} else {
				_actualProtocol.setName("new");
				_actualProtocol.setCreatedOn(new Date());
				_actualProtocol.setPlanType(new PlanTypeBo("Ernährungsprotokoll"));
				_actualProtocol.setCreator(LoginController.getInstance().getActualUser());
			}
			// add a timespan for each day
			while (bean.getEndDate().getTime() >= current.getTime()) {
				Date toInsert = new Date(current.getTime());

				TimeSpanBo span = new TimeSpanBo(toInsert, 1, _actualProtocol);
				_actualProtocol.addTimeSpan(span);
				bean.addTimeSpan(span);
				current.setTime(current.getTime() + 1 * 24 * 60 * 60 * 1000);
				LOGGER.info("one day added");

			}
			for (TimeSpanBo item : bean.getTimeSpans()) {
				LOGGER.info(item.getStart());
			}
			LOGGER.info("fertig");

		}

	}

	/**
	 * @param selectedPlan
	 */
	public void setDietPlanBo(DietPlanBo selectedPlan) {

		_dietPlanBo = selectedPlan;
	}

	/**
	 * @param selectedPlan
	 */
	public DietPlanBo getDietPlanBo() {
		return _dietPlanBo;
	}

	/**
     * 
     */
	public void handlePlanSelected() {
		FacesContext context = FacesContext.getCurrentInstance();
		NutrimentProtocolBean bean = context.getApplication()
				.evaluateExpressionGet(context, "#{" + NUTRIMENTBEANNAME + "}",
						NutrimentProtocolBean.class);

		bean.getTimespans().clear();

		for (TimeSpanBo timespan : getDietPlanBo().getTimeSpans()) {
			bean.addTimeSpan(timespan);
		}

	}

}
