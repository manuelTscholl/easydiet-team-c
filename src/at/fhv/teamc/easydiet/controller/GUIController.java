/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: GUIController.java
 */
package at.fhv.teamc.easydiet.controller;

import at.fhv.teamc.easydiet.model.PatientBo;
import at.fhv.teamc.easydiet.view.ContentTabPane;
import at.fhv.teamc.easydiet.view.EasyDietMenuBar;
import at.fhv.teamc.easydiet.view.EasyDietWindow;
import at.fhv.teamc.easydiet.view.KeyAdapter;
import at.fhv.teamc.easydiet.view.NavigationTabPane;
import at.fhv.teamc.easydiet.view.PatientListener;

import java.util.Set;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.TextInput;

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
    private BusinessLogicController _businessLogicController;

    /**
     * Constructor
     * @param eadw Window with all gui ressources
     */
    public GUIController(EasyDietWindow eadw) {

        _easyDietWindow = eadw;
        _guiNamespaces = _easyDietWindow.getRessources();

        _businessLogicController = BusinessLogicController.getInstance();

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

                // search
                Set<PatientData> patientData = _businessLogicController.searchPatient(((TextInput) component).getText());

                // check if text field is empty, if empty stop activity indicator
                if (_navTab.getSearchTextInput().getText().equals("")) {
                    _navTab.getActivityIndicator().setActive(false);
                }

                updateSearchResult(patientData);

                return true;
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
    public void chooseActivePatient(PatientData p) {
        _businessLogicController.setActivePatient(p);
    }

    /**
     * Update patient data in content tabs
     * @param p
     */
    public void updatePatientData(PatientBo p) {
        _contentTab.updatePatientData(p);

        // update patient name window bar
        String title = _easyDietWindow.getTitle();
        _easyDietWindow.setTitle(title + " Patient: " + p.getForename() + " " + p.getLastname().toUpperCase());
    }
}
