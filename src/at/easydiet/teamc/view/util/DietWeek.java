/**
 * This File is part of Easy Diet
 * created on: 11.04.2011
 * created by: Michael
 * file: DietWeek.java
 */
package at.easydiet.teamc.view.util;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.MealData;
import at.easydiet.teamc.model.data.MealLineData;
import at.easydiet.teamc.view.ComponentListenerAdapter;
import java.util.ArrayList;
import java.util.Set;

import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Expander;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Mouse.Button;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.content.ButtonData;

/**
 * Represents a single week
 * @author Michael
 */
public class DietWeek {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DietWeek.class);
    // instance variables
    private ArrayList<DietDay> _days;
    private TablePane _srcTablePane;
    private Expander _weekExpander;
    private TablePane _weekTable;
    private int _weekNumber;

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

    /**
     * Draw this week
     */
    private void drawWeek() {

        // Expander
        _weekExpander = new Expander();
        _weekExpander.setName("expander_week_" + _weekNumber);
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
        _srcTablePane.getRows().add(planTableRow);

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
        _srcTablePane.getComponentListeners().add(new ComponentListenerAdapter() {

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
     * @param meals for this day
     */
    public void addDay(Set<MealData> meals) {
        DietDay dd = new DietDay(_days.size() + 1, meals);
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
        private TablePane _mealTable;
        private Set<MealData> _meals;

        /**
         * Constructor
         * @param actualDay
         */
        public DietDay(int actualDay, Set<MealData> meals) {
            _actualDay = actualDay;
            _meals = meals;
            _mealTable = new TablePane();
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
            
            // add day content
            _mealTable.getColumns().add(new TablePane.Column()); // new meal button
            _mealTable.getColumns().add(new TablePane.Column()); // meal code
            _mealTable.getColumns().add(new TablePane.Column()); // meal recipes
            
            // first row for add button
            TablePane.Row buttonRow = new TablePane.Row();
            ButtonData buttonData = new ButtonData();
            buttonData.setIcon(getClass().getResource("../bxml/plus-circle.png"));
            PushButton newMealButton = new PushButton(buttonData);
            newMealButton.setPreferredSize(22, 20);
            newMealButton.getStyles().put("toolbar", "true");
            newMealButton.setTooltipDelay(100);
            newMealButton.setTooltipText("Neue Mahlzeit hinzufügen");
            buttonRow.add(newMealButton);
            _mealTable.getRows().add(buttonRow);
            
            // add meals if exist
            if(_meals != null && !_meals.isEmpty()){
                
                for(MealData m:_meals){
                    TablePane.Row mealRow = new TablePane.Row();
                    mealRow.add(null); // meal button
                    Label mealCode = new Label(m.getMealCodeData().getName());
                    mealRow.add(mealCode);
                    
                    // recipes
                    BoxPane recipes = new BoxPane();
                    Set<MealLineData> lines = m.getMealLineData();
                    for(MealLineData mLine:lines){
                        Label l = new Label(mLine.getRecipeData().getName());
                        recipes.add(l);
                    }
                    mealRow.add(recipes);
                    
                    // add content to table
                    _mealTable.getRows().add(mealRow);
                }
            }
            
            // add button listener
            newMealButton.getButtonPressListeners().add(new ButtonPressListener() {

                public void buttonPressed(org.apache.pivot.wtk.Button button) {
                    GUIController.getInstance().addMeal(_actualDay);
                }
            });
            
            // add meal table
            _dayBorder.setContent(_mealTable);
            
            // add row
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
