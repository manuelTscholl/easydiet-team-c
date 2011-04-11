/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: ContentTabPane.java
 */
package at.fhv.teamc.easydiet.view;

import at.fhv.teamc.easydiet.model.PatientBo;
import java.net.URL;
import java.util.ArrayList;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.TabPane;

/**
 * Represents the application's content tabpane (content.bxml)
 * @author Michael
 */
public class ContentTabPane extends TabPane implements Bindable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContentTabPane.class);
    // instance variables
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
     * @param map Contains all UI elements in content.bxml by id name
     * @param url Contains the URL of the content bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get tab content
        _appointmentScrollPane = (ContentAppointmentScrollPane) map.get("content_appointment");
        _patientDataListeners.add(_appointmentScrollPane);
        _overviewScrollPane = (ContentOverviewScrollPane) map.get("content_overview");
        _patientDataListeners.add(_overviewScrollPane);
        _contactJournalScrollPane = (ContentContactJournalScrollPane) map.get("content_contactJournal");
        _patientDataListeners.add(_contactJournalScrollPane);
        _anamnesisScrollPane = (ContentAnamnesisScrollPane) map.get("content_anamnesis");
        _patientDataListeners.add(_anamnesisScrollPane);
        _dietryPlanScrollPane = (ContentDietryPlanScrollPane) map.get("content_dietryPlan");
        _patientDataListeners.add(_dietryPlanScrollPane);

        // add listener for resizing
        getComponentListeners().add(new ComponentListenerAdapter() {

            @Override
            public void sizeChanged(Component component, int previousWidth, int previousHeight) {
                int height = component.getHeight() - 20;
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
     * @param p
     */
    public void updatePatientData(PatientBo p) {

        // select overview tab
        setSelectedIndex(1);

        // update patient data in tabs
        for (PatientDataListener pdl : _patientDataListeners) {
            pdl.updatePatientData(p);
        }
    }
}
