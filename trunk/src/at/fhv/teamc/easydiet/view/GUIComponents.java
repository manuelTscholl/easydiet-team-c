/**
 * This File is part of Easy Diet
 * created on: 11.04.2011
 * created by: Michael
 * file: GUIComponents.java
 */
package at.fhv.teamc.easydiet.view;

import java.util.HashMap;

/**
 * Contains all registered GUI Components by id
 * @author Michael
 */
public class GUIComponents {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContentTabPane.class);
    private static HashMap<String, Object> sCOMPONENTS = new HashMap<String, Object>() {};

    /**
     * Get a component by his id
     * @param id
     * @return
     */
    public static Object get(String id) {
        return sCOMPONENTS.get(id);
    }

    /**
     * Add a new component
     * @param id Component name
     * @param o Component to add
     */
    public static void put(String id, Object o) {
        sCOMPONENTS.put(id, o);
    }
}
