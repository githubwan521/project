package ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

import Basic.Default;
import Basic.Play;
import Basic.User;
import oracle.jrockit.jfr.events.RequestableEventEnvironment;
import server.ManageAnalysis_Sev;
import server.ManagePlay_Sev;
import server.ManageSchedule_Sev;
import server.ManageUser_Sev;

public class SalesData_UI  implements MouseListener{
	User login_user;//登陆进的用户
	JFrame fJFrame;

	private TimeBox start = new TimeBox();
	private TimeBox end = new TimeBox();

	private String[][] sd_play_Data;
	private DefaultTableModel sd_play_Model;
	private JTable sd_play_JT = new JTable();//用户表格
	private JScrollPane sd_play_JS = new JScrollPane();//設置背景   主面板

	private String[][] sd_user_Data;
	private DefaultTableModel sd_user_Model;
	private JTable sd_user_JT = new JTable();//用户表格
	private JScrollPane sd_user_JS = new JScrollPane();//設置背景   主面板

	private Choice sd_user = new Choice();
	private Choice sd_play = new Choice();
	private JButton query = new JButton();
	private JDialog SD_JF;
	private myJPanel_UI sd_Jp = new myJPanel_UI(Default.Get_Path_Img_salesData());//設置背景   主面板
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JButton close = new JButton();
	private static Point origin = new Point();   //全局的位置变量，用于表示鼠标在窗口上的位置

