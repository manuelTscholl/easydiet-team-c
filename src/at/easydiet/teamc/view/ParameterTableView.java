/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: ParameterTableView.java
 */
package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.TableView;

import at.easydiet.teamc.model.data.ParameterDefinitionData;

/**
 * 
 * @author Michael
 */
public class ParameterTableView extends TableView implements Bindable {

	// instance variables
	private List<ParameterDefinitionData> _parameterCache;
	private List<HashMap<String, ParameterDefinitionData>> _tableData;
	private ListButton _parameterListButton;

	{
		_tableData = new ArrayList<HashMap<String, ParameterDefinitionData>>();
	}

	/*
	 * @see
	 * org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections
	 * .Map, java.net.URL, org.apache.pivot.util.Resources)
	 */
	@Override
	public void initialize(Map<String, Object> map, URL url, Resources resources) {
		_parameterListButton = (ListButton) map.get("parameterListButton");
	}

	/**
	 * Set the parameters
	 * 
	 * @param param
	 */
	public void setParameterData(List<ParameterDefinitionData> param) {
		_parameterCache = param;
		_parameterListButton.setListData(_parameterCache);
	}

	/**
	 * Add a new parameter
	 */
	public void addParameter() {

	}

	/**
	 * Remove a specific parameter
	 * 
	 * @param p
	 */
	public void removeParameter(ParameterDefinitionData p) {
		// List<HashMap<String, ParameterDefinitionData>> tableData =
		// getTableData();
		//
		// // search corresponding hashmap and remove it
		// for (HashMap<String, ParameterDefinitionData> h : tableData) {
		// if (h.get("parameter") == p) {
		// tableData.remove(h);
		// break;
		// }
		// }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, ParameterDefinitionData>> getTableData() {
		return (List<HashMap<String, ParameterDefinitionData>>) super
				.getTableData();
	}
}
