package project.service;

import java.io.File;

import project.domain.FileList;

public interface FileService {
	
	public void uploadFile(File file) throws Exception;
	public void downloadFile(File file) throws Exception;
	public File uploadFile(FileList fileList) throws Exception;
	
	
	

}
