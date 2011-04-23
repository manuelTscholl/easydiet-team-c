/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: NavigationMode.java
 */
package at.easydiet.teamc.view.util;

import at.easydiet.teamc.view.NavigationTabPane;

/**
 *
 * @author Michael Sieber
 */
public abstract class NavigationMode {

    // instance variables
    protected NavigationTabPane _navTab;

    /**
     * Draw this mode
     */
    public abstract void draw();

    /**
     * Clean mode
     */
    public void clean() {

        // clean edit tab
        int count = _navTab.getEditTablePane().getRows().getLength() - 1;
       _navTab.getEditTablePane().getRows().remove(1, count);
    }
}
