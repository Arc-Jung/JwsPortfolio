package project.store.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryForAWS {
	
		private static ConnectionFactoryForAWS instance;

		private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
		private static final String URL = "jdbc:oracle:thin:@jws.cc5v8olvshdu.us-east-2.rds.amazonaws.com:1521:LINKPLUS";
		private static final String USER_NAME = "jws";
		private static final String PASSWORD = "00003531";

		private ConnectionFactoryForAWS() {
			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		public static ConnectionFactoryForAWS getInstance() {
			if (instance == null) {
				instance = new ConnectionFactoryForAWS();
			}
			return instance;
		}

		public Connection createConnection() throws SQLException {
			System.out.println("AWS DB연결 성공!");
			return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		}
}
