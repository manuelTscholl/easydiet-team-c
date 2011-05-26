package at.easydiet.model;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

/**
 * This class encapsules a ContactType instance.
 */
public enum ContactTypeBO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private ContactType _model;

    /**
     * Initializes a new instance of the {@link ContactTypeBO} enum.
     * @param model the enum value
     */
	private ContactTypeBO(String value)
	{
		_model = new ContactType(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link ContactType} object.
	 */
 	public ContactType getModel()
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
	public static ContactTypeBO getForModel(ContactType model)
	{
		for(ContactTypeBO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}