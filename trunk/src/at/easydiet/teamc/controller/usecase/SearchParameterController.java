/**
 * This File is part of Easy Diet
 * created on: 22.04.2011
 * created by: Ali Gümüs
 * file: SearchParameterController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.List;
import java.util.Set;

import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.DietParameterBo;
import at.easydiet.teamc.model.data.PatientData;

public class SearchParameterController {
	
	// class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SearchParameterController.class);
    private static volatile SearchParameterController _searchParameterController = null;
    //instance variables
    private DatabaseController _dbController;
    private Set<PatientData> _lastSearchResult;
    private boolean _running = false;
    private final int _sleepBetweenEachSearchLoop = 500;


        /**
	 * Returns a set of all DietParameterTemplateBo's
	 * @return
	 */
	public List<DietParameterBo> getAllParameters() {
		List<DietParameterBo> parameters=_dbController.getInstance().getAllParameters();
		return parameters;
	}

        /**
         * Singelton
         * @return Will return the existing Instance or if no exists a new Instance of {@link SearchParameterController}
         */
	public static SearchParameterController getInstance() {
		if(_searchParameterController==null){
			_searchParameterController=new SearchParameterController();
		}
		return _searchParameterController;
	}

}
