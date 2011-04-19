package at.easydiet.dao;

import java.util.Set;

import at.easydiet.model.Recipe;
import at.easydiet.teamc.model.RecipeBo;

/**
 * A DAO implementation for Recipe objects.
 */
public class RecipeDAO 
        extends GenericHibernateDAO<Recipe, Long>
{
	// implementation in parent class
    
    /**
     * Looks in database for all recipe categories bls looks like (B00000)
     * @return a list of categories
     */
    public Set<RecipeBo> getRecipeMainCategories()
    {
      //TODO should call getRecipeByBlsPatter with the correct regex Pattern
        return null;
    }
    
    /**
     * Looks in database for all recipes which match to the special pattern
     * @param pattern a regex pattern how this should look like
     * @return a list of categories
     */
    public Set<RecipeBo> getRecipeByBlsPattern(String pattern)
    {
        
        return null;
    }
}