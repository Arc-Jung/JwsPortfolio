package project.store;

import java.util.List;

import project.domain.InfoFile;

public interface InfoFileStore {
	void create(InfoFile infoFile);
	void delete(String fileId);
	List<InfoFile> retrieveAll(String infoId);
}
