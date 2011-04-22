/**
 * This File is part of Easy Diet
 * created on: 12.04.2011
 * created by: Michael
 * file: SearchPatientController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.util.Event;
import at.easydiet.teamc.util.EventArgs;

/**
 * Controller for use case: search patient. Implements the Runnable interface
 * and must be started as Thread, because it will start a searching loop.
 * 
 * @author Michael
 */
public class SearchPatientController extends Event<EventArgs> implements
        Runnable
{
    // class variables
    public static final org.apache.log4j.Logger     LOGGER                      = org.apache.log4j.Logger
                                                                                        .getLogger(BusinessLogicDelegationController.class);
    private static volatile SearchPatientController _searchPatientController    = null;
    // instance variables
    private DatabaseController                      _dbController;
    private Set<PatientData>                        _lastSearchResult;
    private Stack<String>                           _searchPatient;
    private boolean                                 _running = false;
    private final int                               _sleepBetweenEachSearchLoop = 500;

    /**
     * Gets the running.
     * 
     * @return the running
     */
    public synchronized boolean isRunning()
    {
        return _running;
    }

    /**
     * Sets the running.
     * 
     * @param running
     *            the running to set
     */
    public synchronized void setRunning(boolean running)
    {
        _running = running;
    }

    /**
     * Gets the lastSearchResult.
     * 
     * @return the lastSearchResult
     */
    public synchronized Set<PatientData> getLastSearchResult()
    {
        return _lastSearchResult;
    }

    /**
     * Sets the lastSearchResult.
     * 
     * @param lastSearchResult
     *            the lastSearchResult to set
     */
    public synchronized void setLastSearchResult(
            Set<PatientData> lastSearchResult)
    {
        _lastSearchResult = lastSearchResult;
        _searchPatientController.fireEvent(EventArgs.Empty);
    }

    /**
     * Initializes a new instance of the {@link SearchPatientController} class.
     */
    private SearchPatientController(Object sender)
    {
        super(sender);
        _dbController = DatabaseController.getInstance();
        _searchPatient = new Stack<String>();
    }

    /**
     * Singelton
     * 
     * @return Will return the existing Instance or if no exists a new Instance
     *         of {@link SearchPatientController}
     */
    public static SearchPatientController getInstance()
    {
        if (_searchPatientController == null)
        {
            _searchPatientController = new SearchPatientController(null);
        }
        return _searchPatientController;

    }

    public void addNewPatientSearchString(String search)
    {
        _searchPatient.add(search);

    }

    /**
     * searches patients in database
     * 
     * @param search
     *            the search string the patients must match
     */
    public void getPatients(String search)
    {
        setRunning(true);
        addNewPatientSearchString(search);
        getPatientsAsync();
        setRunning(false);

    }

    /**
     * Gets the last added search string which was added with the method and
     * searches for this patient This method will start a
     * 
     * @see SearchPatientController#addNewPatientSearchString(String)
     * 
     * @param search
     * @return
     */
    private void getPatientsAsync()
    {

        while (_running)
        {

            if (!_searchPatient.isEmpty())
            {
                String search = _searchPatient.pop();
                _searchPatient.removeAllElements();
                String[] t;

                String name1 = "";
                String name2 = "";
                String svn = "";
                Date date = null;

                String regexName = "[a-zA-Z]{1,}";
                String regexSVN = "[0-9]{0,10}";
                String regexDATE = "[0-9]{1,2}[.][0-9]{1,2}[.][0-9]{1,4}";

                t = search.split(" ");

                // check if search string exists
                if (t == null)
                {
                    return;
                }

                for (int i = 0; i < t.length; i++)
                {

                    // name match
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

                        // svn match
                    }
                    else if (t[i].matches(regexSVN))
                    {

                        svn = t[i];

                        // date match
                    }
                    else if (t[i].matches(regexDATE))
                    {

                        try
                        {

                            // date format dd.MM.yyyy or dd.MM.yy
                            SimpleDateFormat sdfFormat = new SimpleDateFormat(
                                    "dd.MM.y");
                            date = new Date();
                            date = sdfFormat.parse(t[i]);
                        }
                        catch (ParseException p)
                        {
                            LOGGER.error(p);
                        }
                    }
                }

                Set<PatientData> patientDatas = new HashSet<PatientData>();
                Set<PatientBo> patients = _dbController.getPatients(name1,
                        name2, svn, date);

                // check if database set is not empty
                if (patients != null)
                {

                    // convert business patient data to gui patient data
                    for (PatientBo pbo : patients)
                    {
                        patientDatas.add(pbo);
                    }
                }

                setLastSearchResult(patientDatas);
            }

            try
            {
                Thread.sleep(_sleepBetweenEachSearchLoop);
            }
            catch (InterruptedException e)
            {
                LOGGER.debug(e);
            }

            LOGGER.info("Searching Complete next loop will start");

        }

    }

    /**
     * @see java.lang.Runnable#run() Starts the asyncron searching of Patients
     *      with a defined brake Sets the running flag true
     */
    @Override
    public void run()
    {
        setRunning(true);
        getPatientsAsync();
    }

    /**
     * Stops the searching loop by setting the running flag to false
     */
    public void stop()
    {
        setRunning(false);
    }

    /**
     * Starts the searching loop by setting the running flag to start
     */
    public void start()
    {
        if (!isRunning())
        {
            setRunning(true);
            getPatientsAsync();
        }
    }
}
