package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Basic.Default;
import Basic.Seat;
import Basic.Studio;
import Basic.User;
import server.ManageSeat_Sev;
import sun.print.resources.serviceui;

public class Seat_UI implements MouseListener {
	private JDialog seat_JD;//������
	private myJPanel_UI seat_Jp = new myJPanel_UI(Default.Get_Adm_Studio_Seat_BG());//�O�ñ���   �����
	private myJPanel_UI st_Jp = new myJPanel_UI(Default.Get_Adm_Studio_Seat());//�O�ñ���   �����

	private Studio studio;	
	private JButton update = new JButton();
	private JLabel studio_name = new JLabel();
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JButton close = new JButton();
	private JButton[][] seat_jb=new JButton[10][15];//��λ
	private int[][] seat_state;
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	public Seat_UI(JFrame jf,Studio studio, User login_user){
		this.studio = studio;
		seat_JD = new JDialog(jf, true);
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
		

		studio_name.setText(studio.getName());
		info.setText(login_user.getName());
//		info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		
		st_Jp.add(studio_name);
		st_Jp.add(update);
		st_Jp.add(close);
		st_Jp.add(info);
		st_Jp.add(datetime);
		st_Jp.add(seat_Jp);
		seat_JD.add(st_Jp);
		seat_JD.setVisible(true);//���ڿɼ�
	}
	private void listener() {
//		seat_JD.addMouseMotionListener(new MouseMotionAdapter() {
//			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
//			public void mouseDragged(MouseEvent e) {
//				// ������϶�ʱ��ȡ���ڵ�ǰλ��
//				Point tem = seat_JD.getLocation();
//				// ���ô��ڵ�λ��
//				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
//				seat_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
//			}
//		}); 
		seat_JD.addMouseListener(this);
		update.addMouseListener(this);
		close.addMouseListener(this);
		st_Jp.addMouseListener(this);
		
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				//��λ��Ϊ����  ��1�ո�2��ʾΪ1��2��
				seat_jb[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton jt = (JButton)e.getSource();
						String string=new String(((JButton)e.getSource()).getIcon().toString());
						String[] name = jt.getName().split(" ");
						
						if(string.equals(Default.Get_Path_Img_Adm_Studio_Seat(0))){
							jt.setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(1)));
							seat_state[Integer.valueOf(name[0])][Integer.valueOf(name[1])]=1;
						}else if(string.equals(Default.Get_Path_Img_Adm_Studio_Seat(1))){
							jt.setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(2)));
							seat_state[Integer.valueOf(name[0])][Integer.valueOf(name[1])]=2;
						}else{
							jt.setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(0)));
							seat_state[Integer.valueOf(name[0])][Integer.valueOf(name[1])]=0;
						}
						
						datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
					}
				});
			}
		}
	}
	private void setSize() {
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				seat_jb[i][j].setSize(35, 35);
			}
		}
		studio_name.setBounds(128, 90, 200, 30);
		update.setBounds(828, 207, 184, 39);
		info.setBounds(941, 26, 150, 20);
		datetime.setBounds(810, 55, 200, 20);
		close.setBounds(Default.Get_Window_X()-25,0, 25, 25);
	}
	private void Init() {
		Init_UI.JDialogInit(seat_JD, "seat_JD",Default.Get_Window_X(), Default.Get_Window_Y());
		Init_UI.JPanelInit(st_Jp, "st_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(seat_Jp, "seat_Jp",26,138,768,480);
		seat_Jp.setLayout(new GridLayout(10,16));//���񲼾�
		Init_UI.JButtonInit(update, "update");
		Init_UI.JLableInit(studio_name, "studio_name");
		studio_name.setFont(new Font("΢���ź�",0,25));
		studio_name.setForeground(Color.yellow);

		Init_UI.JLableInit(info, "info");
		Init_UI.JLableInit(datetime, "datetime");
		studio_name.setCursor(null);
		info.setCursor(null);
		datetime.setCursor(null);

		Init_UI.JButtonInit(close, "close");
		seat_state = ManageSeat_Sev.get_seat_state(studio);
		for(int i=1 ; i<=9 ; i++){
			for(int j=1 ; j<=14 ; j++){
				//��λ��Ϊ����  ��12��ʾΪ1��2��
				seat_jb[i][j] = new JButton();
				seat_Jp.add(seat_jb[i][j]);
				Init_UI.JButtonInit(seat_jb[i][j],String.valueOf(i)+" "+String.valueOf(j));
				if(seat_state[i][j]==0){
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(0)));
				}else if(seat_state[i][j]==1){
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(1)));
				}else{
					seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(2)));
				}
				
				if(j==3 || j==11){
					JButton tem = new JButton();
					Init_UI.JButtonInit(tem, "tem");
					seat_Jp.add(tem);
				}
			}
			if(i==4){
				for(int k=1 ; k<=16 ; k++){
					JButton tem = new JButton();
					Init_UI.JButtonInit(tem, "tem");
					seat_Jp.add(tem);
					
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("update".equals(e.getComponent().getName())){
			//�õ������λ
			int maxRow=0,maxCol=0;
			for(int i=1 ; i<=9 ; i++){
				for(int j=1 ; j<=14 ; j++){
					if(seat_state[i][j] != 1){
						maxRow = Math.max(maxRow, i);
						maxCol = Math.max(maxCol, j);
					}
				}
			}
			studio.setRow(maxRow);
			studio.setCol(maxCol);
			if(ManageSeat_Sev.update_seat(studio,seat_state)){
				JOptionPane.showMessageDialog(null, "���³ɹ�");  
			}else{
				JOptionPane.showMessageDialog(null, "������λ����ʹ����,�޷�����");  
			}
			seat_state = ManageSeat_Sev.get_seat_state(studio);
			for(int i=1 ; i<=9 ; i++){
				for(int j=1 ; j<=14 ; j++){
					//��λ��Ϊ����  ��12��ʾΪ1��2��
					if(seat_state[i][j]==0){
						seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(0)));
					}else if(seat_state[i][j]==1){
						seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(1)));
					}else{
						seat_jb[i][j].setIcon(new ImageIcon(Default.Get_Path_Img_Adm_Studio_Seat(2)));
					}
				}
			}
		}else if("close".equals(e.getComponent().getName())){
			seat_JD.dispose();//ѡ����λ
//			System.exit(0);
		}
	}
	public void mousePressed(MouseEvent e) {
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("seat_JD".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}		
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		if("st_Jp".equals(e.getComponent().getName())){//�����������ʱ��
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
			System.out.println("aads");
		}
	}

}
