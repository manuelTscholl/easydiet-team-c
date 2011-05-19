/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	24.04.2011
 */

package at.easydiet.teamb.application.handler;

import java.util.ArrayList;
import java.util.List;

import at.easydiet.dao.ParameterDefinitionDAO;
import at.easydiet.model.ParameterDefinition;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.IParameterDefinition;
import at.easydiet.teamb.domain.object.ParameterDefinitionDO;

/**
 * The ParameterDefinitionSearchHandler
 */
public class ParameterDefinitionSearchHandler {

	private ParameterDefinitionDAO _parameterDefinitionDAO;
	private Excluder _excluder;

	/**
	 * Instantiates a new parameterDefinitionSearchHandler
	 */
	public ParameterDefinitionSearchHandler() {
		this(new DefaultExcluder());
	}

	public ParameterDefinitionSearchHandler(Excluder excluder) {
		_parameterDefinitionDAO = new ParameterDefinitionDAO();
		_excluder = excluder;
	}

	/**
	 * Search for {@link ParameterDefinitionViewable}s
	 * 
	 * @param searchText
	 *            search string from to find different {@link ParameterDefinitionViewable}s
	 * @param maxResults
	 *            maximum results which should be found
	 * @return a List with different {@link ParameterDefinitionViewable}s
	 */
	public List<ParameterDefinitionViewable> searchParameterDefinitions(String searchText, int maxResults) {
		ParameterDefinition parameterDefinition = new ParameterDefinition();
		parameterDefinition.setName(searchText.trim());

		List<ParameterDefinition> parameterDefinitions = _parameterDefinitionDAO.searchParameterDefinitions(parameterDefinition, maxResults);
		List<ParameterDefinitionViewable> parameterDefinitionDOs = new ArrayList<ParameterDefinitionViewable>(maxResults);

		for (ParameterDefinition parameterDefinitionViewable : parameterDefinitions) {
			IParameterDefinition p = new ParameterDefinitionDO(parameterDefinitionViewable);
			if (!_excluder.exclude(p)) {
				parameterDefinitionDOs.add(p);
			}
		}

		return parameterDefinitionDOs;
	}

	public interface Excluder {
		public boolean exclude(IParameterDefinition parameterDefinition);
	}

	private static class DefaultExcluder implements Excluder {

		@Override
		public boolean exclude(IParameterDefinition parameterDefinition) {
			return false;
		}
	}
}
