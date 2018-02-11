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
	User login_user;//��½�����û�
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();

	private JDialog sell_JD;//������
	private myJPanel_UI seat_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_Seat());//�O�ñ���   �����
	private myJPanel_UI sell_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_Sell_Seat());//�O�ñ���   �����
	private String[][] detailed_Data;//�û�����
	private DefaultTableModel detailed_Model = new DefaultTableModel(detailed_Data,new String[]{"Row", "Col"}){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable detailed_JT = new JTable(detailed_Model);//�û����
	private JScrollPane detailed_JS = new JScrollPane();//�O�ñ���   �����
	private JButton produce = new JButton();
	private JButton close = new JButton();
	private JButton[][] seat_jb=new JButton[10][15];//��λ
	private int[][] ticket_state;
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	public Sell_UI(JDialog st_JF, Schedule schedule, User Conductor ){
		this.schedule = schedule;
		sell_JD = new JDialog(st_JF, true);
		login_user = Conductor;
		this.Init();//��ʼ��
		this.gettTicketState();
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
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
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = sell_JD.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				sell_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		sell_JD.addMouseListener(this);	
		detailed_JT.addMouseListener(this);
		produce.addMouseListener(this);
		close.addMouseListener(this);
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				//��λ��Ϊ����  ��1�ո�2��ʾΪ1��2��
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
				//��λ��Ϊ����  ��12��ʾΪ1��2��
				if(ticket_state[i][j]==0){//��ѡ��
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(0)));
				}else if(ticket_state[i][j]==1 || ticket_state[i][j]==2){//����������
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(2)));
				}else{//������
//					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Cond_Ticket(3)));
				}
			}
		}
	}
	private void Init() {
		Init_UI.JDialogInit(sell_JD, "sell_JD",Default.Get_Window_X(), Default.Get_Window_Y());
		Init_UI.JPanelInit(sell_Jp, "sell_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(seat_Jp, "seat_Jp",20,84,Default.Get_Window_X()-264,Default.Get_Window_Y()-178);
		seat_Jp.setLayout(new GridLayout(10,16));//���񲼾�
		Init_UI.JScrollPaneInit(detailed_JS,"detailed_JS");
		Init_UI.JTableInit(detailed_JT,"detailed_JT");
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				//��λ��Ϊ����  ��12��ʾΪ1��2��
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
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("produce".equals(e.getComponent().getName())){
			if(detailed_Model.getRowCount()>0){
				ArrayList<String[]> ticket = new ArrayList<String[]>();
				for(int i=0 ; i<detailed_Model.getRowCount() ; i++){
					ticket.add(new String[]{String.valueOf(detailed_Model.getValueAt(i, 0)),String.valueOf(detailed_Model.getValueAt(i, 1))});
					System.out.println("��Ʊ����λ"+String.valueOf(detailed_Model.getValueAt(i, 0))+"  "+ String.valueOf(detailed_Model.getValueAt(i, 1)));
				}
				int[] ticket_id = ManageTicket_DB.get_ticket_id(schedule.getSchedule_id(),ticket);
				for(int i=0 ; i< ticket_id.length ; i++){
					System.out.println("Ʊ"+ticket_id[i]);
				}
				if(ManageTicket_DB.Lock_ticket(ticket_id,1)){
					new Order_UI(sell_JD,schedule, login_user,ticket_id);
					this.gettTicketState();
					while(detailed_Model.getRowCount()>0){
						detailed_Model.removeRow(detailed_Model.getRowCount()-1);
					 } 
				}else{
					JOptionPane.showMessageDialog(null, "��Ʊʧ��,Ʊ�ѱ�����������,��ˢ������");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "��ѡ����λ");  
			}
		}else if("close".equals(e.getComponent().getName())){
			sell_JD.dispose();
		}
	}
	public void mousePressed(MouseEvent e) {
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
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
		if("sell_Jp".equals(e.getComponent().getName())){//�����������ʱ��
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}

}
