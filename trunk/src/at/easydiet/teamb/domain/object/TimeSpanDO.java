package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.DietPlanViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.domain.IDietParameter;
import at.easydiet.teamb.domain.IDietPlan;
import at.easydiet.teamb.domain.IMeal;
import at.easydiet.teamb.domain.ITimeSpan;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.DietParameter;
import at.easydiet.model.Meal;
import at.easydiet.model.TimeSpan;

/**
 * Represents a TimeSpan in the domain layer
 */
public class TimeSpanDO extends AbstractDO<TimeSpan> implements ITimeSpan {
	private static Logger LOGGER = Logger.getLogger(TimeSpanDO.class);

	private TimeSpan _timeSpan;

	/**
	 * Instantiates a new timespan.
	 * 
	 * @param timeSpan
	 *            the timespan
	 */
	public TimeSpanDO(TimeSpan timeSpan) {
		if (timeSpan == null) {
			LOGGER.debug("TimeSpan is null");
		}

		_timeSpan = timeSpan;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getTimeSpanId()
	 */
	@Override
	public long getTimeSpanId() {
		return _timeSpan.getTimeSpanId();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#setTimeSpanId(long)
	 */
	@Override
	public void setTimeSpanId(long timeSpanId) {
		_timeSpan.setTimeSpanId(timeSpanId);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getStart()
	 */
	@Override
	public GregorianCalendar getStart() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_timeSpan.getStart());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#setStart(java.util.GregorianCalendar)
	 */
	@Override
	public void setStart(GregorianCalendar start) {
		_timeSpan.setStart(CalendarUtil.ConvertGregorianCalendarToDate(start));
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getDuration()
	 */
	@Override
	public int getDuration() {
		return _timeSpan.getDuration();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#setDuration(int)
	 */
	@Override
	public void setDuration(int duration) {
		_timeSpan.setDuration(duration);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getDietParameters()
	 */
	@Override
	public DietParameterViewable[] getDietParameters() {
		return ListConverter.toDOArray(_timeSpan.getDietParameters(), new ListConverter.ModelToDO<DietParameterDO, DietParameter>() {
			@Override
			public DietParameterDO convert(DietParameter model) {
				return new DietParameterDO(model);
			}
		}).toArray(new DietParameterViewable[0]);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#addDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void addDietParameter(IDietParameter dietParameter){
		_timeSpan.getDietParameters().add(((DietParameterDO)dietParameter).getModel());
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#removeDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void removeDietParameter(IDietParameter dietParameter){
		_timeSpan.getDietParameters().remove(((DietParameterDO)dietParameter).getModel());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getMeals()
	 */
	@Override
	public MealViewable[] getMeals() {
		return ListConverter.toDOArray(_timeSpan.getMeals(), new ListConverter.ModelToDO<MealDO, Meal>() {
			@Override
			public MealDO convert(Meal model) {
				return new MealDO(model);
			}
		}).toArray(new MealViewable[0]);
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#addMeal(at.easydiet.domain.object.MealDO)
	 */
	@Override
	public void addMeal(IMeal meal){
		_timeSpan.getMeals().add(((MealDO)meal).getModel());
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#removeMeal(at.easydiet.domain.object.MealDO)
	 */
	@Override
	public void removeMeal(IMeal meal){
		_timeSpan.getMeals().remove(((MealDO)meal).getModel());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getEnd()
	 */
	@Override
	public GregorianCalendar getEnd() {
		if (getStart() == null) {
			return null;
		}
		
		GregorianCalendar end = (GregorianCalendar) getStart().clone();
		end.add(GregorianCalendar.DATE, getDuration());
		return end;
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#getDietPlan()
	 */
	@Override
	public DietPlanViewable getDietPlan(){
		return new DietPlanDO(_timeSpan.getDietPlan());
	}
	    
    /* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#setDietPlan(at.easydiet.application.viewobject.DietPlanViewable)
	 */
    @Override
	public void setDietPlan(DietPlanViewable DietPlan) 
    {
    	IDietPlan d = (IDietPlan)DietPlan;
    	_timeSpan.setDietPlan(d.getDietPlan());
    }
    
    /* (non-Javadoc)
	 * @see at.easydiet.domain.object.ITimeSpan#isSet()
	 */
    @Override
	public boolean isSet(){
		return !(_timeSpan==null);
	}

	@Override
	public TimeSpan getModel() {
		return _timeSpan;
	}
}
