package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import constant.Constant;
import constant.MyDocument;

public class user  extends JFrame implements MouseListener {
	private myJLabel homepage,aboutus;//��ҳ    �������� 
	private myJLabel hotmovie,soonmovie,personal;//������ӳ     ������ӳ   ��������
	private JLabel img;//��ҳ��ͼƬ
	private JLabel tem1,tem2,tem3;//��ҳ�İ�ť��ǩ
	private JButton hot1,hot2,hot3,hot4,hot5,hot6;//������ӳ��ͼƬ��ǩ
	private JButton soon1,soon2,soon3,soon4,soon5,soon6;//������ӳ��ͼƬ��ǩ
	private JButton searchbt,close,send;//����,�ر�,����
	private JButton usercenter1,usercenter2,usercenter3,usercenter4,usercenter5,usercenter6;//����,�ر�,����
	private JButton hotlastpage,hotnextpage,soonlastpage,soonnextpage;//�ر�
	private JTextField search,name,email,telph;//ID��
	private JTextArea in;
	private JFrame userJF;
	private JPanel JP;//���  
	private int value, hotreplace;//ͼƬ���ӵĲ���  ��Ӱ���滻����
 	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
//    private Timer timer=new Timer(500,new TimerAction());
	
	public user(){
		value = 0;
		hotreplace=1;
		
		userJF = new JFrame();
		userJF.setSize(Constant.getUserUiSizeX(), Constant.getUserUiSizeY());//���ڴ�С
		InitUI.JFrameInit(userJF,"userJF");//���ڳ�ʼ��
		InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(2),value++);//���ñ���
		userJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ
		
		userJF.addMouseListener(this);
		userJF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = userJF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				userJF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		
		
		//Ĭ�ϳ�ʼ��
		homepage = new myJLabel("��ҳ",JLabel.CENTER);
		InitUI.JLableInit(homepage, "homepage");
		homepage.addMouseListener(this);//����ע���
		hotmovie = new myJLabel("������ӳ",JLabel.CENTER);
		InitUI.JLableInit(hotmovie, "hotmovie");
		hotmovie.addMouseListener(this);
		soonmovie = new myJLabel("������ӳ",JLabel.CENTER);
		InitUI.JLableInit(soonmovie, "soonmovie");
		soonmovie.addMouseListener(this);
		personal = new myJLabel("��������",JLabel.CENTER);
		InitUI.JLableInit(personal, "personal");
		personal.addMouseListener(this);
		aboutus = new myJLabel("��������",JLabel.CENTER);
		InitUI.JLableInit(aboutus, "aboutus");
		aboutus.addMouseListener(this);
		fontInit();//�����ʼ��
		homepage.setFlag(true);//Ĭ��Ϊ��ҳ
		homepage.setFont((new Font("΢���ź�",0,26)));//��������
		homepage.setForeground(Color.red);
	
		search = new JTextField();//ID��
		search.setDocument(new MyDocument(16));//�����û������볤��
		search.setText(" ������ӰƬ��");
		InitUI.JTextFieldInit(search,"search");
		search.setFont(new Font("serif",0, 14));//���������ʽ
		search.addMouseListener(this);//�����˺ſ�
		name = new JTextField();
		name.setDocument(new MyDocument(10));//�����û������볤��
		InitUI.JTextFieldInit(name,"name");
		name.setFont(new Font("serif",0, 14));//���������ʽ
		name.addMouseListener(this);//�����˺ſ�
		email = new JTextField();
		email.setDocument(new MyDocument(20));//�����û������볤��
		InitUI.JTextFieldInit(email,"email");
		email.setFont(new Font("serif",0, 14));//���������ʽ
		email.addMouseListener(this);
		telph = new JTextField();
		telph.setDocument(new MyDocument(13));//�����û������볤��
		InitUI.JTextFieldInit(telph,"telph");
		telph.setFont(new Font("serif",0, 14));//���������ʽ
		telph.addMouseListener(this);
		in = new JTextArea("���飺");
		InitUI.JTextAreaInit(in,"in");
		in.addMouseListener(this);
		
		
		//�½�ʱ�ĳ�ʼ��
		tem1 = new JLabel("",JLabel.CENTER);
		InitUI.JLableInit(tem1, "tem1");
		tem1.addMouseListener(this);
		tem2 = new JLabel("",JLabel.CENTER);
		InitUI.JLableInit(tem2, "tem2");
		tem2.addMouseListener(this);
		tem3 = new JLabel("",JLabel.CENTER);
		InitUI.JLableInit(tem3, "tem3");
		tem3.addMouseListener(this);
		
