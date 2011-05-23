/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: ContentAnamnesisScrollPane.java
 */
package at.easydiet.teamc.view;

import java.io.IOException;
import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ScrollPane;

import at.easydiet.teamb.application.handler.UseCaseManager;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.tab.AbstractTab;
import at.easydiet.teamb.presentation.gui.tab.LaborTab;
import at.easydiet.teamc.model.data.PatientData;

/**
 * Represents the application's content anamnesis tab (content_anamnesis.bxml)
 * 
 * @author Michael
 */
public class ContentAnamnesisScrollPane extends ScrollPane implements Bindable,
		PatientDataListener {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(ContentAnamnesisScrollPane.class);

	// instance variables
	@BXML
	private PushButton _addLaborReportButton;
	@BXML
	private PushButton _addPatientStatusButton;
	@BXML
	private AbstractTab _laborview;
	@BXML
	private AbstractTab _patientStatusview;
	@BXML
	private BoxPane _pane;

	/**
	 * First called after creating the GUI
	 * 
	 * @param namespace Contains all UI elements in content_anamnesis.bxml by id
	 *            name
	 * @param url Contains the URL of the content_anamnesis bxml file
	 * @param rsrcs
	 */
	@Override
	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {

		// register component
		GUIComponents.put(getName(), this);

		// register button listenrs
		_addLaborReportButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						BXMLSerializer bxml = new BXMLSerializer();
						Component component;
						Dialog dialog = new Dialog();
						try {
							component = (Component) bxml
									.readObject(getClass()
											.getResource(
													"bxml/teamb/easydiet_tab_labor_create.bxml"));

							dialog.setContent(component);
							dialog.open(getWindow());
							dialog.setTitle("Laborbefund hinzufügen");

							// set window height
							dialog.setPreferredHeight(getPreferredHeight() - 80);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SerializationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

		_addPatientStatusButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						BXMLSerializer bxml = new BXMLSerializer();
						Component component;
						Dialog dialog = new Dialog();
						try {
							component = (Component) bxml
									.readObject(getClass()
											.getResource(
													"bxml/teamb/easydiet_tab_patientStatus_create.bxml"));
							dialog.setContent(component);
							dialog.open(getWindow());
							dialog.setTitle("Patientenstatus anlegen");
							dialog.setPreferredHeight(getPreferredHeight() - 80);
						} catch (IOException ex) {
							LOGGER.error(ex);
						} catch (SerializationException ex) {
							LOGGER.error(ex);
						}

					}
				});
	}

	/**
	 * Update patient data
	 * 
	 * @param p the new patient data
	 */
	@Override
	public void updatePatientData(PatientData p) {
		LOGGER.trace("NOT IMPLEMENTED");
	}

	/**
	 * Reload the content of this tab
	 */
	public void reload() {
		// try {
		_pane.removeAll();
		// _pane.remove(_patientStatusview);

		BXMLSerializer bxml = new BXMLSerializer();
		try {
			_laborview = (LaborTab) bxml
					.readObject(ContentAnamnesisScrollPane.class
							.getResource("bxml/teamb/easydiet_tab_labor.bxml"));
			// _patientStatusview = (PatientStatusTab) bxml
			// .readObject(ContentAnamnesisScrollPane.class
			// .getResource("bxml/teamb/easydiet_tab_patientstatus.bxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		_pane.add(_laborview);
		_pane.add(_addLaborReportButton);
		_pane.add(_patientStatusview);
		_pane.add(_addPatientStatusButton);
		try {
			_laborview.display(UseCaseManager.getWindowHandler());
		} catch (NoPatientSelectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// _pane.add(_patientStatusview);
		// _laborview.display(UseCaseManager.getWindowHandler());
		// _patientStatusview.display(UseCaseManager.getWindowHandler());

		setView(_pane);
		// } catch (NoPatientSelectedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
