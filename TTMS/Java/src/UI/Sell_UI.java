package ui;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Basic.Default;
import Basic.Schedule;
import Basic.User;
import db.ManageSchedule_DB;
import db.ManageTicket_DB;
import server.ManageSeat_Sev;

public class Sell_UI implements MouseListener {
	Schedule schedule;
	User login_user;//登陆进的用户
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();

	private JDialog sell_JD;//本窗口
	private myJPanel_UI seat_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_Seat());//設置背景   主面板
	private myJPanel_UI sell_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_Sell_Seat());//設置背景   主面板
	private String[][] detailed_Data;//用户数据
	private DefaultTableModel detailed_Model = new DefaultTableModel(detailed_Data,new String[]{"Row", "Col"}){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable detailed_JT = new JTable(detailed_Model);//用户表格
	private JScrollPane detailed_JS = new JScrollPane();//設置背景   主面板
	private JButton produce = new JButton();
	private JButton close = new JButton();
	private JButton[][] seat_jb=new JButton[10][15];//座位
	private int[][] ticket_state;
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置

	public Sell_UI(JDialog st_JF, Schedule schedule, User Conductor ){
		this.schedule = schedule;
		sell_JD = new JDialog(st_JF, true);
		login_user = Conductor;
		this.Init();//初始化
		this.gettTicketState();
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
		info.setText(login_user.getName());
		//	info.setFont(new Font("serif",0,20));
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
			
		detailed_JS.getViewport().add(detailed_JT);
		sell_Jp.add(detailed_JS);
		sell_Jp.add(produce);
		sell_Jp.add(close);
		sell_Jp.add(info);		
		sell_Jp.add(datetime);
		sell_Jp.add(seat_Jp);
		sell_JD.add(sell_Jp);
	
		sell_JD.setVisible(true);//
	}


	private void listener() {
		sell_JD.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = sell_JD.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				sell_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		sell_JD.addMouseListener(this);	
		detailed_JT.addMouseListener(this);
		produce.addMouseListener(this);
		close.addMouseListener(this);
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				//座位名为行列  如1空格2表示为1行2列
				seat_jb[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton jt = (JButton)e.getSource();
						String string=new String(((JButton)e.getSource()).getIcon().toString());
						String[] name = jt.getName().split(" ");
						
						if(string.equals(Default.Get_Path_Img_Cond_Ticket(0))){
							jt.setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(1)));
							detailed_Model.addRow(name);
						}else if(string.equals(Default.Get_Path_Img_Cond_Ticket(1))){
							jt.setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(0)));
							for(int i=0 ; i<detailed_Model.getRowCount() ; i++){
								if(name[0].equals(detailed_Model.getValueAt(i, 0)) && name[1].equals(detailed_Model.getValueAt(i,1))){
									detailed_Model.removeRow(i);
								}
							}
						}
					}
				});
			}
		}
	}


	private void setSize() {
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				seat_jb[i][j].setSize(35, 35);
			}
		}
		info.setBounds(944, 25, 155, 25);
		datetime.setBounds(800, 58, 200, 20);
		
		detailed_JS.setBounds(827,160, 187, 289);
		close.setBounds(Default.Get_Window_X()-25,0, 25, 25);
		produce.setBounds(829,497, 185, 38);
	}
	private void gettTicketState(){
		ticket_state = ManageTicket_DB.get_ticket_state(schedule.getSchedule_id());
		for(int i=1 ; i<=9; i++){
			for(int j=1 ; j<=14 ; j++){
				//座位名为行列  如12表示为1行2列
				if(ticket_state[i][j]==0){//待选中
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(0)));
				}else if(ticket_state[i][j]==1 || ticket_state[i][j]==2){//卖出或被锁定
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(2)));
				}else{//不存在
//					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(3)));
				}
			}
		}
	}
	private void Init() {
		Init_UI.JDialogInit(sell_JD, "sell_JD",Default.Get_Window_X(), Default.Get_Window_Y());
		Init_UI.JPanelInit(sell_Jp, "sell_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(seat_Jp, "seat_Jp",20,84,Default.Get_Window_X()-264,Default.Get_Window_Y()-178);
		seat_Jp.setLayout(new GridLayout(10,16));//网格布局
		Init_UI.JScrollPaneInit(detailed_JS,"detailed_JS");
		Init_UI.JTableInit(detailed_JT,"detailed_JT");
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//日期进入不为手
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手
		
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				//座位名为行列  如12表示为1行2列
				seat_jb[i][j] = new JButton();
				Init_UI.JButtonInit(seat_jb[i][j],String.valueOf(i)+" "+String.valueOf(j));
				seat_Jp.add(seat_jb[i][j]);
				if(j==3 || j==11){
					JButton tem = new JButton();
					Init_UI.JButtonInit(tem, "tem");
					seat_Jp.add(tem);
				}
			}
			if(i==4){
				for(int k=1 ; k<=16 ; k++){
					JButton tem = new JButton();
					Init_UI.JButtonInit(tem, "tem");
					seat_Jp.add(tem);
				}
			}
		}
		Init_UI.JButtonInit(produce , "produce");
		Init_UI.JButtonInit(close, "close");
		
	}
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("produce".equals(e.getComponent().getName())){
			if(detailed_Model.getRowCount()>0){
				ArrayList<String[]> ticket = new ArrayList<String[]>();
				for(int i=0 ; i<detailed_Model.getRowCount() ; i++){
					ticket.add(new String[]{String.valueOf(detailed_Model.getValueAt(i, 0)),String.valueOf(detailed_Model.getValueAt(i, 1))});
					System.out.println("售票的座位"+String.valueOf(detailed_Model.getValueAt(i, 0))+"  "+ String.valueOf(detailed_Model.getValueAt(i, 1)));
				}
				int[] ticket_id = ManageTicket_DB.get_ticket_id(schedule.getSchedule_id(),ticket);
				for(int i=0 ; i< ticket_id.length ; i++){
					System.out.println("票"+ticket_id[i]);
				}
				if(ManageTicket_DB.Lock_ticket(ticket_id,1)){
					new Order_UI(sell_JD,schedule, login_user,ticket_id);
					this.gettTicketState();
					while(detailed_Model.getRowCount()>0){
						detailed_Model.removeRow(detailed_Model.getRowCount()-1);
					 } 
				}else{
					JOptionPane.showMessageDialog(null, "出票失败,票已被加锁或被卖出,请刷新重试");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "请选择座位");  
			}
		}else if("close".equals(e.getComponent().getName())){
			sell_JD.dispose();
		}
	}
	public void mousePressed(MouseEvent e) {
		// 当鼠标按下的时候获得窗口当前的位置
		if("sell_JD".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}		
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		if("sell_Jp".equals(e.getComponent().getName())){//进入组件更新时间
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}

}
