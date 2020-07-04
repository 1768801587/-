package GUI����;

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
 * @date 2020��7��4��
 */

public class playerListGui extends JFrame implements ActionListener, FocusListener {
	
	/** The table. */
	private JTable table;// ��Ա�б�
	 
 	/** The title. */
 	Vector title = new Vector();// �����ÿ�е���������
	
	/** The data. */
	private Vector data = new Vector();// ���������
	
	/** The log 4 j. */
	Logger log4j=Logger.getLogger(this.getClass());//��־��Ϣ
	
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
		pd.playerInfo(teamName);//�洢�öӵ���Ա��Ϣ
		data = pd.rr;// ���������

		this.teamName = teamName;
		super.setTitle(teamName);
		super.setBounds(0, 0, 640, 300);
		super.setLocation(700, 350);

		zj = new JButton("׷��");
		gx = new JButton("����");
		ty = new JButton("����");
		zh=new JButton("ת��");
		zj.addActionListener(this);// Ϊ��ť��Ӽ�����
		gx.addActionListener(this);
		ty.addActionListener(this);
		zh.addActionListener(this);
		JPanel ps = new JPanel();
		ps.setLayout(new FlowLayout());
		ps.add(zj);
		ps.add(ty);
		ps.add(gx);
        ps.add(zh);
		con = super.getContentPane();// ��ô�������
		con.setLayout(new BorderLayout(10, 10));
		
		table = new JTable(data, title);// ���ñ��
		setColumn();// ����ÿ�еĿ��
		con.add(new JScrollPane(table), BorderLayout.CENTER);// �������м�����������ı��

		con.add(ps, BorderLayout.SOUTH);// ���뺬�а�ť�����
		super.setVisible(true);
	}
    
    /**
     * Title add.
     */
    public void titleAdd() {
    	title.add("���:");
		title.add("����:");
		title.add("����:");
		title.add("ѡ����:");
		title.add("λ��:");
		title.add("���:");
		title.add("�����:");
		title.add("�����������÷�:");
		title.add("��������ʱ��:");
		title.add("����/��ʷ:");
		title.add("���º���:");// ��ñ�ĸ�������
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
    
    /**
     * Zj button.
     */
    public void zjButton() {
    	JFrame zjqy = new JFrame("׷�Ӽ�¼");
		zjqy.setSize(400, 500);
		zjqy.setLocation(700, 300);
		zjqy.setLayout(new GridLayout(12, 2, 5, 5));
		JButton sure = new JButton("׷��");
		JLabel pi[] = new JLabel[11];
		JTextField pik[] = new JTextField[11];
		for (int i = 0; i < 10; i++) {
			pi[i] = new JLabel((String) title.get(i + 1));
			pik[i] = new JTextField();
			zjqy.add(pi[i]);
			zjqy.add(pik[i]);
		}
		pi[10] = new JLabel("�������");
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
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == zj) {
			zjButton();
			log4j.info(teamName+"��׷����Ա�ɹ�");
		} else if (e.getSource() == gx) {
			data.clear();
			pd.playerInfo(teamName);
			data = pd.rr;
			this.repaint();
			con.repaint();
			table.updateUI();
			
			JOptionPane.showMessageDialog(null, "�������!!!");
		}else if(e.getSource()==ty) {
			String name = JOptionPane.showInputDialog(null, "������������Ա�����֣�\n", "������Ա", JOptionPane.PLAIN_MESSAGE);
            pd.retirePlayer(name);
            log4j.info(teamName+"����Ա���۳ɹ�");
		}else if(e.getSource()==zh) {
			String name = JOptionPane.showInputDialog(null, "������ת����Ա�����֣�\n", "��Աת��", JOptionPane.PLAIN_MESSAGE);
			String teamName = JOptionPane.showInputDialog(null, "������ת����Աת���������\n", "��Աת��", JOptionPane.PLAIN_MESSAGE);
			pd.changeTeam(name,teamName);
			log4j.info(teamName+"����Աת��ɹ�");
		}

	}

}
