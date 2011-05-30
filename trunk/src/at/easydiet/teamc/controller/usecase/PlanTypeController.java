/**
 * This File is part of EasyDiet
 * Created on: 30.05.2011
 * Created by: Michael
 * File: PlanTypeController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.data.PlanTypeData;

/**
 * 
 * @author Michael
 */
public class PlanTypeController {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(PlanTypeController.class);
	private static PlanTypeController _controller;

	private PlanTypeController() {

	}

	/**
	 * Get an instance of this class
	 * @return
	 */
	public static PlanTypeController getInstance() {
		if (_controller == null) {
			_controller = new PlanTypeController();
		}
		return _controller;
	}

	/**
	 * Get all plan types from the database
	 */
	public List<PlanTypeData> getAllPlanTypes() {
		List<PlanTypeBo> bo = DatabaseController.getInstance()
				.getAllPlanTypes();
		List<PlanTypeData> data = new ArrayList<PlanTypeData>();

		for (PlanTypeBo p : bo) {
			data.add((PlanTypeData) p);
		}
		return data;
	}
}
