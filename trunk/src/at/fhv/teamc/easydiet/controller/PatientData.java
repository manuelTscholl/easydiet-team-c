/**
 * This File is part of Easy Diet
 * created on: 12.04.2011
 * created by: Michael
 * file: PatientData.java
 */

package at.fhv.teamc.easydiet.controller;

import java.util.Date;


public interface PatientData {

    public String getForename();
    public String getLastname();
    public Date getBirthday();
    public String getInsuranceNumber();
}