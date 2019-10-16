package project.service.logic;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Content;
import project.service.ContentService;
import project.store.ContentStore;

@Service
public class ContentServiceLogic implements ContentService{
	
	@Autowired
	private ContentStore contentStore;
	
	
	@Override
	public void registerContent(Content content) {
		Date today = new Date(Calendar.getInstance().getTimeInMillis());
		content.setDateCreate(today);
		content.setViewCount("0");
		content.setVoteCount("0");
		contentStore.create(content);
	}

	@Override
	public Content findContent(String contentId) {
		
		return contentStore.retrieve(contentId);
	}

	@Override
	public void removeContent(String contentId) {
		contentStore.delete(contentId);
	}

	@Override
	public void modifyContent(Content content) {
		contentStore.update(content);
	}

	@Override
	public List<Content> findallContents() {
		
		return contentStore.retrieveAll();
	}

	@Override
	public List<Content> toIndexContents() {
		// TODO Auto-generated method stub
		return contentStore.toIndexContents();
	}

	@Override
	public void addVote(String contentID) {
		// TODO Auto-generated method stub
		contentStore.addVote(contentID);
	}

	@Override
	public List<Content> findCategories(String categoryID) {
		// TODO Auto-generated method stub
		return contentStore.retrieveCategory(categoryID);
	}

	@Override
	public List<Content> findMyContents(String loginID) {
		// TODO Auto-generated method stub
		return contentStore.retrieveMyID(loginID);
	}

	@Override
	public void modifyViewCount(Content content) {
		// TODO Auto-generated method stub
		contentStore.updateViewCount(content);
	}
}
