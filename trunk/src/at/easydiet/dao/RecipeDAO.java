package at.easydiet.dao;

import java.util.List;


import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

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
        
        Recipe recipe = new Recipe();
        recipe.setBlsCode(blsCategorie);//all main categories will be found
        recipe.setName(name);
        
        //The example which hibernate needs to search
        Example recipeExample = Example.create(recipe)
        .excludeZeroes()
        .ignoreCase()
        .enableLike(MatchMode.ANYWHERE)
        ;
        
                
        //the searchresult of hibernate as a List
        List<Recipe> results = getSession().createCriteria(Recipe.class)
        .addOrder(Order.desc("blsCode"))
        .add(recipeExample)
        .addOrder(Order.desc("blsCode"))
        .list();
        
        return results;
 
        
    }
    
}