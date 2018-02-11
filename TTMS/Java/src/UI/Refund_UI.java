package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Basic.Default;
import Basic.Sale;
import Basic.User;
import db.ManageTicket_DB;
import server.ManageSale_Sev;
import sun.security.util.DisabledAlgorithmConstraints;

public class Refund_UI implements MouseListener {
	int sale_id;
	JDialog refund_JD;
	User  login_user;
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();
	private JButton search = new JButton();
	private JButton submit = new JButton();
	
	private myJPanel_UI refund_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_RefundTicket2());//設置背景   主面板
	private String[][] refund_Data;
	private DefaultTableModel refund_Model;
	private JTable refund_JT = new JTable(refund_Model);//用户表格
	private JScrollPane refund_JS = new JScrollPane();//設置背景   主面板
	
	private String[][] ticket_Data;//用户数据
	private DefaultTableModel ticket_Model = new DefaultTableModel(ticket_Data,new String[]{"TicketId", "TicketPrice"}){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable ticket_JT = new JTable(ticket_Model);//用户表格
	private JScrollPane detailed_JS = new JScrollPane();//設置背景   主面板
	
	private JButton close = new JButton();
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置

	//父窗口，登录的用户,订单的id
	public Refund_UI(JDialog rt_JF,User Conductor ,int sale_id) {
		this.sale_id = sale_id;
		refund_JD = new JDialog(rt_JF, true);
		login_user = Conductor;
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
		
		//主窗口
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		refund_Jp.add(info);		
		refund_Jp.add(datetime);
//		refund_Jp.add(search_Box);
//		refund_Jp.add(search);
		refund_Jp.add(close);
		refund_Jp.add(submit);
		refund_JS.getViewport().add(refund_JT);
		detailed_JS.getViewport().add(ticket_JT);
		refund_Jp.add(detailed_JS);
		refund_Jp.add(refund_JS);
		refund_JD.add(refund_Jp);
		refund_JD.setVisible(true);
	}
	public void listener(){
		//主窗口
		info.addMouseListener(this);
		search.addMouseListener(this);
		close.addMouseListener(this);
		submit.addMouseListener(this);
		refund_JT.addMouseListener(this);
		refund_JD.addMouseListener(this);
		refund_Jp.addMouseListener(this);
		refund_JD.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = refund_JD.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				refund_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
	}
	//所有组件的初始化
	public void Init(){
		//主窗口
		Init_UI.JDialogInit(refund_JD,"refund_JD",Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(refund_Jp, "refund_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		refund_Data = ManageSale_Sev.get_sale_item_list(sale_id);
		refund_Model = new DefaultTableModel(refund_Data,Default.Get_Sale_Item_ColumnNames()){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		refund_JT.setModel(refund_Model);
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//日期进入不为手

		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手
		Init_UI.JTextFieldInit(search_Box, "search_Box");
		Init_UI.JButtonInit(search, "search");
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(submit, "submit");
		Init_UI.JScrollPaneInit(refund_JS,"refund_JS");
		Init_UI.JTableInit(refund_JT,"refund_JT");
		Init_UI.JScrollPaneInit(detailed_JS,"detailed_JS");
		Init_UI.JTableInit(ticket_JT,"ticket_JT");
	}
	//设置所有组件的大小与起始位置
	public void setSize(){
		info.setBounds(941, 26, 150, 20);
		datetime.setBounds(840, 55, 200, 20);
		search.setBounds(961, 114, 29, 27);
		search_Box.setBounds(788, 114, 177, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
		submit.setBounds(784, 582, 183, 37);
		refund_JS.setBounds(26,135,Default.Get_Window_X()-375,Default.Get_Window_Y()-183);
		detailed_JS.setBounds(774,137,Default.Get_Window_X()-847,Default.Get_Window_Y()-272);


	}
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("submit".equals(e.getComponent().getName())){
			//得到表格中的数据
			ArrayList<Integer> ticket = new ArrayList<Integer>();
			if(ticket_Model.getRowCount()>0){//表格中要有数据
				float refund=0;
				for(int i=0 ; i<ticket_Model.getRowCount() ; i++){
					ticket.add(new Integer(Integer.valueOf(ticket_Model.getValueAt(i, 0).toString())));
					refund+=Float.valueOf(ticket_Model.getValueAt(i, 1).toString());
				}
				int[] ticket_id=new int[ticket.size()];
				for(int i=0 ; i<ticket.size() ; i++){
					ticket_id[i] = ticket.get(i);
				}
				Sale sale = new Sale(login_user.getId(),"0",String.valueOf(refund),1);
				if(ManageSale_Sev.add(sale, ticket_id)){
					ManageTicket_DB.Lock_ticket(ticket_id,0);
					JOptionPane.showMessageDialog(null, "退票成功");  
					refund_JD.dispose();
				}
			}else{	
				JOptionPane.showMessageDialog(null, "请选中票");  
			}
		}else if("refund_JT".equals(e.getComponent().getName())){
			int selectedRow = refund_JT.getSelectedRow(); // 获得选中行索引
			boolean flag=true;
			if(selectedRow!=-1 && String.valueOf(refund_Model.getValueAt(selectedRow,7)).equals("F")){
				for(int i=0 ; i<ticket_Model.getRowCount() ; i++){
					if(String.valueOf(ticket_Model.getValueAt(i, 0)).equals(String.valueOf(refund_Model.getValueAt(selectedRow,3)))){
						ticket_Model.removeRow(i);
						flag=false;
					}
				}
				if(flag){
					ticket_Model.addRow(new String[]{String.valueOf(refund_Model.getValueAt(selectedRow,3)),String.valueOf(refund_Model.getValueAt(selectedRow,6))});
				}
			}
		}else if("close".equals(e.getComponent().getName())){
			refund_JD.dispose();
//		 	System.exit(0);
	}
	}
	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
		// 当鼠标按下的时候获得窗口当前的位置
		if("refund_JD".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  	
	}

	public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作 
	}

	public void mouseExited(MouseEvent e) {
		if("refund_Jp".equals(e.getComponent().getName())){
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
}
