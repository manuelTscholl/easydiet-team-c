/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	13.04.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;

/**
 * The Class ProtocolTab.
 *
 * @author TeamB
 */
public class ProtocolTab extends AbstractLazyTab implements Bindable {

	private static final Logger LOGGER = Logger.getLogger(ProtocolTab.class);

	/**
	 * Instantiates a new protocol tab.
	 */
	public ProtocolTab() {
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

	}

	@Override
	public AbstractUseCaseHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create() throws OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}

	@Override
	public void save() throws OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}
	
	@Override
	public void display(WindowHandler windowHandler) throws NoPatientSelectedException {
		super.display(windowHandler);
		
		if (_windowHandler.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}
	}
}
