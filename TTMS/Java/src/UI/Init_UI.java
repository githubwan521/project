package ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


//����UI�����Ĭ�ϳ�ʼ��

public class Init_UI{
	//---------���ڵĳ�ʼ����ʽ
	public static void JFrameInit(JFrame jf,String str,int x,int y){//������,������,���ڴ�С
		jf.setLayout(null);//������ַ�ʽ
//		jf.setResizable(false);//���ɸı��С
		jf.setSize(x, y);
		jf.setName(str);//���ô�����
		jf.setTitle(str);//���ڱ���
		jf.setLocationRelativeTo(null);//��ʼλ��Ϊ��Ļ����
		jf.setUndecorated(true);//ȥ��������
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ
//		jf.setVisible(true);//���ڿɼ�

	}
	public static void JDialogInit(JDialog jd,String str,int x,int y){//������,������,���ڴ�С
		jd.setLayout(null);//������ַ�ʽ
//		jf.setResizable(false);//���ɸı��С
		jd.setSize(x, y);
		jd.setName(str);//���ô�����
		jd.setTitle(str);//���ڱ���
		jd.setLocationRelativeTo(null);//��ʼλ��Ϊ��Ļ����
		jd.setUndecorated(true);//ȥ��������
//		jd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ

	}

	//�ı����ʼ��
	public static void JTextAreaInit (JTextArea in,String str){
		in.setOpaque(false);//��Ϊ͸��
		in.setFont(new Font("serif",1,20));//���������ʽ
		in.setName(str);//�����ı��������
		in.setForeground(Color.black);
//		in.setEditable(false);//��������
//		in.setFocusable(false);//�������
		in.setLineWrap(true);//�Զ�����

	}
	//---------�ı���ĳ�ʼ��
	public static void JTextFieldInit(JTextField username,String str){
		username.setOpaque(false);//��Ϊ͸��
		username.setBorder(null);//ȥ���߿�
		username.setFont(new Font("΢���ź�",1, 21));//���������ʽ
		username.setName(str);//�����ı��������
		username.setForeground(Color.black);//����ǰ��ɫΪ��
		username.setBackground(Color.white);//���ñ���ɫΪ��
//		username.setEditable(false);//��������
//		username.setFocusable(false);//�������
//		username.setDocument(new MyDocument(16));//�����������볤��

	}
	//---------�����ĳ�ʼ��
	public static void JPasswordField(JPasswordField password,String str){
		password.setOpaque(false);//��Ϊ͸��
		password.setBorder(null);//ȥ���߿�
		password.setFont(new Font("serif",0, 21));//���������ʽ
		password.setName(str);//�����ı��������
		password.setForeground(Color.black);//����ǰ��ɫΪ��
		password.setBackground(Color.white);//���ñ���ɫΪ��
//		password.setEchoChar((char)0);//����Ϊ����
//		password.setEditable(false);//��������
//		password.setFocusable(false);//�������
//		password.setDocument(new MyDocument(16));//�����������볤��

	}
	//��ǩ��ʼ��
	public static void JLableInit(JLabel jl,String str){
		jl.setName(str);//�����ı��������
//		jl.setFont(new Font("serif",0,20));
		jl.setFont(new Font("΢���ź�",0,20));

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
	public static void JPanelInit(JPanel jp,String str,int x,int y,int size_X,int size_Y){
//		jp.setOpaque(false);//�������͸��
		jp.setName(str);//���������
		jp.setSize(size_X,size_Y);
		jp.setLocation(x, y);

		jp.setLayout(null);//������Ĳ��ַ�ʽ
	}
	//---------��������ʼ��
	public static void JScrollPaneInit(JScrollPane jsp,String str){
		jsp.setOpaque(false);//�������͸��
		jsp.getViewport().setOpaque(false);//���͸��
		jsp.setName(str);//���������
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		jsp.setLayout(null);//������Ĳ��ַ�ʽ
	}
	//---------����ʼ��
	public static void JTableInit(JTable jt,String str){
		jt.setName(str);
		jt.setFont(new Font("serif",0,18));//��������
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		tcr.setHorizontalAlignment(JLabel.CENTER);
		jt.setDefaultRenderer(Object.class, tcr);
//		jt.setForeground(Color.white);//����������ɫ
		jt.setOpaque(false);//���͸��
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        JTableHeader header = jt.getTableHeader();//��ȡͷ��   
        header.setPreferredSize(new Dimension(30, 28));   //��С���
        header.setOpaque(false);//����ͷ��Ϊ͸��  
        header.getTable().setOpaque(false);//����ͷ������ı��͸��  
        header.setDefaultRenderer(renderer);  
        TableCellRenderer headerRenderer = header.getDefaultRenderer();   
        if (headerRenderer instanceof JComponent){
        	//���־���
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);   
            ((JComponent) headerRenderer).setOpaque(false);   
        }
		jt.setRowHeight(40);       
		jt.getTableHeader().setReorderingAllowed(false);//�����϶���
	    jt.setIntercellSpacing(new Dimension(0, 0));   

	}

	public static void ChoiceInit(Choice choice, String str) {
		choice.setName(str);
		choice.setFont(new Font("serif",0,17));

	//		choice.setBackground(Color.red);
		
	}
}
	

