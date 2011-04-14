package at.easydiet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import at.easydiet.model.Patient;



//FIX: Must run in a own thread, may we start the controller in an own class.
//Fix: Lazy loading from database

/**
 * A DAO implementation for Patient objects.
 */
public class PatientDAO extends GenericHibernateDAO<Patient, Long>
{

    
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PatientDAO.class);


    /**
     * Searches patients with the defined criteria in the database via hibernate searching 
     * 
     * 
     * @param name1 firstname of the patient to search for should be empty if not available
     * @param name2 secondname of the patient to search for should be empty if not available
     * @param svn the insurance number of the patient to search
     * @param birthday the birthday of the patient hours:minutes:seconds must be 0
     * @return a empty or a filled list with the patients
     */
    public List<Patient> findBySpecialProperties(String name1,String name2, String svn, Date birthday)
    {
        //if empty strings are comming in they must be null for searching with hibernate
        svn=svn.equals("")?null:svn;
        name1 = name1.equals("")?null:name1;
        name2 = name2.equals("")?null:name2;
        //TODO: the empty strings should be checked with a special string utility class because we will need it more times
        
        
        //when all strings are null there is nothing to search
        if(name1==null&&name2==null&&svn==null&&birthday==null)return new ArrayList<Patient>();
        
        //The next 4 blocks solve the problem that the name1(firstname) and name2(lastname) are swapped in the searchstring
        Patient tempPFornameFirst = new Patient();
        tempPFornameFirst.setBirthday(birthday);
        tempPFornameFirst.setInsuranceNumber(svn);
        tempPFornameFirst.setForename(name1);
        tempPFornameFirst.setLastname(name2);
        
        //The example which hibernate needs to search
        Example firstPatientSearch = Example.create(tempPFornameFirst)
        .excludeZeroes()
        .ignoreCase()
        .enableLike(MatchMode.ANYWHERE)
        ;
        
        Patient tempPLastnameFirst = new Patient();
        tempPLastnameFirst.setBirthday(birthday);
        tempPLastnameFirst.setInsuranceNumber(svn);
        tempPLastnameFirst.setForename(name2);
        tempPLastnameFirst.setLastname(name1);
        
        //The example which hibernate needs to search
        Example secondPatientSearch = Example.create(tempPLastnameFirst)
        .excludeZeroes()
        .ignoreCase()
        .enableLike(MatchMode.ANYWHERE)
        ;
        
        LOGGER.info(String.format("Searching for %s %s %s %s","",name1,name2,svn,birthday ));
        
        //logical or connector that first and lastname can be swapped
        List<Patient> results=findByCriteria(Restrictions.or(firstPatientSearch, secondPatientSearch));
        
        LOGGER.info(String.format("Results found %d",results.size()));
        
        return results;
        
    }
        
}