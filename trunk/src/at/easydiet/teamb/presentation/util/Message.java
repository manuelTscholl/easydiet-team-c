/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	15.04.2011
 */

/**
 * 
 */
package at.easydiet.teamb.presentation.util;

import org.apache.pivot.wtk.Component;

public class Message implements Comparable<Message> {
	private MessageType _messageType;
	private Component _component;
	private String _messageText;

	/**
	 * Initialize a new {@link Message} object
	 * 
	 * @param messageType type of the message
	 * @param component the component containing an invalid value
	 * @param messageText text of the Message
	 */
	public Message(MessageType messageType, Component component,
			String messageText) {
		_messageType = messageType;
		_component = component;
		_messageText = messageText;
	}

	public Message(MessageType messageType, String messageText) {
		this(messageType, null, messageText);
	}

	public Message(MessageType messageType) {
		this(messageType, null);
	}

	/**
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return _messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		_messageType = messageType;
	}

	/**
	 * Gets the component.
	 * 
	 * @return the component
	 */
	public Component getComponent() {
		return _component;
	}

	/**
	 * Sets the component.
	 * 
	 * @param component the new component
	 */
	public void setComponent(Component component) {
		_component = component;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return _messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		_messageText = messageText;
	}

	@Override
	public int compareTo(Message o) {
		return getMessageType().compareTo(o.getMessageType());
	}

	@Override
	public String toString() {
		return super.toString() + " '" + getMessageText() + "'";
	}
}
