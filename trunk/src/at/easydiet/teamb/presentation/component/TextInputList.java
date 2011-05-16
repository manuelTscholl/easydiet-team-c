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
public class TextInputList extends BoxPane {
	private static final Logger LOGGER = Logger.getLogger(TextInputList.class);

	private Set<String> _contents;
	private int _nextIndex = 0;

	/**
	 * Instantiates a new text input list.
	 */
	public TextInputList() {

	}

	/**
	 * Gets the contents.
	 *
	 * @return the contents
	 */
	public Set<String> getContents() {
		//TODO: update set, if it is possible
		return _contents;
	}

	/**
	 * Sets the contents.
	 *
	 * @param contents the new contents
	 */
	public void setContents(Set<String> contents) {
		_contents = contents;

		refresh();
	}

	protected void refresh() {
		removeAll();
		_nextIndex = 0;

		if (_contents != null) {
			for (String s : _contents) {
				add(s);
			}
		}
	}

	private void add(String s) {
		add(new ContentLine(_nextIndex++, s));
	}
	
	/**
	 * Adds the empty.
	 */
	public void addEmpty() {
		add("");
		_contents.add("");
	}

	/**
	 * Removes the.
	 *
	 * @param index the index
	 */
	public void remove(int index) {
		
		Iterator<String> setIter = _contents.iterator();

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
			ContentLine line = (ContentLine)get(i);
			line.setIndex(i);
		}
		
	}

	private class ContentLine extends BoxPane implements ButtonPressListener {
		private int _index;

		public ContentLine(int index, String value) {
			_index = index;

			TextInput input = new TextInput();
			input.setText(value);

			PushButton button = new PushButton("-");
			button.getButtonPressListeners().add(ContentLine.this);

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

		/**
		 * Sets the index.
		 *
		 * @param index the new index
		 */
		public void setIndex(int index) {
			_index = index;
		}

		@Override
		public void buttonPressed(Button button) {
			TextInputList.this.remove(_index);
		}

	}
}
