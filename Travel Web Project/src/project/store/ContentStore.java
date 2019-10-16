package project.store;

import java.util.List;

import project.domain.Content;

public interface ContentStore {
	void create(Content content);
	Content retrieve(String contentId);
	void update(Content content);
	void delete(String contentId);
	List<Content> retrieveAll();
	List<Content> retrieveCategory(String categoryID);
	List<Content> retrieveMyID(String loginID);
	void updateViewCount(Content content);
	
	List<Content> toIndexContents();
	void addVote(String contentID);
}
