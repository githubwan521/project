package ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.corba.se.spi.orb.StringPair;
import com.sun.jndi.toolkit.ctx.StringHeadTail;
import com.sun.org.apache.xpath.internal.operations.And;

import Basic.Default;
import Basic.Studio;
import Basic.User;
import db.ManageDict;
import db.ManageStudio_DB;
import server.ManageStudio_Sev;
import server.ManageUser_Sev;

public class Administrator_UI implements MouseListener{
	User login_user;//��½�����û�
	//�����ֵ����
	private JLabel dict= new JLabel("�����ֵ�",JLabel.CENTER);

	//�û�����
	private JLabel user= new JLabel("�û�����",JLabel.CENTER);
	private myJPanel_UI user_Jp = new myJPanel_UI(Default.Get_Path_Img_Adm_User());//�O�ñ���   �����
	private String[][] user_Data = ManageUser_Sev.get_user_list();//�û�����
	private DefaultTableModel user_Model = new DefaultTableModel(user_Data,Default.Get_User_ColumnNames()){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	
	private JTable user_JT = new JTable(user_Model);//�û����
	private JScrollPane user_JS = new JScrollPane();//�O�ñ���   �����
	private Choice user_identity = new Choice(); 
	private JTextField user_name = new JTextField();
	private JTextField user_tel = new JTextField();
	private JButton user_add = new JButton();
	private JButton user_delete = new JButton();
	private JButton user_reset = new JButton();
	private JButton user_update = new JButton();
	
	//�ݳ�������
	private JLabel studio= new JLabel("�ݳ�������",JLabel.CENTER);
	private myJPanel_UI studio_Jp = new myJPanel_UI(Default.Get_Path_Img_Adm_Studio());//�O�ñ���   �����
	private String[][] studio_Data = ManageStudio_Sev.get_studio_list();//�û�����
	private DefaultTableModel studio_Model = new DefaultTableModel(studio_Data,Default.Get_Studio_ColumnNames()){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	
	private JTable studio_JT = new JTable(studio_Model);//�û����
	private JScrollPane studio_JS = new JScrollPane();//�O�ñ���   �����
	private JTextField studio_name = new JTextField();
	private Choice seat_row = new Choice(); //��ӵ�����
	private Choice seat_col = new Choice(); //��ӵ�����
	private JTextArea studio_info = new JTextArea();
	private JScrollPane studio_info_JS = new JScrollPane(studio_info);//��Ϣ����
	private JButton studio_add = new JButton();
	private JButton studio_delete = new JButton();
	private JButton studio_seat = new JButton();
	
	//������
	private boolean flag=true;
	private JFrame Adm_JF = new JFrame();
	private myJPanel_UI adm_Jp = new myJPanel_UI(Default.Get_Path_Img_Adm());//�O�ñ���   �����
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();//1����Ա2����3��ƱԱ
	private JButton search = new JButton();
	private JButton close = new JButton();

	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	
	
	public Administrator_UI(User administrator){
		login_user = administrator;
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
		
		//�����û����
		user_JS.getViewport().add(user_JT);
		user_Jp.add(user_JS);
		user_Jp.add(user_identity);
		user_Jp.add(user_name);
		user_Jp.add(user_tel);
		
		user_Jp.add(user_reset);
		user_Jp.add(user_add);
		user_Jp.add(user_delete);
		user_Jp.add(user_update);
		
		//�ݳ�������
		studio_JS.getViewport().add(studio_JT);
		studio_Jp.add(studio_info_JS);
		studio_Jp.add(studio_JS);
		studio_Jp.add(studio_name);
		studio_Jp.add(seat_row);
		studio_Jp.add(seat_col);
//		studio_info_JS.add(studio_info);
	
		studio_Jp.add(studio_add);
		studio_Jp.add(studio_delete);
		studio_Jp.add(studio_seat);
		
		//������
		adm_Jp.add(user);
		adm_Jp.add(studio);
		adm_Jp.add(dict);
		info.setText(login_user.getName());
//		info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		
		adm_Jp.add(info);		
		adm_Jp.add(datetime);
		adm_Jp.add(user_Jp);
		adm_Jp.add(search_Box);
		adm_Jp.add(search);
		adm_Jp.add(close);
		Adm_JF.add(adm_Jp);
		Adm_JF.setVisible(true);
	}
	//����ļ���
	public void listener(){
		//�����û����
		user.addMouseListener(this);
		user_Jp.addMouseListener(this);
		user_JT.addMouseListener(this);
		user_add.addMouseListener(this);
		user_delete.addMouseListener(this);
		user_update.addMouseListener(this);
		//�ݳ�������
		studio.addMouseListener(this);
		studio_JT.addMouseListener(this);
		studio_add.addMouseListener(this);
		studio_delete.addMouseListener(this);
		studio_seat.addMouseListener(this);
		user_reset.addMouseListener(this);


		//������
		dict.addMouseListener(this);
		info.addMouseListener(this);
		search.addMouseListener(this);
		search_Box.addMouseListener(this);
		close.addMouseListener(this);
		Adm_JF.addMouseListener(this);
		Adm_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = Adm_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				Adm_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//��������ĳ�ʼ��
	public void Init(){
		//�����û����
		Init_UI.JLableInit(user, "user");
		user.setForeground(Color.red);
		user.setFont(new Font("΢���ź�",0,32));

		Init_UI.JPanelInit(user_Jp, "user_Jp",0,169,Default.Get_Window_X(),Default.Get_Window_Y()-169);
		Init_UI.JScrollPaneInit( user_JS,"user_JS");
		Init_UI.JTableInit(user_JT,"user_JT");
		//�п�
		user_JT.getColumnModel().getColumn(0).setPreferredWidth(10);
		user_JT.getColumnModel().getColumn(1).setPreferredWidth(10);
		user_JT.getColumnModel().getColumn(2).setPreferredWidth(10);
		user_JT.getColumnModel().getColumn(3).setPreferredWidth(30);

		Init_UI.ChoiceInit(user_identity, "user_identity");
		String[] tem =  ManageDict.get_value_list(2);
		for(String i : tem){
			user_identity.add(i);
		}
////		�������
//		RowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<TableModel>(user_JT.getModel());
//		user_JT.setRowSorter(sorter);
		Init_UI.JTextFieldInit(user_name, "user_name");
		user_name.setDocument(new LengthDocument(10));//�����������볤��
		Init_UI.JTextFieldInit(user_tel, "user_tel");
		user_tel.setDocument(new LengthDocument(13));//�����������볤��
		
		Init_UI.JButtonInit(user_add, "user_add");
		Init_UI.JButtonInit(user_delete,"user_delete");
		Init_UI.JButtonInit(user_update, "user_update");
		Init_UI.JButtonInit(user_reset, "user_reset");

		
		
		//�����ݳ���
		Init_UI.JLableInit(studio, "studio");
		studio.setFont(new Font("΢���ź�",0,32));
		studio.setForeground(Color.black);

		Init_UI.JPanelInit(studio_Jp, "studio_Jp",0,169,Default.Get_Window_X(),Default.Get_Window_Y()-169);
		Init_UI.JScrollPaneInit( studio_JS,"studio_JS");
		Init_UI.JTableInit(studio_JT,"studio_JT");
		//�п�
		
		Init_UI.ChoiceInit(seat_row, "seat_row");
		Init_UI.ChoiceInit(seat_col, "seat_col");
		Init_UI.JTextFieldInit(studio_name, "studio_name");
		studio_name.setDocument(new LengthDocument(10));
		for(int i=6 ; i<=9 ; i++){
			seat_row.add(String.valueOf(i));
		}
		for(int i=9 ; i<=14 ; i++){
			seat_col.add(String.valueOf(i));
		}
////		�������
//		RowSorter<javax.swing.table.TableModel> sorter2 = new TableRowSorter<TableModel>(studio_JT.getModel());
//		user_JT.setRowSorter(sorter2);
		Init_UI.JTextAreaInit(studio_info, "studio_info");
		Init_UI.JButtonInit(studio_add, "studio_add");
		Init_UI.JButtonInit(studio_delete,"studio_delete");
		Init_UI.JButtonInit(studio_seat, "studio_seat");
		
		//������
		Init_UI.JLableInit(dict, "dict");
		dict.setFont(new Font("΢���ź�",0,32));
		dict.setForeground(Color.black);
		Init_UI.JFrameInit(Adm_JF,"Adm_JF",Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(adm_Jp, "adm_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JLableInit(info, "info");
		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JTextFieldInit(search_Box, "search_Box");
		Init_UI.JButtonInit(search, "search");
		Init_UI.JButtonInit(close, "close");
	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		//�����û����
		user.setBounds(32, 110, 200, 50);
		user_JS.setBounds(62,21,627,426);
		user_identity.setBounds(812, 21, 179, 26);
		user_name.setBounds(813, 90, 177, 28);
		user_tel.setBounds(813, 156, 177, 28);
		user_add.setBounds(754, 226, 199, 43);
		user_update.setBounds(754, 287, 199, 43);
		user_reset.setBounds(754, 347, 199, 43);
		user_delete.setBounds(754, 406, 199, 43);
		
		//�����ݳ���
		studio.setBounds(280, 110, 200, 50);
		studio_JS.setBounds(62,21,627,426);
		studio_name.setBounds(795, 21, 179, 30);
		seat_row.setBounds(795, 82, 55, 38);
		seat_col.setBounds(919, 82, 55, 40);
		studio_info_JS.setBounds(795, 145, 179, 89);
		studio_add.setBounds(753, 284, 199, 43);
		studio_delete.setBounds(753, 342, 199, 43);
		studio_seat.setBounds(753, 400, 199, 43);
		
		//������
		dict.setBounds(520, 110, 200, 50);
		info.setBounds(941, 22, 150, 20);
		datetime.setBounds(810, 55, 200, 20);
		search.setBounds(962, 114, 29, 27);
		search_Box.setBounds(790, 114, 171, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
	}
	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		 if("user_JT".equals( e.getComponent().getName())){
			int selectedRow = user_JT.getSelectedRow(); // ���ѡ��������
			user_identity.select(user_JT.getValueAt(selectedRow, 1).toString());		
			user_name.setText(user_JT.getValueAt(selectedRow, 2).toString());
			user_tel.setText(user_JT.getValueAt(selectedRow, 3).toString());
		
		 }else if("studio_JT".equals( e.getComponent().getName())){
			int selectedRow = studio_JT.getSelectedRow(); // ���ѡ��������
			studio_name.setText(studio_JT.getValueAt(selectedRow, 1).toString());
			seat_row.select(studio_JT.getValueAt(selectedRow, 2).toString());		
			seat_col.select(studio_JT.getValueAt(selectedRow, 3).toString());		
			studio_info.setText(studio_Data[selectedRow][4]);
		
		}else if("user_add".equals(e.getComponent().getName())){
			//����ƥ��
			if(Regex.RE_matching(user_name.getText(),1) && user_name.getText().length()>1){
				if(Regex.RE_matching(user_tel.getText(),3) || user_tel.getText().equals("")){
					if(ManageUser_Sev.add(new User(user_identity.getSelectedItem(),user_name.getText(),"123456",user_tel.getText()))){
						user_Model.addRow(ManageUser_Sev.get_user_list()[ManageUser_Sev.get_user_list().length-1]); // ���һ��
						JOptionPane.showMessageDialog(null, "��ӳɹ�"); 
					}else{
						JOptionPane.showMessageDialog(null, "���ʧ��,�Ѵ��ڸ��û�");  
					}
				}else{
					JOptionPane.showMessageDialog(null, "�ֻ��Ÿ�ʽ����");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "�û�����ʽ����,����(��ĸ,����,�»���)����ҿ�ͷ��������ĸ,���ܳ���16λ");  
			}
		}else if("studio_add".equals(e.getComponent().getName())){
			if(studio_name.getText().length()>0){
				boolean flag=true;
				for(int i=0 ; i<studio_name.getText().length() ; i++){
					if(Regex.RE_matching(String.valueOf(studio_name.getText().charAt(i)),4)){
						flag=false;
					}
				}
				if(flag){
					if(ManageStudio_Sev.add(new Studio(studio_name.getText(),Integer.valueOf(seat_row.getSelectedItem()).intValue(),Integer.valueOf(seat_col.getSelectedItem()).intValue(),studio_info.getText()))){
						studio_Model.addRow(ManageStudio_Sev.get_studio_list()[ManageStudio_Sev.get_studio_list().length-1]); // ���һ��
						studio_Data = ManageStudio_Sev.get_studio_list();
						JOptionPane.showMessageDialog(null, "��ӳɹ�");  
					}else{
						JOptionPane.showMessageDialog(null, "���ʧ��,�Ѵ��ڸ��ݳ���");
					}
				}else{
					JOptionPane.showMessageDialog(null, "�ݳ��������Ƿ��ַ�");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "�ݳ���������Ϊ��");  
			}
		}else if("user_delete".equals(e.getComponent().getName())){
			int selectedRow = user_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){	
				User user= new User();
				user.setId(Integer.valueOf(user_JT.getValueAt(selectedRow, 0).toString()));
				if(ManageUser_Sev.delete(user)){
					user_Model.removeRow(selectedRow); // ɾ����
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�");  
				}else{
					JOptionPane.showMessageDialog(null,"ɾ��ʧ��");  
				}
			}else{
				JOptionPane.showMessageDialog(null,"ɾ��ʧ��,��ѡ���û�");  
			}
		}else if("studio_delete".equals(e.getComponent().getName())){
			int selectedRow = studio_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){	
				Studio studio= new Studio();
				studio.setId(Integer.valueOf(studio_JT.getValueAt(selectedRow, 0).toString()).intValue());
				if(ManageStudio_Sev.delete(studio)){
					studio_Model.removeRow(selectedRow); // ɾ����
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�");  
				}else{
					JOptionPane.showMessageDialog(null,"ɾ��ʧ��");  
				}
			}else{
				JOptionPane.showMessageDialog(null,"ɾ��ʧ��,��ѡ���ݳ���");  
			}
		}else if("user_update".equals(e.getComponent().getName())){
			//����ƥ��
			int selectedRow = user_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){
				User user = new User();
				user.setIdentity(user_identity.getSelectedItem());
				user.setName(user_name.getText());
				user.setTel(user_tel.getText());
				user.setId(Integer.valueOf(user_JT.getValueAt(selectedRow, 0).toString()));
				ManageUser_Sev.update(user);
//				System.out.println(user_JT.getValueAt(selectedRow, 0).toString()+ user_JT.getValueAt(selectedRow, 2).toString()+ user_JT.getValueAt(selectedRow, 3).toString()+ user_identity.getText()+user_name.getText()+user_pass.getText());
				user_JT.setValueAt(user_identity.getSelectedItem(), selectedRow, 1);
				user_JT.setValueAt(user_name.getText(), selectedRow, 2);
				user_JT.setValueAt(user_tel.getText(), selectedRow, 3);
				JOptionPane.showMessageDialog(null,"���³ɹ�");  
			}
		}else if("user_reset".equals(e.getComponent().getName())){
			int selectedRow = user_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){
				ManageUser_Sev.reset(Integer.valueOf(user_JT.getValueAt(selectedRow, 0).toString()));
//				System.out.println(user_JT.getValueAt(selectedRow, 0).toString()+ user_JT.getValueAt(selectedRow, 2).toString()+ user_JT.getValueAt(selectedRow, 3).toString()+ user_identity.getText()+user_name.getText()+user_pass.getText());
				user_JT.setValueAt(user_identity.getSelectedItem(), selectedRow, 1);
				user_JT.setValueAt(user_name.getText(), selectedRow, 2);
				user_JT.setValueAt(user_tel.getText(), selectedRow, 3);
				JOptionPane.showMessageDialog(null,"���óɹ�");  

			}
		}else if("studio_seat".equals(e.getComponent().getName())){
			//������λ
			int selectedRow = studio_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){
				Studio studio= new Studio();
				studio.setId(Integer.valueOf(studio_JT.getValueAt(selectedRow, 0).toString()).intValue());
				studio.setName(studio_JT.getValueAt(selectedRow, 1).toString());
				studio.setRow(Integer.valueOf(studio_JT.getValueAt(selectedRow, 2).toString()).intValue());
				studio.setCol(Integer.valueOf(studio_JT.getValueAt(selectedRow, 3).toString()).intValue());
				new Seat_UI(Adm_JF,studio,login_user);
				String[] tem = ManageStudio_Sev.get_studio(studio.getId());
				studio_JT.setValueAt(tem[2], selectedRow, 2);
				studio_JT.setValueAt(tem[3], selectedRow, 3);
			}else{
				JOptionPane.showMessageDialog(null, "��ѡ����λ");  
			}

		}else if("user".equals(e.getComponent().getName())){
			flag = true;
			user.setForeground(Color.red);
			studio.setForeground(Color.black);
			dict.setForeground(Color.black);
			user_JT.setModel(user_Model);
			adm_Jp.remove(studio_Jp);
			adm_Jp.add(user_Jp);
			adm_Jp.updateUI();

		}else if("studio".equals(e.getComponent().getName())){
			flag=false;
			studio.setForeground(Color.red);
			user.setForeground(Color.black);
			dict.setForeground(Color.black);
			studio_JT.setModel(studio_Model);
			adm_Jp.remove(user_Jp);
			adm_Jp.add(studio_Jp);
			adm_Jp.updateUI();

		}else if("dict".equals(e.getComponent().getName())){
			flag=false;
			dict.setForeground(Color.red);
			user.setForeground(Color.black);
			studio.setForeground(Color.black);
			new Dict_UI(Adm_JF,login_user);

		}else if("info".equals(e.getComponent().getName())){
			user_JT.setModel(new DefaultTableModel(ManageUser_Sev.get_user_search(String.valueOf(login_user.getName())),Default.Get_User_ColumnNames()){
				public boolean isCellEditable(int row,int column){ 
					return false;
				}  
			});
		}else if("search".equals(e.getComponent().getName())){
			if(flag){
				user_JT.setModel(new DefaultTableModel(ManageUser_Sev.get_user_search(String.valueOf(search_Box.getText())),Default.Get_User_ColumnNames()){
					public boolean isCellEditable(int row,int column){ 
						return false;
					}  
				});
			}else{
				studio_JT.setModel(new DefaultTableModel(ManageStudio_Sev.get_studio_search(String.valueOf(search_Box.getText())),Default.Get_Studio_ColumnNames()){
					public boolean isCellEditable(int row,int column){ 
						return false;
					}  
				});
			}

		}else if("close".equals(e.getComponent().getName())){
			login_user.setStatus(0);
	    	ManageUser_Sev.update_status(login_user);	
	    	System.exit(0);//�˳�����
		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("Adm_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
	}

	public void mouseExited(MouseEvent e) {
		if("Adm_JF".equals(e.getComponent().getName())){//�����������ʱ��
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}else if("user_Jp".equals(e.getComponent().getName())){
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
	public static void main(String[] args){  
		User user = new User("����Ա","motian","765885195","15129******");
		new Administrator_UI(user);
	}
}	

