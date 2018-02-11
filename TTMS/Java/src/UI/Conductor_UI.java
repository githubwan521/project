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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Basic.Default;
import Basic.Studio;
import Basic.User;
import db.ManageDict;
import server.ManageUser_Sev;

//��ƱԱ��������
public class Conductor_UI implements MouseListener {
	User login_user;//��½�����û�
	
	//������
	private JFrame Con_JF = new JFrame();
	private myJPanel_UI con_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_Main());//�O�ñ���   �����
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JButton close = new JButton();
	private JButton sellticket = new JButton();
	private JButton refundticket = new JButton();
	private JButton salesdata = new JButton();

	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	
		
	public Conductor_UI(User Conductor){
		login_user = Conductor;
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
		
		//������
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		
		con_Jp.add(info);		
		con_Jp.add(datetime);
		con_Jp.add(close);
		con_Jp.add(sellticket);
		con_Jp.add(refundticket);
		con_Jp.add(salesdata);
		Con_JF.add(con_Jp);
		Con_JF.setVisible(true);
	}
	//����ļ���
	public void listener(){
		//������
		info.addMouseListener(this);
		close.addMouseListener(this);
		sellticket.addMouseListener(this);
		refundticket.addMouseListener(this);
		salesdata.addMouseListener(this);
		Con_JF.addMouseListener(this);
		Con_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = Con_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				Con_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//��������ĳ�ʼ��
	public void Init(){
		//������
		Init_UI.JFrameInit(Con_JF,"Con_JF",832,535);
		Init_UI.JPanelInit(con_Jp, "con_Jp",0,0,832,535);
		Init_UI.JLableInit(info, "info");
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(sellticket, "sellticket");
		Init_UI.JButtonInit(refundticket, "refundticket");
		Init_UI.JButtonInit(salesdata, "salesdata");

	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		//������
		info.setBounds(744, 30, 150, 20);
		datetime.setBounds(650, 70, 200, 20);
		close.setBounds(807, 0, 25, 25);
		sellticket.setBounds(169, 188, 150, 40);
		refundticket.setBounds(497, 188, 150, 40);
		salesdata.setBounds(356, 338, 120, 90);

	}
	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		 if("sellticket".equals(e.getComponent().getName())){
			new SellTicket_UI(Con_JF,login_user);
		}else if("refundticket".equals(e.getComponent().getName())){
			new RefundTicket_UI(Con_JF,login_user);
		}else if("salesdata".equals(e.getComponent().getName())){
//			new SalesData_UI(Con_JF,login_user);
		}else if("info".equals(e.getComponent().getName())){
			new ModifyPassword(Con_JF, login_user);
		}else if("close".equals(e.getComponent().getName())){
			login_user.setStatus(0);
	    	ManageUser_Sev.update_status(login_user);	
			System.exit(0);//�˳�����
		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("Con_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
	}

	public void mouseExited(MouseEvent e) {
	}
		
//	public static void main(String[] args){
//		User user = new User();
//		user.setId(3);
//		user.setIdentity("��ƱԱ");
//		user.setName("motian");
//		new Conductor_UI(user);
//	}
}
