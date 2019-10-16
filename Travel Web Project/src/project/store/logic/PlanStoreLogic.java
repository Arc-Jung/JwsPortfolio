package project.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import project.domain.Plan;
import project.store.PlanStore;
import project.store.jdbc.ConnectionFactoryForAWS;
import project.store.jdbc.JdbcUtils;

@Repository
public class PlanStoreLogic implements PlanStore{
	
	private ConnectionFactoryForAWS aws;

	
	public PlanStoreLogic()
	{
		aws=ConnectionFactoryForAWS.getInstance();
		System.out.println("스토어측: AWS연결 성공!");
	}

	
	
	@Override
	public void settingPlanner(Plan plan) {
		String location = plan.getLocation();
		String date = plan.getDate();
		String startTime = plan.getStartTime();
		String endTime = plan.getEndTime();
		String vehicle = plan.getVehicle();
		String userID = plan.getUserID();
		String longitude = plan.getLongitude();
		String latitude = plan.getLatitude();
		
		System.out.println("스토어측: settingPlanner함수호출!!");
		System.out.println("location :"+location);
		System.out.println("date :"+date);
		System.out.println("start :"+startTime);
		System.out.println("end :"+endTime);
		System.out.println("vehicle :"+vehicle);
		System.out.println("UserID :"+userID);
		
		
		
		String sql="INSERT INTO PLANNERTB(ID_NUMBER, TIME_START, TIME_END, TRANSPORT, DATETIME, USERID, NAME, DESTINATION_LAT, DESTINATION_LONG)"
				+ "VALUES (PLAN_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = aws.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, startTime);
			psmt.setString(2, endTime);
			psmt.setString(3, vehicle);
			psmt.setString(4, date);
			psmt.setString(5, userID);
			psmt.setString(6, location);
			psmt.setString(7, latitude);
			psmt.setString(8, longitude);
			
			psmt.executeUpdate();
			
			System.out.println("스토어측: 플래너 DB삽입 성공!!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
			
	}



	@Override
	public List<Plan> showAllPlanner(String userID) {
		// TODO Auto-generated method stub
		
		String sql="SELECT ID_NUMBER, NAME, TIME_START, TIME_END, TRANSPORT, DATETIME, DESTINATION_LONG, DESTINATION_LAT FROM PLANNERTB"
				+ " WHERE USERID=?"
				+ " ORDER BY DATETIME ASC";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Plan> plans = new ArrayList<Plan>();
		
		try {
			conn = aws.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, userID);
			rs = psmt.executeQuery();
			
			int no = 1;
			while(rs.next())
			{
				Plan plan = new Plan();
				
				plan.setPlanID(rs.getString(1));
				plan.setLocation(rs.getString(2));
				plan.setStartTime(rs.getString(3));
				plan.setEndTime(rs.getString(4));
				plan.setVehicle(rs.getString(5));
				plan.setDate(rs.getString(6));
				plan.setLongitude(rs.getString(7));
				plan.setLatitude(rs.getString(8));
				
				plan.setNo(no);
				no++;
				
				plans.add(plan);
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("스토어측: 플레너 출력 중 SQL예외발생!");
			throw new RuntimeException(e);
			
		}
		finally
		{
			JdbcUtils.close(psmt,conn,rs);
			System.out.println("JDBC연결 종료!");
		}
		
		
		
		return plans;
	}



	@Override
	public void deletePlanner(String planID) {
		// TODO Auto-generated method stub
		
		String sql="DELETE FROM PLANNERTB WHERE ID_NUMBER = ?";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = aws.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, planID);
			psmt.executeUpdate();
			
			System.out.println("스토어측: 플래너 삭제 성공!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}
		
	}

	
	
	
	

}
