package at.easydiet.teamc.model.data;

import java.util.Set;

/**
 * Checked parameters with the checking result.
 * 
 * @author Michael
 */
public class ParameterCheckResult {

	private Set<DietParameterData> _dietParameterData;

	/**
	 * Instantiates a new parameter check result.
	 */
	public ParameterCheckResult() {

	}

	/**
	 * Adds the diet parameter.
	 * 
	 * @param toadd the toadd
	 */
	public void addDietParameter(DietParameterData toadd) {
		_dietParameterData.add(toadd);
	}

	/**
	 * Gets the check result.
	 * 
	 * @return the check result
	 */
	public Set<DietParameterData> getCheckResult() {
		return _dietParameterData;
	}

}
