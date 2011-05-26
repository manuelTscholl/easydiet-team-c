/**
 * This File is part of EasyDiet
 * Created on: 26.05.2011
 * Created by: Michael
 * File: CreateNutritionProtocolController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.List;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.MealCodeBo;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.RecipeBo;

/**
 * Controller for processing nutrition protocols Every user gets his own
 * controller
 * @author Michael
 */
public class CreateNutritionProtocolController {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(CreateNutritionProtocolController.class);
	// instance variables
	private NutritionProtocolBo _actualProtocol;
	private List<PlanTypeBo> _planTypes;
	private List<MealCodeBo> _mealCodes;
	private List<RecipeBo> _recipes;

	public CreateNutritionProtocolController() {

		// init necessary protocol data
		_planTypes = getAllPlanTypes();
	}

	/**
	 * Get all plan types
	 * @return list of plan types
	 */
	private List<PlanTypeBo> getAllPlanTypes() {
		return BusinessLogicDelegationController.getInstance()
				.getAllPlanTypes();
	}
}
