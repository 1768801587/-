/*
 * 
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class login.
 *
 * @author yujiaqing
 * @version  v1.0
 */

public class login {
	
	/** The pst. */
	public PreparedStatement pst = null;
	
	/** The rs. */
	public ResultSet rs = null;
	
	/** The conn. */
	Connection conn;
	
	/**
	 * Instantiates a new login.
	 *
	 * @param connection the connection
	 */
	public login(connection connection) {
		this.conn=connection.conn;
	}

	/**
	 * Compare.
	 *
	 * @param username the username
	 * @param passward the passward
	 * @return true, if successful
	 */
	public boolean compare(String username, String passward) {
		boolean m = false;
		if (username.equals("") || passward.equals("")) {
			JOptionPane.showMessageDialog(null, "用户名或密码不能为空!!!");
		} else {
			String sql = "select passward from clients where name=\"" + username + "\"";
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery(sql);
				if (rs.next()) {
					String p = rs.getString(1);
					if (p.equals(passward))
						m = true;
					else {
						JOptionPane.showMessageDialog(null, "密码错误!!!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名不存在!!!");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "对不起，没有查询到该用户名！");
			} finally {
				try {
					//pst.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return m;
	}
}
