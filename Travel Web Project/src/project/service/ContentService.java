package project.service;

import java.util.List;

import project.domain.Content;

public interface ContentService {

	void registerContent(Content content);
	
	Content findContent(String contentId);
	
	void removeContent(String contentId);
	
	void modifyContent(Content content);
	
	List<Content> findallContents();
	
	List<Content> findCategories(String categoryID);
	
	List<Content> findMyContents(String loginID);
	
	List<Content> toIndexContents();
	
	void addVote(String contentID);
	
	void modifyViewCount(Content content);
	
}