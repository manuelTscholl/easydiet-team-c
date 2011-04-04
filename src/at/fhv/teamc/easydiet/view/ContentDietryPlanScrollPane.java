/**
 * This File is part of Easy Diet
 * created on: 02.04.2011
 * created by: Michael
 * file: ContentDietryPlanScrollPane.java
 */
package at.fhv.teamc.easydiet.view;

import java.net.URL;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TablePane;

/**
 * Represents the application's content dietry plan tab (content_dietryPlan.bxml)
 * @author Michael
 */
public class ContentDietryPlanScrollPane extends ScrollPane implements Bindable {

    /**
     * First called after creating the GUI
     * @param namespace Contains all UI elements in content_dietryPlan.bxml by id name
     * @param url Contains the URL of the content_dietryPlan bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        
        // EXAMPLE
        //example();
        // END EXAMPLE
    }

    private void example() {
        
        // new week
        Border weekOne = new Border();
        weekOne.setTitle("Woche 1");
        weekOne.getStyles().put("backgroundColor", "#CFE4F1");
        weekOne.getStyles().put("padding", "2");
        weekOne.setPreferredWidth(600);
        setView(weekOne);

        // add tablepane to week
        TablePane tablePane = new TablePane();
        tablePane.getColumns().add(new TablePane.Column());
        weekOne.setContent(tablePane);

        // day 1
        TablePane.Row tro1 = new TablePane.Row();
        Border dayOne = new Border();
        dayOne.setTitle("Tag 1");
        dayOne.getStyles().put("backgroundColor", "#CFE4F1");
        tro1.add(dayOne);
        tablePane.getRows().add(tro1);

        // day 2
        TablePane.Row tro2 = new TablePane.Row();
        Border dayTwo = new Border();
        dayTwo.setTitle("Tag 2");
        dayTwo.getStyles().put("backgroundColor", "#CFE4F1");
        tro2.add(dayTwo);
        tablePane.getRows().add(tro2);
    }

    /**
     * Add a new dietry week
     */
    public void addWeek() {
    }

    /**
     * Add a new dietry day
     */
    public void addDay() {
    }
}
