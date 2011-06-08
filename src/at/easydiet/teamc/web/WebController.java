/**
 * This File is part of EasyDiet
 * Created on: 25.05.2011
 * Created by: Michael
 * File: WebController.java
 */
package at.easydiet.teamc.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import at.easydiet.teamc.controller.LoginController;
import at.easydiet.teamc.controller.usecase.CreateNutritionProtocolController;
import at.easydiet.teamc.controller.usecase.SearchRecipeController;
import at.easydiet.teamc.exception.LoginFailedException;
import at.easydiet.teamc.exception.NoDietPlanException;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.TimeSpanBo;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.PlanTypeData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.view.AddRecipeScrollPane;

/**
 * Main entry point for web modules This controller is unique for each session
 * 
 * @author Michael
 */
@ManagedBean
@SessionScoped
public class WebController
{

    // class variables
    private static final org.apache.log4j.Logger LOGGER     = org.apache.log4j.Logger
                                                                    .getLogger(WebController.class);

    // instance variables
    private PatientData                          _loggedInUser;
    private String                               _username  = "";
    private String                               _password  = "";
    private boolean                              _loggedIn  = false;
    private CreateNutritionProtocolController    _protocolController;
    private String                               _exception = "";
    private DietryPlanData                       _selectedPlan;
    private String                               _chosenRecipe;

    public PatientData getLoggedInUser()
    {
        return _loggedInUser;
    }

    public void setLoggedInUser(PatientData _loggedInUser)
    {
        this._loggedInUser = _loggedInUser;
    }

    public DietryPlanData getSelectedPlan()
    {
        return _selectedPlan;
    }

    public void setSelectedPlan(DietryPlanData plan)
    {
        _selectedPlan = plan;
    }

    public String getChosenRecipe()
    {
        if (_chosenRecipe != null)
        {
            return _chosenRecipe;
        }
        else
            return "";
    }

    public void setChosenRecipe(String chosenRecipe)
    {

    }

    /**
     * Login a patient
     * 
     * @username Login name
     * @password User password
     */
    public void loginPatient()
    {

        // check if name is set
        if (_username != null && !_username.equals("") && _password != null
                && !_password.equals(""))
        {
            try
            {
                _loggedInUser = LoginController.getInstance().loginPatient(
                        _username, _password);
                _loggedIn = true;
            }
            catch (LoginFailedException e)
            {
                _loggedIn = false;
                _exception = e.getMessage();
            }
        }
        else
        {
            _exception = "Bitte Logindaten eintragen";
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("loggedIn", _loggedIn);
        context.addCallbackParam("exception", _exception);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sample info message", "PrimeFaces rocks!"));
    }

    /**
     * Create a new dietry protocol.
     */
    public void createNewProtocol()
    {
        _protocolController = new CreateNutritionProtocolController();
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername()
    {
        return _username;
    }

    /**
     * Sets the username.
     * 
     * @param username
     *            the new username
     */
    public void setUsername(String username)
    {
        _username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword()
    {
        return _password;
    }

    /**
     * Sets the password.
     * 
     * @param password
     *            the new password
     */
    public void setPassword(String password)
    {
        _password = password;
    }

    /**
     * Gets the plan types.
     * 
     * @return the plan types
     */
    public List<PlanTypeData> getPlanTypes()
    {
        return _protocolController.getPlanTypes();
    }

    /**
     * Gets the meal codes.
     * 
     * @return the meal codes
     */
    public Set<MealCodeData> getMealCodes()
    {
        return _protocolController.getMealCodes();
    }

    /**
     * Save the nutrition protocol
     */
    public void saveNutritionProtocol()
    {
        _protocolController.save();
    }

    /**
     * Get all dietry plans
     */
    public List<DietryPlanData> getAllDietryPlans()
    {
        try
        {
            return _protocolController.getAllDietryPlans(_loggedInUser);
        }
        catch (NoDietPlanException e)
        {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return new ArrayList<DietryPlanData>();
        }
    }

    public void dietryPlanSelected()
    {
        LOGGER.info("new plan selected!");
    }

    /**
     * Sets the dietry plan.
     * 
     * @param d
     *            the new dietry plan
     */
    public void setDietryPlan(DietryPlanData d)
    {
        _selectedPlan = d;
    }

    /**
     * Is fired when a date from nutrimentprotocolis selected
     * 
     * @param event
     */
    public void nutrimentProtocolDateSelect(DateSelectEvent event)
    {
        _protocolController.NutrimentProtocolDateSelect();
        LOGGER.info("nutrimentProtocolDateSelect event");
    }

    public List<String> completeRecipeSearch(String query)
    {
        ArrayList<String> s = new ArrayList<String>();
        for (RecipeData rd : searchRecipes(query))
        {
            s.add(rd.getName());
        }
        return s;

    }

    public List<RecipeData> searchRecipes(String query)
    {
        SearchRecipeController src = SearchRecipeController.getInstance();
        ArrayList<RecipeData> recipes = new ArrayList<RecipeData>();
        for (RecipeBo b : src.searchRecipe(null, query))
        {
            recipes.add(b);
        }
        return recipes;

    }

    /**
     * whenever a recipe gets selected this method is called. The method adds
     * the selected recipe to the mealline object
     * 
     * @param e
     */
    public void handleSelect(SelectEvent e)
    {
        _chosenRecipe = e.getObject().toString();
    }

}
