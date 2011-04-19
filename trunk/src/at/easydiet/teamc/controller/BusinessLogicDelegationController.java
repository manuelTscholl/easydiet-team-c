/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: BusinesLogicController.java
 */
package at.easydiet.teamc.controller;

import at.easydiet.teamc.model.data.DietParameterTemplateData;
import at.easydiet.teamc.model.data.PatientData;

import java.util.HashSet;
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
public class BusinessLogicDelegationController
{

    // class variables
    public static final org.apache.log4j.Logger               LOGGER = org.apache.log4j.Logger
                                                                             .getLogger(BusinessLogicDelegationController.class);
    private static volatile BusinessLogicDelegationController _businessLogicDelegationController;
    // instance variables
    private PatientBo                                         _activePatient;
    private SearchPatientController                           _searchPatientController;
    private SearchParameterController                         _searchParameterController;
    private Thread                                            _patientSearchThread;

    /**
     * Initializes a new instance of the
     * {@link BusinessLogicDelegationController} class.
     * 
     * @param sender
     */
    private BusinessLogicDelegationController()
    {
        _searchPatientController = SearchPatientController.getInstance();
        _searchParameterController = SearchParameterController.getInstance();
        _searchPatientController = SearchPatientController.getInstance();
    }

    /**
     * Gets the lastSearchResult.
     * 
     * @return the lastSearchResult
     */
    public synchronized Set<PatientData> getLastPatientSearchResult()
    {
        return _searchPatientController.getLastSearchResult();
    }

    /**
     * Singelton: creates or returns the last created instance
     * 
     * @return
     */
    public static BusinessLogicDelegationController getInstance()
    {
        if (_businessLogicDelegationController == null)
        {
            _businessLogicDelegationController = new BusinessLogicDelegationController();
        }

        return _businessLogicDelegationController;
    }

    /**
     * Searches patients in database, will start an asyncron searching Thread
     * @param search
     * @return The last list of Patients which where found in database, may not the patients from the actual search for actual search use the events
     */
    public Set<PatientData> searchPatient(String search)
    {

        // start search only if at least 2 characters are available
        if (search.length() >= 2)
        {
            _searchPatientController.addNewPatientSearchString(search);

            //starting the search loop if not already started
            if (_patientSearchThread == null)
            {
                _patientSearchThread.setName("PatientSearchThread");
                _patientSearchThread = new Thread(_searchPatientController);
                _patientSearchThread.start();
            }
            
            // let the gui layer know that something updated

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
    public void setActivePatient(PatientData p)
    {
        _activePatient = (PatientBo) p;
    }

    /**
     * Gets the active Patient which was set from the GUI Controller before *
     * 
     * @param p
     */
    public PatientBo getActivePatient()
    {
        return _activePatient;
    }

    public void addNewPatientSearchHandler(IEventHandler<EventArgs> handler)
    {
        _searchPatientController.addHandler(handler);
    }
<<<<<<< .mine
    
    public Set<DietParameterTemplateData> getParameters(){
    	  	
    	/*Set<PatientData> patientDatas = new HashSet<PatientData>();
        Set<PatientBo> patients = _dbController.getPatients(name1, name2, svn, date);

        // check if database set is not empty
        if (patients != null) {

            // convert business patient data to gui patient data
            for (PatientBo pbo : patients) {
                patientDatas.add(pbo);
            }
        }*/
    	
    	Set<DietParameterTemplateData> parameterData=new HashSet<DietParameterTemplateData>();    	
    	Set<DietParameterTemplateBo> parameters=_searchParameterController.getParameters();  
    	
    	if(parameters!=null){
    		//convert business dietparameter to dietparameterdata
    		for(DietParameterTemplateBo dbo:parameters){
    			parameterData.add(dbo);
    		}
    	}
    	    	
    	
    	return null;
    	
=======

    public Set<DietParameterTemplateData> getParameters()
    {
        Set<DietParameterTemplateBo> parameters = _searchParameterController
                .getParameters();
        // cast DietParameterTemplateBo's into DietParameterTemplateData

        return null;

>>>>>>> .r217
    }
}
