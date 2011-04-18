/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: NewDietryPlanDialog.java
 */
package at.easydiet.teamc.view;

import java.net.URL;
import java.util.HashMap;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.content.ButtonData;

/**
 * Represents the dialog to add a new dietry plan
 * Timespan -> Choose Parameters -> Add Parameter values
 * @author Michael
 */
public class NewDietryPlanDialog extends Dialog implements Bindable {

    // instance variables
    private TablePane _dateChooserTablePane;
    private TablePane _parameterChooserTablePane;
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

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        _selectedStep = 0;

        // get GUI components
        _dateChooserTablePane = (TablePane) namespace.get("dateChooserTablePane");
        _parameterChooserTablePane = (TablePane) namespace.get("parameterChooserTablePane");
        _setParametersTablePane = (TablePane) namespace.get("setParametersTablePane");
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

        // disabla back button for first step
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
            close();
        }
    }
}
