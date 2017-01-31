package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPoolingBean {

	// ...

	public void ejbCreate() throws Exception {
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("jdbc/fastCoffeeDB");
	}

	// public void updatePrice(float price, String cofName,
	// String username, String password)
	// throws SQLException{
	//
	// Connection con = null;
	// PreparedStatement pstmt;
	// try {
	// con = ds.getConnection(username, password);
	// con.setAutoCommit(false);
	// pstmt = con.prepareStatement("UPDATE COFFEES " +
	// "SET PRICE = ? " +
	// "WHERE COF_NAME = ?");
	// pstmt.setFloat(1, price);
	// pstmt.setString(2, cofName);
	// pstmt.executeUpdate();
	//
	// con.commit();
	// pstmt.close();
	//
	// } finally {
	// if (con != null)
	// con.close();
	// }
	// }
	public void insert(Data data, String username, String password)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt;
		try {
			con = ds.getConnection(username, password);
			con.setAutoCommit(false);
			pstmt = con
					.prepareStatement("insert into  temp(id, name, position) "
							+ "VALUES (?, ?, ?)");
			pstmt.setInt(1, data.getM_id());
			pstmt.setString(1, data.getM_name());
			pstmt.setString(1, data.getM_position());

			con.commit();
			pstmt.close();
		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} finally {
			if (con != null)
				con.close();
		}

	}

	private DataSource ds = null;
	private Context ctx = null;
}
