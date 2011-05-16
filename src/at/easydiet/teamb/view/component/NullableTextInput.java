/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

package at.easydiet.teamb.view.component;

import org.apache.pivot.wtk.TextInput;

/**
 * Basic Pivot TextInput that supports setText(null)
 * 
 * @author TeamB
 */
public class NullableTextInput extends TextInput {
	@Override
	public void setText(String text) {
		if (text == null) {
			super.setText("");
		} else {
			super.setText(text);
		}
	}
}
