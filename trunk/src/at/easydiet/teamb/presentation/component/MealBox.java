/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	22.04.2011
 */

package at.easydiet.teamb.presentation.component;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Button.State;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ButtonStateListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.FocusTraversalDirection;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.SuggestionPopup;
import org.apache.pivot.wtk.SuggestionPopupCloseListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Column;
import org.apache.pivot.wtk.TablePane.ColumnSequence;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.content.ListViewItemRenderer;
import org.apache.pivot.wtk.media.Image;
import org.apache.pivot.wtk.validation.FloatRangeValidator;

import at.easydiet.teamb.application.handler.MealHandler;
import at.easydiet.teamb.application.handler.MealLineHandler;
import at.easydiet.teamb.application.handler.ParameterHandler;
import at.easydiet.teamb.application.handler.RecipeSearchHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.MealLineErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.MealLineViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.application.viewobject.RecipeViewable;
import at.easydiet.teamb.presentation.component.TimeSpanBox.TimeSpanBoxLine;
import at.easydiet.teamb.presentation.util.Parameterizable;

/**
 * The Class MealBox.
 */
public class MealBox extends Border implements Parameterizable {

	private static final Logger LOGGER = Logger.getLogger(MealBox.class);

	private static final int MAX_POPUP_ENTRIES = 10;

	private BoxPane _container;
	private TextInput _name;
	private TextInput _mealCode;
	private LinkButton _addRecipeButton;
	private DietParameterBox _parameterBox;
	private LinkButton _removeMealButton;
	private Label _noMealLineLabel;
	private SuggestionPopup _suggestionPopup;

	private MealHandler _mealHandler;
	private MealViewable _mealViewable;
	private TimeSpanBoxLine _timeSpanBoxLine;

	private RecipeSearchHandler _recipeSearchHandler;

	private MealLine _previousMealLine = null;
	private HashMap<MealLineViewable, MealLine> _map;

