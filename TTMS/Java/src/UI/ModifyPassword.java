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
	private JLabel modifypassword= new JLabel("�޸���Ϣ",JLabel.CENTER);

	private JPasswordField userpass = new JPasswordField(); //�����
	private JPasswordField repeatpass = new JPasswordField(); //�����
	private JTextField usertel = new JTextField();//name��

	private JButton submit = new JButton();
	private JDialog mp_JD;//������
	private myJPanel_UI mp_jp = new myJPanel_UI(Default.Get_Path_Img_PW());//�O�ñ���   �����
	private JButton close = new JButton();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	public ModifyPassword(JFrame f_jf,User user){
		this.login_user = user;
		mp_JD = new JDialog(f_jf, true);
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
		
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
		userpass.setDocument(new LengthDocument(16));//�����������볤��
		Init_UI.JTextFieldInit(repeatpass, "repeatpass");
		repeatpass.setDocument(new LengthDocument(16));//�����������볤��
		Init_UI.JTextFieldInit(usertel, "usertel");
		usertel.setDocument(new LengthDocument(13));//�����������볤��
		Init_UI.JButtonInit(submit, "submit");
		Init_UI.JButtonInit(close, "close");
	}
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
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
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					mp_JD.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "���벻ƥ��,�޸�ʧ��");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "�����ʽ����ȷ,����ĸ�����ֹ���,�����6λ�Ҳ��ܳ���16λ");  
			}
		}else if("close".equals(e.getComponent().getName())){
			mp_JD.dispose();
		}
	}
	public void mousePressed(MouseEvent e) {
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
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
//		User user = new User("����","motian","765885195","15129******");
//		user.setId(9);
//		new ModifyPassword(null, user);
//	}
}
