/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: ContentTabPane.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.TabPane;

/**
 * Represents the application's content tabpane (content.bxml)
 * @author Michael
 */
public class ContentTabPane extends TabPane implements Bindable {

    // instance variables
    @BXML
    private BoxPane _appointmentBoxPane;
    @BXML
    private BoxPane _overviewBoxPane;
    @BXML
    private BoxPane _contactJournalBoxPane;
    @BXML
    private BoxPane _anamnesisBoxPane;
    @BXML
    private BoxPane _dietryPlanBoxPane;

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in content.bxml by id name
     * @param url Contains the URL of the content bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get GUI components
        _appointmentBoxPane = (BoxPane) map.get("appointmentBoxPane");
        _overviewBoxPane = (BoxPane) map.get("overviewBoxPane");
        _contactJournalBoxPane = (BoxPane) map.get("contactJournalBoxPane");
        _anamnesisBoxPane = (BoxPane) map.get("anamnesisBoxPane");
        _dietryPlanBoxPane = (BoxPane) map.get("dietryPlanBoxPane");
    }
}
