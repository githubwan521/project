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

//售票界面
public class SellTicket_UI  implements MouseListener {
	User login_user;//登陆进的用户
	JFrame fJFrame;//父窗口
	//主窗口
	private JLabel sellticket= new JLabel("售        票",JLabel.CENTER);
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();
	private JButton search = new JButton();
	private JButton sell = new JButton();

    private myJPanel_UI st_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_SellTicket());//設置背景   主面板
	private String[][] st_Data = ManageSchedule_Sev.get_con_schedule_list();
	private DefaultTableModel st_Model = new DefaultTableModel(st_Data,Default.Get_Con_Schedule_ColumnNames()){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable st_JT = new JTable(st_Model);//用户表格
	private JScrollPane st_JS = new JScrollPane();//設置背景   主面板
	
	
	private JButton close = new JButton();
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private JDialog st_JF;
		
	public SellTicket_UI(JFrame con_JF, User Conductor){
		fJFrame = con_JF;
		login_user = Conductor;
		st_JF = new JDialog(con_JF,true);
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
//		fJFrame.setVisible(false);
		
		//主窗口
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
	//组件的监听
	public void listener(){
		//主窗口
		sellticket.addMouseListener(this);
		info.addMouseListener(this);
		search.addMouseListener(this);
		close.addMouseListener(this);
		sell.addMouseListener(this);
		st_JT.addMouseListener(this);
		st_JS.addMouseListener(this);
		
		st_JF.addMouseListener(this);
		st_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = st_JF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				st_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//所有组件的初始化
	public void Init(){
		//主窗口
		Init_UI.JDialogInit(st_JF,"st_JF",Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(st_Jp, "st_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JLableInit(sellticket, "sellticket");
		sellticket.setFont(new Font("serif",1,42));
		sellticket.setForeground(Color.red);
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//日期进入不为手

		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手
		Init_UI.JTextFieldInit(search_Box, "search_Box");
		Init_UI.JButtonInit(search, "search");
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(sell, "sell");
		Init_UI.JScrollPaneInit(st_JS,"st_JS");
		Init_UI.JTableInit(st_JT,"st_JT");
////		表格排序
//		RowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<TableModel>(st_JT.getModel());
//		st_JT.setRowSorter(sorter);

	}
	//设置所有组件的大小与起始位置
	public void setSize(){
		//主窗口
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
	
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		 if("st_JT".equals(e.getComponent().getName())){
		 
		}else if("sell".equals(e.getComponent().getName())){
			int selectedRow = st_JT.getSelectedRow(); // 获得选中行索引
			if(selectedRow!=-1){
				String[] str=ManageSchedule_Sev.get_schedule(Integer.valueOf(st_JT.getValueAt(selectedRow, 0).toString()).intValue());//根据id查找演出计划
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

	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
		// 当鼠标按下的时候获得窗口当前的位置
		if("st_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  	
	}

	public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作 
	}

	public void mouseExited(MouseEvent e) {
		if("st_Jp".equals(e.getComponent().getName())){//进入组件更新时间
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
	public static void main(String[] args){
		User user = new User();
		user.setId(3);
		user.setIdentity("售票员");
		user.setName("606");
		new SellTicket_UI(null,user);
	}
}


