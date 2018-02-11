package ui;

import java.awt.Choice;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Basic.Default;
import Basic.User;
import db.ManageDict;
import server.ManageUser_Sev;

public class login_UI implements MouseListener {
	
	private JTextField username = new JTextField();//name��
	private JPasswordField userpass = new JPasswordField(); //�����
	private Choice user_identity = new Choice();
	
	private JButton login = new JButton();
	private JButton close = new JButton();//��¼���ر�
	private myJPanel_UI Login_JP = new myJPanel_UI(Default.Get_Path_Img_Login());//�O�ñ���
	private JFrame Login_JF = new JFrame();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	public login_UI() {
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();
		
		Login_JP.add(username);
		Login_JP.add(userpass);
		Login_JP.add(user_identity);
		Login_JP.add(login);
		Login_JP.add(close);
		Login_JF.add(Login_JP);
		Login_JF.setVisible(true);
	}
	public void Init(){
		Init_UI.JFrameInit(Login_JF,"Login_JF",Default.Get_Window_X_Login(),Default.Get_Window_Y_Login());
		Init_UI.JPanelInit(Login_JP, "Login_JP",0,0,Default.Get_Window_X_Login(),Default.Get_Window_Y_Login());
		Init_UI.JButtonInit(close, "close");

		Init_UI.JTextFieldInit(username, "username");
		username.setDocument(new LengthDocument(16));//�����û������볤��
		
		Init_UI.JPasswordField(userpass, "userpass");
		userpass.setDocument(new LengthDocument(16));//�����������볤��

		Init_UI.ChoiceInit(user_identity,"user_identity");
		String[] tem =  ManageDict.get_value_list(2);
		for(String i : tem){
			user_identity.add(i);
		}
		Init_UI.JButtonInit(login, "login");

	}
	public void setSize(){
		username.setBounds(88,166, 167, 26);
		userpass.setBounds(88, 232, 167, 26);
		user_identity.setBounds(88, 299, 169, 25);
		close.setBounds(265, 0, 24, 22);
		login.setBounds(72,382,145,35);
	}
	//����ļ���
	public void listener(){
		
		username.addMouseListener(this);
		userpass.addMouseListener(this);
		close.addMouseListener(this);
		login.addMouseListener(this);
		Login_JF.addMouseListener(this);
		Login_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = Login_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				Login_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("username".equals(e.getComponent().getName())){//---------�û�ID��

		}else if("userpass".equals(e.getComponent().getName())){//--------�����
			
		}else if("login".equals( e.getComponent().getName())){
			//��������Ա
			if(username.getText().equals("root") && String.valueOf(userpass.getPassword()).equals("root")){
				if(user_identity.getItem(user_identity.getSelectedIndex()).equals("����Ա")){
					User user = new User("","root","root", "");
					new Administrator_UI(user);
				}
			}else{
				User  user = new User();
				user.setIdentity(user_identity.getItem(user_identity.getSelectedIndex()));
				user.setName(username.getText());
				user.setPass(String.valueOf(userpass.getPassword()));
			    int user_id = ManageUser_Sev.login(user);
			    if(user_id != 0){
			    	if(ManageUser_Sev.search_status(user_id)==0){
				    	user.setId(user_id);
				    	user.setStatus(1);
				    	ManageUser_Sev.update_status(user);
						if(user_identity.getItem(user_identity.getSelectedIndex()).equals("��ƱԱ")){
							new Conductor_UI(user);
						}else if(user_identity.getItem(user_identity.getSelectedIndex()).equals("����Ա")){
							new Administrator_UI(user);
						}else if(user_identity.getItem(user_identity.getSelectedIndex()).equals("����")){
							new Manager_UI(user);
						}
						Login_JF.dispose();
			    	}else{
						JOptionPane.showMessageDialog(null, "���û��ѵ�¼,�����ظ���¼"); 
			    	}
				}else{
					JOptionPane.showMessageDialog(null, "�û�����������֤����"); 
				}
			}
		}else if("close".equals(e.getComponent().getName())){
			System.exit(0);//�˳�����
		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("Login_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  
		
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
		
	}

	public void mouseExited(MouseEvent e) {// ����뿪���ʱִ�еĲ���  
		
	}
	public static void main(String[] args){
		new login_UI();
	}
}