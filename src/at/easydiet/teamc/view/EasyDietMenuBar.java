/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: MainWindow.java
 */
package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Menu;
import org.apache.pivot.wtk.MenuBar;

import at.easydiet.teamc.controller.GUIController;

/**
 * Represents the application's menubar (menu.bxml)
 * 
 * @author Michael
 */
public class EasyDietMenuBar extends MenuBar implements Bindable
{

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(EasyDietMenuBar.class);
    // instance variables
    private Menu.Item                           _addRecipeMenuItem;
    private Menu.Item                           _addPatientMenuItem;

    /**
     * First called after creating the GUI
     * 
     * @param map
     *            Contains all UI elements in menu.bxml by id name
     * @param url
     *            Contains the URL of the menu bxml file
     * @param rsrcs
     */
    @Override
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs)
    {

        // get GUI components
        _addRecipeMenuItem = (Menu.Item) map.get("newRecipeMenuItem");
        _addPatientMenuItem = (Menu.Item) map.get("newPatientMenuItem");
        // add press listeners
        _addRecipeMenuItem.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        GUIController.getInstance().openAddRecipeDialog();
                    }
                });

        _addPatientMenuItem.getButtonPressListeners().add(
                new ButtonPressListener()
                {
                    @Override
                    public void buttonPressed(Button arg0)
                    {
                        GUIController.getInstance().openAddPatientDialog();
                    }
                });

        // register component
        GUIComponents.put(getName(), this);
    }
}
