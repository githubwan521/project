package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class myJPanel_UI extends JPanel {
	Image img;
	public myJPanel_UI(String path){ 
		img =Toolkit.getDefaultToolkit().getImage(path);//需要注意的是如果用相对路径载入图片,则图片文件必须放在类文件所在文件夹或项目的根文件夹中,否则必须用绝对路径。 
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int imWidth = img.getWidth(this);
		int imHeight = img.getHeight(this); // 定义图片的宽度、高度
		int FWidth = getWidth();
		int FHeight = getHeight();// 定义窗口的宽度、高度
		int x = (FWidth - imWidth) / 2;
		int y = (FHeight - imHeight) / 2;// 计算图片的坐标,使图片显示在窗口正中间
		g.drawImage(img, x, y, null);// 绘制图片
	} 
 }