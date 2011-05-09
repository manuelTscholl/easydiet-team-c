/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: RecipeTableView.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.TableView;

import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * Represents a table view for recipes
 * 
 * @author Michael
 */
public class RecipeTableView extends TableView {

	// instance variables
	private List<HashMap<String, Object>> _recipes;

	{
		_recipes = new ArrayList<HashMap<String, Object>>();
		setTableData(_recipes);
	}

	/**
	 * Add recipe to table view
	 * 
	 * @param r Recipe to add
	 */
	public void addRecipe(RecipeData r) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("recipe", r); // recipe name is set by overridden toString
								// method of RecipeData
		map.put("quantity", 100);
		_recipes.add(map);
	}

	/**
	 * Removes a recipe from the table view
	 * 
	 * @param r
	 */
	public void removeRecipe(RecipeData r) {
		List<HashMap<String, Object>> tableData = getTableData();

		// search corresponding hashmap and remove it
		for (HashMap<String, Object> h : tableData) {
			if (h.get("recipe") == r) {
				tableData.remove(h);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> getTableData() {
		return (List<HashMap<String, Object>>) super.getTableData();
	}

	/**
	 * Check if a RecipeData is already in list
	 * 
	 * @param r
	 * @return
	 */
	public boolean containsRecipeData(RecipeData r) {
		List<HashMap<String, Object>> tableData = getTableData();
		for (HashMap<String, Object> h : tableData) {
			if (h.get("recipe") == r) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Set unit in a specific row
	 * 
	 * @param p
	 * @param index
	 */
	public void setUnit(ParameterDefinitionUnitData p, int index) {
		HashMap<String, Object> map = _recipes.get(index);
		final String s = "unit";

		// check if already set
		if (map.containsKey(s)) {
			map.remove(s);
		}

		map.put(s, p);
	}
}
