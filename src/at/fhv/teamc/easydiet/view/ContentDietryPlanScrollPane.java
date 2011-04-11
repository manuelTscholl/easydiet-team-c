/**
 * This File is part of Easy Diet
 * created on: 02.04.2011
 * created by: Michael
 * file: ContentDietryPlanScrollPane.java
 */
package at.fhv.teamc.easydiet.view;

import at.fhv.teamc.easydiet.model.PatientBo;
import java.net.URL;
import java.util.ArrayList;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TablePane;

/**
 * Represents the application's content dietry plan tab (content_dietryPlan.bxml)
 * @author Michael
 */
public class ContentDietryPlanScrollPane extends ScrollPane implements Bindable, PatientDataListener {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContentDietryPlanScrollPane.class);
    // instance variables
    private Map<String, Object> _namespace;
    private TablePane _mainTable;
    private ArrayList<DietWeek> _dietWeeks;

    {
        _dietWeeks = new ArrayList<DietWeek>();
    }


    /**
     * First called after creating the GUI
     * @param namespace Contains all UI elements in content_dietryPlan.bxml by id name
     * @param url Contains the URL of the content_dietryPlan bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

        // get GUI components
        _namespace = namespace;
        _mainTable = (TablePane) _namespace.get("dietryPlanTablePane");
        ((TablePane.Column) _namespace.get("planColumn")).setWidth(getPreferredWidth());

        getComponentListeners().add(new ComponentListenerAdapter() {

            @Override
            public void sizeChanged(Component component, int previousWidth, int previousHeight) {
                int width = ((TablePane.Column) _namespace.get("parameterColumn")).getWidth();
                ((TablePane.Column) _namespace.get("planColumn")).setWidth(component.getWidth() - width);
            }
        });

        // register component
        GUIComponents.put(getName(), this);

        // EXAMPLE
        int end = 15;
        for (int i = 0; i < end; i++) {
            addDay();
        }
    }

    /**
     * Add a new dietry week
     */
    private void addWeek() {
        _dietWeeks.add(new DietWeek(_mainTable, _dietWeeks.size() + 1));
    }

    /**
     * Add a new dietry day
     */
    public void addDay() {
        int actualWeek;

        // check for existing weeks
        if (_dietWeeks.isEmpty()) {
            addWeek();
            actualWeek = _dietWeeks.size() - 1;
            _dietWeeks.get(actualWeek).addDay();
        } else { // existing week

            // get actual week
            actualWeek = _dietWeeks.size() - 1;

            // check week is full
            if (_dietWeeks.get(actualWeek).size() >= 7) {
                addWeek();
                actualWeek = _dietWeeks.size() - 1;
                _dietWeeks.get(actualWeek).addDay();
            } else {
                _dietWeeks.get(actualWeek).addDay();
            }
        }
    }

    /**
     * Update patient data
     * @param p
     */
    public void updatePatientData(PatientBo p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
