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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.RowSorter;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import Basic.Default;
import Basic.Schedule;
import Basic.User;
import db.ManageDict;
import db.ManageTicket_DB;
import server.ManagePlay_Sev;
import server.ManageSchedule_Sev;
import sun.security.krb5.internal.Ticket;

public class Dict_UI  implements MouseListener {
	User login_user;
	JFrame fJFrame;//父窗口
	String string;
	//主窗口
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
    private myJPanel_UI dict_Jp = new myJPanel_UI(Default.Get_Path_Img_Adm_Dict());//設置背景   主面板
	private JButton  close= new JButton();
	private JButton  add= new JButton();
	private JButton  delete= new JButton();
	private JButton  update= new JButton();
	private  JTree tree;
	private JTextField father = new JTextField();
	private JTextField child = new JTextField();
	
	private static Point origin = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private JDialog dict_JF;
		
	public Dict_UI(JFrame Adm_JF, User login_user){
		fJFrame = Adm_JF;
		dict_JF = new JDialog(Adm_JF,true);
		this.login_user = login_user;
		this.Init();//初始化
		this.setSize();//默认设置起始位置与大小
		this.listener();//监听
		tree();
//		fJFrame.setVisible(false);
		

		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		dict_Jp.add(add);
		dict_Jp.add(delete);
		dict_Jp.add(update);

		dict_Jp.add(info);
		dict_Jp.add(father);
		dict_Jp.add(child);

		dict_Jp.add(datetime);
		dict_Jp.add(close);

		dict_JF.add(dict_Jp);
		dict_JF.setVisible(true);
	}
	public void tree(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		int[] root_data=ManageDict.get_index_list(1);
		for(int i=1 ; i< root_data.length ; i++){
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(ManageDict.get_value(root_data[i]));
			int[] child_data=ManageDict.get_index_list(root_data[i]);
			for(int j=0 ; j< child_data.length ; j++){
				node.add(new DefaultMutableTreeNode(ManageDict.get_value(child_data[j])));
			}
			root.add(node);
		}
        JTree tree = new JTree(root);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                if (node == null){
                    return;
                }else{
                	child.setText(node.getUserObject().toString());
                	father.setText(ManageDict.get_value(ManageDict.get_fid(node.getUserObject().toString())));
                	string = new String(node.getUserObject().toString());
                }
            }
        });
        
        tree.setBounds(48, 117, 176, 380);
		dict_Jp.add(tree);
	}
	//组件的监听
	public void listener(){
		//主窗口
		add.addMouseListener(this);
		delete.addMouseListener(this);
		update.addMouseListener(this);

		info.addMouseListener(this);
		close.addMouseListener(this);
		dict_JF.addMouseListener(this);
		dict_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point tem = dict_JF.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				dict_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//所有组件的初始化
	public void Init(){
		//主窗口
		Init_UI.JDialogInit(dict_JF,"dict_JF",597,573);
		Init_UI.JPanelInit(dict_Jp, "dict_Jp",0,0,597,573);
		Init_UI.JLableInit(info, "info");
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//日期进入不为手
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(add, "add");
		Init_UI.JButtonInit(delete, "delete");
		Init_UI.JButtonInit(update, "update");

		Init_UI.JTextFieldInit(father, "father");
//		father.setEditable(false);//屏蔽输入

		Init_UI.JTextFieldInit(child, "child");

	}
	//设置所有组件的大小与起始位置
	public void setSize(){
		//主窗口
		info.setBounds(508, 24, 150, 20);
		datetime.setBounds(410, 56, 200, 20);
		close.setBounds(572, 0, 25, 25);

		father.setBounds(378, 160, 170, 30);
		child.setBounds(378, 233, 170, 30);
		add.setBounds(322, 321, 183, 37);
		delete.setBounds(322, 390, 183, 36);
		update.setBounds(322, 458, 183, 36);

	}

	
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		 if("add".equals(e.getComponent().getName())){
			 int f_id = ManageDict.get_id(father.getText());
			 if(f_id != 0){
				ManageDict.add(child.getText(), f_id);
				JOptionPane.showMessageDialog(null, "添加成功"); 
//				dict_JF.remove(tree);
//				tree();
//				dict_JF.add(tree);
				dict_JF.dispose();
				new Dict_UI(fJFrame, login_user);
			 }else{
				JOptionPane.showMessageDialog(null, "添加失败,不存在父节点");  
			 }
		 }else if("delete".equals(e.getComponent().getName())){
			 int f_id = ManageDict.get_id(father.getText());
			 if(f_id == 0){
					JOptionPane.showMessageDialog(null, "删除失败,不存在父节点"); 
			 }else if(f_id<=13){
				 JOptionPane.showMessageDialog(null, "删除失败,无法删除默认节点");  
			 }else{
				if(ManageDict.get_id(child.getText()) !=0){
					if(ManageDict.delete(child.getText())){
						JOptionPane.showMessageDialog(null, "删除成功");
						dict_JF.dispose();
						new Dict_UI(fJFrame, login_user);
					}else{
						JOptionPane.showMessageDialog(null, "删除失败,无法直接删除父节点");
					}
				}else{
					 JOptionPane.showMessageDialog(null, "删除失败,不存在该节点");  
				}
			 }
		 }else if("update".equals(e.getComponent().getName())){
			 int f_id = ManageDict.get_id(father.getText());
			 if(f_id == 0){
				 JOptionPane.showMessageDialog(null, "修改失败,不存在父节点"); 
			 }else if(f_id<=13){
				 JOptionPane.showMessageDialog(null, "修改失败,无法修改默认节点");  
			 }else{
				ManageDict.update(string,child.getText());
				JOptionPane.showMessageDialog(null, "修改成功");  
				dict_JF.dispose();
				new Dict_UI(fJFrame, login_user);
			 }
		 }else if("close".equals(e.getComponent().getName())){
//			 fJFrame.setVisible(true);
			 dict_JF.dispose();
//			 System.exit(0);
		}
	}

	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
		// 当鼠标按下的时候获得窗口当前的位置
		if("dict_JF".equals(e.getComponent().getName())){
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
//		user.setId(3);
//		user.setIdentity("管理员");
//		user.setName("motian");
//		new Dict_UI(null,user);
//	}
}


