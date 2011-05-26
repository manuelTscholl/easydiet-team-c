package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a PatientStateType instance.
 */
public enum PatientStateTypeBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private PatientStateType _model;

    /**
     * Initializes a new instance of the {@link PatientStateTypeBO} enum.
     * @param model the enum value
     */
	private PatientStateTypeBO(String value)
	{
		_model = new PatientStateType(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link PatientStateType} object.
	 */
 	public PatientStateType getModel()
	{
		return _model;
	}
	
	/**
	 * Gets the name of this enum value.
	 * @return the name of this enum value
	 */
	public String getName()
	{
		return _model.getName();
	}
	
	/**
	 * Returns the BusinessObject matching to the specified model object.
	 * @returns the enum value matching to the given model or the default value. 
	 */
	public static PatientStateTypeBO getForModel(PatientStateType model)
	{
		for(PatientStateTypeBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}