package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.presentation.util.DietParameterChangedEventArg;

/**
 * Represents a DietParameter
 */
public interface DietParameterViewable extends DietParameterTemplateViewable {

	/**
	 * Gets the actual.
	 * 
	 * @return the actual
	 */
	public String getActual();

	/**
	 * Checks if is possible.
	 * 
	 * @return true, if is possible
	 */
	public boolean isPossible();

	/**
	 * Gets the start of this instance.
	 * 
	 * @return the start currently set for this instance.
	 */
	public GregorianCalendar getStart();

	/**
	 * Adds the PossibleChangeListener
	 *
	 * @param handler the handler
	 */
	public void addPossibleChangeListener(EventListener<DietParameterChangedEventArg> handler);

	/**
	 * Removes the PossibleChangeListener
	 *
	 * @param handler the handler
	 */
	public void removePossibleChangeListener(EventListener<DietParameterChangedEventArg> handler);

}
