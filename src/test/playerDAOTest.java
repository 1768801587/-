package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import DAO.playerDAO;
import DAO.connection;

// TODO: Auto-generated Javadoc
/**
 * The Class playerDAOTest.
 *
 * @author yujiaqing
 * @version v1.0
 * @date 2020年7月4日
 */

public class playerDAOTest {

	/** The connection. */
	connection connection = new connection();

	/** The player. */
	playerDAO player;

	/** The name. */
	String name;

	/**
	 * Start.
	 */
	@Before
	public void start() {
		try {
			connection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player = new playerDAO(connection);
		name = "科比-布莱恩特";
	}

	/**
	 * Insert player 1 test.
	 */
	@Test
	public void insertPlayer1Test() {
		boolean f1 = player.insertPlayer1(name);
		assertTrue(f1);
	}

	/**
	 * Change team test.
	 */
	@Test
	public void changeTeamTest() {
		String teamName = "湖人";
		boolean f2 = player.changeTeam1(name, teamName);
		assertFalse(f2);
	}

	/**
	 * Find player test.
	 */
	@Test
	public void findPlayerTest() {
		Vector f3 = new Vector();
		f3 = player.findPlayer("勒布朗-詹姆斯");
		String f33 = (String) f3.get(1);
		assertEquals("勒布朗-詹姆斯", f33);
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
