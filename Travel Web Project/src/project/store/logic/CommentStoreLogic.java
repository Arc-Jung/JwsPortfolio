package project.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.domain.Comment;
import project.store.CommentStore;
import project.store.jdbc.ConnectionFactoryForAWS;
import project.store.jdbc.JdbcUtils;

@Repository
public class CommentStoreLogic implements CommentStore{

	private ConnectionFactoryForAWS connectionFactory;
	
	public CommentStoreLogic() {
	
		connectionFactory = ConnectionFactoryForAWS.getInstance();
	}
	
	
	
	@Override
	public void create(Comment comment) {
		  
    	String sql = "INSERT INTO COMMENTTB(COMMENT_NUMBER, CONTENT_ID, ID_ID, COMMENT_TEXT, DATE_CREATE) VALUES (COMMENT_SEQ.NEXTVAL,?,?,?,SYSDATE)" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, comment.getContentId());
			psmt.setString(2,comment.getCreatorName() );
			psmt.setString(3,comment.getCommentText() );
			psmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}
		
	}


	@Override
	public void delete(String commentId) {
		String sql = "DELETE FROM COMMENTTB WHERE COMMENT_NUMBER = ?" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, commentId);
			psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
	}

	@Override
	public List<Comment> retrieveAll(String contentId) {
		// 
				System.out.println("댓글모두찾기");
				String sql = "SELECT COMMENT_NUMBER, COMMENT_TEXT, ID_ID, CONTENT_ID FROM COMMENTTB  WHERE CONTENT_ID = ?";
				
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				List<Comment> comments = new ArrayList<Comment>();
				
				try {
					conn = connectionFactory.createConnection();
					psmt = conn.prepareStatement(sql.toString());
					psmt.setString(1, contentId);
					rs = psmt.executeQuery();
					
					while(rs.next())
					{
						Comment comment = new Comment();
						comment.setCommentId(rs.getString(1));
						comment.setCommentText(rs.getString(2));
						comment.setCreatorName(rs.getString(3));
						comment.setContentId(rs.getString(4));
						comments.add(comment);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					JdbcUtils.close(psmt, conn, rs);
				}

				
				return comments;
	}

	
	
}
