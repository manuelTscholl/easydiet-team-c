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
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TableView;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.presentation.gui.NewDietPlanSheet;

/**
 * The Class DietPlanTab.
 */
public class DietPlanTab extends AbstractLazyTab implements Bindable {
	private static Logger LOGGER = Logger.getLogger(DietPlanTab.class);

	// TODO: when error box in abstracttab, move this also to abstract tab (scrollbars are needed everywhere)
	@BXML
	private BoxPane _content;

	// Error Box
	// TODO: find reusable way for this implementation (AbstractTab?)
	@BXML
	private Border _errorHeaderBorder = null;
	@BXML
	private TablePane.Row _errorTableRow = null;
	@BXML
	private Label _errorHeaderInfoLabel = null;
	private boolean _errorBoxIsOpen = false;
	@BXML
	private TableView _errorTableView = null;
	private ArrayList<HashMap<String, String>> _errorEntries = new ArrayList<HashMap<String, String>>();

	private Component _currentContent;

	private NewDietPlanSheet _newDietPlanSheet;

	private DietPlanComponent _currentComponent;

	/**
	 * Instance of diet plan tab
	 */
	public DietPlanTab() {

		// TODO: remove dummy data for error list
		for (int i = 0; i < 100; i++) {
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put("error", "Error" + i);

			_errorEntries.add(entry);
		}

		setErrorTableData(_errorEntries);
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
	}

	@Override
	public AbstractUseCaseHandler getHandler() {
		// TODO create handler for this view
		return null;
	}

	@Override
	public void create() {
		if (_newDietPlanSheet == null) {
			_newDietPlanSheet = new NewDietPlanSheet(this);
		}
		_newDietPlanSheet.open(getWindow());
	}

	/**
	 * Change content.
	 *
	 * @param component the component
	 */
	public void changeContent(DietPlanComponent component) {
		super.setTabContent(component);
		_currentComponent = component;
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		if (_currentComponent != null) {
			_currentComponent.save();
		}
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		// TODO Auto-generated method stub
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
