/**
 * This File is part of Easy Diet
 * created on: 06.04.2011
 * created by: Michael
 * file: PatientListener.java
 */

package at.easydiet.teamc.view;

import at.easydiet.teamc.controller.PatientData;

/**
 * Listener for gui patient chooser
 * @author Michael
 */
public interface PatientListener {

    public void chooseActivePatient(PatientData p);
}
