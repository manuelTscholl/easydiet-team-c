/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

package at.easydiet.teamb.presentation.component;

import org.apache.pivot.wtk.TextArea;

/**
 * A Pivot NullableTextArea that supports setText(null)
 * 
 * @author TeamB
 */
public class NullableTextArea extends TextArea {
	@Override
	public void setText(String text) {
		if (text == null) {
			super.setText("");
		} else {
			super.setText(text);
		}
	}
	
	@Override
	public String getText() {
		if (getCharacterCount() <= 0) {
			return null;
		} else {
			return super.getText();
		}
	}
}
