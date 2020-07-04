package GUI界面;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import DAO.connection;
import DAO.playerDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class playerListGui.
 *
 * @author yujiaqing
 * @version  v1.0
 * @date 2020年7月4日
 */

public class playerListGui extends JFrame implements ActionListener, FocusListener {
	
	/** The table. */
	private JTable table;// 球员列表
	 
 	/** The title. */
 	Vector title = new Vector();// 表格中每列的列名集合
	
	/** The data. */
	private Vector data = new Vector();// 表格中数据
	
	/** The log 4 j. */
	Logger log4j=Logger.getLogger(this.getClass());//日志信息
	
	/** The zj. */
	private JButton zj;
	
	/** The ty. */
	private JButton ty;
	
	/** The gx. */
	private JButton gx;
	
	/** The zh. */
	private JButton zh;
	
	/** The team name. */
	private String teamName;
	
	/** The pd. */
	playerDAO pd;
	
	/** The con. */
	private Container con;
    
    /**
     * Instantiates a new player list gui.
     *
     * @param connection the connection
     */
    public playerListGui(connection connection) {
    	pd = new playerDAO(connection);
    	titleAdd();
    }
	
	/**
	 * Instantiates a new player list gui.
	 *
	 * @param connection the connection
	 * @param teamName the team name
	 */
	public playerListGui(connection connection, String teamName) {
		titleAdd();

		pd = new playerDAO(connection, teamName);
		pd.playerInfo(teamName);//存储该队的球员信息
		data = pd.rr;// 获得行数据

		this.teamName = teamName;
		super.setTitle(teamName);
		super.setBounds(0, 0, 640, 300);
		super.setLocation(700, 350);

		zj = new JButton("追加");
		gx = new JButton("更新");
		ty = new JButton("退役");
		zh=new JButton("转会");
		zj.addActionListener(this);// 为按钮添加监听器
		gx.addActionListener(this);
		ty.addActionListener(this);
		zh.addActionListener(this);
		JPanel ps = new JPanel();
		ps.setLayout(new FlowLayout());
		ps.add(zj);
		ps.add(ty);
		ps.add(gx);
        ps.add(zh);
		con = super.getContentPane();// 获得窗体容器
		con.setLayout(new BorderLayout(10, 10));
		
		table = new JTable(data, title);// 设置表格
		setColumn();// 设置每列的宽度
		con.add(new JScrollPane(table), BorderLayout.CENTER);// 向容器中加入带滚动条的表格

		con.add(ps, BorderLayout.SOUTH);// 加入含有按钮的面板
		super.setVisible(true);
	}
    
    /**
     * Title add.
     */
    public void titleAdd() {
    	title.add("编号:");
		title.add("姓名:");
		title.add("年龄:");
		title.add("选秀年:");
		title.add("位置:");
		title.add("身高:");
		title.add("球队名:");
		title.add("上赛季场均得分:");
		title.add("场均出场时间:");
		title.add("现役/历史:");
		title.add("球衣号码:");// 获得表的各列名称
    }
	
	/**
	 * Sets the column.
	 */
	public void setColumn() {
		TableColumn column[] = new TableColumn[11];
		for (int i = 0; i < 11; i++) {
			column[i] = new TableColumn();
			column[i] = table.getColumnModel().getColumn(i);
		}
		column[0].setPreferredWidth(35);
		column[1].setPreferredWidth(105);
		column[2].setPreferredWidth(35);
		column[3].setPreferredWidth(47);
		column[4].setPreferredWidth(53);
		column[5].setPreferredWidth(41);
		column[6].setPreferredWidth(59);
		column[7].setPreferredWidth(102);
		column[8].setPreferredWidth(94);
		column[9].setPreferredWidth(68);
		column[10].setPreferredWidth(68);
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
    
    /**
     * Zj button.
     */
    public void zjButton() {
    	JFrame zjqy = new JFrame("追加记录");
		zjqy.setSize(400, 500);
		zjqy.setLocation(700, 300);
		zjqy.setLayout(new GridLayout(12, 2, 5, 5));
		JButton sure = new JButton("追加");
		JLabel pi[] = new JLabel[11];
		JTextField pik[] = new JTextField[11];
		for (int i = 0; i < 10; i++) {
			pi[i] = new JLabel((String) title.get(i + 1));
			pik[i] = new JTextField();
			zjqy.add(pi[i]);
			zjqy.add(pik[i]);
		}
		pi[10] = new JLabel("出生年份");
		pik[10] = new JTextField();
		zjqy.add(pi[10]);
		zjqy.add(pik[10]);
		zjqy.add(sure);
		zjqy.setVisible(true);
		sure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = pik[1].getText();
				Vector re = new Vector();
				for (int i = 0; i < 11; i++)
					re.add((pik[i].getText()));
				pd.insertPlayer(re);
				zjqy.dispose();
			}

		});
    }
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == zj) {
			zjButton();
			log4j.info(teamName+"队追加球员成功");
		} else if (e.getSource() == gx) {
			data.clear();
			pd.playerInfo(teamName);
			data = pd.rr;
			this.repaint();
			con.repaint();
			table.updateUI();
			
			JOptionPane.showMessageDialog(null, "更新完成!!!");
		}else if(e.getSource()==ty) {
			String name = JOptionPane.showInputDialog(null, "请输入退役球员的名字：\n", "退役球员", JOptionPane.PLAIN_MESSAGE);
            pd.retirePlayer(name);
            log4j.info(teamName+"队球员退役成功");
		}else if(e.getSource()==zh) {
			String name = JOptionPane.showInputDialog(null, "请输入转会球员的名字：\n", "球员转会", JOptionPane.PLAIN_MESSAGE);
			String teamName = JOptionPane.showInputDialog(null, "请输入转会球员转入球队名：\n", "球员转会", JOptionPane.PLAIN_MESSAGE);
			pd.changeTeam(name,teamName);
			log4j.info(teamName+"队球员转会成功");
		}

	}

}
