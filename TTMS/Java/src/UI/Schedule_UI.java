package UI;

import Basic.*;
import server.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Schedule_UI  implements  MouseListener {
	User login_user;//��½�����û�
	JFrame fJFrame;//������
	//������

	private JLabel schedule= new JLabel("��  ��  ��  ��  ��  ��",JLabel.CENTER);
	private JLabel info = new JLabel();
	private JLabel datetime = new JLabel();
	private JTextField search_Box = new JTextField();
	private JButton search = new JButton();
	private JButton sd_add = new JButton();
	private JButton sd_delete = new JButton();
	private JButton sd_update = new JButton();
	private JButton sd_refresh = new JButton();

	private Choice sd_play = new Choice();
	private Choice sd_studio = new Choice();
	private Choice sd_start_HH = new Choice();
	private Choice sd_start_MM = new Choice();
	private Choice sd_status = new Choice();
    private ui.DateChooser sd_start_time = new ui.DateChooser("yyyy-MM-dd");
	private JTextField sd_end_time = new JTextField(); 
	private JTextField sd_price = new JTextField(); 

    private myJPanel_UI sd_Jp = new myJPanel_UI(Default.Get_Path_Img_Man_Schedule());//�O�ñ���   �����
	private String[][] sd_Data = ManageSchedule_Sev.get_schedule_list();
	private DefaultTableModel sd_Model = new DefaultTableModel(sd_Data,Default.Get_Schedule_ColumnNames()){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	};
	private JTable sd_JT = new JTable(sd_Model);//�û����
	private JScrollPane sd_JS = new JScrollPane();//�O�ñ���   �����
	
	private JButton close = new JButton();
	private static Point origin = new Point();// ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
	private JDialog sd_JF;
		
	public Schedule_UI(JFrame Con_JF, User Conductor){
		fJFrame = Con_JF;
		login_user = Conductor;
		sd_JF = new JDialog(Con_JF, true);
		this.Init();//��ʼ��
		this.setSize();//Ĭ��������ʼλ�����С
		this.lisdener();//����
//		fJFrame.setVisible(false);
		
		//������
		info.setText(login_user.getName());
	//	info.setFont(new Font("serif",0,20));
		datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		sd_Jp.add(schedule);
		sd_Jp.add(info);		
		sd_Jp.add(datetime);
		sd_Jp.add(search_Box);
		sd_Jp.add(search);
		sd_Jp.add(close);
		sd_Jp.add(sd_refresh);
		sd_Jp.add(sd_add);
		sd_Jp.add(sd_delete);
		sd_Jp.add(sd_update);
		sd_Jp.add(sd_play);
		sd_Jp.add(sd_studio);

		sd_Jp.add(sd_start_time);
		sd_Jp.add(sd_start_HH);
		sd_Jp.add(sd_start_MM);
		sd_Jp.add(sd_end_time);
		sd_Jp.add(sd_status);
		sd_Jp.add(sd_price);

		sd_JS.getViewport().add(sd_JT);
		sd_Jp.add(sd_JS);
		sd_JF.add(sd_Jp);
		sd_JF.setVisible(true);
	}
	//����ļ���
	public void lisdener(){
		//������
		schedule.addMouseListener(this);
		info.addMouseListener(this);
		search.addMouseListener(this);
		close.addMouseListener(this);
		sd_JT.addMouseListener(this);
		sd_JS.addMouseListener(this);
		sd_start_HH.addMouseListener(this);
		sd_start_MM.addMouseListener(this);
		sd_play.addMouseListener(this);
		sd_add.addMouseListener(this);
		sd_delete.addMouseListener(this);
		sd_update.addMouseListener(this);
		sd_status.addMouseListener(this);
		sd_refresh.addMouseListener(this);
		sd_JF.addMouseListener(this);
		sd_Jp.addMouseListener(this);
		sd_JF.addMouseMotionListener(new MouseMotionAdapter() {
			// �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
			public void mouseDragged(MouseEvent e) {
				// ������϶�ʱ��ȡ���ڵ�ǰλ��
				Point tem = sd_JF.getLocation();
				// ���ô��ڵ�λ��
				// ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
				sd_JF.setLocation(tem.x + e.getX() - origin.x, tem.y + e.getY()- origin.y);
			}
		}); 

	}
	//��������ĳ�ʼ��
	public void Init(){
		//������
		ui.Init_UI.JDialogInit(sd_JF,"sd_JF",Default.Get_Window_X(),Default.Get_Window_Y());
		ui.Init_UI.JPanelInit(sd_Jp, "sd_Jp",0,0,Default.Get_Window_X(),Default.Get_Window_Y());
		ui.Init_UI.JLableInit(schedule, "schedule");
		schedule.setFont(new Font("serif",1,38));
		schedule.setForeground(Color.red);
		ui.Init_UI.JLableInit(info, "info");
		ui.Init_UI.JLableInit(datetime, "datetime");
		datetime.setCursor(null);//���ڽ��벻Ϊ��
		ui.Init_UI.JTextFieldInit(search_Box, "search_Box");
		ui.Init_UI.JButtonInit(sd_add, "sd_add");
		ui.Init_UI.JButtonInit(sd_delete, "sd_delete");
		ui.Init_UI.JButtonInit(sd_update, "sd_update");
		ui.Init_UI.JButtonInit(sd_refresh, "sd_refresh");
		ui.Init_UI.JTextFieldInit(sd_price, "sd_price");
		sd_price.setDocument(new ui.DoubleDocument());

		ui.Init_UI.JButtonInit(search, "search");
		ui.Init_UI.JButtonInit(close, "close");
		ui.Init_UI.JScrollPaneInit(sd_JS,"sd_JS");
		ui.Init_UI.JTableInit(sd_JT,"sd_JT");
		//�����п�
		sd_JT.getColumnModel().getColumn(0).setPreferredWidth(28);
		sd_JT.getColumnModel().getColumn(1).setPreferredWidth(28);
		sd_JT.getColumnModel().getColumn(5).setPreferredWidth(30);
		sd_JT.getColumnModel().getColumn(6).setPreferredWidth(30);

		ui.Init_UI.ChoiceInit(sd_play, "sd_play");
		ui.Init_UI.ChoiceInit(sd_status, "sd_status");
		sd_status.add("Y");
		sd_status.add("N");
		
		String[][] play=ManagePlay_Sev.get_play_list();
		for(int i=0 ; i<play.length ; i++){
			sd_play.add(play[i][3]);
		}
		ui.Init_UI.ChoiceInit(sd_studio, "sd_studio");
		String[][] studio=ManageStudio_Sev.get_studio_list();
		for(int i=0 ; i<studio.length ; i++){
			sd_studio.add(studio[i][1]);
		}
	
		ui.Init_UI.ChoiceInit(sd_start_HH, "sd_start_HH");
		for(int i=0 ; i<=23 ; i++){
			if(i<10){
				sd_start_HH.add("0"+String.valueOf(i));
			}else{
				sd_start_HH.add(String.valueOf(i));
			}
		}
		ui.Init_UI.ChoiceInit(sd_start_MM, "sd_start_MM");
		for(int i=0 ; i<=55 ; i+=5){
			if(i<10){
				sd_start_MM.add("0"+String.valueOf(i));
			}else{
				sd_start_MM.add(String.valueOf(i));
			}
		}
		ui.Init_UI.JTextFieldInit(sd_end_time, "sd_end_time");
		sd_end_time.setFont(new Font("΢���ź�",1, 20));//���������ʽ
     	sd_end_time.setEditable(false);//��������

	}
	//������������Ĵ�С����ʼλ��
	public void setSize(){
		//������
		schedule.setBounds(260, 100, 400, 50);
		info.setBounds(941, 26, 150, 20);
		datetime.setBounds(840, 55, 200, 20);
		search.setBounds(961, 114, 29, 27);
		search_Box.setBounds(790, 114, 171, 27);
		close.setBounds(Default.Get_Window_X()-25, 0, 25, 25);
		sd_JS.setBounds(62,328,928,305);

		sd_play.setBounds(201, 227, 155, 28);
		sd_studio.setBounds(201, 179, 155, 28);
		sd_start_time.setBounds(539, 179, 107, 30);
		sd_start_HH.setBounds(652, 179, 45, 28);
		sd_start_MM.setBounds(706, 179, 45, 28);
		sd_end_time.setBounds(539, 227, 180, 30);
		sd_price.setBounds(904, 227, 87, 30);
		sd_add.setBounds(91, 272, 202, 41);
		sd_delete.setBounds(759, 272, 202, 41);
		sd_update.setBounds(425, 272, 202, 41);
		sd_status.setBounds(904, 179, 87, 29);
		sd_refresh.setBounds(719, 227, 32, 30);
	}
	
	public void mouseClicked(MouseEvent e) {// �������ʱִ�еĲ��� 
//		if("sd_JF".equals(e.getComponent().getName())){
//			int play_id = ManagePlay_Sev.get_play_id(sd_play.getSelectedItem());
//			sd_end_time.setText(ManagePlay_Sev.get_play_endtime(play_id, DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
//								             sd_start_MM.getSelectedItem()));
//		}else
		if("sd_refresh".equals(e.getComponent().getName())){
			int play_id = ManagePlay_Sev.get_play_id(sd_play.getSelectedItem());
			sd_end_time.setText(ManagePlay_Sev.get_play_endtime(play_id, ui.DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
								             sd_start_MM.getSelectedItem()));
		}else if("sd_JT".equals(e.getComponent().getName())){
			int selectedRow = sd_JT.getSelectedRow(); // ���ѡ��������
			sd_start_HH.select(sd_JT.getValueAt(selectedRow, 3).toString().substring(11,13));		
			sd_start_MM.select(sd_JT.getValueAt(selectedRow, 3).toString().substring(14,16));		
			sd_play.select(sd_JT.getValueAt(selectedRow, 2).toString());		
			sd_studio.select(sd_JT.getValueAt(selectedRow, 1).toString());		
			sd_status.select(sd_JT.getValueAt(selectedRow, 6).toString());		
			sd_end_time.setText(sd_JT.getValueAt(selectedRow, 4).toString());
			sd_price.setText(sd_JT.getValueAt(selectedRow, 5).toString());
//			try {
//				sd_start_time = new DateChooser(new SimpleDateFormat("yyyy-MM-dd").parse(sd_JT.getValueAt(selectedRow, 3).toString()),"yyyy-MM-dd");
//				System.out.print(sd_start_time.showDate.getText());
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
		}else if("sd_add".equals(e.getComponent().getName())){
			if(sd_price.getText().length()>0){
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
				Calendar c = Calendar.getInstance();  
			    c.setTime(new Date());  
			    c.add(Calendar.DAY_OF_MONTH, 1);// ����+1��  
			    String start_time = format.format(c.getTime()); 
				
				System.out.println(sd_start_time.showDate.getText());
				if(sd_start_time.showDate.getText().compareTo(start_time)>=0){
					Schedule schedule = new Schedule();
					int studio_id = ManageStudio_Sev.get_studio_id(sd_studio.getSelectedItem());
					int play_id = ManagePlay_Sev.get_play_id(sd_play.getSelectedItem());
					String price = new String(sd_price.getText());
					String time = new String(ui.DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
							                 sd_start_MM.getSelectedItem());
					schedule.setPlay_id(play_id);
					schedule.setStudio_id(studio_id);
					schedule.setSchedule_time(time);
					schedule.setSchedule_price(price);
					if(sd_status.getSelectedItem().equals("Y")){
						schedule.setSchedule_status(1);
					}else{
						schedule.setSchedule_status(0);
					}
					if(ManageSchedule_Sev.add(schedule)){
						sd_Model.addRow(ManageSchedule_Sev.get_schedule_list()[ManageSchedule_Sev.get_schedule_list().length-1]); // ���һ��
						sd_Data = ManageSchedule_Sev.get_schedule_list();
						JOptionPane.showMessageDialog(null, "����ݳ��ƻ��ɹ�");  
					}else{
						JOptionPane.showMessageDialog(null, "�ݳ��ƻ���ͻ,�밲�������е��ݳ��ƻ���ʼ֮���4Сʱ");  
					}
					int tem_play_id = ManagePlay_Sev.get_play_id(sd_play.getSelectedItem());
					sd_end_time.setText(ManagePlay_Sev.get_play_endtime(tem_play_id, ui.DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
										             sd_start_MM.getSelectedItem()));
				}else{
					JOptionPane.showMessageDialog(null,"�ݳ��ƻ���ʼ����������ǰһ��");  
				}
			}else{
				JOptionPane.showMessageDialog(null,"������Ʊ��");  
			}
		}else if("sd_delete".equals(e.getComponent().getName())){
			int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ�����ݳ��ƻ���?", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION);   
			if (n == JOptionPane.YES_OPTION) {
				int selectedRow = sd_JT.getSelectedRow();// ���ѡ���е�����
				if(sd_JT.getValueAt(selectedRow, 6).toString().equals("N")){	
					if (selectedRow != -1){	
						
						int schedule_id = (Integer.valueOf(sd_JT.getValueAt(selectedRow, 0).toString()));
						if(ManageSchedule_Sev.delete(schedule_id)){
							sd_Model.removeRow(selectedRow); // ɾ����
							JOptionPane.showMessageDialog(null,"ɾ���ɹ�");  
						}else{
							JOptionPane.showMessageDialog(null,"ɾ��ʧ��,���ݳ��ƻ�������ʹ����");  
						}
					}else{
						JOptionPane.showMessageDialog(null,"ɾ��ʧ��,��ѡ���ݳ��ƻ�");  
					}
				}else{
					JOptionPane.showMessageDialog(null,"�޷�ɾ���������ݵ��ݳ��ƻ�,");  
				}
			}else if (n == JOptionPane.NO_OPTION) {   
			}  
		}else if("sd_update".equals(e.getComponent().getName())){
			if(sd_price.getText().length()>0){

				int tem_play_id = ManagePlay_Sev.get_play_id(sd_play.getSelectedItem());
				sd_end_time.setText(ManagePlay_Sev.get_play_endtime(tem_play_id, ui.DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
									             sd_start_MM.getSelectedItem()));
				int selectedRow = sd_JT.getSelectedRow();
				if(selectedRow != -1){
					if(sd_JT.getValueAt(selectedRow, 6).toString().equals("N")){		
						Schedule schedule = new Schedule();
	//			        System.out.println(datatime.showDate.getText());
						int studio_id = ManageStudio_Sev.get_studio_id(sd_studio.getSelectedItem());
						int play_id = ManagePlay_Sev.get_play_id(sd_play.getSelectedItem());
						int status;
						if(sd_status.getSelectedItem().equals("Y")){
							status=1;
						}else{
							status=0;
						}
						String price = new String(sd_price.getText());
						String time = new String(ui.DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
								                 sd_start_MM.getSelectedItem());
						schedule.setPlay_id(play_id);
						schedule.setStudio_id(studio_id);
						schedule.setSchedule_time(time);
						schedule.setSchedule_price(price);
						schedule.setSchedule_status(status);
						schedule.setSchedule_id(Integer.valueOf(sd_JT.getValueAt(selectedRow, 0).toString()));
						if(ManageSchedule_Sev.update(schedule)){
							JOptionPane.showMessageDialog(null,"�޸ĳɹ�");  
							sd_JT.setValueAt(sd_studio.getSelectedItem(),selectedRow, 1);
							sd_JT.setValueAt(sd_play.getSelectedItem(),selectedRow, 2);
							sd_JT.setValueAt(ui.DateChooser.showDate.getText()+" "+sd_start_HH.getSelectedItem()+":"+
					                 		sd_start_MM.getSelectedItem(),selectedRow, 3);
							sd_JT.setValueAt(sd_end_time.getText(),selectedRow, 4);
							sd_JT.setValueAt(sd_price.getText(),selectedRow, 5);
							sd_JT.setValueAt(sd_status.getSelectedItem(),selectedRow, 6);
						}else{
							JOptionPane.showMessageDialog(null,"�޸�ʧ��");  
						}
	
					}else{
						JOptionPane.showMessageDialog(null,"�޷��޸��������ݵ��ݳ��ƻ�");  
					}
				}else{
					JOptionPane.showMessageDialog(null,"��ѡ��Ҫ�޸ĵ��ݳ��ƻ�");  
				}
			}else{
				JOptionPane.showMessageDialog(null,"������Ʊ��");  
			}
		}else if("schedule".equals(e.getComponent().getName())){
			sd_JT.setModel(sd_Model);
		}else if("search".equals(e.getComponent().getName())){
			sd_JT.setModel(new DefaultTableModel(ManageSchedule_Sev.get_search_schedule_list(String.valueOf(search_Box.getText())),Default.Get_Schedule_ColumnNames()){
				public boolean isCellEditable(int row,int column){ 
					return false;
				}  
			});
		}else if("close".equals(e.getComponent().getName())){
//			 System.exit(0);
			sd_JF.dispose();
		}
	}

	public void mousePressed(MouseEvent e) {// ���������ϰ���ʱִ�еĲ���  
		// ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
		if("sd_JF".equals(e.getComponent().getName())){
	    	origin.x = e.getX();
			origin.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// ����ͷ�ʱִ�еĲ���  	
	}

	public void mouseEntered(MouseEvent e) {// ���������ʱִ�еĲ��� 
	}

	public void mouseExited(MouseEvent e) {
		if("sd_Jp".equals(e.getComponent().getName())){//�����������ʱ��
			datetime.setText(DateFormat.getDateInstance().format(new Date())+"  "+(new Date().toString().substring(11, 16)));
		}
	}
	
	public static void main(String[] args){
		User user = new User();
		user.setId(3);
		user.setIdentity("����");
		user.setName("motian");
		new Schedule_UI(null,user);
	}
}


