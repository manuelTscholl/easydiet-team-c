package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.application.viewobject.TimeSpanViewable;
import at.easydiet.teamb.domain.IDietParameter;
import at.easydiet.teamb.domain.IDietPlan;
import at.easydiet.teamb.domain.ITimeSpan;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.teamb.domain.util.PlanTypeEnum;
import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.TimeSpan;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietPlanDAO;

/**
 * Represents a DietPlanDomainObject in the domain layer
 */
public class DietPlanDO extends AbstractDO<DietPlan> implements IDietPlan {
	private static Logger LOGGER = Logger.getLogger(DietPlanDO.class);

	private DietPlan _dietPlan;

	/**
	 * Instantiates a new diet plan.
	 */
	public DietPlanDO() {
		this(new DietPlan());
	}

	/**
	 * Instantiates a new diet plan.
	 * 
	 * @param dietPlan
	 *            the diet plan
	 */
	public DietPlanDO(DietPlan dietPlan) {
		if (dietPlan == null) {
			LOGGER.debug("DietPlan is null");
		}

		_dietPlan = dietPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getDietPlan()
	 */
	@Override
	public DietPlan getDietPlan() {
		return _dietPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getDietPlanId()
	 */
	@Override
	public long getDietPlanId() {
		return _dietPlan.getDietPlanId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#setDietPlanId(long)
	 */
	@Override
	public void setDietPlanId(long dietPlanId) {
		_dietPlan.setDietPlanId(dietPlanId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getName()
	 */
	@Override
	public String getName() {
		return _dietPlan.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_dietPlan.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getCreatedOn()
	 */
	@Override
	public GregorianCalendar getCreatedOn() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_dietPlan.getCreatedOn());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#setCreatedOn(java.util.GregorianCalendar)
	 */
	@Override
	public void setCreatedOn(GregorianCalendar createdOn) {
		_dietPlan.setCreatedOn(CalendarUtil.ConvertGregorianCalendarToDate(createdOn));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getPlanType()
	 */
	@Override
	public PlanTypeEnum getPlanType() {
		return PlanTypeEnum.getPlanTypeDO(_dietPlan.getPlanType());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#setPlanType(at.easydiet.domain.util.PlanTypeEnum)
	 */
	@Override
	public void setPlanType(PlanTypeEnum planType) {
		PlanTypeEnum planTypeDO = (PlanTypeEnum) planType;
		_dietPlan.setPlanType((planTypeDO != null) ? planTypeDO.getPlanType() : null);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getDietParameters()
	 */
	@Override
	public DietParameterViewable[] getDietParameters() {
		return ListConverter.toDOArray(_dietPlan.getDietParameters(), new ListConverter.ModelToDO<DietParameterDO, DietParameter>() {
			@Override
			public DietParameterDO convert(DietParameter model) {
				return new DietParameterDO(model);
			}
		}).toArray(new DietParameterViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#addDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void addDietParameter(IDietParameter dietParameter) {
		_dietPlan.getDietParameters().add(((DietParameterDO)dietParameter).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#removeDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void removeDietParameter(IDietParameter dietParameter) {
		_dietPlan.getDietParameters().remove(((DietParameterDO)dietParameter).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getCreator()
	 */
	@Override
	public SystemUserViewable getCreator() {
		return new SystemUserDO(_dietPlan.getCreator());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#setCreator(at.easydiet.application.viewobject.SystemUserViewable)
	 */
	@Override
	public void setCreator(SystemUserViewable creator) {
		SystemUserDO systemUserDO = (SystemUserDO) creator;
		_dietPlan.setCreator(systemUserDO.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getTimeSpans()
	 */
	@Override
	public TimeSpanViewable[] getTimeSpans() {
		return ListConverter.toDOArray(_dietPlan.getTimeSpans(), new ListConverter.ModelToDO<TimeSpanDO, TimeSpan>() {
			@Override
			public TimeSpanDO convert(TimeSpan model) {
				return new TimeSpanDO(model);
			}
		}).toArray(new TimeSpanViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#addTimeSpan(at.easydiet.domain.object.TimeSpanDO)
	 */
	@Override
	public void addTimeSpan(ITimeSpan timespan) {
		_dietPlan.getTimeSpans().add(((TimeSpanDO)timespan).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#removeTimeSpan(at.easydiet.domain.object.TimeSpanDO)
	 */
	@Override
	public void removeTimeSpan(ITimeSpan timespan) {
		_dietPlan.getTimeSpans().remove(((TimeSpanDO)timespan).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#getDietTreatment()
	 */
	@Override
	public DietTreatmentViewable getDietTreatment() {
		return new DietTreatmentDO(_dietPlan.getDietTreatment());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#setDietTreatment(at.easydiet.application.viewobject.DietTreatmentViewable)
	 */
	@Override
	public void setDietTreatment(DietTreatmentViewable dietTreatment) {
		// TODO: check that this dietTreamtent is from the same patient as this dietplan
		DietTreatmentDO d = (DietTreatmentDO) dietTreatment;
		_dietPlan.setDietTreatment((d != null) ? d.getModel() : null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietPlan#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_dietPlan == null);
	}

	@Override
	public DietPlan getModel() {
		return _dietPlan;
	}

	@Override
	public void saveDietPlan() throws DatabaseException {
		// TODO Auto-generated method stub
		DietPlanDAO dpDAO = DAOFactory.getInstance().getDietPlanDAO();
		try {
			dpDAO.makePersistent(_dietPlan);
		} catch (HibernateException ex) {
			LOGGER.error(ex.toString());
			throw new DatabaseException(ex);
		}
	}

	@Override
	public void deleteDietPlan() throws DatabaseException {
		DietPlanDAO dpDAO = DAOFactory.getInstance().getDietPlanDAO();
		try {
			dpDAO.makeTransient(_dietPlan);
		} catch (HibernateException ex) {
			LOGGER.error(ex.toString());
			throw new DatabaseException(ex);
		}
	}
}
