/**
 * This File is part of Easy Diet
 * created on: 02.04.2011
 * created by: Michael
 * file: ContentDietryPlanScrollPane.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.view.util.DietWeek;
import java.net.URL;
import java.util.ArrayList;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.Viewport;
import org.apache.pivot.wtk.ViewportListener;

import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.PatientData;

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
    private TablePane _planTable;
    private TablePane _parameterTable;
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
    @Override
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

        // initalize main table
        initMainTable();

        // listener for scrolling, this listener always displays the parameter area at the top
        getViewportListeners().add(new ViewportListener() {

            public void scrollTopChanged(Viewport scrollPane, int previousScrollTop) {
                _parameterTable.getStyles().put("padding", "{top:" + scrollPane.getScrollTop() + "}");
            }

            public void scrollLeftChanged(Viewport scrollPane, int previousScrollLeft) {
            }

            public void viewChanged(Viewport scrollPane, Component previousView) {
            }
        });

        // register component
        GUIComponents.put(getName(), this);

        //TODO FOR TESTING ONLY
        for (int i = 0; i < 10; i++) {
            addDay();
        }
    }

    /**
     * Initialize main table
     */
    private void initMainTable() {

        // plan table
        _planTable = new TablePane();
        _planTable.getColumns().add(new TablePane.Column());
        _planTable.getStyles().put("verticalSpacing", "5");

        // parameter table
        _parameterTable = new TablePane();
        _parameterTable.getColumns().add(new TablePane.Column());
        _parameterTable.getStyles().put("verticalSpacing", "5");

        // add both tables
        TablePane.Row tro = new TablePane.Row();
        tro.add(_planTable);
        tro.add(_parameterTable);

        _mainTable.getRows().add(tro);
        _mainTable.getStyles().put("verticalSpacing", "5");

        addParameterTest();
    }

    /**
     * Add parameter test section to plan
     */
    private void addParameterTest() {
        TablePane.Row tro = new TablePane.Row();
        Label l = new Label("PARAMETER TEST");
        tro.add(l);
        _parameterTable.getRows().add(tro);
    }

    /**
     * Draw the dietry plan
     * @param dpd Plan to draw
     */
    public void drawDietryPlan(DietryPlanData dpd) {
        
        // clean
        //TODO clean before new plan is drawn
        
        // draw days
        for (int i = 0; i < dpd.getDuration(); i++) {
            addDay();
        }
    }

    /**
     * Add a new dietry week
     */
    private void addWeek() {
        _dietWeeks.add(new DietWeek(_planTable, _dietWeeks.size() + 1));
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
    @Override
    public void updatePatientData(PatientData p) {
        LOGGER.trace("NOT IMPLEMENTED");
    }

    /**
     * Update the dietry plan
     */
    public void updateDietryPlan() {
        DietryPlanData dpd = GUIController.getInstance().getDietryPlan();
        drawDietryPlan(dpd);
    }
}
