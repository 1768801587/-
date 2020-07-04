package test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import DAO.connection;
import DAO.register;


    // TODO: Auto-generated Javadoc
/**
     * The Class registerTest.
     *
     * @author yujiaqing
     * @version  v1.0
     * @date 2020Äê7ÔÂ4ÈÕ
     */
    
public class registerTest {
     
     /** The connection. */
     connection connection=new connection();
     
     /** The re. */
     register re;
     
     /**
      * Register 1 test.
      */
     @Test
     public void Register1Test() {
    	 try {
    		 connection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 re=new register(connection.conn);
    	 boolean fl=re.Register1("012", "012");
    	 assertTrue(fl);
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
