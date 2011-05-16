/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

/**
 * 
 */
package at.easydiet.teamb.application.util.ValidatorArgs;

/**
 * The Enum PatientStateErrorField.
 *
 * @author TeamB
 */
public enum PatientStateErrorField {
	/** Datum und Zeit*/
	DATETIME, 
	/** Anamnese */
	ANAMNESIS, 
	/** Gewicht */
	WEIGHT, 
	/** Gewichtsperzentil */
	WEIGHTPERCENTILE, 
	/** Groeße */
	HEIGHT, 
	/** Groeßenperzentil */
	HEIGHTPERCENTILE, 
	/** Compliance */
	COMPLIANCE, 
	/** Motivation */
	MOTIVATION, 
	/** Type */
	TYPE, 
	/** Ersteller */
	CREATOR, 
	/** Laborbefund */
	LABORREPORTS, 
	/** Patient */
	PATIENT;
}
