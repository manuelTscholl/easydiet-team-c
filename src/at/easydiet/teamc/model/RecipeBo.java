package at.easydiet.teamc.model;

// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.ParameterDefinitionUnit;
import at.easydiet.model.Recipe;
import at.easydiet.model.RecipeIngredient;
import at.easydiet.teamc.controller.DietParameterUnitController;
import at.easydiet.teamc.controller.usecase.SearchParameterController;
import at.easydiet.teamc.model.data.NutrimentParameterData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.RecipeIngredientData;

/**
 * RecipeBo generated by hbm2java
 */
public class RecipeBo implements java.io.Serializable, Saveable, RecipeData {

    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RecipeBo.class);
    private Recipe _Recipe;
    private Map<Long, NutrimentParameterBo> _nutrimentParametersMap;

    private RecipeBo() {
    }

    public RecipeBo(Recipe recipe) {
        this._Recipe = recipe;
    }

    public RecipeBo(Recipe recipe, String name, int difficulty) {
        this(recipe);
        this._Recipe.setName(name);
        this._Recipe.setDifficulty(difficulty);
    }

    public RecipeBo(Recipe recipe, String name, String blsCode, int difficulty,
            Clob description, Clob benefits, Clob cookInstructions,
            Set<RecipeIngredientBo> recipesIngredientsBo,
            Set<NutrimentParameterBo> nutrimentParametersBo) {
        this(recipe, name, difficulty);
        this._Recipe.setBlsCode(blsCode);
        this._Recipe.setDescription(description);
        this._Recipe.setBenefits(benefits);
        this._Recipe.setCookInstructions(cookInstructions);
        for (RecipeIngredientBo recipeIngredientBo : recipesIngredientsBo) {
            this._Recipe.getIngredients().add(
                    recipeIngredientBo.getRecipeIngredient());
        }

        for (NutrimentParameterBo nutrimentParameterBo : nutrimentParametersBo) {
            this._Recipe.getNutrimentParameters().add(
                    nutrimentParameterBo.getNutrimentParameter());
        }

    }

    /**
     * Calculates the values for all nutriment parameter.
     */
    public void calculateNutrimentParameter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getRecipeId() {
        return this.getRecipe().getRecipeId();
    }

    public void setRecipeId(long recipeId) {
        this.getRecipe().setRecipeId(recipeId);
    }

    @Override
    public String getName() {
        return this.getRecipe().getName();
    }

    public void setName(String name) {
        this.getRecipe().setName(name);
    }

    @Override
    public String getBlsCode() {
        return this.getRecipe().getBlsCode();
    }

    public void setBlsCode(String blsCode) {
        this.getRecipe().setBlsCode(blsCode);
    }

    @Override
    public int getDifficulty() {
        return this.getRecipe().getDifficulty();
    }

    public void setDifficulty(int difficulty) {
        this.getRecipe().setDifficulty(difficulty);
    }

    @Override
    public Clob getDescription() {
        return this.getRecipe().getDescription();
    }

    public void setDescription(Clob description) {
        this.getRecipe().setDescription(description);
    }

    @Override
    public Clob getBenefits() {
        return this.getRecipe().getBenefits();
    }

    public void setBenefits(Clob benefits) {
        this.getRecipe().setBenefits(benefits);
    }

    @Override
    public Clob getCookInstructions() {
        return this.getRecipe().getCookInstructions();
    }

    public void setCookInstructions(Clob cookInstructions) {
        this.getRecipe().setCookInstructions(cookInstructions);
    }

    public Set<RecipeIngredientBo> getRecipeIngredientsBo() {
        Set<RecipeIngredientBo> temp = new HashSet<RecipeIngredientBo>(
                this._Recipe.getIngredients().size());
        for (RecipeIngredient recipeIngredient : this._Recipe.getIngredients()) {
            temp.add(new RecipeIngredientBo(recipeIngredient));
        }
        return temp;
    }

    public void setRecipeIngredientsBo(
            Set<RecipeIngredientBo> recipesIngredientsBo) {
        for (RecipeIngredientBo recipeIngredientBo : recipesIngredientsBo) {
            this._Recipe.getIngredients().add(
                    recipeIngredientBo.getRecipeIngredient());
        }
    }

    public Set<NutrimentParameterBo> getNutrimentParameters() {
        //TODO q&d fix für lazy initiating
        if(_nutrimentParametersMap==null){
            this.getNutrimentParametersMap();
        }
        Set<NutrimentParameterBo> temp = new HashSet<NutrimentParameterBo>(
                this._Recipe.getNutrimentParameters().size());
        for (NutrimentParameter nutrimentParameter : this._Recipe.getNutrimentParameters()) {
            temp.add(new NutrimentParameterBo(nutrimentParameter));
        }
        return temp;
    }

    public void setNutrimentParameters(
            Set<NutrimentParameterBo> nutrimentParametersBo) {
        for (NutrimentParameterBo nutrimentParameterBo : nutrimentParametersBo) {
            this._Recipe.getNutrimentParameters().add(
                    nutrimentParameterBo.getNutrimentParameter());
        }
    }

    /**
     * @return the _Recipe
     */
    protected Recipe getRecipe() {
        return _Recipe;
    }

    /**
     * @param Recipe the _Recipe to set
     */
    public void setRecipe(Recipe Recipe) {
        this._Recipe = Recipe;
    }

    @Override
    public boolean save() {
        if (DAOFactory.getInstance().getRecipeDAO().makePersistent(getRecipe()) != null) {
            return true;
        }
        return false;
    }

    protected double getNutrimentParameterValue(NutrimentParameterBo npb) {
        for (NutrimentParameterBo nutrimentParameterBo : this.getNutrimentParameters()) {
            if (nutrimentParameterBo.getParameterDefinition().getName().equals(npb.getParameterDefinition().getName())) {
                return Double.parseDouble(nutrimentParameterBo.getValue());
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return _Recipe.getName();
    }

    @Override
    public List<RecipeIngredientData> getRecipeIngredientsData() {
        List<RecipeIngredientData> temp = new ArrayList<RecipeIngredientData>(
                this.getRecipeIngredientsBo().size());

        if (this.getRecipeIngredientsBo().size() <= 0) {
            temp.add((RecipeIngredientData) this);
            return temp;
        }

        for (RecipeIngredientBo rib : this.getRecipeIngredientsBo()) {
            temp.add((RecipeIngredientData) rib.getIngredient());
        }

        return temp;
    }

    @Override
    public List<NutrimentParameterData> getNutrimentParametersData() {
        List<NutrimentParameterData> temp = new ArrayList<NutrimentParameterData>();

        for (NutrimentParameterBo npb : getNutrimentParameters()) {
            temp.add((NutrimentParameterData)npb);
        }
        return temp;
    }

    @Override
    public float getTotalAmount() {
        if(this._Recipe.getAmount()>0){
        return this._Recipe.getAmount();
        }
        return 0;
    }

    public void setTotalAmount(float amount){
        this._Recipe.setAmount(amount);
    }

    /**
     * updates the recipe calculates new weight and also new parametervalues
     */
    public void calcParameters() {
        // mapping of the special parameter to its sum value class
        Map<Long, ValidationSumValue> summedIngredientParameter = new HashMap<Long, RecipeBo.ValidationSumValue>();
        Map<Long, NutrimentParameter> idParameterMapping = new HashMap<Long, NutrimentParameter>();
        long currParameterdefid;
        float sumValue;

        //reset and calculate new weight
        this.setTotalAmount(0);

        for(RecipeIngredientBo recipeIngredientBo: this.getRecipeIngredientsBo()){
            this.setTotalAmount(this.getTotalAmount()+recipeIngredientBo.getIngredientAmount());
        }

        //calculate new parameter values
        for (RecipeIngredientBo recipeIngredient : getRecipeIngredientsBo()) {// all ingredients

            float brtaifactor=(recipeIngredient.getIngredientAmount()/recipeIngredient.getRecipe().getTotalAmount());//baserecipe to as ingredient factor for absolute units
            float fractionofrecipe=recipeIngredient.getIngredientAmount()/_Recipe.getAmount();//fractionofrecipe needed to get the right fractins of relative units

            for (NutrimentParameter parameter : recipeIngredient.getRecipeIngredient().getRecipe().getNutrimentParameters()) {// for
                // each parameter in the ingredient
                currParameterdefid = parameter.getParameterDefinition().getParameterDefinitionId();
                if (summedIngredientParameter.containsKey(currParameterdefid)) {// if parameter already exists
                    ValidationSumValue sum = summedIngredientParameter.get(currParameterdefid);
                    ParameterDefinitionUnitBo target = sum.getUnitBo();
                    ParameterDefinitionUnitBo source = new ParameterDefinitionUnitBo(
                            parameter.getUnit());


                    try {// converting the type and then sum it

                        sumValue = DietParameterUnitController.getInstance().convert(source, target, Float.parseFloat(parameter.getValue()));

                        if(target.getName().contains("/")){
                            sum.setSum(sum.getSum() + (sumValue*fractionofrecipe));
                        }else{
                            sum.setSum(sum.getSum() + (sumValue*brtaifactor));
                        }

                    } catch (NumberFormatException e) {
                        LOGGER.debug(e);
                    } catch (OperationNotSupportedException e) {
                        LOGGER.debug(e);
                    }

                } else {

                        // if condition /

                    ValidationSumValue validSum = new ValidationSumValue(new ParameterDefinitionUnitBo(parameter.getUnit()));

                    summedIngredientParameter.put(currParameterdefid, validSum);
                    if(validSum._unit.getName().contains("/")){
                        validSum.setSum(Float.parseFloat(parameter.getValue())*fractionofrecipe);
                    }else{
                        validSum.setSum(Float.parseFloat(parameter.getValue())*brtaifactor);
                    }
                    idParameterMapping.put(currParameterdefid,getNutrimentParametersMap().get(currParameterdefid).getNutrimentParameter());
                }

            }
        }

        // all recipes with the correct sum and unit will be added to the recipe
        for (Entry<Long, ValidationSumValue> item : summedIngredientParameter.entrySet()) {
            NutrimentParameter tempValue = idParameterMapping.get(item.getKey());

            tempValue.setUnit(item.getValue().getUnit());
            tempValue.setValue(String.valueOf(item.getValue().getSum()));
        }

    }

    public void addRecipeIngredient(RecipeBo recipeBo, float amount,
            ParameterDefinitionUnitBo pdub) {
        NutrimentParameterBo temp;
        Set<NutrimentParameterBo> tempset = new HashSet<NutrimentParameterBo>();

        if (getNutrimentParametersMap().isEmpty()) {
            _nutrimentParametersMap = new HashMap<Long, NutrimentParameterBo>();
            for (NutrimentParameterBo npb : recipeBo.getNutrimentParameters()) {
                temp = new NutrimentParameterBo(Float.toString(0),
                        npb.getParameterDefinition(), npb.getParameterDefinition().getFirstUnit());
                getNutrimentParametersMap().put(npb.getParameterDefinition().getParameterDefinitionId(), temp);
                this._Recipe.getNutrimentParameters().add(
                        temp.getNutrimentParameter());
                tempset.add(temp);
            }
            this.setNutrimentParameters(tempset);
        }

        RecipeIngredientBo recipeIngredientBo = new RecipeIngredientBo(amount,
                null, recipeBo);
        this._Recipe.getIngredients().add(
                recipeIngredientBo.getRecipeIngredient());
        this.calcParameters();
    }
    
    public ParameterDefinitionUnitBo getUnit()
    {
        return new ParameterDefinitionUnitBo(_Recipe.getUnit());
    }
    
    public void setUnit(ParameterDefinitionUnit unit){
        
        _Recipe.setUnit(unit);
    }

    public void changeRecipeIngredient(float amount, RecipeBo rb,
            ParameterDefinitionUnitBo pdu) {

        RecipeIngredientBo rib = searchRecipeIngredientBo(rb);

        if (rib != null) {
            rib.setAmount(amount);
            rib.setUnit(pdu);
        }
    }

    public void removeRecipeIngredient(RecipeBo recipeBo) {
        RecipeIngredientBo rib = searchRecipeIngredientBo(recipeBo);
        if (rib != null) {
            this._Recipe.getIngredients().remove(rib);
        }
    }

    private RecipeIngredientBo searchRecipeIngredientBo(RecipeBo recipeBo) {
        for (RecipeIngredientBo rib : this.getRecipeIngredientsBo()) {
            if (recipeBo.getName().equals(rib.getRecipe().getName())) {
                return rib;
            }
        }
        return null;
    }

    /**
     * @return the _nutrimentPrameters
     */
    protected Map<Long, NutrimentParameterBo> getNutrimentParametersMap() {

        if (_nutrimentParametersMap == null) {
            _nutrimentParametersMap = new HashMap<Long, NutrimentParameterBo>();
            ParameterDefinitionBo temppdb;
            NutrimentParameterBo tempnpb;

            for (ParameterDefinitionData pdd : SearchParameterController.getInstance().getAllParameterDefinitions()) {
                temppdb = (ParameterDefinitionBo) pdd;

                tempnpb=new NutrimentParameterBo(Float.toString(0), temppdb, temppdb.getFirstUnit());

                _nutrimentParametersMap.put(temppdb.getParameterDefinition().getParameterDefinitionId(),tempnpb);
                this._Recipe.getNutrimentParameters().add(tempnpb.getNutrimentParameter());

            }

        }
        return _nutrimentParametersMap;
    }

    @Override
    public NutrimentParameterData getNutrimentParameterData(
            ParameterDefinitionData pdd) {
        ParameterDefinitionBo pdb = (ParameterDefinitionBo) pdd;
        return getNutrimentParametersMap().get(pdb.getParameterDefinitionId());
    }

    private static class ValidationSumValue {

        private float _sum;
        private ParameterDefinitionUnitBo _unit;

        /**
         * Gets the sum.
         *
         * @return the sum
         */
        public float getSum() {
            return _sum;
        }

        /**
         * Sets the sum.
         *
         * @param sum the sum to set
         */
        public void setSum(float sum) {
            _sum = sum;
        }

        /**
         * Gets the unit.
         *
         * @return the unit
         */
        public ParameterDefinitionUnitBo getUnitBo() {
            return _unit;
        }

        /**
         * Gets the unit.
         *
         * @return the unit
         */
        public ParameterDefinitionUnit getUnit() {
            return _unit.getParameterDefinitionUnit();
        }

        /**
         * Initializes a new instance of the {@link ValidationSumValue} class.
         *
         * @param sum
         * @param unit
         */
        private ValidationSumValue(ParameterDefinitionUnitBo unit) {
            super();
            _sum = 0;
            _unit = unit;
        }
    }
}
