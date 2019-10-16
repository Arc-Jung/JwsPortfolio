package project.domain;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable {

    private static final long serialVersionUID = -4946269376442479040L;
    
    private String userID;
    private String planID;
    
    private String location;
    private String date;
    private String startTime;
    private String endTime;
    private String vehicle;
    
    private String longitude;
    private String latitude;
    
    private int no;
    
    
    //Get硫붿냼�뱶 
    public String getUserID()
    {
    	return userID;
    }
    
    public String getPlanID()
    {
    	return planID;
    }
     
    public String getLocation()
    {
    	return location;
    }
    
    public String getDate()
    {
    	return date;
    }
    
    public String getStartTime()
    {
    	return startTime;
    }
    
    public String getEndTime()
    {
    	return endTime;
    }
    
    public String getVehicle()
    {
    	return vehicle;
    }
    
    public String getLongitude()
    {
    	return longitude;
    }
    
    public String getLatitude()
    {
    	return latitude;
    }
    
    public int getNo()
    {
    	return no;
    }
    
    // Set硫붿냼�뱶 
    public void setUserID(String userID)
    {
    	this.userID = userID;
    }
   
    
    public void setLocation(String location)
    {
    	this.location = location;
    }
    
    public void setDate(String date)
    {
    	this.date = date;
    }
    
    public void setStartTime(String startTime)
    {
    	this.startTime=startTime;
    }
    
    public void setEndTime(String endTime)
    {
    	this.endTime = endTime;
    }
    
    public void setVehicle(String vehicle)
    {
    	this.vehicle = vehicle;
    }
    
    public void setPlanID(String planID)
    {
    	this.planID = planID;
    }
    
    public void setLongitude(String longitude)
    {
    	this.longitude = longitude;
    }
    
    public void setLatitude(String latitude)
    {
    	this.latitude = latitude;
    }
    
    public void setNo(int no)
    {
    	this.no = no;
    }
    
}
