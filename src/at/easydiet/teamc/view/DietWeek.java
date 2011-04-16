/**
 * This File is part of Easy Diet
 * created on: 11.04.2011
 * created by: Michael
 * file: DietWeek.java
 */
package at.easydiet.teamc.view;

import java.util.ArrayList;

import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Expander;
import org.apache.pivot.wtk.Mouse.Button;
import org.apache.pivot.wtk.TablePane;

/**
 * Represents a single week
 * @author Michael
 */
public class DietWeek {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DietWeek.class);
    private static boolean sIS_DRAWN;
    private static TablePane sPLAN_TABLE;
    // instance variables
    private ArrayList<DietDay> _days;
    private TablePane _srcTablePane;
    private Expander _weekExpander;
    private TablePane _weekTable;
    private int _weekNumber;

    static {
        sIS_DRAWN = false;
        sPLAN_TABLE = new TablePane();
    }

    /**
     * Constructor
     * @param srcTablePane
     * @param week Week number
     */
    public DietWeek(TablePane srcTablePane, int week) {
        _days = new ArrayList<DietDay>();
        _srcTablePane = srcTablePane;
        _weekNumber = week;
        drawWeek();
    }

    private void initMainTable() {

        // check if already drawn
        if (!sIS_DRAWN) {
            sPLAN_TABLE.getColumns().add(new TablePane.Column());
            sPLAN_TABLE.getStyles().put("verticalSpacing", "5");

            TablePane.Row planTableRow = new TablePane.Row();
            planTableRow.add(sPLAN_TABLE);
            _srcTablePane.getRows().add(planTableRow);
            _srcTablePane.getStyles().put("verticalSpacing", "5");

            sIS_DRAWN = true;
        }
    }

    /**
     * Draw this week
     */
    private void drawWeek() {
        initMainTable();

        // Expander
        _weekExpander = new Expander();
        _weekExpander.setTitle("Woche: " + _weekNumber);
        _weekExpander.getStyles().put("backgroundColor", "#EBEDEF");

        // Expander inner table
        _weekTable = new TablePane();
        _weekTable.getColumns().add(new TablePane.Column());
        _weekTable.getStyles().put("verticalSpacing", "5");
        _weekExpander.setContent(_weekTable);

        // add to planTable
        TablePane.Row planTableRow = new TablePane.Row();
        planTableRow.add(_weekExpander);
        sPLAN_TABLE.getRows().add(planTableRow);

        initListeners();
    }

    /**
     * Initialzie listeners
     */
    private void initListeners() {

        // set expander listener
        _weekExpander.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener() {

            @Override
            public boolean mouseDown(Component component, Button button, int x, int y) {
                return true;
            }

            @Override
            public boolean mouseUp(Component component, Button button, int x, int y) {
                return true;
            }

            @Override
            public boolean mouseClick(Component component, Button button, int x, int y, int count) {
                final int titleBarHeight = 20;

                // check if title bar is clicked and not content
                if (y <= titleBarHeight) {

                    // check if expander is open
                    if (_weekExpander.isExpanded()) {
                        _weekExpander.setExpanded(false);
                    } else {
                        _weekExpander.setExpanded(true);
                    }
                }

                return true;
            }
        });

        // add listener for resizing
        sPLAN_TABLE.getComponentListeners().add(new ComponentListenerAdapter() {

            @Override
            public void sizeChanged(Component component, int previousWidth, int previousHeight) {
                _weekExpander.setWidth(component.getWidth() - 10);
            }
        });

        // add listener for resizing
        _weekExpander.getComponentListeners().add(new ComponentListenerAdapter() {

            @Override
            public void sizeChanged(Component component, int previousWidth, int previousHeight) {
                for (DietDay d : _days) {
                    d.resize(component.getWidth(), component.getHeight());
                }
            }
        });
    }

    /**
     * Get number of days in this week
     */
    public int size() {
        return _days.size();
    }

    /**
     * Add a new day
     */
    public void addDay() {
        DietDay dd = new DietDay(_days.size() + 1);
        _days.add(dd);
    }

    /**
     * Represents a single day
     * @author Michael
     */
    public class DietDay {

        // instance variables
        private Border _dayBorder;
        private int _actualDay;

        /**
         * Constructor
         * @param actualDay
         */
        public DietDay(int actualDay) {
            _actualDay = actualDay;
            drawDay();
        }

        /**
         * Draw this day
         */
        private void drawDay() {

            // create new row
            TablePane.Row dayRow = new TablePane.Row();
            _dayBorder = new Border();
            _dayBorder.setTitle("Tag: " + _actualDay);
            _dayBorder.getStyles().put("backgroundColor", "#EBEDEF");
            dayRow.add(_dayBorder);
            _weekTable.getRows().add(dayRow);
        }

        /**
         * Resize day border
         * @param width
         * @param height
         */
        private void resize(int width, int height) {
            _dayBorder.setPreferredWidth(width);
        }
    }
}
