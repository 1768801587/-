package GUI界面;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import DAO.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import 其他.music;


// TODO: Auto-generated Javadoc
/**
 * The Class thridGui.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020年7月4日
 */
public class thridGui extends JFrame implements ActionListener, FocusListener,WindowListener {
    
    /** The wenlab. */
    //冒号前面的标签,第一个是界面大标题  某某队
	private JLabel wenlab[]=new JLabel[7];
	
	/** The dalab. */
	//冒号后面的标签
	private JLabel dalab[]=new JLabel[7];
	
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
	
	/** The menu item import. */
	private JMenuItem menuItemImport=new JMenuItem("导出球员");
	
	/** The playerlist. */
	private JButton playerlist;
	
	/** The connection. */
	connection connection;
	
	/** The team DAO. */
	private teamDAO teamDAO;
	
	/** The player DAO. */
	private playerDAO playerDAO;
	
    /** The team name. */
    private String teamName;
    
    /** The rs. */
    private ResultSet rs=null;//球队
    
    /** The sg. */
    private secondGui sg;//原来页面
    
    /** The col. */
    Vector col=new Vector();
    
    /** The log 4 j. */
    Logger log4j=Logger.getLogger(this.getClass());//日志信息
	
	/**
	 * Instantiates a new thrid gui.
	 *
	 * @param sg the sg
	 * @param teamName the team name
	 * @param connection the connection
	 */
	public thridGui(secondGui sg,String teamName,connection connection) {//传入第二个页面的类，以至于可以在本类设置上个类是否锁定
		this.connection=connection;
		teamDAO=new teamDAO(connection,teamName);
		playerDAO=new playerDAO(connection,teamName);
		this.sg=sg;
		this.teamName=teamName;
		// 设置窗体名字
		super.setTitle(teamName);
		super.setBounds(0, 0, 800, 700);
		super.setLocation(500, 200);

		super.getContentPane().add(new JLabel(new ImageIcon("picture\\LeBron4.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);

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
		menuTool.add(menuItemImport);
		// 给菜单项注册监听器
		menuItemIntroduction.addActionListener(this);
		menuItemExitLogin.addActionListener(this);
		menuItemExitSystem.addActionListener(this);
		menuItemImport.addActionListener(this);
		super.addWindowListener(this);
		// 添加组件
		// 添加标签组件
		wenlab[0] = new JLabel(teamName+"队");
		wenlab[0].setBounds(275, 30, 250, 40);
		wenlab[0].setFont(new Font("楷体", Font.BOLD, 40));
		wenlab[0].setForeground(Color.blue);
		this.getLayeredPane().add(wenlab[0]);
		
		wenlab[1]=new JLabel("球队名称：");
		setJLabel(wenlab[1],20,75);
		dalab[1]=new JLabel();
		setJLabel(dalab[1],110,75);
		
		wenlab[2]=new JLabel("成立年份：");
		setJLabel(wenlab[2],20,115);
		dalab[2]=new JLabel();
		setJLabel(dalab[2],110,115);
		
		wenlab[3]=new JLabel("球员数量：");
		setJLabel(wenlab[3],20,155);
		dalab[3]=new JLabel();
		setJLabel(dalab[3],110,155);
		
		wenlab[4]=new JLabel("主场位置：");
		setJLabel(wenlab[4],20,195);
		dalab[4]=new JLabel();
		setJLabel(dalab[4],110,195);
		
		wenlab[5]=new JLabel("总冠军数量：");
		setJLabel(wenlab[5],20,235);
		dalab[5]=new JLabel();
		setJLabel(dalab[5],120,235);
		
		wenlab[6]=new JLabel("球队主教练：");
		setJLabel(wenlab[6],20,275);
		dalab[6]=new JLabel();
		dalab[6].setBounds(120, 275, 180, 20);
		dalab[6].setFont(new Font("楷体", Font.BOLD, 18));
		dalab[6].setForeground(Color.white);
		this.getLayeredPane().add(dalab[6]);
		
		playerlist=new JButton("球员列表");
		playerlist.setBounds(20, 320, 120, 30);
		playerlist.setFont(new Font("楷体", Font.BOLD, 20));
		playerlist.setForeground(Color.pink);
		this.getLayeredPane().add(playerlist);
		playerlist.addActionListener(this);
		
		showTeamInfo();//显示球队信息
		super.setVisible(true);
	}
	
	/**
	 * Sets the col.
	 */
	public void setCol() {
		col.add("编号");
		col.add("姓名");
		col.add("年龄");
		col.add("选秀年");
		col.add("位置");
		col.add("身高");
		col.add("球队名");
		col.add("上赛季场均得分");
		col.add("场均出场时间");
		col.add("现役/历史");
		col.add("球衣号码");// 获得表的各列名称
	}
    
    /**
     * Sets the J label.
     *
     * @param lab the lab
     * @param a the a
     * @param b the b
     */
    public void setJLabel(JLabel lab,int a,int b) {
    	lab.setBounds(a, b, 120, 20);
    	lab.setFont(new Font("楷体", Font.BOLD, 18));
    	lab.setForeground(Color.blue);
		this.getLayeredPane().add(lab);
    }
	
	/**
	 * Show team info.
	 */
	public void showTeamInfo() {//显示球队信息
         String [] teaminfo=teamDAO.teamInfo();
         for(int i=1;i<7;i++)
         dalab[i].setText(teaminfo[i]);
	}
	
	/**
	 * Import player.
	 *
	 * @param pd the pd
	 */
	public void importPlayer(playerDAO pd) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File("F:\\code\\JAVA\\球员管理系统\\导出文件\\"+teamName+".xls"));
			WritableSheet sheet=workbook.createSheet("sheet标签名称",0);
			setCol();//增加各列的列名
			//单层for循环加入各列的列名
			for(int i=0;i<11;i++) {
				jxl.write.Label l=new jxl.write.Label(i,0,""+col.get(i));
				try {
					sheet.addCell(l);
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					log4j.error("错误信息:"+e1.getMessage());
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log4j.error("错误信息:"+e1.getMessage());
				}
			}
			//双层for循环导出球员数据
			for(int i=1;i<7;i++) {
				Vector line=new Vector();
				line=(Vector) pd.rr.get(i-1);
				for(int j=0;j<11;j++) {
					jxl.write.Label l=new jxl.write.Label(j,i,""+line.get(j));
					try {
						sheet.addCell(l);
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			workbook.write();//导出数据完成后，要写入文件
			try {
				workbook.close();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			JOptionPane.showMessageDialog(null, "简单说明：球队页面可查看相关信息和球员信息");
		} else if (e.getSource() == menuItemExitLogin) {
			super.dispose();
			sg.dispose();
			new startGui().show();
		} else if (e.getSource() == menuItemExitSystem) {
			JOptionPane.showMessageDialog(null, "成功退出程序");
			System.exit(0);
		}else if(e.getSource()==menuItemImport) {
			playerDAO pd=new playerDAO(connection);
			pd.playerInfo(teamName);
			importPlayer(pd);
			JOptionPane.showMessageDialog(null, "导出文件到\"F:\\code\\JAVA\\球员管理系统\\导出文件\"成功！！！");
		}
		if(e.getSource()==playerlist) {//显示球员列表
			new playerListGui(connection,teamName);
			log4j.info(teamName+"队球员列表展出正常");
		}

	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowOpened(WindowEvent e) {
		sg.setEnabled(false);//锁定原来的窗口
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowClosing(WindowEvent e) {
		sg.setEnabled(true);//恢复原来的窗口
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowClosed(WindowEvent e) {
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
