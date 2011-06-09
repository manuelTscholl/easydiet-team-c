/**
 *
 * @author Michael Sieber
 * 16.04.2011
 * DietryPlanData.java
 */

package at.easydiet.teamc.model.data;


import java.util.Date;
import java.util.List;
import java.util.Set;

import at.easydiet.teamc.model.TimeSpanBo;


/**
 *
 * @author Stephan Svoboda
 */
public interface DietryPlanData {

    /**
     *
     * @return
     */
    int getDuration();
    /**
     *
     * @return
     */
    MealData getMealData();
    /**
     *
     * @return
     */
    List<DietPlanParameterCollectionVo> getDietPlanParameterCollectionVo();
    /**
     *
     * @param day
     * @return
     */
    Set<MealData> getMealsByDay(int day);
    /**
     * 
     * @return
     */
    public List<MealData> getAllMeals();
    
    /**
     * 
     * @return
     */
    public Date getFirstTimeSpan();
}
