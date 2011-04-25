package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.dao.DAOFactory;
import at.easydiet.teamc.model.data.MealCodeData;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.Meal;
import at.easydiet.model.TimeSpan;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.ParameterCheckResult;

/**
 * DietPlanBo generated by hbm2java
 */
public class DietPlanBo implements java.io.Serializable, Saveable, DietryPlanData {

    private PlanTypeBo _planType;
    private SystemUserBo _creator;
    private DietPlan _DietPlan;

    protected DietPlanBo() {
    }

    public DietPlanBo(DietPlan dietPlan) {
        this._DietPlan = dietPlan;
    }

    public DietPlanBo(String name, Date createdOn, PlanTypeBo planTypeBo, SystemUserBo creatorBo) {
        this(new DietPlan(name, createdOn, planTypeBo.getPlanType(), creatorBo.getSystemUser()));
        this._planType = planTypeBo;
        this._creator = creatorBo;
    }

    public DietPlanBo(String name, Date createdOn, PlanTypeBo planType, Set<DietParameterBo> dietParameters, SystemUserBo creator, Set<TimeSpanBo> timeSpans) {
        this(name, createdOn, planType, creator);

        for (DietParameterBo dietParameterBo : dietParameters) {
            this._DietPlan.getDietParameters().add(dietParameterBo.getDietParameter());
        }

        for (TimeSpanBo timeSpanBo : timeSpans) {
            this._DietPlan.getTimeSpans().add(timeSpanBo.getTimeSpan());
        }
    }

    public long getDietPlanId() {
        return this.getDietPlan().getDietPlanId();
    }

    public void setDietPlanId(long dietPlanId) {
        this.getDietPlan().setDietPlanId(dietPlanId);
    }

    public String getName() {
        return this.getDietPlan().getName();
    }

    public void setName(String name) {
        this.getDietPlan().setName(name);
    }

    public Date getCreatedOn() {
        return this.getDietPlan().getCreatedOn();
    }

    public void setCreatedOn(Date createdOn) {
        this.getDietPlan().setCreatedOn(createdOn);
    }

    public PlanTypeBo getPlanType() {
        return this._planType;
    }

    public void setPlanType(PlanTypeBo planType) {
        this.getDietPlan().setPlanType(planType.getPlanType());
        this._planType = planType;
    }

    public Set<DietParameterBo> getDietParameters() {
        Set<DietParameterBo> temp = new HashSet<DietParameterBo>(this._DietPlan.getDietParameters().size());
        for (DietParameter dietParameter : this._DietPlan.getDietParameters()) {
            temp.add(new DietParameterBo(dietParameter));
        }
        return temp;
    }

    public void setDietParameters(Set<DietParameterBo> dietParametersBo) {
        for (DietParameterBo dietParameterBo : dietParametersBo) {
            this._DietPlan.getDietParameters().add(dietParameterBo.getDietParameter());
        }
    }

    public SystemUserBo getCreator() {
        return this._creator;
    }

    public void setCreator(SystemUserBo creator) {
        this.getDietPlan().setCreator(creator.getSystemUser());
        this._creator = creator;
    }

    public Set<TimeSpanBo> getTimeSpans() {
        Set<TimeSpanBo> temp = new HashSet<TimeSpanBo>(this._DietPlan.getTimeSpans().size());
        for (TimeSpan timeSpan : this._DietPlan.getTimeSpans()) {
            temp.add(new TimeSpanBo(timeSpan));
        }
        return temp;
    }

    public void setTimeSpans(Set<TimeSpanBo> timeSpansBo) {
        for (TimeSpanBo timeSpanBo : timeSpansBo) {
            this._DietPlan.getTimeSpans().add(timeSpanBo.getTimeSpan());
        }
    }

    public void addTimeSpan(TimeSpanBo timeSpanBo){
        this._DietPlan.getTimeSpans().add(timeSpanBo.getTimeSpan());
    }

    /**
     * @return the _DietPlan
     */
    protected DietPlan getDietPlan() {
        return _DietPlan;
    }

    /**
     * @param DietPlan the _DietPlan to set
     */
    public void setDietPlan(DietPlan DietPlan) {
        this._DietPlan = DietPlan;
    }

    @Override
    public boolean save() {
       if(DAOFactory.getInstance().getDietPlanDAO().makePersistent(this._DietPlan)!=null){
	        return true;
        }
	    return false;

    }

    public MealBo addMealCode(MealCodeData mcd, int day) {
    	MealBo mBo=new MealBo(new Meal(mcd.getCode(), mcd.getName()));
        serachTimespan(day).addMeal(mBo);
    	return mBo;
    }

    public int addMealLine(MealBo currentMealBo) {
       return currentMealBo.addMealLine();
    }

    public ParameterCheckResult checkRecipeWithParameters(RecipeBo rb) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void addRecipe(RecipeBo recipeBo, double quantity, int mealLineID) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getDuration() {
        int temp=0;
        if(getTimeSpans().size()>0){
            for (TimeSpanBo tsb : this.getTimeSpans()) {
                temp=temp+tsb.getDuration();
            }
        }
        return temp;
    }

    /**
     * Looks for the right Timespan in a diatplan.
     *
     * @param day
     * @return
     */
    private TimeSpanBo serachTimespan(int day){
        for(TimeSpanBo tsb:this.getTimeSpans()){
            day=day-tsb.getDuration();
            if(day<=0){
                return tsb;
            }
        }
        return null;
    }
}
