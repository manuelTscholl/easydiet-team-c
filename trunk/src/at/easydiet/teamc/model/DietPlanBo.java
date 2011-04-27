package at.easydiet.teamc.model;
// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.teamc.model.data.MealData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.DietParameter;
import at.easydiet.model.DietParameterTemplate;
import at.easydiet.model.DietPlan;
import at.easydiet.model.Meal;
import at.easydiet.model.TimeSpan;
import at.easydiet.teamc.model.data.DietParameterData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.ParameterCheckResult;

/**
 * DietPlanBo generated by hbm2java
 */
public class DietPlanBo implements java.io.Serializable, Saveable, DietryPlanData {

    private PlanTypeBo _planType;
    private SystemUserBo _creator;
    private DietPlan _dietPlan;

    /**
     *
     */
    protected DietPlanBo() {
    }

    /**
     *
     * @param dietPlan
     */
    public DietPlanBo(DietPlan dietPlan) {
        this._dietPlan = dietPlan;
    }

    /**
     *
     * @param name
     * @param createdOn
     * @param planTypeBo
     * @param creatorBo
     */
    public DietPlanBo(String name, Date createdOn, PlanTypeBo planTypeBo, SystemUserBo creatorBo, DietTreatmentBo dietTreatmentBo) {
        this(new DietPlan(name, createdOn, planTypeBo.getPlanType(), dietTreatmentBo.getDietTreatment(), creatorBo.getSystemUser()));
        this._planType = planTypeBo;
        this._creator = creatorBo;
    }

    /**
     *
     * @param name
     * @param createdOn
     * @param planType
     * @param dietParameters
     * @param creator
     * @param timeSpans
     */
    public DietPlanBo(String name, Date createdOn, PlanTypeBo planType, Set<DietParameterBo> dietParameters, SystemUserBo creator, Set<TimeSpanBo> timeSpans, DietTreatmentBo dietTreatmentBo) {
        this(name, createdOn, planType, creator, dietTreatmentBo);

        for (DietParameterBo dietParameterBo : dietParameters) {
            this._dietPlan.getDietParameters().add(dietParameterBo.getDietParameter());
        }

        for (TimeSpanBo timeSpanBo : timeSpans) {
            this._dietPlan.getTimeSpans().add(timeSpanBo.getTimeSpan());
        }
    }

    /**
     *
     * @return
     */
    public long getDietPlanId() {
        return this.getDietPlan().getDietPlanId();
    }

    /**
     *
     * @param dietPlanId
     */
    public void setDietPlanId(long dietPlanId) {
        this.getDietPlan().setDietPlanId(dietPlanId);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.getDietPlan().getName();
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.getDietPlan().setName(name);
    }

    /**
     *
     * @return
     */
    public Date getCreatedOn() {
        return this.getDietPlan().getCreatedOn();
    }

    /**
     *
     * @param createdOn
     */
    public void setCreatedOn(Date createdOn) {
        this.getDietPlan().setCreatedOn(createdOn);
    }

    /**
     *
     * @return
     */
    public PlanTypeBo getPlanType() {
        return this._planType;
    }

    /**
     *
     * @param planType
     */
    public void setPlanType(PlanTypeBo planType) {
        this.getDietPlan().setPlanType(planType.getPlanType());
        this._planType = planType;
    }

    /**
     *
     * @return
     */
    public Set<DietParameterBo> getDietParameters() {
        Set<DietParameterBo> temp = new HashSet<DietParameterBo>(this._dietPlan.getDietParameters().size());
        for (DietParameter dietParameter : this._dietPlan.getDietParameters()) {
            temp.add(new DietParameterBo(dietParameter));
        }
        return temp;
    }

    /**
     *
     * @param dietParametersBo
     */
    public void setDietParameters(Set<DietParameterBo> dietParametersBo) {
        for (DietParameterBo dietParameterBo : dietParametersBo) {
            this._dietPlan.getDietParameters().add(dietParameterBo.getDietParameter());
        }
    }

