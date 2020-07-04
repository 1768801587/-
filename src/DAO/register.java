/*
 * 
 */
package DAO;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class register.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020年7月4日
 */

public class register extends JFrame implements ActionListener, FocusListener {
	
	/** The tp. */
	private JLabel tp;
	
	/** The namelab. */
	private JLabel namelab;// 创建标签
	
	/** The passlab. */
	private JLabel passlab;
	
	/** The passlab 2. */
	private JLabel passlab2;// 重复密码
	
	/** The nametext. */
	private JTextField nametext;
	
	/** The passtext. */
	private JPasswordField passtext;// 创建密码框
	
	/** The passtext 2. */
	private JPasswordField passtext2;// 重复密码
	
	/** The zhuce. */
	private JButton zhuce;// JButton 创建按钮
	
	/** The clear. */
	private JButton clear;
	
	/** The connection. */
	connection connection;
	
	/** The pst. */
	private PreparedStatement pst;
	
	/** The rs. */
	ResultSet rs = null;

	/**
	 * Instantiates a new register.
	 *
	 * @param conn the conn
	 */
	public register(Connection conn) {
		this.connection = new connection();
		this.connection.conn = conn;
	}

	/**
	 * Instantiates a new register.
	 *
	 * @param connection the connection
	 */
	public register(connection connection) {
		super.setTitle("注册窗口");
		super.setBounds(0, 0, 600, 450);
		super.setLocation(500, 200);

		super.getContentPane().add(new JLabel(new ImageIcon("F:\\code\\JAVA\\球员管理系统\\picture\\LeBron2.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);

		this.connection = connection;
		// 添加组件
		tp = new JLabel("球员管理系统");
		tp.setBounds(160, 20, 300, 40);
		tp.setFont(new Font("楷体", Font.BOLD, 40));
		tp.setForeground(Color.blue);
		this.getLayeredPane().add(tp);

		namelab = new JLabel("用户名:");
		namelab.setBounds(140, 120, 150, 30);
		namelab.setFont(new Font("楷体", Font.BOLD, 30));
		namelab.setForeground(Color.white);
		this.getLayeredPane().add(namelab);

		passlab = new JLabel("密  码:");
		passlab.setBounds(140, 180, 150, 30);
		passlab.setFont(new Font("楷体", Font.BOLD, 30));
		passlab.setForeground(Color.yellow);
		this.getLayeredPane().add(passlab);

		passlab2 = new JLabel("重复密码:");
		passlab2.setBounds(140, 240, 150, 30);
		passlab2.setFont(new Font("楷体", Font.BOLD, 30));
		passlab2.setForeground(Color.white);
		this.getLayeredPane().add(passlab2);

		nametext = new JTextField();
		nametext.setBounds(280, 120, 150, 30);
		nametext.setFont(new Font("楷体", Font.BOLD, 20));
		nametext.setForeground(Color.black);
		this.getLayeredPane().add(nametext);

		passtext = new JPasswordField();
		passtext.setBounds(280, 180, 150, 30);
		passtext.setFont(new Font("楷体", Font.BOLD, 20));
		passtext.setForeground(Color.black);
		this.getLayeredPane().add(passtext);

		passtext2 = new JPasswordField();
		passtext2.setBounds(280, 240, 150, 30);
		passtext2.setFont(new Font("楷体", Font.BOLD, 20));
		passtext2.setForeground(Color.black);
		this.getLayeredPane().add(passtext2);

		zhuce = new JButton("注册");
		zhuce.setBounds(140, 320, 100, 50);
		zhuce.setFont(new Font("楷体", Font.BOLD, 20));
		zhuce.setForeground(Color.black);
		this.getLayeredPane().add(zhuce);

		clear = new JButton("清除");
		clear.setBounds(370, 320, 100, 50);
		clear.setFont(new Font("楷体", Font.BOLD, 20));
		clear.setForeground(Color.black);
		this.getLayeredPane().add(clear);

		zhuce.addActionListener(this);
		clear.addActionListener(this);

		super.setVisible(true);

	}

	/**
	 * Register.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public void Register(String username, String password) {
		try {
			String sql = "insert into clients(name,passward)values(\'" + username + "\',\'" + password + "\')";
			System.out.println(sql);
			pst = connection.conn.prepareStatement(sql);
			try {
				int a = 0;
				a = pst.executeUpdate(sql);
				if (a == 1) {
					JOptionPane.showMessageDialog(null, "注册成功!!!");
					super.dispose();
				}
			} catch (SQLException e) {
				System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
				JOptionPane.showMessageDialog(null, "对不起,该用户名已经存在!!!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "获取连接失败!!!");
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Register 1.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean Register1(String username, String password) {

		try {
			String sql = "select passward from clients where name=\'" + username + "\'";
			pst = connection.conn.prepareStatement(sql);
			try {
				rs = pst.executeQuery(sql);
				if (rs.first()) {
					return false;
				} else {
					return true;
				}
			} catch (SQLException e) {

				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "获取连接失败!!!");
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	    */
	    
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	    */
	    
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clear) {
			nametext.setText("");
			passtext.setText("");
			passtext2.setText("");
		}
		if (e.getSource() == zhuce) {
			if (nametext.getText().equals("") || passtext.getText().equals("") || passtext2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
			} else if (passtext.getText().equals(passtext2.getText())) {
				Register(nametext.getText(), passtext.getText());
			} else {
				JOptionPane.showMessageDialog(null, "两次密码不一致!!!");
				System.out.println(passtext.getText() + " " + passtext2.getText());
			}
		}

	}

}
