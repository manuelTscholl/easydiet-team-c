/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: BusinesLogicController.java
 */
package at.easydiet.teamc.controller;

import java.util.Set;

import at.easydiet.teamc.controller.usecase.SearchPatientController;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.util.EventArgs;
import at.easydiet.teamc.util.IEventHandler;



/**
 * Controller for business logic
 * 
 * @author Michael
 */
public class BusinessLogicDelegationController
{

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(BusinessLogicDelegationController.class);
    private PatientBo                           _activePatient;
    private SearchPatientController                     _searchPatientController;
    private static BusinessLogicDelegationController      _businessLogicDelegationController;

    /** 
     * Initializes a new instance of the {@link BusinessLogicDelegationController} class. 
     * @param sender
     */
    private BusinessLogicDelegationController()
    {
        _searchPatientController=SearchPatientController.getInstance();
    }

    /**
     * Gets the lastSearchResult.
     * @return the lastSearchResult
     */
    public synchronized Set<PatientData> getLastPatientSearchResult()
    {
        return _searchPatientController.getLastSearchResult();
    }


    /**
     * Singelton: creates or returns the last created instance
     * @return
     */
    public static BusinessLogicDelegationController getInstance()
    {
        if (_businessLogicDelegationController == null)
            _businessLogicDelegationController = new BusinessLogicDelegationController();

        return _businessLogicDelegationController;
    }

    
    
    /**
     * Search patient
     * 
     * @param search
     */
    public Set<PatientData> searchPatient(String search)
    {
        _searchPatientController = SearchPatientController.getInstance();
        // start search only if at least 2 characters are available
        if (search.length() >= 2)
        {
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

}
