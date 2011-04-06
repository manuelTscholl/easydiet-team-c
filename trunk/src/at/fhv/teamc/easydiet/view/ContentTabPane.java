/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: ContentTabPane.java
 */
package at.fhv.teamc.easydiet.view;

import at.easydiet.model.Patient;
import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ScrollPane;
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

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in content.bxml by id name
     * @param url Contains the URL of the content bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get tab content
        _appointmentScrollPane = (ContentAppointmentScrollPane)map.get("content_appointment");
        _overviewScrollPane = (ContentOverviewScrollPane)map.get("content_overview");
        _contactJournalScrollPane = (ContentContactJournalScrollPane)map.get("content_contactJournal");
        _anamnesisScrollPane = (ContentAnamnesisScrollPane)map.get("content_anamnesis");
        _dietryPlanScrollPane = (ContentDietryPlanScrollPane)map.get("content_dietryPlan");
    }

    /**
     * Update patient data in tabs
     * @param p
     */
    public void updatePatientData(Patient p){
        LOGGER.debug("update patients received");

        // update patient name window bar
        MainWindow.setPatientName(p.getForename() + " " + p.getLastname().toUpperCase());

        // select overview tab

        // set data
        _overviewScrollPane.updatePatientData(p);
    }
}
