/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.DefaultProperty;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.json.JSON;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Mouse.Button;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.ScrollPane.ScrollBarPolicy;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Column;
import org.apache.pivot.wtk.TablePane.ColumnSequence;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.TablePane.RowSequence;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.content.TableViewCellRenderer;
import org.apache.pivot.wtk.content.TableViewImageCellRenderer;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.UseCaseManager;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.presentation.component.MessageBar;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.util.Colors;
import at.easydiet.teamb.presentation.util.Message;
import at.easydiet.teamb.presentation.util.MessageType;

/**
 * The Class AbstractTab.
 */
@DefaultProperty("tabContent")
public abstract class AbstractTab extends Border {

	private static final Logger LOGGER = Logger.getLogger(PatientTab.class);

	// constants for error box
	private final static int DEFAULT_ERROR_HEADER_HEIGHT = 30;
	private final static int DEFAULT_ERROR_TABLE_HEIGHT = 120;

	private final static String DEFAULT_ERROR_HEADER_STRING = "Achtung! Es haben sich Fehler eingeschlichen.";
	private final static String DEFAULT_INFO_HEADER_STRING = "Es gibt Anmerkungen zur Eingabe";
	private final static String DEFAULT_DETAILS_HEADER_INFO_STRING = "Für weitere Details bitte hier klicken.";

	private final static String COLUMN_ICON_NAME = "icon";
	private final static String COLUMN_ERROR_NAME = "error";

	protected UseCaseManager _useCaseManager;

	/**
	 * scroll pane which contains main content
	 */
	private ScrollPane _scrollPane = null;

	/**
	 * table row for error box
	 */
	private Row _errorTableRow = new Row(DEFAULT_ERROR_HEADER_HEIGHT);

	/**
	 * error box which expands by clicking on the header
	 */
	private BoxPane _errorBox = null;

	/**
	 * table view which contains the errors
	 */
	private TableView _errorTableView = null;
	private Label _errorHeaderLabel;
	private Label _errorHeaderInfoLabel = null;

	private boolean _errorBoxIsOpen = false;

	private TreeMap<MessageType, HashSet<Message>> _messageMap;

	private HashMap<Component, Object> _originColorMap;

	/**
	 * Instantiates a new abstract box pane.
	 */
	public AbstractTab() {

		// set icon or text as tabData
		TabPane.setTabData(this, getButtonData());

		// define table
		TablePane tablePane = new TablePane();
		ColumnSequence columns = tablePane.getColumns();
		columns.add(new Column(1, true));

		RowSequence rows = tablePane.getRows();
		rows.add(new Row(1, true));
		rows.add(_errorTableRow);

		_scrollPane = new ScrollPane();
		_scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.FILL);
		_scrollPane.setVerticalScrollBarPolicy(ScrollBarPolicy.FILL_TO_CAPACITY);
		rows.get(0).add(_scrollPane);

		// define error box
		_errorBox = new BoxPane(Orientation.VERTICAL);

		// style errorBox
		_errorBox.getStyles().put("fill", "true");
		_errorBox.getStyles().put("padding", 0);
		_errorBox.getStyles().put("backgroundColor", "#fdfae0");

		// error header border - error box part 1
		Border errorHeaderBorder = new Border();
		errorHeaderBorder.setPreferredHeight(DEFAULT_ERROR_HEADER_HEIGHT);
		errorHeaderBorder.getStyles().put("color", "#ecd701");
		errorHeaderBorder.getStyles().put("backgroundColor", "#ffc800");

		// error description border - error box part 2
		Border errorDescriptionBorder = new Border();
		errorDescriptionBorder.getStyles().put("color", "#fdfae0");
		errorDescriptionBorder.getStyles().put("padding", 0);

		// Box which contains content of part 1
		BoxPane errorHeaderBoxPane = new BoxPane(Orientation.HORIZONTAL);
		errorHeaderBoxPane.setPreferredHeight(DEFAULT_ERROR_HEADER_HEIGHT);
		errorHeaderBoxPane.getStyles().put("padding", 2);
		errorHeaderBoxPane.getStyles().put("verticalAlignment", "center");
		errorHeaderBoxPane.getStyles().put("backgroundColor", "#ffe800");

