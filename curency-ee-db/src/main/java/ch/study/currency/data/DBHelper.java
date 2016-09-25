package ch.study.currency.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**Build on connection for the application to the local Database. 
 * @author Mirko Eberlein
 *
 */
public enum DBHelper {
	INSTANCE;
	private Connection connection;

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection == null){
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite::resource:currency.db");
			connection.setAutoCommit(false);
		}
		return connection;
	}
}
