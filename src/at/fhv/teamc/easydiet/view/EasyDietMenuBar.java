/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: MainWindow.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.MenuBar;

/**
 * Represents the application's menubar (menu.bxml)
 * @author Michael
 */
public class EasyDietMenuBar extends MenuBar implements Bindable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(EasyDietMenuBar.class);

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in menu.bxml by id name
     * @param url Contains the URL of the menu bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
    }
}
