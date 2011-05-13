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
		final String actualGram = "actualGram";

		// check if row exists
		if (map != null) {

			// check if already set
			if (map.get(paramString) == p) {
				map.remove(paramString);
			}
		} else {
			map = new HashMap<String, Object>();
			_tableData.add(map);
		}

		map.put(paramString, p);
		map.put(unitString, p.getUnit());
		map.put(operatorString, p.getCheckOperator());
		map.put(actualGram, p.getActualOnGram());

	}

	private HashMap<String, Object> getRowByNutrimentParameter(
			NutrimentParameterRuleData n) {
		for (HashMap<String, Object> map : _tableData) {
			if (map.get("parameter") == n || map.get("parameter") == null) {
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

	/**
	 * Get a parameter by his row
	 * 
	 * @param row
	 * @return
	 */
	public NutrimentParameterRuleData getParameter(int row) {
		HashMap<String, Object> map = _tableData.get(row);
		return (NutrimentParameterRuleData) map.get("parameter");
	}
}
