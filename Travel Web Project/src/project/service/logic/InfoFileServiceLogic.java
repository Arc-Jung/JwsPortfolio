package project.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.domain.InfoFile;
import project.service.InfoFileService;
import project.store.InfoFileStore;
import project.store.jdbc.S3Util;

@Service
public class InfoFileServiceLogic implements InfoFileService {

	@Autowired
	private InfoFileStore infoFileStore;

	@Override
	public void uploadInfoFile(List<MultipartFile> multipartFiles, String infoId) {
		for (MultipartFile multipartFile : multipartFiles) {
			InfoFile infoFile = new InfoFile();
			try {
				String uploadpath = "image";
				String savePath = S3Util.uploadFile(uploadpath, multipartFile.getOriginalFilename(),
						multipartFile.getBytes());

				infoFile.setFilePath(savePath);
				infoFile.setTravelInfoId(infoId);

				infoFileStore.create(infoFile);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void deleteInfoFile(String infoId) {
		List<InfoFile> files= infoFileStore.retrieveAll(infoId);
		for(InfoFile file: files) {
			try {
			S3Util.deleteFile(file.getFilePath());
			infoFileStore.delete(file.getFileId());
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}


	}

	@Override
	public List<InfoFile> findallInfoFiles(String infoId) {
		return infoFileStore.retrieveAll(infoId);
	}

}
