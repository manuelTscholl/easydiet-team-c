package at.easydiet.dao;

import java.util.List;


import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import at.easydiet.model.Recipe;
import org.hibernate.criterion.Order;

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
     * @param blsCategorie the Code which the recipes must match
     * @param name the recipes should have
     * @return a list of the matching recipes
     */
    public List<Recipe> searchRecipe(String blsCategorie, String name)
    {
        if(blsCategorie==null||blsCategorie.equals(""))
        {
            blsCategorie=null;
        }
        if(name==null||name.equals(""))
        {
            name=null;            
        }
        
        Recipe receipt = new Recipe();
        receipt.setBlsCode(blsCategorie);//all main categories will be found
        receipt.setName(name);
        
        //The example which hibernate needs to search
        Example receiptExample = Example.create(receipt)
        .excludeZeroes()
        .ignoreCase()
        .enableLike(MatchMode.ANYWHERE)
        ;
        
                
        //the searchresult of hibernate as a List
        List<Recipe> results = getSession().createCriteria(Recipe.class)
        .add(receiptExample).addOrder(Order.desc("blscode"))
        .list();
        
        
        return results;
 
        
    }
    
}