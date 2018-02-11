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
	private JTextField username;//������ID��
	private JPasswordField password1,password2; //�����
	private JButton register,close; 
	private JFrame login,registerJF;
	private int value;//ͼƬ���ӵĲ���
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	
	
	public registerUI(JFrame login){
		//---------���س�ʼ��
		//����������
		value = 0;
		this.login = login;
	
		registerJF = new JFrame();
		registerJF.setSize(Constant.getRegisterUiSizeX(), Constant.getRegisterUiSizeY());//���ڴ�С
		InitUI.JFrameInit(registerJF,"registerJF");//���ڳ�ʼ��
		InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);//���ñ���
		registerJF.addMouseListener(this);
		registerJF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point p = registerJF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				registerJF.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		}); 
		
		
		username = new JTextField();//ID��
		username.setDocument(new MyDocument(16));//�����û������볤��
		username.setText(" ������ID/����");
		InitUI.JTextFieldInit(username,"username");
		username.addMouseListener(this);//�����˺ſ�
		
		password1 = new JPasswordField();//�����
		password1.setDocument(new MyDocument(16));//�����������볤��
		password1.setText(" ����������");
		InitUI.JPasswordField(password1,"password1");
		password1.addMouseListener(this);//���������
		
		password2 = new JPasswordField(" ���ٴ���������");//�����
		password2.setDocument(new MyDocument(16));//�����������볤��
		password2.setText(" ���ٴ���������");
		InitUI.JPasswordField(password2,"password2");
		password2.addMouseListener(this);//���������
		
		register = new JButton();
		InitUI.JButtonInit(register, "register");
		register.addMouseListener(this);//����ע���
		close = new JButton();
		InitUI.JButtonInit(close, "close");
		close.addMouseListener(this);
		
		JPanel JP = new JPanel(null);//�����ò��ַ�ʽ
		InitUI.JPanelInit(JP, "JP");
		JP.setSize(registerJF.getWidth(), registerJF.getHeight());//����СΪ���ڴ�С
			
	   //2--������ּ����
		username.setBounds(52, 213,187, 28);//ID�����ʼλ�����С
		JP.add(username);
		password1.setBounds(52, 271, 187, 28);//�������ʼλ�����С
		JP.add(password1);
		password2.setBounds(52, 330, 187, 28);//�������ʼλ�����С
		JP.add(password2);
		register.setBounds(73,401,145,35);//ע�������ʼλ�����С
		JP.add(register);
		close.setBounds(265, 0, 24, 22);//�ر���ʼλ�����С
		JP.add(close);
		
		registerJF.add(JP);	
		
		registerJF.setVisible(true);//���ڿɼ�
		login.setVisible(false);
	}

	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ���  
		if("username".equals(e.getComponent().getName())){//---------�û�ID��
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			if(" ������ID/����".equals(username.getText())){
				username.setFont(new Font("Arial",1,18));//���������ʽ-----��������
				username.setText("");
				username.setForeground(Color.black);//����ǰ��ɫΪ��
				username.setEditable(true);//��������
				username.setFocusable(true);//��ʾ���
				username.grabFocus();//��λ���
				
				//���������������ʱ��겻������������
				if("".equals(password1.getText())){
					InitUI.JPasswordField(password1, "password1");
	            	password1.setText(" ����������");
				}
				if("".equals(password2.getText())){
					InitUI.JPasswordField(password2, "password2");
	            	password2.setText(" ���ٴ���������");
				}
			}else{
				//��ӡ������û���
				System.out.println(username.getText());
			}
		}else if("password1".equals(e.getComponent().getName())){//--------�����
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			if(" ����������".equals(password1.getText())){
            	password1.setFont(new Font("serif",1,30));//���������ʽ
            	password1.setText("");
            	password1.setEchoChar('��');
            	password1.setForeground(Color.black);//����ǰ��ɫΪ��
            	password1.setEditable(true);//��������
            	password1.setFocusable(true);//��ʾ���
            	password1.grabFocus();//��λ���
            	
            	if("".equals(username.getText())){
            		InitUI.JTextFieldInit(username, "username");
    				username.setText(" ������ID/����");
    			}
            	if("".equals(password2.getText())){
            		InitUI.JPasswordField(password2, "password2");
	            	password2.setText(" ���ٴ���������");
				}
			}else{
				//��ӡ���������
			}
		}else if("password2".equals(e.getComponent().getName())){//----------ע��
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			 if(" ���ٴ���������".equals(password2.getText())){
				 password2.setFont(new Font("serif",1,30));//���������ʽ
				 password2.setText("");
				 password2.setEchoChar('��');
				 password2.setForeground(Color.black);//����ǰ��ɫΪ��
				 password2.setEditable(true);//��������
				 password2.setFocusable(true);//��ʾ���
				 password2.grabFocus();//��λ���
	            	
            	if("".equals(username.getText())){
            		InitUI.JTextFieldInit(username, "username");
    				username.setText(" ������ID/����");
    			}
            	if("".equals(password1.getText())){
            		InitUI.JPasswordField(password1, "password1");
					password1.setText(" ����������");
				}
			}else{
				//��ӡ���������
			}
		}else if("register".equals(e.getComponent().getName())){//---------�ύע����Ϣ
			InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiImg(),value++);
			
			if(" ������ID/����".equals(username.getText()) || "".equals(username.getText())){
				InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiFailImg(1),value++);
			}else if(" ����������".equals(password1.getText()) || " ���ٴ���������".equals(password2.getText()) ||
					"".equals(password1.getText()) || "".equals(password2.getText())){
				InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiFailImg(2),value++);
			}else if(Account.register(username.getText(),password1.getText(),password2.getText())){
				JOptionPane.showMessageDialog(null, "ע��ɹ�", "YYY", JOptionPane.ERROR_MESSAGE);

				InitUI.JTextFieldInit(username, "username");
				username.setText(" ������ID/����");
				InitUI.JPasswordField(password1, "password1");
				password1.setText(" ����������");
				InitUI.JPasswordField(password2, "password2");
            	password2.setText(" ���ٴ���������");
			}else{
				InitUI.JFrameBackdropInit(registerJF,Constant.getRegisterUiFailImg(2),value++);
			}
		}else if("close".equals(e.getComponent().getName())){//--------�ر�
			login.setVisible(true);//�����ڿɼ�
			registerJF.dispose();//���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ
		}  
	}

	  
    public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ���  
    }  
    public void mouseExited(MouseEvent e) {// ����뿪���ʱִ�еĲ���  
    	Component component = e.getComponent ();
    	if("username".equals(component.getName())){//�û�ID��
			if("".equals(username.getText())){
				InitUI.JTextFieldInit(username, "username");
				username.setText(" ������ID/����");
			}
		}else if("password1".equals(component.getName())){
            if("".equals(password1.getText())){
            	InitUI.JPasswordField(password1, "password1");
            	password1.setText(" ����������");
			}
		}else if("password2".equals(component.getName())){
            if("".equals(password2.getText())){
            	InitUI.JPasswordField(password2, "password2");
            	password2.setText(" ���ٴ���������");
			}
		}
    }  
    public void mousePressed(MouseEvent e) {//���������ϰ���ʱִ�еĲ���  
    	// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("registerJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
    }  
    public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  
    }   
}
