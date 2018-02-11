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

import org.omg.CORBA.portable.ValueInputStream;

import account.Account;
import constant.Constant;
import constant.MyDocument;

public class ticket implements MouseListener {
	private JFrame ticketJF;
	private JButton date;
	private JButton choice1,choice2,choice3,choice4,choice5,choice6;
	private JButton  seat,buy,close;
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	private int value;
	
	public ticket(){
		value =0 ;
		
		ticketJF = new JFrame();
		ticketJF.setSize(Constant.getTicketUiSizeX(), Constant.getTicketUiSizeY());//���ڴ�С
		InitUI.JFrameInit(ticketJF,"ticketJF");//���ڳ�ʼ��
		InitUI.JFrameBackdropInit(ticketJF,Constant.getHotTicketImg(1),value++);//���ñ���
		ticketJF.addMouseListener(this);
		ticketJF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point p = ticketJF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				ticketJF.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		}); 
		
		
		choice1 = new JButton();
		InitUI.JButtonInit(choice1, "choice1");
		choice1.addMouseListener(this);
		choice2 = new JButton();
		InitUI.JButtonInit(choice2, "choice2");
		choice2.addMouseListener(this);
		choice3 = new JButton();
		InitUI.JButtonInit(choice3, "choice3");
		choice3.addMouseListener(this);
		choice4 = new JButton();
		InitUI.JButtonInit(choice4, "choice4");
		choice4.addMouseListener(this);
		choice5 = new JButton();
		InitUI.JButtonInit(choice5, "choice5");
		choice5.addMouseListener(this);
		choice6 = new JButton();
		InitUI.JButtonInit(choice6, "choice6");
		choice6.addMouseListener(this);
		seat = new JButton();
		InitUI.JButtonInit(seat, "seat");
		seat.addMouseListener(this);
		buy = new JButton();
		InitUI.JButtonInit(buy, "buy");
		buy.addMouseListener(this);
		close = new JButton();
		InitUI.JButtonInit(close, "close");
		close.addMouseListener(this);
		
		JPanel JP = new JPanel(null);//�����ò��ַ�ʽ
		InitUI.JPanelInit(JP, "JP");
		JP.setSize(ticketJF.getWidth(), ticketJF.getHeight());//����СΪ���ڴ�С
			
	   //2--������ּ����
		choice1.setBounds(382, 164,71, 52);
		JP.add(choice1);
		choice2.setBounds(627, 164,71, 52);
		JP.add(choice2);
		choice3.setBounds(382, 240,71, 52);
		JP.add(choice3);
		choice4.setBounds(627, 240,71, 52);
		JP.add(choice4);
		choice5.setBounds(382, 315,71, 52);
		JP.add(choice5);
		choice6.setBounds(627, 315,71, 52);
		JP.add(choice6);
		seat.setBounds(269, 393,155, 40);
		JP.add(seat);
		buy.setBounds(513, 393,155, 40);
		JP.add(buy);
		close.setBounds(731, 0, 20, 20);//�ر���ʼλ�����С
		JP.add(close);
		
		ticketJF.add(JP);	
		ticketJF.setVisible(true);//���ڿɼ�
	}
	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ���  
		if("choice1".equals(e.getComponent().getName())){
			
		
		}else if("choice2".equals(e.getComponent().getName())){
			
		
		}else if("choice3".equals(e.getComponent().getName())){
			
		
		}else if("choice4".equals(e.getComponent().getName())){
			
		
		}else if("choice5".equals(e.getComponent().getName())){
			
		
		}else if("choice6".equals(e.getComponent().getName())){
			
		
		}else if("seat".equals(e.getComponent().getName())){
			
		
		}else if("buy".equals(e.getComponent().getName())){
			
		
		}else if("close".equals(e.getComponent().getName())){
			ticketJF.dispose();//���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ
		}
	}

	  
    public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ���  
    }  
    public void mouseExited(MouseEvent e) {// ����뿪���ʱִ�еĲ���  
    	
    }  
    public void mousePressed(MouseEvent e) {//���������ϰ���ʱִ�еĲ���  
    	// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("ticketJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
    }  
    public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  
    }  
}
