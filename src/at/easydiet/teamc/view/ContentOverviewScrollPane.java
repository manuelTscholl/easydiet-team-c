/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: ContentOverviewScrollPane.java
 */
package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ScrollPane;

import at.easydiet.teamc.model.data.PatientData;
import org.apache.pivot.wtk.Label;



/**
 * Represents the application's content overview tab (content_overview.bxml)
 * @author Michael
 */
public class ContentOverviewScrollPane extends ScrollPane implements Bindable, PatientDataListener {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContentOverviewScrollPane.class);

    /**
     * First called after creating the GUI
     * @param namespace Contains all UI elements in content_overview.bxml by id name
     * @param url Contains the URL of the content_overview bxml file
     * @param rsrcs
     */
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

        // register component
        GUIComponents.put(getName(), this);
    }

    /**
     * Update patient data
     * @param p
     */
    @Override
    public void updatePatientData(PatientData p) {
        Label l = new Label(p.getForename() + " " + p.getLastname());
        setView(l);
        LOGGER.trace("not implemented");
    }
}
