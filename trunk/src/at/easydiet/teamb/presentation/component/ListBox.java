/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	13.05.2011
 */

package at.easydiet.teamb.presentation.component;

import org.apache.log4j.Logger;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseListener;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Column;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.presentation.gui.tab.PatientStatusTab;

public class ListBox<T> extends BoxPane {
	private static final Logger LOGGER = Logger.getLogger(ListBox.class);
	private ListBoxRenderer<T> _renderer;
	
	public ListBox() {
		super(Orientation.VERTICAL);
		
		getStyles().put("fill", true);
	}
	
	public void addViewobject(T object){
		if(_renderer != null){
			add(new ListBoxLine(object));
		}
	}
	
	public void setRenderer(ListBoxRenderer<T> renderer){
		_renderer = renderer;
	}
	
	public class ListBoxLine extends BoxPane {
		private T _object;
		
		private LinkButton _stateButton;
		private LinkButton _removeButton;
		
		public ListBoxLine(T object){
			super(Orientation.VERTICAL);
			getStyles().put("fill", true);
			
			TablePane tablePane = new TablePane();
			tablePane.getColumns().add(new Column(1, true));
			tablePane.getColumns().add(new Column(-1));
			
			_object = object;
			_stateButton = new LinkButton(_renderer.getName(object));

			try {
				_removeButton = new LinkButton(Image.load(PatientStatusTab.class.getResource("/gfx/icon/16x16px/remove.png")));
			} catch (TaskExecutionException e) {
				LOGGER.warn("Unable to load image and create remove button", e);
			}

			Row row = new Row();
			row.add(_stateButton);
			row.add(_removeButton);
			tablePane.getRows().add(row);
			add(tablePane);
			
			_removeButton.getButtonPressListeners().add(new ButtonPressListener() {
				
				@Override
				public void buttonPressed(Button button) {
					if (_renderer.delete(_object)) {
						ListBox.this.remove(ListBoxLine.this);
					}
				}
			});
			_stateButton.getButtonPressListeners().add(new ButtonPressListener() {
				
				@Override
				public void buttonPressed(Button button) {
					_renderer.show(_object);
				}
			});
			

			
			getComponentMouseListeners().add(new ComponentMouseListener.Adapter() {
				@Override
				public void mouseOver(Component component) {
					ListBoxLine.this.getStyles().put("backgroundColor", "#efefef");
				}
				
				@Override
				public void mouseOut(Component component) {
					ListBoxLine.this.getStyles().put("backgroundColor", "#ffffff");
				}
				
			});
		}
		
		public T getViewobject(){
			return _object;
		}
	}
}
