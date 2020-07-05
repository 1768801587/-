/*
 * 
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;


    // TODO: Auto-generated Javadoc
/**
     * The Class playerDAO.
     *
     * @author yujiaqing
     * @version  v1.0
     * @date 2020年7月4日
     */
    
public class playerDAO {
	
	/** The conn. */
	private Connection conn;
	
	/** The pst. */
	private PreparedStatement pst = null;
	
	/** The rs. */
	private ResultSet rs = null;//表格的时候用
	
	/** The frs. */
	private ResultSet frs=null;//查找球员时候用
	
	/** The rr. */
	public Vector rr = new Vector();
	
	/** The team name. */
	private String teamName;

	/**
	 * Instantiates a new player DAO.
	 *
	 * @param connection the connection
	 * @param teamName the team name
	 */
	public playerDAO(connection connection, String teamName) {
		conn = connection.conn;
		this.teamName = teamName;
	}
    
    /**
     * Instantiates a new player DAO.
     *
     * @param connection the connection
     */
    public playerDAO(connection connection) {
    	this.conn=connection.conn;
    }
	
	/**
	 * Sets the age.
	 */
	public void setAge() {
		String sql = "update player set age=YEAR(NOW())-birthYear";
		try {
			pst = conn.prepareStatement(sql);
			int line=pst.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("没有连接到数据库");
			e.printStackTrace();
		}

	}

	/**
	 * Insert player.
	 *
	 * @param info the info
	 */
	public void insertPlayer(Vector info) {
		// varchar   int  int  varchar     double   varchar   double   double   varchar   varchar int
		String sql = "insert into player(name,age,careerYear,playerLocation,height,teamName,point,time,activity,jerseyNumber,birthYear) values(\'"
				+ info.get(0) + "\'," + info.get(1) + ","  + info.get(2) + ","+ "\'"+  info.get(3) +"\'," + info.get(4)
				+ "," + "\'" + info.get(5) + "\'," + info.get(6)+ ","+ info.get(7) + ","+ "\'" + info.get(8) + "\',\'"
				+ info.get(9)+  "\'," + info.get(10) + ")";
	
		//System.out.println(sql);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int a = 0;
			a = stmt.executeUpdate(sql);
			if (a == 1)
				JOptionPane.showMessageDialog(null, "追加成功!!!");

		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
			if(info.isEmpty())
			JOptionPane.showMessageDialog(null, "记录不允许空值!!!");
			else
			JOptionPane.showMessageDialog(null, "对不起,格式不符合要求或该记录已经存在!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert player 1.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean insertPlayer1(String name) {
		// varchar   int  int  varchar     double   varchar   double   double   varchar   varchar int
		/*String sql = "insert into player(name,age,careerYear,playerLocation,height,teamName,point,time,activity,jerseyNumber,birthYear) values(\'"
				+ info.get(0) + "\'," + info.get(1) + ","  + info.get(2) + ","+ "\'"+  info.get(3) +"\'," + info.get(4)
				+ "," + "\'" + info.get(5) + "\'," + info.get(6)+ ","+ info.get(7) + ","+ "\'" + info.get(8) + "\',\'"
				+ info.get(9)+  "\'," + info.get(10) + ")";*/
	   String sql="select id from player where name =\'"+name+"\'";
		//System.out.println(sql);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet a = null;
			a = stmt.executeQuery(sql);
			if (a .first()) {	
				return false;
			}
			else {	
				return true;
			}
				
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    /**
     * Retire player.
     *
     * @param name the name
     */
    public void retirePlayer(String name) {
    	String sql="update player set activity='历史'  WHERE name=\'"+name+"\'";
    	Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int a = 0;
			a = stmt.executeUpdate(sql);
			if (a == 1)
				JOptionPane.showMessageDialog(null, "退役球员成功!!!");
			else if(a==0) {
				JOptionPane.showMessageDialog(null, "找不到该球员!!!");
			}
		}catch (SQLException e) {
			System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
			e.printStackTrace();
		}
    }
    
    /**
     * Change team.
     *
     * @param name the name
     * @param teamName the team name
     */
    public void changeTeam(String name,String teamName) {
    	String sql="update player set teamName=\""+teamName+"\" where name=\""+name+"\"" ;
    	Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int a = 0;
			a = stmt.executeUpdate(sql);
			if (a == 1)
				JOptionPane.showMessageDialog(null, "球员转会成功!!!");
			else if(a==0) {
				JOptionPane.showMessageDialog(null, "找不到该球员或球队!!!");
			}
		}catch (SQLException e) {
			System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
			e.printStackTrace();
		}
    	
    }
    
    /**
     * Change team 1.
     *
     * @param name the name
     * @param teamName the team name
     * @return true, if successful
     */
    public boolean changeTeam1(String name,String teamName) {
    	String sql="update player set teamName=\""+teamName+"\" where name=\""+name+"\"" ;
    	Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int a = 0;
			a = stmt.executeUpdate(sql);
			if (a == 1)
				return true;
			else if(a==0) {
				return false;
			}
		}catch (SQLException e) {
			System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
    	return false;
    }
    
    /**
     * Find player.
     *
     * @param name the name
     * @return the vector
     */
    public Vector findPlayer(String name) {
    	String sql="select * from player where name=\""+name+"\"" ;
    	Vector re = new Vector();
    	try {
			pst = conn.prepareStatement(sql);
			frs = pst.executeQuery(sql);
			try {
				while (frs.next()) {	
					re.add(frs.getInt("id"));
					re.add(frs.getString("name"));
					re.add(frs.getInt("age"));
					re.add(frs.getInt("careerYear"));
					re.add(frs.getString("playerLocation"));
					re.add(frs.getDouble("height"));
					re.add(frs.getString("teamName"));
					re.add(frs.getDouble("point"));
					re.add(frs.getDouble("time"));
					re.add(frs.getString("activity"));
					re.add(frs.getString("jerseyNumber"));
				}
			} catch (SQLException e) {
				System.out.println("无球队");
				e.printStackTrace();
				// System.out.println(rr);
			} finally {
				try {
					frs.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			System.out.println("没有连接到数据库");
			// JOptionPane.showMessageDialog(null, "不存在该球队!!!");
			e.printStackTrace();
			// System.out.println(rr);
		}
    	return re;
    }
    
    /**
     * P I.
     *
     * @param teamName the team name
     */
    public void pI(String teamName) {
    	String sql = "select * from player where teamName=\"" + teamName + "\"  order by id ASC";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			try {
				while (rs.next()) {
					Vector re = new Vector();
					re.add(rs.getInt("id"));
					re.add(rs.getString("name"));
					re.add(rs.getInt("age"));
					re.add(rs.getInt("careerYear"));
					re.add(rs.getString("playerLocation"));
					re.add(rs.getDouble("height"));
					re.add(rs.getString("teamName"));
					re.add(rs.getDouble("point"));
					re.add(rs.getDouble("time"));
					re.add(rs.getString("activity"));
					re.add(rs.getString("jerseyNumber"));
					rr.add(re);
				}
			} catch (SQLException e) {
				System.out.println("无球队");
				e.printStackTrace();
				// System.out.println(rr);
			} finally {
				try {
					rs.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			System.out.println("没有连接到数据库");
			// JOptionPane.showMessageDialog(null, "不存在该球队!!!");
			e.printStackTrace();
			// System.out.println(rr);
		}
    }
    
    /**
     * Player info.
     *
     * @param teamName the team name
     */
    /*public void playerInfo() {
		pI(teamName);
	}*/
	public void playerInfo(String teamName) {
		pI(teamName);
	}
}
