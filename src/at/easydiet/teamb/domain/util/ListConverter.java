/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	26.04.2011
 */

package at.easydiet.teamb.domain.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The Class ListConverter.
 */
public class ListConverter {
	
	/**
	 * To do array.
	 *
	 * @param <D> the generic type of the models which you want to generate
	 * @param <M> the generic type of the DOs
	 * @param models the models
	 * @param converter the converter
	 * @return the list
	 */
	public static <D extends AbstractDO<?>, M> List<D> toDOArray(Set<M> models, ModelToDO<D, M> converter) {
		List<D> list = new ArrayList<D>();
		for (M m : models) {
			list.add(converter.convert(m));
		}

		return list;
	}

	/**
	 * To do array.
	 *
	 * @param <D> the generic type
	 * @param <M> the generic type
	 * @param models the models
	 * @param converter the converter
	 * @return the list
	 */
	public static <D extends AbstractDO<?>, M> List<D> toDOArray(List<M> models, ModelToDO<D, M> converter) {
		List<D> list = new ArrayList<D>();
		for (M m : models) {
			list.add(converter.convert(m));
		}

		return list;
	}

	/**
	 * The Interface ModelToDO.
	 *
	 * @param <D> the generic type
	 * @param <M> the generic type
	 */
	public interface ModelToDO<D extends AbstractDO<?>, M> {
		
		/**
		 * Convert.
		 *
		 * @param model the model
		 * @return the d
		 */
		public D convert(M model);
	}
}
