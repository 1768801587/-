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
 * @date 2020��7��4��
 */

public class register extends JFrame implements ActionListener, FocusListener {
	
	/** The tp. */
	private JLabel tp;
	
	/** The namelab. */
	private JLabel namelab;// ������ǩ
	
	/** The passlab. */
	private JLabel passlab;
	
	/** The passlab 2. */
	private JLabel passlab2;// �ظ�����
	
	/** The nametext. */
	private JTextField nametext;
	
	/** The passtext. */
	private JPasswordField passtext;// ���������
	
	/** The passtext 2. */
	private JPasswordField passtext2;// �ظ�����
	
	/** The zhuce. */
	private JButton zhuce;// JButton ������ť
	
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
		super.setTitle("ע�ᴰ��");
		super.setBounds(0, 0, 600, 450);
		super.setLocation(500, 200);

		super.getContentPane().add(new JLabel(new ImageIcon("F:\\code\\JAVA\\��Ա����ϵͳ\\picture\\LeBron2.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);

		this.connection = connection;
		// ������
		tp = new JLabel("��Ա����ϵͳ");
		tp.setBounds(160, 20, 300, 40);
		tp.setFont(new Font("����", Font.BOLD, 40));
		tp.setForeground(Color.blue);
		this.getLayeredPane().add(tp);

		namelab = new JLabel("�û���:");
		namelab.setBounds(140, 120, 150, 30);
		namelab.setFont(new Font("����", Font.BOLD, 30));
		namelab.setForeground(Color.white);
		this.getLayeredPane().add(namelab);

		passlab = new JLabel("��  ��:");
		passlab.setBounds(140, 180, 150, 30);
		passlab.setFont(new Font("����", Font.BOLD, 30));
		passlab.setForeground(Color.yellow);
		this.getLayeredPane().add(passlab);

		passlab2 = new JLabel("�ظ�����:");
		passlab2.setBounds(140, 240, 150, 30);
		passlab2.setFont(new Font("����", Font.BOLD, 30));
		passlab2.setForeground(Color.white);
		this.getLayeredPane().add(passlab2);

		nametext = new JTextField();
		nametext.setBounds(280, 120, 150, 30);
		nametext.setFont(new Font("����", Font.BOLD, 20));
		nametext.setForeground(Color.black);
		this.getLayeredPane().add(nametext);

		passtext = new JPasswordField();
		passtext.setBounds(280, 180, 150, 30);
		passtext.setFont(new Font("����", Font.BOLD, 20));
		passtext.setForeground(Color.black);
		this.getLayeredPane().add(passtext);

		passtext2 = new JPasswordField();
		passtext2.setBounds(280, 240, 150, 30);
		passtext2.setFont(new Font("����", Font.BOLD, 20));
		passtext2.setForeground(Color.black);
		this.getLayeredPane().add(passtext2);

		zhuce = new JButton("ע��");
		zhuce.setBounds(140, 320, 100, 50);
		zhuce.setFont(new Font("����", Font.BOLD, 20));
		zhuce.setForeground(Color.black);
		this.getLayeredPane().add(zhuce);

		clear = new JButton("���");
		clear.setBounds(370, 320, 100, 50);
		clear.setFont(new Font("����", Font.BOLD, 20));
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
					JOptionPane.showMessageDialog(null, "ע��ɹ�!!!");
					super.dispose();
				}
			} catch (SQLException e) {
				System.out.println(e.getErrorCode() + "    " + e.getLocalizedMessage());
				JOptionPane.showMessageDialog(null, "�Բ���,���û����Ѿ�����!!!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ȡ����ʧ��!!!");
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
			JOptionPane.showMessageDialog(null, "��ȡ����ʧ��!!!");
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

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	    */
	    
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	    */
	    
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (�� Javadoc)
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
				JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ��");
			} else if (passtext.getText().equals(passtext2.getText())) {
				Register(nametext.getText(), passtext.getText());
			} else {
				JOptionPane.showMessageDialog(null, "�������벻һ��!!!");
				System.out.println(passtext.getText() + " " + passtext2.getText());
			}
		}

	}

}
