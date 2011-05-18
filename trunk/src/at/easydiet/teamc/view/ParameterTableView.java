/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: ParameterTableView.java
 */
package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamc.model.data.NutrimentParameterData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;

/**
 * Represents a table view for parameters
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
	 * @param param Parameter to add in the tableview
	 * @param totalAmount The Parameter amount
	 */
	public void setParameterData(NutrimentParameterRuleData p, float totalAmount) {
		HashMap<String, Object> map = getRowByNutrimentParameter(p);
		final String paramString = "parameter";
		final String unitString = "unit";
		final String valueString = "value";
		final String operatorString = "checkOperator";
		final String actualGram = "actualGram";
		final String error = "error";
		URL errorImage = ParameterTableView.class.getResource("bxml/error.png");

		// check if row exists
		if (map != null) {
			map.clear();
		} else {
			map = new HashMap<String, Object>();
			_tableData.add(map);
		}

		map.put(paramString, p);
		map.put(unitString, p.getUnit());
		map.put(valueString, p.getValue());
		map.put(error, errorImage);
		map.put(operatorString, p.getCheckOperator());
		map.put(actualGram, totalAmount);

		try {

			Image i = null;
			if (p.IsViolated()) {
				i = Image.load(errorImage);
			}
			map.put(error, i);
		} catch (TaskExecutionException e) {
			// ignore
		}

		// update view
		this.repaint();
	}

	/**
	 * Get a row by the nutriment parameter
	 * 
	 * @param n NutrimentParameter for which a row is searched
	 * @return TableViewRow for the corresponding NutrimentParameter
	 */
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
	 * @param index Row index of the parameter which has to be removed
	 */
	public void removeParameter(int index) {
		final int count = 1;
		_tableData.remove(index, count);
	}

	/**
	 * Get the data of this tableview
	 * 
	 * @return The Table data
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> getTableData() {
		return (List<HashMap<String, Object>>) super.getTableData();
	}

	/**
	 * Get the value from a specific row
	 * 
	 * @param row from which the value is wanted
	 * @return Value of the table row
	 */
	public double getValue(int row) {
		HashMap<String, Object> map = _tableData.get(row);
		return Double.parseDouble(map.get("value").toString());
	}

	/**
	 * Get a parameter by his row
	 * 
	 * @param row from which the parameter is wanted
	 * @return The parameter of this row
	 */
	public NutrimentParameterRuleData getParameter(int row) {

		// check if row exists
		if (row == -1 || row >= _tableData.getLength()) {
			return null;
		}

		HashMap<String, Object> map = _tableData.get(row);
		return (NutrimentParameterRuleData) map.get("parameter");
	}

	/**
	 * Check if the table contains a specific parameter
	 * 
	 * @param n Parmeter to check
	 * @return true if the tableview contains the entered parameter
	 */
	public boolean containsParameter(NutrimentParameterData n) {
		for (HashMap<String, Object> map : _tableData) {
			NutrimentParameterRuleData rule = (NutrimentParameterRuleData) map
					.get("parameter");
			if (n.getName().equals(rule.getName())) {
				return true;
			}
		}

		return false;
	}
}
