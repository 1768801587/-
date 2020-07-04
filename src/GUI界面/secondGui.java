package GUI����;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import DAO.connection;
import ����.music;

// TODO: Auto-generated Javadoc
/**
 * The Class secondGui.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020��7��4��
 */

public class secondGui extends JFrame implements ActionListener, FocusListener, MouseListener {
	
	/** The qd. */
	private JLabel qd;
	
	/** The qdw. */
	private JLabel qdw;// ������ӱ�ǩ
	
	/** The qde. */
	private JLabel qde;
	
	/** The bar. */
	// ��Ӳ˵���
	private JMenuBar bar = new JMenuBar();
	
	/** The menu file. */
	private JMenu menuFile = new JMenu("�˵�");
	
	/** The menu tool. */
	private JMenu menuTool = new JMenu("����");

	/** The menu item introduction. */
	private JMenuItem menuItemIntroduction = new JMenuItem("˵��");
	
	/** The menu item exit login. */
	private JMenuItem menuItemExitLogin = new JMenuItem("�˳���¼");
	
	/** The menu item exit system. */
	private JMenuItem menuItemExitSystem = new JMenuItem("�˳�ϵͳ");
	
	/** The menu item music. */
	private JMenuItem menuItemMusic = new JMenuItem("��������");
	
	/** The xibu. */
	private JButton xibu[] = new JButton[15];// ������Ӱ�ť
	
	/** The dongbu. */
	private JButton dongbu[] = new JButton[15];

	/** The zj. */
	private JLabel zj;
	
	/** The ty. */
	private JLabel ty;
	
	/** The zh. */
	private JLabel zh;
	
	/** The cx. */
	private JLabel cx;

	/** The dn. */
	String dn[] = { "��������", "����", "���˹", "76��", "����", "��ӥ", "�Ȼ�", "ħ��", "���", "�Ʒ�", "��ʿ", "��ţ", "����", "������", "��¹" };
	
	/** The xn. */
	String xn[] = { "��ʿ", "�촬", "����", "̫��", "����", "���", "ɭ����", "������", "����", "��ʿ", "������", "����", "���", "���", "����" };
	
	/** The log 4 j. */
	Logger log4j=Logger.getLogger(this.getClass());//��־��Ϣ
	
	/** The connection. */
	connection connection;
	
	/** The th. */
	private thridGui th;
	
	/** The pl. */
	private playerListGui pl;
	
	/** The play 0. */
	music play0 = new music("music\\one-day.mp3");
	
	/** The fa. */
	private int fa = 0;// ������ֵĿ���

