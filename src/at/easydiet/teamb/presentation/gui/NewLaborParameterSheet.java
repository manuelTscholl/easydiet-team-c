/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

package at.easydiet.teamb.presentation.gui;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.MessageType;

import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.LaborParameterErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.presentation.component.LaborParameterBox;
import at.easydiet.teamb.presentation.component.sheet.NewLaborParameterContentBorder;


/**
 * The Class NewLaborParameterSheet.
 */
public class NewLaborParameterSheet extends AbstractSheet implements EventListener<ValidatorArgs<LaborParameterErrorField>> {
	private NewLaborParameterContentBorder _content = null;
	private LaborParameterBox _laborParameterBox = null;
	
	/**
	 * Instantiates a new new labor parameter sheet.
	 *
	 * @param laborParameterBox the labor parameter box
	 */
	public NewLaborParameterSheet(LaborParameterBox laborParameterBox) {
		_laborParameterBox = laborParameterBox;
		
		// get content for abstract sheet
		BXMLSerializer bxmlSerializer = new BXMLSerializer();

		_content = null;
		try {
			_content = (NewLaborParameterContentBorder) bxmlSerializer.readObject(NewDietParameterSheet.class, "easydiet_popup_new_labor_parameter.bxml");
		} catch (IOException ex) {
			LOGGER.warn("Can not read 'easydiet_popup_popup_new_labor_parameter.bxml'", ex);
		} catch (SerializationException ex) {
			LOGGER.warn("Can not read 'easydieasydiet_popup_new_labor_parameter.bxml'");
		}

		_content.getLaborParameterHandler().addValidadedListener(this);
		
		// set content
		setMainContent(_content);

		addButtonPressListenerToSaveButton(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				LOGGER.debug("pressed Save button");

				try {
					_laborParameterBox.addParameter(_content.getLaborParameterHandler());
					_isAllowedToClose = true;
					close();
				} catch (Exception ex) {
					_isAllowedToClose = false;

					LOGGER.debug("user hitted create though there are still errors: ", ex);
					Alert.alert(MessageType.ERROR, "Es sind noch fehler vorhanden!", getWindow());
				}
			}
		});

		addButtonPressListenerToDiscardButton(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				LOGGER.info("pressed Discard button");
				_isAllowedToClose = true;

				// discard if necessary

				close();
			}
		});
	}

	@Override
	public void fired(Object sender, ValidatorArgs<LaborParameterErrorField> eventObject) {
		if (eventObject.getErrorFields().isEmpty()) {
			_messageLabel.setText("");
		} else {
			StringBuilder sb = new StringBuilder();
			for (LaborParameterErrorField errorField : eventObject.getErrorFields()) {
				String field = null;
				switch (errorField) {
					case DEFINITION:
						field = "Name";
						break;
					case CHECKOPERATOR:
						field = "Vergleichsoperator";
						break;
					case VALUE:
						field = "Wert";
						break;
					case UNIT:
						field = "Mengeneinheit";
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
