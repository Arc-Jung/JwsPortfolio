package project.service;

import java.util.List;

import project.domain.Comment;

public interface CommentService {

	void registerComment(Comment comment);
	void removeComment (String commentId);
	List<Comment> findallComments(String contentId);
	
	
}
