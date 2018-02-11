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
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private int value;
	
	public ticket(){
		value =0 ;
		
		ticketJF = new JFrame();
		ticketJF.setSize(Constant.getTicketUiSizeX(), Constant.getTicketUiSizeY());//窗口大小
		InitUI.JFrameInit(ticketJF,"ticketJF");//窗口初始化
		InitUI.JFrameBackdropInit(ticketJF,Constant.getHotTicketImg(1),value++);//设置背景
		ticketJF.addMouseListener(this);
		ticketJF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = ticketJF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
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
		
		JPanel JP = new JPanel(null);//不适用布局方式
		InitUI.JPanelInit(JP, "JP");
		JP.setSize(ticketJF.getWidth(), ticketJF.getHeight());//面板大小为窗口大小
			
	   //2--组件布局及添加
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
		close.setBounds(731, 0, 20, 20);//关闭起始位置与大小
		JP.add(close);
		
		ticketJF.add(JP);	
		ticketJF.setVisible(true);//窗口可见
	}
	
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作  
		if("choice1".equals(e.getComponent().getName())){
			
		
		}else if("choice2".equals(e.getComponent().getName())){
			
		
		}else if("choice3".equals(e.getComponent().getName())){
			
		
		}else if("choice4".equals(e.getComponent().getName())){
			
		
		}else if("choice5".equals(e.getComponent().getName())){
			
		
		}else if("choice6".equals(e.getComponent().getName())){
			
		
		}else if("seat".equals(e.getComponent().getName())){
			
		
		}else if("buy".equals(e.getComponent().getName())){
			
		
		}else if("close".equals(e.getComponent().getName())){
			ticketJF.dispose();//隐藏当前窗口，并释放窗体占有的其他资源
		}
	}

	  
    public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作  
    }  
    public void mouseExited(MouseEvent e) {// 鼠标离开组件时执行的操作  
    	
    }  
    public void mousePressed(MouseEvent e) {//鼠标在组件上按下时执行的操作  
    	// 当鼠标按下的时候获得窗口当前的位置
		if("ticketJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
    }  
    public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  
    }  
}
