package test;
import java.sql.SQLException;

import project.store.jdbc.ConnectionFactoryForAWS;

public class DBConnectionTest {
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
	    ConnectionFactoryForAWS testAws=ConnectionFactoryForAWS.getInstance();
	    
	    
	    testAws.createConnection();

	}
}
