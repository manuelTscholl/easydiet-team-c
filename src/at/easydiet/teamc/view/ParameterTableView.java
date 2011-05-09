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

import at.easydiet.teamc.model.data.ParameterDefinitionData;

/**
 * 
 * @author Michael
 */
public class ParameterTableView extends TableView {

	// instance variables
	private List<ParameterDefinitionData> _parameterCache;
	private List<HashMap<String, ParameterDefinitionData>> _tableData;

	{
		_tableData = new ArrayList<HashMap<String, ParameterDefinitionData>>();
		setTableData(_tableData);
	}

	/**
	 * Set a parameter in specific row
	 * 
	 * @param param
	 */
	public void setParameterData(ParameterDefinitionData p, int index) {
		HashMap<String, ParameterDefinitionData> map = _tableData.get(index);
		final String paramString = "parameter";

		// check if already set
		if (map.containsKey(paramString)) {
			map.remove(paramString);
		}

		map.put(paramString, p);
	}

	/**
	 * Add a new parameter
	 */
	public void addParameter() {
		HashMap<String, ParameterDefinitionData> map = new HashMap<String, ParameterDefinitionData>();
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
	public List<HashMap<String, ParameterDefinitionData>> getTableData() {
		return (List<HashMap<String, ParameterDefinitionData>>) super
				.getTableData();
	}
}
