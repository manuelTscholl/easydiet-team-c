/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: ContentAnamnesisScrollPane.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ScrollPane;

/**
 * Represents the application's content anamnesis tab (content_anamnesis.bxml)
 * @author Michael
 */
public class ContentAnamnesisScrollPane extends ScrollPane implements Bindable, Resizable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContentAnamnesisScrollPane.class);

    /**
     * First called after creating the GUI
     * @param namespace Contains all UI elements in content_anamnesis.bxml by id name
     * @param url Contains the URL of the content_anamnesis bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

        // register window in main window
        MainWindow.registerResizableComponent(this);
    }

    /**
     * Resize content_anamnesis.xml
     * @param height
     * @param width
     */
    public void resize(int height, int width) {
        setPreferredHeight(height - 70);
    }
}
