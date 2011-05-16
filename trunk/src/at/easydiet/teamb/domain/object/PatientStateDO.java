package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;
import java.util.Set;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientStateTypeViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.IPatientState;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.LaborReport;
import at.easydiet.model.PatientState;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.PatientStateDAO;

/**
 * Represents a PatientState in the domain layer
 */
public class PatientStateDO extends AbstractDO<PatientState> implements IPatientState {
	private static Logger LOGGER = Logger.getLogger(PatientStateDO.class);

	private PatientState _patientState;

	/**
	 * Instantiates a new patientstate.
	 * 
	 * @param patientState
	 *            the patientstate
	 */
	public PatientStateDO(PatientState patientState) {
		if (patientState == null) {
			LOGGER.debug("PatientState is null");
		}

		_patientState = patientState;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getPatientStateId()
	 */
	@Override
	public long getPatientStateId() {
		return _patientState.getPatientStateId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setPatientStateId(long)
	 */
	@Override
	public void setPatientStateId(long patientStateId) {
		_patientState.setPatientStateId(patientStateId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getDate()
	 */
	@Override
	public GregorianCalendar getDate() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_patientState.getDate());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setDate(java.util.GregorianCalendar)
	 */
	@Override
	public void setDate(GregorianCalendar date) {
		_patientState.setDate(CalendarUtil.ConvertGregorianCalendarToDate(date));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getAnamnesis()
	 */
	@Override
	public String getAnamnesis() {
		return ClobConverter.ClobToString(_patientState.getAnamnesis());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setAnamnesis(java.lang.String)
	 */
	@Override
	public void setAnamnesis(String anamnesis) {
		_patientState.setAnamnesis(ClobConverter.StringToClob(anamnesis));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getWeight()
	 */
	@Override
	public Integer getWeight() {
		return _patientState.getWeight();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setWeight(java.lang.Integer)
	 */
	@Override
	public void setWeight(Integer weight) {
		_patientState.setWeight(weight);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getWeightPercentile()
	 */
	@Override
	public float getWeightPercentile() {
		return _patientState.getWeightPercentile();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setWeightPercentile(float)
	 */
	@Override
	public void setWeightPercentile(float weightPercentile) {
		_patientState.setWeightPercentile(weightPercentile);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getHeight()
	 */
	@Override
	public Integer getHeight() {
		return _patientState.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setHeight(java.lang.Integer)
	 */
	@Override
	public void setHeight(Integer height) {
		_patientState.setHeight(height);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getHeightPercentile()
	 */
	@Override
	public float getHeightPercentile() {
		return _patientState.getHeightPercentile();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setHeightPercentile(float)
	 */
	@Override
	public void setHeightPercentile(float heightPercentile) {
		_patientState.setHeightPercentile(heightPercentile);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getCompliance()
	 */
	@Override
	public int getCompliance() {
		return _patientState.getCompliance();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setCompliance(int)
	 */
	@Override
	public void setCompliance(int compliance) {
		_patientState.setCompliance(compliance);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getMotivation()
	 */
	@Override
	public int getMotivation() {
		return _patientState.getMotivation();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setMotivation(int)
	 */
	@Override
	public void setMotivation(int motivation) {
		_patientState.setMotivation(motivation);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getType()
	 */
	@Override
	public PatientStateTypeViewable getType() {
		return (_patientState.getType() == null) ? null : new PatientStateTypeDO(_patientState.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setType(at.easydiet.application.viewobject.PatientStateTypeViewable)
	 */
	@Override
	public void setType(PatientStateTypeViewable type) {
		PatientStateTypeDO d = (PatientStateTypeDO) type;
		_patientState.setType(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getCreator()
	 */
	@Override
	public SystemUserViewable getCreator() {
		return new SystemUserDO(_patientState.getCreator());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setCreator(at.easydiet.application.viewobject.SystemUserViewable)
	 */
	@Override
	public void setCreator(SystemUserViewable creator) {
		SystemUserDO d = (SystemUserDO) creator;
		_patientState.setCreator(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getLaborReports()
	 */
	@Override
	public LaborReportViewable[] getLaborReports() {
		return ListConverter.toDOArray(_patientState.getLaborReports(), new ListConverter.ModelToDO<LaborReportDO, LaborReport>() {
			@Override
			public LaborReportDO convert(LaborReport model) {
				return new LaborReportDO(model);
			}
		}).toArray(new LaborReportViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#addLabporReport(at.easydiet.domain.object.LaborReportDO)
	 */
	@Override
	public void addLaborReport(LaborReportViewable laborReport) {
		_patientState.getLaborReports().add(((LaborReportDO)laborReport).getModel());
	}
	
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#removeLabporReport(at.easydiet.domain.object.LaborReportDO)
	 */
	@Override
	public void removeLaborReport(LaborReportViewable laborReport) {
		_patientState.getLaborReports().remove(((LaborReportDO)laborReport).getModel());
	}
	
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getDietTreatments()
	 */
	@Override
	public DietTreatmentViewable[] getDietTreatments() {
		return ListConverter.toDOArray(_patientState.getDietTreatments(), new ListConverter.ModelToDO<DietTreatmentDO, DietTreatment>() {
			@Override
			public DietTreatmentDO convert(DietTreatment model) {
				return new DietTreatmentDO(model);
			}
		}).toArray(new DietTreatmentViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#addDietTreatment(at.easydiet.domain.object.LaborReportDO)
	 */
	@Override
	public void addDietTreatment(DietTreatmentViewable dietTreatment) {
		_patientState.getDietTreatments().add(((DietTreatmentDO)dietTreatment).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#removeDietTreatment(at.easydiet.domain.object.LaborReportDO)
	 */
	@Override
	public void removeDietTreatment(DietTreatmentViewable dietTreatment) {
		_patientState.getDietTreatments().remove(((DietTreatmentDO)dietTreatment).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#getPatient()
	 */
	@Override
	public PatientViewable getPatient() {
		return new PatientDO(_patientState.getPatient());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientState#setPatient(at.easydiet.application.viewobject.PatientViewable)
	 */
	@Override
	public void setPatient(PatientViewable Patient) {
		PatientDO d = (PatientDO) Patient;
		_patientState.setPatient(d.getModel());
	}

	@Override
	public PatientState getModel() {
		return _patientState;
	}

	@Override
	public void save() throws DatabaseException {
		_patientState.getPatient().getPatientStates().add(_patientState);
		PatientStateDAO dao = DAOFactory.getInstance().getPatientStateDAO();
		dao.makePersistent(_patientState);
	}
	
	@Override
	public void discard() {
		LOGGER.debug("discarding changes of " + _patientState);
		if (isInDatabase()) {
			HibernateUtil.currentSession().evict(_patientState);
			_patientState = DAOFactory.getInstance().getPatientStateDAO().findById(_patientState.getPatientStateId(), false);
		} else {
			_patientState = new PatientState();
		}
	}
	
	@Override
	public boolean isInDatabase() {
		return HibernateUtil.currentSession().contains(_patientState);
	}

	@Override
	public void remove() throws DatabaseException {
		Set<PatientState> set = _patientState.getPatient().getPatientStates();
		if(!set.isEmpty()){
			set.remove(_patientState);
		}
		PatientStateDAO dao = DAOFactory.getInstance().getPatientStateDAO();
		dao.makeTransient(_patientState);
	}
}
