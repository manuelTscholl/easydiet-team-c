/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	11.05.2011
 */

package at.easydiet.teamb.presentation.gui;

import at.easydiet.teamb.presentation.gui.tab.AbstractTab;
import at.easydiet.teamb.presentation.gui.tab.LazyTab;

/**
 * The Class AbstractLazyTab.
 */
public abstract class AbstractLazyTab extends AbstractTab {
	
	private LazyTab _lazyTab;

	/**
	 * Sets the lazy tab.
	 *
	 * @param lazyTab the new lazy tab
	 */
	public void setLazyTab(LazyTab lazyTab) {
		_lazyTab = lazyTab;
	}
	
	/**
	 * Gets the lazy tab.
	 *
	 * @return the lazy tab
	 */
	public LazyTab getLazyTab() {
		return _lazyTab;
	}
}
