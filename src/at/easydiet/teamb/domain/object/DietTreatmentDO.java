package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.viewobject.ContactJournalViewable;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.DietPlanViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentSystemUserViewable;
import at.easydiet.teamb.application.viewobject.NutritionProtocolViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.TreatmentStateViewable;
import at.easydiet.teamb.domain.IContactJournal;
import at.easydiet.teamb.domain.IDietTreatment;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.ContactJournal;
import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.DietTreatmentSystemUser;
import at.easydiet.model.NutritionProtocol;
import at.easydiet.model.PatientState;
import at.easydiet.model.TreatmentState;

/**
 * Represents a DietTreatment in the domain layer
 */
public class DietTreatmentDO extends AbstractDO<DietTreatment> implements IDietTreatment {
	private static Logger LOGGER = Logger.getLogger(DietTreatmentDO.class);

	private DietTreatment _dietTreatment;

	/**
	 * Instantiates a new diet treatment.
	 * 
	 * @param dietTreatment
	 *            the diet treatment
	 */
	public DietTreatmentDO(DietTreatment dietTreatment) {
		if (dietTreatment == null) {
			LOGGER.debug("DietTreatment is null");
		}

		_dietTreatment = dietTreatment;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getDietTreatmentId()
	 */
	@Override
	public long getDietTreatmentId() {
		return _dietTreatment.getDietTreatmentId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#setDietTreatmentId(long)
	 */
	@Override
	public void setDietTreatmentId(long dietTreatmentId) {
		_dietTreatment.setDietTreatmentId(dietTreatmentId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getStart()
	 */
	@Override
	public GregorianCalendar getStart() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_dietTreatment.getStart());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#setStart(java.util.GregorianCalendar)
	 */
	@Override
	public void setStart(GregorianCalendar start) {
		_dietTreatment.setStart(CalendarUtil.ConvertGregorianCalendarToDate(start));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getDuration()
	 */
	@Override
	public int getDuration() {
		return _dietTreatment.getDuration();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#setDuration(int)
	 */
	@Override
	public void setDuration(int duration) {
		_dietTreatment.setDuration(duration);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getName()
	 */
	@Override
	public String getName() {
		return _dietTreatment.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_dietTreatment.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getNutritionProtocols()
	 */
	@Override
	public NutritionProtocolViewable[] getNutritionProtocols() {
		return ListConverter.toDOArray(_dietTreatment.getNutritionProtocols(), new ListConverter.ModelToDO<NutritionProtocolDO, NutritionProtocol>() {
			@Override
			public NutritionProtocolDO convert(NutritionProtocol model) {
				return new NutritionProtocolDO(model);
			}
		}).toArray(new NutritionProtocolViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#addNutritionProtocol(at.easydiet.domain.object.NutritionProtocolDO)
	 */
	@Override
	public void addNutritionProtocol(NutritionProtocolDO nutritionProtocol) {
		_dietTreatment.getNutritionProtocols().add(nutritionProtocol.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#removeNutritionProtocol(at.easydiet.domain.object.NutritionProtocolDO)
	 */
	@Override
	public void removeNutritionProtocol(NutritionProtocolDO nutritionProtocol) {
		_dietTreatment.getNutritionProtocols().remove(nutritionProtocol.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getDietPlans()
	 */
	@Override
	public DietPlanViewable[] getDietPlans() {
		return ListConverter.toDOArray(_dietTreatment.getDietPlans(), new ListConverter.ModelToDO<DietPlanDO, DietPlan>() {
			@Override
			public DietPlanDO convert(DietPlan model) {
				return new DietPlanDO(model);
			}
		}).toArray(new DietPlanViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#addDietPlan(at.easydiet.domain.object.DietPlanDO)
	 */
	@Override
	public void addDietPlan(DietPlanDO dietPlan) {
		_dietTreatment.getDietPlans().add(dietPlan.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#removeDietPlans(at.easydiet.domain.object.DietPlanDO)
	 */
	@Override
	public void removeDietPlans(DietPlanDO dietPlans) {
		_dietTreatment.getDietPlans().remove(dietPlans.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getDietParameters()
	 */
	@Override
	public DietParameterViewable[] getDietParameters() {
		return ListConverter.toDOArray(_dietTreatment.getDietParameters(), new ListConverter.ModelToDO<DietParameterDO, DietParameter>() {
			@Override
			public DietParameterDO convert(DietParameter model) {
				return new DietParameterDO(model);
			}
		}).toArray(new DietParameterViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#addDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void addDietParameter(DietParameterDO dietParameter) {
		_dietTreatment.getDietParameters().add(dietParameter.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#removeDietParameter(at.easydiet.domain.object.DietParameterDO)
	 */
	@Override
	public void removeDietParameter(DietParameterDO dietParameter) {
		_dietTreatment.getDietParameters().remove(dietParameter.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getPatientStates()
	 */
	@Override
	public PatientStateViewable[] getPatientStates() {
		return ListConverter.toDOArray(_dietTreatment.getPatientStates(), new ListConverter.ModelToDO<PatientStateDO, PatientState>() {
			@Override
			public PatientStateDO convert(PatientState model) {
				return new PatientStateDO(model);
			}
		}).toArray(new PatientStateViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#addPatientState(at.easydiet.domain.object.PatientStateDO)
	 */
	@Override
	public void addPatientState(PatientStateDO patientState) {
		_dietTreatment.getPatientStates().add(patientState.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#removePatientState(at.easydiet.domain.object.PatientStateDO)
	 */
	@Override
	public void removePatientState(PatientStateDO patientState) {
		_dietTreatment.getPatientStates().remove(patientState.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getSystemUsers()
	 */
	@Override
	public DietTreatmentSystemUserViewable[] getSystemUsers() {
		return ListConverter.toDOArray(_dietTreatment.getSystemUsers(), new ListConverter.ModelToDO<DietTreatmentSystemUserDO, DietTreatmentSystemUser>() {
			@Override
			public DietTreatmentSystemUserDO convert(DietTreatmentSystemUser model) {
				return new DietTreatmentSystemUserDO(model);
			}
		}).toArray(new DietTreatmentSystemUserViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#addSystemUser(at.easydiet.domain.object.DietTreatmentSystemUserDO)
	 */
	@Override
	public void addSystemUser(DietTreatmentSystemUserDO dietTreatmentSystemUser) {
		_dietTreatment.getSystemUsers().add(dietTreatmentSystemUser.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#removeSystemUser(at.easydiet.domain.object.DietTreatmentSystemUserDO)
	 */
	@Override
	public void removeSystemUser(DietTreatmentSystemUserDO dietTreatmentSystemUser) {
		_dietTreatment.getSystemUsers().remove(dietTreatmentSystemUser.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getContactJournals()
	 */
	@Override
	public ContactJournalViewable[] getContactJournals() {
		return ListConverter.toDOArray(_dietTreatment.getContactJournals(), new ListConverter.ModelToDO<ContactJournalDO, ContactJournal>() {
			@Override
			public ContactJournalDO convert(ContactJournal model) {
				return new ContactJournalDO(model);
			}
		}).toArray(new ContactJournalViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#addContactJournal(at.easydiet.domain.IContactJournal)
	 */
	@Override
	public void addContactJournal(IContactJournal contactJournal) {
		_dietTreatment.getContactJournals().add(((ContactJournalDO) contactJournal).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#removeContactJournal(at.easydiet.domain.IContactJournal)
	 */
	@Override
	public void removeContactJournal(IContactJournal contactJournal) {
		_dietTreatment.getContactJournals().remove(((ContactJournalDO) contactJournal).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getTreatmentState()
	 */
	@Override
	public TreatmentStateViewable getTreatmentState() {
		return new TreatmentStateDO(_dietTreatment.getTreatmentState());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#setTreatmentState(at.easydiet.model.TreatmentState)
	 */
	@Override
	public void setTreatmentState(TreatmentState treatmentState) {
		_dietTreatment.setTreatmentState(treatmentState);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#getPatient()
	 */
	@Override
	public PatientViewable getPatient() {
		return new PatientDO((_dietTreatment == null) ? null : _dietTreatment.getPatient());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#setPatient(at.easydiet.application.viewobject.PatientViewable)
	 */
	@Override
	public void setPatient(PatientViewable patient) {
		PatientDO d = (PatientDO) patient;
		_dietTreatment.setPatient(d.getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietTreatment#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_dietTreatment == null);
	}

	@Override
	public DietTreatment getModel() {
		return _dietTreatment;
	}
}
