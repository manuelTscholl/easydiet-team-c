/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: GUIController.java
 */
package at.easydiet.teamc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.teamc.exception.GeneratingDietryPlanException;
import at.easydiet.teamc.exception.NoDateException;
import at.easydiet.teamc.exception.NoDietTreatmentException;
import at.easydiet.teamc.exception.NoPatientException;
import at.easydiet.teamc.exception.TimeIntersectionException;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.DietParameterData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.MealData;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.util.CollectionConverter;
import at.easydiet.teamc.util.EventArgs;
import at.easydiet.teamc.util.IEventHandler;
import at.easydiet.teamc.view.ChooseMealDialog;
import at.easydiet.teamc.view.ContentTabPane;
import at.easydiet.teamc.view.DietPlanDialog;
import at.easydiet.teamc.view.EasyDietMenuBar;
import at.easydiet.teamc.view.EasyDietWindow;
import at.easydiet.teamc.view.GUIComponents;
import at.easydiet.teamc.view.KeyAdapter;
import at.easydiet.teamc.view.NavigationTabPane;
import at.easydiet.teamc.view.PatientListener;

/**
 * Controls and knows all GUI elements
 * @author Michael
 */
public class GUIController implements PatientListener {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(GUIController.class);
    private static GUIController GUI_CONTROLLER;
    // instance variables
    private EasyDietWindow _easyDietWindow;
    private Map<String, Object> _guiNamespaces;
    private NavigationTabPane _navTab;
    private EasyDietMenuBar _menuBar;
    private ContentTabPane _contentTab;
    private BusinessLogicDelegationController _businessLogicDelegationController;

    /**
     * Singelton creates a new instance of this class or returns the existing
     * @return
     */
    public static GUIController getInstance() {

        if (GUI_CONTROLLER == null) {
            GUI_CONTROLLER = new GUIController();
        }

        return GUI_CONTROLLER;
    }

    /**
     * Constructor
     * @param eadw Window with all gui ressources
     */
    private GUIController() {

        _easyDietWindow = (EasyDietWindow) GUIComponents.get("easyDietWindow");
        _guiNamespaces = _easyDietWindow.getRessources();


        _businessLogicDelegationController = BusinessLogicDelegationController.getInstance();

        // get gui containers
        _navTab = (NavigationTabPane) _guiNamespaces.get("navTab");
        _menuBar = (EasyDietMenuBar) _guiNamespaces.get("menuBar");
        _contentTab = (ContentTabPane) _guiNamespaces.get("contentTab");

        // register for patient events
        _navTab.registerListener(this);

        // add key event for search textinput field
        _navTab.getSearchTextInput().getComponentKeyListeners().add(new KeyAdapter() {

            @Override
            public boolean keyTyped(Component component, char character) {

                // check if activity indicator is already active
                if (!_navTab.getActivityIndicator().isActive()) {
                    _navTab.getActivityIndicator().setActive(true);
                }

                // asyncron search that the gui is not blocked while searching in database
                String searchText = ((TextInput) component).getText();

                _businessLogicDelegationController.searchPatient(searchText);


                // check if text field is empty, if empty stop activity indicator
                if (_navTab.getSearchTextInput().getText().equals("")) {
                    _navTab.getActivityIndicator().setActive(false);
                }


                return true;
            }
        });

        //updates the gui when event is fired
        _businessLogicDelegationController.addNewPatientSearchHandler(new IEventHandler<EventArgs>() {

            @Override
            public void fired(Object sender, EventArgs e) {
                updateSearchResult(_businessLogicDelegationController.getLastPatientSearchResult());
            }
        });


    }

    /**
     * Update search results in navigation search tab with received set
     * @param patients
     */
    public void updateSearchResult(Set<PatientData> patients) {
        _navTab.updateSearchResult((org.apache.pivot.collections.Set<PatientData>) CollectionConverter.convertToPivotSet(patients));
    }

    /**
     * Set a new active patient
     * @param p
     */
    @Override
    public void chooseActivePatient(PatientData p) {
        _contentTab.setSelectedTabByName("overviewTab");
        _businessLogicDelegationController.setActivePatient(p);
        _navTab.unlockPatientSpecificItems();
        _contentTab.unlockPatientSpecificItems();
        updatePatientData(p);
    }

    /**
     * Update patient data in content tabs
     * @param p
     */
    public void updatePatientData(PatientData p) {
        _contentTab.updatePatientData(p);

        // update patient name in window bar
        _easyDietWindow.setTitle("EasyDiet - Patient: " + p.getForename() + " " + p.getLastname().toUpperCase());
    }

