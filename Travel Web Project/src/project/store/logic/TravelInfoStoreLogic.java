package project.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import project.Pagination;
import project.domain.TravelInfo;
import project.store.TravelInfoStore;
import project.store.jdbc.ConnectionFactoryForAWS;
import project.store.jdbc.JdbcUtils;

@Repository
public class TravelInfoStoreLogic implements TravelInfoStore {

	private ConnectionFactoryForAWS connectionFactory;

	public TravelInfoStoreLogic() {

		connectionFactory = ConnectionFactoryForAWS.getInstance();
	}

	@Override
	public String create(TravelInfo travelInfo) {
		String sql = "INSERT INTO DESTINATION(DESTINATION_ID, DESTINATION_NAME, DESTINATION_TEXT, DESTINATION_VOTE,DESTINATION_LOCATION, DESTINATION_LONGITUDE, DESTINATION_LATITUDE) VALUES (INFO_SEQ.NEXTVAL,?,?,?,?,?,?)";
		String findsql="SELECT INFO_SEQ.CURRVAL FROM DUAL";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		Statement stmt=null;
		ResultSet rs=null;
		String infoId=null;
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, travelInfo.getInfoName());
			psmt.setString(2, travelInfo.getInfoText());
			psmt.setString(3, travelInfo.getVoteCount());
			psmt.setString(4, travelInfo.getLocation());
			psmt.setString(5, travelInfo.getLongitude());
			psmt.setString(6, travelInfo.getLatitude());
			psmt.execute();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(findsql);
			if(rs.next())
			{
				infoId=rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}
		return infoId;
	}

	@Override
	public TravelInfo retrieve(String infoId) {
		System.out.println("info찾기");
    	String sql = "SELECT DESTINATION_ID, DESTINATION_NAME , DESTINATION_TEXT , DESTINATION_VOTE, DESTINATION_LOCATION ,DESTINATION_IMG, DESTINATION_LONGITUDE, DESTINATION_LATITUDE FROM DESTINATION WHERE DESTINATION_ID=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		TravelInfo travelInfo = null;

		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, infoId);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				
				travelInfo = new TravelInfo();
				
				travelInfo.setInfoId(rs.getString(1));
				travelInfo.setInfoName(rs.getString(2));
				travelInfo.setInfoText(rs.getString(3));
				travelInfo.setVoteCount(rs.getString(4));
				travelInfo.setLocationID(rs.getString(5));
				travelInfo.setThumbImg(rs.getString(6));
				travelInfo.setLongitude(rs.getString(7));
				travelInfo.setLatitude(rs.getString(8));
				
				
				String location = rs.getString(5);
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}

		
		return travelInfo;
	}

	@Override
	public void update(TravelInfo travelInfo) {
		String sql = "UPDATE DESTINATION SET DESTINATION_NAME=?, DESTINATION_TEXT=?, DESTINATION_IMG=? WHERE DESTINATION_ID=?" ;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, travelInfo.getInfoName());
			psmt.setString(2, travelInfo.getInfoText());
			psmt.setString(3, travelInfo.getThumbImg());
			psmt.setString(4, travelInfo.getInfoId());
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
		

	}

	@Override
	public void delete(String infoId) {
		String sql = "DELETE FROM DESTINATION WHERE DESTINATION_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, infoId);
			psmt.executeUpdate();
			
			System.out.println("DB삭제 완료!  infoID:"+infoId);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}

	}

	@Override
	public List<TravelInfo> retrieveAll() {
		System.out.println("info모두찾기");
		String sql = "SELECT DESTINATION_ID, DESTINATION_NAME , DESTINATION_TEXT, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM DESTINATION";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();
		
		try {
			conn = connectionFactory.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString(1));
				travelInfo.setInfoName(rs.getString(2));
				travelInfo.setInfoText(rs.getString(3));
				travelInfo.setVoteCount(rs.getString(4));
				
				travelInfo.setThumbImg(rs.getString(6));
				
			
				String location = rs.getString(5);
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
				
				travelInfos.add(travelInfo);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(stmt, conn, rs);
		}

		
		return travelInfos;
	}

	@Override
	public List<TravelInfo> readByName(String name) {
		String sql = "SELECT DESTINATION_ID, DESTINATION_NAME , DESTINATION_TEXT, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM DESTINATION WHERE  DESTINATION_NAME LIKE ?" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, "%"+name+"%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString(1));
				travelInfo.setInfoName(rs.getString(2));
				travelInfo.setInfoText(rs.getString(3));
				travelInfo.setVoteCount(rs.getString(4));
				
				travelInfo.setThumbImg(rs.getString(6));
				
				
				String location = rs.getString(5);
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
				
				travelInfos.add(travelInfo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, psmt, conn);
		}
		return travelInfos;
	}

	@Override
	public List<TravelInfo> findToIndex() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT DESTINATION_ID, DESTINATION_NAME, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM DESTINATION WHERE ROWNUM <= 4 ORDER BY DESTINATION_VOTE DESC" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			rs = psmt.executeQuery();
			while (rs.next()) {
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
				travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
				travelInfo.setVoteCount(rs.getString("DESTINATION_VOTE"));

				travelInfo.setThumbImg(rs.getString("DESTINATION_IMG"));
				
				
				String location = rs.getString("DESTINATION_LOCATION");
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
				
				travelInfos.add(travelInfo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, psmt, conn);
		}
		return travelInfos;
	}

	@Override
	public void addVote(String infoID) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE DESTINATION SET DESTINATION_VOTE=DESTINATION_VOTE+1 WHERE DESTINATION_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, infoID);
			psmt.execute();
			
			System.out.println("스토어측: 게시글"+infoID+"번 좋아요 로직 동작!");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}
		
		
	}

	@Override
	public Map<String, List<TravelInfo>> findToPlanner() {
		// TODO Auto-generated method stub
		
		Map<String, List<TravelInfo>> selectMap = new HashMap<String, List<TravelInfo>>();
		
		String sql = "SELECT DESTINATION_ID, DESTINATION_NAME, DESTINATION_LOCATION, DESTINATION_LONGITUDE, DESTINATION_LATITUDE FROM DESTINATION WHERE DESTINATION_LOCATION=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		List<TravelInfo> travelInfosA = new ArrayList<TravelInfo>();
		List<TravelInfo> travelInfosB = new ArrayList<TravelInfo>();
		List<TravelInfo> travelInfosC = new ArrayList<TravelInfo>();
		List<TravelInfo> travelInfosD = new ArrayList<TravelInfo>();
		List<TravelInfo> travelInfosE = new ArrayList<TravelInfo>();
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			
			for(int i=0; i<4; i++)
			{
				if(i==0)
				{
					psmt.setString(1, "A");
					rs = psmt.executeQuery();
					
					while(rs.next())
					{
						TravelInfo travelInfo = new TravelInfo();
						travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
						travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
						travelInfo.setLocation(rs.getString("DESTINATION_LOCATION"));
						travelInfo.setLongitude(rs.getString("DESTINATION_LONGITUDE"));
						travelInfo.setLatitude(rs.getString("DESTINATION_LATITUDE"));
						travelInfosA.add(travelInfo);
						
						selectMap.put("A", travelInfosA);
					}
					
					System.out.println("A지역 전달완료!");
					psmt.clearParameters();
				}
				else if(i==1)
				{
					psmt.setString(1, "B");
					rs = psmt.executeQuery();
					
					while(rs.next())
					{
						
						TravelInfo travelInfo = new TravelInfo();
						travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
						travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
						travelInfo.setLocation(rs.getString("DESTINATION_LOCATION"));
						travelInfo.setLongitude(rs.getString("DESTINATION_LONGITUDE"));
						travelInfo.setLatitude(rs.getString("DESTINATION_LATITUDE"));
						travelInfosB.add(travelInfo);
						
						selectMap.put("B", travelInfosB);
					}
					
					System.out.println("B지역 전달완료!");
					psmt.clearParameters();
					
				}
				else if(i==2)
				{
					psmt.setString(1, "C");
					rs = psmt.executeQuery();
					
					while(rs.next())
					{
						
						TravelInfo travelInfo = new TravelInfo();
						travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
						travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
						travelInfo.setLocation(rs.getString("DESTINATION_LOCATION"));
						travelInfo.setLongitude(rs.getString("DESTINATION_LONGITUDE"));
						travelInfo.setLatitude(rs.getString("DESTINATION_LATITUDE"));
						travelInfosC.add(travelInfo);
						
						selectMap.put("C", travelInfosC);
					}
					
					System.out.println("C지역 전달완료!");
					psmt.clearParameters();
					
				}
				else if(i==3)
				{
					psmt.setString(1, "D");
					rs = psmt.executeQuery();
					
					while(rs.next())
					{
						
						TravelInfo travelInfo = new TravelInfo();
						travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
						travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
						travelInfo.setLocation(rs.getString("DESTINATION_LOCATION"));
						travelInfo.setLongitude(rs.getString("DESTINATION_LONGITUDE"));
						travelInfo.setLatitude(rs.getString("DESTINATION_LATITUDE"));
						travelInfosD.add(travelInfo);
						
						selectMap.put("D", travelInfosD);
						
					}
					
					System.out.println("D지역 전달완료!");
					psmt.clearParameters();
				}
				else if(i==4)
				{
					psmt.setString(1, "E");
					rs = psmt.executeQuery();
					
					while(rs.next())
					{
						
						TravelInfo travelInfo = new TravelInfo();
						travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
						travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
						travelInfo.setLocation(rs.getString("DESTINATION_LOCATION"));
						travelInfo.setLongitude(rs.getString("DESTINATION_LONGITUDE"));
						travelInfo.setLatitude(rs.getString("DESTINATION_LATITUDE"));
						travelInfosE.add(travelInfo);
						
						selectMap.put("E", travelInfosD);
						
					}
					
					System.out.println("E지역 전달완료!");
					psmt.clearParameters();
				}
				
			}
	
		} catch (SQLException e) {
			
			System.out.println("스토어측: 오류 발생!! \n"+e);
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}
		
		
		return selectMap;
	}
	@Override
	public List<TravelInfo> readByPage(Pagination pagination) {
		String sql = "SELECT A.DESTINATION_ID, A.DESTINATION_NAME , A.DESTINATION_TEXT, A.DESTINATION_VOTE, A.DESTINATION_LOCATION, A.DESTINATION_IMG "
				+ "FROM ( SELECT ROWNUM AS RNUM, B.DESTINATION_ID, B.DESTINATION_NAME , B.DESTINATION_TEXT, B.DESTINATION_VOTE, B.DESTINATION_LOCATION, B.DESTINATION_IMG "
				+ "FROM ( SELECT DESTINATION_ID, DESTINATION_NAME , DESTINATION_TEXT, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM DESTINATION ORDER BY DESTINATION_ID DESC) B "
				+ "WHERE ROWNUM <= ?) A WHERE A.RNUM > ?" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(2, String.valueOf(pagination.getStartList()));
			psmt.setString(1, String.valueOf(pagination.getEndList()));
			rs = psmt.executeQuery();
			while (rs.next()) {
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString(1));
				travelInfo.setInfoName(rs.getString(2));
				travelInfo.setInfoText(rs.getString(3));
				travelInfo.setVoteCount(rs.getString(4));
				
				travelInfo.setThumbImg(rs.getString(6));
				
				String location = rs.getString(5);
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}

				
				travelInfos.add(travelInfo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, psmt, conn);
		}
		return travelInfos;
	}

	@Override
	public List<TravelInfo> readByNamePage(Pagination pagination, String name) {
		String sql = "SELECT A.DESTINATION_ID, A.DESTINATION_NAME , A.DESTINATION_TEXT, A.DESTINATION_VOTE, A.DESTINATION_LOCATION, A.DESTINATION_IMG "
				+ "FROM ( SELECT ROWNUM AS RNUM, B.DESTINATION_ID, B.DESTINATION_NAME , B.DESTINATION_TEXT, B.DESTINATION_VOTE, B.DESTINATION_LOCATION, B.DESTINATION_IMG "
				+ "FROM ( SELECT DESTINATION_ID, DESTINATION_NAME , DESTINATION_TEXT, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM DESTINATION ORDER BY DESTINATION_ID DESC) B "
				+ "WHERE DESTINATION_NAME LIKE ? AND ROWNUM < ?) A WHERE DESTINATION_NAME LIKE ? AND A.RNUM >= ?" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, "%"+name+"%");
			psmt.setString(2, String.valueOf(pagination.getEndList()));
			psmt.setString(3, "%"+name+"%");
			psmt.setString(4, String.valueOf(pagination.getStartList()));
			rs = psmt.executeQuery();
			while (rs.next()) {
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString(1));
				travelInfo.setInfoName(rs.getString(2));
				travelInfo.setInfoText(rs.getString(3));
				travelInfo.setVoteCount(rs.getString(4));

				travelInfo.setThumbImg(rs.getString(6));
				
				String location = rs.getString(5);
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
				
				
				travelInfos.add(travelInfo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, psmt, conn);
		}
		return travelInfos;
	}

	@Override
	public List<TravelInfo> categoryRetrieveAll(String category) {
		// TODO Auto-generated method stub
		System.out.println("info카테고리찾기");
		String sql = "SELECT DESTINATION_ID, DESTINATION_NAME , DESTINATION_TEXT, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM DESTINATION WHERE DESTINATION_LOCATION=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();
		
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, category);
			
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString(1));
				travelInfo.setInfoName(rs.getString(2));
				travelInfo.setInfoText(rs.getString(3));
				travelInfo.setVoteCount(rs.getString(4));
				
				travelInfo.setThumbImg(rs.getString(6));
				
				travelInfo.setLocationID(rs.getString(5));
				String location = rs.getString(5);
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
				
				travelInfos.add(travelInfo);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}

		
		return travelInfos;
	}

	@Override
	public List<TravelInfo> findToLikeLocation(String location) {
		// TODO Auto-generated method stub
		String sql = "SELECT DESTINATION_ID, DESTINATION_NAME, DESTINATION_VOTE, DESTINATION_LOCATION, DESTINATION_IMG FROM (SELECT * FROM DESTINATION WHERE DESTINATION_LOCATION=?) WHERE ROWNUM <= 4 ORDER BY DESTINATION_VOTE DESC" ;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();
		
		System.out.println(location+"지역 요청받음");
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, location);
			rs = psmt.executeQuery();
			while (rs.next()) {
				TravelInfo travelInfo = new TravelInfo();
				travelInfo.setInfoId(rs.getString("DESTINATION_ID"));
				travelInfo.setInfoName(rs.getString("DESTINATION_NAME"));
				travelInfo.setVoteCount(rs.getString("DESTINATION_VOTE"));

				travelInfo.setThumbImg(rs.getString("DESTINATION_IMG"));
				
				
				location = rs.getString("DESTINATION_LOCATION");
				if(location.equals("A"))
				{
					travelInfo.setLocation("수도권");
				}
				else if(location.equals("B"))
				{
					travelInfo.setLocation("강원권");
				}
				else if(location.equals("C"))
				{
					travelInfo.setLocation("경상권");
				}
				else if(location.equals("D"))
				{
					travelInfo.setLocation("전라권");
				}
				else
				{
					travelInfo.setLocation("제주권");
				}
				
				travelInfos.add(travelInfo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs, psmt, conn);
		}
		return travelInfos;
	}
	
	
}
