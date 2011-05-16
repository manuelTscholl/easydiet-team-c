/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	20.04.2011
 */

package at.easydiet.teamb.domain.util;

import java.sql.Clob;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import at.easydiet.dao.HibernateUtil;

/**
 * A converter for Converting Clobs and Strings
 * @author TeamB
 */
public class ClobConverter {
	private static Logger LOGGER = Logger.getLogger(ClobConverter.class);

	/**
	 * Converts a Clob into a string
	 * @param clob Clob, which has to be converted
	 * @return String
	 */
	@SuppressWarnings("finally")
	public static String ClobToString(Clob clob) {
		String text = null;
		try {
			text = clob.getSubString(1, (int) clob.length());
		} catch (SQLException ex) {
			LOGGER.error("Clob cannot be converted to String", ex);
			throw new SQLException("Clob cannot be converted");
		} finally {
			return text;
		}
	}

	/**
	 * Converts a String into a Clob
	 * @param text String, which has to be converted
	 * @return Clob
	 */
	public static Clob StringToClob(String text) {
		if (text == null) {
			return null;
		}
		
		Clob clob = HibernateUtil.currentSession().getLobHelper().createClob(text);
		return clob;
	}
}