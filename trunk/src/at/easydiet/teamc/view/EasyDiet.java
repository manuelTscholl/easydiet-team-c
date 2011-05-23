/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: EasyDiet.java
 */
package at.easydiet.teamc.view;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.TablePane;

import at.easydiet.teamc.controller.GUIController;

/**
 * Represents the application's main entry point into the UI (main.xml)
 * 
 * @author Michael
 */
public class EasyDiet implements Application {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(EasyDiet.class);
	private static final String sTITLE;
	private static final String sMAIN_XML;
	public static final String LANGUAGE_KEY = "language";

	// instance variables
	private GUIController _guiController;
	private Map<String, Object> _ressources;

	static {
		sTITLE = "EasyDiet";
		sMAIN_XML = "bxml/main.bxml";
	}

	// instance variables
	private EasyDietWindow window;

	@Override
	/**
	 * Main entry point of the application
	 */
	public void startup(Display display, Map<String, String> properties)
			throws Exception {

		// Load the Pivot window
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		try {
			window = (EasyDietWindow) bxmlSerializer.readObject(EasyDiet.class
					.getResource(sMAIN_XML));
		} catch (IOException exception) {
			LOGGER.fatal(exception.toString());
			throw new RuntimeException(exception);
		} catch (SerializationException exception) {
			LOGGER.fatal(exception.toString());
			throw new RuntimeException(exception);
		}

		// Open the Pivot window on the display
		window.open(display);

		// load gui controller
		_guiController = GUIController.getInstance();

		// set title and icon
		window.setTitle(sTITLE);

		// get all gui components
		_ressources = window.getRessources();

		// add listener for resizing window
		window.getComponentListeners().add(new ComponentListenerAdapter() {

			@Override
			public void sizeChanged(Component component, int previousWidth,
					int previousHeight) {

				// resize width
				TablePane.Column tpc = (TablePane.Column) _ressources
						.get("mainTableColumn");
				tpc.setWidth(component.getWidth());

				// resize height
				TablePane.Row tpr = (TablePane.Row) _ressources
						.get("mainTableRow");
				tpr.setHeight(component.getHeight() - 20);

				// resize navigation tab and content tab
				NavigationTabPane tp = (NavigationTabPane) _ressources
						.get("navTab");
				tp.setPreferredHeight(component.getHeight() - 33);
				ContentTabPane cp = (ContentTabPane) _ressources
						.get("contentTab");
				cp.setPreferredHeight(component.getHeight() - 33);
			}
		});
	}

	@Override
	public boolean shutdown(boolean optional) throws Exception {
		if (window != null) {
			window.close();
		}

		return false;
	}

	@Override
	public void suspend() throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void resume() throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
