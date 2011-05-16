/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import java.util.GregorianCalendar;

import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.presentation.util.DietParameterChangedEventArg;

/**
 * The Interface IDietParameter.
 */
public interface IDietParameter extends IDietParameterTemplate, DietParameterViewable {

	/**
	 * Gets the start of this instance.
	 * 
	 * @return the start currently set for this instance.
	 */
	public abstract GregorianCalendar getStart();

	/**
	 * Sets the start of this instance.
	 * 
	 * @param start
	 *            the new start of this instance.
	 */
	public abstract void setStart(GregorianCalendar start);

	/**
	 * Gets the actual.
	 *
	 * @return the actual
	 */
	public abstract String getActual();

	/**
	 * Sets the actual.
	 *
	 * @param actual the actual to set
	 */
	public abstract void setActual(String actual);

	/**
	 * Checks if is possible.
	 *
	 * @return the possible
	 * isn't Saved in the DB
	 */
	public abstract boolean isPossible();

	/**
	 * Sets the possible.
	 *
	 * @param possible the possible to set
	 * isn't saved in the DB
	 */
	public abstract void setPossible(boolean possible);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.DietParameterViewable#addPossibleChangeListener(at.easydiet.application.util.EventListener)
	 */
	public abstract void addPossibleChangeListener(EventListener<DietParameterChangedEventArg> handler);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.DietParameterViewable#removePossibleChangeListener(at.easydiet.application.util.EventListener)
	 */
	public abstract void removePossibleChangeListener(EventListener<DietParameterChangedEventArg> handler);

}