package ui;

import Basic.Default;
import Basic.User;
import UI.myJPanel_UI;
import server.ManageSale_Sev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;

//退票
public class RefundTicket_UI implements MouseListener {
	User login_user;//登陆进的用户
	JFrame fJFrame;//父窗口
	private JLabel refundticket= new JLabel("退票",JLabel.CENTER);
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();
	private JButton search = new JButton();
	private JButton refund = new JButton();
	
	private myJPanel_UI rt_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_RefundTicket());//設置背景   主面板
	private String[][] rt_Data;
	private DefaultTableModel rt_Model;
	private JTable rt_JT = new JTable(rt_Model);//用户表格
	private JScrollPane rt_JS = new JScrollPane();//設置背景   主面板
	private JButton close = new JButton();
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private JDialog rt_JF;
	
	public RefundTicket_UI(JFrame con_JF, User login_user) {
		fJFrame = con_JF;
		rt_JF = new JDialog(con_JF,true);
		this.login_user = login_user;
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
	
		//主窗口
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		rt_Jp.add(refundticket);
		rt_Jp.add(info);		
		rt_Jp.add(datetime);
		rt_Jp.add(search_Box);
		rt_Jp.add(search);
		rt_Jp.add(close);
		rt_Jp.add(refund);
		rt_JS.getViewport().add(rt_JT);
		rt_Jp.add(rt_JS);
		rt_JF.add(rt_Jp);
		rt_JF.setVisible(true);
	}
	//组件的监听
	public void listener(){
		//主窗口
		refundticket.addMouseListener(this);
		info.addMouseListener(this);
		search.addMouseListener(this);
		close.addMouseListener(this);
		refund.addMouseListener(this);
		rt_JT.addMouseListener(this);
		rt_JS.addMouseListener(this);
		rt_Jp.addMouseListener(this);
		rt_JF.addMouseListener(this);
		rt_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = rt_JF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				rt_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//所有组件的初始化
	public void Init(){
		//主窗口
		ui.Init_UI.JDialogInit(rt_JF,"rt_JF",Default.Get_Window_X(),Default.Get_Window_Y());
		ui.Init_UI.JPanelInit(rt_Jp, "rt_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		ui.Init_UI.JLableInit(refundticket, "refundticket");
		rt_Data = ManageSale_Sev.get_user_sale_list(login_user.getId());
		rt_Model = new DefaultTableModel(rt_Data,Default.Get_Sale_ColumnNames()){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		rt_JT.setModel(rt_Model);
		refundticket.setFont(new Font("serif",1,42));
		refundticket.setForeground(Color.red);
		ui.Init_UI.JLableInit(info, "info");
		info.setCursor(null);//日期进入不为手

		ui.Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手
		ui.Init_UI.JTextFieldInit(search_Box, "search_Box");
		ui.Init_UI.JButtonInit(search, "search");
		ui.Init_UI.JButtonInit(close, "close");
		ui.Init_UI.JButtonInit(refund, "refund");
		ui.Init_UI.JScrollPaneInit(rt_JS,"st_JS");
		ui.Init_UI.JTableInit(rt_JT,"rt_JT");

	}
	//设置所有组件的大小与起始位置
	public void setSize(){
		//主窗口
		refundticket.setBounds(400, 40, 200, 50);
		info.setBounds(941, 26, 150, 20);
		datetime.setBounds(840, 55, 200, 20);
		search.setBounds(961, 114, 29, 27);
		search_Box.setBounds(788, 114, 177, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
		refund.setBounds(770, 524, 188, 45);
		rt_JS.setBounds(63,190,Default.Get_Window_X()-409,Default.Get_Window_Y()-230);
	}
	
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("refund".equals(e.getComponent().getName())){
			int selectedRow = rt_JT.getSelectedRow(); // 获得选中行索引
			if(selectedRow != -1){
				//退票订单窗口
				int sale_id = Integer.valueOf(rt_JT.getValueAt(selectedRow, 0).toString());
				new ui.Refund_UI(rt_JF,login_user,sale_id);
			}
		}else if("refundticket".equals(e.getComponent().getName())){
			rt_JT.setModel(rt_Model);
		}else if("search".equals(e.getComponent().getName())){
			//只根据订单查
			rt_JT.setModel(new DefaultTableModel(ManageSale_Sev.get_search_user_sale_list(login_user.getId(),String.valueOf(search_Box.getText())),Default.Get_Sale_ColumnNames()){
				public boolean isCellEditable(int row,int column){ 
					return false;
				}  
			});
		}else if("close".equals(e.getComponent().getName())){
//			 fJFrame.setVisible(true);
			rt_JF.dispose();
//		 System.exit(0);
		}		
	}

	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
		// 当鼠标按下的时候获得窗口当前的位置
		if("rt_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  	
	}

	public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作 
	}

	public void mouseExited(MouseEvent e) {
		if("rt_Jp".equals(e.getComponent().getName())){
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
	public static void main(String[] args){
		User user = new User();
		user.setId(3);
		user.setIdentity("售票员");
		user.setName("606");
		new RefundTicket_UI(null,user);
	}
}


