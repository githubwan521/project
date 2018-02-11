package ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.org.apache.xpath.internal.operations.And;

import Basic.Default;
import Basic.User;
import server.ManageUser_Sev;

public class ModifyPassword implements MouseListener{
	User login_user;
	private JLabel modifypassword= new JLabel("修改信息",JLabel.CENTER);

	private JPasswordField userpass = new JPasswordField(); //密码框
	private JPasswordField repeatpass = new JPasswordField(); //密码框
	private JTextField usertel = new JTextField();//name框

	private JButton submit = new JButton();
	private JDialog mp_JD;//本窗口
	private myJPanel_UI mp_jp = new myJPanel_UI(Default.Get_Path_Img_PW());//設置背景   主面板
	private JButton close = new JButton();
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置

	public ModifyPassword(JFrame f_jf,User user){
		this.login_user = user;
		mp_JD = new JDialog(f_jf, true);
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
		
		mp_jp.add(modifypassword);
		mp_jp.add(userpass);
		mp_jp.add(usertel);
		mp_jp.add(repeatpass);
		mp_jp.add(submit);
		mp_jp.add(close);
		mp_JD.add(mp_jp);
		mp_JD.setVisible(true);
	}

	private void listener() {
		mp_JD.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point tem = mp_JD.getLocation();
				mp_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		mp_JD.addMouseListener(this);	
		close.addMouseListener(this);
		submit.addMouseListener(this);
		
	}

	private void setSize() {
		modifypassword.setBounds(400, 40, 200, 50);
		close.setBounds(265,0, 25, 25);
		userpass.setBounds(89,166, 167, 27);
		repeatpass.setBounds(89,233, 167, 27);
		usertel.setBounds(89,300, 167, 27);
		submit.setBounds(72,382, 145, 35);

	}

	private void Init() {
		Init_UI.JDialogInit(mp_JD, "mp_JD",290, 485);
		Init_UI.JPanelInit(mp_jp, "mp_jp",0,0,290,485);
		Init_UI.JLableInit(modifypassword, "modifypassword");
		modifypassword.setFont(new Font("serif",1,42));
		modifypassword.setForeground(Color.red);
		Init_UI.JTextFieldInit(userpass, "userpass");
		userpass.setDocument(new LengthDocument(16));//限制密码输入长度
		Init_UI.JTextFieldInit(repeatpass, "repeatpass");
		repeatpass.setDocument(new LengthDocument(16));//限制密码输入长度
		Init_UI.JTextFieldInit(usertel, "usertel");
		usertel.setDocument(new LengthDocument(13));//限制密码输入长度
		Init_UI.JButtonInit(submit, "submit");
		Init_UI.JButtonInit(close, "close");
	}
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("submit".equals(e.getComponent().getName())){
			if(Regex.RE_matching(String.valueOf(userpass.getPassword()),2) && String.valueOf(userpass.getPassword()).length()>6){
				if(String.valueOf(userpass.getPassword()).equals(String.valueOf(repeatpass.getPassword()))){
					User user = new User();
					user.setId(login_user.getId());
					user.setName(login_user.getName());
					user.setIdentity(login_user.getIdentity());
					user.setPass(String.valueOf(userpass.getPassword()));
					user.setTel(usertel.getText());
					ManageUser_Sev.update_pw(user);
					JOptionPane.showMessageDialog(null, "修改成功");
					mp_JD.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "密码不匹配,修改失败");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "密码格式不正确,须字母和数字构成,须大于6位且不能超过16位");  
			}
		}else if("close".equals(e.getComponent().getName())){
			mp_JD.dispose();
		}
	}
	public void mousePressed(MouseEvent e) {
		// 当鼠标按下的时候获得窗口当前的位置
		if("mp_JD".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}		
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
//	public static void main(String[] args){
//		User user = new User("经理","motian","765885195","15129******");
//		user.setId(9);
//		new ModifyPassword(null, user);
//	}
}
