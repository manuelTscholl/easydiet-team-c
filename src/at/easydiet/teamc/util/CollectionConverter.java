/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: CollectionConverter.java
 */
package at.easydiet.teamc.util;

/**
 * Convert a java.util.List to a org.apache.pivot.collections.List
 * and vice versa
 * @author Michael Sieber
 */
public class CollectionConverter<T> {

    /**
     * Convert from Java List to Pivot List
     * @param <T> List Data type
     * @param pivotList to convert
     * @return java list
     */
    public static <T> java.util.List<T> convertToJavaList(
            org.apache.pivot.collections.List<T> pivotList) {
        if (pivotList != null) {

            // convert
            java.util.List<T> javaList = new java.util.ArrayList<T>();
            for (T value : pivotList) {
                javaList.add(value);
            }
            return javaList;
        } else {
            return null;
        }
    }

    /**
     * Convert from Java Set to Pivot Set
     * @param <T> List Data type
     * @param pivotList to convert
     * @return java list
     */
    public static <T> java.util.Set<T> convertToJavaSet(
            org.apache.pivot.collections.Set<T> pivotSet) {
        if (pivotSet != null) {

            // convert
            java.util.Set<T> javaSet = new java.util.HashSet<T>();
            for (T value : pivotSet) {
                javaSet.add(value);
            }
            return javaSet;
        } else {
            return null;
        }
    }

    /**
     * Convert from Java List to Pivot List
     * @param <T> List Data type
     * @param javaList to convert
     * @return java list
     */
    public static <T> org.apache.pivot.collections.Collection<T> convertToPivotList(
            java.util.Collection<T> javaList) {
        if (javaList != null) {

            // convert
            org.apache.pivot.collections.List<T> pivotList = new org.apache.pivot.collections.ArrayList<T>();
            for (T value : javaList) {
                pivotList.add(value);
            }

            return pivotList;
        } else {
            return null;
        }
    }

    /**
     * Convert from Java List to Pivot List
     * @param <T> List Data type
     * @param javaList to convert
     * @return java list
     */
    public static <T> org.apache.pivot.collections.Set<T> convertToPivotSet(
            java.util.Set<T> javaSet) {
        if (javaSet != null) {

            // convert
            org.apache.pivot.collections.Set<T> pivotSet = new org.apache.pivot.collections.HashSet<T>();
            for (T value : javaSet) {
                pivotSet.add(value);
            }

            return pivotSet;
        } else {
            return null;
        }
    }
}
