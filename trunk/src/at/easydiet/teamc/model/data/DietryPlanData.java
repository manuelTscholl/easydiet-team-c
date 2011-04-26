/**
 *
 * @author Michael Sieber
 * 16.04.2011
 * DietryPlanData.java
 */

package at.easydiet.teamc.model.data;

import at.easydiet.teamc.model.DietPlanParameterCollectionVo;


public interface DietryPlanData {

    int getDuration();
    MealData getMealData();
    DietPlanParameterCollectionVo getDietPlanParameterCollectionVo();
}
