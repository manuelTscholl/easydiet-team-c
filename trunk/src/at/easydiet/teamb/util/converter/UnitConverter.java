/**
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */
package at.easydiet.teamb.util.converter;

import java.util.HashMap;

import at.easydiet.teamb.util.exception.UnknownUnitStringException;

/**
 * Useful utility class that converts between different units
 * 
 * @see Convertable
 * @author TeamB
 */
public class UnitConverter {
	private static UnitConverter _unitConverter;
	private HashMap<String, Convertable> _convertableMap;

	private UnitConverter() {
		// Declared HashMap
		_convertableMap = new HashMap<String, Convertable>();

		// add all Units
		addConverter(new GrammToKiloGramm());
		addConverter(new GramToMilligram());
		addConverter(new KilogramToMilligram());
		addConverter(new KillogramToGram());
		addConverter(new MilligramToGram());
		addConverter(new MilligramToKilogram());
		addConverter(new LiterToMilliliter());
		addConverter(new MilliliterToLiter());
	}

	/**
	 * @param convertString
	 *            Contains the source and destination unit defined by {@link Convertable#getConvertString()}
	 * @param value The value which should be converted in the source unit
	 * @return The value in the destination unit
	 * @throws UnknownUnitStringException When there is no suitable Converter found for the given {@code convertString}
	 */
	public static double convert(String convertString, double value) throws UnknownUnitStringException {
		if (_unitConverter == null) {
			_unitConverter = new UnitConverter();
		}

		Convertable convertto = _unitConverter._convertableMap.get(convertString);

		// get ConverterObject from HashTable
		if (convertto != null) {
			// converterString was in HashTable included
			return convertto.convert(value);
		} else {
			// converterString not in HashTable included
			throw new UnknownUnitStringException();
		}
	}

	/**
	 * @param x The converter which should be added
	 */
	public void addConverter(Convertable x) {
		_convertableMap.put(x.getConvertString(), x);
	}
}
