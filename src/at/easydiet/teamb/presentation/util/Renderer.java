/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	26.04.2011
 */

package at.easydiet.teamb.presentation.util;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;

import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;

/**
 * The Class Renderer.
 */
public class Renderer {
	private Renderer() {}
	
	/**
	 * The Class ParameterListButtonDataRenderer.
	 */
	public static class ParameterListButtonDataRenderer extends ListButtonDataRenderer {
		@Override
		public void render(Object data, Button button, boolean highlight) {
			if (data instanceof CheckOperatorEnum) {
				CheckOperatorEnum checkOperatorEnum = (CheckOperatorEnum) data;
				super.render(checkOperatorEnum.getName(), button, highlight);
			} else {
				super.render(data, button, highlight);
			}
		}
	}
	
	/**
	 * The Class ParameterListViewItemRenderer.
	 */
	public static class ParameterListViewItemRenderer extends ListViewItemRenderer {
		@Override
		public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
			if (item instanceof CheckOperatorEnum) {
				CheckOperatorEnum checkOperatorEnum = (CheckOperatorEnum) item;
				super.render(checkOperatorEnum.getName(), index, listView, selected, checked, highlighted, disabled);
			} else {
				super.render(item, index, listView, selected, checked, highlighted, disabled);
			}
		}
	}
	
	/**
	 * The Class UnitListButtonDataRenderer.
	 */
	public static class UnitListButtonDataRenderer extends ListButtonDataRenderer {
		@Override
		public void render(Object data, Button button, boolean highlight) {
			if (data instanceof ParameterDefinitionUnitViewable) {
				ParameterDefinitionUnitViewable parameterDefinitionUnitViewable = (ParameterDefinitionUnitViewable) data;
				super.render(parameterDefinitionUnitViewable.getName(), button, highlight);
			} else {
				super.render(data, button, highlight);
			}
		}
	}
	
	/**
	 * The Class UnitListViewItemRenderer.
	 */
	public static class UnitListViewItemRenderer extends ListViewItemRenderer {
		@Override
		public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
			if (item instanceof ParameterDefinitionUnitViewable) {
				ParameterDefinitionUnitViewable parameterDefinitionUnitViewable = (ParameterDefinitionUnitViewable) item;
				super.render(parameterDefinitionUnitViewable.getName(), index, listView, selected, checked, highlighted, disabled);
			} else {
				super.render(item, index, listView, selected, checked, highlighted, disabled);
			}
		}
	}
}
