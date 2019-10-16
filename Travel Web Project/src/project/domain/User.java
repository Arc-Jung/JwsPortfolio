package project.domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -4946269376442479040L;
    
    private String userID;
    private String userPW;
    private String userName;
    private String userNName;
    private String userTel;
    private String userMail;
    private int userOrder;
    private boolean logined = false;
    private int isAdmin = 0;
    
    //get�޼ҵ�
    public String getUserID()
    {
    	return userID;
    }
    
    public String getUserPW()
    {
    	return userPW;
    }
    
    public String getUserName()
    {
    	return userName;
    }
    
    public String getUserNName()
    {
    	return userNName;
    }
    
    public String getUserTel()
    {
    	return userTel;
    }
    
    public String getUserMail()
    {
    	return userMail;
    }
    
    public boolean getLogined()
    {
    	return logined;
    }
    
    public int getUserOrder()
    {
    	return userOrder;
    }
    
    public int getAdmin()
    {
    	return isAdmin;
    }

    
    //set�޼ҵ�
    public void setUserID(String userID)
    {
    	this.userID = userID;
    }
    
    public void setUserPW(String userPW)
    {
    	this.userPW = userPW;
    }
    
    public void setUserName(String userName)
    {
    	this.userName = userName;
    }
    
    public void setUserNName(String userNName)
    {
    	this.userNName = userNName;
    }
    
    public void setUserTel(String userTel)
    {
    	this.userTel = userTel;
    }
    
    public void setUserMail(String userMail)
    {
    	this.userMail = userMail;
    }

    public void setLogined(boolean logined)
    {
    	this.logined = logined;
    }
    
    public void setUserOrder(int userOrder)
    {
    	this.userOrder = userOrder;
    }
    
    public void setAdmin(int isAdmin)
    {
    	this.isAdmin = isAdmin;
    }

}
