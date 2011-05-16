/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	15.04.2011
 */

/**
 * 
 */
package at.easydiet.teamb.application.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.IllnessDAO;
import at.easydiet.model.Illness;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.domain.object.IllnessDO;

/**
 * @author TeamB
 */
public class IllnessSearchHandler {

	private static Logger LOGGER = Logger.getLogger(IllnessSearchHandler.class);

	private IllnessDAO _illnessDao = DAOFactory.getInstance().getIllnessDAO();

	/**
	 * Search for {@link Illness}es
	 * 
	 * @param searchText
	 *            search string from to find different {@link Illness}es
	 * @param maxResults
	 *            maximum results which should be found
	 * @return a List with different {@link Illness}es
	 */
	public List<IllnessViewable> searchIllnesses(String searchText, int maxResults) {
		LOGGER.debug("Searching for Illness containing '" + searchText + "'");
		
		ArrayList<Illness> exIllnesses = new ArrayList<Illness>();

		String[] searchPart = searchText.trim().split(" ");

		for (String part : searchPart) {
			Illness i = new Illness();
			i.setName(part);

			exIllnesses.add(i);
		}

		//convert Illness to IllnessVO
		List<Illness> result = _illnessDao.searchIllnesses(exIllnesses, maxResults);
		List<IllnessViewable> illnessDOs = new ArrayList<IllnessViewable>();
		
		for (Illness illness : result) {
			illnessDOs.add(new IllnessDO(illness));
		}
		
		return illnessDOs;
	}
}
