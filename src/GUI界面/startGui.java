package GUI����;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.connection;
import DAO.login;
import DAO.register;

// TODO: Auto-generated Javadoc
/**
 * The Class startGui.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020��7��4��
 */

public class startGui extends JFrame implements ActionListener, FocusListener {
	
	/** The pan. */
	private JPanel pan = new JPanel();
	
	/** The pan 1. */
	private JPanel pan1 = new JPanel();
	
	/** The tp. */
	private JLabel tp;
	
	/** The namelab. */
	private JLabel namelab;// ������ǩ
	
	/** The passlab. */
	private JLabel passlab;
	
	/** The nametext. */
	private JTextField nametext;
	
	/** The passtext. */
	private JPasswordField passtext;// ���������
	
	/** The denglu. */
	private JButton denglu;// JButton ������ť
	
	/** The zhuce. */
	private JButton zhuce;
	
	/** The clear. */
	private JButton clear;
	
	/** The connection. */
	connection connection=new connection();
	
	/**
	 * Instantiates a new start gui.
	 */
	public startGui() {
		
		try {
			connection.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setTitle("��¼����");
		super.setBounds(0, 0, 600, 450);
		super.setLocation(300, 200);
  
		super.getContentPane().add(new JLabel(new ImageIcon("F:\\code\\JAVA\\��Ա����ϵͳ\\picture\\Lebron.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);
		// ������
		tp = new JLabel("��Ա����ϵͳ");
		tp.setBounds(160, 20, 300, 40);
		tp.setFont(new Font("����", Font.BOLD, 40));
		tp.setForeground(Color.blue);
		this.getLayeredPane().add(tp);
 
		namelab = new JLabel("�û���:");
		namelab.setBounds(150, 120, 150, 30);
		namelab.setFont(new Font("����", Font.BOLD, 30));
		namelab.setForeground(Color.white);
		this.getLayeredPane().add(namelab);

		passlab = new JLabel("��  ��:");
		passlab.setBounds(150, 180, 150, 30);
		passlab.setFont(new Font("����", Font.BOLD, 30));
		passlab.setForeground(Color.black);
		this.getLayeredPane().add(passlab);

		nametext = new JTextField();
		nametext.setBounds(270, 120, 150, 30);
		nametext.setFont(new Font("����", Font.BOLD, 20));
		nametext.setForeground(Color.black);
		this.getLayeredPane().add(nametext);

		passtext = new JPasswordField();
		passtext.setBounds(270, 180, 150, 30);
		passtext.setFont(new Font("����", Font.BOLD, 20));
		passtext.setForeground(Color.black);
		this.getLayeredPane().add(passtext);

		denglu = new JButton("��¼");
		denglu.setBounds(130, 280, 100, 50);
		denglu.setFont(new Font("����", Font.BOLD, 20));
		denglu.setForeground(Color.black);
		this.getLayeredPane().add(denglu);

		zhuce = new JButton("ע��");
		zhuce.setBounds(250, 280, 100, 50);
		zhuce.setFont(new Font("����", Font.BOLD, 20));
		zhuce.setForeground(Color.black);
		this.getLayeredPane().add(zhuce);

		clear = new JButton("���");
		clear.setBounds(370, 280, 100, 50);
		clear.setFont(new Font("����", Font.BOLD, 20));
		clear.setForeground(Color.black);
		this.getLayeredPane().add(clear);

		denglu.addActionListener(this);// ��Ӽ���
		zhuce.addActionListener(this);
		clear.addActionListener(this);
		
		super.setVisible(true);
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
		}
		if (e.getSource() == denglu) {
			if(new login(connection).compare(nametext.getText(), passtext.getText())) {
				JOptionPane.showMessageDialog(null, "��¼�ɹ�!!!");
				//��½�ɹ��󣬹ص���ǰҳ�棬���������ҳ��
				super.dispose();
				new secondGui(connection);
			}
		}
		if (e.getSource() == zhuce) {
			new register(connection);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		new startGui();
	}

}