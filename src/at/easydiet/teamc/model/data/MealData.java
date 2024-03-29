/**
 * This File is part of Easy Diet
 * created on: 22.04.2011
 * created by: Ali Gümüs
 * file: MealData.java
 */
package at.easydiet.teamc.model.data;

import java.util.Set;

/**
 *
 * @author Ali Gümüs
 */
public interface MealData {

    /**
     *
     * @return
     */
    long getMealId();
    /**
     *
     * @return
     */
    MealCodeData getMealCodeData();
    /**
     *
     * @return
     */
    Set<DietParameterData> getDietParameterData();
    /**
     *
     * @return
     */
    Set<MealLineData> getMealLineData();
}