		// Scroll pane which contains content of part 2
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPreferredHeight(DEFAULT_ERROR_TABLE_HEIGHT);
		scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.FILL);
		scrollPane.setVerticalScrollBarPolicy(ScrollBarPolicy.FILL_TO_CAPACITY);

		// content part 1
		try {
			ImageView errorHeaderImageView = new ImageView(Image.load(AbstractTab.class.getResource("/gfx/icon/16x16px/warning.png")));
			errorHeaderBoxPane.add(errorHeaderImageView);
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Can not load image", ex);
		}

		_errorHeaderLabel = new Label(DEFAULT_ERROR_HEADER_STRING);
		_errorHeaderInfoLabel = new Label(DEFAULT_DETAILS_HEADER_INFO_STRING);

		_errorHeaderLabel.getStyles().put("font", "bold");
		_errorHeaderInfoLabel.getStyles().put("font", "normal italic");
		_errorHeaderInfoLabel.getStyles().put("color", "#555555");

		errorHeaderBoxPane.add(_errorHeaderLabel);
		errorHeaderBoxPane.add(_errorHeaderInfoLabel);
		_errorHeaderLabel.setVisible(true);

		// content part 2
		_errorTableView = new TableView();
		_errorTableView.getStyles().put("backgroundColor", "#fef7b2");
		_errorTableView.getStyles().put("alternateRowBackgroundColor", "#fdfae0");
		_errorTableView.getStyles().put("selectionBackgroundColor", "#ffa800");
		_errorTableView.getStyles().put("horizontalGridColor", "#ffffff");
		_errorTableView.getStyles().put("verticalGridColor", "#ffffff");

		_errorTableView.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter() {
			@Override
			public boolean mouseClick(Component component, Button button, int x, int y, int count) {
				if (button == Button.LEFT) {
					Object cellData = JSON.get(_errorTableView.getSelectedRow(), COLUMN_ERROR_NAME);
					if (cellData instanceof Message) {
						Component c = ((Message) cellData).getComponent();

						if (c != null) {
							c.requestFocus();
							c.scrollAreaToVisible(c.getBounds());
						}
					}

				}

				return false;
			}
		});

		TableView.ColumnSequence tableViewColumns = _errorTableView.getColumns();
		TableView.Column iconColumn = new TableView.Column(COLUMN_ICON_NAME);
		iconColumn.setCellRenderer(new TableViewImageCellRenderer());

		TableView.Column errorColumn = new TableView.Column(COLUMN_ERROR_NAME);
		errorColumn.setCellRenderer(new TableViewCellRenderer() {
			@Override
			public void render(Object row, int rowIndex, int columnIndex, TableView tableView, String columnName, boolean selected, boolean highlighted,
					boolean disabled) {

				Object cellData = JSON.get(row, columnName);
				if (cellData instanceof Message) {
					setText(((Message) cellData).getMessageText());
				} else {
					super.render(row, rowIndex, columnIndex, tableView, columnName, selected, highlighted, disabled);
				}
			}
		});

		iconColumn.setWidth(20);
		errorColumn.setWidth(1, true);

		tableViewColumns.add(iconColumn);
		tableViewColumns.add(errorColumn);

		scrollPane.setView(_errorTableView);

		// add part 1 and 2
		errorHeaderBorder.setContent(errorHeaderBoxPane);
		errorDescriptionBorder.setContent(scrollPane);

		// add error box parts
		_errorBox.add(errorHeaderBorder);
		_errorBox.add(errorDescriptionBorder);

		// add error box
		_errorTableRow.add(_errorBox);

		// add table
		setContent(tablePane);

		// initialize button press listeners
		errorHeaderBorder.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter() {
			@Override
			public boolean mouseClick(Component component, Button button, int x, int y, int count) {
				if (_errorBoxIsOpen) {
					collapse();
				} else {
					expand();
				}

				return false;
			}
		});

		_messageMap = new TreeMap<MessageType, HashSet<Message>>();
		_originColorMap = new HashMap<Component, Object>();

		refreshMessageBox();
	}

	private void collapse() {
		_errorBoxIsOpen = false;
		_errorTableRow.setHeight(DEFAULT_ERROR_HEADER_HEIGHT);

		_errorHeaderInfoLabel.setVisible(true);

	}

	private void expand() {
		_errorBoxIsOpen = true;

		int height = DEFAULT_ERROR_HEADER_HEIGHT + DEFAULT_ERROR_TABLE_HEIGHT + 5;
		_errorTableRow.setHeight(height);

		_errorHeaderInfoLabel.setVisible(false);
	}

	/**
	 * Required to set ButtonData to tab
	 * 
	 * @return ButtonData
	 */
	@Deprecated
	// this should be removed in all subclasses
	public ButtonData getButtonData() {
		return null;
	}

	/**
	 * Gets the handler.
	 * 
	 * @return the main Handler which controls this Component
	 */
	public abstract AbstractUseCaseHandler getHandler();

	/**
	 * Set the error list visible
	 * 
	 * @param visible
	 *            if set to true
	 */
	private void setErrorBoxVisible(boolean visible) {
		_errorBox.setVisible(visible);

		if (!visible) {
			collapse();
		}
	}

	/**
	 * Is error box visible
	 * 
	 * @return true if visible; false otherwise
	 */
	public boolean isErrorBoxVisible() {
		return _errorBox.isVisible();
	}

	/**
	 * Set main content
	 * 
	 * @param content
	 *            represents main content
	 */
	public void setTabContent(Component content) {
		_scrollPane.setView(content);
	}

	/**
	 * Set error table data. TableData can contain keys for "icon" and "error".
	 * 
	 * @param tableData
	 *            represents error table content
	 */
	@Deprecated
	public void setErrorTableData(List<?> tableData) {
		_errorTableView.setTableData(tableData);
		_errorTableView.repaint();
	}

	/**
	 * Gets the table data of the error box.
	 * 
	 * @return table data
	 */
	public List<?> getErrorTableData() {
		return _errorTableView.getTableData();
	}

	/**
	 * Creates the Object which depends on the Tab.
	 * 
	 * @throws ExitNotPermittedException
	 *             the exit not permitted exception
	 * @throws OperationNotPermittedException
	 *             the operation not permitted exception
	 */
	public abstract void create() throws ExitNotPermittedException, OperationNotPermittedException;

	/**
	 * Save the Object which depends on the Tab.
	 * 
	 * @throws DatabaseException
	 *             the database exception
	 * @throws ErrorInFormException
	 *             the error in form exception
	 * @throws OperationNotPermittedException
	 *             the operation not permitted exception
	 */
	public abstract void save() throws DatabaseException, ErrorInFormException, OperationNotPermittedException;

	/**
	 * Discard the Object which depends on the Tab.
	 * 
	 * @throws OperationNotPermittedException
	 *             the operation not permitted exception
	 */
	public abstract void discard() throws OperationNotPermittedException;

	/**
	 * Display the Object which depends on the Tab.
	 * 
	 * @param useCaseManager
	 *            the window handler
	 * @throws NoPatientSelectedException
	 *             the no patient selected exception
	 */
	public void display(UseCaseManager useCaseManager) throws NoPatientSelectedException {
		LOGGER.debug("displaying " + this);

		if (_useCaseManager != useCaseManager) {
			_useCaseManager = useCaseManager;
		}
	}

	/**
	 * Put message the Object which depends on the Tab.
	 * 
	 * @param message
	 *            the message
	 */
	public void putMessage(Message message) {
		HashSet<Message> messageSet;

		if (_messageMap.containsKey(message.getMessageType())) {
			messageSet = _messageMap.get(message.getMessageType());
		} else {
			messageSet = new HashSet<Message>();
			_messageMap.put(message.getMessageType(), messageSet);
		}

		messageSet.add(message);

		if (message.getComponent() != null) {
			if (!_originColorMap.containsKey(message.getComponent())) {
				_originColorMap.put(message.getComponent(), message.getComponent().getStyles().get("backgroundColor"));
			}
			message.getComponent().getStyles().put("backgroundColor", Colors.WARNING.getHexCode());
		}

		refreshMessageBox();
	}

	public void showInfoBar(String message, MessageType type) {
//		MessageBar messageBar = EasyBar.getCurrentInstance().getMessageBar();
//		messageBar.setMessage(message, type);
//		messageBar.start();
	}

	/**
	 * Removes a Message from the message list
	 * 
	 * @param message
	 *            the message that should be removed
	 */
	public void removeMessage(Message message) {
		if (_messageMap.containsKey(message.getMessageType())) {
			_messageMap.get(message.getMessageType()).remove(message);
		}

		if (message.getComponent() != null && _originColorMap.containsKey(message.getComponent())) {
			message.getComponent().getStyles().put("backgroundColor", _originColorMap.get(message.getComponent()));
			_originColorMap.remove(message.getComponent());
		}

		refreshMessageBox();
	}

	/**
	 * Refresh message box
	 */
	public void refreshMessageBox() {
		ArrayList<org.apache.pivot.collections.HashMap<String, Object>> messages = new ArrayList<org.apache.pivot.collections.HashMap<String, Object>>();

		String header = DEFAULT_INFO_HEADER_STRING;

		for (Entry<MessageType, HashSet<Message>> messageEntries : _messageMap.entrySet()) {
			for (Message messageEntry : messageEntries.getValue()) {
				org.apache.pivot.collections.HashMap<String, Object> entry = new org.apache.pivot.collections.HashMap<String, Object>();
				entry.put(COLUMN_ERROR_NAME, messageEntry);
				try {
					String icon = null;

					switch (messageEntry.getMessageType()) {
						case Error:
							icon = "error.png";
							break;
						case Info:
							icon = "info.png";
							break;
						case Warning:
							icon = "warning.png";
							break;
						default:
							LOGGER.error("Unknown messagetype: " + messageEntry.getMessageType());
							break;
					}

					if (icon != null) {
						entry.put(COLUMN_ICON_NAME, Image.load(AbstractTab.class.getResource("/gfx/icon/16x16px/" + icon)));
					}
				} catch (TaskExecutionException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

				messages.add(entry);
			}

			if (messageEntries.getKey() == MessageType.Error && !messageEntries.getValue().isEmpty()) {
				header = DEFAULT_ERROR_HEADER_STRING;
			}
		}

		_errorHeaderLabel.setText(header);
		_errorTableView.setTableData(messages);

		setErrorBoxVisible(!messages.isEmpty());
	}

	/**
	 * called before another tab gets displayed
	 */
	public void remove() {
		LOGGER.debug("removing " + this + " from display");
		_useCaseManager = null;
	}

    /**
     * @param component
     */
    public void removeMessages(Component component)
    {
        LOGGER.info("I söt was tua");
        //XXX wichtig
        
        
    }
}
