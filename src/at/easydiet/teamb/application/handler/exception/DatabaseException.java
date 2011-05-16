/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	25.04.2011
 */

package at.easydiet.teamb.application.handler.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.hibernate.HibernateException;

/**
 * The Database Exception will be thrown when hibernate throws a error
 * 
 * @author TeamB
 */
public class DatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778646163178741359L;
	
	HibernateException _hibernateException;

	
	/**
	 * Instantiates a new database exception and saves the Exception.
	 *
	 * @param ex the {@link Exception}
	 */
	public DatabaseException(HibernateException ex) {
		_hibernateException = ex;
	}

	@Override
	public void printStackTrace() {
		_hibernateException.printStackTrace();
	}
	
	@Override
	public void printStackTrace(PrintStream s) {
		_hibernateException.printStackTrace(s);
	}
	
	@Override
	public void printStackTrace(PrintWriter s) {
		_hibernateException.printStackTrace(s);
	}
}
