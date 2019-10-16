package project.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.domain.Content;
import project.store.ContentStore;
import project.store.jdbc.ConnectionFactoryForAWS;
import project.store.jdbc.JdbcUtils;

@Repository
public class ContentStoreLogic implements ContentStore {
private ConnectionFactoryForAWS connectionFactory;
	
	public ContentStoreLogic() {
	
		connectionFactory = ConnectionFactoryForAWS.getInstance();
	}
	
	public void create(Content content) {
        
    	String sql = "INSERT INTO CONTENTTB(CONTENT_ID, CATEGORY, CONTENT_TITLE, ID_ID, CONTENT_TEXT, DATE_CREATE, VIEW_COUNT, VOTE_COUNT) VALUES (CONTENT_SEQ.NEXTVAL,?,?,?,?,SYSDATE,?,?)" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, content.getCategory());
			psmt.setString(2, content.getTitle());
			psmt.setString(3, content.getCreatorName());
			psmt.setString(4, content.getContentText());
			psmt.setString(5,content.getViewCount());
			psmt.setString(6, content.getVoteCount());
			psmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}
		System.out.println("생성");
    }
	
	public void delete(String contentId) {
        //  	
    	String sql = "DELETE FROM CONTENTTB WHERE CONTENT_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, contentId);
			psmt.executeUpdate();
			
			System.out.println("DB삭제 완료!  ID:"+contentId);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
		
		retrieveAll();
    }
	
	public void update(Content content) {
        //
    	
    	String sql = "UPDATE CONTENTTB SET CONTENT_TITLE=?, CONTENT_TEXT=?, CATEGORY=? WHERE CONTENT_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, content.getTitle());
			psmt.setString(2, content.getContentText());
			psmt.setString(3, content.getCategory());
			psmt.setString(4, content.getContentId());
			psmt.executeUpdate();
			
			System.out.println("CONTENT수정완료!");
			System.out.println(content.getCategory());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
		
		retrieveAll();

    }
	
	
	public Content retrieve(String contentId) {
        //
		System.out.println("찾기");
    	String sql = "SELECT CONTENT_ID, CONTENT_TITLE, CONTENT_TEXT, DATE_CREATE, VIEW_COUNT, ID_ID, CATEGORY, VOTE_COUNT FROM CONTENTTB WHERE CONTENT_ID=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Content content =null;

		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, contentId);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				content = new Content();
				content.setContentId(rs.getString(1));
				content.setTitle(rs.getString(2));
				content.setContentText(rs.getString(3));
				content.setDateCreate(rs.getDate(4));
				content.setViewCount(rs.getString(5));
				content.setCreatorName(rs.getString(6));
				content.setCategory(rs.getString(7));
				content.setVoteCount(rs.getString(8));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}

		
		return content;
    }
	
	
	public List<Content> retrieveAll() {
		// 
		System.out.println("모두찾기");
		String sql = "SELECT CONTENT_ID, CONTENT_TITLE ,CONTENT_TEXT ,DATE_CREATE, VIEW_COUNT, ID_ID, CATEGORY, VOTE_COUNT FROM CONTENTTB ORDER BY CONTENT_ID DESC";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Content> contents = new ArrayList<Content>();
		
		try {
			conn = connectionFactory.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				
				Content content = new Content();
				content.setContentId(rs.getString(1));
				content.setTitle(rs.getString(2));
				content.setContentText(rs.getString(3));
				content.setDateCreate(rs.getDate(4));
				content.setViewCount(rs.getString(5));
				content.setCreatorName(rs.getString(6));
				content.setVoteCount(rs.getString(7));
				if(rs.getString("CATEGORY").equals("A"))
				{
					content.setCategory("여행 후기 & 팁");
				}
				else if(rs.getString("CATEGORY").equals("B"))
				{
					content.setCategory("여행지 Q&A");

				}
				else
				{
					content.setCategory("자유게시판");
				}
				contents.add(content);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(stmt, conn, rs);
		}

		
		return contents;
	}

	//메인페이지에 띄어주기 위한 게시글
	@Override
	public List<Content> toIndexContents() {
		// TODO Auto-generated method stub
		String sql = "SELECT CONTENT_ID, CONTENT_TITLE ,CONTENT_TEXT ,DATE_CREATE, VIEW_COUNT, ID_ID FROM CONTENTTB WHERE CATEGORY='A'";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Content> contents = new ArrayList<Content>();
		
		try {
			conn = connectionFactory.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				
				Content content = new Content();
				content.setContentId(rs.getString(1));
				content.setTitle(rs.getString(2));
				content.setContentText(rs.getString(3));
				content.setDateCreate(rs.getDate(4));
				content.setViewCount(rs.getString(5));
				content.setCreatorName(rs.getString(6));
				contents.add(content);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(stmt, conn, rs);
		}

		return contents;
	}

	@Override
	public void addVote(String contentID) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE CONTENTTB SET VOTE_COUNT=VOTE_COUNT+1 WHERE CONTENT_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, contentID);
			psmt.execute();
			
			System.out.println("스토어측: 게시글"+contentID+"번 좋아요 로직 동작!");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}
		
	}

	@Override
	public List<Content> retrieveCategory(String categoryID) {
		// TODO Auto-generated method stub
		
		System.out.println("게시판 카테고리별 찾기");
		String sql = "SELECT CONTENT_ID, CONTENT_TITLE ,CONTENT_TEXT ,DATE_CREATE, VIEW_COUNT, ID_ID, CATEGORY, VOTE_COUNT FROM CONTENTTB WHERE CATEGORY=? ORDER BY DATE_CREATE DESC";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Content> contents = new ArrayList<Content>();
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, categoryID);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				
				Content content = new Content();
				content.setContentId(rs.getString(1));
				content.setTitle(rs.getString(2));
				content.setContentText(rs.getString(3));
				content.setDateCreate(rs.getDate(4));
				content.setViewCount(rs.getString(5));
				content.setCreatorName(rs.getString(6));
				content.setCategory(rs.getString(7));
				content.setVoteCount(rs.getString(8));
				contents.add(content);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}

		return contents;
	}

	@Override
	public List<Content> retrieveMyID(String loginID) {
		// TODO Auto-generated method stub

		System.out.println("내가 쓴 게시글 찾기");
		String sql = "SELECT CONTENT_ID, CONTENT_TITLE ,CONTENT_TEXT ,DATE_CREATE, VIEW_COUNT, ID_ID, CATEGORY, VOTE_COUNT FROM CONTENTTB WHERE ID_ID=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Content> contents = new ArrayList<Content>();
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, loginID);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				
				Content content = new Content();
				content.setContentId(rs.getString(1));
				content.setTitle(rs.getString(2));
				content.setContentText(rs.getString(3));
				content.setDateCreate(rs.getDate(4));
				content.setViewCount(rs.getString(5));
				content.setCreatorName(rs.getString(6));
				content.setCategory(rs.getString(7));
				content.setVoteCount(rs.getString(8));
				contents.add(content);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}

		return contents;
	}

	@Override
	public void updateViewCount(Content content) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE CONTENTTB SET VIEW_COUNT=? WHERE CONTENT_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, content.getViewCount());
			psmt.setString(2, content.getContentId());
			psmt.executeUpdate();
			
			System.out.println("ViewCount수정완료!");
			System.out.println(content.getCategory());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
		
		retrieveAll();

		
	}
}
