package project.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import project.domain.User;
import project.store.UserStore;
import project.store.jdbc.ConnectionFactoryForAWS;
import project.store.jdbc.JdbcUtils;

@Repository
public class UserStoreLogic implements UserStore{

	private ConnectionFactoryForAWS aws;
	public UserStoreLogic()
	{
		aws=ConnectionFactoryForAWS.getInstance();
		System.out.println("스토어측: AWS서버 연결성공!!");
	}


	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub

		String tel = user.getUserTel();

		System.out.println("id: "+user.getUserID());
		System.out.println("pw: "+user.getUserPW());
		System.out.println("email: "+user.getUserMail());
		System.out.println("name: "+user.getUserName());
		System.out.println("tel: "+user.getUserTel());
		System.out.println("nname: "+user.getUserNName());




		String sql="INSERT INTO USERTB(ID_NUMBER, PASSWORD_ID, EMAIL_ID, ID_ID, DATE_CREATE, DEL_ID, USER_NAME, NICK_NAME, TEL_ID)"
				+ "VALUES (USERID_SEQ.NEXTVAL,?,?,?,SYSDATE,0,?,?,?)";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = aws.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, user.getUserPW());
			psmt.setString(2, user.getUserMail());
			psmt.setString(3, user.getUserID());
			psmt.setString(4, user.getUserName());
			psmt.setString(5, user.getUserNName());
			psmt.setString(6, user.getUserTel());
			psmt.executeUpdate();
			
			System.out.println("스토어측: DB 계정삽입 완료!!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}


	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

		String sql="UPDATE USERTB SET PASSWORD_ID=?, EMAIL_ID=?, USER_NAME=?, NICK_NAME=?, TEL_ID=?"
				+ " WHERE ID_ID=?";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = aws.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, user.getUserPW());
			psmt.setString(2, user.getUserMail());
			psmt.setString(3, user.getUserName());
			psmt.setString(4, user.getUserNName());
			psmt.setString(5, user.getUserTel());
			psmt.setString(6, user.getUserID());
			
			psmt.executeUpdate();
			
			System.out.println("스토어측: 계정 정보변경 완료!!");

		} catch (SQLException e) {
			System.out.println("스토어측: 계정 정보변경 중 오류 발생!!");
			throw new RuntimeException(e);
		} finally {		
			JdbcUtils.close(psmt, conn);
		}

	}


	@Override
	public User loginUser(String userID, String userPW) {
		// TODO Auto-generated method stub
		
		 User user = new User();
		
		
		 Connection conn = null;
	     PreparedStatement psmt = null;
	     ResultSet rs = null;
	         
	     //String sql = "SELECT PASSWORD_ID, EMAIL_ID, DATE_CREATE, POINT_ID, USER_NAME, NICK_NAME, TEL_ID, ID_NUMBER FROM USERTB WHERE ID_ID=?";
	     String sql = "SELECT PASSWORD_ID, EMAIL_ID, DATE_CREATE, USER_NAME, NICK_NAME, TEL_ID, ID_NUMBER, ADMIN_ID FROM USERTB WHERE ID_ID=? AND DEL_ID=0";    
	     try {
	            
	            conn = aws.createConnection();
	            psmt = conn.prepareStatement(sql);
	            psmt.setString(1, userID);    
	            rs = psmt.executeQuery();
	            
	            if(rs.next())
	            {
	            	 if (userPW.equals(rs.getString("PASSWORD_ID")))
		            	{
		            		user.setUserMail(rs.getString("EMAIL_ID"));
		            		user.setUserName(rs.getString("USER_NAME"));
		            		user.setUserNName(rs.getString("NICK_NAME"));
		            		user.setUserTel(rs.getString("TEL_ID"));
		            		//포인트 받는 기능 미구현
		            		//user.setUserPoint(rs.getInt("POINT_ID"));
		            		user.setUserOrder(rs.getInt("ID_NUMBER"));
		            		user.setAdmin(rs.getInt("ADMIN_ID"));
		            		
		            		user.setLogined(true);
		            	}
		            else 
		            	{
		            		System.out.println("DB오류");
		            	}
	            }
	            else
	            {
	            	System.out.println("로그인정보 없음!");
	            }
	         } 
	     catch (SQLException e) {
	          System.out.println("로그인 오류발생!");
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	     }
	     
	     finally
	     {   	
	    	 System.out.println("로그인 성공!!");
			 JdbcUtils.close(psmt, conn);
			 
	     }
	     
	     return user;
	}


	@Override
	public boolean findUserPW(String userID, String userName, String userMail) {
		// TODO Auto-generated method stub
		 String password = null;
		
		
		 Connection conn = null;
	     PreparedStatement psmt = null;
	     ResultSet rs = null;
	         
	     String sql = "SELECT PASSWORD_ID FROM USERTB WHERE ID_ID=? AND EMAIL_ID=? AND USER_NAME=?";
	     
 
	     try {
	            
	            conn = aws.createConnection();
	            psmt = conn.prepareStatement(sql);
	            psmt.setString(1, userID);
	            psmt.setString(2, userMail);
	            psmt.setString(3, userName);
	            rs = psmt.executeQuery();
	            
	            if(rs.next())
	            {
	            	System.out.println("스토어측: 맞는 PW찾음!!");
		            password = (rs.getString("PASSWORD_ID"));
		            
		            return true; 

		        }
	            else 
	            { 
	            	System.out.println("스토어측: 알맞는 DB에 유저 데이터 없음");
	            	
	            	return false;
	            }
	         }
	     
	     catch (SQLException e) {
	          System.out.println("로그인 오류발생!");
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	     }
	     
	     finally
	     {   	
			 JdbcUtils.close(psmt, conn);
			 
	     }
	     return false;
	}


	@Override
	public void deleteUser(String userID, String userPW) {
		// TODO Auto-generated method stub
	
		 Connection conn = null;
	     PreparedStatement psmt = null;
	     
	     //String sql = "DELETE FROM USERTB WHERE ID_ID=? AND PASSWORD_ID=?"; 
	     String sql = "UPDATE USERTB SET DEL_ID=1 WHERE ID_ID=? AND PASSWORD_ID=?";
	    		 
	     try {
	            conn = aws.createConnection();
	            psmt = conn.prepareStatement(sql);
	            psmt.setString(1, userID);
	            psmt.setString(2, userPW);
	            psmt.executeUpdate();
	            System.out.println("탈퇴 성공!!");
	     }
	            
	     catch (SQLException e) {
	          System.out.println("탈퇴 오류발생!");
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	     }
	     
	     finally
	     {   	
			 JdbcUtils.close(psmt, conn);			 
	     }

	}
	
	
	@Override
	public void changePW(String userID, String pwd) {
		// TODO Auto-generated method stub
		String sql="UPDATE USERTB SET PASSWORD_ID=?"
				+ " WHERE ID_ID=?";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = aws.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, pwd);
			psmt.setString(2, userID);
			psmt.executeUpdate();
			
			System.out.println("스토어측: 암호변경 완료!!");

		} catch (SQLException e) {
			System.out.println("스토어측: 계정 정보변경 중 오류 발생!!");
			throw new RuntimeException(e);
		} finally {		
			JdbcUtils.close(psmt, conn);
		}

	}
}

