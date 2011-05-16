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
import org.apache.pivot.util.Vote;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetStateListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Column;
import org.apache.pivot.wtk.TablePane.ColumnSequence;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.TablePane.RowSequence;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

/**
 * The Class AbstractBoxPane.
 */
public abstract class AbstractSheet extends Sheet {

	protected static final Logger LOGGER = Logger.getLogger(AbstractSheet.class);

	protected static final String DEFAULT_SAVE_TEXT = "Anlegen";
	protected static final String DEFAULT_DISCARD_TEXT = "Verwerfen";

	protected static final String DEFAULT_ADD_ICON_LOCATION = "/gfx/icon/16x16px/add.png";
	protected static final String DEFAULT_REMOVE_ICON_LOCATION = "/gfx/icon/16x16px/remove.png";

	protected Row _row = null;
	protected Label _messageLabel = null;
	protected LinkButton _saveButton = null;
	protected LinkButton _discardButton = null;

	protected boolean _isAllowedToClose = false;

	/**
	 * Defines an abstract sheet which is split into a main content and a bottom line. Bottom line contains a label, save and discard link buttons.
	 */
	public AbstractSheet() {
		getStyles().put("backgroundColor", "#ffffff");

		BoxPane container = new BoxPane();
		container.getStyles().put("verticalAlignment", "bottom");

		// define table
		TablePane tablePane = new TablePane();
		ColumnSequence columns = tablePane.getColumns();
		columns.add(new Column());

		RowSequence rows = tablePane.getRows();
		_row = new Row();
		rows.add(_row);
		rows.add(new Row(-1));

		// define bottom line
		BoxPane bottomLine = new BoxPane();
		bottomLine.getStyles().put("padding", "10");
		bottomLine.getStyles().put("horizontalAlignment", HorizontalAlignment.RIGHT);
		bottomLine.getStyles().put("verticalAlignment", VerticalAlignment.CENTER);

		// create and insert elements into bottom line
		_messageLabel = new Label();

		try {
			_saveButton = new LinkButton(new ButtonData(Image.load(AbstractSheet.class.getResource(DEFAULT_ADD_ICON_LOCATION)), DEFAULT_SAVE_TEXT));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			_saveButton = new LinkButton(new ButtonData(DEFAULT_SAVE_TEXT));
		}

		try {
			_discardButton = new LinkButton(new ButtonData(Image.load(AbstractSheet.class.getResource(DEFAULT_REMOVE_ICON_LOCATION)), DEFAULT_DISCARD_TEXT));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			_discardButton = new LinkButton(new ButtonData(DEFAULT_DISCARD_TEXT));
		}

		bottomLine.add(_messageLabel);
		bottomLine.add(_saveButton);
		bottomLine.add(_discardButton);

		rows.get(1).add(bottomLine);

		container.add(tablePane);
		setContent(container);

		getComponentKeyListeners().add(new ComponentKeyListener.Adapter() {

			@Override
			public boolean keyPressed(Component component, int keyCode, KeyLocation keyLocation) {
				if (keyCode == Keyboard.KeyCode.ESCAPE) {
					_discardButton.press();
				}
				if (keyCode == Keyboard.KeyCode.ENTER) {
					_saveButton.press();
				}

				return false;
			}
		});

		getSheetStateListeners().add(new SheetStateListener.Adapter() {

			@Override
			public void sheetClosed(Sheet sheet) {
				_isAllowedToClose = false;
			}

			@Override
			public void sheetCloseVetoed(Sheet sheet, Vote reason) {
				if (reason == Vote.APPROVE) {
					LOGGER.debug("_isAllowedToClose: " + _isAllowedToClose);
					close();
				}
			}

			@Override
			public Vote previewSheetClose(Sheet sheet, boolean result) {
				LOGGER.debug("_isAllowedToClose: " + _isAllowedToClose);
				if (!_isAllowedToClose) {
					return Vote.DENY;
				} else {
					return Vote.APPROVE;
				}
			}
		});
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
	 * Set a content to the main content
	 * 
	 * @param component
	 *            component which is set to main content
	 */
	protected void setMainContent(Component component) {
		if (_row.getLength() > 0) {
			_row.remove(0, _row.getLength());
		}

		_row.add(component);
	}

	/**
	 * Add button press listener to save button
	 * 
	 * @param buttonPressListener
	 *            which is added
	 */
	public void addButtonPressListenerToSaveButton(ButtonPressListener buttonPressListener) {
		_saveButton.getButtonPressListeners().add(buttonPressListener);
	}

	/**
	 * Add button press listener to discard button
	 * 
	 * @param buttonPressListener
	 *            which is added
	 */
	public void addButtonPressListenerToDiscardButton(ButtonPressListener buttonPressListener) {
		_discardButton.getButtonPressListeners().add(buttonPressListener);
	}

	/**
	 * Checks if is allowed to close.
	 *
	 * @return true, if is allowed to close
	 */
	public boolean isAllowedToClose() {
		return _isAllowedToClose;
	}

	/**
	 * Force close.
	 */
	public void forceClose() {
		_isAllowedToClose = true;
		close();
	}
}
