/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import org.apache.log4j.Logger;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.ProxyUseCaseHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.PatientChangedEventArg;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.presentation.gui.EasyBar;
import at.easydiet.teamb.presentation.gui.YesNoAbortSheet;
import at.easydiet.teamb.util.Initializator;

/**
 * Useful for decorating a AbstractTab. Creates every time a new instance of the containing Tab when it gets displayed. (Within the TabPane of Pivot, always the
 * same instance would be used)
 * 
 * @author TeamB
 */
public class LazyTab extends AbstractTab implements EventListener<PatientChangedEventArg> {
	private static final Logger LOGGER = Logger.getLogger(LazyTab.class);

	private Initializator<? extends AbstractTab> _initializator;
	private AbstractTab _tab;

	private ProxyUseCaseHandler _proxyHandler;

	/**
	 * Creates a new Instance of LazyTab
	 * 
	 * @param initializator
	 *            Used to get a new Instance of the containing AbstractTab when this tab should be displayed.
	 */
	public LazyTab(Initializator<? extends AbstractTab> initializator) {
		_initializator = initializator;

		getStyles().put("thickness", 0);
		getStyles().put("padding", 0);

		_proxyHandler = new ProxyUseCaseHandler();
	}

	@Override
	public void display(WindowHandler windowHandler) {
		changeContent(_initializator.createInstance(), windowHandler);
	}

	@Override
	public void fired(final Object sender, final PatientChangedEventArg eventObject) {
		if (_tab != null) {
			try {
				_windowHandler.changeHandler(getHandler());
			} catch (ExitNotPermittedException ex) {
				LOGGER.debug("Changing content failed", ex);

				new YesNoAbortSheet(EasyBar.getCurrentInstance(), ex.getExitOptions(), new YesNoAbortSheet.Redo() {

					@Override
					public void redo() throws ExitNotPermittedException {
						fired(sender, eventObject);
					}
				});
			}

			_tab.remove();

			display(_windowHandler);
		}
	}

	@Override
	public AbstractUseCaseHandler getHandler() {
		return _proxyHandler;
	}

	@Override
	public void create() throws ExitNotPermittedException, OperationNotPermittedException {
		if (_tab != null) {
			try {
				_tab.create();
			} catch (NoPatientSelectedException ex) {
				LOGGER.debug("creating new instacne without a selected patient not possible", ex);
				Alert.alert(MessageType.WARNING, "Es muss zuerst ein Patient ausgewählt sein!", getWindow());
			}
		} else {
			throw new OperationNotPermittedException();
		}
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException, OperationNotPermittedException {
		if (_tab != null) {
			_tab.save();
		}
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		if (_tab != null) {
			_tab.discard();
			display(_windowHandler);
		} else {
			throw new OperationNotPermittedException();
		}
	}

	@Override
	public void remove() {
		if (_windowHandler != null) {
			_windowHandler.removePatientListener(this);
		}

		super.remove();

		if (_tab != null) {
			_tab.remove();

			if (_tab instanceof AbstractLazyTab) {
				LOGGER.debug("removing " + this + " as lazy tab of " + _tab);
				((AbstractLazyTab) _tab).setLazyTab(null);
			}

			_tab = null;
			_proxyHandler.setHandler(null);
		}
	}

	public void changeContent(AbstractTab tab) {
		changeContent(tab, _windowHandler);
	}

	private void changeContent(AbstractTab tab, WindowHandler windowHandler) {
		WindowHandler oldWindowHandler = _windowHandler;

		try {
			super.display(windowHandler);
		} catch (NoPatientSelectedException ex) {
			LOGGER.fatal("AbstractTab is not in a position to throw a NoPatientSelectedException", ex);
		}

		if (_windowHandler != oldWindowHandler) {
			if (oldWindowHandler != null) {
				oldWindowHandler.removePatientListener(this);
			}
			_windowHandler.addPatientListener(this);
		}

		if (_tab != null) {
			_tab.remove();

			// resist on side effects of AbstractTab.remove()
			_windowHandler = windowHandler;
		}

		_tab = tab;
		try {
			if (_tab instanceof AbstractLazyTab) {
				LOGGER.debug("setting " + this + " as lazy tab of " + _tab);
				((AbstractLazyTab) _tab).setLazyTab(this);
			}

			_tab.display(_windowHandler);
			setContent(_tab);
			_proxyHandler.setHandler(_tab.getHandler());
		} catch (NoPatientSelectedException ex) {
			Border border = new Border();

			BoxPane boxPane = new BoxPane(Orientation.VERTICAL);
			boxPane.getStyles().put("verticalAlignment", "center");
			boxPane.getStyles().put("horizontalAlignment", "center");

			ImageView errorImage = new ImageView();
			Label message = new Label("Bitten einen Patienten auswählen.");
			String createPatientMessage = ("Sie haben auch die Möglichkeit einen neuen Patienten anzulegen.");
			Label createPatient = new Label();
			message.getStyles().put("font", "bold");
			message.getStyles().put("color", "#ffa800");
			createPatient.getStyles().put("color", "#777777");

			if (_tab instanceof PatientTab) {
				// Special "no patient selected" view.
				try {
					errorImage.setImage(Image.load(LazyTab.class.getResource("/gfx/icon/user_info/user_warning_create_new_info.png")));
				} catch (TaskExecutionException e) {
					LOGGER.warn("Unable to load image", e);
				}

				createPatient.setText(createPatientMessage);
			} else {
				// Default "no patient selected" view.
				try {
					errorImage.setImage(Image.load(LazyTab.class.getResource("/gfx/icon/user_info/user_warning_select_patient.png")));
				} catch (TaskExecutionException e) {
					LOGGER.warn("Unable to load image", e);
				}

				createPatient.setText("");
			}

			boxPane.add(errorImage);
			boxPane.add(message);
			boxPane.add(createPatient);

			border.setContent(boxPane);
			setContent(border);
		}
	}
}
