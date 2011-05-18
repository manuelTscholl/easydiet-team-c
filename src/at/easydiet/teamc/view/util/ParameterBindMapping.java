/**
 * This File is part of EasyDiet
 * Created on: 26.04.2011
 * Created by: Michael Sieber
 * File: ParameterBindMapping.java
 */
package at.easydiet.teamc.view.util;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.pivot.wtk.TextInput;

/**
 * Mapping for parameter value inputs
 * 
 * @author Michael Sieber
 */
public class ParameterBindMapping implements TextInput.TextBindMapping {
	protected static final DecimalFormat FORMAT = new DecimalFormat("0.0");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TextInput.TextBindMapping#toString(java.lang.Object)
	 */
	@Override
	public String toString(Object value) {
		Double d = Double.parseDouble(value.toString());
		return FORMAT.format(d);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.pivot.wtk.TextInput.TextBindMapping#valueOf(java.lang.String)
	 */
	@Override
	public Object valueOf(String text) {

		// in case of an empty string to validate, return a default value, to
		// avoid an NPE
		if (text.length() < 1)
			return new String("0");

		try {
			return FORMAT.parse(text);
		} catch (ParseException ex) {
			throw new NumberFormatException(ex.getMessage());
		}
	}

}
