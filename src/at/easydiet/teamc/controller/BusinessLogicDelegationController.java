/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: BusinesLogicController.java
 */
package at.easydiet.teamc.controller;

import at.easydiet.teamc.model.data.DietParameterTemplateData;
import at.easydiet.teamc.model.data.PatientData;
import java.util.Set;


import at.easydiet.model.DietParameterTemplate;
import at.easydiet.teamc.controller.usecase.SearchParameterController;
import at.easydiet.teamc.controller.usecase.SearchPatientController;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.util.EventArgs;
import at.easydiet.teamc.util.IEventHandler;
import at.easydiet.teamc.model.*;


/**
 * Controller for business logic
 * 
 * @author Michael
 */
public class BusinessLogicDelegationController {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicDelegationController.class);
    private static volatile BusinessLogicDelegationController _businessLogicDelegationController;
    // instance variables
    private PatientBo _activePatient;
    private SearchPatientController _searchPatientController;
    private SearchParameterController _searchParameterController;
    

    /** 
     * Initializes a new instance of the {@link BusinessLogicDelegationController} class. 
     * @param sender
     */
    private BusinessLogicDelegationController() {
        _searchPatientController = SearchPatientController.getInstance();
        _searchParameterController=SearchParameterController.getInstance();
    }

    /**
     * Gets the lastSearchResult.
     * @return the lastSearchResult
     */
    public synchronized Set<PatientData> getLastPatientSearchResult() {
        return _searchPatientController.getLastSearchResult();
    }

    /**
     * Singelton: creates or returns the last created instance
     * @return
     */
    public static BusinessLogicDelegationController getInstance() {
        if (_businessLogicDelegationController == null) {
            _businessLogicDelegationController = new BusinessLogicDelegationController();
        }

        return _businessLogicDelegationController;
    }

    /**
     * Search patient
     * 
     * @param search
     */
    public Set<PatientData> searchPatient(String search) {
        _searchPatientController = SearchPatientController.getInstance();
        // start search only if at least 2 characters are available
        if (search.length() >= 2) {
            _searchPatientController.getPatients(search);
            //let the gui layer know that something updated
            _searchPatientController.fireEvent(EventArgs.Empty);

            return getLastPatientSearchResult();
        }

        return null;
    }

    /**
     * Sets the active Patient
     * 
     * @param p
     *            the patient which should be set to active
     */
    public void setActivePatient(PatientData p) {
        _activePatient = (PatientBo) p;
    }

    /**
     * Gets the active Patient which was set from the GUI Controller before *
     * 
     * @param p
     */
    public PatientBo getActivePatient() {
        return _activePatient;
    }

    public void addNewPatientSearchHandler(IEventHandler<EventArgs> handler) {
        _searchPatientController.addHandler(handler);
    }
    
    public Set<DietParameterTemplateData> getParameters(){
    	Set<DietParameterTemplateBo> parameters=_searchParameterController.getParameters();    	
    	//cast DietParameterTemplateBo's into DietParameterTemplateData
    	
    	
    	
    	return null;
    	
    }
}