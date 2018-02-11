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

//售票员的主界面
public class Manager_UI implements MouseListener {
	User login_user;//登陆进的用户

	//主窗口
	private JFrame Man_JF = new JFrame();
	private myJPanel_UI man_Jp = new myJPanel_UI(Default.Get_Path_Img_Man_Main());//設置背景   主面板
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JButton close = new JButton();
	private JButton play = new JButton();
	private JButton schedule = new JButton();
	private JButton salesdata = new JButton();

	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置

	public Manager_UI(User Manager){
		login_user = Manager;
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听

		//主窗口
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));

		man_Jp.add(info);
		man_Jp.add(datetime);
		man_Jp.add(close);
		man_Jp.add(play);
		man_Jp.add(schedule);
		man_Jp.add(salesdata);
		Man_JF.add(man_Jp);
		Man_JF.setVisible(true);
	}
	//组件的监听
	public void listener(){
		//主窗口
		info.addMouseListener(this);
		close.addMouseListener(this);
		play.addMouseListener(this);
		schedule.addMouseListener(this);
		salesdata.addMouseListener(this);
		Man_JF.addMouseListener(this);
		Man_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = Man_JF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				Man_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		});

	}
	//所有组件的初始化
	public void Init(){
		//主窗口
		Init_UI.JFrameInit(Man_JF,"Con_JF",832,535);
		Init_UI.JPanelInit(man_Jp, "man_Jp",0,0,832,535);
		Init_UI.JLableInit(info, "info");
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(play, "play");
		Init_UI.JButtonInit(schedule, "schedule");
		Init_UI.JButtonInit(salesdata, "salesdata");

	}
	//设置所有组件的大小与起始位置
	public void setSize(){
			//主窗口
		info.setBounds(744, 30, 150, 20);
		datetime.setBounds(650, 70, 200, 20);
		close.setBounds(832-25, 0, 25, 25);
		play.setBounds(169, 188, 150, 40);
		schedule.setBounds(497, 188, 150, 40);
		salesdata.setBounds(356, 338, 120, 90);

	}

	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作
		 if("play".equals(e.getComponent().getName())){
			new Play_UI(Man_JF,login_user);
		}else if("schedule".equals(e.getComponent().getName())){
			new Schedule_UI(Man_JF,login_user);
		}else if("salesdata".equals(e.getComponent().getName())){
			new SalesData_UI(Man_JF,login_user);
		}else if("info".equals(e.getComponent().getName())){
			new ModifyPassword(Man_JF, login_user);
		}else if("close".equals(e.getComponent().getName())){
			login_user.setStatus(0);
	    	ManageUser_Sev.update_status(login_user);
			System.exit(0);//退出程序
		}
	}

	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作
		// 当鼠标按下的时候获得窗口当前的位置
		if("Man_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作
	}

	public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作
	}

	public void mouseExited(MouseEvent e) {
	}



//	public static void main(String[] args){
//		User user = new User();
//		user.setId(9);
//		user.setIdentity("经理");
//		user.setName("motian");
//		new Manager_UI(user);
//	}
}