	/**
	 * Instantiates a new second gui.
	 *
	 * @param connection the connection
	 */
	public secondGui(connection connection) {
		play0.start();
		super.setTitle("��Ӵ���");
		super.setBounds(0, 0, 800, 1000);
		super.setLocation(300, 20);

		this.connection = connection;
		super.getContentPane().add(new JLabel(new ImageIcon("F:\\code\\JAVA\\��Ա����ϵͳ\\picture\\Lebron3.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);

		pl = new playerListGui(connection);//�����µ���Ա�б��࣬����Ϊ��ҳ��׷����Ա��������Ա����Աת���
		// �˵���
		// 1.�Ѳ˵����뵽JFrame
		super.setJMenuBar(bar);
		// 2.�Ѳ˵����뵽JMenuBar��
		bar.add(menuFile);
		bar.add(menuTool);
		// 3.�Ѳ˵�����뵽�˵���
		// ====3.1��˵�����˵���
		menuFile.add(menuItemIntroduction);
		menuFile.add(menuItemExitLogin);
		menuFile.add(menuItemExitSystem);
		// menuFile.addSeparator();// ����ָ���
		// ====3.2�򹤾߲˵��м���˵���
		menuTool.add(menuItemMusic);
		// ���˵���ע�������
		menuItemIntroduction.addActionListener(this);
		menuItemExitLogin.addActionListener(this);
		menuItemExitSystem.addActionListener(this);
		menuItemMusic.addActionListener(this);

		// ��ӱ�ǩ�밴ť���
		qd = new JLabel("�������");
		qd.setBounds(300, 35, 200, 40);
		qd.setFont(new Font("����", Font.BOLD, 40));
		qd.setForeground(Color.white);
		this.getLayeredPane().add(qd);

		qdw = new JLabel("�������");
		qdw.setBounds(180, 80, 200, 30);
		qdw.setFont(new Font("����", Font.BOLD, 30));
		qdw.setForeground(Color.blue);
		this.getLayeredPane().add(qdw);

		qde = new JLabel("�������");
		qde.setBounds(480, 80, 200, 30);
		qde.setFont(new Font("����", Font.BOLD, 30));
		qde.setForeground(Color.blue);
		this.getLayeredPane().add(qde);

		// ����������������ӵİ�ť
		for (int i = 0; i < 15; i++) {
			xibu[i] = new JButton(xn[i]);
			dongbu[i] = new JButton(dn[i]);
			if (i % 2 == 0) {
				xibu[i].setBounds(100, 120 + i * 40, 120, 40);
				dongbu[i].setBounds(420, 120 + i * 40, 120, 40);
			} else if (i % 2 == 1) {
				xibu[i].setBounds(240, 120 + (i - 1) * 40, 120, 40);
				dongbu[i].setBounds(560, 120 + (i - 1) * 40, 120, 40);
			}
			xibu[i].setFont(new Font("����", Font.BOLD, 20));
			dongbu[i].setFont(new Font("����", Font.BOLD, 20));
			xibu[i].setForeground(Color.black);
			dongbu[i].setForeground(Color.black);
			this.getLayeredPane().add(xibu[i]);
			this.getLayeredPane().add(dongbu[i]);

			xibu[i].addActionListener(this);
			dongbu[i].addActionListener(this);
		}

		// ����������ת�ᣬ׷����Ա�Ȱ�ť
		zj = new JLabel("׷��");
		zj.setFont(new Font("����", Font.BOLD, 30));
		ty = new JLabel("����");
		ty.setFont(new Font("����", Font.BOLD, 30));
		zh = new JLabel("ת��");
		zh.setFont(new Font("����", Font.BOLD, 30));
		cx = new JLabel("��ѯ");
		cx.setFont(new Font("����", Font.BOLD, 30));

		zj.addMouseListener(this);// Ϊ��ť��Ӽ�����
		ty.addMouseListener(this);
		zh.addMouseListener(this);
		cx.addMouseListener(this);

		JPanel ps = new JPanel();
		ps.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		ps.add(zj);
		ps.add(ty);
		ps.add(zh);
		ps.add(cx);
		ps.setBounds(180, 800, 400, 50);
		ps.setFont(new Font("����", Font.BOLD, 30));
		ps.setBackground(Color.pink);
		ps.setForeground(Color.blue);
		this.getLayeredPane().add(ps);

		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    
    /**
     * Rizhi.
     */
    public void rizhi() {
    	Logger log4j=Logger.getLogger(this.getClass());
    	try {
    		
    	}catch(Exception e){
    		log4j.error(e.getMessage());
    	}
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

		if (e.getSource() == menuItemIntroduction) {
			JOptionPane.showMessageDialog(null, "��˵���������ӿ��Խ���������ҳ�鿴�����Ϣ");
		} else if (e.getSource() == menuItemExitLogin) {
			play0.stop();
			super.dispose();
			new startGui().show();
		} else if (e.getSource() == menuItemExitSystem) {
			JOptionPane.showMessageDialog(null, "�ɹ��˳�����");
			play0.stop();
			System.exit(0);
		} else if (e.getSource() == menuItemMusic) {
			if (fa == 0) {
				play0.suspend();
				fa = 1;
			} else if (fa == 1) {
				play0.resume();
				fa = 0;
			}
		}
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == xibu[i]) {
				new thridGui(this, xn[i], connection);

			} else if (e.getSource() == dongbu[i]) {
				th = new thridGui(this, dn[i], connection);

			}
		}

	}
    
    /**
     * Find player.
     *
     * @param datitle the datitle
     */
    public void findPlayer(Vector datitle) {
    	JFrame findGui = new JFrame("��ѯ");
		findGui.setSize(450, 600);
		findGui.setLocation(700, 200);
		findGui.setLayout(new GridLayout(11, 2, 2, 2));

		JLabel wenpi[] = new JLabel[11];
		JLabel dapi[] = new JLabel[11];
		
		  for (int i = 0; i < 11; i++) { 
	       wenpi[i] = new JLabel(""+ pl.title.get(i ));
	       dapi[i]=new JLabel(""+datitle.get(i));
	       wenpi[i].setFont(new Font("����", Font.BOLD, 25));
	       dapi[i].setFont(new Font("����", Font.BOLD, 25));
	       findGui.add(wenpi[i]);
	       findGui.add(dapi[i]);
		  } 
		  findGui.setVisible(true);
    }
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == zj) {
			pl.zjButton();
		} else if (e.getSource() == ty) {
			String name = JOptionPane.showInputDialog(null, "������������Ա�����֣�\n", "������Ա", JOptionPane.PLAIN_MESSAGE);
			pl.pd.retirePlayer(name);
			log4j.info(name+"��Ա���۳ɹ�");
		} else if (e.getSource() == zh) {
			String name = JOptionPane.showInputDialog(null, "������ת����Ա�����֣�\n", "��Աת��", JOptionPane.PLAIN_MESSAGE);
			String teamName = JOptionPane.showInputDialog(null, "������ת����Աת���������\n", "��Աת��", JOptionPane.PLAIN_MESSAGE);
			pl.pd.changeTeam(name, teamName);
			log4j.info(name+"��Աת��ɹ�");
		} else if (e.getSource() == cx) {
			String name = JOptionPane.showInputDialog(null, "��������Ҫ��ѯ��Ա�����֣�\n", "�鿴��Ա��Ϣ", JOptionPane.PLAIN_MESSAGE);
			Vector datitle=new Vector();
			datitle=pl.pd.findPlayer(name);//��ѯ������Ա��Ϣ
			if(datitle.isEmpty()) {
				JOptionPane.showMessageDialog(null, "δ��ѯ������Ա������");
			}
			else {
				findPlayer(datitle);	
			log4j.info(name+"��Ա��ѯ�ɹ�");
			}
			
		}

	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == zj) {
			zj.setForeground(Color.white);
		} else if (e.getSource() == zh) {
			zh.setForeground(Color.BLUE);
		} else if (e.getSource() == ty) {
			ty.setForeground(Color.GREEN);
		} else if (e.getSource() == cx) {
			cx.setForeground(Color.red);
		}

	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == zj) {
			zj.setForeground(Color.blue);
		} else if (e.getSource() == zh) {
			zh.setForeground(Color.green);
		} else if (e.getSource() == ty) {
			ty.setForeground(Color.red);
		} else if (e.getSource() == cx) {
			cx.setForeground(Color.white);
		}

	}
}
