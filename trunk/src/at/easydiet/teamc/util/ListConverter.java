/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: ListConverter.java
 */
package at.easydiet.teamc.util;


/**
 * Convert a java.util.List to a org.apache.pivot.collections.List
 * and vice versa
 * @author Michael Sieber
 */
public class ListConverter<T> {

    /**
     * Convert from Java List to Pivot List
     * @param <T> List Data type
     * @param pivotList to convert
     * @return java list
     */
    public static <T> java.util.List<T> convertToJavaList(org.apache.pivot.collections.List<T> pivotList) {

        // convert
        java.util.List<T> javaList = new java.util.ArrayList<T>();
        for (T value : pivotList) {
            javaList.add(value);
        }

        return javaList;
    }

    /**
     * Convert from Java List to Pivot List
     * @param <T> List Data type
     * @param pivotList to convert
     * @return java list
     */
    public static <T> org.apache.pivot.collections.List<T> convertToPivotList(java.util.List<T> javaList) {

        // convert
        org.apache.pivot.collections.List<T> pivotList = new org.apache.pivot.collections.ArrayList<T>();
        for (T value : javaList) {
            pivotList.add(value);
        }

        return pivotList;
    }
}
