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
import at.easydiet.teamb.application.handler.LaborReportHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.presentation.component.ListBox;
import at.easydiet.teamb.presentation.component.ListBoxRenderer;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.util.DateUtil;

/**
 * @author TeamB
 */
public class LaborTab extends AbstractLazyTab implements Bindable {
	@BXML
	private BoxPane _pane;
	private ListBox<LaborReportViewable> _list;
	
	private static final Logger LOGGER = Logger.getLogger(LaborTab.class);

	/**
	 * Instantiates a new labor tab.
	 */
	public LaborTab() {
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
	public void create() throws OperationNotPermittedException, ExitNotPermittedException {
		editStatus(null);
	}
	
	private void editStatus(LaborReportViewable report) throws ExitNotPermittedException, OperationNotPermittedException {
		if (_windowHandler.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}
		
		try {
			LaborReportCreateTab createTab = (LaborReportCreateTab) new BXMLSerializer().readObject(LaborReportCreateTab.class,
					"easydiet_tab_labor_create.bxml");

			if(report != null){
				createTab.setLaborReport(report);
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
	public void save() throws DatabaseException, ErrorInFormException, OperationNotPermittedException {
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
		
		_list = new ListBox();
		_list.setRenderer(new ListBoxRenderer<LaborReportViewable>() {
			
			@Override
			public void show(LaborReportViewable report) {
				try {
					editStatus(report);
				} catch (ExitNotPermittedException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (OperationNotPermittedException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			
			@Override
			public boolean delete(LaborReportViewable report) {
				LaborReportHandler handler = new LaborReportHandler(report);
				try {
					handler.remove();
					return true;
				} catch (DatabaseException ex) {
					LOGGER.error("Cannot remove LaborReport: " + report.toString(),ex);
				}
				return false;
			}

			@Override
			public String getName(LaborReportViewable report) {
				return DateUtil.CalendarToString(report.getDate(),"dd.MM.yy HH:mm");
			}
		});
		
		LaborReportViewable[] reports = WindowHandler.getWindowHandler().getSelectedPatient().getLaborReports();
		for(LaborReportViewable report : reports){
			_list.addViewobject(report);
		}
		
		_pane.add(_list);
	}
}
