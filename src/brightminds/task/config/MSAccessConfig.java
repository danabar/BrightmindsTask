package brightminds.task.config;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * this class have database configuration and connection method
 *
 * @author Dana Barghouthi
 *
 */
public class MSAccessConfig {

	private MSAccessConfig() {
		super();

	}

	/**
	 * This method return database connection object
	 *
	 * @return
	 */
	public static Connection connection() {
		Connection connection = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			connection = DriverManager.getConnection(
					"jdbc:ucanaccess://C:\\Task\\db\\accountsdb.accdb");
			Logger.getLogger(MSAccessConfig.class)
					.info("Connected Successfully");
		} catch (Exception e) {
			Logger.getLogger(MSAccessConfig.class).error(e.getMessage());
		}

		return connection;

	}
}