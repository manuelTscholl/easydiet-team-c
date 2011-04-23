/**
 * This File is part of Easy Diet
 * created on: 17.04.2011
 * created by: Michael
 * file: ChooseMealDialog.java
 */

package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Dialog;

/**
 * Represents the ChooseMealDialog.bxml
 * @author Michael
 */
public class ChooseMealDialog extends Dialog implements Bindable {
    
    // instance variables
    private int _day;

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        
    }
    
    /**
     * Add day for which this meal is for
     * @param day 
     */
    public void setDay(int day){
        _day = day;
    }

}
