package at.easydiet.teamb.presentation.gui;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.Vote;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TabPane.TabSequence;
import org.apache.pivot.wtk.TabPaneSelectionListener;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.ExitOptions;
import at.easydiet.teamb.presentation.component.MainTabPane;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.tab.AbstractTab;
import at.easydiet.teamb.presentation.gui.tab.DietPlanTab;
import at.easydiet.teamb.presentation.gui.tab.LazyTab;
import at.easydiet.teamb.util.Initializator;

/**
 * The Class EasyDietWindow.
 * 
 * @author TeamB
 */
public class EasyDietWindow extends Window implements Bindable, TabPaneSelectionListener {

	private static Logger LOGGER = Logger.getLogger(EasyDietWindow.class);

	@BXML
	private TabPane _tabPane = null;
	@BXML
	private EasyBar _easyBar;

	private AbstractTab _currentTab;

	private WindowHandler _windowHandler;
	
	private int _nextIndex = 0;

	/**
	 * Initialize the EasyDiet window.IOException
	 */
	public EasyDietWindow() {
		_windowHandler = WindowHandler.getWindowHandler();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map, java.net.URL, org.apache.pivot.util.Resources)
	 */
	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		_tabPane.getStyles().put("tabOrientation", Orientation.VERTICAL);

		// register to events
		registerToEasyDietButton();
		_tabPane.getTabPaneSelectionListeners().add(this);

