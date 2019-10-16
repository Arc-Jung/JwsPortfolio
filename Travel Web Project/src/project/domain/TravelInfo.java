package project.domain;

import java.io.Serializable;

import java.util.List;

public class TravelInfo implements Serializable{
	private static final long serialVersionUID = -7755173628930202505L;

	private String infoId;
	private String infoName;
	private String infoText;
	private String voteCount;
	private String location;
	private String thumbImg;
	
	private String longitude;
	private String latitude;
	
	private String locationID;
	
	
	private List<InfoFile> files;
	
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getInfoText() {
		return infoText;
	}
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
	public String getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}
	public String getThumbImg() {
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	public List<InfoFile> getFiles() {
		return files;
	}
	public void setFiles(List<InfoFile> files) {
		this.files = files;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	//지도 좌표값 처리부분
	public String getLongitude()
	{
		return longitude;
	}
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getLocationID()
	{
		return locationID;
	}
	public void setLocationID(String locationID)
	{
		this.locationID = locationID;
	}
}
