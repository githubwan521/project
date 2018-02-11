package UI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class InitUI{
	//---------窗口的初始化方式
	public static void JFrameInit(JFrame jf,String str){//主窗口,窗口名,窗口大小
		jf.setLayout(null);//清除布局方式
//		jf.setResizable(false);//不可改变大小
		jf.setName(str);//设置窗口名
		jf.setTitle(str);//窗口标题
		jf.setLocationRelativeTo(null);//起始位置为屏幕中央
		jf.setUndecorated(true);//去掉标题栏
	}
	//--------设置该窗口的背景
	public static void JFrameBackdropInit(JFrame jf,String str,int value){//主窗口,背景图路径---------图片加的层数
		JLabel imgLabel = new myJLabel(new ImageIcon(str).getImage());//得到背景标签
		imgLabel.setBounds(0, 0, jf.getWidth(),jf.getHeight());//设置背景标签在面版的显示位置与大小  
        jf.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE+value));//将背景标签添加到jfram的LayeredPane面板里。  
        ((JPanel)jf.getContentPane()).setOpaque(false); //将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。  
        
	}
	
//	//内部类用图片重绘背景
	public static class myJLabel extends JLabel {  
	       private Image image;  
	       public myJLabel(Image image){  
	            this.image = image;  
	       }   
	       public void paintComponent(Graphics g){
	            super.paintComponent(g);  
	            int x = this.getWidth();  
	            int y = this.getHeight();  
	            g.drawImage(image, 0, 0, x, y, null);  
	       }  
	 }
	//文本域初始化
	public static void JTextAreaInit (JTextArea in,String str){
		in.setOpaque(false);//设为透明
		in.setFont(new Font("serif",1,23));//设置字体格式
		in.setName(str);//设置文本域的名字
		in.setForeground(Color.gray);
		in.setEditable(false);//屏蔽输入
		in.setFocusable(false);//消除光标
		in.setLineWrap(true);//自动换行
	}
	//---------文本框的初始化
	public static void JTextFieldInit(JTextField username,String str){
		username.setOpaque(false);//设为透明
		username.setBorder(null);//去掉边框
		username.setFont(new Font("serif",0, 12));//设置字体格式
		username.setName(str);//设置文本框的名字
		username.setForeground(Color.gray);//设置前景色为灰
//		username.setBackground(Color.white);//设置背景色为白
		username.setEditable(false);//屏蔽输入
		username.setFocusable(false);//消除光标
	}
	//---------密码框的初始化
	public static void JPasswordField(JPasswordField password,String str){
		password.setOpaque(false);//设为透明
		password.setBorder(null);//去掉边框
		password.setFont(new Font("serif",0, 12));//设置字体格式
		password.setName(str);//设置文本框的名字
		password.setForeground(Color.gray);//设置前景色为灰
		password.setEchoChar((char)0);//设置为明文
		password.setEditable(false);//屏蔽输入
		password.setFocusable(false);//消除光标
	}
	//标签初始化
	public static void JLableInit(JLabel jl,String str){
		jl.setName(str);//设置文本框的名字
		jl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		jl.setForeground(Color.white);
	}
	//---------按键初始化
	public static void JButtonInit(JButton jb,String str){
		jb.setName(str);
		jb.setFocusable(false);//消除光标
		jb.setContentAreaFilled(false);//按键透明
//		jb.setIcon(new ImageIcon(""));//设置背景
//		jb.setBorder(null);//去掉边框
	}
	//---------面板初始化
	public static void JPanelInit(JPanel jp,String str){
		jp.setOpaque(false);//设置面板透明
		jp.setName(str);//设置面板名
	}
}
	
