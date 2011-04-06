/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: ContentContactJournalScrollPane.java
 */
package at.fhv.teamc.easydiet.view;

import at.easydiet.model.Patient;
import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ScrollPane;

/**
 * Represents the application's content contact journal tab (content_contactJournal.bxml)
 * @author Michael
 */
public class ContentContactJournalScrollPane extends ScrollPane implements Bindable, PatientDataListener {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContentContactJournalScrollPane.class);

    /**
     * First called after creating the GUI
     * @param namespace Contains all UI elements in content_contactJournal.bxml by id name
     * @param url Contains the URL of the content_contactJournal bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

    }

    /**
     * Update patient data
     * @param p
     */
    public void updatePatientData(Patient p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
