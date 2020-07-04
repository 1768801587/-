package GUI����;

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
import ����.music;


// TODO: Auto-generated Javadoc
/**
 * The Class thridGui.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020��7��4��
 */
public class thridGui extends JFrame implements ActionListener, FocusListener,WindowListener {
    
    /** The wenlab. */
    //ð��ǰ��ı�ǩ,��һ���ǽ�������  ĳĳ��
	private JLabel wenlab[]=new JLabel[7];
	
	/** The dalab. */
	//ð�ź���ı�ǩ
	private JLabel dalab[]=new JLabel[7];
	
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
	
	/** The menu item import. */
	private JMenuItem menuItemImport=new JMenuItem("������Ա");
	
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
    private ResultSet rs=null;//���
    
    /** The sg. */
    private secondGui sg;//ԭ��ҳ��
    
    /** The col. */
    Vector col=new Vector();
    
    /** The log 4 j. */
    Logger log4j=Logger.getLogger(this.getClass());//��־��Ϣ
	
	/**
	 * Instantiates a new thrid gui.
	 *
	 * @param sg the sg
	 * @param teamName the team name
	 * @param connection the connection
	 */
	public thridGui(secondGui sg,String teamName,connection connection) {//����ڶ���ҳ����࣬�����ڿ����ڱ��������ϸ����Ƿ�����
		this.connection=connection;
		teamDAO=new teamDAO(connection,teamName);
		playerDAO=new playerDAO(connection,teamName);
		this.sg=sg;
		this.teamName=teamName;
		// ���ô�������
		super.setTitle(teamName);
		super.setBounds(0, 0, 800, 700);
		super.setLocation(500, 200);

		super.getContentPane().add(new JLabel(new ImageIcon("picture\\LeBron4.jpg")));
		super.getLayeredPane().setLayout(null);
		super.getLayeredPane().setOpaque(false);

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
		menuTool.add(menuItemImport);
		// ���˵���ע�������
		menuItemIntroduction.addActionListener(this);
		menuItemExitLogin.addActionListener(this);
		menuItemExitSystem.addActionListener(this);
		menuItemImport.addActionListener(this);
		super.addWindowListener(this);
		// ������
		// ��ӱ�ǩ���
		wenlab[0] = new JLabel(teamName+"��");
		wenlab[0].setBounds(275, 30, 250, 40);
		wenlab[0].setFont(new Font("����", Font.BOLD, 40));
		wenlab[0].setForeground(Color.blue);
		this.getLayeredPane().add(wenlab[0]);
		
		wenlab[1]=new JLabel("������ƣ�");
		setJLabel(wenlab[1],20,75);
		dalab[1]=new JLabel();
		setJLabel(dalab[1],110,75);
		
		wenlab[2]=new JLabel("������ݣ�");
		setJLabel(wenlab[2],20,115);
		dalab[2]=new JLabel();
		setJLabel(dalab[2],110,115);
		
		wenlab[3]=new JLabel("��Ա������");
		setJLabel(wenlab[3],20,155);
		dalab[3]=new JLabel();
		setJLabel(dalab[3],110,155);
		
		wenlab[4]=new JLabel("����λ�ã�");
		setJLabel(wenlab[4],20,195);
		dalab[4]=new JLabel();
		setJLabel(dalab[4],110,195);
		
		wenlab[5]=new JLabel("�ܹھ�������");
		setJLabel(wenlab[5],20,235);
		dalab[5]=new JLabel();
		setJLabel(dalab[5],120,235);
		
		wenlab[6]=new JLabel("�����������");
		setJLabel(wenlab[6],20,275);
		dalab[6]=new JLabel();
		dalab[6].setBounds(120, 275, 180, 20);
		dalab[6].setFont(new Font("����", Font.BOLD, 18));
		dalab[6].setForeground(Color.white);
		this.getLayeredPane().add(dalab[6]);
		
		playerlist=new JButton("��Ա�б�");
		playerlist.setBounds(20, 320, 120, 30);
		playerlist.setFont(new Font("����", Font.BOLD, 20));
		playerlist.setForeground(Color.pink);
		this.getLayeredPane().add(playerlist);
		playerlist.addActionListener(this);
		
		showTeamInfo();//��ʾ�����Ϣ
		super.setVisible(true);
	}
	
	/**
	 * Sets the col.
	 */
	public void setCol() {
		col.add("���");
		col.add("����");
		col.add("����");
		col.add("ѡ����");
		col.add("λ��");
		col.add("���");
		col.add("�����");
		col.add("�����������÷�");
		col.add("��������ʱ��");
		col.add("����/��ʷ");
		col.add("���º���");// ��ñ�ĸ�������
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
    	lab.setFont(new Font("����", Font.BOLD, 18));
    	lab.setForeground(Color.blue);
		this.getLayeredPane().add(lab);
    }
	
	/**
	 * Show team info.
	 */
	public void showTeamInfo() {//��ʾ�����Ϣ
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
			WritableWorkbook workbook = Workbook.createWorkbook(new File("F:\\code\\JAVA\\��Ա����ϵͳ\\�����ļ�\\"+teamName+".xls"));
			WritableSheet sheet=workbook.createSheet("sheet��ǩ����",0);
			setCol();//���Ӹ��е�����
			//����forѭ��������е�����
			for(int i=0;i<11;i++) {
				jxl.write.Label l=new jxl.write.Label(i,0,""+col.get(i));
				try {
					sheet.addCell(l);
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					log4j.error("������Ϣ:"+e1.getMessage());
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log4j.error("������Ϣ:"+e1.getMessage());
				}
			}
			//˫��forѭ��������Ա����
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
			workbook.write();//����������ɺ�Ҫд���ļ�
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
			JOptionPane.showMessageDialog(null, "��˵�������ҳ��ɲ鿴�����Ϣ����Ա��Ϣ");
		} else if (e.getSource() == menuItemExitLogin) {
			super.dispose();
			sg.dispose();
			new startGui().show();
		} else if (e.getSource() == menuItemExitSystem) {
			JOptionPane.showMessageDialog(null, "�ɹ��˳�����");
			System.exit(0);
		}else if(e.getSource()==menuItemImport) {
			playerDAO pd=new playerDAO(connection);
			pd.playerInfo(teamName);
			importPlayer(pd);
			JOptionPane.showMessageDialog(null, "�����ļ���\"F:\\code\\JAVA\\��Ա����ϵͳ\\�����ļ�\"�ɹ�������");
		}
		if(e.getSource()==playerlist) {//��ʾ��Ա�б�
			new playerListGui(connection,teamName);
			log4j.info(teamName+"����Ա�б�չ������");
		}

	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowOpened(WindowEvent e) {
		sg.setEnabled(false);//����ԭ���Ĵ���
		
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowClosing(WindowEvent e) {
		sg.setEnabled(true);//�ָ�ԭ���Ĵ���
		
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowClosed(WindowEvent e) {
		
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	    */
	    
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	    /* (�� Javadoc)
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
