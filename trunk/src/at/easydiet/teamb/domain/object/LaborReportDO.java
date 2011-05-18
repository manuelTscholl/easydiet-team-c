package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;
import java.util.Set;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.LaborParameterViewable;
import at.easydiet.teamb.application.viewobject.LaborReportTypeViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.ILaborReport;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.LaborParameter;
import at.easydiet.model.LaborReport;
import at.easydiet.model.PatientState;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.LaborReportDAO;

/**
 * Represents a LaborReportDomainObject
 */
public class LaborReportDO extends AbstractDO<LaborReport> implements ILaborReport {
	private static Logger LOGGER = Logger.getLogger(LaborReportDO.class);

	private LaborReport _laborReport;

	/**
	 * Instantiates a new labor report do.
	 * 
	 * @param laborReport
	 *            the labor report
	 */
	public LaborReportDO(LaborReport laborReport) {
		if (laborReport == null) {
			LOGGER.debug("CheckOperator is null");
		}

		_laborReport = laborReport;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#getLaborReportId()
	 */
	@Override
	public long getLaborReportId() {
		return _laborReport.getLaborReportId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#setLaborReportId(long)
	 */
	@Override
	public void setLaborReportId(long laborReportId) {
		_laborReport.setLaborReportId(laborReportId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#getDate()
	 */
	@Override
	public GregorianCalendar getDate() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_laborReport.getDate());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#setDate(java.util.GregorianCalendar)
	 */
	@Override
	public void setDate(GregorianCalendar date) {
		_laborReport.setDate(CalendarUtil.ConvertGregorianCalendarToDate(date));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#getNotice()
	 */
	@Override
	public String getNotice() {
		return ClobConverter.ClobToString(_laborReport.getNotice());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#setNotice(java.lang.String)
	 */
	@Override
	public void setNotice(String notice) {
		_laborReport.setNotice(ClobConverter.StringToClob(notice));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#getCreator()
	 */
	@Override
	public SystemUserViewable getCreator() {
		return new SystemUserDO(_laborReport.getCreator());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#setCreator(at.easydiet.application.viewobject.SystemUserViewable)
	 */
	@Override
	public void setCreator(SystemUserViewable creator) {
		_laborReport.setCreator(creator.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#getDietParameters()
	 */
	@Override
	public LaborParameterViewable[] getLaborParameters() {
		return ListConverter.toDOArray(_laborReport.getLaborParameters(), new ListConverter.ModelToDO<LaborParameterDO, LaborParameter>() {
			@Override
			public LaborParameterDO convert(LaborParameter model) {
				return new LaborParameterDO(model);
			}
		}).toArray(new LaborParameterViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#addDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void addLaborParameter(LaborParameterViewable parameter) {
		_laborReport.getLaborParameters().add(((LaborParameterDO)parameter).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#removeDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void removeLaborParameter(LaborParameterViewable parameter) {
		_laborReport.getLaborParameters().remove(((LaborParameterDO)parameter).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#getPatient()
	 */
	@Override
	public PatientViewable getPatient() {
		return new PatientDO(_laborReport.getPatient());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReport#setPatient(at.easydiet.application.viewobject.PatientViewable)
	 */
	@Override
	public void setPatient(PatientViewable patient) {
		_laborReport.setPatient(patient.getModel());
	}

	@Override
	public void setType(LaborReportTypeViewable type) {
		_laborReport.setLaborReportType(((LaborReportTypeDO)type).getModel());
		
	}

	@Override
	public LaborReportTypeViewable getType() {
		return (_laborReport.getLaborReportType() == null) ? null : new LaborReportTypeDO(_laborReport.getLaborReportType());
	}
	
	@Override
	public void save() throws DatabaseException {
		_laborReport.getPatient().getLaborReports().add(_laborReport);
		LaborReportDAO dao = DAOFactory.getInstance().getLaborReportDAO();
		dao.makePersistent(_laborReport);
	}
	
	@Override
	public void delete() throws DatabaseException{
		Set<LaborReport> set = _laborReport.getPatient().getLaborReports();
		if(!set.isEmpty()){
			set.remove(_laborReport);
		}
		LaborReportDAO dao = DAOFactory.getInstance().getLaborReportDAO();
		dao.makeTransient(_laborReport);
	}
	
	@Override
	public void discard() {
		LOGGER.debug("discarding changes of " + _laborReport);
		if (isInDatabase()) {
			HibernateUtil.currentSession().evict(_laborReport);
			_laborReport = DAOFactory.getInstance().getLaborReportDAO().findById(_laborReport.getLaborReportId(), false);
		} else {
			_laborReport = new LaborReport();
		}
	}
	
	@Override
	public boolean isInDatabase() {
		return HibernateUtil.currentSession().contains(_laborReport);
	}
	
	@Override
	public LaborReport getModel() {
		return _laborReport;
	}
}
