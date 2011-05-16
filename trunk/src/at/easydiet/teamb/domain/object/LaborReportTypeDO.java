/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain.object;

import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.ILaborReportType;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ListConverter;
import at.easydiet.model.LaborReportType;
import at.easydiet.model.ParameterDefinition;

/**
 * The Class LaborReportTypeDO.
 */
public class LaborReportTypeDO extends AbstractDO<LaborReportType> implements ILaborReportType{
	private LaborReportType _type;
	

	/**
	 * Instantiates a new labor report type do.
	 *
	 * @param type the type
	 */
	public LaborReportTypeDO(LaborReportType type){
		_type = type;
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReportTYpe#getName()
	 */
	@Override
	public String getName() {
		return _type.getName();
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReportTYpe#setName(java.lang.String)
	 */
	@Override
	public void setName(String Name) {
		_type.setName(Name);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborReportTYpe#getParameterDefinitions()
	 */
	public ParameterDefinitionViewable[] getParameterDefinitions() {
		return ListConverter.toDOArray(_type.getParameterDefinitions(), new ListConverter.ModelToDO<ParameterDefinitionDO, ParameterDefinition>() {
			@Override
			public ParameterDefinitionDO convert(ParameterDefinition model) {
				return new ParameterDefinitionDO(model);
			}
		}).toArray(new ParameterDefinitionViewable[0]);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.ILaborReportType#addParameterDefintion(at.easydiet.application.viewobject.ParameterDefinitionViewable)
	 */
	public void addParameterDefintion(ParameterDefinitionViewable def){
		_type.getParameterDefinitions().add(((ParameterDefinitionDO)def).getModel());
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.ILaborReportType#removeParameterDefintion(at.easydiet.application.viewobject.ParameterDefinitionViewable)
	 */
	public void removeParameterDefintion(ParameterDefinitionViewable def){
		_type.getParameterDefinitions().remove(((ParameterDefinitionDO)def).getModel());
	}

	@Override
	public LaborReportType getModel() {
		return _type;
	}
}
