package at.easydiet.teamc.web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CalendarBean{

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
}
