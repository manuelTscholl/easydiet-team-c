/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	13.05.2011
 */

package at.easydiet.teamb.presentation.component;

import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.effects.FadeDecorator;
import org.apache.pivot.wtk.effects.FadeTransition;
import org.apache.pivot.wtk.effects.Transition;
import org.apache.pivot.wtk.effects.TransitionListener;

import at.easydiet.teamb.presentation.util.MessageType;

/**
 * Represents a message bar.
 * 
 * @author TeamB
 */
public class MessageBar extends Border {
	protected Label _message = null;

	protected static String GREEN_BORDER = "#64c870";
	protected static String GREEN_FILL = "#b0f282";

	protected static String RED_BORDER = "#a84a4a";
	protected static String RED_FILL = "#f28282";
	
	protected static String YELLOW_BORDER = "#f8de84";
	protected static String YELLOW_FILL = "#fef7b2";

	protected static int TRANSITION_DURATION = 3000;
	protected static int TRANSITION_RATE = 30;

	protected MessageType _messageType = MessageType.Info;

	private MessageBarTransition _collapseTransition = null;

	/**
	 * Message bar.
	 */
	public MessageBar() {
		_message = new Label();
		_message.getStyles().put("font", "18");
		_message.getStyles().put("verticalAlignment", "center");
		_message.getStyles().put("horizontalAlignment", "center");

		setHeight(20);

		setContent(_message);
	}

	/**
	 * Set message and color according to it's type.
	 * @param message message which is shown.
	 * @param type message type: Info, Warning, Error.
	 */
	public void setMessage(String message, MessageType type) {
		_messageType = type;
		_message.setText(message);
		_message.getStyles().put("color", "#000000");

		String border = "";
		String fill = "";

		switch (type) {
			case Info:
				border = GREEN_BORDER;
				fill = GREEN_FILL;
				break;
			case Error:
				border = RED_BORDER;
				fill = RED_FILL;
				break;
			case Warning:
				border = YELLOW_BORDER;
				fill = YELLOW_FILL;

			default:
				border = GREEN_BORDER;
				fill = GREEN_FILL;
				break;
		}

		getStyles().put("color", border);
		getStyles().put("backgroundColor", fill);
	}

	@Override
	public void setVisible(boolean visible) {
		if (!visible) {
			_message.getStyles().put("color", "#ffffff");

			getStyles().put("color", "#ffffff");
			getStyles().put("backgroundColor", "#ffffff");
		} else {
			setMessage(_message.getText(), _messageType);
		}
	}

	/**
	 * Start animation.
	 */
	public void start() {
		setVisible(true);

		if (_collapseTransition == null) {
			_collapseTransition = new MessageBarTransition(this, TRANSITION_DURATION, TRANSITION_RATE);

			TransitionListener transitionListener = new TransitionListener() {
				@Override
				public void transitionCompleted(Transition transition) {
					_collapseTransition = null;
					MessageBar.this.setVisible(false);
				}
			};

			_collapseTransition.start(transitionListener);
		} else {
			_collapseTransition.end();
			start();
		}
	}

	/**
	 * Fast-forward animation.
	 */
	public void end() {
		_collapseTransition.end();
	}

	/**
	 * @return true if is running.
	 */
	public boolean isRunning() {
		return _collapseTransition.isRunning();
	}

	/**
	 * @return current time.
	 */
	public long getCurrentTime() {
		return _collapseTransition.getCurrentTime();
	}

	/**
	 * @return duration.
	 */
	public int getDuration() {
		return _collapseTransition.getDuration();
	}

	/**
	 * @return elapsed time.
	 */
	public int getElapsedTime() {
		return _collapseTransition.getElapsedTime();
	}

	/**
	 * @return get rate.
	 */
	public int getRate() {
		return _collapseTransition.getRate();
	}

	/**
	 * @return get interval.
	 */
	public int getInterval() {
		return _collapseTransition.getInterval();
	}

	/**
	 * @return get percent complete.
	 */
	public float getPercentComplete() {
		return _collapseTransition.getPercentComplete();
	}

	/**
	 * @return get start time.
	 */
	public long getStartTime() {
		return _collapseTransition.getStartTime();
	}

	/**
	 * Set duration of animation in ms.
	 * @param duration in ms.
	 */
	public void setDuration(int duration) {
		_collapseTransition.setDuration(duration);
	}

	/**
	 * Set refresh rate per second.
	 * @param rate refresh rate per second.
	 */
	public void setRate(int rate) {
		_collapseTransition.setRate(rate);
	}

	/**
	 * Stop animation.
	 */
	public void stop() {
		_collapseTransition.stop();
	}

	/**
	 * Custom fade effect.
	 * @author TeamB
	 */
	public class MessageBarTransition extends FadeTransition {
		private Component _component;
		private FadeDecorator _fadeDecorator = new FadeDecorator();

		/**
		 * Instance of MessageBarTransition.
		 * @param component which the should be effected.
		 * @param duration in ms.
		 * @param rate
		 */
		public MessageBarTransition(Component component, int duration, int rate) {
			super(component, duration, rate);

			_component = component;
		}

		/**
		 * return component.
		 */
		public Component getComponent() {
			return _component;
		}

		@Override
		public void start(TransitionListener transitionListener) {
			_component.getDecorators().add(_fadeDecorator);

			super.start(transitionListener);
		}

		@Override
		public void stop() {
			_component.getDecorators().remove(_fadeDecorator);

			super.stop();
		}

		@Override
		protected void update() {
			float percentComplete = getPercentComplete();

			if (percentComplete < 1.0f) {

				_fadeDecorator.setOpacity(1.0f - percentComplete);
				_component.repaint();
			}
		}
	}
}