	/**
	 * Instantiates a new meal box.
	 *
	 * @param mealHandler the meal handler
	 * @param timeSpanBoxLine the time span box line
	 */
	public MealBox(MealHandler mealHandler, TimeSpanBoxLine timeSpanBoxLine) {
		_mealHandler = mealHandler;
		_mealViewable = mealHandler.getMeal();

		_map = new HashMap<MealLineViewable, MealBox.MealLine>();

		_timeSpanBoxLine = timeSpanBoxLine;

		BoxPane content = new BoxPane(Orientation.VERTICAL);
		content.getStyles().put("fill", true);

		_name = new TextInput();
		_name.setPrompt("Name der Mahlzeit");
		_name.setText((_mealViewable.getName() != null) ? _mealViewable.getName() : "");
		_name.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
			@Override
			public void textChanged(TextInput textInput) {
				_mealHandler.setName(textInput.getText());
			}
		});

		_mealCode = new TextInput();
		_mealCode.setPrompt("Mahlzeitcode");
		_mealCode.setText((_mealViewable.getCode() != null) ? _mealViewable.getCode() : "");
		_mealCode.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
			@Override
			public void textInserted(TextInput textInput, int index, int count) {
				_mealHandler.setCode(textInput.getText());
				updateSuggestion(textInput);
			}

			@Override
			public void textRemoved(TextInput textInput, int index, int count) {
				_mealHandler.setCode(textInput.getText());
				updateSuggestion(textInput);
			}
		});

		_container = new BoxPane(Orientation.VERTICAL);
		_container.getStyles().put("fill", true);

		_noMealLineLabel = new Label("Es sind noch keine Rezepte/Nahrungsmittel vorhanden");

		_parameterBox = new DietParameterBox(this);
		_parameterBox.addParameters(_mealHandler.getDietParameters());

		try {
			_addRecipeButton = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/add.png")),
					"Zusätzliches Rezept angeben"));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			_addRecipeButton = new LinkButton(new ButtonData("Zusätzliches Rezept angeben"));
		}
		_addRecipeButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				addMealLine(_mealHandler.addMealLine());
			}
		});

		BoxPane mealLineContent = new BoxPane(Orientation.VERTICAL);
		mealLineContent.getStyles().put("fill", true);
		mealLineContent.getStyles().put("spacing", 15);
		mealLineContent.add(_container);
		mealLineContent.add(_noMealLineLabel);
		mealLineContent.add(_addRecipeButton);

		try {
			_removeMealButton = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/remove.png")), "Mahlzeit entfernen"));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load remove image", ex);

			_removeMealButton = new LinkButton(new ButtonData("Mahlzeit entfernen"));
		}
		_removeMealButton.getButtonPressListeners().add(new ButtonPressListener() {
			@Override
			public void buttonPressed(Button button) {
				_timeSpanBoxLine.removeMeal(MealBox.this);
			}
		});

		// set styles
		setTitle("Mahlzeit");
		getStyles().put("padding", 10);
		getStyles().put("color", "#cfcfcf");

		// put it all together
		BoxPane metaContainer = new BoxPane();
		metaContainer.getStyles().put("verticalAlignment", "center");
		metaContainer.add(_name);
		metaContainer.add(_mealCode);

		TablePane table = new TablePane();
		ColumnSequence columns = table.getColumns();
		columns.add(new Column(1, true));
		columns.add(new Column(-1));

		Border removeMealButtonBorder = new Border(_removeMealButton);
		removeMealButtonBorder.getStyles().put("padding", 5);
		removeMealButtonBorder.getStyles().put("color", "#dfdfdf");
		removeMealButtonBorder.getStyles().put("backgroundColor", "#efefef");

		Row row = new Row(-1);
		row.add(metaContainer);
		row.add(removeMealButtonBorder);
		table.getRows().add(row);

		Border mealLineBorder = new Border(mealLineContent);
		mealLineBorder.setTitle("Rezepte/Nahrungsmittel");
		mealLineBorder.getStyles().put("padding", 10);

		Border parameterBorder = new Border(_parameterBox);
		parameterBorder.setTitle("Spezielle Parameter für diese Mahlzeit");
		parameterBorder.getStyles().put("padding", 10);

		content.add(table);
		content.add(parameterBorder);
		content.add(mealLineBorder);

		setContent(content);

		_recipeSearchHandler = new RecipeSearchHandler(WindowHandler.getWindowHandler().getSelectedPatient());

		_suggestionPopup = new SuggestionPopup();
		_suggestionPopup.setListSize(MAX_POPUP_ENTRIES);
		_suggestionPopup.setSuggestionRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof RecipeViewable) {
					// TODO: mark disfavors
					RecipeViewable recipe = (RecipeViewable) item;
					super.render(recipe.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}

			@Override
			public String toString(Object item) {
				if (item instanceof RecipeViewable) {
					RecipeViewable recipe = (RecipeViewable) item;
					return super.toString(recipe.getName());
				} else {
					return super.toString(item);
				}
			}
		});

		addMealLines(_mealHandler.getMealLineHandlers());
	}

	protected void updateSuggestion(TextInput textInput) {
		List<String> codes = _mealHandler.searchMealCodes(textInput.getText(), (MAX_POPUP_ENTRIES * 2));

		if (codes.size() > 0) {
			// create new Pivot-ArrayList
			ArrayList<String> suggestions = new ArrayList<String>(codes.size());
			for (String string : codes) {
				suggestions.add(string);
			}

			_suggestionPopup.setSuggestionData(suggestions);
			_suggestionPopup.open(textInput);
		}
	}

	/**
	 * Adds the meal lines.
	 *
	 * @param mealLines the meal lines
	 */
	public void addMealLines(MealLineHandler[] mealLines) {
		for (MealLineHandler mealLine : mealLines) {
			addMealLine(mealLine);
		}
	}

	/**
	 * Adds the meal line.
	 *
	 * @param mealLine the meal line
	 */
	public void addMealLine(MealLineHandler mealLine) {
		if (_map.containsKey(mealLine.getMealLine())) {
			return;
			// already inserted
		}

		MealLine line = null;

		if (mealLine.getMealLine().isAlternative()) {
			if (!_map.containsKey(mealLine.getMealLine().getParent())) {
				throw new IllegalArgumentException("parent not found! change your insert order!");
			}
			MealLine parent = _map.get(mealLine.getMealLine().getParent());
			line = new MealLine(mealLine, _previousMealLine);

			MealLine last = (MealLine) _container.get((_container.getLength() - 1));
			if (last == parent || (last._mealLine.isAlternative() && last._parentMealLine == parent)) {
				// insert add last position
				_previousMealLine = line;
			}

			insert(line, _container.indexOf(parent));
		} else {
			line = (_previousMealLine = new MealLine(mealLine, _previousMealLine));
			_container.add(line);
		}
		_map.put(line._mealLine, line);

		_noMealLineLabel.setVisible(false);
	}

	private class MealLine extends BoxPane implements EventListener<ValidatorArgs<MealLineErrorField>>, SuggestionPopupCloseListener {

		private Label _alternativeSymbol;
		private TextInput _name;
		private TextInput _quantity;
		private Label _unit;
		private LinkButton _remove;
		private Checkbox _alternativeCheckbox;

		private MealLineHandler _handler;
		private MealLineViewable _mealLine;
		private MealLine _parentMealLine;

		public MealLine(MealLineHandler mealLineHandler, MealLine previousMealLine) {
			_handler = mealLineHandler;
			_mealLine = _handler.getMealLine();
			_parentMealLine = previousMealLine;

			_handler.addValidadedListener(this);

			_alternativeSymbol = new Label("->");
			_alternativeSymbol.setVisible(_mealLine.isAlternative());
			_name = new TextInput();
			_name.setPrompt("Rezept/Nahrungsmittel");
			_name.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
				@Override
				public void textInserted(TextInput textInput, int index, int count) {
					updateSelectedRecipe(null);
					updateRecipeSuggestion(textInput);
				}

				@Override
				public void textRemoved(TextInput textInput, int index, int count) {
					updateSelectedRecipe(null);
					updateRecipeSuggestion(textInput);
				}
			});

			_unit = new Label("g");
			try {
				_remove = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/remove.png"))));
			} catch (TaskExecutionException ex) {
				LOGGER.warn("Unable to load remove image", ex);

				_remove = new LinkButton(new ButtonData("löschen"));
			}
			_alternativeCheckbox = new Checkbox("ist eine alternative");
			_alternativeCheckbox.setVisible((_parentMealLine != null));

			_quantity = new TextInput();
			// ensure only positive float values in quantity
			_quantity.setTextSize(6);
			_quantity.setValidator(new FloatRangeValidator(0f, Float.MAX_VALUE));
			_quantity.setPrompt("Anzahl");
			_quantity.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
				@Override
				public void textChanged(TextInput textInput) {
					float quantity;
					try {
						quantity = Float.parseFloat(textInput.getText());
					} catch (NumberFormatException e) {
						LOGGER.debug(e);
						quantity = -1f;
					}
					_handler.setQuantity(quantity);
				}
			});

			// recognize remove button click
			_remove.getButtonPressListeners().add(new ButtonPressListener() {
				@Override
				public void buttonPressed(Button button) {

					try {
						_mealHandler.removeMealLine(_handler);
					} catch (DatabaseException ex) {
						LOGGER.error("Couldn't remote the MealLine from the Database", ex);
						Alert.alert(MessageType.ERROR, "Es ist ein unbekannter Fehler aufgetreten", getWindow());
					}
					int index = _container.indexOf(MealLine.this);
					_container.remove(MealLine.this);
					_handler.removeValidadedListener(MealLine.this);
					if (_container.getLength() == 0) {
						_noMealLineLabel.setVisible(true);
					} else if (index > -1) {
						for (int i = index; index < _container.getLength(); i++) {
							Component comp = _container.get(i);
							if (comp instanceof MealLine) {
								((MealLine) comp).updateAlternative();
								break;
							}
						}
					}
					_map.remove(_mealLine);
				}
			});

			// recognize alternative toggle
			_alternativeCheckbox.getButtonStateListeners().add(new ButtonStateListener() {

				@Override
				public void stateChanged(Button button, State previousState) {
					if (button.isSelected()) {
						_handler.setMealLineAsAlternative(_parentMealLine._handler);

						if (_parentMealLine._mealLine.isAlternative()) {
							_parentMealLine = _parentMealLine._parentMealLine;
						}

						for (MealLineViewable mealLine : _mealLine.getAlternatives()) {
							MealLine alternative = _map.get(mealLine);
							if (alternative != null) {
								alternative._parentMealLine = _parentMealLine;
							}
						}
					} else {
						_handler.removeMealLineAlternative();

						int index = _container.indexOf(MealLine.this);

						int newIndex = -1;
						for (int i = (index + 1); i < _container.getLength(); i++) {
							Component comp = _container.get(i);
							if (comp instanceof MealLine) {
								MealLine line = (MealLine) comp;
								if (!line._mealLine.isAlternative()) {
									newIndex = i;
									break;
								}
							}
						}

						if (newIndex < 0) {
							newIndex = _container.getLength();
						}

						if (newIndex > -1) {
							MealLine newParent = null;
							for (int i = (newIndex - 1); i >= 0; i++) {
								Component comp = _container.get(i);
								if (comp instanceof MealLine) {
									newParent = (MealLine) comp;
									break;
								}
							}
							_previousMealLine = newParent;

							_container.move(index, (newIndex - 1));

						}
					}
					updateAlternative();
				}
			});

			getStyles().put("verticalAlignment", "center");

			add(_alternativeSymbol);
			add(_name);
			add(_quantity);
			add(_unit);
			add(_remove);
			add(_alternativeCheckbox);
		}

		private void updateAlternative() {
			_alternativeSymbol.setVisible(_mealLine.isAlternative());
			_alternativeCheckbox.setSelected(_mealLine.isAlternative());
			if (_container.get(0) == this) {
				_alternativeCheckbox.setVisible(false);
			}
		}

		private void updateSelectedRecipe(RecipeViewable recipe) {
			_handler.setRecipe(recipe);
		}

		private void updateRecipeSuggestion(TextInput textInput) {
			List<RecipeViewable> recipeList = _recipeSearchHandler.searchRecipes(textInput.getText(), (MAX_POPUP_ENTRIES * 2));

			if (recipeList.size() > 0) {
				// create new Pivot-ArrayList
				ArrayList<RecipeViewable> suggestions = new ArrayList<RecipeViewable>(recipeList.size());
				for (RecipeViewable recipeViewable : recipeList) {
					suggestions.add(recipeViewable);
				}

				// open pop up
				_suggestionPopup.setSuggestionData(suggestions);
				_suggestionPopup.open(textInput, this);
			} else {
				_suggestionPopup.close();
			}
		}

		
		@Override
		public void fired(Object sender, ValidatorArgs<MealLineErrorField> eventObject) {
			if (eventObject.getErrorFields().isEmpty()) {
				getStyles().put("backgroundColor", "#ffffff");
			} else {
				// TODO: better error display
				getStyles().put("backgroundColor", "#ff0000");
			}
		}

		@Override
		public void suggestionPopupClosed(SuggestionPopup suggestionpopup) {
			if (suggestionpopup.getResult()) {
				Object selected = suggestionpopup.getSelectedSuggestion();

				if (selected instanceof RecipeViewable) {
					updateSelectedRecipe((RecipeViewable) selected);
					_name.transferFocus(FocusTraversalDirection.FORWARD);
				} else {
					updateSelectedRecipe(null);
				}
			}
		}

	}

	@Override
	public void removeParameter(ParameterHandler parameterHandler) {
		_mealHandler.removeMealDietParameter(parameterHandler);
	}

	@Override
	public void addParameter(ParameterHandler parameterHandler) throws ErrorInFormException {
		_mealHandler.addMealDietParameter(parameterHandler);
		_parameterBox.addParameter(parameterHandler);
	}

	/**
	 * Gets the handler.
	 *
	 * @return the handler
	 */
	public MealHandler getHandler() {
		return _mealHandler;
	}
}
