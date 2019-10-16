package project.domain;

import java.io.File;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileList implements Serializable{
	
	
	private String fileName;
	private String fileSize;
	private String fileType;
	private String uploadFilePath;
	private String fileLink;
	private String orgname;
	private String newname;
	private File Filedata;//파일로 보낼 때
	private MultipartFile multifile;//멀티파트파일로 보낼때
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	public String getFileLink() {
		return fileLink;
	}
	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public File getFiledata() {
		return Filedata;
	}
	public void setFiledata(File filedata) {
		Filedata = filedata;
	}
	public MultipartFile getMultifile() {
		return multifile;
	}
	public void setMultifile(MultipartFile multifile) {
		this.multifile = multifile;
	}
	
	

	
	
	
}
