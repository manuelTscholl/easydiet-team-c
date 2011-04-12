/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: BusinesLogicController.java
 */
package at.fhv.teamc.easydiet.controller;

import at.fhv.easydiet.controller.usecase.SearchPatientController;
import at.fhv.teamc.easydiet.model.PatientBo;
import java.util.Set;

/**
 * Controller for business logic
 * 
 * @author Michael
 */
public class BusinessLogicController {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicController.class);
    private PatientBo _activePatient;
    SearchPatientController _searchPatientController;

    public BusinessLogicController(){
        _searchPatientController = new SearchPatientController();
    }

    /**
     * Not implemented
     * 
     * @param search
     */
    public Set<PatientData> searchPatient(String search) {

        // start search only if at least 2 characters are available
        if(search.length() >= 2){
            return _searchPatientController.getPatients(search);
        }

        return null;
    }

    /**
     * Sets the active Patient
     * @param p the patient which should be set to active
     */
    public void setActivePatient(PatientData p) {
        _activePatient = (PatientBo)p;
    }

    /**
     * Gets the active Patient which was set from the GUI Controller before     * 
     * @param p
     */
    public PatientBo getActivePatient() {
        return _activePatient;
    }
}
