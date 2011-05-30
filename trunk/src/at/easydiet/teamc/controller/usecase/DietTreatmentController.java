/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.controller.usecase;



import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.DietPlanBo;
import at.easydiet.teamc.model.DietTreatmentBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stephan Svoboda
 */
public class DietTreatmentController {

    private  PatientBo _activePatient;
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicDelegationController.class);

    public DietTreatmentController() {
    }

    public DietTreatmentController(PatientBo activePatient) {
        this._activePatient = _activePatient;
    }

    /**
     * @return the _activePatient
     */
    public PatientBo getActivePatient() {
        return _activePatient;
    }

    /**
     * @param activePatient the _activePatient to set
     */
    public void setActivePatient(PatientBo activePatient) {
        this._activePatient = activePatient;
    }

    /**
     * Returns all Meals associated to a the active patient.
     *
     * @return List<DietryPlanData>
     */
    public List<DietryPlanData> getAllDietPlans(){
        List<DietryPlanData> temp = new ArrayList<DietryPlanData>();
        for(DietTreatmentBo  dtb: _activePatient.getTreatments()){
            for(DietPlanBo dpb: dtb.getDietPlans()){
                    temp.add(dpb);
            }
        }
        return temp;
    }


    

}
