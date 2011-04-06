/**
 * This File is part of Easy Diet
 * created on: 06.04.2011
 * created by: Michael
 * file: PatientListener.java
 */

package at.fhv.teamc.easydiet.view;

import at.easydiet.model.Patient;

/**
 * Listener for gui patient chooser
 * @author Michael
 */
public interface PatientListener {

    public void chooseActivePatient(Patient p);
}