/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: GUIController.java
 */
package at.easydiet.teamc.controller;

import java.util.Set;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.util.EventArgs;
import at.easydiet.teamc.util.IEventHandler;
import at.easydiet.teamc.view.ContentTabPane;
import at.easydiet.teamc.view.EasyDietMenuBar;
import at.easydiet.teamc.view.EasyDietWindow;
import at.easydiet.teamc.view.KeyAdapter;
import at.easydiet.teamc.view.NavigationTabPane;
import at.easydiet.teamc.view.PatientListener;



/**
 * Controls and knows all GUI elements
 * @author Michael
 */
public class GUIController implements PatientListener {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(GUIController.class);
    // instance variables
    private EasyDietWindow _easyDietWindow;
    private Map<String, Object> _guiNamespaces;
    private NavigationTabPane _navTab;
    private EasyDietMenuBar _menuBar;
    private ContentTabPane _contentTab;
    private BusinessLogicDelegationController _businessLogicDelegationController;

    /**
     * Constructor
     * @param eadw Window with all gui ressources
     */
    public GUIController(EasyDietWindow eadw) {

        _easyDietWindow = eadw;
        _guiNamespaces = _easyDietWindow.getRessources();

        _businessLogicDelegationController = BusinessLogicDelegationController.getInstance();

        // get gui containers
        _navTab = (NavigationTabPane) _guiNamespaces.get("navTab");
        _menuBar = (EasyDietMenuBar) _guiNamespaces.get("menuBar");
        _contentTab = (ContentTabPane) _guiNamespaces.get("contentTab");

        // register for patient events
        _navTab.registerListener(this);

        // add key event for search textinput field
        _navTab.getSearchTextInput().getComponentKeyListeners().add(new KeyAdapter() {

            @Override
            public boolean keyTyped(Component component, char character) {

                // check if activity indicator is already active
                if (!_navTab.getActivityIndicator().isActive()) {
                    _navTab.getActivityIndicator().setActive(true);
                }                
        
                // asyncron search that the gui is not blocked while searching in database
                String searchText = ((TextInput) component).getText();

                _businessLogicDelegationController.searchPatient(searchText);


                // check if text field is empty, if empty stop activity indicator
                if (_navTab.getSearchTextInput().getText().equals("")) {
                    _navTab.getActivityIndicator().setActive(false);
                }


                return true;
            }
        });
        
        //updates the gui when event is fired
        _businessLogicDelegationController.addNewPatientSearchHandler(new IEventHandler<EventArgs>()
        {            
            @Override
            public void fired(Object sender, EventArgs e)
            {
                updateSearchResult(_businessLogicDelegationController.getLastPatientSearchResult());
            }
        });
        

    }

    /**
     * Update search results in navigation search tab with received set
     * @param patients
     */
    public void updateSearchResult(Set<PatientData> patients) {
        _navTab.updateSearchResult(patients);
    }

    /**
     * Set a new active patient
     * @param p
     */
    @Override
    public void chooseActivePatient(PatientData p) {
        _contentTab.setSelectedTabByName("overviewTab");
        updatePatientData(p);
        _businessLogicDelegationController.setActivePatient(p);
    }

    /**
     * Update patient data in content tabs
     * @param p
     */
    public void updatePatientData(PatientData p) {
        _contentTab.updatePatientData(p);

        // update patient name in window bar
        _easyDietWindow.setTitle("EasyDiet - Patient: " + p.getForename() + " " + p.getLastname().toUpperCase());
    }
}