		img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(2)).getImage());
		img.setName("img");
		img.addMouseListener(this);
		
		
		close = new JButton();
		InitUI.JButtonInit(close, "close");
		close.addMouseListener(this);
		searchbt = new JButton();
		InitUI.JButtonInit(searchbt, "searchbt");
		searchbt.addMouseListener(this);
		
		hotlastpage = new JButton();
		InitUI.JButtonInit(hotlastpage, "hotlastpage");
		hotlastpage.setBorder(null);//ȥ���߿�
		hotlastpage.addMouseListener(this);
		hotnextpage = new JButton();
		InitUI.JButtonInit(hotnextpage, "hotnextpage");
		hotnextpage.setBorder(null);//ȥ���߿�
		hotnextpage.addMouseListener(this);
		hot1 = new JButton();
		InitUI.JButtonInit(hot1, "hot1");
		hot1.addMouseListener(this);
		hot2 = new JButton();
		InitUI.JButtonInit(hot2, "hot2");
		hot2.addMouseListener(this);
		hot3 = new JButton();
		InitUI.JButtonInit(hot3, "hot3");
		hot3.addMouseListener(this);
		hot4 = new JButton();
		InitUI.JButtonInit(hot4, "hot4");
		hot4.addMouseListener(this);
		hot5 = new JButton();
		InitUI.JButtonInit(hot5, "hot5");
		hot5.addMouseListener(this);
		hot6 = new JButton();
		InitUI.JButtonInit(hot6, "hot6");
		hot6.addMouseListener(this);
		soonlastpage = new JButton();
		InitUI.JButtonInit(soonlastpage, "soonlastpage");
		soonlastpage.setBorder(null);//ȥ���߿�
		soonlastpage.addMouseListener(this);
		soonnextpage = new JButton();
		InitUI.JButtonInit(soonnextpage, "soonnextpage");
		soonnextpage.setBorder(null);//ȥ���߿�
		soonnextpage.addMouseListener(this);
		soon1 = new JButton();
		InitUI.JButtonInit(soon1, "soon1");
		soon1.addMouseListener(this);
		soon2 = new JButton();
		InitUI.JButtonInit(soon2, "soon2");
		soon2.addMouseListener(this);
		soon3 = new JButton();
		InitUI.JButtonInit(soon3, "soon3");
		soon3.addMouseListener(this);
		soon4 = new JButton();
		InitUI.JButtonInit(soon4, "soon4");
		soon4.addMouseListener(this);
		soon5 = new JButton();
		InitUI.JButtonInit(soon5, "soon5");
		soon5.addMouseListener(this);
		soon6 = new JButton();
		InitUI.JButtonInit(soon6, "soon6");
		soon6.addMouseListener(this);
		send = new JButton();
		InitUI.JButtonInit(send, "send");
		send.addMouseListener(this);
		usercenter1 = new JButton();
		InitUI.JButtonInit(usercenter1, "usercenter1");
		usercenter1.addMouseListener(this);
		usercenter2 = new JButton();
		InitUI.JButtonInit(usercenter2, "usercenter2");
		usercenter2.addMouseListener(this);
		usercenter3 = new JButton();
		InitUI.JButtonInit(usercenter3, "usercenter3");
		usercenter3.addMouseListener(this);
		usercenter4 = new JButton();
		InitUI.JButtonInit(usercenter4, "usercenter4");
		usercenter4.addMouseListener(this);
		usercenter5 = new JButton();
		InitUI.JButtonInit(usercenter5, "usercenter5");
		usercenter5.addMouseListener(this);
		usercenter6 = new JButton();
		InitUI.JButtonInit(usercenter6, "usercenter6");
		usercenter6.addMouseListener(this);
	
		
		
		//���������еľ��Բ���
		//1--�������
        JP = new JPanel(null);//��ʹ�ò��ַ�ʽ
        InitUI.JPanelInit(JP, "JP");
		JP.setBounds(0,0,userJF.getWidth(),userJF.getHeight());//����СΪ���ڴ�С
		
