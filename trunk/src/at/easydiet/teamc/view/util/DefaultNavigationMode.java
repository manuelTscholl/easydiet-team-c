/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: DefaultNavigationMode.java
 */
package at.easydiet.teamc.view.util;

import at.easydiet.teamc.view.NavigationTabPane;

/**
 * This is the default mode in the navigation
 * 
 * @author Michael Sieber
 */
public class DefaultNavigationMode extends NavigationMode {

	/**
	 * Create new defaultnavigationmode.
	 * 
	 * @param navTab the navigation tab
	 */
	public DefaultNavigationMode(NavigationTabPane navTab) {
		_navTab = navTab;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.teamc.view.util.NavigationMode#draw()
	 */
	@Override
	public void draw() {
		clean();
	}
}
