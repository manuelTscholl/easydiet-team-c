/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: EasyDietWindow.java
 */
package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TabPaneSelectionListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.Window;

/**
 * Represents the application's main entry point into the UI (main.xml)
 * 
 * @author Michael
 */
public class EasyDietWindow extends Window implements Bindable {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(EasyDietWindow.class);
	// instance variables
	private Map<String, Object> _namespace;
	private TablePane _content;
	private TablePane.Column _menuBarColumn;
	private NavigationTabPane _navTab;
	private ContentTabPane _contentTab;
	private ContentAnamnesisScrollPane _anamnesisScrollPane;

	/**
	 * First called after creating the GUI
	 * 
	 * @param map Contains all UI elements in main.xml by id name
	 * @param url Contains the URL of the main bxml file
	 * @param rsrcs
	 */
	@Override
	public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {
		_namespace = map;

		// get GUI elements
		_content = (TablePane) map.get("mainContentTablePane");
		_menuBarColumn = (TablePane.Column) map.get("mainTableColumn");
		_navTab = (NavigationTabPane) map.get("navTab");
		_contentTab = (ContentTabPane) map.get("contentTab");

		// register
		GUIComponents.put("easyDietWindow", this);

		// listener for content tab to change navigation tab
		_contentTab.getTabPaneSelectionListeners().add(
				new TabPaneSelectionListener() {

					// TabPane tp contains the tap from which we are coming and
					// his index
					@Override
					public Vote previewSelectedIndexChange(TabPane tp, int i) {
						return Vote.APPROVE;
					}

					@Override
					public void selectedIndexChangeVetoed(TabPane tp, Vote vote) {
					}

					// TabPane tp contains the actual clicked tap and his index
					@Override
					public void selectedIndexChanged(TabPane tp, int i) {

						// appointment Tab clicked
						if (tp.getSelectedTab().getName()
								.equals("appointmentTab")) {
						} else if (tp.getSelectedTab().getName()
								.equals("overviewTab")) { // overview Tab
															// clicked
							// TODO placeholder implement if necessary
						} else if (tp.getSelectedTab().getName()
								.equals("contactJournalTab")) { // contactJournal
																// Tab clicked
							// TODO placeholder implement if necessary
						} else if (tp.getSelectedTab().getName()
								.equals("anamnesisTab")) { // anamnesis Tab
															// clicked
							_contentTab.reloadAnamnesisTab();
						} else if (tp.getSelectedTab().getName()
								.equals("dietryPlanTab")) { // dietryPlan Tab
															// clicked
							_navTab.setDietryPlanMode();
						}
					}
				});
	}

	/**
	 * Get all ressources of main.xml
	 * 
	 * @return
	 */
	public Map<String, Object> getRessources() {
		return _namespace;
	}
}
