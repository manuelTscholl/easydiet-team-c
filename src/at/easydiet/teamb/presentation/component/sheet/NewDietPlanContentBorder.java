/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	23.04.2011
 */

package at.easydiet.teamb.presentation.component.sheet;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;

import at.easydiet.teamb.application.handler.UseCaseManager;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.domain.util.PlanTypeEnum;

/**
 * The Class NewDietPlanContentBorder.
 */
public class NewDietPlanContentBorder extends Border implements Bindable {

	@BXML
	private TextInput _name;
	@BXML
	private ListButton _treatment;
	@BXML
	private ListButton _type;

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		_treatment.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof DietTreatmentViewable) {
					DietTreatmentViewable dietTreatmentViewable = (DietTreatmentViewable) data;
					super.render(dietTreatmentViewable.getName(), button, highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_treatment.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof DietTreatmentViewable) {
					DietTreatmentViewable dietTreatmentViewable = (DietTreatmentViewable) item;
					super.render(dietTreatmentViewable.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}
		});

		PlanTypeEnum[] planTypes = PlanTypeEnum.values();
		_type.setListData(new ArrayList<PlanTypeEnum>(planTypes, 0, planTypes.length));
		_type.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof PlanTypeEnum) {
					PlanTypeEnum planTypeEnum = (PlanTypeEnum) data;
					super.render(planTypeEnum.getName(), button, highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_type.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof PlanTypeEnum) {
					PlanTypeEnum planTypeEnum = (PlanTypeEnum) item;
					super.render(planTypeEnum.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}
		});
	}

	/**
	 * Reset the DietPlan.
	 */
	public void reset() {
		_treatment.setSelectedIndex(-1);

		DietTreatmentViewable[] treatmentsSet = UseCaseManager.getWindowHandler().getSelectedPatient().getTreatments();
		_treatment.setListData(new ArrayList<DietTreatmentViewable>(treatmentsSet, 0, treatmentsSet.length));

		_type.setSelectedIndex(-1);
	}

	/**
	 * Gets the diet plan name.
	 *
	 * @return the diet plan name
	 */
	public String getDietPlanName() {
		return _name.getText();
	}

	/**
	 * Gets the diet treatment.
	 *
	 * @return the diet treatment
	 */
	public DietTreatmentViewable getDietTreatment() {
		Object selected = _treatment.getSelectedItem();

		if (selected instanceof DietTreatmentViewable) {
			return (DietTreatmentViewable) selected;
		} else {
			return null;
		}
	}

	/**
	 * Gets the diet plan type.
	 *
	 * @return the diet plan type
	 */
	public PlanTypeEnum getDietPlanType() {
		Object selected = _type.getSelectedItem();

		if (selected instanceof PlanTypeEnum) {
			return (PlanTypeEnum) selected;
		} else {
			return null;
		}
	}

}
