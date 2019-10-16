package project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import project.domain.InfoFile;

public interface InfoFileService {

	void uploadInfoFile(List<MultipartFile> multipartFiles,String infoId);
	void deleteInfoFile(String infoId);
	List<InfoFile> findallInfoFiles(String infoId);
	
}
