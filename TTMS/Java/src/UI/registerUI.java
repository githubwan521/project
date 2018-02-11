package UI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import account.Account;
import constant.Constant;
import constant.MyDocument;

public class registerUI  extends JFrame implements MouseListener{
	private JTextField username;//密码与ID框
	private JPasswordField password1,password2; //密码框
	private JButton register,close; 
	private JFrame login,registerJF;
	private int value;//图片叠加的层数
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	
	
	public registerUI(JFrame login){
		//---------本地初始化
		//隐藏主窗口
		value = 0;
		this.login = login;
	
		registerJF = new JFrame();
		registerJF.setSize(Constant.getRegisterUiSizeX(), Constant.getRegisterUiSizeY());//窗口大小
		InitUI.JFrameInit(registerJF,"registerJF");//窗口初始化
		InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);//设置背景
		registerJF.addMouseListener(this);
		registerJF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = registerJF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				registerJF.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		}); 
		
		
		username = new JTextField();//ID框
		username.setDocument(new MyDocument(16));//限制用户名输入长度
		username.setText(" 请输入ID/邮箱");
		InitUI.JTextFieldInit(username,"username");
		username.addMouseListener(this);//监听账号框
		
		password1 = new JPasswordField();//密码框
		password1.setDocument(new MyDocument(16));//限制密码输入长度
		password1.setText(" 请输入密码");
		InitUI.JPasswordField(password1,"password1");
		password1.addMouseListener(this);//监听密码框
		
		password2 = new JPasswordField(" 请再次输入密码");//密码框
		password2.setDocument(new MyDocument(16));//限制密码输入长度
		password2.setText(" 请再次输入密码");
		InitUI.JPasswordField(password2,"password2");
		password2.addMouseListener(this);//监听密码框
		
		register = new JButton();
		InitUI.JButtonInit(register, "register");
		register.addMouseListener(this);//监听注册键
		close = new JButton();
		InitUI.JButtonInit(close, "close");
		close.addMouseListener(this);
		
		JPanel JP = new JPanel(null);//不适用布局方式
		InitUI.JPanelInit(JP, "JP");
		JP.setSize(registerJF.getWidth(), registerJF.getHeight());//面板大小为窗口大小
			
	   //2--组件布局及添加
		username.setBounds(52, 213,187, 28);//ID框的起始位置与大小
		JP.add(username);
		password1.setBounds(52, 271, 187, 28);//组件的起始位置与大小
		JP.add(password1);
		password2.setBounds(52, 330, 187, 28);//组件的起始位置与大小
		JP.add(password2);
		register.setBounds(73,401,145,35);//注册键的起始位置与大小
		JP.add(register);
		close.setBounds(265, 0, 24, 22);//关闭起始位置与大小
		JP.add(close);
		
		registerJF.add(JP);	
		
		registerJF.setVisible(true);//窗口可见
		login.setVisible(false);
	}

	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作  
		if("username".equals(e.getComponent().getName())){//---------用户ID框
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			if(" 请输入ID/邮箱".equals(username.getText())){
				username.setFont(new Font("Arial",1,18));//设置字体格式-----后续更改
				username.setText("");
				username.setForeground(Color.black);//设置前景色为黑
				username.setEditable(true);//接受输入
				username.setFocusable(true);//显示光标
				username.grabFocus();//定位光标
				
				//处理密码框无内容时鼠标不在密码框的问题
				if("".equals(password1.getText())){
					InitUI.JPasswordField(password1, "password1");
	            	password1.setText(" 请输入密码");
				}
				if("".equals(password2.getText())){
					InitUI.JPasswordField(password2, "password2");
	            	password2.setText(" 请再次输入密码");
				}
			}else{
				//打印输入的用户名
				System.out.println(username.getText());
			}
		}else if("password1".equals(e.getComponent().getName())){//--------密码框
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			if(" 请输入密码".equals(password1.getText())){
            	password1.setFont(new Font("serif",1,30));//设置字体格式
            	password1.setText("");
            	password1.setEchoChar('·');
            	password1.setForeground(Color.black);//设置前景色为黑
            	password1.setEditable(true);//接受输入
            	password1.setFocusable(true);//显示光标
            	password1.grabFocus();//定位光标
            	
            	if("".equals(username.getText())){
            		InitUI.JTextFieldInit(username, "username");
    				username.setText(" 请输入ID/邮箱");
    			}
            	if("".equals(password2.getText())){
            		InitUI.JPasswordField(password2, "password2");
	            	password2.setText(" 请再次输入密码");
				}
			}else{
				//打印输入的密码
			}
		}else if("password2".equals(e.getComponent().getName())){//----------注册
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			 if(" 请再次输入密码".equals(password2.getText())){
				 password2.setFont(new Font("serif",1,30));//设置字体格式
				 password2.setText("");
				 password2.setEchoChar('·');
				 password2.setForeground(Color.black);//设置前景色为黑
				 password2.setEditable(true);//接受输入
				 password2.setFocusable(true);//显示光标
				 password2.grabFocus();//定位光标
	            	
            	if("".equals(username.getText())){
            		InitUI.JTextFieldInit(username, "username");
    				username.setText(" 请输入ID/邮箱");
    			}
            	if("".equals(password1.getText())){
            		InitUI.JPasswordField(password1, "password1");
					password1.setText(" 请输入密码");
				}
			}else{
				//打印输入的密码
			}
		}else if("register".equals(e.getComponent().getName())){//---------提交注册信息
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			if(" 请输入ID/邮箱".equals(username.getText()) || "".equals(username.getText())){
				InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiFailImg(1),value++);
			}else if(" 请输入密码".equals(password1.getText()) || " 请再次输入密码".equals(password2.getText()) ||
					"".equals(password1.getText()) || "".equals(password2.getText())){
				InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiFailImg(2),value++);
			}else if(Account.register(username.getText(),password1.getText(),password2.getText())){
				JOptionPane.showMessageDialog(null, "注册成功", "YYY", JOptionPane.ERROR_MESSAGE);

				InitUI.JTextFieldInit(username, "username");
				username.setText(" 请输入ID/邮箱");
				InitUI.JPasswordField(password1, "password1");
				password1.setText(" 请输入密码");
				InitUI.JPasswordField(password2, "password2");
            	password2.setText(" 请再次输入密码");
			}else{
				InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiFailImg(2),value++);
			}
		}else if("close".equals(e.getComponent().getName())){//--------关闭
			login.setVisible(true);//主窗口可见
			registerJF.dispose();//隐藏当前窗口，并释放窗体占有的其他资源
		}  
	}

	  
    public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作  
    }  
    public void mouseExited(MouseEvent e) {// 鼠标离开组件时执行的操作  
    	Component component = e.getComponent ();
    	if("username".equals(component.getName())){//用户ID框
			if("".equals(username.getText())){
				InitUI.JTextFieldInit(username, "username");
				username.setText(" 请输入ID/邮箱");
			}
		}else if("password1".equals(component.getName())){
            if("".equals(password1.getText())){
            	InitUI.JPasswordField(password1, "password1");
            	password1.setText(" 请输入密码");
			}
		}else if("password2".equals(component.getName())){
            if("".equals(password2.getText())){
            	InitUI.JPasswordField(password2, "password2");
            	password2.setText(" 请再次输入密码");
			}
		}
    }  
    public void mousePressed(MouseEvent e) {//鼠标在组件上按下时执行的操作  
    	// 当鼠标按下的时候获得窗口当前的位置
		if("registerJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
    }  
    public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  
    }   
}
