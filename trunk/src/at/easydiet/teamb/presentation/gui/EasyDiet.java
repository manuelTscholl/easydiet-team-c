/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	12.04.2011
 */

package at.easydiet.teamb.presentation.gui;

import java.awt.Dimension;
import java.util.Locale;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Theme;

import at.easydiet.teamb.presentation.component.MainTabPane;
import at.easydiet.teamb.presentation.component.skin.MainTabPaneSkin;

/**
 * The Class EasyDiet.
 */
public class EasyDiet implements Application {

	private EasyDietWindow _window = null;

	@Override
	public void startup(Display display, Map<String, String> properties) throws Exception {
		Locale.setDefault(Locale.ENGLISH);
		//define for the main TabPane our custom skin
		Theme.getTheme().set(MainTabPane.class, MainTabPaneSkin.class);
		
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        
        //fall back to AWT to set the minimum size of the window
        display.getHostWindow().setMinimumSize(new Dimension(1020, 700));
        display.getHostWindow().setSize(1020, 700);
        
        _window = (EasyDietWindow)bxmlSerializer.readObject(EasyDietWindow.class, "easydiet_window.bxml");
        _window.open(display);
        _window.startup();
	}

	@Override
	public boolean shutdown(boolean optional) {
		if (_window != null) {
			_window.close();
		}

		return false;
	}
	
	@Override
	public void resume() throws Exception {	}

	@Override
	public void suspend() throws Exception { }
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
        DesktopApplicationContext.main(EasyDiet.class, args);
    }
}