//		2--������ּ����
		search.setBounds(58,181, 116, 23);
		JP.add(search);
		searchbt.setBounds(173, 181, 25, 23);
		JP.add(searchbt);
		homepage.setBounds(46,235, 170, 30);//��5
		JP.add(homepage);
		hotmovie.setBounds(43, 305, 170, 30);//������ӳ
		JP.add(hotmovie);
		soonmovie.setBounds(43,375,170,30);//������ӳ
		JP.add(soonmovie);
		personal.setBounds(43,445,170,30);//��������
		JP.add(personal);
		aboutus.setBounds(43,515,170,30);//��������
		JP.add(aboutus);
		tem1.setBounds(549,567,19,19);//��ҳ��ҳ��ť
		JP.add(tem1);
		tem2.setBounds(621,567,19,19);
		JP.add(tem2);
		tem3.setBounds(695,567,19,19);
		JP.add(tem3);
		img.setBounds(262,83,737,454);
		JP.add(img);
		hotlastpage.setBounds(239,258,35,112);
		JP.add(hotlastpage);
		hotnextpage.setBounds(984,257,35,112);
		JP.add(hotnextpage);
		hot1.setBounds(301,39,172,253);
		JP.add(hot1);
		hot2.setBounds(542,39,175,251);
		JP.add(hot2);
		hot3.setBounds(787,39,175,254);
		JP.add(hot3);
		hot4.setBounds(298,344,178,251);
		JP.add(hot4);
		hot5.setBounds(543,344,176,251);
		JP.add(hot5);
		hot6.setBounds(787,344,175,252);
		JP.add(hot6);
		soonlastpage.setBounds(239,258,35,112);
		JP.add(soonlastpage);
		soonnextpage.setBounds(984,257,35,112);
		JP.add(soonnextpage);
		soon1.setBounds(301,39,172,253);
		JP.add(soon1);
		soon2.setBounds(542,39,175,251);
		JP.add(soon2);
		soon3.setBounds(787,39,175,254);
		JP.add(soon3);
		soon4.setBounds(298,344,178,251);
		JP.add(soon4);
		soon5.setBounds(543,344,176,251);
		JP.add(soon5);
		soon6.setBounds(787,344,175,252);
		JP.add(soon6);
		usercenter1.setBounds(296,186,202,49);
		JP.add(usercenter1);
		usercenter2.setBounds(296,257,202,49);
		JP.add(usercenter2);
		usercenter3.setBounds(296,330,202,49);
		JP.add(usercenter3);
		usercenter4.setBounds(296,404,202,49);
		JP.add(usercenter4);
		usercenter5.setBounds(296,476,202,49);
		JP.add(usercenter5);
		usercenter6.setBounds(296,547,202,49);
		JP.add(usercenter6);
		name.setBounds(387,171, 223, 31);
		JP.add(name);
		email.setBounds(387,223, 223, 31);
		JP.add(email);
		telph.setBounds(387,276, 223, 31);
		JP.add(telph);
		in.setBounds(276,334, 334,221);
		JP.add(in);//������
		send.setBounds(651,512,158,43);
		JP.add(send);
		close.setBounds(1029, 1, 20, 21);//�ر���ʼλ�����С
		JP.add(close);
		
		hidebt();
		img.setVisible(true);
		tem1.setVisible(true);
		tem2.setVisible(true);
		tem3.setVisible(true);
		
		userJF.add(JP);
		userJF.setVisible(true);//���ڿɼ�
	}

	private void fontInit(){//�����ʼ��
		homepage.setFont((new Font("΢���ź�",0,20)));//��������
		homepage.setForeground(Color.white);
		homepage.setFlag(false);
		hotmovie.setFont((new Font("΢���ź�",0,20)));//��������
		hotmovie.setForeground(Color.white);
		hotmovie.setFlag(false);
		soonmovie.setFont((new Font("΢���ź�",0,20)));//��������
		soonmovie.setForeground(Color.white);
		soonmovie.setFlag(false);
		personal.setFont((new Font("΢���ź�",0,20)));//��������
		personal.setForeground(Color.white);
		personal.setFlag(false);
		aboutus.setFont((new Font("΢���ź�",0,20)));//��������
		aboutus.setForeground(Color.white);
		aboutus.setFlag(false);
	}
	
	//���ز���
	private void hidebt(){
		img.setVisible(false);
		tem1.setVisible(false);
		tem2.setVisible(false);
		tem3.setVisible(false);
		hotlastpage.setVisible(false);
		hotnextpage.setVisible(false);
		hot1.setVisible(false);
		hot2.setVisible(false);
		hot3.setVisible(false);
		hot4.setVisible(false);
		hot5.setVisible(false);
		hot6.setVisible(false);
		soonlastpage.setVisible(false);
		soonnextpage.setVisible(false);
		soon1.setVisible(false);
		soon2.setVisible(false);
		soon3.setVisible(false);
		soon4.setVisible(false);
		soon5.setVisible(false);
		soon6.setVisible(false);
		usercenter1.setVisible(false);
		usercenter2.setVisible(false);
		usercenter3.setVisible(false);
		usercenter4.setVisible(false);
		usercenter5.setVisible(false);
		usercenter6.setVisible(false);
		name.setVisible(false);
		email.setVisible(false);
		telph.setVisible(false);
		in.setVisible(false);
		send.setVisible(false);
	}
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("homepage".equals( e.getComponent().getName())){
			fontInit();
			userJF.remove(homepage);
			homepage.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(2),value++);//���ñ���
			homepage.setFont((new Font("΢���ź�",0,25)));//��������
    		homepage.setForeground(Color.red);
    		
    		//������
    		hidebt();
			img.setVisible(true);
			tem1.setVisible(true);
			tem2.setVisible(true);
			tem3.setVisible(true);
			JP.repaint();//ˢ��
    		
		}else if("hotmovie".equals(e.getComponent().getName())){
			fontInit();
			hotmovie.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(21),value++);//���ñ���
			hotmovie.setFont((new Font("΢���ź�",0,25)));//��������
			hotmovie.setForeground(Color.orange);

			hidebt();
			hot1.setVisible(true);
			hot2.setVisible(true);
			hot3.setVisible(true);
			hot4.setVisible(true);
			hot5.setVisible(true);
			hot6.setVisible(true);
			hotlastpage.setVisible(true);
			hotnextpage.setVisible(true);
			JP.repaint();//ˢ��
			
		}else if("soonmovie".equals( e.getComponent().getName())){
			fontInit();
			soonmovie.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(31),value++);//���ñ���
			soonmovie.setFont((new Font("΢���ź�",0,25)));//��������
			soonmovie.setForeground(Color.yellow);
    		
			hidebt();
			soon1.setVisible(true);
			soon2.setVisible(true);
			soon3.setVisible(true);
			soon4.setVisible(true);
			soon5.setVisible(true);
			soon6.setVisible(true);
			soonlastpage.setVisible(true);
			soonnextpage.setVisible(true);
			JP.repaint();//ˢ��
			
		}else if("personal".equals( e.getComponent().getName())){
			fontInit();
			personal.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(4),value++);//���ñ���
			personal.setFont((new Font("΢���ź�",0,25)));//��������
			personal.setForeground(Color.green);
			
			hidebt();
			usercenter1.setVisible(true);
			usercenter2.setVisible(true);
			usercenter3.setVisible(true);
			usercenter4.setVisible(true);
			usercenter5.setVisible(true);
			usercenter6.setVisible(true);
			JP.repaint();//ˢ��
		}else if("aboutus".equals( e.getComponent().getName())){
			fontInit();
			aboutus.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(5),value++);//���ñ���
			aboutus.setFont((new Font("΢���ź�",0,25)));//��������
			aboutus.setForeground(Color.pink);
    		
			hidebt();
			name.setVisible(true);
			email.setVisible(true);
			telph.setVisible(true);
			in.setVisible(true);
			send.setVisible(true);
			JP.repaint();//ˢ��
		}else if("search".equals(e.getComponent().getName())){//--------�ر�
			if(" ������ӰƬ��".equals(search.getText())){
				search.setText("");
				search.setForeground(Color.black);//����ǰ��ɫΪ��
				search.setEditable(true);//��������
				search.setFocusable(true);//��ʾ���
				search.grabFocus();//��λ���
			}
		}else if("searchbt".equals(e.getComponent().getName())){//--------�ر�
			System.out.println("Ŀǰû��");
		}else if("tem1".equals(e.getComponent().getName())){//--------�ر�
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(1),value++);//���ñ���

			
			JP.remove(img);//ɾ��ͼƬ
			JP.repaint();//ˢ��
			img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(1)).getImage());
			img.setBounds(262,83,737,454);		
			JP.add(img);
		
		} else if("tem2".equals(e.getComponent().getName())){//--------�ر�
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(2),value++);//���ñ���

			JP.remove(img);//ɾ��ͼƬ
			JP.repaint();//ˢ��
			img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(2)).getImage());
			img.setBounds(262,83,737,454);			
			JP.add(img);
		
		} else if("tem3".equals(e.getComponent().getName())){//--------�ر�
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(3),value++);//���ñ���

			JP.remove(img);//ɾ��ͼƬ
			JP.repaint();//ˢ��
			img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(3)).getImage());
			img.setBounds(262,83,737,454);		
			JP.add(img);
		
		}else if("hotlastpage".equals(e.getComponent().getName())){//��һҳ
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(21),value++);//���ñ���

		}else if("hotnextpage".equals(e.getComponent().getName())){//��һҳ
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(22),value++);//���ñ���

		}else if("hot1".equals(e.getComponent().getName())){//��Ϣ
			new ticket();
		}else if("hot2".equals(e.getComponent().getName())){
		
		}else if("hot3".equals(e.getComponent().getName())){
		
		}else if("hot4".equals(e.getComponent().getName())){
		
		}else if("hot5".equals(e.getComponent().getName())){
		
		}else if("hot6".equals(e.getComponent().getName())){
		
		}else if("soonlastpage".equals(e.getComponent().getName())){//��һҳ
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(31),value++);//���ñ���

		}else if("soonnextpage".equals(e.getComponent().getName())){//��һҳ
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(32),value++);//���ñ���

		}else if("soon1".equals(e.getComponent().getName())){
		
		}else if("soon2".equals(e.getComponent().getName())){
		
		}else if("soon3".equals(e.getComponent().getName())){
		
		}else if("soon4".equals(e.getComponent().getName())){
		
		}else if("soon5".equals(e.getComponent().getName())){
		
		}else if("soon6".equals(e.getComponent().getName())){
			
		}else if("usercenter1".equals(e.getComponent().getName())){
			
		}else if("usercenter2".equals(e.getComponent().getName())){
			
		}else if("usercenter3".equals(e.getComponent().getName())){
			
		}else if("usercenter4".equals(e.getComponent().getName())){
			
		}else if("usercenter5".equals(e.getComponent().getName())){
			
		}else if("usercenter6".equals(e.getComponent().getName())){
			
		}else if("name".equals(e.getComponent().getName())){
			name.setFont(new Font("serif",1,24));//���������ʽ
			name.setForeground(Color.black);//����ǰ��ɫΪ��
			name.setEditable(true);//��������
			name.setFocusable(true);//��ʾ���
			name.grabFocus();//��λ���
	            	
			//�����û���������ʱ��겻���û��������
			if("".equals(in.getText())){
            		InitUI.JTextAreaInit(in, "in");
            		in.setText("���飺");
    		}
		}else if("email".equals(e.getComponent().getName())){
			email.setFont(new Font("serif",1,24));//���������ʽ
			email.setForeground(Color.black);//����ǰ��ɫΪ��
			email.setEditable(true);//��������
			email.setFocusable(true);//��ʾ���
			email.grabFocus();//��λ���
	            	
			//�����û���������ʱ��겻���û��������
			if("".equals(in.getText())){
            		InitUI.JTextAreaInit(in, "in");
            		in.setText("���飺");
    		}
		}else if("telph".equals(e.getComponent().getName())){
			telph.setFont(new Font("serif",1,24));//���������ʽ
			telph.setForeground(Color.black);//����ǰ��ɫΪ��
			telph.setEditable(true);//��������
			telph.setFocusable(true);//��ʾ���
			telph.grabFocus();//��λ���
	            	
			//�����û���������ʱ��겻���û��������
			if("".equals(in.getText())){
            		InitUI.JTextAreaInit(in, "in");
            		in.setText("���飺");
    		}
		}else if("in".equals(e.getComponent().getName())){
		  if("���飺".equals(in.getText())){
			  in.setFont(new Font("serif",1,25));//���������ʽ
			  in.setText("");
			  in.setForeground(Color.black);//����ǰ��ɫΪ��
			  in.setEditable(true);//��������
			  in.setFocusable(true);//��ʾ���
			  in.grabFocus();//��λ���
		  }
		}else if("send".equals(e.getComponent().getName())){
			  if(!"".equals(name.getText()) && !"".equals(email.getText()) && 
				 !"".equals(telph.getText()) && !"".equals(in.getText()) &&
				 !"���飺".equals(in.getText())){
				  name.setText("");
				  email.setText("");
				  telph.setText("");
				  in.setText("���飺");
				  InitUI.JTextFieldInit(name,"name");
				  InitUI.JTextFieldInit(email,"email");
				  InitUI.JTextFieldInit(telph,"telph");
				  InitUI.JTextAreaInit(in,"in");
			  }
		}else if("close".equals(e.getComponent().getName())){
			System.exit(0);//�˳�����
		}  
	}  
  
    public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
    	if("homepage".equals( e.getComponent().getName())){
    		homepage.setFont((new Font("΢���ź�",0,25)));//��������
    		homepage.setForeground(Color.red);
		}else if("hotmovie".equals( e.getComponent().getName())){
			hotmovie.setFont((new Font("΢���ź�",0,25)));//��������
			hotmovie.setForeground(Color.orange);
		}else if("soonmovie".equals( e.getComponent().getName())){
			soonmovie.setFont((new Font("΢���ź�",0,25)));//��������
			soonmovie.setForeground(Color.yellow);
		}else if("personal".equals( e.getComponent().getName())){
			personal.setFont((new Font("΢���ź�",0,25)));//��������
			personal.setForeground(Color.green);
		}else if("aboutus".equals( e.getComponent().getName())){
			aboutus.setFont((new Font("΢���ź�",0,25)));//��������
			aboutus.setForeground(Color.pink);
		}else if("hotlastpage".equals( e.getComponent().getName())){
			hotlastpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����Ϊ��
		}else if("hotnextpage".equals( e.getComponent().getName())){
			hotnextpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����Ϊ��
		}else if("soonlastpage".equals( e.getComponent().getName())){
			soonlastpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����Ϊ��
		}else if("soonnextpage".equals( e.getComponent().getName())){
			soonnextpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����Ϊ��
		}
    	
    }  
    public void mouseExited(MouseEvent e) {// ����뿪���ʱִ�еĲ���  
		if("homepage".equals( e.getComponent().getName())){
			if(homepage.getFlag() == false){//û��ʹ�������ǩҳ
				homepage.setFont((new Font("΢���ź�",0,20)));//��������
				homepage.setForeground(Color.white);
			}
		}else if("hotmovie".equals( e.getComponent().getName())){
			if(hotmovie.getFlag() == false){//û��ʹ�������ǩҳ
				hotmovie.setFont((new Font("΢���ź�",0,20)));//��������
				hotmovie.setForeground(Color.white);
			}
		}else if("soonmovie".equals( e.getComponent().getName())){
			if(soonmovie.getFlag() == false){//û��ʹ�������ǩҳ
				soonmovie.setFont((new Font("΢���ź�",0,20)));//��������
				soonmovie.setForeground(Color.white);
			}		
		}else if("personal".equals( e.getComponent().getName())){
			if(personal.getFlag() == false){//û��ʹ�������ǩҳ
				personal.setFont((new Font("΢���ź�",0,20)));//��������
				personal.setForeground(Color.white);
			}		
		}else if("aboutus".equals( e.getComponent().getName())){
			if(aboutus.getFlag() == false){//û��ʹ�������ǩҳ
				aboutus.setFont((new Font("΢���ź�",0,20)));//��������
				aboutus.setForeground(Color.white);
			}
		}else if("search".equals(e.getComponent().getName())){//--------�ر�
			if("".equals(search.getText())){
				InitUI.JTextFieldInit(search, "search");
				search.setText(" ������ӰƬ��");
			}
		}
    }  
    public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
    	// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("userJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

    public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  
    }

    public static void main(String[] args){ 
    	new user();
    }
}
 