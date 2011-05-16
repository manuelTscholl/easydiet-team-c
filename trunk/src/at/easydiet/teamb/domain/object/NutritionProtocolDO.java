package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.INutritionProtocol;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.model.NutritionProtocol;

/**
 * Represents a NutritionProtocol in the domain layer
 */
public class NutritionProtocolDO extends DietPlanDO implements INutritionProtocol {
	private static Logger LOGGER = Logger.getLogger(NutritionProtocolDO.class);

	private NutritionProtocol _nutritionProtocol;

	/**
	 * Instantiates a new nutrition protocol.
	 * 
	 * @param nutritionProtocol
	 *            the nutrition protocol
	 */
	public NutritionProtocolDO(NutritionProtocol nutritionProtocol) {
		if (nutritionProtocol == null) {
			LOGGER.debug("NutritionProtocol is null");
		}

		_nutritionProtocol = nutritionProtocol;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#getDate()
	 */
	@Override
	public GregorianCalendar getDate() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_nutritionProtocol.getDate());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#setDate(java.util.GregorianCalendar)
	 */
	@Override
	public void setDate(GregorianCalendar date) {
		_nutritionProtocol.setDate(CalendarUtil.ConvertGregorianCalendarToDate(date));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#getContact()
	 */
	@Override
	public String getContact() {
		return _nutritionProtocol.getContact();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#setContact(java.lang.String)
	 */
	@Override
	public void setContact(String contact) {
		_nutritionProtocol.setContact(contact);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#getNotice()
	 */
	@Override
	public String getNotice() {
		return ClobConverter.ClobToString(_nutritionProtocol.getNotice());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#setNotice(java.lang.String)
	 */
	@Override
	public void setNotice(String notice) {
		_nutritionProtocol.setNotice(ClobConverter.StringToClob(notice));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.INutritionProtocol#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_nutritionProtocol == null);
	}

	public NutritionProtocol getModel() {
		return _nutritionProtocol;
	}
}
