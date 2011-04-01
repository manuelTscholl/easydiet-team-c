/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: NavigationTabPane.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TextInput;

/**
 * Represents the application's navigation tabpane (navigation.bxml)
 * @author Michael
 */
public class NavigationTabPane extends TabPane implements Bindable {

    // instance variables
    @BXML
    private TextInput _searchTextInput;
    @BXML
    private BoxPane _searchResultBoxPane;
    @BXML
    private BoxPane _patientDataBoxPane;
    @BXML
    private BoxPane _navigationBoxPane;
    @BXML
    private BoxPane _editBoxPane;

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in navigation.bxml by id name
     * @param url Contains the URL of the navigation bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get GUI components
        _searchTextInput = (TextInput) map.get("searchTextInput");
        _searchResultBoxPane = (BoxPane) map.get("searchResultBoxPane");
        _patientDataBoxPane = (BoxPane) map.get("patientDataBoxPane");
        _navigationBoxPane = (BoxPane) map.get("navigationBoxPane");
        _editBoxPane = (BoxPane) map.get("editBoxPane");
    }
}
