package at.easydiet.teamb.presentation.gui;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Window;

import at.easydiet.teamb.application.handler.UseCaseCreateDietPlanHandler;
import at.easydiet.teamb.application.handler.UseCaseDietPlanHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.CreateDietplanErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.presentation.component.sheet.NewDietPlanContentBorder;
import at.easydiet.teamb.presentation.gui.tab.AbstractTab;
import at.easydiet.teamb.presentation.gui.tab.DietPlanCreateTab;
import at.easydiet.teamb.presentation.gui.tab.DietPlanTab;

/**
 * @author TeamB
 */
public class NewDietPlanSheet extends AbstractSheet implements EventListener<ValidatorArgs<CreateDietplanErrorField>> {

	private static Logger LOGGER = Logger.getLogger(NewDietPlanSheet.class);

	private WindowHandler _windowHandler;
	private UseCaseCreateDietPlanHandler _useCaseCreateDietPlanHandler;

	private NewDietPlanContentBorder _content;

	private AbstractTab _underlyingTab;

	/**
	 * Lets you create a new diet plan.
	 *
	 * @param underlyingTab the underlying tab
	 */
	public NewDietPlanSheet(AbstractTab underlyingTab) {
		_windowHandler = WindowHandler.getWindowHandler();

		_underlyingTab = underlyingTab;
		
		// get content for abstract sheet
		BXMLSerializer bxmlSerializer = new BXMLSerializer();

		try {
			_content = (NewDietPlanContentBorder) bxmlSerializer.readObject(NewDietPlanSheet.class, "easydiet_popup_new_diet_plan.bxml");
		} catch (IOException ex) {
			LOGGER.fatal("Can not read 'easydiet_popup_create_new_diet_plan.bxml'", ex);
		} catch (SerializationException ex) {
			LOGGER.fatal("Can not read 'easydiet_popup_create_new_diet_plan.bxml'", ex);
		}

		// set content
		setMainContent(_content);

		addButtonPressListenerToSaveButton(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				try {
					_useCaseCreateDietPlanHandler.setDietPlanName(_content.getDietPlanName());
					_useCaseCreateDietPlanHandler.setDietTreatment(_content.getDietTreatment());
					_useCaseCreateDietPlanHandler.setPlanType(_content.getDietPlanType());
					_useCaseCreateDietPlanHandler.setCreator(_windowHandler.getCreator());

					UseCaseDietPlanHandler ucdph = _useCaseCreateDietPlanHandler.getUseCaseDietPlanHandler();
					_windowHandler.changeHandler(ucdph);
					
					DietPlanCreateTab createTab = (DietPlanCreateTab) new BXMLSerializer().readObject(DietPlanCreateTab.class,
							"easydiet_tab_dietplan_create.bxml");

					createTab.setHandler(ucdph);

					// TODO bernhard change to abstract method
					((DietPlanTab) _underlyingTab).changeContent(createTab);

					_isAllowedToClose = true;
					close();
				} catch (ErrorInFormException e) {
					LOGGER.debug("user hitted create though there are still errors", e);

					// there are errors in the sheet
					Alert.alert(MessageType.ERROR, "Es sind noch fehler vorhanden!", getWindow());
				} catch (ExitNotPermittedException e) {
					LOGGER.error("unknown exit not permitted exception (should had be covered by ErrorInFormException)", e);
					Alert.alert(MessageType.ERROR, "Es sind unbekannter Fehler aufgetreten", getWindow());
				} catch (IOException e) {
					LOGGER.error("Could not load bxml", e);
					Alert.alert(MessageType.ERROR, "Es ist ein unbekannter Fehler aufgetreten", getWindow());
				} catch (SerializationException ex) {
					LOGGER.error("Could not load bxml", ex);
					Alert.alert(MessageType.ERROR, "Es ist ein unbekannter Fehler aufgetreten", getWindow());
				}
			}
		});

		addButtonPressListenerToDiscardButton(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				_useCaseCreateDietPlanHandler.discard();

				try {
					_windowHandler.changeHandler(_underlyingTab.getHandler());
					_isAllowedToClose = true;
					close();
				} catch (ExitNotPermittedException ex) {
					// this should never be happen because we did discard all changes before
					LOGGER.error("unexpected exitnotpermittedexception after discarding all changes", ex);
					Alert.alert(MessageType.ERROR, "Es ist ein unbekannter Fehler aufgetreten", getWindow());
				}
			}
		});
	}

	@Override
	public void open(final Display display, final Window owner, final SheetCloseListener sheetCloseListener) {
		//get a new handler (ensure that handlers are not reused)
		_useCaseCreateDietPlanHandler = new UseCaseCreateDietPlanHandler(_windowHandler.getSelectedPatient());
		_useCaseCreateDietPlanHandler.addValidatorListener(this);
		
		_content.reset();
		
		try {
			_windowHandler.changeHandler(_useCaseCreateDietPlanHandler);
			super.open(display, owner, sheetCloseListener);
		} catch (ExitNotPermittedException ex) {
			YesNoAbortSheet question = new YesNoAbortSheet(EasyBar.getCurrentInstance(), ex.getExitOptions(), new YesNoAbortSheet.Redo() {
				
				@Override
				public void redo() {
					open(display, owner, sheetCloseListener);
				}
			});
			question.open(_underlyingTab.getWindow());
		}
	}

	@Override
	public void fired(Object sender, ValidatorArgs<CreateDietplanErrorField> eventObject) {
		if (eventObject.getErrorFields().isEmpty()) {
			_messageLabel.setText("");
		} else {
			StringBuilder sb = new StringBuilder();
			for (CreateDietplanErrorField errorField : eventObject.getErrorFields()) {
				String field = null;
				switch (errorField) {
					case NAME:
						field = "Name";
						break;
					case TREATMENT:
						field = "Behandlung";
						break;
					case TYPE:
						field = "Diätplantyp";
						break;
					case PATIENT:
						field = "Patient";
						break;

					default:
						LOGGER.warn("unknown ErrorField Enum value");
						break;
				}
				
				if (field != null) {
					if (sb.length() != 0) {
						sb.append(", ");
					}
					sb.append(field);
				}
			}
			
			sb.insert(0, "Folgende Felder sind Fehlerhaft: ");
			_messageLabel.setText(sb.toString());
		}
	}
}