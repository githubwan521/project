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
	//---------���ڵĳ�ʼ����ʽ
	public static void JFrameInit(JFrame jf,String str){//������,������,���ڴ�С
		jf.setLayout(null);//������ַ�ʽ
//		jf.setResizable(false);//���ɸı��С
		jf.setName(str);//���ô�����
		jf.setTitle(str);//���ڱ���
		jf.setLocationRelativeTo(null);//��ʼλ��Ϊ��Ļ����
		jf.setUndecorated(true);//ȥ��������
	}
	//--------���øô��ڵı���
	public static void JFrameBackdropInit(JFrame jf,String str,int value){//������,����ͼ·��---------ͼƬ�ӵĲ���
		JLabel imgLabel = new myJLabel(new ImageIcon(str).getImage());//�õ�������ǩ
		imgLabel.setBounds(0, 0, jf.getWidth(),jf.getHeight());//���ñ�����ǩ��������ʾλ�����С  
        jf.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE+value));//��������ǩ��ӵ�jfram��LayeredPane����  
        ((JPanel)jf.getContentPane()).setOpaque(false); //�����������Ϊ͸��������LayeredPane����еı���������ʾ������  
        
	}
	
//	//�ڲ�����ͼƬ�ػ汳��
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
	//�ı����ʼ��
	public static void JTextAreaInit (JTextArea in,String str){
		in.setOpaque(false);//��Ϊ͸��
		in.setFont(new Font("serif",1,23));//���������ʽ
		in.setName(str);//�����ı��������
		in.setForeground(Color.gray);
		in.setEditable(false);//��������
		in.setFocusable(false);//�������
		in.setLineWrap(true);//�Զ�����
	}
	//---------�ı���ĳ�ʼ��
	public static void JTextFieldInit(JTextField username,String str){
		username.setOpaque(false);//��Ϊ͸��
		username.setBorder(null);//ȥ���߿�
		username.setFont(new Font("serif",0, 12));//���������ʽ
		username.setName(str);//�����ı��������
		username.setForeground(Color.gray);//����ǰ��ɫΪ��
//		username.setBackground(Color.white);//���ñ���ɫΪ��
		username.setEditable(false);//��������
		username.setFocusable(false);//�������
	}
	//---------�����ĳ�ʼ��
	public static void JPasswordField(JPasswordField password,String str){
		password.setOpaque(false);//��Ϊ͸��
		password.setBorder(null);//ȥ���߿�
		password.setFont(new Font("serif",0, 12));//���������ʽ
		password.setName(str);//�����ı��������
		password.setForeground(Color.gray);//����ǰ��ɫΪ��
		password.setEchoChar((char)0);//����Ϊ����
		password.setEditable(false);//��������
		password.setFocusable(false);//�������
	}
	//��ǩ��ʼ��
	public static void JLableInit(JLabel jl,String str){
		jl.setName(str);//�����ı��������
		jl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����Ϊ��
		jl.setForeground(Color.white);
	}
	//---------������ʼ��
	public static void JButtonInit(JButton jb,String str){
		jb.setName(str);
		jb.setFocusable(false);//�������
		jb.setContentAreaFilled(false);//����͸��
//		jb.setIcon(new ImageIcon(""));//���ñ���
//		jb.setBorder(null);//ȥ���߿�
	}
	//---------����ʼ��
	public static void JPanelInit(JPanel jp,String str){
		jp.setOpaque(false);//�������͸��
		jp.setName(str);//���������
	}
}
	
