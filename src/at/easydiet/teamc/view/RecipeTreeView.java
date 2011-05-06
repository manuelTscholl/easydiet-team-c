/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: RecipeTreeView.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.content.TreeBranch;

import at.easydiet.teamc.model.data.RecipeData;

/**
 * 
 * @author Michael
 */
public class RecipeTreeView extends TreeView {

	// instance variables
	private List<RecipeData> _mainCategories;

	public void setMainCategories(List<RecipeData> mainCategories) {
		_mainCategories = mainCategories;

		TreeBranch branch = new TreeBranch();
		for (RecipeData r : _mainCategories) {
			RecipeTreeNode node = new RecipeTreeNode(r);
			branch.add(node);
		}

		setTreeData(branch);
	}
}
