 package db;

import Basic.Default;
import Basic.Schedule;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManageOrder_DB {
	//得到详细订单里的数据
	public static String[][] getorder_list(Schedule schedule, int[] ticket) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	 		 for(int i=0 ; i<ticket.length ; i++){
	 			 PreparedStatement Statement=connect.prepareStatement("select studio_name,seat_row,seat_column,play_name,schedule_time,play_duration,ticket_price from schedule,play,seat,studio,ticket where play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id and ticket.seat_id=seat.seat_id and ticket.ticket_id=? and schedule.schedule_id=?");
	 			 Statement.setInt(1,ticket[i]);
	 			 Statement.setInt(2,schedule.getSchedule_id());
	 			 ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	 			 while(set.next()){
	 				 System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(set.getTimestamp("schedule_time").getTime()+set.getInt(("play_duration"))*60*1000)));
	 				 strlist.add(new String[]{set.getString("studio_name"),String.valueOf(set.getInt("seat_row")),String.valueOf(set.getInt("seat_column")),set.getString("play_name"),set.getTimestamp("schedule_time").toString().substring(0,16),
            			                    new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(set.getTimestamp("schedule_time").getTime()+set.getInt(("play_duration"))*60*1000)),String.valueOf(set.getFloat("ticket_price"))});        	   
	 			 }
	 		 }
	         String[][] str = new String[strlist.size()][7];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<7 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        		 System.out.print(str[i][j]+" ");
	        	 }
	        	 System.out.println();
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到详细订单里的数据错误！！！");
	     }
		return null;
	}

}