    /**
     * Open a new dialog for creating a new dietry plan
     */
    public void createDietryPlan() {

        // load dialog for creating a new dietplan
        BXMLSerializer bxml = new BXMLSerializer();
        DietPlanDialog dietryPlanDialog;
        try {
            dietryPlanDialog = (DietPlanDialog) bxml.readObject(DietPlanDialog.class,
                    "bxml/diet_plan_dialog.bxml");
            dietryPlanDialog.open(_easyDietWindow);
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (SerializationException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Get all parameters
     * @return All available parameters
     */
    public List<DietParameterData> getAllParameters() {
        java.util.List<DietParameterData> parameters = _businessLogicDelegationController.getAllParameters();
        List<DietParameterData> pivotList = (List<DietParameterData>) CollectionConverter.convertToPivotList(parameters);

        return pivotList;
    }

    /**
     * Create a new dietry plan
     * @param start Plan start date
     * @param end Plan end date
     * @param dpList List of parameters for this plan
     * @param parameterMaxValues Max Values for the chosen parameters
     * @param parameterMinValues Min Values for the chosen parameters
     */
    public void newDietryPlan(Date start, Date end, List<DietParameterData> dpList,
            List<Double> parameterMaxValues, List<Double> parameterMinValues) {

        // convert pivot list to java list
        java.util.List<DietParameterData> list = (java.util.List<DietParameterData>) CollectionConverter.convertToJavaList(dpList);
        java.util.List<Double> maxValueList = (java.util.List<Double>) CollectionConverter.convertToJavaList(parameterMaxValues);
        java.util.List<Double> minValueList = (java.util.List<Double>) CollectionConverter.convertToJavaList(parameterMinValues);

        DietryPlanData plan;
		try {
			plan = _businessLogicDelegationController.newDietryPlan(start, end, list,
			        maxValueList, minValueList);	
			if(plan==null){
				throw new GeneratingDietryPlanException();
			}
			_navTab.drawDietryPlanMenu(plan);
            _contentTab.drawDietryPlan(plan);
		} catch (NoPatientException e) {
			Alert.alert("Fehler beim Erstellend des Di‰tplans! Es wurde kein Patient gefunden.", _easyDietWindow);
		} catch (NoDateException e) {
			Alert.alert("Fehler beim Erstellen des Di‰tplans. Kein Datum vorhanden ",_easyDietWindow);
		} catch (TimeIntersectionException e) {
			Alert.alert("Fehler beim Erstellen des Di‰tplans. Es existiert bereits ein Plan",_easyDietWindow);
		} catch (NoDietTreatmentException e) {
			Alert.alert("Fehler beim Erstellen des Di‰tplans. Der Patient ist keiner Behandlung zugeordnet",_easyDietWindow);
		} catch (GeneratingDietryPlanException e) {
			Alert.alert(MessageType.ERROR, "Der Di√§tplan konnte nicht erstellt werden.", _easyDietWindow);
            LOGGER.error("DietPlan is empty");
		}
        
    }

    /**
     * Opens the dialog for creating a new meal
     * @param day to add a meal
     */
    public void addMeal(int day) {

        // load dialog for creating a new dietplan
        BXMLSerializer bxml = new BXMLSerializer();
        ChooseMealDialog chooseMealDialog;
        try {
            chooseMealDialog = (ChooseMealDialog) bxml.readObject(ChooseMealDialog.class,
                    "bxml/choose_meal_dialog.bxml");
            chooseMealDialog.setDay(day);
            chooseMealDialog.open(_easyDietWindow);
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (SerializationException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Returns all meals with the given codes
     * @return 
     */
    public List<MealCodeData> getAllMealCodes() {
        return (List<MealCodeData>) CollectionConverter.convertToPivotList(_businessLogicDelegationController.getAllMealCodes());
    }

    /**
     * Add a new meal code
     * @param md Meal code to add
     * @param day Day for this meal
     */
    public void addMealCode(MealCodeData md, int day) {
        _businessLogicDelegationController.addMealCode(md, day);
    }

    /**
     * Add a new MealLine
     * @return Index of this meal line
     */
    public int addMealLine() {
        return _businessLogicDelegationController.addMealLine();
    }

    /**
     * Get all recipe main categories
     */
    public org.apache.pivot.collections.Set<RecipeData> getRecipeMainCategories() {
        return (org.apache.pivot.collections.Set<RecipeData>) CollectionConverter.convertToPivotSet(_businessLogicDelegationController.getRecipeMainCategories());
    }

    /**
     *   
     *
     * @param mainCategory the BLS Code of this categorie
     * @param search
     * @return All matched recipe with parameter check
     */
    public org.apache.pivot.collections.Set<CheckedRecipeVo> searchRecipe(String mainCategory, String search) {
        return (org.apache.pivot.collections.Set<CheckedRecipeVo>) CollectionConverter.convertToPivotSet(_businessLogicDelegationController.searchRecipe(mainCategory, search));
    }

    /**
     * Add a recipe to a meal
     * @param rd Recipe to add
     * @param quantity Quantity of the recipe
     * @param mealLineId MealLine to add this recipe
     * @return 
     */
    public MealData addRecipeToMeal(RecipeData rd, float quantity, int mealLineId) {
        return _businessLogicDelegationController.addRecipetoMeal(rd, quantity, mealLineId);
    }

    /**
     * Save the actual dietry plan
     */
    public void saveDietryPlan() {
        _businessLogicDelegationController.saveDietryPlan();
    }

    /**
     * Get the actual dietry plan
     * @return actual dietry plan
     */
    public DietryPlanData getDietryPlan() {
        return _businessLogicDelegationController.getDietryPlan();
    }
}
