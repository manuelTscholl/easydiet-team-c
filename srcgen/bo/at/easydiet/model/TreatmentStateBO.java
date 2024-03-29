package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a TreatmentState instance.
 */
public enum TreatmentStateBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private TreatmentState _model;

    /**
     * Initializes a new instance of the {@link TreatmentStateBO} enum.
     * @param model the enum value
     */
	private TreatmentStateBO(String value)
	{
		_model = new TreatmentState(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link TreatmentState} object.
	 */
 	public TreatmentState getModel()
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
	public static TreatmentStateBO getForModel(TreatmentState model)
	{
		for(TreatmentStateBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}