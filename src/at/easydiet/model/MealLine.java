package at.easydiet.model;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MealLine
 */
public class MealLine  implements java.io.Serializable
{

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_alternatives == null) ? 0 : _alternatives.hashCode());
		result = prime * result + ((_info == null) ? 0 : _info.hashCode());
		result = prime * result + (int) (_mealLineId ^ (_mealLineId >>> 32));
		result = prime * result + ((_parent == null) ? 0 : _parent.hashCode());
		result = prime * result + Float.floatToIntBits(_quantity);
		result = prime * result + ((_unit == null) ? 0 : _unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MealLine))
			return false;
		MealLine other = (MealLine) obj;
		if (_alternatives == null) {
			if (other._alternatives != null)
				return false;
		} else if (!_alternatives.equals(other._alternatives))
			return false;
		if (_info == null) {
			if (other._info != null)
				return false;
		} else if (!_info.equals(other._info))
			return false;
		if (_mealLineId != other._mealLineId)
			return false;
		if (_parent == null) {
			if (other._parent != null)
				return false;
		} else if (!_parent.equals(other._parent))
			return false;
		if (Float.floatToIntBits(_quantity) != Float
				.floatToIntBits(other._quantity))
			return false;
		if (_unit == null) {
			if (other._unit != null)
				return false;
		} else if (!_unit.equals(other._unit))
			return false;
		return true;
	}

	private long _mealLineId;
    private float _quantity;
    private Clob _info;
    private List<MealLine> _alternatives = new ArrayList<MealLine>(0);
    private Recipe _recipe;
    private ParameterDefinitionUnit _unit;
    private MealLine _parent;
    private Meal _meal;

    /**
     * Initializes a new instance of the {@link MealLine} class.
     */
    public MealLine() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link MealLine} class.
     * @param quantity the quantity to set for this instance
     * @param recipe the recipe to set for this instance
     * @param unit the unit to set for this instance
     * @param meal the meal to set for this instance
     */
    public MealLine(float quantity, Recipe recipe, ParameterDefinitionUnit unit, Meal meal) 
    {
        _quantity = quantity;
        _recipe = recipe;
        _unit = unit;
        _meal = meal;
    }

    /**
     * Initializes a new instance of the {@link MealLine} class.
     * @param quantity the quantity to set for this instance
     * @param info the info to set for this instance
     * @param alternatives the alternatives to set for this instance
     * @param recipe the recipe to set for this instance
     * @param unit the unit to set for this instance
     * @param parent the parent to set for this instance
     * @param meal the meal to set for this instance
     */
    public MealLine(float quantity, Clob info, List<MealLine> alternatives, Recipe recipe, ParameterDefinitionUnit unit, MealLine parent, Meal meal) 
    {
       _quantity = quantity;
       _info = info;
       _alternatives = alternatives;
       _recipe = recipe;
       _unit = unit;
       _parent = parent;
       _meal = meal;
    }
   
    /**       
     * Gets the mealLineId of this instance. 
     * @return the mealLineId currently set for this instance.
     */
    public long getMealLineId() 
    {
        return _mealLineId;
    }
    
    /**       
     * Sets the mealLineId of this instance. 
     * @param mealLineId the new mealLineId of this instance.
     */    
    public void setMealLineId(long mealLineId) 
    {
        _mealLineId = mealLineId;
    }
    
    /**       
     * Gets the quantity of this instance. 
     * @return the quantity currently set for this instance.
     */
    public float getQuantity() 
    {
        return _quantity;
    }
    
    /**       
     * Sets the quantity of this instance. 
     * @param quantity the new quantity of this instance.
     */    
    public void setQuantity(float quantity) 
    {
        _quantity = quantity;
    }
    
    /**       
     * Gets the info of this instance. 
     * @return the info currently set for this instance.
     */
    public Clob getInfo() 
    {
        return _info;
    }
    
    /**       
     * Sets the info of this instance. 
     * @param info the new info of this instance.
     */    
    public void setInfo(Clob info) 
    {
        _info = info;
    }
    
    /**       
     * Gets the alternatives of this instance. 
     * @return the alternatives currently set for this instance.
     */
    public List<MealLine> getAlternatives() 
    {
        return _alternatives;
    }
    
    /**       
     * Sets the alternatives of this instance. 
     * @param alternatives the new alternatives of this instance.
     */    
    public void setAlternatives(List<MealLine> alternatives) 
    {
        _alternatives = alternatives;
    }
    
    /**       
     * Gets the recipe of this instance. 
     * @return the recipe currently set for this instance.
     */
    public Recipe getRecipe() 
    {
        return _recipe;
    }
    
    /**       
     * Sets the recipe of this instance. 
     * @param recipe the new recipe of this instance.
     */    
    public void setRecipe(Recipe recipe) 
    {
        _recipe = recipe;
    }
    
    /**       
     * Gets the unit of this instance. 
     * @return the unit currently set for this instance.
     */
    public ParameterDefinitionUnit getUnit() 
    {
        return _unit;
    }
    
    /**       
     * Sets the unit of this instance. 
     * @param unit the new unit of this instance.
     */    
    public void setUnit(ParameterDefinitionUnit unit) 
    {
        _unit = unit;
    }
    
    /**       
     * Gets the parent of this instance. 
     * @return the parent currently set for this instance.
     */
    public MealLine getParent() 
    {
        return _parent;
    }
    
    /**       
     * Sets the parent of this instance. 
     * @param parent the new parent of this instance.
     */    
    public void setParent(MealLine parent) 
    {
        _parent = parent;
    }
    
    /**       
     * Gets the meal of this instance. 
     * @return the meal currently set for this instance.
     */
    public Meal getMeal() 
    {
        return _meal;
    }
    
    /**       
     * Sets the meal of this instance. 
     * @param meal the new meal of this instance.
     */    
    public void setMeal(Meal meal) 
    {
        _meal = meal;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
