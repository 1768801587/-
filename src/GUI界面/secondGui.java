package GUI界面;

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
import 其他.music;

// TODO: Auto-generated Javadoc
/**
 * The Class secondGui.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020年7月4日
 */

public class secondGui extends JFrame implements ActionListener, FocusListener, MouseListener {
	
	/** The qd. */
	private JLabel qd;
	
	/** The qdw. */
	private JLabel qdw;// 东部球队标签
	
	/** The qde. */
	private JLabel qde;
	
	/** The bar. */
	// 添加菜单栏
	private JMenuBar bar = new JMenuBar();
	
	/** The menu file. */
	private JMenu menuFile = new JMenu("菜单");
	
	/** The menu tool. */
	private JMenu menuTool = new JMenu("工具");

	/** The menu item introduction. */
	private JMenuItem menuItemIntroduction = new JMenuItem("说明");
	
	/** The menu item exit login. */
	private JMenuItem menuItemExitLogin = new JMenuItem("退出登录");
	
	/** The menu item exit system. */
	private JMenuItem menuItemExitSystem = new JMenuItem("退出系统");
	
	/** The menu item music. */
	private JMenuItem menuItemMusic = new JMenuItem("背景音乐");
	
	/** The xibu. */
	private JButton xibu[] = new JButton[15];// 西部球队按钮
	
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
	String dn[] = { "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "老鹰", "热火", "魔术", "奇才", "黄蜂", "骑士", "公牛", "活塞", "步行者", "雄鹿" };
	
	/** The xn. */
	String xn[] = { "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "开拓者", "雷霆", "爵士", "独行侠", "鹈鹕", "火箭", "马刺", "灰熊" };
	
	/** The log 4 j. */
	Logger log4j=Logger.getLogger(this.getClass());//日志信息
	
	/** The connection. */
	connection connection;
	
	/** The th. */
	private thridGui th;
	
	/** The pl. */
	private playerListGui pl;
	
	/** The play 0. */
	music play0 = new music("music\\one-day.mp3");
	
	/** The fa. */
	private int fa = 0;// 标记音乐的开关

	/**
	 * Instantiates a new second gui.
	 *
	 * @param connection the connection
	 */
	public secondGui(connection connection) {
		play0.start();
		super.setTitle("球队窗口");
		super.setBounds(0, 0, 800, 1000);
		super.setLocation(300, 20);

		this.connection = connection;
		super.getContentPane().add(new JLabel(new ImageIcon("F:\\code\\JAVA\\球员管理系统\\picture\\Lebron3.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);

		pl = new playerListGui(connection);//定义新的球员列表类，用来为当页面追加球员，退役球员，球员转会等
		// 菜单项
		// 1.把菜单加入到JFrame
		super.setJMenuBar(bar);
		// 2.把菜单加入到JMenuBar中
		bar.add(menuFile);
		bar.add(menuTool);
		// 3.把菜单项加入到菜单中
		// ====3.1向菜单加入菜单项
		menuFile.add(menuItemIntroduction);
		menuFile.add(menuItemExitLogin);
		menuFile.add(menuItemExitSystem);
		// menuFile.addSeparator();// 加入分割线
		// ====3.2向工具菜单中加入菜单项
		menuTool.add(menuItemMusic);
		// 给菜单项注册监听器
		menuItemIntroduction.addActionListener(this);
		menuItemExitLogin.addActionListener(this);
		menuItemExitSystem.addActionListener(this);
		menuItemMusic.addActionListener(this);

		// 添加标签与按钮组件
		qd = new JLabel("所有球队");
		qd.setBounds(300, 35, 200, 40);
		qd.setFont(new Font("楷体", Font.BOLD, 40));
		qd.setForeground(Color.white);
		this.getLayeredPane().add(qd);

		qdw = new JLabel("西部球队");
		qdw.setBounds(180, 80, 200, 30);
		qdw.setFont(new Font("楷体", Font.BOLD, 30));
		qdw.setForeground(Color.blue);
		this.getLayeredPane().add(qdw);

		qde = new JLabel("东部球队");
		qde.setBounds(480, 80, 200, 30);
		qde.setFont(new Font("楷体", Font.BOLD, 30));
		qde.setForeground(Color.blue);
		this.getLayeredPane().add(qde);

		// 下面是增加所有球队的按钮
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
			xibu[i].setFont(new Font("楷体", Font.BOLD, 20));
			dongbu[i].setFont(new Font("楷体", Font.BOLD, 20));
			xibu[i].setForeground(Color.black);
			dongbu[i].setForeground(Color.black);
			this.getLayeredPane().add(xibu[i]);
			this.getLayeredPane().add(dongbu[i]);

			xibu[i].addActionListener(this);
			dongbu[i].addActionListener(this);
		}

		// 下面是增加转会，追求球员等按钮
		zj = new JLabel("追加");
		zj.setFont(new Font("楷体", Font.BOLD, 30));
		ty = new JLabel("退役");
		ty.setFont(new Font("楷体", Font.BOLD, 30));
		zh = new JLabel("转会");
		zh.setFont(new Font("楷体", Font.BOLD, 30));
		cx = new JLabel("查询");
		cx.setFont(new Font("楷体", Font.BOLD, 30));

		zj.addMouseListener(this);// 为按钮添加监听器
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
		ps.setFont(new Font("楷体", Font.BOLD, 30));
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

		if (e.getSource() == menuItemIntroduction) {
			JOptionPane.showMessageDialog(null, "简单说明：点击球队可以进入该球队首页查看相关信息");
		} else if (e.getSource() == menuItemExitLogin) {
			play0.stop();
			super.dispose();
			new startGui().show();
		} else if (e.getSource() == menuItemExitSystem) {
			JOptionPane.showMessageDialog(null, "成功退出程序");
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
    	JFrame findGui = new JFrame("查询");
		findGui.setSize(450, 600);
		findGui.setLocation(700, 200);
		findGui.setLayout(new GridLayout(11, 2, 2, 2));

		JLabel wenpi[] = new JLabel[11];
		JLabel dapi[] = new JLabel[11];
		
		  for (int i = 0; i < 11; i++) { 
	       wenpi[i] = new JLabel(""+ pl.title.get(i ));
	       dapi[i]=new JLabel(""+datitle.get(i));
	       wenpi[i].setFont(new Font("楷体", Font.BOLD, 25));
	       dapi[i].setFont(new Font("楷体", Font.BOLD, 25));
	       findGui.add(wenpi[i]);
	       findGui.add(dapi[i]);
		  } 
		  findGui.setVisible(true);
    }
	
	
	    /* (非 Javadoc)
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
			String name = JOptionPane.showInputDialog(null, "请输入退役球员的名字：\n", "退役球员", JOptionPane.PLAIN_MESSAGE);
			pl.pd.retirePlayer(name);
			log4j.info(name+"球员退役成功");
		} else if (e.getSource() == zh) {
			String name = JOptionPane.showInputDialog(null, "请输入转会球员的名字：\n", "球员转会", JOptionPane.PLAIN_MESSAGE);
			String teamName = JOptionPane.showInputDialog(null, "请输入转会球员转入球队名：\n", "球员转会", JOptionPane.PLAIN_MESSAGE);
			pl.pd.changeTeam(name, teamName);
			log4j.info(name+"球员转会成功");
		} else if (e.getSource() == cx) {
			String name = JOptionPane.showInputDialog(null, "请输入需要查询球员的名字：\n", "查看球员信息", JOptionPane.PLAIN_MESSAGE);
			Vector datitle=new Vector();
			datitle=pl.pd.findPlayer(name);//查询到的球员信息
			if(datitle.isEmpty()) {
				JOptionPane.showMessageDialog(null, "未查询到该球员！！！");
			}
			else {
				findPlayer(datitle);	
			log4j.info(name+"球员查询成功");
			}
			
		}

	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	    /* (非 Javadoc)
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

	
	    /* (非 Javadoc)
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
