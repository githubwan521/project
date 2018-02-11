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

//��������
public class Order_UI implements MouseListener{
	Schedule schedule;
	User login_user;//��½�����û�
	int[] ticket_id;//�����е�Ʊ

	private JDialog order_JD;//������
	
	private ui.myJPanel_UI order_Jp = new ui.myJPanel_UI(Default.Get_Path_Img_Cond_order());//�O�ñ���   �����
	private String[][] detailed_Data;
	private DefaultTableModel detailed_Model;
	private JTable detailed_JT = new JTable();//�û����
	private JScrollPane detailed_JS = new JScrollPane();//�O�ñ���   �����
	private JTextField payable = new JTextField();//Ӧ�����
	private JTextField paid = new JTextField();//ʵ�����
	private JTextField refund  = new JTextField();//�һؽ��
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();

	private JButton generate = new JButton();
	private JButton undo = new JButton();
	private JButton close = new JButton();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	public Order_UI(JDialog sell_JD, Schedule schedule, User Conductor, int[] ticket_id) {
		this.schedule = schedule;
		order_JD = new JDialog(sell_JD, true);
		login_user = Conductor;
		this.ticket_id = ticket_id;
		
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
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
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = order_JD.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				order_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		order_JD.addMouseListener(this);	
		paid.setDocument(new ui.DoubleDocument());//ֻ������С��
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
		info.setCursor(null);//���ڽ��벻Ϊ��

		ui.Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��

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
		payable.setEditable(false);//��������
		Init_UI.JTextFieldInit(paid, "paid");
		Init_UI.JTextFieldInit(refund, "refund");
		refund.setEditable(false);//��������

		Init_UI.JButtonInit(undo , "undo");
		Init_UI.JButtonInit(generate , "generate");
		Init_UI.JButtonInit(close, "close");
	}
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("generate".equals(e.getComponent().getName())){
			float payment,total;
			total = Float.valueOf(payable.getText());//Ӧ��֧��
			if(!paid.getText().equals("")){
				payment =  Float.valueOf(paid.getText());//ʵ��֧��
				if(payment-total >=0){
					refund.setText(String.valueOf(payment-total));
					JOptionPane.showMessageDialog(null, "�ύ�ɹ�");
					Sale sale=new Sale(login_user.getId(),paid.getText(),String.valueOf(payment-total),0);
					System.out.println("or"+sale.getUser_id());
					ManageTicket_DB.sell_ticket(ticket_id);
					ManageSale_Sev.add(sale,ticket_id);
					order_JD.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "֧��ʧ��,����������֧�����");
					paid.setText("");
				}
			}else{
				JOptionPane.showMessageDialog(null, "֧��ʧ��,����������֧�����");
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
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
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
		if("order_Jp".equals(e.getComponent().getName())){//�����������ʱ��
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}

}

