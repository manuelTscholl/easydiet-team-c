/**
 * This File is part of Easy Diet
 * created on: 18.04.2011
 * created by: Ali Gümüs
 * file: SearchRecipeController.java
 */
package at.easydiet.teamc.controller.usecase;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.util.Event;
import at.easydiet.teamc.util.EventArgs;
import java.util.Set;

/**
 *
 * @author Ali Gümüs
 */
public class SearchRecipeConroller {

    //class Variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicDelegationController.class);
    private static volatile  SearchRecipeConroller _searchRecipeController = null;
    //instance variables
    private DatabaseController _dbController;
    private Set<PatientData> _lastSearchResult;


    /**
     * Singelton
     * @return Will return the existing Instance or if no exists a new Instance of {@link SearchRecipeController}
     */
    
     public static SearchRecipeConroller getInstance() {
            if(_searchRecipeController==null){
                    _searchRecipeController=new SearchRecipeConroller();
             }
             return _searchRecipeController;
        }
}
