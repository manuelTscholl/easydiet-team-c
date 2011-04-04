/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: ContentOverviewScrollPane.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ScrollPane;

/**
 * Represents the application's content overview tab (content_overview.bxml)
 * @author Michael
 */
public class ContentOverviewScrollPane extends ScrollPane implements Bindable, Resizable {

    /**
     * First called after creating the GUI
     * @param namespace Contains all UI elements in content_overview.bxml by id name
     * @param url Contains the URL of the content_overview bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

        // register window in main window
        MainWindow.registerResizableComponent(this);
    }

    /**
     * Resize content_overview.xml
     * @param height
     * @param width
     */
    public void resize(int height, int width) {
        setPreferredHeight(height - 70);
    }
}
