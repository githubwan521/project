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
	private myJLabel homepage,aboutus;//首页    关于我们 
	private myJLabel hotmovie,soonmovie,personal;//正在热映     即将上映   个人中心
	private JLabel img;//首页的图片
	private JLabel tem1,tem2,tem3;//首页的按钮标签
	private JButton hot1,hot2,hot3,hot4,hot5,hot6;//正在热映的图片标签
	private JButton soon1,soon2,soon3,soon4,soon5,soon6;//正在热映的图片标签
	private JButton searchbt,close,send;//搜索,关闭,发送
	private JButton usercenter1,usercenter2,usercenter3,usercenter4,usercenter5,usercenter6;//搜索,关闭,发送
	private JButton hotlastpage,hotnextpage,soonlastpage,soonnextpage;//关闭
	private JTextField search,name,email,telph;//ID框
	private JTextArea in;
	private JFrame userJF;
	private JPanel JP;//面板  
	private int value, hotreplace;//图片叠加的层数  电影的替换次数
 	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
//    private Timer timer=new Timer(500,new TimerAction());
	
	public user(){
		value = 0;
		hotreplace=1;
		
		userJF = new JFrame();
		userJF.setSize(Constant.getUserUiSizeX(), Constant.getUserUiSizeY());//窗口大小
		InitUI.JFrameInit(userJF,"userJF");//窗口初始化
		InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(2),value++);//设置背景
		userJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//隐藏当前窗口，并释放窗体占有的其他资源
		
		userJF.addMouseListener(this);
		userJF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = userJF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				userJF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
		
		
		//默认初始化
		homepage = new myJLabel("首页",JLabel.CENTER);
		InitUI.JLableInit(homepage, "homepage");
		homepage.addMouseListener(this);//监听注册键
		hotmovie = new myJLabel("正在热映",JLabel.CENTER);
		InitUI.JLableInit(hotmovie, "hotmovie");
		hotmovie.addMouseListener(this);
		soonmovie = new myJLabel("即将上映",JLabel.CENTER);
		InitUI.JLableInit(soonmovie, "soonmovie");
		soonmovie.addMouseListener(this);
		personal = new myJLabel("个人中心",JLabel.CENTER);
		InitUI.JLableInit(personal, "personal");
		personal.addMouseListener(this);
		aboutus = new myJLabel("关于我们",JLabel.CENTER);
		InitUI.JLableInit(aboutus, "aboutus");
		aboutus.addMouseListener(this);
		fontInit();//字体初始化
		homepage.setFlag(true);//默认为首页
		homepage.setFont((new Font("微软雅黑",0,26)));//设置字体
		homepage.setForeground(Color.red);
	
		search = new JTextField();//ID框
		search.setDocument(new MyDocument(16));//限制用户名输入长度
		search.setText(" 请输入影片名");
		InitUI.JTextFieldInit(search,"search");
		search.setFont(new Font("serif",0, 14));//设置字体格式
		search.addMouseListener(this);//监听账号框
		name = new JTextField();
		name.setDocument(new MyDocument(10));//限制用户名输入长度
		InitUI.JTextFieldInit(name,"name");
		name.setFont(new Font("serif",0, 14));//设置字体格式
		name.addMouseListener(this);//监听账号框
		email = new JTextField();
		email.setDocument(new MyDocument(20));//限制用户名输入长度
		InitUI.JTextFieldInit(email,"email");
		email.setFont(new Font("serif",0, 14));//设置字体格式
		email.addMouseListener(this);
		telph = new JTextField();
		telph.setDocument(new MyDocument(13));//限制用户名输入长度
		InitUI.JTextFieldInit(telph,"telph");
		telph.setFont(new Font("serif",0, 14));//设置字体格式
		telph.addMouseListener(this);
		in = new JTextArea("建议：");
		InitUI.JTextAreaInit(in,"in");
		in.addMouseListener(this);
		
		
		//新建时的初始化
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
		hotlastpage.setBorder(null);//去掉边框
		hotlastpage.addMouseListener(this);
		hotnextpage = new JButton();
		InitUI.JButtonInit(hotnextpage, "hotnextpage");
		hotnextpage.setBorder(null);//去掉边框
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
		soonlastpage.setBorder(null);//去掉边框
		soonlastpage.addMouseListener(this);
		soonnextpage = new JButton();
		InitUI.JButtonInit(soonnextpage, "soonnextpage");
		soonnextpage.setBorder(null);//去掉边框
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
	
		
		
		//组件在面板中的绝对布局
		//1--面板设置
        JP = new JPanel(null);//不使用布局方式
        InitUI.JPanelInit(JP, "JP");
		JP.setBounds(0,0,userJF.getWidth(),userJF.getHeight());//面板大小为窗口大小
		
