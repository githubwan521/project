package UI;
import account.Account;
import constant.Constant;
import constant.MyDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginUI extends JFrame implements MouseListener {
	private JTextField username;//ID??
	private JPasswordField password; //?????
	private JButton login,close;//????????
	private JPanel JP;//???  
	private JLabel register,forget;//???????????
	private JFrame loginJF;
	private int value;//??????????
	private static Point origin = new Point();// ????λ????????????????????????λ??
	
	public loginUI(){
		value = 0;
		loginJF = new JFrame();
		loginJF.setSize(Constant.getLoginUiSizeX(), Constant.getLoginUiSizeY());//?????С
		InitUI.JFrameInit(loginJF,"loginJF");//????????
		loginJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//?????????????????????е????????
		InitUI.JFrameBackdropInit(loginJF,Constant.getLoginUiImg(),value++);//???????
		loginJF.addMouseListener(this);
		loginJF.addMouseMotionListener(new MouseMotionAdapter() {
			// ?????mouseDragged ???????????????????????????????????
			public void mouseDragged(MouseEvent e) {
				// ??????????????????λ??
				Point tem = loginJF.getLocation();
				// ????????λ??
				// ????????λ?? + ??????????λ?? - ???????????????λ??
				loginJF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		
		register = new JLabel("Register",JLabel.CENTER);
		InitUI.JLableInit(register, "register");
		register.addMouseListener(this);//????????
		
		forget = new JLabel("Forget",JLabel.CENTER);
		InitUI.JLableInit(forget, "forget");
		forget.addMouseListener(this);
		fontInit();
		
        //???????
		username = new JTextField();//ID??
		username.setDocument(new MyDocument(16));//???????????????
		username.setText(" ??????ID/????");
		InitUI.JTextFieldInit(username,"username");
		username.addMouseListener(this);//????????
		password = new JPasswordField();//?????
		password.setDocument(new MyDocument(16));//??????????????
		password.setText(" ??????????");
		InitUI.JPasswordField(password,"password");
		password.addMouseListener(this);//?????????
		
		
		login = new JButton();
		InitUI.JButtonInit(login, "login");
		login.addMouseListener(this);//?????????
		close = new JButton();
		InitUI.JButtonInit(close, "close");
		close.addMouseListener(this);//????????
		
		//?????????е???????
		//1--???????
        JP = new JPanel(null);//????ò?????
        InitUI.JPanelInit(JP, "JP");
		JP.setBounds(0,0,loginJF.getWidth(), loginJF.getHeight());//????С??????С
		
//		//2--???????????
		username.setBounds(52,226, 187, 28);//ID??????λ?????С
		JP.add(username);
		password.setBounds(52, 284, 187, 28);//?????????λ?????С
		JP.add(password);
		login.setBounds(73,358,145,35);//??????????λ?????С
		JP.add(login);
		close.setBounds(265, 0, 24, 22);//??????λ?????С
		JP.add(close);
		register.setBounds(20,430,70,23);//?????????λ?????С
		JP.add(register);
		forget.setBounds(208,430,70,23);//??????????????λ?????С
		JP.add(forget);
		
		
		loginJF.add(JP);
		loginJF.setVisible(true);//??????
	}
	
	private void fontInit(){//????????
		register.setFont((new Font("serif",0,16)));//????????
		register.setForeground(Color.white);
		forget.setFont((new Font("serif",0,16)));//????????
		forget.setForeground(Color.white);
	}
	
    public void mouseClicked(MouseEvent e) {// ??????????е????  
		if("username".equals(e.getComponent().getName())){//---------???ID??
			InitUI.JFrameBackdropInit(loginJF,Constant.getLoginUiImg(),value++);//???????

			if(" ??????ID/????".equals(username.getText())){
				username.setFont(new Font("Arial",1,18));//??????????-----????????
				username.setText("");
				username.setForeground(Color.black);//???????????
				username.setEditable(true);//????????
				username.setFocusable(true);//??????
				username.grabFocus();//??λ???
				
				//???????????????????????????????
				if("".equals(password.getText())){
					InitUI.JPasswordField(password, "password");
	            	password.setText(" ??????????");
				}
			}else{
				//?????????????
				System.out.println(username.getText());
			}
		}else if("password".equals(e.getComponent().getName())){//--------?????
			InitUI.JFrameBackdropInit(loginJF,Constant.getLoginUiImg(),value++);//???????

            if(" ??????????".equals(password.getText())){
            	password.setFont(new Font("serif",1,30));//??????????
            	password.setText("");
            	password.setEchoChar('��');
            	password.setForeground(Color.black);//???????????
            	password.setEditable(true);//????????
            	password.setFocusable(true);//??????
            	password.grabFocus();//??λ???
            	
            	//???????????????????????????????
            	if("".equals(username.getText())){
            		InitUI.JTextFieldInit(username, "username");
    				username.setText(" ??????ID/????");
    			}
			}else{
				//????????????
				System.out.println(password.getPassword());
			}
		}else if("register".equals(e.getComponent().getName())){//----------???
			new registerUI(loginJF);
			
		}else if("forget".equals(e.getComponent().getName())){//----------????????
			
		}else if("login".equals(e.getComponent().getName())){//----------???
			if(" ??????ID/????".equals(username.getText()) || "".equals(username.getText()) ){
				InitUI.JFrameBackdropInit(loginJF,Constant.getLoginUiFailImg(1),value++);
			}else if(" ??????????".equals(password.getText()) || "".equals(password.getText())){
				InitUI.JFrameBackdropInit(loginJF,Constant.getLoginUiFailImg(2),value++);
			}else if(Account.login(username.getText(),password.getText())){
				new user();
				loginJF.dispose();
			}else{
				InitUI.JFrameBackdropInit(loginJF,Constant.getLoginUiFailImg(3),value++);
			}
		}else if("close".equals(e.getComponent().getName())){//--------???
			System.exit(0);//???????
		}  
	}  
  
    public void mouseEntered(MouseEvent e) {// ????????????е????  
    	if("forget".equals( e.getComponent().getName())){
    		forget.setFont((new Font("serif",0, 21)));
    		forget.setForeground(Color.red);
		}else if("register".equals( e.getComponent().getName())){
			register.setFont((new Font("serif",0, 21)));
			register.setForeground(Color.blue);
		}
    }  
    public void mouseExited(MouseEvent e) {// ???????????е????  
    	Component component = e.getComponent ();
    	
    	if("username".equals(component.getName())){//???ID??
			if("".equals(username.getText())){
				InitUI.JTextFieldInit(username, "username");
				username.setText(" ??????ID/????");
			}
		}else if("password".equals(component.getName())){
            if("".equals(password.getText())){
            	InitUI.JPasswordField(password, "password");
            	password.setText(" ??????????");
			}
		}else if("register".equals(component.getName())){
			fontInit();
		}else if("forget".equals(component.getName())){
			fontInit();
		}
    }  
    public void mousePressed(MouseEvent e) {// ????????????????е????  
    	// ????????????????????λ??
		if("loginJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}
    
    public void mouseReleased(MouseEvent e) {// ?????????е????  
    }  
}
