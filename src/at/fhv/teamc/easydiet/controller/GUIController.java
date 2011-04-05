/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: GUIController.java
 */
package at.fhv.teamc.easydiet.controller;

import at.easydiet.model.Patient;
import at.fhv.teamc.easydiet.view.ContentTabPane;
import at.fhv.teamc.easydiet.view.EasyDietMenuBar;
import at.fhv.teamc.easydiet.view.EasyDietWindow;
import at.fhv.teamc.easydiet.view.KeyAdapter;
import at.fhv.teamc.easydiet.view.NavigationTabPane;
import java.util.Set;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.TextInput;

/**
 * Controls and knows all GUI elements
 * @author Michael
 */
public class GUIController {

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
     * Add a window to the controller and get the ressources
     * @param eadw
     */
    public void addWindow(EasyDietWindow eadw) {
        _easyDietWindow = eadw;
        _guiNamespaces = _easyDietWindow.getRessources();

        _businessLogicController = new BusinessLogicController();

        // get gui containers
        _navTab = (NavigationTabPane) _guiNamespaces.get("navTab");
        _menuBar = (EasyDietMenuBar) _guiNamespaces.get("menuBar");
        _contentTab = (ContentTabPane) _guiNamespaces.get("contentTab");

        // add key event for search textinput field
        _navTab.getSearchTextInput().getComponentKeyListeners().add(new KeyAdapter() {

            @Override
            public boolean keyTyped(Component component, char character) {
                _businessLogicController.searchPatient(((TextInput) component).getText());
                return true;
            }
        });

    }

    /**
     * Update search results in navigation search tab with received set
     * @param patients
     */
    public void updateSearchResult(Set<Patient> patients) {
        _navTab.updateSearchResult(patients);
    }
}
