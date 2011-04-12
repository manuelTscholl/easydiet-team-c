/**
 * This File is part of Easy Diet
 * created on: 12.04.2011
 * created by: Michael
 * file: SearchPatientController.java
 */
package at.fhv.easydiet.controller.usecase;

import at.fhv.teamc.easydiet.controller.BusinessLogicController;
import at.fhv.teamc.easydiet.controller.DatabaseController;
import at.fhv.teamc.easydiet.controller.PatientData;
import at.fhv.teamc.easydiet.model.PatientBo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller for use case: search patient
 *
 * @author Michael
 */
public class SearchPatientController {

// class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BusinessLogicController.class);
    // instance variables
    DatabaseController _dbController;

    /**
     * Get patient from search string
     * @param search
     * @return
     */
    public Set<PatientData> getPatients(String search) {
        String[] t;

        String name1 = "";
        String name2 = "";
        String svn = "";
        Date date = new Date();

        String regexName = "[a-zA-Z]{2,}";
        String regexSVN = "[0-9]{10}";
        String regexDATE = "[0-9]{1,2}[.][0-9]{1,2}[.][0-9]{1,4}";

        t = search.split(" ");

        // check if search string exists
        if (t == null) {
            return null;
        }

        for (int i = 0; i < t.length; i++) {

            // name match
            if (t[i].matches(regexName)) {

                if (name1.equals("")) {

                    name1 = t[i];
                } else {
                    name2 = t[i];
                }

                // svn match
            } else if (t[i].matches(regexSVN)) {

                svn = t[i];

                // date match
            } else if (t[i].matches(regexDATE)) {

                try {

                    // date format dd.MM.yyyy or dd.MM.yy
                    SimpleDateFormat sdfFormat = new SimpleDateFormat("dd.MM.y");
                    date = sdfFormat.parse(t[i]);
                } catch (ParseException p) {
                    LOGGER.error(p);
                }
            }
        }

        Set<PatientData> patientDatas = new HashSet<PatientData>();
        Set<PatientBo> patients = _dbController.getPatients(name1, name2, svn, date);

        // check if database set is not empty
        if (patients != null) {
            
            // convert business patient data to gui patient data
            for (PatientBo pbo : patients) {
                patientDatas.add(pbo);
            }
        }

        return patientDatas;
    }
}
