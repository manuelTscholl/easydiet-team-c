/**
 * This File is part of Easy Diet
 * created on: 15.04.2011
 * created by: Michael
 * file: SearchResult.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.model.data.PatientData;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Set;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.content.ButtonData;

/**
 * Draws search results with caching
 * @author Michael
 */
public class SearchResult {

    // class variables
    private static SearchResult _searchResult;
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SearchResult.class);
    // instance variables
    private NavigationTabPane _navTab;
    private TablePane _searchResultTablePane;
    private HashMap<Long, PatientData> _result;

    /**
     * Initialize searchresults
     * @param searchTp Parent Table to draw results in
     */
    private SearchResult(NavigationTabPane navTab, TablePane searchTp) {
        _navTab = navTab;
        _searchResultTablePane = searchTp;
    }

    /**
     * Singleton
     * @return
     */
    public static SearchResult getInstance(NavigationTabPane navTab, TablePane searchTp) {
        if (_searchResult == null) {
            _searchResult = new SearchResult(navTab, searchTp);
        }

        return _searchResult;
    }

    /**
     * Draw results
     * @param results
     */
    public void drawResults(Set<PatientData> results) {

        // check if older results are available
        if (_result != null && !_result.isEmpty()) {
            if (!updateResults(results)) {
                draw();
            }
        } else { // no older results available
            setResult(results);
            draw();
        }
    }

    /**
     * Convert result set to hashmap
     * @param results
     */
    private void setResult(Set<PatientData> results) {
        _result = new HashMap<Long, PatientData>();
        
        for (PatientData p : results) {
            _result.put(p.getPatientId(), p);
        }
    }

    /**
     * Draw search Results in parent table
     */
    private void draw() {

        // check if row exists
        if (_searchResultTablePane.getRows().getLength() > 0) {

            // remove all existing rows
            _searchResultTablePane.getRows().remove(0, _searchResultTablePane.getRows().getLength());
        }

        // check if results are available to draw
        if (_result != null && !_result.isEmpty()) {

            for (PatientData p : _result.values()) {
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
                final PatientData patient = p;
                nameButton.getButtonPressListeners().add(new ButtonPressListener() {

                    @Override
                    public void buttonPressed(Button button) {
                        _navTab.notifyPatientListeners(patient);
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
        } else { // result is empty
            TablePane.Row tro = new TablePane.Row();
            tro.add(new Label("Keine passenden Patienten"));
            _searchResultTablePane.getRows().add(tro);
        }

    }

    /**
     * Compare old search results with the new one and update if necessary
     * @param newResults New Results to update with the older ones
     * @return True if results have been updated
     */
    private boolean updateResults(Set<PatientData> newResults) {

        int matchingResults = 0;
        for (PatientData pd : newResults) {

            // check if data already drawn
            if (_result.get(pd.getPatientId()) != null) {
                matchingResults++;
            }
        }

        // check if both results are the same
        if (matchingResults == _result.size() && matchingResults == newResults.size()) {
            return true;
        }

        // not the same results
        setResult(newResults);
        return false;
    }
}
