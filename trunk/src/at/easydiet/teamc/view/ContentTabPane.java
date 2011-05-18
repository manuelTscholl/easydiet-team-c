/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: ContentTabPane.java
 */
package at.easydiet.teamc.view;

import java.net.URL;
import java.util.ArrayList;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.TabPane;

import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.PatientData;

/**
 * Represents the application's content tabpane (content.bxml)
 * 
 * @author Michael
 */
public class ContentTabPane extends TabPane implements Bindable {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(ContentTabPane.class);
	// instance variables
	private BoxPane _appointmentBoxPane;
	private BoxPane _overviewBoxPane;
	private BoxPane _contactJournalBoxPane;
	private BoxPane _anamnesisBoxPane;
	private BoxPane _dietryBoxPane;
	private ContentAppointmentScrollPane _appointmentScrollPane;
	private ContentOverviewScrollPane _overviewScrollPane;
	private ContentContactJournalScrollPane _contactJournalScrollPane;
	private ContentAnamnesisScrollPane _anamnesisScrollPane;
	private ContentDietryPlanScrollPane _dietryPlanScrollPane;
	private ArrayList<PatientDataListener> _patientDataListeners;

	{
		_patientDataListeners = new ArrayList<PatientDataListener>();
	}

	/**
	 * First called after creating the GUI
	 * 
	 * @param map Contains all UI elements in content.bxml by id name
	 * @param url Contains the URL of the content bxml file
	 * @param rsrcs
	 */
	@Override
	public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

		// get tab content
		_appointmentBoxPane = (BoxPane) map.get("appointmentTab");
		_overviewBoxPane = (BoxPane) map.get("overviewTab");
		_contactJournalBoxPane = (BoxPane) map.get("contactJournalTab");
		_anamnesisBoxPane = (BoxPane) map.get("anamnesisTab");
		_dietryBoxPane = (BoxPane) map.get("dietryPlanTab");
		_appointmentScrollPane = (ContentAppointmentScrollPane) map
				.get("content_appointment");
		_patientDataListeners.add(_appointmentScrollPane);
		_overviewScrollPane = (ContentOverviewScrollPane) map
				.get("content_overview");
		_patientDataListeners.add(_overviewScrollPane);
		_contactJournalScrollPane = (ContentContactJournalScrollPane) map
				.get("content_contactJournal");
		_patientDataListeners.add(_contactJournalScrollPane);
		_anamnesisScrollPane = (ContentAnamnesisScrollPane) map
				.get("content_anamnesis");
		_patientDataListeners.add(_anamnesisScrollPane);
		_dietryPlanScrollPane = (ContentDietryPlanScrollPane) map
				.get("content_dietryPlan");
		_patientDataListeners.add(_dietryPlanScrollPane);

		// add listener for resizing
		getComponentListeners().add(new ComponentListenerAdapter() {

			@Override
			public void sizeChanged(Component component, int previousWidth,
					int previousHeight) {
				int height = component.getHeight() - 37;
				_appointmentScrollPane.setPreferredHeight(height);
				_overviewScrollPane.setPreferredHeight(height);
				_contactJournalScrollPane.setPreferredHeight(height);
				_anamnesisScrollPane.setPreferredHeight(height);
				_dietryPlanScrollPane.setPreferredHeight(height);

			}
		});

		// register component
		GUIComponents.put(getName(), this);
	}

	/**
	 * Update patient data in tabs
	 * 
	 * @param p the new patient
	 */
	public void updatePatientData(PatientData p) {

		// update patient data in tabs
		for (PatientDataListener pdl : _patientDataListeners) {
			pdl.updatePatientData(p);
		}
	}

	/**
	 * Select a Tab by his bxml id
	 * 
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
	 * Draw a dietry plan
	 * 
	 * @param plan to draw
	 */
	public void drawDietryPlan(DietryPlanData plan) {
		_dietryPlanScrollPane.drawDietryPlan(plan);
	}

	/**
	 * Unlock all menu items, which are patient specific
	 */
	public void unlockPatientSpecificItems() {
		_overviewBoxPane.setEnabled(true);
		_contactJournalBoxPane.setEnabled(true);
		_anamnesisBoxPane.setEnabled(true);
		_dietryBoxPane.setEnabled(true);
	}
}
