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
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TabPane;

/**
 * Represents the application's content tabpane (content.bxml)
 * @author Michael
 */
public class ContentTabPane extends TabPane implements Bindable {

    // instance variables
    @BXML
    private ScrollPane _appointmentScrollPane;
    @BXML
    private ScrollPane _overviewScrollPanePane;
    @BXML
    private ScrollPane _contactJournalScrollPane;
    @BXML
    private ScrollPane _anamnesisScrollPane;
    @BXML
    private ScrollPane _dietryPlanScrollPane;

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in content.bxml by id name
     * @param url Contains the URL of the content bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get GUI components
        _appointmentScrollPane = (ScrollPane) map.get("appointmentBoxPane");
        _overviewScrollPanePane = (ScrollPane) map.get("overviewBoxPane");
        _contactJournalScrollPane = (ScrollPane) map.get("contactJournalBoxPane");
        _anamnesisScrollPane = (ScrollPane) map.get("anamnesisBoxPane");
        _dietryPlanScrollPane = (ScrollPane) map.get("dietryPlanBoxPane");
    }
}
