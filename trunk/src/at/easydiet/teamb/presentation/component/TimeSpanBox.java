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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Expander;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Column;
import org.apache.pivot.wtk.TablePane.ColumnSequence;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.DietPlanHandler;
import at.easydiet.teamb.application.handler.MealHandler;
import at.easydiet.teamb.application.handler.ParameterHandler;
import at.easydiet.teamb.application.handler.TimeSpanHandler;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.viewobject.TimeSpanViewable;
import at.easydiet.teamb.presentation.util.Parameterizable;

/**
 * Represents a TimeSpanBox in GUI. Can contain several TimeSpans.
 * @author TeamB
 */
public class TimeSpanBox extends BoxPane {
	private static final Logger LOGGER = Logger.getLogger(TimeSpanBox.class);

	private DietPlanHandler _dietPlanHandler;

	private Label _noTimeSpan;

	/**
	 * Instance of TimeSpanBox. Can contain several TimeSpans.
	 */
	public TimeSpanBox() {
		super(Orientation.VERTICAL);
		getStyles().put("fill", true);

		_noTimeSpan = new Label("Es sind noch keine Zeiträume für diese Diät vorhanden");
		add(_noTimeSpan);
	}

	/**
	 * Set diet plan handler
	 * @param dietPlanHandler set diet plan handler
	 */
	public void setHandler(DietPlanHandler dietPlanHandler) {
		_dietPlanHandler = dietPlanHandler;
	}

	/**
	 * Add given time spans
	 * @param timeSpanHandlers Set of TimeSpanHandler
	 */
	public void addTimeSpans(Set<TimeSpanHandler> timeSpanHandlers) {
		for (TimeSpanHandler timeSpanHandler : timeSpanHandlers) {
			addTimeSpan(timeSpanHandler);
		}
	}

	/**
	 * Add given TimeSpanHandler
	 * @param timeSpanHandler is added
	 */
	public void addTimeSpan(TimeSpanHandler timeSpanHandler) {
		add(new TimeSpanBoxLine(timeSpanHandler));
		_noTimeSpan.setVisible(false);
	}

	/**
	 * Represents a TimeSpanBoxLine in TimeSpanBox. Can contain several meals.
	 * @author TeamB
	 */
	public class TimeSpanBoxLine extends Expander implements Parameterizable {
		private DietParameterBox _parameterBox;
		private LinkButton _addMealButton;
		private LinkButton _removeTimeSpanButton;
		private BoxPane _container;

		private TimeSpanHandler _timeSpanHandler;
		private TimeSpanViewable _timeSpan;

		/**
		 * Instance of TimeSpanBoxLine. Can contain several meals.
		 * @param timeSpanHandler required to handle time spans
		 */
		public TimeSpanBoxLine(TimeSpanHandler timeSpanHandler) {
			_timeSpanHandler = timeSpanHandler;
			_timeSpan = timeSpanHandler.getTimeSpan();

			DateFormat dateFormat = DateFormat.getDateInstance(SimpleDateFormat.SHORT);
			setTitle("Zeitraum von " + dateFormat.format(_timeSpan.getStart().getTime()) + " bis " + dateFormat.format(_timeSpan.getEnd().getTime()));
			getStyles().put("borderColor", "#dfdfdf");
			getStyles().put("titleBarBorderColor", "#000000");

			BoxPane content = new BoxPane(Orientation.VERTICAL);
			content.getStyles().put("fill", true);

			_parameterBox = new DietParameterBox(this, _timeSpanHandler.getDietParameters());

			Border parameterBorder = new Border(_parameterBox);
			parameterBorder.setTitle("Spezielle Parameter für diesen Zeitraum");
			parameterBorder.getStyles().put("color", "#cfcfcf");
			parameterBorder.getStyles().put("padding", 10);

			try {
				_addMealButton = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/add.png")), "neue Mahlzeit"));
			} catch (TaskExecutionException ex) {
				LOGGER.warn("Unable to load add image", ex);

				_addMealButton = new LinkButton(new ButtonData("neue Mahlzeit"));
			}
			_addMealButton.getButtonPressListeners().add(new ButtonPressListener() {

				@Override
				public void buttonPressed(Button button) {
					addMeal(_timeSpanHandler.addMeal());
				}
			});
			
			try {
				_removeTimeSpanButton = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/remove.png")), "Zeitraum entfernen"));
			} catch (TaskExecutionException ex) {
				LOGGER.warn("Unable to load remove image", ex);

				_removeTimeSpanButton = new LinkButton(new ButtonData("Zeitraum entfernen"));
			}
			_removeTimeSpanButton.getButtonPressListeners().add(new ButtonPressListener() {
				@Override
				public void buttonPressed(Button button) {
					removeTimeSpan(TimeSpanBoxLine.this);
				}
			});

			content.add(parameterBorder);

			_container = new BoxPane(Orientation.VERTICAL);
			_container.getStyles().put("fill", true);
			addMeals(timeSpanHandler.getMealHandlers());
			
			content.add(_container);
			
	 		TablePane table = new TablePane();
	 		table.getStyles().put("padding", 5);
	 		table.getStyles().put("backgroundColor", "#efefef");
	 		
			ColumnSequence columns = table.getColumns();
			columns.add(new Column(1, true));
			columns.add(new Column(-1));
	
			Row row = new Row(-1);
			row.add(_addMealButton);
			row.add(_removeTimeSpanButton);
			table.getRows().add(row);
			 
			Border addMealRemoveTimeSpanBorder = new Border(table);
			addMealRemoveTimeSpanBorder.getStyles().put("color", "#dfdfdf");

			content.add(addMealRemoveTimeSpanBorder);

			setContent(content);
		}
		
		/**
		 * Gets current set time span.
		 * @return TimeSpanViewable
		 */
		public TimeSpanViewable getTimeSpan() {
			return _timeSpan;
		}

		/**
		 * Add given meal handlers
		 * @param list
		 */
		private void addMeals(MealHandler[] list) {
			for (MealHandler mealHandler : list) {
				addMeal(mealHandler);
			}
		}

		/**
		 * Add given meal handler
		 * @param mealHandler
		 */
		private void addMeal(MealHandler mealHandler) {
			_container.add(new MealBox(mealHandler, TimeSpanBoxLine.this));
		}
		
		/**
		 * Remove given time span box line
		 * @param timeSpanBoxLine
		 */
		private void removeTimeSpan(TimeSpanBoxLine timeSpanBoxLine) {
			_dietPlanHandler.removeTimeSpan(_timeSpanHandler);
			getParent().remove(this);
		}

		@Override
		public void removeParameter(ParameterHandler parameterHandler) {
			_timeSpanHandler.removeTimeSpanDietParameter(parameterHandler);
		}

		@Override
		public void addParameter(ParameterHandler parameterHandler) throws ErrorInFormException {
			_timeSpanHandler.addTimeSpanDietParameter(parameterHandler);
			_parameterBox.addParameter(parameterHandler);
		}

		/**
		 * Removes the meal.
		 *
		 * @param mealBox the meal box
		 */
		public void removeMeal(MealBox mealBox) {
			_timeSpanHandler.removeMeal(mealBox.getHandler());
			_container.remove(mealBox);
		}
	}
}
