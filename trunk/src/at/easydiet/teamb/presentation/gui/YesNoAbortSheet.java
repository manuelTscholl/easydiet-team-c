/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */

package at.easydiet.teamb.presentation.gui;

import org.apache.log4j.Logger;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.util.ExitOptions;
import at.easydiet.teamb.presentation.component.MealBox;

/**
 * The Class AbstractBoxPane.
 */
public class YesNoAbortSheet extends Sheet {

	protected static final Logger LOGGER = Logger.getLogger(MealBox.class);

	protected static final String DEFAULT_YES_TEXT = "Änderungen Speichern";
	protected static final String DEFAULT_NO_TEXT = "Änderungen Verwerfen";
	protected static final String DEFAULT_ABORT_TEXT = "Vorgang abbrechen";

	protected static final String DEFAULT_MESSAGE_TEXT = "Es sind nicht gespeicherte Änderungen Vorhanden. Wie wollen Sie fortfahren?";

	protected static final String DEFAULT_YES_ICON_LOCATION = "/gfx/icon/16x16px/accept.png";
	protected static final String DEFAULT_NO_ICON_LOCATION = "/gfx/icon/16x16px/remove.png";
	protected static final String DEFAULT_ABORT_ICON_LOCATION = "/gfx/icon/16x16px/delete.png";

	protected Label _messageLabel = null;
	protected LinkButton _yesButton = null;
	protected LinkButton _noButton = null;
	protected LinkButton _abortButton = null;

	protected EasyBar _easyBar = null;

	private Redo _redo;

	/**
	 * Defines an abstract sheet which is split into a main content and a bottom line. Bottom line contains a label, save and discard link buttons.
	 *
	 * @param easyBar the easy bar
	 * @param exitOptions the exit options
	 * @param redo the redo
	 */
	public YesNoAbortSheet(EasyBar easyBar, ExitOptions exitOptions, Redo redo) {
		_easyBar = easyBar;
		_redo = redo;

		getStyles().put("backgroundColor", "#ffffff");

		BoxPane container = new BoxPane(Orientation.VERTICAL);
		container.setWidth(350);
		container.setHeight(100);
		container.getStyles().put("padding", 10);
		container.getStyles().put("horizontalAlignment", "center");
		container.getStyles().put("verticalAlignment", "center");

		_messageLabel = new Label(DEFAULT_MESSAGE_TEXT);
		_messageLabel.getStyles().put("font", "24, bold");

		try {
			_yesButton = new LinkButton(new ButtonData(Image.load(YesNoAbortSheet.class.getResource(DEFAULT_YES_ICON_LOCATION)), DEFAULT_YES_TEXT));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			_yesButton = new LinkButton(new ButtonData(DEFAULT_YES_TEXT));
		}

		try {
			_noButton = new LinkButton(new ButtonData(Image.load(YesNoAbortSheet.class.getResource(DEFAULT_NO_ICON_LOCATION)), DEFAULT_NO_TEXT));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			_noButton = new LinkButton(new ButtonData(DEFAULT_NO_TEXT));
		}

		try {
			_abortButton = new LinkButton(new ButtonData(Image.load(YesNoAbortSheet.class.getResource(DEFAULT_ABORT_ICON_LOCATION)), DEFAULT_ABORT_TEXT));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			_abortButton = new LinkButton(new ButtonData(DEFAULT_ABORT_TEXT));
		}

		BoxPane buttons = new BoxPane();
		buttons.getStyles().put("padding", 15);

		if (exitOptions == ExitOptions.Save || exitOptions == ExitOptions.DiscardOrSave) {
			buttons.add(_yesButton);
		}
		if (exitOptions == ExitOptions.Discard || exitOptions == ExitOptions.DiscardOrSave) {
			buttons.add(_noButton);
		}
		
		buttons.add(_abortButton);

		container.add(_messageLabel);
		container.add(buttons);

		setContent(container);

		initLinkButtons();
	}

	/**
	 * Gets the message label
	 * 
	 * @return
	 */
	protected Label getMessage() {
		return _messageLabel;
	}

	/**
	 * Initialize button press listeners.
	 */
	private void initLinkButtons() {
		_yesButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				_easyBar.getSaveButton().press();
				close();
				fireRedo();
			}
		});
		_noButton.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				// TODO Auto-generated method stub
				_easyBar.getCancelButton().press();
				close();
				fireRedo();
			}
		});
		_abortButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				close();
			}
		});
	}

	protected void fireRedo() {
		if (_redo != null) {
			try {
				_redo.redo();
			} catch (ExitNotPermittedException ex) {
				LOGGER.debug("redo didn't work, try again...", ex);
				open(getDisplay());
			}
		}
	}

	/**
	 * The Interface Redo.
	 */
	public interface Redo {
		
		/**
		 * Redo.
		 *
		 * @throws ExitNotPermittedException the exit not permitted exception
		 */
		public void redo() throws ExitNotPermittedException;
	}
}
