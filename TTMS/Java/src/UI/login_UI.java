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
	
	private JTextField username = new JTextField();//name框
	private JPasswordField userpass = new JPasswordField(); //密码框
	private Choice user_identity = new Choice();
	
	private JButton login = new JButton();
	private JButton close = new JButton();//登录，关闭
	private myJPanel_UI Login_JP = new myJPanel_UI(Default.Get_Path_Img_Login());//設置背景
	private JFrame Login_JF = new JFrame();
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置

	public login_UI() {
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
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
		username.setDocument(new LengthDocument(16));//限制用户名输入长度
		
		Init_UI.JPasswordField(userpass, "userpass");
		userpass.setDocument(new LengthDocument(16));//限制密码输入长度

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
	//组件的监听
	public void listener(){
		
		username.addMouseListener(this);
		userpass.addMouseListener(this);
		close.addMouseListener(this);
		login.addMouseListener(this);
		Login_JF.addMouseListener(this);
		Login_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = Login_JF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				Login_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("username".equals(e.getComponent().getName())){//---------用户ID框

		}else if("userpass".equals(e.getComponent().getName())){//--------密码框
			
		}else if("login".equals( e.getComponent().getName())){
			//超级管理员
			if(username.getText().equals("root") && String.valueOf(userpass.getPassword()).equals("root")){
				if(user_identity.getItem(user_identity.getSelectedIndex()).equals("管理员")){
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
						if(user_identity.getItem(user_identity.getSelectedIndex()).equals("售票员")){
							new Conductor_UI(user);
						}else if(user_identity.getItem(user_identity.getSelectedIndex()).equals("管理员")){
							new Administrator_UI(user);
						}else if(user_identity.getItem(user_identity.getSelectedIndex()).equals("经理")){
							new Manager_UI(user);
						}
						Login_JF.dispose();
			    	}else{
						JOptionPane.showMessageDialog(null, "该用户已登录,不能重复登录"); 
			    	}
				}else{
					JOptionPane.showMessageDialog(null, "用户名与密码验证错误"); 
				}
			}
		}else if("close".equals(e.getComponent().getName())){
			System.exit(0);//退出程序
		}
	}

	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
		// 当鼠标按下的时候获得窗口当前的位置
		if("Login_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  
		
	}

	public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作 
		
	}

	public void mouseExited(MouseEvent e) {// 鼠标离开组件时执行的操作  
		
	}
	public static void main(String[] args){
		new login_UI();
	}
}