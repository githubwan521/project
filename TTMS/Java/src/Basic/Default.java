package Basic;

//Ĭ��ֵ
public class Default {
	public static String Get_Connection_Information(){//�õ�������Ϣ
		return "jdbc:mysql://127.0.0.1:3306/TTMS?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//	    return "jdbc:mysql://120.77.201.150:3306/TTMS?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	}
	public static String Get_test(){
		return ".\\img\\test.jpg";
	}
	public static String Get_Path_Img_Logo(){//�õ�logo�����ͼƬ
		return ".\\img\\logo.jpg";
	}
	public static String Get_Path_Img_Adm(){//�õ�ϵͳ����Ա�����ͼƬ
		return ".\\img\\Adm\\Adm.jpg";
	}
	public static String Get_Path_Img_Adm_User(){//�õ�ϵͳ����Ա�����û������ͼƬ
		return ".\\img\\Adm\\Adm_User.jpg";
	}
	public static String Get_Path_Img_Adm_Studio(){//�õ�ϵͳ����Ա�����ݳ��������ͼƬ
		return ".\\img\\Adm\\Adm_Studio.jpg";
	}
	public static String Get_Adm_Studio_Seat(){//�õ�ϵͳ����Ա������λ�����ͼƬ
		return ".\\img\\Adm\\Adm_Studio_Seat.jpg";
	}
	public static String Get_Adm_Studio_Seat_BG(){//�õ�ϵͳ����Ա������λ����ı���ͼƬ
		return ".\\img\\Adm\\Adm_Studio_Seat_bg.jpg";
	}
	public static String Get_Path_Img_Adm_Studio_Seat(int i) {//�õ���λͼ
		return ".\\img\\Adm\\seat\\"+String.valueOf(i)+".jpg";
	}
	public static String Get_Path_Img_Cond_Main() {//�õ�Ʊ��״̬ͼ
		return ".\\img\\Cond\\condmain.jpg";
	}
	public static String Get_Path_Img_Cond_Ticket(int i) {//�õ�Ʊ��״̬ͼ
		return ".\\img\\Cond\\Ticket\\"+String.valueOf(i)+".jpg";
	}
	public static String Get_Path_Img_Cond_SellTicket() {
		return ".\\img\\Cond\\sellticket.jpg";
	}
	public static String Get_Path_Img_Cond_Sell_Seat() {
		return ".\\img\\Cond\\sell_seat.jpg";
	}
	public static String Get_Path_Img_Cond_Seat() {
		return ".\\img\\Cond\\seat.jpg";
	}
	public static String Get_Path_Img_Cond_order() {
		return ".\\img\\Cond\\order.jpg";
	}
	public static String Get_Path_Img_Cond_RefundTicket( ) {
		return ".\\img\\Cond\\refund.jpg";
	}
	public static String Get_Path_Img_Cond_RefundTicket2() {
		return ".\\img\\Cond\\refund2.jpg";
	}
	public static String Get_Path_Img_Man_Main() {
		return ".\\img\\Man\\main.jpg";
	}
	public static String Get_Path_Img_Man_Play() {
		return ".\\img\\Man\\play.jpg";
	}
	public static String Get_Path_Img_Man_Schedule() {
		return ".\\img\\Man\\schedule.jpg";
	}
	public static String Get_Path_Img_Adm_Dict() {
		return ".\\img\\Adm\\dict.jpg";
	}
	public static String Get_Path_Img_salesData() {
		return ".\\img\\salesdata.jpg";
	}
	public static String Get_Path_Img_Login() {
		return ".\\img\\login.jpg";
	}
	public static String Get_Path_Img_PW(){//�õ�logo�����ͼƬ
		return ".\\img\\pw.jpg";
	}
	public static int Get_Window_X(){//�õ����ڴ�С
		return 1050;
	}
	public static int Get_Window_Y(){
		return 668;
	}
	public static int Get_Window_X_Login(){//�õ����ڴ�С
		return 290;
	}
	public static int Get_Window_Y_Login(){
		return 486;
	}
	public static String[] Get_User_ColumnNames(){//�õ�Ĭ���û��������
		return new String[]{"UserID", "Identity", "Name","Tel","Create Time"};
	}
	public static Object[] Get_Studio_ColumnNames() {//�õ�Ĭ���ݳ����������
		return new String[]{"StudioID", "Name", "Row","Col"};	
	}
	public static Object[] Get_Schedule_ColumnNames() {//�õ�Ĭ���ݳ��ƻ��������
		return new String[]{"ScheduleID", "StudioName", "PlayName","StartTime","EndTime","Price","Arranged"};	
	}
	public static Object[] Get_Con_Schedule_ColumnNames() {//�õ�Ĭ���ݳ��ƻ��������
		return new String[]{"ScheduleID", "StudioName", "PlayName","Price"};	
	}
	public static Object[] Get_Order_ColumnNames() {//�õ���ϸ��������
		return new String[]{"StudioName","Row","Col","PlayName", "StartTime","EndTime","TicketPrice"};	
	}
	public static Object[] Get_Sale_ColumnNames() {//�õ���������
		return new String[]{"SaleID","TicketNumber","SalePayment","SaleRefund","SaleTime"};	
	}
	//�õ���ϸ��������
	public static Object[] Get_Sale_Item_ColumnNames() {
		return new String[]{"SaleItemID","PlayName","StudioName","TicketID","Row","Col","TicketPrice","Retired"};	
	}
	public static Object[] Get_Play_ColumnNames() {//�õ���Ŀ����
		return new String[]{"PlayID","PlayType","playLanguage","PlayName","PlayIntroduction"};	
	}
	public static Object[]  Get_User_SellData_ColumnNames(){   //��ƱԱ����������
		   return  new String[] {"ConductorName","SalesVolume"};
	}
	public static Object[]  Get_Play_SellData_ColumnNames(){    //��Ŀ����������
		return  new  String[]  {"PlayName","SalesVolume"};
	}
	
}
