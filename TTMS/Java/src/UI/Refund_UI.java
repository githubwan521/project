package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Basic.Default;
import Basic.Sale;
import Basic.User;
import db.ManageTicket_DB;
import server.ManageSale_Sev;
import sun.security.util.DisabledAlgorithmConstraints;

public class Refund_UI implements MouseListener {
	int sale_id;
	JDialog refund_JD;
	User  login_user;
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();
	private JButton search = new JButton();
	private JButton submit = new JButton();
	
	private myJPanel_UI refund_Jp = new myJPanel_UI(Default.Get_Path_Img_Cond_RefundTicket2());//�O�ñ���   �����
	private String[][] refund_Data;
	private DefaultTableModel refund_Model;
	private JTable refund_JT = new JTable(refund_Model);//�û����
	private JScrollPane refund_JS = new JScrollPane();//�O�ñ���   �����
	
	private String[][] ticket_Data;//�û�����
	private DefaultTableModel ticket_Model = new DefaultTableModel(ticket_Data,new String[]{"TicketId", "TicketPrice"}){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable ticket_JT = new JTable(ticket_Model);//�û����
	private JScrollPane detailed_JS = new JScrollPane();//�O�ñ���   �����
	
	private JButton close = new JButton();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��

	//�����ڣ���¼���û�,������id
	public Refund_UI(JDialog rt_JF,User Conductor ,int sale_id) {
		this.sale_id = sale_id;
		refund_JD = new JDialog(rt_JF, true);
		login_user = Conductor;
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.listener();//����
		
		//������
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		refund_Jp.add(info);		
		refund_Jp.add(datetime);
//		refund_Jp.add(search_Box);
//		refund_Jp.add(search);
		refund_Jp.add(close);
		refund_Jp.add(submit);
		refund_JS.getViewport().add(refund_JT);
		detailed_JS.getViewport().add(ticket_JT);
		refund_Jp.add(detailed_JS);
		refund_Jp.add(refund_JS);
		refund_JD.add(refund_Jp);
		refund_JD.setVisible(true);
	}
	public void listener(){
		//������
		info.addMouseListener(this);
		search.addMouseListener(this);
		close.addMouseListener(this);
		submit.addMouseListener(this);
		refund_JT.addMouseListener(this);
		refund_JD.addMouseListener(this);
		refund_Jp.addMouseListener(this);
		refund_JD.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = refund_JD.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				refund_JD.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 
	}
	//��������ĳ�ʼ��
	public void Init(){
		//������
		Init_UI.JDialogInit(refund_JD,"refund_JD",Default.Get_Window_X(),Default.Get_Window_Y());
		Init_UI.JPanelInit(refund_Jp, "refund_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		refund_Data = ManageSale_Sev.get_sale_item_list(sale_id);
		refund_Model = new DefaultTableModel(refund_Data,Default.Get_Sale_Item_ColumnNames()){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		refund_JT.setModel(refund_Model);
		Init_UI.JLableInit(info, "info");
		info.setCursor(null);//���ڽ��벻Ϊ��

		Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		Init_UI.JTextFieldInit(search_Box, "search_Box");
		Init_UI.JButtonInit(search, "search");
		Init_UI.JButtonInit(close, "close");
		Init_UI.JButtonInit(submit, "submit");
		Init_UI.JScrollPaneInit(refund_JS,"refund_JS");
		Init_UI.JTableInit(refund_JT,"refund_JT");
		Init_UI.JScrollPaneInit(detailed_JS,"detailed_JS");
		Init_UI.JTableInit(ticket_JT,"ticket_JT");
	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		info.setBounds(941, 26, 150, 20);
		datetime.setBounds(840, 55, 200, 20);
		search.setBounds(961, 114, 29, 27);
		search_Box.setBounds(788, 114, 177, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
		submit.setBounds(784, 582, 183, 37);
		refund_JS.setBounds(26,135,Default.Get_Window_X()-375,Default.Get_Window_Y()-183);
		detailed_JS.setBounds(774,137,Default.Get_Window_X()-847,Default.Get_Window_Y()-272);


	}
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
		if("submit".equals(e.getComponent().getName())){
			//�õ�����е�����
			ArrayList<Integer> ticket = new ArrayList<Integer>();
			if(ticket_Model.getRowCount()>0){//�����Ҫ������
				float refund=0;
				for(int i=0 ; i<ticket_Model.getRowCount() ; i++){
					ticket.add(new Integer(Integer.valueOf(ticket_Model.getValueAt(i, 0).toString())));
					refund+=Float.valueOf(ticket_Model.getValueAt(i, 1).toString());
				}
				int[] ticket_id=new int[ticket.size()];
				for(int i=0 ; i<ticket.size() ; i++){
					ticket_id[i] = ticket.get(i);
				}
				Sale sale = new Sale(login_user.getId(),"0",String.valueOf(refund),1);
				if(ManageSale_Sev.add(sale, ticket_id)){
					ManageTicket_DB.Lock_ticket(ticket_id,0);
					JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�");  
					refund_JD.dispose();
				}
			}else{	
				JOptionPane.showMessageDialog(null, "��ѡ��Ʊ");  
			}
		}else if("refund_JT".equals(e.getComponent().getName())){
			int selectedRow = refund_JT.getSelectedRow(); // ���ѡ��������
			boolean flag=true;
			if(selectedRow!=-1 && String.valueOf(refund_Model.getValueAt(selectedRow,7)).equals("F")){
				for(int i=0 ; i<ticket_Model.getRowCount() ; i++){
					if(String.valueOf(ticket_Model.getValueAt(i, 0)).equals(String.valueOf(refund_Model.getValueAt(selectedRow,3)))){
						ticket_Model.removeRow(i);
						flag=false;
					}
				}
				if(flag){
					ticket_Model.addRow(new String[]{String.valueOf(refund_Model.getValueAt(selectedRow,3)),String.valueOf(refund_Model.getValueAt(selectedRow,6))});
				}
			}
		}else if("close".equals(e.getComponent().getName())){
			refund_JD.dispose();
//		 	System.exit(0);
	}
	}
	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("refund_JD".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
	}

	public void mouseExited(MouseEvent e) {
		if("refund_Jp".equals(e.getComponent().getName())){
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
}
