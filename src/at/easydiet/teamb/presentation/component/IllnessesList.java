/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	17.04.2011
 */

package at.easydiet.teamb.presentation.component;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;

/**
 * @author TeamB
 */
public class IllnessesList extends BoxPane {
	private static final Logger LOGGER = Logger.getLogger(IllnessesList.class);

	private Set<String> _illnesses;
	private int _nextIndex = 0;

	/**
	 * Instantiates a new illnesses list.
	 */
	public IllnessesList() {

	}

	/**
	 * Gets the illnesses.
	 *
	 * @return the illnesses
	 */
	public Set<String> getIllnesses() {
		//TODO: update set, if it is possible
		return _illnesses;
	}

	/**
	 * Sets the illnesses.
	 *
	 * @param illnesses the new illnesses
	 */
	public void setIllnesses(Set<String> illnesses) {
		_illnesses = illnesses;

		refresh();
	}

	/**
	 * Refresh.
	 */
	protected void refresh() {
		removeAll();
		_nextIndex = 0;

		if (_illnesses != null) {
			for (String s : _illnesses) {
				add(s);
			}
		}
	}

	/**
	 * Adds the.
	 *
	 * @param s the s
	 */
	private void add(String s) {
		add(new IllnesseLine(_nextIndex++, s));
	}
	
	/**
	 * Adds the empty.
	 */
	public void addEmpty() {
		add("");
		_illnesses.add("");
	}

	/**
	 * Removes the.
	 *
	 * @param index the index
	 */
	public void remove(int index) {
		
		Iterator<String> setIter = _illnesses.iterator();

		int i;
		for (i = 0; i <= index; i++) {
			try {
				setIter.next();
			} catch (NoSuchElementException e) {
				LOGGER.error("Element at index " + index + " not found", e);
				return;
			}
		}

		setIter.remove();
		remove(index, 1);
		_nextIndex--;
		
		for (i = index; i < _nextIndex; i++) {
			IllnesseLine line = (IllnesseLine)get(i);
			line.setIndex(i);
		}
		
	}

	/**
	 * The Class IllnesseLine.
	 */
	private class IllnesseLine extends BoxPane implements ButtonPressListener {
		private int _index;

		public IllnesseLine(int index, String value) {
			_index = index;

			TextInput input = new TextInput();
			input.setText(value);

			PushButton button = new PushButton("-");
			button.getButtonPressListeners().add(IllnesseLine.this);

			add(input);
			add(button);
		}

		/**
		 * Gets the index.
		 *
		 * @return the index
		 */
		@SuppressWarnings("unused")
		public int getIndex() {
			return _index;
		}

		public void setIndex(int index) {
			_index = index;
		}

		@Override
		public void buttonPressed(Button button) {
			IllnessesList.this.remove(_index);
		}

	}
}