		TabSequence tabSequence = _tabPane.getTabs();
		tabSequence.add(createLazyTab("patient", "patient", "Stammdaten"));
		tabSequence.add(createLazyTab("treatment", "treatment", "Behandlung"));
		tabSequence.add(createLazyTab("patientstatus", "status", "Patientenstatus"));
		tabSequence.add(createLazyTab("diet", "diet", "Diät"));
		tabSequence.add(createLazyTab("protocol", "protocols", "Protokolle"));
		tabSequence.add(createLazyTab("labor", "lab", "Labor"));
		BoxPane emptyTab = new BoxPane();
		emptyTab.setEnabled(false);
		tabSequence.add(emptyTab);
		tabSequence.add(createLazyTab("usermanagement", "usermanagement", "Benutzerverwaltung"));
		tabSequence.add(createLazyTab("recipe", "recipes", "Rezepte"));
		
	}

	private void registerToEasyDietButton() {
		_easyBar.getAddButton().getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(final Button button) {
				try {
					_currentTab.create();
				} catch (ExitNotPermittedException ex) {
					YesNoAbortSheet question = new YesNoAbortSheet(EasyBar.getCurrentInstance(), ex.getExitOptions(), new YesNoAbortSheet.Redo() {

						@Override
						public void redo() {
							buttonPressed(button);
						}
					});
					question.open(getWindow());
				} catch (OperationNotPermittedException ex) {
					LOGGER.warn("create not allowed", ex);
					Alert.alert(MessageType.WARNING, "Neu anlegen nicht möglich", getWindow());
				}
			}
		});

		_easyBar.getRemoveButton().getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				// TODO: remove
				Alert.alert(MessageType.QUESTION, "geloescht", EasyDietWindow.this);
			}
		});

		_easyBar.getPrintButton().getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				// TODO: open print dialog
			}
		});

		_easyBar.getSaveButton().getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {

				try {
					_currentTab.save();
				} catch (DatabaseException ex) {
					LOGGER.error("databaseexception at saving", ex);
					Alert.alert(MessageType.ERROR, "Es ist ein Datenbankfehler aufgetreten!", getWindow());
				} catch (ErrorInFormException ex) {
					Alert.alert(MessageType.ERROR, "Es sind noch Fehler in der Form vorhanden!", getWindow());
				} catch (OperationNotPermittedException ex) {
					LOGGER.warn("saving not possible", ex);
					Alert.alert(MessageType.WARNING, "Speichern nicht möglich", getWindow());
				}
			}
		});

		_easyBar.getCancelButton().getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				try {
					_currentTab.discard();
				} catch (OperationNotPermittedException ex) {
					LOGGER.debug("_currentTab is not supporting discarding", ex);

					Alert.alert(MessageType.WARNING, "Diese Funktion ist nicht möglich", getWindow());
				}
			}
		});

	}

	/**
	 * Sets the Focus into the Search Area.
	 */
	public void startup() {
		_easyBar.getSearchTextInput().requestFocus();
	}

	private LazyTab createLazyTab(final String tabFile, String iconFile, String alternativeText) {
		LazyTab tab = new LazyTab(new Initializator<AbstractTab>() {

			@Override
			public AbstractTab createInstance() {
				try {
					return (AbstractTab) new BXMLSerializer().readObject(AbstractTab.class, "easydiet_tab_" + tabFile + ".bxml");
				} catch (IOException ex) {
					LOGGER.fatal(ex);
				} catch (SerializationException ex) {
					LOGGER.fatal(ex);
				}

				return null;
			}
		});

		try {
			MainTabPane.setTabData(tab, new ButtonData(Image.load(DietPlanTab.class.getResource("/gfx/icon/48x48px/" + iconFile + ".png"))));
			MainTabPane.setTooltipText(tab, alternativeText);
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Can not load button data icon", ex);
			MainTabPane.setTabData(tab, new ButtonData(alternativeText));
		}

		return tab;
	}

	@Override
	public void selectedIndexChanged(final TabPane tabPane, final int previousSelectedIndex) {
		if (tabPane.getSelectedIndex() != previousSelectedIndex) {
			LOGGER.debug("tab changed to " + tabPane.getSelectedIndex() + ", previous tab was: " + previousSelectedIndex);

			AbstractUseCaseHandler newHandler = null;
			AbstractTab newTab = null;

			Component component = tabPane.getSelectedTab();

			if (component instanceof AbstractTab) {
				newTab = ((AbstractTab) component);

				try {
					newTab.display(_windowHandler);
				} catch (NoPatientSelectedException ex) {
					LOGGER.error("changing tab without having a patient selected and the tab isn't able to handle it by himself", ex);
					Alert.alert(MessageType.ERROR, "Bevor Sie diese Aktion ausführen, muss ein Patient ausgewählt sein.", getWindow());
					_tabPane.setSelectedIndex(previousSelectedIndex);
					return;
				}

				newHandler = newTab.getHandler();
			}

			try {
				_windowHandler.changeHandler(newHandler);
			} catch (ExitNotPermittedException e) {
				LOGGER.debug("Changing Handler failed.", e);

				// remove the tab, that was not displayed
				if (newTab != null) {
					newTab.remove();
				}

				_tabPane.setSelectedIndex(previousSelectedIndex);

				YesNoAbortSheet question = new YesNoAbortSheet(EasyBar.getCurrentInstance(), e.getExitOptions(), new YesNoAbortSheet.Redo() {

					@Override
					public void redo() {
						tabPane.setSelectedIndex(previousSelectedIndex);
					}
				});
				question.open(getWindow());
				// TODO: show same prompts as in Vetoed
			}

			if (_currentTab != null) {
				_currentTab.remove();
			}

			_currentTab = newTab;
		}
	}

	@Override
	public void selectedIndexChangeVetoed(final TabPane tabPane, Vote reason) {
		LOGGER.debug("change tab failed. current tab is " + tabPane.getSelectedIndex());

		ExitOptions exitOptions = _windowHandler.getExitOptions();

		YesNoAbortSheet question = new YesNoAbortSheet(EasyBar.getCurrentInstance(), exitOptions, new YesNoAbortSheet.Redo() {

			@Override
			public void redo() {
				tabPane.setSelectedIndex(_nextIndex);
			}
		});
		question.open(getWindow());
		/*
		 * TODO: if there are no errors: show prompt to save changed content. On abort do nothing. On yes and no call windowHandler.save() or
		 * windowHandler.discard() and if that was successful change call tabPane.setSelectedIndex(_nextTab) if there are errors: show prompt to discard
		 * changed content or abort
		 */
	}

	@Override
	public Vote previewSelectedIndexChange(TabPane tabPane, int selectedIndex) {
		if (tabPane.getSelectedIndex() != selectedIndex) {
			LOGGER.debug("try to change to tab " + selectedIndex + ". current tab is " + tabPane.getSelectedIndex());

			_nextIndex = selectedIndex;

			AbstractUseCaseHandler newHandler = null;
			Component component = tabPane.getTabs().get(selectedIndex);
			if (component instanceof AbstractTab) {
				newHandler = ((AbstractTab) component).getHandler();
			}

			if (_windowHandler.changeHandlerAllowed(newHandler)) {
				return Vote.APPROVE;
			} else {
				return Vote.DENY;
			}
		} else {
			// chaning to the same tab doesn't have any effect
			return Vote.APPROVE;
		}
	}
}