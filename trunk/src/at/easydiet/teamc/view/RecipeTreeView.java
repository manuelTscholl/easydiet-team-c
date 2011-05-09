/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: RecipeTreeView.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.content.TreeBranch;

import at.easydiet.teamc.model.data.RecipeData;

/**
 * This class repesents a recipe tree view
 * 
 * @author Michael
 */
public class RecipeTreeView extends TreeView {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(RecipeTreeView.class);
	// instance variables
	private final List<RecipeTreeBranch> _mainCategories;
	private final List<RecipeTreeBranch> _subCategories;
	private final HashMap<RecipeTreeBranch, List<RecipeTreeNode>> _recipeCache;
	private boolean _searchMode;

	public RecipeTreeView() {

		// init lists
		_mainCategories = new ArrayList<RecipeTreeBranch>();
		_subCategories = new ArrayList<RecipeTreeBranch>();
		_recipeCache = new HashMap<RecipeTreeBranch, List<RecipeTreeNode>>();
	}

	/**
	 * Set categories in tree view
	 * 
	 * @param categories
	 */
	public void setCategories(List<RecipeData> categories) {
		setMainCategories(categories);
		setSubCategories(categories);
	}

	/**
	 * Set main categories
	 * 
	 * @param categories
	 */
	private void setMainCategories(List<RecipeData> categories) {
		final int mainCatLength = 1;

		TreeBranch branch = new TreeBranch();
		for (RecipeData r : categories) {

			// get bls code
			String blsCode = r.getBlsCode();

			// main category
			if (blsCode.length() == mainCatLength) {
				RecipeTreeBranch node = new RecipeTreeBranch(r, true);
				_mainCategories.add(node);
				branch.add(node);
			}
		}

		setTreeData(branch);
	}

	/**
	 * Set sub categories
	 * 
	 * @param categories
	 */
	private void setSubCategories(List<RecipeData> categories) {
		final int subCatLength = 2;

		TreeBranch branch = new TreeBranch();
		for (RecipeData r : categories) {

			// get bls code
			String blsCode = r.getBlsCode();

			// sub category
			if (blsCode.length() == subCatLength) {
				RecipeTreeBranch node = new RecipeTreeBranch(r, false);
				RecipeTreeBranch mainCat = getBranchByBLSCode(
						blsCode.substring(0, 1), _mainCategories);

				if (mainCat != null) {
					mainCat.add(node);
					_subCategories.add(node);
				}
			}
		}
	}

	/**
	 * Get a recipe tree branch by his bls code
	 * 
	 * @param blsCode
	 * @return
	 */
	private RecipeTreeBranch getBranchByBLSCode(String blsCode,
			List<RecipeTreeBranch> categories) {
		for (RecipeTreeBranch t : categories) {
			if (t.getRecipeData().getBlsCode().contains(blsCode)) {
				return t;
			}
		}

		return null;
	}

	/**
	 * Displays a list of recipes as search result
	 * 
	 * @param recipe
	 */
	public void setSearchedRecipes(List<RecipeData> recipes) {
		_searchMode = true;
		clearAllTreeNodes();

		for (RecipeData r : recipes) {

			// check if main category is found
			if (r.getBlsCode().length() > 1) {

				// get subcategory
				RecipeTreeBranch category = getBranchByBLSCode(r.getBlsCode()
						.substring(0, 2), _subCategories);

				if (category != null) {
					RecipeTreeNode node = new RecipeTreeNode(r);
					category.add(node);
				}
			}
		}

		expandAll();
	}

	/**
	 * Add recipes to the opened subcategory branch
	 * 
	 * @param subCategory
	 * @param recipes
	 */
	public void addRecipesToOpenedBranch(RecipeTreeBranch subCategory,
			List<RecipeData> recipes) {

		// check if search mode is active
		if (!_searchMode) {

			// check if recipes are already in cache
			if (!_recipeCache.containsKey(subCategory)) {

				List<RecipeTreeNode> recipesList = new ArrayList<RecipeTreeNode>();

				for (RecipeData r : recipes) {
					RecipeTreeNode node = new RecipeTreeNode(r);
					recipesList.add(node);
					subCategory.add(node);
				}

				_recipeCache.put(subCategory, recipesList);
			}
		}
	}

	/**
	 * Clear the search results
	 */
	public void clearSearchResult() {
		_searchMode = false;
		clearAllTreeNodes();
		loadCachedRecipes();
		collapseAll();
	}

	/**
	 * Clear all tree nodes but no branches
	 */
	private void clearAllTreeNodes() {

		// go through all subcategories and remove their nodes
		for (RecipeTreeBranch branch : _subCategories) {
			branch.remove(0, branch.getLength());
		}
	}

	/**
	 * Loads the cached recipes and displays them
	 */
	private void loadCachedRecipes() {

		for (RecipeTreeBranch branch : _recipeCache) {
			for (RecipeTreeNode node : _recipeCache.get(branch)) {
				branch.add(node);
			}
		}
	}

	/**
	 * Get search mode
	 * 
	 * @return
	 */
	public boolean isSearchMode() {
		return _searchMode;
	}

	/**
	 * Check if a tree branch is already in cache
	 * 
	 * @param t
	 * @return
	 */
	public boolean cacheContains(RecipeTreeBranch t) {
		return _recipeCache.containsKey(t);
	}
}
