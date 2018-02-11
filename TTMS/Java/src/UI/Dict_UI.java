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
	JFrame fJFrame;//������
	String string;
	//������
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
    private myJPanel_UI dict_Jp = new myJPanel_UI(Default.Get_Path_Img_Adm_Dict());//�O�ñ���   �����
	private JButton  close= new JButton();
	private JButton  add= new JButton();
	private JButton  delete= new JButton();
	private JButton  update= new JButton();
	private  JTree tree;
	private JTextField father = new JTextField();
	private JTextField child = new JTextField();
	
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	private JDialog dict_JF;
		
	public Dict_UI(JFrame Adm_JF, User login_user){
		fJFrame = Adm_JF;
		dict_JF = new JDialog(Adm_JF,true);
		this.login_user = login_user;
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
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
	//����ļ���
	public void listener(){
		//������
		add.addMouseListener(this);
		delete.addMouseListener(this);
		update.addMouseListener(this);

		info.addMouseListener(this);
		close.addMouseListener(this);
		dict_JF.addMouseListener(this);
		dict_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = dict_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				dict_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//��������ĳ�ʼ��
	public void Init(){
		//������
		Init_UI.JDialogInit(dict_JF,"dict_JF",597,573);
		Init_UI.JPanelInit(dict_Jp, "dict_Jp",0,0,597,573);
		Init_UI.JLableInit(info, "info");
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(add, "add");
		Init_UI.JButtonInit(delete, "delete");
		Init_UI.JButtonInit(update, "update");

		Init_UI.JTextFieldInit(father, "father");
//		father.setEditable(false);//��������

		Init_UI.JTextFieldInit(child, "child");

	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		//������
		info.setBounds(508, 24, 150, 20);
		datetime.setBounds(410, 56, 200, 20);
		close.setBounds(572, 0, 25, 25);

		father.setBounds(378, 160, 170, 30);
		child.setBounds(378, 233, 170, 30);
		add.setBounds(322, 321, 183, 37);
		delete.setBounds(322, 390, 183, 36);
		update.setBounds(322, 458, 183, 36);

	}

	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		 if("add".equals(e.getComponent().getName())){
			 int f_id = ManageDict.get_id(father.getText());
			 if(f_id != 0){
				ManageDict.add(child.getText(), f_id);
				JOptionPane.showMessageDialog(null, "��ӳɹ�"); 
//				dict_JF.remove(tree);
//				tree();
//				dict_JF.add(tree);
				dict_JF.dispose();
				new Dict_UI(fJFrame, login_user);
			 }else{
				JOptionPane.showMessageDialog(null, "���ʧ��,�����ڸ��ڵ�");  
			 }
		 }else if("delete".equals(e.getComponent().getName())){
			 int f_id = ManageDict.get_id(father.getText());
			 if(f_id == 0){
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��,�����ڸ��ڵ�"); 
			 }else if(f_id<=13){
				 JOptionPane.showMessageDialog(null, "ɾ��ʧ��,�޷�ɾ��Ĭ�Ͻڵ�");  
			 }else{
				if(ManageDict.get_id(child.getText()) !=0){
					if(ManageDict.delete(child.getText())){
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						dict_JF.dispose();
						new Dict_UI(fJFrame, login_user);
					}else{
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��,�޷�ֱ��ɾ�����ڵ�");
					}
				}else{
					 JOptionPane.showMessageDialog(null, "ɾ��ʧ��,�����ڸýڵ�");  
				}
			 }
		 }else if("update".equals(e.getComponent().getName())){
			 int f_id = ManageDict.get_id(father.getText());
			 if(f_id == 0){
				 JOptionPane.showMessageDialog(null, "�޸�ʧ��,�����ڸ��ڵ�"); 
			 }else if(f_id<=13){
				 JOptionPane.showMessageDialog(null, "�޸�ʧ��,�޷��޸�Ĭ�Ͻڵ�");  
			 }else{
				ManageDict.update(string,child.getText());
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");  
				dict_JF.dispose();
				new Dict_UI(fJFrame, login_user);
			 }
		 }else if("close".equals(e.getComponent().getName())){
//			 fJFrame.setVisible(true);
			 dict_JF.dispose();
//			 System.exit(0);
		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("dict_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
	}

	public void mouseExited(MouseEvent e) {
	}
	
//	public static void main(String[] args){
//		User user = new User();
//		user.setId(3);
//		user.setIdentity("����Ա");
//		user.setName("motian");
//		new Dict_UI(null,user);
//	}
}