    /**
     *
     * @return
     */
    public SystemUserBo getCreator() {
        return this._creator;
    }

    /**
     *
     * @param creator
     */
    public void setCreator(SystemUserBo creator) {
        this.getDietPlan().setCreator(creator.getSystemUser());
        this._creator = creator;
    }

    /**
     *
     * @return
     */
    public Set<TimeSpanBo> getTimeSpans() {
        Set<TimeSpanBo> temp = new HashSet<TimeSpanBo>(this._dietPlan.getTimeSpans().size());
        for (TimeSpan timeSpan : this._dietPlan.getTimeSpans()) {
            temp.add(new TimeSpanBo(timeSpan));
        }
        return temp;
    }

    /**
     *
     * @param timeSpansBo
     */
    public void setTimeSpans(Set<TimeSpanBo> timeSpansBo) {
        for (TimeSpanBo timeSpanBo : timeSpansBo) {
            this._dietPlan.getTimeSpans().add(timeSpanBo.getTimeSpan());
        }
    }

    /**
     *
     * @param timeSpanBo
     */
    public void addTimeSpan(TimeSpanBo timeSpanBo) {
        this._dietPlan.getTimeSpans().add(timeSpanBo.getTimeSpan());
    }

    /**
     * @return the _dietPlan
     */
    protected DietPlan getDietPlan() {
        return _dietPlan;
    }

    /**
     * @param DietPlan the _dietPlan to set
     */
    public void setDietPlan(DietPlan DietPlan) {
        this._dietPlan = DietPlan;
    }

    /**
     *
     * @return
     */
    @Override
    /**
     * Saves a diatplan in database.
     */
    public boolean save() {
        if (DAOFactory.getInstance().getDietPlanDAO().makePersistent(this._dietPlan) != null) {
            return true;
        }
        return false;

    }

    /**
     * Adds a meal to a diaplan.
     *
     * @param mcd
     * @param day
     * @return
     */
    public MealBo addMealCode(MealCodeData mcd, int day) {
        //TODO better name for that method addMeal instead of addMealCode
        TimeSpanBo timeSpanBo = serachTimespan(day);
        MealBo mBo = new MealBo(new Meal(mcd.getCode(), mcd.getName(), timeSpanBo.getTimeSpan()));
        timeSpanBo.addMeal(mBo);
        return mBo;
    }

    /**
     * Adds a line to meal in progress.
     *
     * @param currentMealBo
     * @return
     */
    public int addMealLine(MealBo currentMealBo) {
        return currentMealBo.addMealLine();
    }

    /**
     * Checks recipe with parameters if recipe has quantity 100g.
     *
     * @param rb
     * @param pb
     * @return
     */
    public ParameterCheckResult checkRecipeWithParameters(RecipeBo rb, DietTreatmentBo dtb, DietPlanBo dpb) {
        ParameterCheckResult pcr = new ParameterCheckResult();
        Set<DietParameterBo> temp = (HashSet<DietParameterBo>) dtb.getDietParameters();

        double currentparametervalue = 0;
        double averageparametervalue = 0;

        for (DietParameterBo dietParameterBo : dpb.getDietParameters()) {
            temp.add(dietParameterBo);
        }

        for (NutrimentParameterBo nutrimentParameterBo : rb.getNutrimentParameters()) {
            for (DietParameterBo dietParameterBo : temp) {
                if (nutrimentParameterBo.getParameterDefinition().getName().equals(dietParameterBo.getParameterDefinition().getName())) {
                    currentparametervalue = 0;

                    for (TimeSpanBo tsb : dpb.getTimeSpans()) {
                        for (MealBo mb : tsb.getMeals()) {
                            for (MealLineBo mlb : mb.getMealLines()) {
                                if (mlb.getMealLines().size() > 0) {
                                    averageparametervalue = 0;
                                    for (MealLineBo mlbmlb : mlb.getMealLines()) {

                                        averageparametervalue += mlbmlb.getRecipe().getNutrimentParameterValue(nutrimentParameterBo);
                                    }
                                    currentparametervalue += (averageparametervalue / mlb.getMealLines().size());
                                } else {
                                    currentparametervalue += mlb.getRecipe().getNutrimentParameterValue(nutrimentParameterBo);
                                }
                            }
                        }

                    }
                    if (dietParameterBo.getCheckOperatorBo().getName().equals(">=")) {
                        if (currentparametervalue <= Double.parseDouble(dietParameterBo.getParameterValue())) {
                            pcr.addDietParameter((DietParameterData) dietParameterBo);
                        }
                    } else {
                        if (currentparametervalue >= Double.parseDouble(dietParameterBo.getParameterValue())) {
                            pcr.addDietParameter((DietParameterData) dietParameterBo);
                        }
                    }

                }

            }
        }
        return pcr;
    }

