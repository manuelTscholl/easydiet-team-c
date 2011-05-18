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

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.MessageType;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.PatientStateHandler;
import at.easydiet.teamb.application.handler.UseCaseManager;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.presentation.component.ListBox;
import at.easydiet.teamb.presentation.component.ListBoxRenderer;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.util.DateUtil;

/**
 * The Class TreatmentTab.
 *
 * @author TeamB
 */
public class PatientStatusTab extends AbstractLazyTab implements Bindable {

	private static final Logger LOGGER = Logger.getLogger(PatientStatusTab.class);

	@BXML
	private BoxPane _pane;
	private ListBox<PatientStateViewable> _list;

	/**
	 * Instantiates a new treatment tab.
	 */
	public PatientStatusTab() {
		super();
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
	public void save() throws OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}

	@Override
	public void create() throws ExitNotPermittedException, OperationNotPermittedException {
		editStatus(null);
	}
	
	private void editStatus(PatientStateViewable state) throws ExitNotPermittedException, OperationNotPermittedException {
		if (_useCaseManager.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}
		
		try {
			PatientStatusCreateTab createTab = (PatientStatusCreateTab) new BXMLSerializer().readObject(PatientStatusCreateTab.class,
					"easydiet_tab_patientStatus_create.bxml");
			
			if(state != null){
				createTab.setPatientState(state);
			}
			
			getLazyTab().changeContent(createTab);
			
		} catch (IOException ex) {
			LOGGER.error("Could not load bxml", ex);
			Alert.alert(MessageType.ERROR, "Es ist ein unbekannter Fehler aufgetreten", getWindow());
		} catch (SerializationException ex) {
			LOGGER.error("Could not load bxml", ex);
			Alert.alert(MessageType.ERROR, "Es ist ein unbekannter Fehler aufgetreten", getWindow());
		}
	}
	
	@Override
	public void display(UseCaseManager useCaseManager) throws NoPatientSelectedException {
		super.display(useCaseManager);
		
		if (_useCaseManager.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}
		
		_list = new ListBox<PatientStateViewable>();
		_list.setRenderer(new ListBoxRenderer<PatientStateViewable>() {
			
			@Override
			public void show(PatientStateViewable patientStateViewable) {
				try {
					editStatus(patientStateViewable);
				} catch (ExitNotPermittedException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (OperationNotPermittedException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			
			@Override
			public boolean delete(PatientStateViewable state) {
				PatientStateHandler handler = new PatientStateHandler(state);
				try {
					handler.removeState();
					return true;
				} catch (DatabaseException ex) {
					LOGGER.error("Cannot remove State: " + state.toString(),ex);
				}
				return false;
			}

			@Override
			public String getName(PatientStateViewable obj) {
				PatientStateViewable state = (PatientStateViewable)obj;
				return state.getType().getName() + " - " + DateUtil.CalendarToString(state.getDate(),"dd.MM.yy HH:mm");
			}
		});
		
		PatientStateViewable[] states = _useCaseManager.getSelectedPatient().getPatientStates();
		for(PatientStateViewable state : states){
			_list.addViewobject(state);
		}
		
		_pane.add(_list);
	}
}
