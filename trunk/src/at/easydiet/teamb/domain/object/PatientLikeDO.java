/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain.object;

import at.easydiet.teamb.application.viewobject.PatientLikeGradeViewable;
import at.easydiet.teamb.domain.IPatientLike;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.Patient;
import at.easydiet.model.PatientLike;

/**
 * The Class PatientLikeDO.
 */
public class PatientLikeDO extends AbstractDO<PatientLike> implements IPatientLike{
	private PatientLike _like;
	
	/**
	 * Instantiates a new patient like do.
	 *
	 * @param like the like
	 */
	public PatientLikeDO(PatientLike like){
		_like = like;
	}
	
	@Override
	public Patient getPatient() {
		return _like.getPatient();
	}

	@Override
	public void setPatient(Patient patient) {
		_like.setPatient(patient);
	}

	@Override
	public String getBlsPattern() {
		return _like.getBlsPattern();
	}

	@Override
	public void setBlsPattern(String blsPattern) {
		_like.setBlsPattern(blsPattern);
	}

	@Override
	public PatientLikeGradeViewable getGrade() {
		return new PatientLikeGradeDO(_like.getGrade());
	}

	@Override
	public void setGrade(PatientLikeGradeViewable grade) {
		_like.setGrade(((PatientLikeGradeDO)grade).getModel());
		
	}

	@Override
	public String getNotice() {
		return _like.getNotice();
	}

	@Override
	public void setNotice(String notice) {
		_like.setNotice(notice);
		
	}

	@Override
	public PatientLike getModel() {
		return _like;
	}
}
