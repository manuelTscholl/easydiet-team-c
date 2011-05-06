/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: DoubleValidator.java
 */
package at.easydiet.teamc.view.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import org.apache.pivot.wtk.validation.Validator;

/**
 * Validate double input fields
 * 
 * @author Michael
 */
public class DoubleValidator implements Validator {

	protected static final DecimalFormat FORMAT = new DecimalFormat("0.0");
	static {
		FORMAT.setParseBigDecimal(true);
	}

	@Override
	public boolean isValid(String text) {
		boolean valid = true;

		// check for valid input
		if (text.length() > 0) {
			ParsePosition parsePosition = new ParsePosition(0);
			BigDecimal numericAmount = (BigDecimal) FORMAT.parse(text,
					parsePosition);
			valid = (numericAmount != null && numericAmount.scale() <= 2
					&& numericAmount.signum() >= 0
					&& parsePosition.getErrorIndex() == -1 && parsePosition
					.getIndex() == text.length());
		}

		return valid;
	}
}
