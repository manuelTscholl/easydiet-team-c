/**
 * This File is part of Easy Diet
 * created on: 01.04.2011
 * created by: Michael
 * file: NavigationTabPane.java
 */
package at.fhv.teamc.easydiet.view;

import at.easydiet.model.Patient;
import java.awt.Font;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.content.ButtonData;

/**
 * Represents the application's navigation tabpane (navigation.bxml)
 * @author Michael
 */
public class NavigationTabPane extends TabPane implements Bindable, Resizable {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(NavigationTabPane.class);
    // instance variables
    @BXML
    private TextInput _searchTextInput;
    @BXML
    private BoxPane _patientDataBoxPane;
    @BXML
    private BoxPane _navigationBoxPane;
    @BXML
    private BoxPane _editBoxPane;
    @BXML
    private TablePane _searchResultTablePane;
    @BXML
    private ScrollPane _searchResultScrollPane;
    private ArrayList<PatientListener> _patientListeners;

    {
        _patientListeners = new ArrayList<PatientListener>();
    }

    /**
     * First called after creating the GUI
     * @param map Contains all UI elements in navigation.bxml by id name
     * @param url Contains the URL of the navigation bxml file
     * @param rsrcs
     */
    public void initialize(Map<String, Object> map, URL url, Resources rsrcs) {

        // get GUI components
        _searchTextInput = (TextInput) map.get("searchTextInput");
        _patientDataBoxPane = (BoxPane) map.get("patientDataBoxPane");
        _navigationBoxPane = (BoxPane) map.get("navigationBoxPane");
        _editBoxPane = (BoxPane) map.get("editBoxPane");
        _searchResultTablePane = (TablePane) map.get("searchResultTablePane");
        _searchResultScrollPane = (ScrollPane) map.get("searchResultScrollPane");

        // register for resize
        MainWindow.registerResizableComponent(this);
    }

    /**
     * Getter for the search textinput field
     * @return
     */
    public TextInput getSearchTextInput() {
        return _searchTextInput;
    }

    /**
     * Update search results with received set
     * @param patients
     */
    public void updateSearchResult(Set<Patient> patients) {

        for (Patient p : patients) {
            TablePane.Row tro = new TablePane.Row();

            // create new inner table
            TablePane innerTp = new TablePane();
            innerTp.getColumns().add(new TablePane.Column(-1));
            innerTp.getColumns().add(new TablePane.Column(-1));

            // add name
            TablePane.Row innerNameTro = new TablePane.Row();
            innerNameTro.setHeight(15);
            Label nameLabel = new Label("Name: ");
            nameLabel.getStyles().put("font", new Font("Verdana", Font.BOLD, 11));
            ButtonData nameButtonData = new ButtonData(p.getForename() + " " + p.getLastname().toUpperCase());
            LinkButton nameButton = new LinkButton(nameButtonData);
            final Patient patient = p;
            nameButton.getButtonPressListeners().add(new ButtonPressListener() {

                public void buttonPressed(Button button) {
                    notifyPatientListeners(patient);
                }
            });
            innerNameTro.add(nameLabel);
            innerNameTro.add(nameButton);
            innerTp.getRows().add(innerNameTro);

            // add birthdate
            TablePane.Row innerBdayTro = new TablePane.Row();
            innerBdayTro.setHeight(15);
            Label bdayLabel = new Label("Geburtstag: ");
            bdayLabel.getStyles().put("font", new Font("Verdana", Font.BOLD, 11));
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Label bday = new Label(format.format(p.getBirthday()));
            innerBdayTro.add(bdayLabel);
            innerBdayTro.add(bday);
            innerTp.getRows().add(innerBdayTro);

            // add svn number
            TablePane.Row innerSvnTro = new TablePane.Row();
            innerSvnTro.setHeight(15);
            Label svnLLabel = new Label("SVN: ");
            svnLLabel.getStyles().put("font", new Font("Verdana", Font.BOLD, 11));
            Label svn = new Label(p.getInsuranceNumber());
            innerSvnTro.add(svnLLabel);
            innerSvnTro.add(svn);
            innerTp.getRows().add(innerSvnTro);

            // add inner table to top table
            tro.add(innerTp);
            _searchResultTablePane.getRows().add(tro);
        }
    }

    /**
     * Resize navigation.xml
     * @param height
     * @param width
     */
    public void resize(int height, int width) {
        _searchResultScrollPane.setPreferredHeight(height - 85);
    }

    /**
     * Register a new patient listener
     * @param pl patientListener Object
     */
    public void registerListener(PatientListener pl){

        // check if already registerd
        if(!_patientListeners.contains(pl)){
            _patientListeners.add(pl);
        }
    }

    /**
     * Notify patient listeners, a patient is selected as active
     * @param p Patient which wants to be active
     */
    private void notifyPatientListeners(Patient p){
        for(PatientListener pl:_patientListeners){
            pl.chooseActivePatient(p);
        }
    }
}
