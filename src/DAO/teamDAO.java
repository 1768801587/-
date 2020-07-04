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
 * The Class teamDAO.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020年7月4日
 */

public class teamDAO {
	
	/** The connection. */
	connection connection;
	
	/** The team name. */
	String teamName;
	
	/** The pst. */
	private PreparedStatement pst = null;
	
	/** The rs. */
	private ResultSet rs = null;
	
	/** The jl. */
	private String[] jl = new String[7];

	/**
	 * Instantiates a new team DAO.
	 *
	 * @param connection the connection
	 * @param teamName the team name
	 */
	public teamDAO(connection connection, String teamName) {
		this.connection = connection;
		this.teamName = teamName;
		for (int i = 0; i < 7; i++) {
			jl[i] = new String();
		}
	}

	/**
	 * Team info.
	 *
	 * @return the string[]
	 */
	public String[] teamInfo() {
		String sql = "select * from team where teamName=\"" + teamName + "\"";
		try {
			pst = connection.conn.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			if (rs.next()) {
				try {
					jl[1]=rs.getString(1);
					jl[2]=rs.getString(2);
					jl[3]="" + rs.getInt(3);
					jl[4]=rs.getString(5);
					jl[5]="" + rs.getInt(4);
					jl[6]=rs.getString(6);
				} catch (SQLException e) {
					System.out.println("发生错误");
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "没有查询到相关信息!!!");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "555不存在该球队!!!");
			e.printStackTrace();
		}

		return jl;

	}
}
