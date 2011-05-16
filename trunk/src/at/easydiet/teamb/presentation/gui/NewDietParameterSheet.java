package at.easydiet.teamb.presentation.gui;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.MessageType;

import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.ParameterErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.presentation.component.sheet.NewParameterContentBorder;
import at.easydiet.teamb.presentation.util.Parameterizable;

/**
 * @author TeamB
 */
public class NewDietParameterSheet extends AbstractSheet implements EventListener<ValidatorArgs<ParameterErrorField>> {

	private static Logger LOGGER = Logger.getLogger(NewDietParameterSheet.class);

	private NewParameterContentBorder _content;
	private Parameterizable _parameterizable;

	/**
	 * Lets you create a new parameter.
	 *
	 * @param parameterizable the parameterizable
	 */
	public NewDietParameterSheet(Parameterizable parameterizable) {
		_parameterizable = parameterizable;

		// get content for abstract sheet
		BXMLSerializer bxmlSerializer = new BXMLSerializer();

		_content = null;
		try {
			_content = (NewParameterContentBorder) bxmlSerializer.readObject(NewDietParameterSheet.class, "easydiet_popup_new_parameter.bxml");
		} catch (IOException ex) {
			LOGGER.warn("Can not read 'easydiet_popup_new_parameter.bxml'", ex);
		} catch (SerializationException ex) {
			LOGGER.warn("Can not read 'easydiet_popup_new_parameter.bxml'", ex);
		}

		_content.getParameterHandler().addValidadedListener(this);
		
		// set content
		setMainContent(_content);

		addButtonPressListenerToSaveButton(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				LOGGER.debug("pressed Save button");

				try {
					_parameterizable.addParameter(_content.getParameterHandler());
					_isAllowedToClose = true;
					close();
				} catch (ErrorInFormException ex) {
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
	public void fired(Object sender, ValidatorArgs<ParameterErrorField> eventObject) {
		if (eventObject.getErrorFields().isEmpty()) {
			_messageLabel.setText("");
		} else {
			StringBuilder sb = new StringBuilder();
			for (ParameterErrorField errorField : eventObject.getErrorFields()) {
				String field = null;
				switch (errorField) {
					case TYPE:
						field = "Parametertyp";
						break;
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