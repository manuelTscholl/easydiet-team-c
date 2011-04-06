/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: EasyDietWindow.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.Window;

/**
 * Represents the application's main entry point into the UI (main.xml)
 * @author Michael
 */
public class EasyDietWindow extends Window implements Bindable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(EasyDietWindow.class);
    // instance variables
    private Map<String, Object> _namespace;
    private TablePane _content;
    private TablePane.Column _menuBarColumn;

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in main.xml by id name
     * @param url Contains the URL of the main bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
        _namespace = map;

        // get GUI elements
        _content = (TablePane) map.get("mainContentTablePane");
        _menuBarColumn = (TablePane.Column)map.get("mainTableColumn");
    }

    /**
     * Get all ressources of main.xml
     * @return
     */
    public Map<String, Object> getRessources() {
        return _namespace;
    }
}
