package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import DAO.connection;
import DAO.teamDAO;


    // TODO: Auto-generated Javadoc
/**
     * The Class teamDAOTest.
     *
     * @author yujiaqing
     * @version  v1.0
     * @date 2020ƒÍ7‘¬4»’
     */
    
public class teamDAOTest {
    
    /** The connection. */
    connection connection=new connection();
    
    /** The td. */
    teamDAO td;
    
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
    	td=new teamDAO(connection,"√Õ¡˙");
    }
	
	/**
	 * Teaminfo test.
	 */
	@Test
	public void teaminfoTest() {
		String f5[]=td.teamInfo();
		String f55=f5[1];
		assertEquals("√Õ¡˙",f55);
		try {
			connection.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
