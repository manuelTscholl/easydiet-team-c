package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.model.NutritionProtocol;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.TimeSpanBo;

@ManagedBean
@SessionScoped
public class NutrimentProtocolBean extends NutritionProtocolBo{

	private Date _startDate;
	private Date _endDate;
	
	
    /**
     * Gets the startDate.
     * @return the startDate
     */  
    public Date getStartDate()
    {
        return _startDate;
    }
    
    /**
     * Sets the startDate.
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate)
    {
        _startDate = startDate;
    }
    /**
     * Gets the endDate.
     * @return the endDate
     */
    public Date getEndDate()
    {
        return _endDate;
    }
    /**
     * Sets the endDate.
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate)
    {
        _endDate = endDate;
    }
    

    public List<TimeSpanBo> getTimespans()
    {
        List<TimeSpanBo> bos = new ArrayList<TimeSpanBo>();
        
        if(getTimeSpans()!=null)
        for (TimeSpanBo timeSpanBo : getTimeSpans())
        {
            bos.add(timeSpanBo);
        }
        return bos;
    }
}