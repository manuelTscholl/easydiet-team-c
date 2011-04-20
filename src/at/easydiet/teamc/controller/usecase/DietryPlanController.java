/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.controller.usecase;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.data.DietParameterTemplateData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.util.Event;
import at.easydiet.teamc.util.EventArgs;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Stephan Svoboda
 */
public class DietryPlanController extends Event<EventArgs>{

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicDelegationController.class);
    private static volatile DietryPlanController _dietryPlanController = null;
    // instance variables
    private DatabaseController _dbController;

    private DietryPlanController(Object sender){
        super(sender);
        _dbController=DatabaseController.getInstance();
    }


    /**
     * Singelton
     * @return Will return the existing Instance or if no exists a new Instance of {@link SearchPatientController}
     */
    public static DietryPlanController getInstance() {
        if (_dietryPlanController == null) {
            _dietryPlanController = new DietryPlanController(null);
        }
        return _dietryPlanController;

    }

    public DietryPlanData newDietryPlan(Date startdate, Date enddate, Set<DietParameterTemplateData> dptd){
        DietryPlanData temp=new DietryPlanData() {

            public int getDuration() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        return temp;

    }

}
