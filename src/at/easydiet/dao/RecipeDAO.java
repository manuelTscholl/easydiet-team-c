package at.easydiet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.NotSupportedException;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import at.easydiet.model.Patient;
import at.easydiet.model.Recipe;
import at.easydiet.teamc.controller.DatabaseController;
import at.easydiet.teamc.model.RecipeBo;

/**
 * A DAO implementation for Recipe objects.
 */
public class RecipeDAO 
        extends GenericHibernateDAO<Recipe, Long>
{
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RecipeDAO.class);

    
    /**
     * Searches recipes by their names or categories
     * the hibernate match method is anywhere
     * @param categorie which the recipes must match
     * @param name the recipes should have
     * @return a list of the matching recipes
     */
    public List<Recipe> searchRecipe(String categorie, String name)
    {
        if(categorie==null||categorie.equals(""))
        {
            categorie=null;
        }
        if(name==null||name.equals(""))
        {
            name=null;            
        }
        
        Recipe receipt = new Recipe();
        receipt.setBlsCode(categorie);//all main categories will be found
        receipt.setName(name);
        
        //The example which hibernate needs to search
        Example receiptExample = Example.create(receipt)
        .excludeZeroes()
        .ignoreCase()
        .enableLike(MatchMode.ANYWHERE)
        ;
        
                
        //the searchresult of hibernate as a List
        List<Recipe> results = getSession().createCriteria(Recipe.class)
        .add(receiptExample)
        .list();
        
        
        return results;
 
        
    }
    
}