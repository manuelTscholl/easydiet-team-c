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
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
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
public class TreatmentTab extends AbstractLazyTab implements Bindable {

	private static final Logger LOGGER = Logger.getLogger(PatientStatusTab.class);

	@BXML
	private BoxPane _pane;
	
	private ListBox<DietTreatmentViewable> _list;  

	/**
	 * Instantiates a new treatment tab.
	 */
	public TreatmentTab() {
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
		if (_windowHandler.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}
		throw new OperationNotPermittedException();
	}
	
	@Override
	public void display(WindowHandler windowHandler) throws NoPatientSelectedException {
		super.display(windowHandler);
		
		if (_windowHandler.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}
		
		_list = new ListBox<DietTreatmentViewable>();
		_list.setRenderer(new ListBoxRenderer<DietTreatmentViewable>() {
			
			@Override
			public void show(DietTreatmentViewable dietTreatmentViewable) {
				//TODO
			}
			
			@Override
			public boolean delete(DietTreatmentViewable dietTreatmentViewable) {
				//TODO
				return false;
			}

			@Override
			public String getName(DietTreatmentViewable treatment) {
				return treatment.getName() + " - " + DateUtil.CalendarToString(treatment.getStart(), "dd.MM.yy") + " - " + treatment.getDuration() + " Tage";
			}
		});
		
		DietTreatmentViewable[] treatments = _windowHandler.getSelectedPatient().getTreatments();
		for(DietTreatmentViewable treatment : treatments){
			_list.addViewobject(treatment);
		}
		
		_pane.add(_list);
	}
}
