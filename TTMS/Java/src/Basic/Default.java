package Basic;

//默认值
public class Default {
	public static String Get_Connection_Information(){//得到连接信息
		return "jdbc:mysql://127.0.0.1:3306/TTMS?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//	    return "jdbc:mysql://120.77.201.150:3306/TTMS?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	}
	public static String Get_test(){
		return ".\\img\\test.jpg";
	}
	public static String Get_Path_Img_Logo(){//得到logo界面的图片
		return ".\\img\\logo.jpg";
	}
	public static String Get_Path_Img_Adm(){//得到系统管理员界面的图片
		return ".\\img\\Adm\\Adm.jpg";
	}
	public static String Get_Path_Img_Adm_User(){//得到系统管理员管理用户界面的图片
		return ".\\img\\Adm\\Adm_User.jpg";
	}
	public static String Get_Path_Img_Adm_Studio(){//得到系统管理员管理演出厅界面的图片
		return ".\\img\\Adm\\Adm_Studio.jpg";
	}
	public static String Get_Adm_Studio_Seat(){//得到系统管理员管理座位界面的图片
		return ".\\img\\Adm\\Adm_Studio_Seat.jpg";
	}
	public static String Get_Adm_Studio_Seat_BG(){//得到系统管理员管理座位界面的背景图片
		return ".\\img\\Adm\\Adm_Studio_Seat_bg.jpg";
	}
	public static String Get_Path_Img_Adm_Studio_Seat(int i) {//得到座位图
		return ".\\img\\Adm\\seat\\"+String.valueOf(i)+".jpg";
	}
	public static String Get_Path_Img_Cond_Main() {//得到票的状态图
		return ".\\img\\Cond\\condmain.jpg";
	}
	public static String Get_Path_Img_Cond_Ticket(int i) {//得到票的状态图
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
	public static String Get_Path_Img_PW(){//得到logo界面的图片
		return ".\\img\\pw.jpg";
	}
	public static int Get_Window_X(){//得到窗口大小
		return 1050;
	}
	public static int Get_Window_Y(){
		return 668;
	}
	public static int Get_Window_X_Login(){//得到窗口大小
		return 290;
	}
	public static int Get_Window_Y_Login(){
		return 486;
	}
	public static String[] Get_User_ColumnNames(){//得到默认用户表格列名
		return new String[]{"UserID", "Identity", "Name","Tel","Create Time"};
	}
	public static Object[] Get_Studio_ColumnNames() {//得到默认演出厅表格列名
		return new String[]{"StudioID", "Name", "Row","Col"};	
	}
	public static Object[] Get_Schedule_ColumnNames() {//得到默认演出计划表格列名
		return new String[]{"ScheduleID", "StudioName", "PlayName","StartTime","EndTime","Price","Arranged"};	
	}
	public static Object[] Get_Con_Schedule_ColumnNames() {//得到默认演出计划表格列名
		return new String[]{"ScheduleID", "StudioName", "PlayName","Price"};	
	}
	public static Object[] Get_Order_ColumnNames() {//得到详细订单列名
		return new String[]{"StudioName","Row","Col","PlayName", "StartTime","EndTime","TicketPrice"};	
	}
	public static Object[] Get_Sale_ColumnNames() {//得到订单列名
		return new String[]{"SaleID","TicketNumber","SalePayment","SaleRefund","SaleTime"};	
	}
	//得到详细订单列名
	public static Object[] Get_Sale_Item_ColumnNames() {
		return new String[]{"SaleItemID","PlayName","StudioName","TicketID","Row","Col","TicketPrice","Retired"};	
	}
	public static Object[] Get_Play_ColumnNames() {//得到剧目列名
		return new String[]{"PlayID","PlayType","playLanguage","PlayName","PlayIntroduction"};	
	}
	public static Object[]  Get_User_SellData_ColumnNames(){   //售票员的销售数据
		   return  new String[] {"ConductorName","SalesVolume"};
	}
	public static Object[]  Get_Play_SellData_ColumnNames(){    //剧目的销售数据
		return  new  String[]  {"PlayName","SalesVolume"};
	}
	
}
