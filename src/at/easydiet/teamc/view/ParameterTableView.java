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

import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;

/**
 * 
 * @author Michael
 */
public class ParameterTableView extends TableView {

	// instance variables
	private List<ParameterDefinitionData> _parameterCache;
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
	public void setParameterData(ParameterDefinitionData p, int index) {
		HashMap<String, Object> map = _tableData.get(index);
		final String paramString = "parameter";

		// check if already set
		if (map.containsKey(paramString)) {
			map.remove(paramString);
		}

		map.put(paramString, p);
	}

	/**
	 * Set a unit in a specific row
	 * 
	 * @param p
	 * @param index
	 */
	public void setCheckOperator(CheckOperatorData p, int index) {
		HashMap<String, Object> map = _tableData.get(index);
		final String s = "checkOperator";

		// check if already set
		if (map.containsKey(s)) {
			map.remove(s);
		}

		map.put(s, p);
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
}
