/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: RecipeNameValidator.java
 */
package at.easydiet.teamc.view.util;

import org.apache.pivot.wtk.validation.Validator;

/**
 * Validator for recipe names
 * 
 * @author Michael
 */
public class RecipeNameValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.pivot.wtk.validation.Validator#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String text) {

		if (text.length() > 0) {

			String regex = "^[a-zA-Z/ 0-9]{0,}$";

			// check if matches
			if (text.matches(regex)) {
				return true;
			}
		}
		return false;
	}
}
