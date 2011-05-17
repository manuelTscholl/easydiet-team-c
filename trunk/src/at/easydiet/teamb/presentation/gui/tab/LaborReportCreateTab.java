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
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextAreaContentListener;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.LaborReportHandler;
import at.easydiet.teamb.application.handler.UseCaseLaborReportHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.EventArgs;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.LaborReportErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.LaborReportTypeViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.presentation.component.DateTimeInput;
import at.easydiet.teamb.presentation.component.DateTimeInputSelectionListener;
import at.easydiet.teamb.presentation.component.LaborParameterBox;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.presentation.util.Message;
import at.easydiet.teamb.presentation.util.MessageType;

/**
 * @author TeamB
 */
public class LaborReportCreateTab extends AbstractLazyTab implements Bindable,
		EventListener<ValidatorArgs<LaborReportErrorField>> {

	private static final Logger LOGGER = Logger
			.getLogger(LaborReportCreateTab.class);

	@BXML
	private ListButton _laborReportType = null;
	@BXML
	private BoxPane _dateTimeContainerBoxPane = null;
	@BXML
	private Border _laborParameterBorder = null;
	@BXML
	private TextArea _noticeTextArea = null;
	@BXML
	private PushButton _save = null;

	private boolean _updating;

	private DateTimeInput _dateTimeBoxPane = null;

	private UseCaseLaborReportHandler _useCaseHandler = null;
	private LaborReportHandler _reportHandler = null;

	private LaborReportViewable _laborReport = null;

	private LinkedList<Component> _lastErrors;

	private EventListener<EventArgs> _laborParameterListener;

	private LaborParameterBox _laborParameterBox;

	/**
	 * Instantiates a new labor create report tab.
	 */
	public LaborReportCreateTab() {
		_lastErrors = new LinkedList<Component>();

		_save.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button arg0) {
				try {
					save();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ErrorInFormException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void setLaborReport(LaborReportViewable report) {
		_useCaseHandler = new UseCaseLaborReportHandler(report);
		_reportHandler = _useCaseHandler.getLaborReportHandler();
		_laborReport = _reportHandler.getLaborReport();
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		_laborReportType.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof LaborReportTypeViewable) {
					LaborReportTypeViewable laborReportTypeViewable = (LaborReportTypeViewable) data;
					super.render(laborReportTypeViewable.getName(), button,
							highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_laborReportType.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView,
					boolean selected, boolean checked, boolean highlighted,
					boolean disabled) {
				if (item instanceof LaborReportTypeViewable) {
					LaborReportTypeViewable laborReportTypeViewable = (LaborReportTypeViewable) item;
					super.render(laborReportTypeViewable.getName(), index,
							listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked,
							highlighted, disabled);
				}
			}
		});
		_laborReportType.getListButtonSelectionListeners().add(
				new ListButtonSelectionListener.Adapter() {
					@Override
					public void selectedItemChanged(ListButton listButton,
							Object previousSelectedItem) {
						if (!_updating) {
							Object item = listButton.getSelectedItem();
							if (item instanceof LaborReportTypeViewable) {
								_reportHandler
										.setLaborReportType((LaborReportTypeViewable) item);
							} else {
								LOGGER.warn("expected datatype LaborReportTypeViewable, found "
										+ item.getClass());
							}
						}
					}
				});

		_noticeTextArea.getTextAreaContentListeners().add(
				new TextAreaContentListener.Adapter() {
					@Override
					public void textChanged(TextArea textArea) {
						if (_updating) {
							return;
						}

						if (textArea.getCharacterCount() <= 0) {
							_reportHandler.setNotice(null);
						} else {
							_reportHandler.setNotice(textArea.getText());
						}
					}
				});

		_dateTimeBoxPane = new DateTimeInput();
		_dateTimeBoxPane.getDateTimeInputSelectionListeners().add(
				new DateTimeInputSelectionListener() {

					@Override
					public void dateTimeChanged(DateTimeInput dateTimeInput) {
						if (!_updating) {
							_reportHandler.setDate(dateTimeInput.getDate());
						}
					}
				});
		_dateTimeContainerBoxPane.add(_dateTimeBoxPane);
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		_useCaseHandler.save();
		showInfoBar("Erfolgreich gespeichert!", MessageType.Info);

		getLazyTab().display(_windowHandler);
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		if (_useCaseHandler != null) {
			_useCaseHandler.discard();
		} else {
			throw new OperationNotPermittedException();
		}
	}

	@Override
	public void fired(Object sender,
			ValidatorArgs<LaborReportErrorField> eventObject) {

		LinkedList<Component> newErrors = new LinkedList<Component>();
		for (LaborReportErrorField laborReportErrorField : eventObject
				.getErrorFields()) {
			Component component = null;
			StringBuilder message = new StringBuilder("Das Feld ");
			switch (laborReportErrorField) {
			case DATETIME:
				component = _dateTimeBoxPane;
				message.append("Datum oder Uhrzeit");
				break;
			case NOPARAMETERS:
				component = _laborParameterBorder;
				message.append("Labor Parameter");
				break;
			default:
				LOGGER.error("unknown labor report error field");
				message.append(laborReportErrorField);
				break;
			}

			if (component != null) {
				_lastErrors.remove(component);
				newErrors.add(component);
				if (component == _dateTimeBoxPane) {
					message.append(" ist nicht gültig");
				} else {
					message.append(" darf nicht leer sein");
				}
				putMessage(new Message(MessageType.Error, component,
						message.toString()));
			}
		}

		// remove old errors for
		for (Component component : _lastErrors) {
			removeMessages(component);
		}

		_lastErrors = newErrors;

	}

	@Override
	public AbstractUseCaseHandler getHandler() {
		return _useCaseHandler;
	}

	@Override
	public void create() throws ExitNotPermittedException,
			OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}

	@Override
	public void display(WindowHandler windowHandler)
			throws NoPatientSelectedException {
		super.display(windowHandler);

		if (_windowHandler.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}

		if (_useCaseHandler == null) {
			_useCaseHandler = new UseCaseLaborReportHandler(
					_windowHandler.getCreator(),
					_windowHandler.getSelectedPatient());
			_reportHandler = _useCaseHandler.getLaborReportHandler();
			_laborReport = _reportHandler.getLaborReport();
		}

		_reportHandler.addValidadedListener(this);
		_reportHandler
				.addLaborReportChangedListener((_laborParameterListener = new LaborParameterListener()));

		update();
	}

	@Override
	public void remove() {
		super.remove();

		_laborParameterBox.remove();
		_laborParameterBox = null;

		if (_useCaseHandler != null) {
			_reportHandler.removeValidadedListener(this);
			_reportHandler
					.removeLaborReportChangedListener(_laborParameterListener);
			_reportHandler = null;
			_useCaseHandler = null;
		}
	}

	/**
	 * Update.
	 */
	public void update() {
		_updating = true;
		_laborReportType.setListData(new ArrayList<LaborReportTypeViewable>(
				_reportHandler.getParameterTypes()));

		if (_laborParameterBox != null) {
			_laborParameterBox.remove();
		}
		_laborParameterBorder
				.setContent((_laborParameterBox = new LaborParameterBox(this,
						_reportHandler)));
		_laborReportType.setSelectedItem(_reportHandler.getLaborReport()
				.getType());
		_dateTimeBoxPane.setDate(_laborReport.getDate());

		_updating = false;
	}

	private class LaborParameterListener implements EventListener<EventArgs> {

		@Override
		public void fired(Object sender, EventArgs eventObject) {
			_laborParameterBox.remove();
			_laborParameterBorder
					.setContent((_laborParameterBox = new LaborParameterBox(
							LaborReportCreateTab.this, _reportHandler)));
		}
	}
}