package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a DietParameterType instance.
 */
public enum DietParameterTypeBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private DietParameterType _model;

    /**
     * Initializes a new instance of the {@link DietParameterTypeBO} enum.
     * @param model the enum value
     */
	private DietParameterTypeBO(String value)
	{
		_model = new DietParameterType(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link DietParameterType} object.
	 */
 	public DietParameterType getModel()
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
	public static DietParameterTypeBO getForModel(DietParameterType model)
	{
		for(DietParameterTypeBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}