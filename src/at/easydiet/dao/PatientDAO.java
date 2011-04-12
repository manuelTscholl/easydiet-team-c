package at.easydiet.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import at.easydiet.model.Patient;
import at.fhv.teamc.easydiet.controller.DatabaseController;

/**
 * A DAO implementation for Patient objects.
 */
public class PatientDAO extends GenericHibernateDAO<Patient, Long>
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PatientDAO.class);

    public List<Patient> findBySpecialProperties(String name1,String name2, String svn, Date birthday)
    {
        
        //Solves the problem that fore and lastname can be insert in a different order
        Patient tempPFornameFirst = new Patient();
        tempPFornameFirst.setBirthday(birthday);
        tempPFornameFirst.setInsuranceNumber(svn==""?null:svn);
        tempPFornameFirst.setForename(name1==""?null:name1);
        tempPFornameFirst.setLastname(name2==""?null:name2);
        
//FIXME: Geburtsdatum Suche funktioniert noch nicht ganz
//FIXME: Vor und Zuname muss noch vertuascht abgefragt werden
        

//        Patient tempPLastnameFirst = new Patient();
//        tempPLastnameFirst.setBirthday(birthday);
//        tempPLastnameFirst.setInsuranceNumber(svn);
//        tempPLastnameFirst.setForename(name2);
//        tempPLastnameFirst.setLastname(name1);
        
        LOGGER.info(String.format("Searching for %s %s %s %s","",name1,name2,svn,birthday ));
        
        Example firstPatientSearch = Example.create(tempPFornameFirst)
        .excludeZeroes()
        .ignoreCase()
        .enableLike(MatchMode.ANYWHERE)
        ;
        
        List<Patient> results = getSession().createCriteria(Patient.class)
        .add(Example.create(tempPFornameFirst))
        .list();
        LOGGER.info(String.format("Results found %s",results.size()));
        
        return results;
    }
    
    
}