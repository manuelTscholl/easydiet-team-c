//package at.easydiet.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import at.easydiet.application.viewobject.LaborReportTypeViewable;
//import at.easydiet.domain.object.LaborReportTypeDO;
//import at.easydiet.model.LaborReportType;
//
///**
// * A DAO implementation for LaborReportType objects.
// */
//public class LaborReportTypeDAO 
//        extends GenericHibernateDAO<LaborReportType, Long>
//{
//	/**
//	 * Gets all Laborreport Types from the database
//	 * @return List<DietParameterType>
//	 */
//	public LaborReportTypeViewable[] getParameterTypes(){
//		List<LaborReportType> types = findAll();
//		
//		List<LaborReportTypeViewable> typesDO = new ArrayList<LaborReportTypeViewable>(types.size());
//		for (LaborReportType dietParameter : types) {
//			typesDO.add(new LaborReportTypeDO(dietParameter));
//		}
//		
//		return typesDO.toArray(new LaborReportTypeViewable[0]);
//	}
//}