	public SalesData_UI(JFrame con_JF, User Conductor) {
		this.fJFrame = con_JF;
		login_user = Conductor;
		SD_JF = new JDialog(con_JF,true);
		this.Init();
		this.setSize();
		this.listener();

		info.setText(login_user.getName());
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));

		sd_Jp.add(start);
		sd_Jp.add(end);

		sd_Jp.add(info);
		sd_Jp.add(datetime);
		sd_Jp.add(close);
		sd_Jp.add(sd_user);
		sd_Jp.add(sd_play);
		sd_Jp.add(query);
		sd_user_JS.getViewport().add(sd_user_JT);
		sd_play_JS.getViewport().add(sd_play_JT);
		sd_Jp.add(sd_play_JS);
		sd_Jp.add(sd_user_JS);
		SD_JF.add(sd_Jp);
		SD_JF.setVisible(true);
	}

	private void listener() {
		close.addMouseListener(this);
	    query.addMouseListener(this);
	    SD_JF.addMouseListener(this);
	    SD_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = SD_JF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				SD_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		});

	}

	private void setSize() {
		info.setBounds(944, 25, 155, 25);
		datetime.setBounds(830, 64, 200, 20);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);

		sd_play_JS.setBounds(27, 94, 270, Default.Get_Window_Y()-130);
		sd_user_JS.setBounds(753, 94, 270,Default.Get_Window_Y()-130 );
		start.setBounds(530, 143, 130, 37);
		end.setBounds(530, 200, 130, 37);

		sd_play.setBounds(516, 312, 150, 30);
		sd_user.setBounds(516, 368, 150, 30);
		query.setBounds(415, 525, 220, 45);

	}

	private void Init() {
		//主窗口
		Init_UI.JDialogInit(SD_JF, "SD_JF", Default.Get_Window_X(), Default.Get_Window_Y());
		Init_UI.JPanelInit(sd_Jp, "sd_Jp", 0 , 0, Default.Get_Window_X(), Default.Get_Window_Y());
//		Init_UI.JPanelInit(start, "start",539, 179, 107, 30);
//		Init_UI.JPanelInit(end, "end",639, 279, 107, 30);

		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//日期进入不为手
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手

		Init_UI.JButtonInit(start, "start");
		Init_UI.JButtonInit(end, "end");

		Init_UI.JButtonInit(close, "close");
		Init_UI.JScrollPaneInit(sd_user_JS,"sd_user_JS");
		Init_UI.JScrollPaneInit(sd_play_JS,"sd_play_JS");
		Init_UI.JTableInit(sd_user_JT,"sd_user_JT");
		Init_UI.JTableInit(sd_play_JT,"sd_play_JT" );

		sd_play_Data = ManageAnalysis_Sev.analysisAllplay_sev(login_user,start.getText(),end.getText());
		sd_play_Model = new DefaultTableModel(sd_play_Data,Default.Get_Play_SellData_ColumnNames()){
				public boolean isCellEditable(int row,int column){
					return false;
				}
		};
		sd_user_Data = ManageAnalysis_Sev.analysisAllSaler_sev(login_user,start.getText(),end.getText());
		sd_user_Model = new DefaultTableModel(sd_user_Data,Default.Get_User_SellData_ColumnNames()){
					public boolean isCellEditable(int row,int column){
						return false;
					}
		};
		sd_play_JT.setModel(sd_play_Model);
		sd_user_JT.setModel(sd_user_Model);

		Init_UI.JButtonInit(query, "query");
		Init_UI.ChoiceInit(sd_user, "sd_user");
		sd_user.add("ALL");
		if(login_user.getIdentity().equals("经理")){
			String[] user = ManageUser_Sev.get_user();
			for(int i=0 ; i<user.length ; i++){
				sd_user.add(user[i]);
			}
		}else{
			sd_user.add(login_user.getName());
		}
		Init_UI.ChoiceInit(sd_play, "sd_play");
		sd_play.add("ALL");
		String[] play = ManageSchedule_Sev.get_play();
		for(int i=0 ; i<play.length ; i++){
			sd_play.add(play[i]);
		}
	}

	public void mouseClicked(MouseEvent e) {
		 if("query".equals(e.getComponent().getName())){
			 if(sd_play.getSelectedObjects().toString().equals("ALL") && sd_user.getSelectedObjects().toString().equals("ALL")){
//				sd_play_Data = ManageAnalysis_Sev.'方法名'(login_user,"ALL","ALL",start.getText(),end.getText())[0];
//				sd_user_Data = ManageAnalysis_Sev.'方法名'(login_user,"ALL","ALL",start.getText(),end.getText())[1];
			 }else if(sd_play.getSelectedObjects().toString().equals("ALL")){
//				 sd_play_Data = ManageAnalysis_Sev.'方法名'(login_user,"ALL",sd_user.getSelectedObjects().toString(),start.getText(),end.getText())[0];
//				 sd_user_Data = ManageAnalysis_Sev.'方法名'(login_user,"ALL",sd_user.getSelectedObjects().toString(),start.getText(),end.getText())[1];
			 }else if(sd_user.getSelectedObjects().toString().equals("ALL")){
//				 sd_play_Data = ManageAnalysis_Sev.'方法名'(login_user,sd_play.getSelectedObjects().toString(),"ALL",start.getText(),end.getText())[0];
//				 sd_user_Data = ManageAnalysis_Sev.'方法名'(login_user,sd_play.getSelectedObjects().toString(),"ALL",start.getText(),end.getText())[1];
		 	 }else{
//		 		sd_play_Data = ManageAnalysis_Sev.'方法名'(login_user,sd_play.getSelectedObjects().toString(),sd_user.getSelectedObjects().toString(),start.getText(),end.getText())[0];
//				sd_user_Data = ManageAnalysis_Sev.'方法名'(login_user,sd_play.getSelectedObjects().toString(),sd_user.getSelectedObjects().toString(),start.getText(),end.getText())[1];

		 	 }
			 sd_play_Model = new DefaultTableModel(sd_play_Data,Default.Get_Play_SellData_ColumnNames()){
					public boolean isCellEditable(int row,int column){
						return false;
					}
			 };
			 sd_user_Model = new DefaultTableModel(sd_user_Data,Default.Get_User_SellData_ColumnNames()){
				public boolean isCellEditable(int row,int column){
					return false;
				}
			 };
			 sd_play_JT.setModel(sd_play_Model);
			 sd_user_JT.setModel(sd_user_Model);
		 }else if("close".equals(e.getComponent().getName())){
			 SD_JF.dispose();
		 }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
	// 当鼠标按下的时候获得窗口当前的位置
		if("SD_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}
	public void mouseReleased(MouseEvent e) {
		if("sd_Jp".equals(e.getComponent().getName())){//进入组件更新时间
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	public static void main(String[] args){
		User user = new User();
		user.setId(3);
		user.setIdentity("经理");
		user.setName("motian");
		new SalesData_UI(null, user);
	}

}
