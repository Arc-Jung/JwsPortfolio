package project.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.domain.InfoFile;
import project.domain.TravelInfo;
import project.store.InfoFileStore;
import project.store.jdbc.ConnectionFactoryForAWS;
import project.store.jdbc.JdbcUtils;

@Repository
public class InfoFileStoreLogic implements InfoFileStore {

	private ConnectionFactoryForAWS connectionFactory;

	public InfoFileStoreLogic() {

		connectionFactory = ConnectionFactoryForAWS.getInstance();
	}

	@Override
	public void create(InfoFile infoFile) {
		String sql = "INSERT INTO FILETB(FILE_ID, LINK, INFO_ID) VALUES (FILE_SEQ.NEXTVAL,?,?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, infoFile.getFilePath());
			psmt.setString(2, infoFile.getTravelInfoId());
			psmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn);
		}
	}

	@Override
	public void delete(String fileId) {
		String sql = "DELETE FROM FILETB WHERE FILE_ID=?" ;

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, fileId);
			psmt.executeUpdate();
			
			System.out.println("DB삭제 완료!  infoID:"+fileId);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			
			JdbcUtils.close(psmt, conn);
		}

	}

	@Override
	public List<InfoFile> retrieveAll(String infoId) {
		String sql = "SELECT FILE_ID, LINK, INFO_ID FROM FILETB WHERE INFO_ID= ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<InfoFile> infoFiles = new ArrayList<InfoFile>();

		try {
			conn = connectionFactory.createConnection();
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, infoId);
			rs = psmt.executeQuery();

			while (rs.next()) {

				InfoFile infoFile = new InfoFile();
				infoFile.setFileId(rs.getString(1));
				infoFile.setFilePath(rs.getString(2));
				infoFile.setTravelInfoId(rs.getString(3));
				infoFiles.add(infoFile);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(psmt, conn, rs);
		}

		return infoFiles;
	}

}
