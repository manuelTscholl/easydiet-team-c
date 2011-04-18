/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: NavigationTabPane.java
 */
package at.easydiet.teamc.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ActivityIndicator;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.teamc.model.data.PatientData;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;

/**
 * Represents the application's navigation tabpane (navigation.bxml)
 * @author Michael
 */
public class NavigationTabPane extends TabPane implements Bindable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(NavigationTabPane.class);
    // instance variables
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
        _searchTextInput = (TextInput) map.get("searchTextInput");
        _patientDataBoxPane = (BoxPane) map.get("patientDataBoxPane");
        _navigationBoxPane = (BoxPane) map.get("navigationBoxPane");
        _editTablePane = (TablePane) map.get("editTablePane");
        _searchResultTablePane = (TablePane) map.get("searchResultTablePane");
        _searchResultScrollPane = (ScrollPane) map.get("searchResultScrollPane");
        _searchActivityIndicator = (ActivityIndicator) map.get("searchActivityIndicator");

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

        //TODO example popup
        TablePane.Row tro = new TablePane.Row();
        Button b = new PushButton("Neuen Diätplan erstellen");
        tro.add(b);
        _editTablePane.getRows().add(tro);

        b.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                BXMLSerializer bxml = new BXMLSerializer();
                NewDietryPlanDialog d;
                try {
                    d = (NewDietryPlanDialog) bxml.readObject(DateDialog.class, "bxml/new_dietry_plan_dialog.bxml");
                    EasyDietWindow window = (EasyDietWindow) GUIComponents.get("easyDietWindow");
                    d.open(window);
                } catch (IOException ex) {
                    LOGGER.error(ex);
                } catch (SerializationException ex) {
                    LOGGER.error(ex);
                }
            }
        });

        TablePane.Row tro1 = new TablePane.Row();
        Button b1 = new PushButton("Mahlzeit hinzufügen");
        tro1.add(b1);
        _editTablePane.getRows().add(tro1);

        b1.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                BXMLSerializer bxml = new BXMLSerializer();
                ChooseMealDialog d;
                try {
                    d = (ChooseMealDialog) bxml.readObject(DateDialog.class, "bxml/choose_meal_dialog.bxml");
                    EasyDietWindow window = (EasyDietWindow) GUIComponents.get("easyDietWindow");
                    d.open(window);
                } catch (IOException ex) {
                    LOGGER.error(ex);
                } catch (SerializationException ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }
}
