/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: BusinesLogicController.java
 */
package at.fhv.teamc.easydiet.controller;

import at.easydiet.model.Patient;

/**
 * Controller for business logic
 * @author Michael
 */
public class BusinessLogicController {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicController.class);

    /**
     * Search patient in database
     * @param search string
     */
    public void searchPatient(String search) {
        LOGGER.trace("Sucheingabe: " + search);
    }

        /**
     * Search patient in database
     * @param search string
     */
    public void chooseActivePatient(Patient p) {
        LOGGER.trace("Patient: " + p.getInsuranceNumber());
    }
}
