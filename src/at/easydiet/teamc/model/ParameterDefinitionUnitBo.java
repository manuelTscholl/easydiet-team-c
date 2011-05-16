package at.easydiet.teamc.model;

import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;

/**
 * Represents a ParameterDefinitionUnit
 */
public class ParameterDefinitionUnitBo implements java.io.Serializable,
		Saveable, ParameterDefinitionUnitData {

	/**
	 * A unique serialization id.
	 */

	private ParameterDefinitionDataTypeBo _type;
	private ParameterDefinitionUnit _ParameterDefinitionUnit;

	/**
	 * Initializes a new instance of the {@link ParameterDefinitionUnit} class.
	 */
	private ParameterDefinitionUnitBo() {

	}

	public ParameterDefinitionUnitBo(
			ParameterDefinitionUnit parameterDefinitionUnit) {
		this._ParameterDefinitionUnit = parameterDefinitionUnit;
	}

	/**
	 * Initializes a new instance of the {@link ParameterDefinitionUnit} class.
	 * 
	 * @param name the name to set for this instance
	 * @param type the type to set for this instance
	 */
	public ParameterDefinitionUnitBo(String name,
			ParameterDefinitionDataTypeBo type) {
		this(new ParameterDefinitionUnit(name,
				type.getParameterDefinitionDataType()));
		_type = type;
	}

	/**
	 * Gets the parameterDefinitionUnitId of this instance.
	 * 
	 * @return the parameterDefinitionUnitId currently set for this instance.
	 */
	public long getParameterDefinitionUnitId() {
		return this.getParameterDefinitionUnit().getParameterDefinitionUnitId();
	}

	/**
	 * Sets the parameterDefinitionUnitId of this instance.
	 * 
	 * @param parameterDefinitionUnitId the new parameterDefinitionUnitId of
	 *            this instance.
	 */
	public void setParameterDefinitionUnitId(long parameterDefinitionUnitId) {
		this.getParameterDefinitionUnit().setParameterDefinitionUnitId(
				parameterDefinitionUnitId);
	}

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	@Override
	public String getName() {
		return this.getParameterDefinitionUnit().getName();
	}

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name the new name of this instance.
	 */
	public void setName(String name) {
		this.getParameterDefinitionUnit().setName(name);
	}

	/**
	 * Gets the type of this instance.
	 * 
	 * @return the type currently set for this instance.
	 */
	public ParameterDefinitionDataTypeBo getType() {
		return this._type;
	}

	/**
	 * Sets the type of this instance.
	 * 
	 * @param type the new type of this instance.
	 */
	public void setType(ParameterDefinitionDataTypeBo type) {
		this.getParameterDefinitionUnit().setType(
				type.getParameterDefinitionDataType());
		this._type = type;
	}

	@Override
	public boolean save() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @return the _ParameterDefinitionUnit
	 */
	protected ParameterDefinitionUnit getParameterDefinitionUnit() {
		return _ParameterDefinitionUnit;
	}

	/**
	 * @param ParameterDefinitionUnit the _ParameterDefinitionUnit to set
	 */
	public void setParameterDefinitionUnit(
			ParameterDefinitionUnit ParameterDefinitionUnit) {
		this._ParameterDefinitionUnit = ParameterDefinitionUnit;
	}

	@Override
	public String toString() {
		return getName();
	}

    @Override
    public boolean equals(Object obj) {
        return this.equals((ParameterDefinitionUnitBo) obj);
    }
    
    public boolean equals(ParameterDefinitionUnitBo parameterDefinitionUnitBo){
        if(this.getParameterDefinitionUnitId()==parameterDefinitionUnitBo.getParameterDefinitionUnitId()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) this.getParameterDefinitionUnitId();
    }



}
