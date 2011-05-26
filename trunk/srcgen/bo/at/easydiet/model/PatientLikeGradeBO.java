package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a PatientLikeGrade instance.
 */
public class PatientLikeGradeBO 
{
	private PatientLikeGrade _model;
	
    /**
     * Initializes a new instance of the {@link PatientLikeGradeBO} class.
     */
	public PatientLikeGradeBO()
	{
		// TODO: add default values
		this(new PatientLikeGrade());
	}
	
    /**
     * Initializes a new instance of the {@link PatientLikeGradeBO} class.
     * @param model the original model object
     */
	public PatientLikeGradeBO(PatientLikeGrade model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link PatientLikeGrade} object.
	 */
 	public PatientLikeGrade getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the patientLikeGradeId of this instance. 
     * @return the patientLikeGradeId currently set for this instance.
     */
    public long getPatientLikeGradeId() 
    {
        return _model.getPatientLikeGradeId();
    }
    
    /**       
     * Sets the patientLikeGradeId of this instance. 
     * @param patientLikeGradeId the new patientLikeGradeId of this instance.
     */    
    public void setPatientLikeGradeId(long patientLikeGradeId) 
    {
        _model.setPatientLikeGradeId(patientLikeGradeId);
    }

    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _model.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _model.setName(name);
    }

}