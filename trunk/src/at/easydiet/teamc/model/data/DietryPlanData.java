/**
 *
 * @author Michael Sieber
 * 16.04.2011
 * DietryPlanData.java
 */

package at.easydiet.teamc.model.data;

import at.easydiet.teamc.model.DietPlanParameterCollectionVo;
import java.util.Set;


public interface DietryPlanData {

    int getDuration();
    MealData getMealData();
    DietPlanParameterCollectionVo getDietPlanParameterCollectionVo();
    Set<MealData> getMealsByDay(int day);
}
