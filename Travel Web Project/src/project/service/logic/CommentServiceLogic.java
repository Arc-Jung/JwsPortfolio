package project.service.logic;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Comment;
import project.service.CommentService;
import project.store.CommentStore;

@Service
public class CommentServiceLogic implements CommentService{

	@Autowired
	private CommentStore commentStore;
	
	@Override
	public void registerComment(Comment comment) {
		// TODO Auto-generated method stub
		Date today = new Date(Calendar.getInstance().getTimeInMillis());
		comment.setCreateDate(today);
		commentStore.create(comment);
	}

	@Override
	public void removeComment(String commentId) {
		// TODO Auto-generated method stub
		commentStore.delete(commentId);
	}

	@Override
	public List<Comment> findallComments(String contentId) {
		// TODO Auto-generated method stub
		return commentStore.retrieveAll(contentId);
	}

}
