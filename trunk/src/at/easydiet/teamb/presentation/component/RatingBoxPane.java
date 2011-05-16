/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.presentation.component;

import java.util.LinkedList;

import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseListener;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.media.Image;

/**
 * Represents a rating box.
 * 
 * @author TeamB
 */
public class RatingBoxPane extends BoxPane {	
	
	private int _selectedIndex = 0;
	private int _maxElments = 0;
	
	private LinkedList<RatingBoxElement> _elements;
	
	/**
	 * Creates an instance of RatingBoxPane. Requires at least one element.
	 * @param maxElements 		requires at least one element.
	 * @param icon				displayed if an element is not hovered or selected.
	 * @param iconHover			displayed if an element is hovered.
	 * @param iconHighlighted	displayed if an element is selected.
	 */
	public RatingBoxPane(int maxElements, Image icon, Image iconHover, Image iconHighlighted) {
		if(maxElements < 1) {
			throw new IllegalArgumentException("RatingBoxPane: maxElemnt: Requires at least one element");
		}
		_maxElments = maxElements;
		_elements = new LinkedList<RatingBoxPane.RatingBoxElement>();
		
		for(int i = 0; i < _maxElments; i++) {
			RatingBoxElement element = new RatingBoxElement(i, icon, iconHover, iconHighlighted);
			_elements.add(element);
			add(element);
		}
		
		getComponentMouseListeners().add(new ComponentMouseListener.Adapter() {
						
			@Override
			public void mouseOut(Component component) {
				setSelectedIndex(_selectedIndex);				
			}
		});
	}
	
	/**
	 * Set index of selected element. Zero based.
	 * @param index of selected element. Zero based.
	 */
	public void setSelectedIndex(int index)  {
		if(index > _maxElments) {
			throw new IndexOutOfBoundsException("Index must be smaller than max elements");
		}
		_selectedIndex = index;

		// set elements highlighted up to selected index
		for(int i = 0; i < _selectedIndex; i++) {
			_elements.get(i).setHighlighted(true);
		}
		// unset highlighted status of the rest
		for(int j = _selectedIndex; j < _maxElments; j++) {
			_elements.get(j).setHighlighted(false);
		}
	}
	
	/**
	 * Set index of hovered element. Zero based.
	 * @param index of hovered element. Zero based.
	 */
	public void setHoverIndex(int index) {
		// set elements highlighted up to selected index
		for(int i = 0; i < _selectedIndex; i++) {
			_elements.get(i).setHighlighted(true);
		}
		// set elements hovered up to given index
		for(int j = _selectedIndex; j < index; j++) {
			_elements.get(j).setHover(true);
		}
		// unset highlighted status of the rest
		for(int k = index; k < _maxElments; k++) {
			_elements.get(k).setHover(false);
		}
	}
	
	/**
	 * Get selected index
	 * @return selected index
	 */
	public int getSelectedIndex() {
		return _selectedIndex;
	}
	
	/**
	 * Represents an element of the RatingBox
	 * 
	 * @author TeamB
	 */
	private class RatingBoxElement extends LinkButton {
		private int _value;
		private Image _icon;
		private Image _iconHover;
		private Image _iconHighlighted;
		
		// ESCA-JAVA0128:
		/**
		 * Represents an element of the RatingBoxPane
		 * @param value value of the element
		 * @param icon Image of the icon
		 * @param iconHover Image of the hovered icon
		 * @param iconHighlighted Image of the highlighted icon
		 */
		public RatingBoxElement(int value, Image icon, Image iconHover, Image iconHighlighted) {
			_value = value;
			_icon = icon;
			_iconHover = iconHover;
			_iconHighlighted = iconHighlighted;
			
			setButtonData(_icon);
			
			getButtonPressListeners().add(new ButtonPressListener() {
				
				@Override
				public void buttonPressed(Button arg0) {
					setHighlighted(true);
					RatingBoxPane.this.setSelectedIndex(_value + 1);
				}
			});
			
			getComponentMouseListeners().add(new ComponentMouseListener.Adapter() {
				
				@Override
				public void mouseOver(Component component) {
					setHover(true);
					RatingBoxPane.this.setHoverIndex(_value + 1);
				}
			});
		}
		
		/**
		 * Gets the current value of this element
		 * @return value
		 */
		@SuppressWarnings("unused")
		public int getValue() {
			return _value;
		}
		
		/**
		 * Set highlighted status.
		 * @param highlighted if true
		 */
		public void setHighlighted(boolean highlighted) {
			if(highlighted) {
				setButtonData(_iconHighlighted);
			} else {
				setButtonData(_icon);
			}
		}
		
		/**
		 * Set hovered status.
		 * @param hover if true
		 */
		public void setHover(boolean hover) {
			if(hover) {
				setButtonData(_iconHover);
			} else {
				setButtonData(_icon);
			}
		}
	}
}
