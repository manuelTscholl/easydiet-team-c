package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a PlanType instance.
 */
public enum PlanTypeBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private PlanType _model;

    /**
     * Initializes a new instance of the {@link PlanTypeBO} enum.
     * @param model the enum value
     */
	private PlanTypeBO(String value)
	{
		_model = new PlanType(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link PlanType} object.
	 */
 	public PlanType getModel()
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
	public static PlanTypeBO getForModel(PlanType model)
	{
		for(PlanTypeBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}