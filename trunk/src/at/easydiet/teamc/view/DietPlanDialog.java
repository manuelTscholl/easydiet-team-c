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
import java.util.HashMap;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
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
    private List<DietParameterData> _chosenParameters;
    private List<Double> _parameterMinValues;
    private List<Double> _parameterMaxValues;

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        _selectedStep = 0;

        // get GUI components
        _dateChooserTablePane = (TablePane) namespace.get("dateChooserTablePane");
        _startDate = (CalendarButton) namespace.get("startDate");
        _endDate = (CalendarButton) namespace.get("endDate");
        _parameterChooserTablePane = (TablePane) namespace.get("parameterChooserTablePane");
        _setParametersTablePane = (TablePane) namespace.get("setParametersTablePane");
        _parameterListView = (ListView) namespace.get("parameterListView");
        _chosenParameterListView = (ListView) namespace.get("chosenParameterListView");
        _addParameterButton = (PushButton) namespace.get("addParameterButton");
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
                //TODO implement
            }
        });
        
        _removeParameterButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                //TODO implement
            }
        });
        
        // get all parameters
        List<DietParameterData> parameters = GUIController.getInstance().getAllParameters();
        _parameterListView.setListData(parameters);
    }

    /**
     * Set active step and disable all other steps
     * @param lastStep Index of the last step which is actually active
     * @param nextStep Index of the next step
     */
    private void setStep(int lastStep, int nextStep) {

        // check if in range
        if (nextStep < _stepsLabel.size() && nextStep >= 0) {

            // go to next step
            _stepsTablePane.get(nextStep).setVisible(true);
            _stepsTablePane.get(lastStep).setVisible(false);

            // go to next step label color
            _stepsLabel.get(nextStep).getStyles().put("color", "#000000");
            _stepsLabel.get(lastStep).getStyles().put("color", "#BDBDBD");
        }

        // final step
        if (_selectedStep == _stepsLabel.size() - 1) {
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
        if (nextStep > _stepsLabel.size()) {
            _selectedStep = _stepsLabel.size() - 1;
        }

        // check if finish button is pressed
        if(nextStep == _stepsLabel.size()){
            
            // get date
            Date start = _startDate.getSelectedDate().toCalendar().getTime();
            Date end = _endDate.getSelectedDate().toCalendar().getTime();
            
            // process
            GUIController.getInstance().newDietryPlan(start, end, _chosenParameters, _parameterMaxValues,
                    _parameterMinValues);
            close();
        }
    }
}
