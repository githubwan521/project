package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Basic.Default;
import Basic.Schedule;
import Basic.User;
import db.ManageTicket_DB;
import server.ManagePlay_Sev;
import server.ManageSchedule_Sev;
import sun.security.krb5.internal.Ticket;

//��Ʊ����
public class SellTicket_UI  implements MouseListener {
	User login_user;//��½�����û�
	JFrame fJFrame;//������
	//������
	private JLabel sellticket= new JLabel("��        Ʊ",JLabel.CENTER);
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();
	private JButton search = new JButton();
	private JButton sell = new JButton();

    private myJPanel_UI st_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_SellTicket());//�O�ñ���   �����
	private String[][] st_Data = ManageSchedule_Sev.get_con_schedule_list();
	private DefaultTableModel st_Model = new DefaultTableModel(st_Data,Default.Get_Con_Schedule_ColumnNames()){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable st_JT = new JTable(st_Model);//�û����
	private JScrollPane st_JS = new JScrollPane();//�O�ñ���   �����
	
	
	private JButton close = new JButton();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	private JDialog st_JF;
		
	public SellTicket_UI(JFrame con_JF, User Conductor){
		fJFrame = con_JF;
		login_user = Conductor;
		st_JF = new JDialog(con_JF,true);
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
//		fJFrame.setVisible(false);
		
		//������
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		st_Jp.add(sellticket);
		st_Jp.add(info);		
		st_Jp.add(datetime);
		st_Jp.add(search_Box);
		st_Jp.add(search);
		st_Jp.add(close);
		st_Jp.add(sell);
		st_JS.getViewport().add(st_JT);
		st_Jp.add(st_JS);
		st_JF.add(st_Jp);
		st_JF.setVisible(true);
	}
	//����ļ���
	public void listener(){
		//������
		sellticket.addMouseListener(this);
		info.addMouseListener(this);
		search.addMouseListener(this);
		close.addMouseListener(this);
		sell.addMouseListener(this);
		st_JT.addMouseListener(this);
		st_JS.addMouseListener(this);
		
		st_JF.addMouseListener(this);
		st_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = st_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				st_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//��������ĳ�ʼ��
	public void Init(){
		//������
		Init_UI.JDialogInit(st_JF,"st_JF",Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(st_Jp, "st_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JLableInit(sellticket, "sellticket");
		sellticket.setFont(new Font("serif",1,42));
		sellticket.setForeground(Color.red);
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//���ڽ��벻Ϊ��

		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JTextFieldInit(search_Box, "search_Box");
		Init_UI.JButtonInit(search, "search");
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(sell, "sell");
		Init_UI.JScrollPaneInit(st_JS,"st_JS");
		Init_UI.JTableInit(st_JT,"st_JT");
////		�������
//		RowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<TableModel>(st_JT.getModel());
//		st_JT.setRowSorter(sorter);

	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		//������
//		sellticket.setBounds(400, 40, 200, 50);
//		info.setBounds(830, 10, 150, 20);
//		datetime.setBounds(825, 35, 200, 20);
//		search.setBounds(940, 91, 28, 28);
//		search_Box.setBounds(780, 92, 160, 26);
//		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
//		sell.setBounds(780, 392, 160, 26);
//		st_JS.setBounds(30,120,Default.Get_Window_X()-260,Default.Get_Window_Y()-150);
	
		sellticket.setBounds(280, 110, 200, 50);
		info.setBounds(944, 25, 155, 25);
		datetime.setBounds(830, 64, 200, 20);
		search.setBounds(962, 113, 28, 28);
		search_Box.setBounds(790, 114, 173, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
		sell.setBounds(769, 525, 188, 44);
		st_JS.setBounds(62,193,Default.Get_Window_X()-408,Default.Get_Window_Y()-237);

	}
	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		 if("st_JT".equals(e.getComponent().getName())){
		 
		}else if("sell".equals(e.getComponent().getName())){
			int selectedRow = st_JT.getSelectedRow(); // ���ѡ��������
			if(selectedRow!=-1){
				String[] str=ManageSchedule_Sev.get_schedule(Integer.valueOf(st_JT.getValueAt(selectedRow, 0).toString()).intValue());//����id�����ݳ��ƻ�
				Schedule schedule=new Schedule(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2]),str[3],str[4]);
				new Sell_UI(st_JF, schedule, login_user);
			}
		}else if("sellticket".equals(e.getComponent().getName())){
			st_JT.setModel(st_Model);
		}else if("search".equals(e.getComponent().getName())){
			st_JT.setModel(new DefaultTableModel(ManageSchedule_Sev.get_con_search_schedule_list(String.valueOf(search_Box.getText())),Default.Get_Con_Schedule_ColumnNames()){
				public boolean isCellEditable(int row,int column){ 
					return false;
				}  
			});
		}else if("info".equals(e.getComponent().getName())){
//			new ModifyPassword(st_JF, login_user);
		}else if("close".equals(e.getComponent().getName())){
//			 fJFrame.setVisible(true);
			 st_JF.dispose();
//			 System.exit(0);
		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("st_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
	}

	public void mouseExited(MouseEvent e) {
		if("st_Jp".equals(e.getComponent().getName())){//�����������ʱ��
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
	public static void main(String[] args){
		User user = new User();
		user.setId(3);
		user.setIdentity("��ƱԱ");
		user.setName("606");
		new SellTicket_UI(null,user);
	}
}


