
package at.easydiet.teamc.model.data;

import java.util.Set;

public class ParameterCheckResult {

	private Set<DietParameterData> _dietParameterData;
	
	public ParameterCheckResult(){
		
	}
	
	public void addDietParameter(DietParameterData toadd){
		_dietParameterData.add(toadd);
	}
	
	
	public Set<DietParameterData> getCheckResult(){
		return _dietParameterData;
	}
	
	
    
}
