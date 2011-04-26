/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: DietPlanDialog.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.DietParameterData;
import java.net.URL;
import java.util.Date;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.content.ButtonData;

/**
 * Represents the dialog to add a new dietry plan
 * Timespan -> Choose Parameters -> Add Parameter values
 * @author Michael
 */
public class DietPlanDialog extends Dialog implements Bindable {

    // instance variables
    private TablePane _dateChooserTablePane;
    private TablePane _parameterChooserTablePane;
    private ListView _parameterListView;
    private ListView _chosenParameterListView;
    private PushButton _addParameterButton;
    private PushButton _removeParameterButton;
    private TablePane _setParametersTablePane;
    private TableView _parameterTableView;
    private PushButton _cancelButton;
    private PushButton _backButton;
    private PushButton _forwardButton;
    private Label _dateChooserLabel;
    private Label _parametersChooserLabel;
    private Label _setParametersLabel;
    private HashMap<Integer, TablePane> _stepsTablePane;
    private HashMap<Integer, Label> _stepsLabel;
    private int _selectedStep;
    private CalendarButton _startDate;
    private CalendarButton _endDate;
    private List<DietParameterData> _parameters;
    private HashMap<String, DietParameterData> _chosenParameterNames;
    private List<DietParameterData> _chosenParameters;
    private List<Double> _parameterMinValues;
    private List<Double> _parameterMaxValues;

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        _selectedStep = 0;
        
        // init lists
        _chosenParameterNames = new HashMap<String, DietParameterData>();
        _chosenParameters = new ArrayList<DietParameterData>();
        _parameterMaxValues = new ArrayList<Double>();
        _parameterMinValues = new ArrayList<Double>();

        // get GUI components
        _dateChooserTablePane = (TablePane) namespace.get("dateChooserTablePane");
        _startDate = (CalendarButton) namespace.get("startDate");
        _endDate = (CalendarButton) namespace.get("endDate");
        _parameterChooserTablePane = (TablePane) namespace.get("parameterChooserTablePane");
        _setParametersTablePane = (TablePane) namespace.get("setParametersTablePane");
        _parameterListView = (ListView) namespace.get("parameterListView");
        _chosenParameterListView = (ListView) namespace.get("chosenParameterListView");
        _addParameterButton = (PushButton) namespace.get("addParameterButton");
        _parameterTableView = (TableView) namespace.get("parameterTableView");
        _removeParameterButton = (PushButton) namespace.get("removeParameterButton");
        _cancelButton = (PushButton) namespace.get("cancelButton");
        _backButton = (PushButton) namespace.get("backButton");
        _forwardButton = (PushButton) namespace.get("forwardButton");
        _dateChooserLabel = (Label) namespace.get("dateChooserLabel");
        _parametersChooserLabel = (Label) namespace.get("parametersChooserLabel");
        _setParametersLabel = (Label) namespace.get("setParametersLabel");

        // store tablepanes and labels with step index
        _stepsTablePane = new HashMap<Integer, TablePane>();
        _stepsLabel = new HashMap<Integer, Label>();
        _stepsTablePane.put(0, _dateChooserTablePane);
        _stepsLabel.put(0, _dateChooserLabel);
        _stepsTablePane.put(1, _parameterChooserTablePane);
        _stepsLabel.put(1, _parametersChooserLabel);
        _stepsTablePane.put(2, _setParametersTablePane);
        _stepsLabel.put(2, _setParametersLabel);

        // disable back button for first step
        _backButton.setEnabled(false);

        // get all parameters and put into list
        _parameters = GUIController.getInstance().getAllParameters();
        _parameterListView.setListData(_parameters);
        _chosenParameterListView.setListData(_chosenParameters);

