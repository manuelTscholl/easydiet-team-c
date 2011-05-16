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

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.IPatientLikeGrade;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.PatientLikeGrade;

/**
 * The Class PatientLikeGradeDO.
 */
public class PatientLikeGradeDO extends AbstractDO<PatientLikeGrade> implements IPatientLikeGrade {
	private static Logger LOGGER = Logger.getLogger(PatientLikeGradeDO.class);
	
	private PatientLikeGrade _grade;
	
	/**
	 * Instantiates a new patient like grade do.
	 *
	 * @param grade the grade
	 */
	public PatientLikeGradeDO(PatientLikeGrade grade){
		if (grade == null) {
			LOGGER.debug("Grade is null");
		}
		
		_grade = grade;
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientLikeGrade#getPatientLikeGradeId()
	 */
	@Override
	public long getPatientLikeGradeId() 
    {
        return _grade.getPatientLikeGradeId();
    }
    
    /* (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientLikeGrade#setPatientLikeGradeId(long)
	 */    
    @Override
	public void setPatientLikeGradeId(long patientLikeGradeId) 
    {
    	_grade.setPatientLikeGradeId(patientLikeGradeId);
    }
    
    /* (non-Javadoc)
	 * @see at.easydiet.model.PatientGradViewable#getName()
	 */
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientLikeGrade#getName()
	 */
	@Override
	public String getName() 
    {
        return _grade.getName();
    }
    
    /* (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientLikeGrade#setName(java.lang.String)
	 */    
    @Override
	public void setName(String name) 
    {
    	_grade.setName(name);
    }

	@Override
	public PatientLikeGrade getModel() {
		return _grade;
	}
}
