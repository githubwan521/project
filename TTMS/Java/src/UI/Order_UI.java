package ui;

import Basic.*;
import db.ManageTicket_DB;
import server.ManageOrder_Sev;
import server.ManageSale_Sev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;

//订单界面
public class Order_UI implements MouseListener{
	Schedule schedule;
	User login_user;//登陆进的用户
	int[] ticket_id;//订单中的票

	private JDialog order_JD;//本窗口
	
	private ui.myJPanel_UI order_Jp = new ui.myJPanel_UI(Default.Get_Path_Img_Cond_order());//設置背景   主面板
	private String[][] detailed_Data;
	private DefaultTableModel detailed_Model;
	private JTable detailed_JT = new JTable();//用户表格
	private JScrollPane detailed_JS = new JScrollPane();//設置背景   主面板
	private JTextField payable = new JTextField();//应付金额
	private JTextField paid = new JTextField();//实付金额
	private JTextField refund  = new JTextField();//找回金额
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();

	private JButton generate = new JButton();
	private JButton undo = new JButton();
	private JButton close = new JButton();
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置

	public Order_UI(JDialog sell_JD, Schedule schedule, User Conductor, int[] ticket_id) {
		this.schedule = schedule;
		order_JD = new JDialog(sell_JD, true);
		login_user = Conductor;
		this.ticket_id = ticket_id;
		
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
		info.setText(login_user.getName());
		//	info.setFont(new Font("serif",0,20));
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		
		
		detailed_JS.getViewport().add(detailed_JT);
		order_Jp.add(detailed_JS);
		order_Jp.add(payable);
		order_Jp.add(paid);
		order_Jp.add(refund);
		order_Jp.add(undo);
		order_Jp.add(generate);
		order_Jp.add(info);
		order_Jp.add(datetime);
		order_Jp.add(close);
		order_JD.add(order_Jp);
		
		order_JD.setVisible(true);//
	}
	private void listener() {
		order_JD.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = order_JD.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				order_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		order_JD.addMouseListener(this);	
		paid.setDocument(new ui.DoubleDocument());//只能输入小数
		undo.addMouseListener(this);
		generate.addMouseListener(this);
		close.addMouseListener(this);
		order_Jp.addMouseListener(this);
	
	}
	private void setSize() {
		detailed_JS.setBounds(77,102, 892, 360);
		close.setBounds(Default.Get_Window_X()-25,0, 25, 25);
		payable.setBounds(175,495, 149, 31);
		paid.setBounds(497,495, 151, 31);
		refund.setBounds(820,495, 151, 31);
		generate.setBounds(621,555, 186, 40);
		info.setBounds(944, 25, 155, 25);
		datetime.setBounds(830, 64, 200, 20);
		
		undo.setBounds(243,555, 186, 40);
	}
	private void Init() {
		ui.Init_UI.JDialogInit(order_JD, "order_JD",Default.Get_Window_X(), Default.Get_Window_Y());
		ui.Init_UI.JPanelInit(order_Jp, "order_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		ui.Init_UI.JScrollPaneInit(detailed_JS,"detailed_JS");
		ui.Init_UI.JLableInit(info, "info");
		info.setCursor(null);//日期进入不为手

		ui.Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手

		ui.Init_UI.JTableInit(detailed_JT,"detailed_JT");
		detailed_Data = ManageOrder_Sev.get_order_list(schedule,ticket_id);
//		for(int i=0 ; i<detailed_Data.length ; i++){
//			for(int j=0 ; j<detailed_Data[i].length ; i++){
//				System.out.println("sa"+detailed_Data[i][j]);
//			}
//		}
		detailed_JT.setModel(new DefaultTableModel(detailed_Data,Default.Get_Order_ColumnNames()){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		});
		ui.Init_UI.JTextFieldInit(payable, "payable");
		float tem=0;
		for(int i=0 ; i<detailed_Data.length ; i++){
			tem += Float.valueOf(detailed_Data[i][6]);
		}
		payable.setText(String.valueOf(tem));
		payable.setEditable(false);//屏蔽输入
		Init_UI.JTextFieldInit(paid, "paid");
		Init_UI.JTextFieldInit(refund, "refund");
		refund.setEditable(false);//屏蔽输入

		Init_UI.JButtonInit(undo , "undo");
		Init_UI.JButtonInit(generate , "generate");
		Init_UI.JButtonInit(close, "close");
	}
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("generate".equals(e.getComponent().getName())){
			float payment,total;
			total = Float.valueOf(payable.getText());//应该支付
			if(!paid.getText().equals("")){
				payment =  Float.valueOf(paid.getText());//实际支付
				if(payment-total >=0){
					refund.setText(String.valueOf(payment-total));
					JOptionPane.showMessageDialog(null, "提交成功");
					Sale sale=new Sale(login_user.getId(),paid.getText(),String.valueOf(payment-total),0);
					System.out.println("or"+sale.getUser_id());
					ManageTicket_DB.sell_ticket(ticket_id);
					ManageSale_Sev.add(sale,ticket_id);
					order_JD.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "支付失败,请重新输入支付金额");
					paid.setText("");
				}
			}else{
				JOptionPane.showMessageDialog(null, "支付失败,请重新输入支付金额");
				paid.setText("");
			}
		}else if("undo".equals(e.getComponent().getName())){
			ManageTicket_DB.Lock_ticket(ticket_id,0);
			order_JD.dispose();
		}else if("close".equals(e.getComponent().getName())){
			ManageTicket_DB.Lock_ticket(ticket_id,0);
			order_JD.dispose();
		}
	}
	public void mousePressed(MouseEvent e) {
		// 当鼠标按下的时候获得窗口当前的位置
		if("order_JD".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}		
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		if("order_Jp".equals(e.getComponent().getName())){//进入组件更新时间
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}

}