        // button listeners
        _forwardButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                setStep(_selectedStep, ++_selectedStep);
            }
        });

        _backButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                setStep(_selectedStep, --_selectedStep);
            }
        });

        // button listeners for adding and removing parameters
        _addParameterButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                DietParameterData param = (DietParameterData) _parameterListView.getSelectedItem();
                _chosenParameters.add(param);
                _chosenParameterNames.put(param.getParameterName(), param);
                _parameters.remove(param);
            }
        });

        _removeParameterButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                DietParameterData param = (DietParameterData) _chosenParameterListView.getSelectedItem();
                _parameters.add(param);
                _chosenParameters.remove(param);
                _chosenParameterNames.remove(param.getParameterName());
            }
        });

        // event for changing dates to validate them
        _startDate.getCalendarButtonSelectionListeners().add(new CalendarButtonSelectionListener() {

            public void selectedDateChanged(CalendarButton calendarButton, CalendarDate previousSelectedDate) {

                // get dates
                Date start = calendarButton.getSelectedDate().toCalendar().getTime();
                Date end = _endDate.getSelectedDate().toCalendar().getTime();

                // if endDate is now before this new start date, 
                // set end date to start date
                if (start.after(end)) {
                    _endDate.setSelectedDate(calendarButton.getSelectedDate());
                }
            }
        });
        _endDate.getCalendarButtonSelectionListeners().add(new CalendarButtonSelectionListener() {

            public void selectedDateChanged(CalendarButton calendarButton, CalendarDate previousSelectedDate) {

                // get dates
                Date start = _startDate.getSelectedDate().toCalendar().getTime();
                Date end = calendarButton.getSelectedDate().toCalendar().getTime();

                // if start date is now after this new end date, 
                // set start date to end date
                if (start.after(end)) {
                    _startDate.setSelectedDate(calendarButton.getSelectedDate());
                }
            }
        });
    }

    /**
     * Set active step and disable all other steps
     * @param lastStep Index of the last step which is actually active
     * @param nextStep Index of the next step
     */
    private void setStep(int lastStep, int nextStep) {

        // check if in range
        if (nextStep < _stepsLabel.getCount() && nextStep >= 0) {

            // go to next step
            _stepsTablePane.get(nextStep).setVisible(true);
            _stepsTablePane.get(lastStep).setVisible(false);

            // go to next step label color
            _stepsLabel.get(nextStep).getStyles().put("color", "#000000");
            _stepsLabel.get(lastStep).getStyles().put("color", "#BDBDBD");
        }

        // final step -> add parameter values
        if (_selectedStep == _stepsLabel.getCount() - 1) {
            updateParamTable();

            _forwardButton.setButtonData(new ButtonData("Fertigstellen"));
        } else {
            _forwardButton.setButtonData(new ButtonData("Weiter"));
        }

        // fist step -> back not available
        if (_selectedStep == 0) {
            _backButton.setEnabled(false);
        } else {
            _backButton.setEnabled(true);
        }

        // selected index is too high
        if (nextStep > _stepsLabel.getCount()) {
            _selectedStep = _stepsLabel.getCount() - 1;
        }

        // check if finish button is pressed
        if (nextStep == _stepsLabel.getCount()) {

            // get date
            Date start = _startDate.getSelectedDate().toCalendar().getTime();
            Date end = _endDate.getSelectedDate().toCalendar().getTime();

            // get parameter values
            ArrayList<HashMap<String, String>> map =
                    (ArrayList<HashMap<String, String>>) _parameterTableView.getTableData();

            // create max and min values list
            List<DietParameterData> params = new ArrayList<DietParameterData>();
            List<Double> maxValues = new ArrayList<Double>();
            List<Double> minValues = new ArrayList<Double>();
            for (HashMap<String, String> data : map) {
                params.add(_chosenParameterNames.get(data.get("parameter")));
                maxValues.add(Double.parseDouble(data.get("maximum").toString()));
                minValues.add(Double.parseDouble(data.get("minimum").toString()));
            }

            // process
            GUIController.getInstance().newDietryPlan(start, end, params, maxValues,
                    minValues);
            close();
        }
    }

    /**
     * Update table view for adding parameter values
     */
    private void updateParamTable() {
        ArrayList<HashMap<String, String>> tableData = new ArrayList<HashMap<String, String>>();

        for (DietParameterData d : _chosenParameters) {
            HashMap<String, String> row = new HashMap<String, String>();
            row.put("parameter", d.getParameterName());
            row.put("minimum", "0");
            row.put("maximum", "0");
            tableData.add(row);
        }

        _parameterTableView.setTableData(tableData);
    }
}
