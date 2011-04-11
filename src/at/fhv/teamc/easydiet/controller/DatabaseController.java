/**
 * This file is part of Easy Diet.
 *   created on: 11.04.2011
 *   created by: Manuel
 *   file: DatabaseController.java
 */
package at.fhv.teamc.easydiet.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientDAO;
import at.easydiet.model.Patient;
import at.fhv.teamc.easydiet.model.*;

/**
 * @author Manuel
 *
 */
public class DatabaseController
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(DatabaseController.class);

    /** 
     * Initializes a new instance of the {@link DatabaseController} class. 
     */
    public DatabaseController()
    {
        super();
    }
    
    /**
     * Searches in the Database for the special Patient 
     * @param name1
     * @param name2
     * @param svn the insurance Number of the Patient
     * @param birthday
     * @return all Patients with the wanted data as a HashSet
     */
    public Set<PatientBo> getPatients(String name1,String name2,  String svn, Date birthday)
    {
        
        PatientDAO patientDao = DAOFactory.getInstance().getPatientDAO();
        Set<PatientBo> patients = new HashSet<PatientBo>();
        
        //Solves the problem that fore and lastname can be insert in a different order
        Patient tempPFornameFirst = new Patient();
        tempPFornameFirst.setBirthday(birthday);
        tempPFornameFirst.setInsuranceNumber(svn);
        tempPFornameFirst.setForename(name1);
        tempPFornameFirst.setLastname(name2);

        Patient tempPLastnameFirst = new Patient();
        tempPLastnameFirst.setBirthday(birthday);
        tempPLastnameFirst.setInsuranceNumber(svn);
        tempPLastnameFirst.setForename(name2);
        tempPLastnameFirst.setLastname(name1);
        
        
        //adding all founded patients to the list
        for (Patient patient : patientDao.findByExample(tempPFornameFirst, null))
        {
            patients.add(new PatientBo(patient));
        }
        for (Patient patient : patientDao.findByExample(tempPLastnameFirst, null))
        {//if the object is not already added
            if(!patients.contains(patient))
            patients.add(new PatientBo(patient));
        }
        
        
        return patients;
    }
    
    
}
