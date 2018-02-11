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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Basic.Default;
import Basic.Play;
import Basic.Schedule;
import Basic.Studio;
import Basic.User;
import db.ManageDict;
import db.ManageTicket_DB;
import server.ManagePlay_Sev;
import server.ManageSchedule_Sev;
import server.ManageUser_Sev;
import sun.security.krb5.internal.Ticket;


public class Play_UI  implements MouseListener {
	User login_user;//��½�����û�
	JFrame fJFrame;//������
	//������
	private JLabel play= new JLabel("��     Ŀ     ��     ��",JLabel.CENTER);
	private String[][] play_Data = ManagePlay_Sev.get_play_list();
	private DefaultTableModel play_Model = new DefaultTableModel(play_Data,Default.Get_Play_ColumnNames()){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable play_JT = new JTable(play_Model);//�û����
	private JScrollPane play_JS = new JScrollPane();//�O�ñ���   �����
	private JTextField play_name = new JTextField();
	private Choice play_type = new Choice(); 
	private Choice play_lanage = new Choice();
	private Choice play_duration = new Choice();
	//���о�ĿͼƬ
	private JTextArea play_info = new JTextArea();
	private JScrollPane play_info_JS = new JScrollPane(play_info);//��Ϣ����
	private JButton play_add = new JButton("");
	private JButton play_delete = new JButton();
	private JButton play_update = new JButton();

	private JDialog Play_JF;
	private myJPanel_UI play_Jp = new myJPanel_UI(Default.Get_Path_Img_Man_Play());//�O�ñ���   �����
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();//1����Ա2����3��ƱԱ
	private JButton search = new JButton();
	private JButton close = new JButton();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	public Play_UI(JFrame Con_JF, User Manage){
		fJFrame = Con_JF;
		login_user = Manage;
		Play_JF = new JDialog(Con_JF, true);
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
//		fJFrame.setVisible(false);
		
		//������
		info.setText(" "+login_user.getName());
		System.out.println(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		play_JS.getViewport().add(play_JT);
		play_Jp.add(play_info_JS);
		play_Jp.add(play_JS);
		play_Jp.add(play);

		play_Jp.add(play_name);
		play_Jp.add(play_type);
		play_Jp.add(play_lanage);
		play_Jp.add(play_duration);
		
		play_Jp.add(play_add);
//		play_Jp.add(play_delete);
		play_Jp.add(play_update);
		
		play_Jp.add(info);		
		play_Jp.add(datetime);
		play_Jp.add(search_Box);
		play_Jp.add(search);
		play_Jp.add(close);
		Play_JF.add(play_Jp);
		Play_JF.setVisible(true);
		
	}
	
	//����ļ���
	public void listener(){
		play.addMouseListener(this);
		play_JT.addMouseListener(this);
		play_add.addMouseListener(this);
		play_delete.addMouseListener(this);
		play_update.addMouseListener(this);
		play_Jp.addMouseListener(this);
		//������
		info.addMouseListener(this);
		search.addMouseListener(this);
		search_Box.addMouseListener(this);
		close.addMouseListener(this);
		Play_JF.addMouseListener(this);
		Play_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = Play_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				Play_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//��������ĳ�ʼ��
	public void Init(){
		Init_UI.JLableInit(play, "play");
		play.setForeground(Color.red);
		play.setFont(new Font("serif",1,38));

		Init_UI.JScrollPaneInit( play_JS,"play_JS");
		Init_UI.JTableInit(play_JT,"play_JT");
//		�������
//		RowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<TableModel>(play_JT.getModel());
//		play_JT.setRowSorter(sorter);
		Init_UI.ChoiceInit(play_type, "play_type");
		Init_UI.ChoiceInit(play_lanage, "play_lanage");
		Init_UI.ChoiceInit(play_duration, "play_duration");
		String[] tem =ManageDict.get_value_list(4);
		for(int i=0 ; i<tem.length ; i++ ){
			play_type.add(tem[i]);
		}
		tem =ManageDict.get_value_list(3);
		for(int i=0 ; i<tem.length ; i++ ){
			play_lanage.add(tem[i]);
		}
		for(int i=60 ; i<=200 ; i++ ){
			play_duration.add(String.valueOf(i));
		}
		Init_UI.JTextFieldInit(play_name,"play_name");
		play_name.setDocument(new LengthDocument(16));

		//��ʼ��������
		Init_UI.JTextAreaInit(play_info, "play_info");
		Init_UI.JButtonInit(play_add, "play_add");
		Init_UI.JButtonInit(play_delete,"play_delete");
		Init_UI.JButtonInit(play_update, "play_update");

		Init_UI.JDialogInit(Play_JF,"Play_JF",Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(play_Jp, "play_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//���ڽ��벻Ϊ��

		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JTextFieldInit(search_Box, "search_Box");
		Init_UI.JButtonInit(search, "search");
		Init_UI.JButtonInit(close, "close");
	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		play.setBounds(270, 105, 400, 50);
		play_JS.setBounds(441,181,548,404);
		play_name.setBounds(274, 181, 148, 30);
		play_type.setBounds(274, 275, 148, 30);
		play_lanage.setBounds(274, 228, 148, 30);
		play_duration.setBounds(274, 323, 148, 30);
		play_info_JS.setBounds(62, 391, 359, 134);
		play_add.setBounds(61, 548, 158, 40);
		play_delete.setBounds(163, 608, 158, 40);
		play_update.setBounds(264, 548, 158, 40);
		
		info.setBounds(941, 26, 150, 20);
		datetime.setBounds(840, 55, 200, 20);
		search.setBounds(961, 114, 29, 27);
		search_Box.setBounds(790, 114, 171, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
	}
	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("play_JT".equals(e.getComponent().getName())){
			int selectedRow = play_JT.getSelectedRow(); // ���ѡ��������
			play_type.select(play_JT.getValueAt(selectedRow, 1).toString());		
			play_lanage.select(play_JT.getValueAt(selectedRow, 2).toString());
			play_name.setText(play_JT.getValueAt(selectedRow, 3).toString());
			play_duration.select(play_JT.getValueAt(selectedRow, 4).toString());
			play_info.setText(play_Data[selectedRow][5]);

		}else if("play_add".equals(e.getComponent().getName())){
			if(play_name.getText().length()>0){
				boolean flag=true;
				for(int i=0 ; i<play_name.getText().length() ; i++){
					if(Regex.RE_matching(String.valueOf(play_name.getText().charAt(i)),4)){
						flag=false;
					}
				}
				if(flag){
					if(ManagePlay_Sev.add(new Play(play_type.getSelectedIndex()+1,play_lanage.getSelectedIndex()+1,play_name.getText(),play_info.getText(),Integer.valueOf(play_duration.getSelectedItem()).intValue()))){
						play_Model.addRow(ManagePlay_Sev.get_play_list()[ManagePlay_Sev.get_play_list().length-1]); // ���һ��
						play_Data = ManagePlay_Sev.get_play_list();
						JOptionPane.showMessageDialog(null,"��ӳɹ�");  
					}else{
						JOptionPane.showMessageDialog(null,"���ʧ��,����ͬ��ͬ����ľ�Ŀ������");  
					}
				}else{
					JOptionPane.showMessageDialog(null,"��Ŀ�����Ƿ��ַ�");  
				}
			}else{
				JOptionPane.showMessageDialog(null, "��Ŀ������Ϊ��");  
			}
		}else if("play_delete".equals(e.getComponent().getName())){
			int selectedRow = play_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){	
				int play_id = Integer.valueOf(play_JT.getValueAt(selectedRow, 0).toString());
				if(ManagePlay_Sev.delete(play_id)){
					play_Model.removeRow(selectedRow); // ɾ����
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�");  
				}else{
					JOptionPane.showMessageDialog(null,"ɾ��ʧ��,�þ�Ŀ���ڱ�����");  
				}
			}
		}else if("play_update".equals(e.getComponent().getName())){
			int selectedRow = play_JT.getSelectedRow();// ���ѡ���е�����
			if (selectedRow != -1){
				Play play = new Play(play_type.getSelectedIndex()+1,play_lanage.getSelectedIndex()+1,play_name.getText(),play_info.getText(),Integer.valueOf(play_duration.getSelectedItem()).intValue());
				play.setPlay_id(Integer.valueOf(play_JT.getValueAt(selectedRow, 0).toString()));
				ManagePlay_Sev.update(play);
				play_JT.setValueAt(play_type.getSelectedItem(), selectedRow, 1);
				play_JT.setValueAt(play_lanage.getSelectedItem(), selectedRow, 2);
				play_JT.setValueAt(play_name.getText(), selectedRow, 3);
				play_JT.setValueAt(play_duration.getSelectedItem(), selectedRow, 4);
				JOptionPane.showMessageDialog(null,"���³ɹ�");  
			}else{
				JOptionPane.showMessageDialog(null,"����ʧ��");  
			}
		}else if("play".equals(e.getComponent().getName())){
			play_JT.setModel(play_Model);
		}else if("search".equals(e.getComponent().getName())){
			play_JT.setModel(new DefaultTableModel(ManagePlay_Sev.get_search_play_list(String.valueOf(search_Box.getText())),Default.Get_Play_ColumnNames()){
				public boolean isCellEditable(int row,int column){ 
					return false;
				}  
			});
		}else if("info".equals(e.getComponent().getName())){
			//�޸��û�����
		}else if("close".equals(e.getComponent().getName())){
//			System.exit(0);//�˳�����
			Play_JF.dispose();

		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("Play_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
		
	}

	public void mouseExited(MouseEvent e) {
		if("play_Jp".equals(e.getComponent().getName())){
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
	public static void main(String[] args){  
		User user = new User("����","motian","765885195","15129******");
		user.setId(9);
		new Play_UI(null,user);
	}

}


