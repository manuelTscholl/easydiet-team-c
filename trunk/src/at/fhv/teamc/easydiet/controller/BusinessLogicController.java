/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: BusinesLogicController.java
 */
package at.fhv.teamc.easydiet.controller;

import at.fhv.teamc.easydiet.model.PatientBo;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

/**
 * Controller for business logic
 * 
 * @author Michael
 */
public class BusinessLogicController
{

    // class variables
    public static final org.apache.log4j.Logger LOGGER     = org.apache.log4j.Logger
                                                                   .getLogger(BusinessLogicController.class);

    // connects to the database and gets all the needed data.
    DatabaseController                          controller = new DatabaseController();

    /**
     * Not implemented
     * 
     * @param search
     */
    public void searchPatient(String search)
    {
        LOGGER.trace("Sucheingabe: " + search);
    }

    /**
     * Not implemented
     * 
     * @param p
     */
    public void chooseActivePatient(PatientBo p)
    {
        LOGGER.trace("Patient: " + p.getInsuranceNumber());
    }

    /**
     * This method calls the DB-controller which finally gets the required
     * patient objects
     * 
     * @param search
     *            the complete string which will be parsed in firstname,
     *            secondname, svn and date
     * @return a list of PatientBo's which matched to the specified search
     *         string
     */
    public Set<PatientBo> getPatients(String search)
    {

        String[] t;

        String name1 = "";
        String name2 = "";
        String SVN = "";
        Date date = new Date();

        String regexName = "/[a-z]{2,}/i";
        String regexSVN = "/[0-9]{10}";
        String regexDATE = "/[0-9]{1,2}[.][0-9]{1,2}[.][0-9]{1,4}";

        t = search.split(" ");

        if (t == null || t.length > 4)
        {

            return null;
        }

        for (int i = 0; i < t.length; i++)
        {

            if (t[i].matches(regexName))
            {

                if (name1.equals(""))
                {

                    name1 = t[i];
                }
                else
                {
                    name2 = t[i];
                }
            }
            else if (t[i].matches(regexSVN))
            {

                SVN = t[i];
            }
            else if (t[i].matches(regexDATE))
            {

                try
                {
                    date = DateFormat.getInstance().parse(t[i]);
                }
                catch (ParseException p)
                {

                    LOGGER.error(p);
                }
            }
        }
        return controller.getPatients(name1, name2, SVN, date);
    }
}