//		2--组件布局及添加
		search.setBounds(58,181, 116, 23);
		JP.add(search);
		searchbt.setBounds(173, 181, 25, 23);
		JP.add(searchbt);
		homepage.setBounds(46,235, 170, 30);//首5
		JP.add(homepage);
		hotmovie.setBounds(43, 305, 170, 30);//正在热映
		JP.add(hotmovie);
		soonmovie.setBounds(43,375,170,30);//即将上映
		JP.add(soonmovie);
		personal.setBounds(43,445,170,30);//个人中心
		JP.add(personal);
		aboutus.setBounds(43,515,170,30);//关于我们
		JP.add(aboutus);
		tem1.setBounds(549,567,19,19);//首页换页按钮
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
		JP.add(in);//超过框
		send.setBounds(651,512,158,43);
		JP.add(send);
		close.setBounds(1029, 1, 20, 21);//关闭起始位置与大小
		JP.add(close);
		
		hidebt();
		img.setVisible(true);
		tem1.setVisible(true);
		tem2.setVisible(true);
		tem3.setVisible(true);
		
		userJF.add(JP);
		userJF.setVisible(true);//窗口可见
	}

	private void fontInit(){//字体初始化
		homepage.setFont((new Font("微软雅黑",0,20)));//设置字体
		homepage.setForeground(Color.white);
		homepage.setFlag(false);
		hotmovie.setFont((new Font("微软雅黑",0,20)));//设置字体
		hotmovie.setForeground(Color.white);
		hotmovie.setFlag(false);
		soonmovie.setFont((new Font("微软雅黑",0,20)));//设置字体
		soonmovie.setForeground(Color.white);
		soonmovie.setFlag(false);
		personal.setFont((new Font("微软雅黑",0,20)));//设置字体
		personal.setForeground(Color.white);
		personal.setFlag(false);
		aboutus.setFont((new Font("微软雅黑",0,20)));//设置字体
		aboutus.setForeground(Color.white);
		aboutus.setFlag(false);
	}
	
	//隐藏部件
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
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("homepage".equals( e.getComponent().getName())){
			fontInit();
			userJF.remove(homepage);
			homepage.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(2),value++);//设置背景
			homepage.setFont((new Font("微软雅黑",0,25)));//设置字体
    		homepage.setForeground(Color.red);
    		
    		//先隐藏
    		hidebt();
			img.setVisible(true);
			tem1.setVisible(true);
			tem2.setVisible(true);
			tem3.setVisible(true);
			JP.repaint();//刷新
    		
		}else if("hotmovie".equals(e.getComponent().getName())){
			fontInit();
			hotmovie.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(21),value++);//设置背景
			hotmovie.setFont((new Font("微软雅黑",0,25)));//设置字体
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
			JP.repaint();//刷新
			
		}else if("soonmovie".equals( e.getComponent().getName())){
			fontInit();
			soonmovie.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(31),value++);//设置背景
			soonmovie.setFont((new Font("微软雅黑",0,25)));//设置字体
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
			JP.repaint();//刷新
			
		}else if("personal".equals( e.getComponent().getName())){
			fontInit();
			personal.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(4),value++);//设置背景
			personal.setFont((new Font("微软雅黑",0,25)));//设置字体
			personal.setForeground(Color.green);
			
			hidebt();
			usercenter1.setVisible(true);
			usercenter2.setVisible(true);
			usercenter3.setVisible(true);
			usercenter4.setVisible(true);
			usercenter5.setVisible(true);
			usercenter6.setVisible(true);
			JP.repaint();//刷新
		}else if("aboutus".equals( e.getComponent().getName())){
			fontInit();
			aboutus.setFlag(true);
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(5),value++);//设置背景
			aboutus.setFont((new Font("微软雅黑",0,25)));//设置字体
			aboutus.setForeground(Color.pink);
    		
			hidebt();
			name.setVisible(true);
			email.setVisible(true);
			telph.setVisible(true);
			in.setVisible(true);
			send.setVisible(true);
			JP.repaint();//刷新
		}else if("search".equals(e.getComponent().getName())){//--------关闭
			if(" 请输入影片名".equals(search.getText())){
				search.setText("");
				search.setForeground(Color.black);//设置前景色为黑
				search.setEditable(true);//接受输入
				search.setFocusable(true);//显示光标
				search.grabFocus();//定位光标
			}
		}else if("searchbt".equals(e.getComponent().getName())){//--------关闭
			System.out.println("目前没有");
		}else if("tem1".equals(e.getComponent().getName())){//--------关闭
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(1),value++);//设置背景

			
			JP.remove(img);//删除图片
			JP.repaint();//刷新
			img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(1)).getImage());
			img.setBounds(262,83,737,454);		
			JP.add(img);
		
		} else if("tem2".equals(e.getComponent().getName())){//--------关闭
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(2),value++);//设置背景

			JP.remove(img);//删除图片
			JP.repaint();//刷新
			img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(2)).getImage());
			img.setBounds(262,83,737,454);			
			JP.add(img);
		
		} else if("tem3".equals(e.getComponent().getName())){//--------关闭
			InitUI.JFrameBackdropInit(userJF,Constant.getUserPerfaceUiImg(3),value++);//设置背景

			JP.remove(img);//删除图片
			JP.repaint();//刷新
			img = new InitUI.myJLabel(new ImageIcon(Constant.getUserPerfaceMovieImg(3)).getImage());
			img.setBounds(262,83,737,454);		
			JP.add(img);
		
		}else if("hotlastpage".equals(e.getComponent().getName())){//上一页
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(21),value++);//设置背景

		}else if("hotnextpage".equals(e.getComponent().getName())){//下一页
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(22),value++);//设置背景

		}else if("hot1".equals(e.getComponent().getName())){//信息
			new ticket();
		}else if("hot2".equals(e.getComponent().getName())){
		
		}else if("hot3".equals(e.getComponent().getName())){
		
		}else if("hot4".equals(e.getComponent().getName())){
		
		}else if("hot5".equals(e.getComponent().getName())){
		
		}else if("hot6".equals(e.getComponent().getName())){
		
		}else if("soonlastpage".equals(e.getComponent().getName())){//上一页
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(31),value++);//设置背景

		}else if("soonnextpage".equals(e.getComponent().getName())){//下一页
			InitUI.JFrameBackdropInit(userJF,Constant.getUserUiImg(32),value++);//设置背景

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
			name.setFont(new Font("serif",1,24));//设置字体格式
			name.setForeground(Color.black);//设置前景色为黑
			name.setEditable(true);//接受输入
			name.setFocusable(true);//显示光标
			name.grabFocus();//定位光标
	            	
			//处理用户框无内容时鼠标不在用户框的问题
			if("".equals(in.getText())){
            		InitUI.JTextAreaInit(in, "in");
            		in.setText("建议：");
    		}
		}else if("email".equals(e.getComponent().getName())){
			email.setFont(new Font("serif",1,24));//设置字体格式
			email.setForeground(Color.black);//设置前景色为黑
			email.setEditable(true);//接受输入
			email.setFocusable(true);//显示光标
			email.grabFocus();//定位光标
	            	
			//处理用户框无内容时鼠标不在用户框的问题
			if("".equals(in.getText())){
            		InitUI.JTextAreaInit(in, "in");
            		in.setText("建议：");
    		}
		}else if("telph".equals(e.getComponent().getName())){
			telph.setFont(new Font("serif",1,24));//设置字体格式
			telph.setForeground(Color.black);//设置前景色为黑
			telph.setEditable(true);//接受输入
			telph.setFocusable(true);//显示光标
			telph.grabFocus();//定位光标
	            	
			//处理用户框无内容时鼠标不在用户框的问题
			if("".equals(in.getText())){
            		InitUI.JTextAreaInit(in, "in");
            		in.setText("建议：");
    		}
		}else if("in".equals(e.getComponent().getName())){
		  if("建议：".equals(in.getText())){
			  in.setFont(new Font("serif",1,25));//设置字体格式
			  in.setText("");
			  in.setForeground(Color.black);//设置前景色为黑
			  in.setEditable(true);//接受输入
			  in.setFocusable(true);//显示光标
			  in.grabFocus();//定位光标
		  }
		}else if("send".equals(e.getComponent().getName())){
			  if(!"".equals(name.getText()) && !"".equals(email.getText()) && 
				 !"".equals(telph.getText()) && !"".equals(in.getText()) &&
				 !"建议：".equals(in.getText())){
				  name.setText("");
				  email.setText("");
				  telph.setText("");
				  in.setText("建议：");
				  InitUI.JTextFieldInit(name,"name");
				  InitUI.JTextFieldInit(email,"email");
				  InitUI.JTextFieldInit(telph,"telph");
				  InitUI.JTextAreaInit(in,"in");
			  }
		}else if("close".equals(e.getComponent().getName())){
			System.exit(0);//退出程序
		}  
	}  
  
    public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作 
    	if("homepage".equals( e.getComponent().getName())){
    		homepage.setFont((new Font("微软雅黑",0,25)));//设置字体
    		homepage.setForeground(Color.red);
		}else if("hotmovie".equals( e.getComponent().getName())){
			hotmovie.setFont((new Font("微软雅黑",0,25)));//设置字体
			hotmovie.setForeground(Color.orange);
		}else if("soonmovie".equals( e.getComponent().getName())){
			soonmovie.setFont((new Font("微软雅黑",0,25)));//设置字体
			soonmovie.setForeground(Color.yellow);
		}else if("personal".equals( e.getComponent().getName())){
			personal.setFont((new Font("微软雅黑",0,25)));//设置字体
			personal.setForeground(Color.green);
		}else if("aboutus".equals( e.getComponent().getName())){
			aboutus.setFont((new Font("微软雅黑",0,25)));//设置字体
			aboutus.setForeground(Color.pink);
		}else if("hotlastpage".equals( e.getComponent().getName())){
			hotlastpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		}else if("hotnextpage".equals( e.getComponent().getName())){
			hotnextpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		}else if("soonlastpage".equals( e.getComponent().getName())){
			soonlastpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		}else if("soonnextpage".equals( e.getComponent().getName())){
			soonnextpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		}
    	
    }  
    public void mouseExited(MouseEvent e) {// 鼠标离开组件时执行的操作  
		if("homepage".equals( e.getComponent().getName())){
			if(homepage.getFlag() == false){//没有使用这个标签页
				homepage.setFont((new Font("微软雅黑",0,20)));//设置字体
				homepage.setForeground(Color.white);
			}
		}else if("hotmovie".equals( e.getComponent().getName())){
			if(hotmovie.getFlag() == false){//没有使用这个标签页
				hotmovie.setFont((new Font("微软雅黑",0,20)));//设置字体
				hotmovie.setForeground(Color.white);
			}
		}else if("soonmovie".equals( e.getComponent().getName())){
			if(soonmovie.getFlag() == false){//没有使用这个标签页
				soonmovie.setFont((new Font("微软雅黑",0,20)));//设置字体
				soonmovie.setForeground(Color.white);
			}		
		}else if("personal".equals( e.getComponent().getName())){
			if(personal.getFlag() == false){//没有使用这个标签页
				personal.setFont((new Font("微软雅黑",0,20)));//设置字体
				personal.setForeground(Color.white);
			}		
		}else if("aboutus".equals( e.getComponent().getName())){
			if(aboutus.getFlag() == false){//没有使用这个标签页
				aboutus.setFont((new Font("微软雅黑",0,20)));//设置字体
				aboutus.setForeground(Color.white);
			}
		}else if("search".equals(e.getComponent().getName())){//--------关闭
			if("".equals(search.getText())){
				InitUI.JTextFieldInit(search, "search");
				search.setText(" 请输入影片名");
			}
		}
    }  
    public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
    	// 当鼠标按下的时候获得窗口当前的位置
		if("userJF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

    public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  
    }

    public static void main(String[] args){ 
    	new user();
    }
}
 