package test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import DAO.connection;
import DAO.login;

// TODO: Auto-generated Javadoc
/**
 * The Class loginTest.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020Äê7ÔÂ4ÈÕ
 */

public class loginTest {
	
	/** The connection. */
	connection connection = new connection();
	
	/**
	 * Compare test.
	 */
	@Test
	public void compareTest() {
		try {
			connection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login a = new login(connection);
		boolean flag = a.compare("12345", "12345");
		assertTrue(flag);
	}
	
	/**
	 * Over.
	 */
	@After
	public void over() {
		try {
			connection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
