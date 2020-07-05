/*
 * 
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class connection.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020.7.4 
 */
    //
public class connection {
    
    /** The url. */
    private final String url="jdbc:mysql://localhost:3306/球员管理系统?useUnicode=true&characterEncoding=UTF-8";
	
	/** The username. */
	private final String username="root";
	
	/** The password. */
	private final String password="yu104610";
	
	/** The conn. */
	public Connection conn;
    
    /**
     * Connect.
     *
     * @throws Exception the exception
     */
    public void connect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(url,username,password);
	}
}
