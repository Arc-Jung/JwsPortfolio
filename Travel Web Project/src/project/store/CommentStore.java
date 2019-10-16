package project.store;

import java.util.List;

import project.domain.Comment;

public interface CommentStore {
	void create(Comment comment);
	void delete(String commentId);
	List<Comment> retrieveAll(String contentId);
}
