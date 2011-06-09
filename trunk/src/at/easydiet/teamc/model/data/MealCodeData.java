/**
 * This File is part of Easy Diet
 * created on: 18.04.2011
 * created by: Ali Gümüs
 * file: MealCodeData.java
 */

package at.easydiet.teamc.model.data;

import at.easydiet.teamc.model.TimeSpanBo;

/**
 *
 * @author Stephan Svoboda
 */
public interface MealCodeData {

    /**
     *
     * @return
     */
    String getName();
    /**
     *
     * @return
     */
    String getCode();
    
    /**
     * 
     * @return
     */
    TimeSpanBo getTimeSpan();
}
