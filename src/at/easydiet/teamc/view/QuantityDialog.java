/**
 * This File is part of EasyDiet
 * Created on: 26.04.2011
 * Created by: Michael Sieber
 * File: QuantityDialog.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.view.util.QuantityListener;
import java.net.URL;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;

/**
 *
 * @author Michael Sieber
 */
public class QuantityDialog extends Dialog implements Bindable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(QuantityDialog.class);
    // instance variables
    private float _quantity;
    private TextInput _quantityTextInput;
    private PushButton _okButton;
    private List<QuantityListener> _listeners;
    
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        _listeners = new ArrayList<QuantityListener> ();
        
        // get GUI components
        _quantityTextInput = (TextInput)namespace.get("quantityTextInput");
        _okButton = (PushButton)namespace.get("okButton");
        
        // add button listener
        _okButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                _quantity = Float.parseFloat(_quantityTextInput.getText());
                notifyListeners();
            }
        });
        
        // add listener for enter key
        _quantityTextInput.getComponentKeyListeners().add(new ComponentKeyListener() {

            public boolean keyTyped(Component component, char character) {
                // not needed
                return true;
            }

            public boolean keyPressed(Component component, int keyCode, KeyLocation keyLocation) {
                final int enterKey = 10;
                
                // check for pressing enter
                if(keyCode == enterKey){
                    _okButton.press();
                }
                return true;
            }

            public boolean keyReleased(Component component, int keyCode, KeyLocation keyLocation) {
                // not needed
                return true;
            }
        });
    }

    public float getQuantity(){
        return _quantity;
    }
    
    /**
     * Add a new listener
     * @param q 
     */
    public void addQuantityListener(QuantityListener q){
        _listeners.add(q);
    }
    
    /**
     * Notify all listeners
     */
    private void notifyListeners(){
        for(QuantityListener q:_listeners){
            q.updateQuantity(_quantity);
        }
    }
}
