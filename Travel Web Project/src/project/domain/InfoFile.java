package project.domain;

import java.io.Serializable;

public class InfoFile implements Serializable{

	private static final long serialVersionUID = -7755173628930202505L;
	
	private String fileId;
	private String filePath;
	private String travelInfoId;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTravelInfoId() {
		return travelInfoId;
	}
	public void setTravelInfoId(String travelInfoId) {
		this.travelInfoId = travelInfoId;
	}
	
	
}