    /**
     *
     * @return cumulativ duration of all timespans of a diatplan
     */
    public int getDuration() {
        int temp = 0;


        if (getTimeSpans().size() > 0) {
            for (TimeSpanBo tsb : this.getTimeSpans()) {
                temp = temp + tsb.getDuration();


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
    private TimeSpanBo serachTimespan(int day) {
        for (TimeSpanBo tsb : this.getTimeSpans()) {
            day = day - tsb.getDuration();


            if (day <= 0) {
                return tsb;


            }
        }
        return null;

    }

    public MealData getMealData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public List<DietPlanParameterCollectionVo> getDietPlanParameterCollectionVo() {
        HashMap<String, ValueRange> parameters=new HashMap<String, ValueRange>();
        
        Set<DietParameterBo> dpbo= this.getDietParameters();
        ArrayList<DietPlanParameterCollectionVo> paramsToReturn=new ArrayList<DietPlanParameterCollectionVo>();
       
        for(DietParameterBo currentDietParamBo: dpbo){
        	if(!parameters.containsKey(currentDietParamBo.getParameterDefinition().getName())){
        		parameters.put(currentDietParamBo.getParameterDefinition().getName(), new ValueRange(0,0,0));
        		
        		if(currentDietParamBo.getCheckOperatorBo().getName().equals(">=")){
        			parameters.get(currentDietParamBo.getParameterDefinition().getName()).
        			setMin(Double.parseDouble(currentDietParamBo.getValue()));        			
        		}else{        		
        			parameters.get(currentDietParamBo.getParameterDefinition().getName()).
        			setMax(Double.parseDouble(currentDietParamBo.getValue())); 
        		}
        	}else{
        		if(currentDietParamBo.getCheckOperatorBo().getName().equals(">=")){
        			parameters.get(currentDietParamBo.getParameterDefinition().getName()).
        			setMin(Double.parseDouble(currentDietParamBo.getValue()));        			
        		}else{        		
        			parameters.get(currentDietParamBo.getParameterDefinition().getName()).
        			setMax(Double.parseDouble(currentDietParamBo.getValue())); 
        		}
        	}
        }

        //TODO q&d solution
        for(String name:parameters.keySet()){
            paramsToReturn.add(new DietPlanParameterCollectionVo(name, parameters.get(name).getMin(), parameters.get(name).getMax(), parameters.get(name).getCurrent()));
        }
        //get the current values
        return paramsToReturn;
        
}
        
        
        
        


    public Set<MealData> getMealsByDay(int day) {
        Set<MealData> _meals = new HashSet<MealData>();;
        TimeSpan[] timespans = _dietPlan.getTimeSpans().toArray(new TimeSpan[_dietPlan.getTimeSpans().size()]);

        TimeSpan currTimespan = null;
        for (int i = 0; i < timespans.length; i++) {
            currTimespan = timespans[i];
            if (i == day) {
                for (Meal m : currTimespan.getMeals()) {
                    MealBo mBo = new MealBo(m);
                    _meals.add((MealData) mBo);
                }
            }
        }
        return _meals;

    }
}
