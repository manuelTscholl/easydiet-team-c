/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: ParameterTableView.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.TableView;

import at.easydiet.teamc.exception.ParameterWithoutUnitException;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;

/**
 * 
 * @author Michael
 */
public class ParameterTableView extends TableView {

	// instance variables
	private List<NutrimentParameterRuleData> _parameterCache;
	private List<HashMap<String, Object>> _tableData;

	{
		_tableData = new ArrayList<HashMap<String, Object>>();
		setTableData(_tableData);
	}

	/**
	 * Set a parameter in specific row
	 * 
	 * @param param
	 */
	public void setParameterData(NutrimentParameterRuleData p) {
		HashMap<String, Object> map = getRowByNutrimentParameter(p);
		final String paramString = "parameter";
		final String unitString = "unit";
		final String operatorString = "checkOperator";

		// check if already set
		if (map.containsKey(paramString)) {
			map.remove(paramString);
		}

		map.put(paramString, p);
		try {
			map.put(unitString, p.getUnit());
		} catch (ParameterWithoutUnitException e) {
			// TODO exception handling
		}
		map.put(operatorString, p.getCheckOperator());

	}

	private HashMap<String, Object> getRowByNutrimentParameter(
			NutrimentParameterRuleData n) {
		for (HashMap<String, Object> map : _tableData) {
			if (map.get("parameter") == n) {
				return map;
			}
		}

		return null;
	}

	/**
	 * Add a new parameter
	 */
	public void addParameter() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("value", 0);
		_tableData.add(map);
	}

	/**
	 * Remove a specific parameter
	 * 
	 * @param p
	 */
	public void removeParameter(int index) {
		final int count = 1;
		_tableData.remove(index, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> getTableData() {
		return (List<HashMap<String, Object>>) super.getTableData();
	}

	/**
	 * Get the value from a specific row
	 * 
	 * @param row
	 * @return
	 */
	public double getValue(int row) {
		HashMap<String, Object> map = _tableData.get(row);
		return Double.parseDouble(map.get("value").toString());
	}
}
