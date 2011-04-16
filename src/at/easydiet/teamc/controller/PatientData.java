/**
 * This File is part of Easy Diet
 * created on: 12.04.2011
 * created by: Michael
 * file: PatientData.java
 */

package at.easydiet.teamc.controller;

import java.util.Date;


public interface PatientData {

    public long getPatientId();
    public String getForename();
    public String getLastname();
    public Date getBirthday();
    public String getInsuranceNumber();
}
