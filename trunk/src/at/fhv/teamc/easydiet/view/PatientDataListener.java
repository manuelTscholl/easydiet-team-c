/**
 * This File is part of Easy Diet
 * created on: 06.04.2011
 * created by: Michael
 * file: PatientDataListener.java
 */

package at.fhv.teamc.easydiet.view;

import at.fhv.teamc.easydiet.model.PatientBo;

/**
 * Listener for patient data
 * @author Michael
 */
public interface PatientDataListener {

    public void updatePatientData(PatientBo p);
}
