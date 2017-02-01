package jdbc;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONObject;

public class ConnectionPoolingBean {
	private DataSource ds = null;
	private Context ctx = null;
	
	public ConnectionPoolingBean() throws NamingException{
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("jdbc/fastCoffeeDB"); //TODO - replace with WAS values
	}
	
	public Connection getConnction(String username, String password){
		Connection conToDB = null;
		try{
			conToDB = ds.getConnection(username, password);
			conToDB.setAutoCommit(false);
		}catch(SQLException SQLE){
			SQLE.printStackTrace();
		}
		if(conToDB != null){
			return conToDB;
		}else{
			return null;
		}
			 
		
	} 

	public void insert(Connection conToDB, JSONObject obj )
			throws SQLException {
		
		PreparedStatement pstmt;
		try {
			
			PreparedStatement stmt = conToDB.prepareStatement("INSERT INTO JSON VALUES (?)");

			  while (true) {
			      // Create a new Clob instance as I'm inserting into a CLOB data type
			      Clob clob = conToDB.createClob();
			      // Store my JSON into the CLOB
			      clob.setString(1, obj.toString());
			      // Set clob instance as input parameter for INSERT statement
			      stmt.setClob(1, clob);
			      // Execute the INSERT statement
			      int affectedRows = stmt.executeUpdate();
			      // Free up resource
			      clob.free();
			      // Commit inserted row to the database
			      conToDB.commit();
			      System.out.println(affectedRows + " row(s) inserted.");
			  }
			
			
		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} finally {
			if (conToDB != null)
				conToDB.close();
		}

	}


}
