/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: NavigationTabPane.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.model.data.DietryPlanData;
import java.net.URL;
import java.util.ArrayList;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ActivityIndicator;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.view.util.DefaultNavigationMode;
import at.easydiet.teamc.view.util.DietryPlanNavigationMode;
import at.easydiet.teamc.view.util.NavigationMode;
import org.apache.pivot.collections.Set;

/**
 * Represents the application's navigation tabpane (navigation.bxml)
 * @author Michael
 */
public class NavigationTabPane extends TabPane implements Bindable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(NavigationTabPane.class);
    // instance variables
    private BoxPane _searchBoxPane;
    private BoxPane _editBoxPane;
    @BXML
    private TextInput _searchTextInput;
    @BXML
    private BoxPane _patientDataBoxPane;
    @BXML
    private BoxPane _navigationBoxPane;
    @BXML
    private TablePane _editTablePane;
    @BXML
    private TablePane _searchResultTablePane;
    @BXML
    private ScrollPane _searchResultScrollPane;
    @BXML
    private ActivityIndicator _searchActivityIndicator;
    private ArrayList<PatientListener> _patientListeners;
    private NavigationMode _navigationMode;

    {
        _patientListeners = new ArrayList<PatientListener>();
    }

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in navigation.bxml by id name
     * @param url Contains the URL of the navigation bxml file
     * @param rsrcs
     */
    @Override
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get GUI components
        _searchBoxPane = (BoxPane)map.get("searchTab");
        _editBoxPane = (BoxPane)map.get("editTab");
        _patientDataBoxPane = (BoxPane)map.get("patientDataTab");
        _searchTextInput = (TextInput) map.get("searchTextInput");
        _navigationBoxPane = (BoxPane) map.get("navigationTab");
        _editTablePane = (TablePane) map.get("editTablePane");
        _searchResultTablePane = (TablePane) map.get("searchResultTablePane");
        _searchResultScrollPane = (ScrollPane) map.get("searchResultScrollPane");
        _searchActivityIndicator = (ActivityIndicator) map.get("searchActivityIndicator");

        // init navigation mode
        _navigationMode = new DefaultNavigationMode(this);

        // register component
        GUIComponents.put(getName(), this);

        // listener for resizing
        getComponentListeners().add(new ComponentListenerAdapter() {

            @Override
            public void sizeChanged(Component component, int previousWidth, int previousHeight) {
                _searchResultScrollPane.setPreferredHeight(component.getHeight() - 35);
            }
        });
    }

    public TablePane getEditTablePane() {
        return _editTablePane;
    }

    public BoxPane getNavigationBoxPane() {
        return _navigationBoxPane;
    }

    public BoxPane getPatientDataBoxPane() {
        return _patientDataBoxPane;
    }

    /**
     * Getter for the search textinput field
     * @return
     */
    public TextInput getSearchTextInput() {
        return _searchTextInput;
    }

    /**
     * Getter for the search activity indicator
     * @return
     */
    public ActivityIndicator getActivityIndicator() {
        return _searchActivityIndicator;
    }

    /**
     * Set active tab
     * @param id Tab id
     */
    public void setActiveTab(int id) {
        setSelectedIndex(id);
    }

    /**
     * Update search results with received set
     * @param patients
     */
    public void updateSearchResult(Set<PatientData> patients) {

        // draw search results
        SearchResult searchResult = SearchResult.getInstance(this, _searchResultTablePane);
        searchResult.drawResults(patients);

        // stop activityIndicator
        _searchActivityIndicator.setActive(false);
    }

    /**
     * Register a new patient listener
     * @param pl patientListener Object
     */
    public void registerListener(PatientListener pl) {

        // check if already registerd
        if (!_patientListeners.contains(pl)) {
            _patientListeners.add(pl);
        }
    }

    /**
     * Notify patient listeners, a patient is selected as active
     * @param p Patient which wants to be active
     */
    protected void notifyPatientListeners(PatientData p) {
        for (PatientListener pl : _patientListeners) {
            pl.chooseActivePatient(p);
        }
    }

    /**
     * Select a Tab by his bxml id
     * @param name BXML ID
     */
    public void setSelectedTabByName(String name) {
        for (int i = 0; i < getTabs().getLength(); i++) {
            if (getTabs().get(i).getName().equals(name)) {
                setSelectedIndex(i);
                return;
            }
        }
    }

    /**
     * Set dietry plan mode. Change content of each tab
     * to provide usefull informations for the dietry plan
     */
    public void setDietryPlanMode() {
        _navigationMode = new DietryPlanNavigationMode(this);
        _navigationMode.draw();
    }

    /**
     * Draw a menu for dietry plan
     * @param plan to draw a menu for
     */
    public void drawDietryPlanMenu(DietryPlanData plan) {
        //TODO implement navigation for dietry plan
    }

    /**
     * Unlock all menu items, which are patient specific
     */
    public void unlockPatientSpecificItems() {
        _patientDataBoxPane.setEnabled(true);
        _navigationBoxPane.setEnabled(true);
        _editBoxPane.setEnabled(true);
    }
}
