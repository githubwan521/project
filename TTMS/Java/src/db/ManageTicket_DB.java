package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;

import Basic.Default;
import Basic.Schedule;
import Basic.Studio;

public class ManageTicket_DB {
	//批量添加票
	public static boolean add_ticket(Schedule schedule) {
		 try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
   	  try {
   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 		    
 		    //得到所有的座位id
   			PreparedStatement Statement=connect.prepareStatement("select seat_id,seat_status from seat where studio_id=?");
	        Statement.setInt(1,schedule.getStudio_id());
   			ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql

   			ArrayList<Integer[]> seatlist = new ArrayList<Integer[]>();
	         while (set.next()) {
           	      seatlist.add(new Integer[]{set.getInt("seat_id"),set.getInt("seat_status")});
	         }
	         for(int i=0 ; i<seatlist.size();i++){
	        	Statement=connect.prepareStatement("insert into ticket(seat_id,schedule_id,ticket_price,ticket_status) values(?,?,?,?)");
	 	        Statement.setInt(1,seatlist.get(i)[0].intValue());
	 	        Statement.setInt(2,schedule.getSchedule_id());
	 	        Statement.setString(3,schedule.getSchedule_price());
	 	        if(seatlist.get(i)[1].intValue() == 0){
	 	        	Statement.setInt(4,0);
	 	        }else{
	 	        	Statement.setInt(4,3);
	 	        }
	 	        		
	 	        Statement.executeUpdate();
	         }
	         return true;
   	 }catch(SQLException e){
   		 System.out.print("批量添加票错误！！！");
        }
   	 return false;	
   }
	public static boolean delete_ticket(int schedule_id) {
		 try {
           Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
        }catch (Exception e) {
           System.out.print("加载MySQL驱动错误!");
       }
  	  try {
  		 //连接数据库
  		 //连接数据库
	 		Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	        PreparedStatement Statement=connect.prepareStatement(" delete from ticket where schedule_id=?");
	        Statement.setInt(1,schedule_id);//替换问号
	        if(Statement.executeUpdate()>0){//删除成功
	        	return true;
	        }else{
	        	return false;
	        }
  	 }catch(SQLException e){
  		 System.out.print("批量删除票错误！！！");
       }
  	 return false;	
  }
	//查询票的状态
	public static int[][] get_ticket_state(int schedule_id) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		try {
	        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	     }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	         PreparedStatement Statement=connect.prepareStatement("select seat_row,seat_column,ticket_status from schedule,ticket,seat where schedule.schedule_id=ticket.schedule_id and ticket.seat_id=seat.seat_id and schedule.schedule_id=?");
	         Statement.setInt(1,schedule_id);
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         int[][] seat_state = new int[10][15];
	         while (set.next()){
	        	 seat_state[set.getInt("seat_row")][set.getInt("seat_column")] = set.getInt("ticket_status");
	         }
	        return seat_state;
	      
	   	 }catch(SQLException e){
			System.out.print("查询票的状态错误！！！");
	   	 }
	   	 return null;
}
	//根据座位坐标与演出计划id查找该座位的票id
	public static int[] get_ticket_id(int schedule_id, ArrayList<String[]> ticket){
		try {
	        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	     }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 ArrayList<Integer> ticket_id_tem = new ArrayList<Integer>();

	 		 for(int i=0 ; i<ticket.size() ; i++){
	        	 PreparedStatement Statement=connect.prepareStatement("select ticket_id from ticket,schedule,seat where schedule.schedule_id=ticket.schedule_id and ticket.seat_id=seat.seat_id and seat.seat_row=? and seat.seat_column=? and schedule.schedule_id=?");
	        	 Statement.setString(1,ticket.get(i)[0]);
	        	 Statement.setString(2,ticket.get(i)[1]);
	        	 Statement.setInt(3,schedule_id);
	        	 ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	        	 while (set.next()){
	        		 ticket_id_tem.add(new Integer(set.getInt("ticket_id")));
	        	 }
	         }
	 		 int[] ticket_id = new int[ticket_id_tem.size()];
	 		 for(int i=0 ; i<ticket_id_tem.size() ; i++){
	 			 ticket_id[i] = ticket_id_tem.get(i);
	 			 System.out.println(ticket_id[i]);
	 		 }
	        return ticket_id;
	      
	   	 }catch(SQLException e){
			System.out.print("根据座位坐标与演出计划id查找该座位的票错误！！！");
	   	 }
	   	 return null;
	}
	//改票的状态票
	public static boolean Lock_ticket(int[] ticket_id,int status){
		try {
	        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	     }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }
	   	 try {
   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 		 //先得到票的状态
 		 for(int i=0 ; i<ticket_id.length ; i++){
 			 PreparedStatement Statement=connect.prepareStatement("select ticket_status from ticket where ticket_id=?");
 			 Statement.setInt(1,ticket_id[i]);
 			 ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
 			 while (set.next()){
        		 if(set.getInt("ticket_status") == status){//如果票的状态为代修改的状态则不允许修改
        			 return false;
        		 }
        	 }
 		 }
         
 		 for(int i=0 ; i<ticket_id.length ; i++){
 			 PreparedStatement Statement=connect.prepareStatement("update ticket set ticket_status=? where ticket_id=?");
 			 Statement.setInt(1,status);
 			 Statement.setInt(2,ticket_id[i]);
 			 Statement.executeUpdate();//执行已发送的预编译的sql
 		 }
         
 		 return true;
      
	   	 }catch(SQLException e){
			System.out.print("查询票的状态错误！！！");
	   	 }
		return false;
	}
	public static boolean sell_ticket(int[] ticket_id){
		try {
	        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	     }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }
	   	 try {
   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 		 //先得到票的状态
 		 for(int i=0 ; i<ticket_id.length ; i++){
 			 PreparedStatement Statement=connect.prepareStatement("update ticket set ticket_status=2  where ticket_id=?");
 			 Statement.setInt(1,ticket_id[i]);
 			   Statement.executeUpdate();//执行已发送的预编译的sql
 		 }

        return true;
      
	   	 }catch(SQLException e){
			System.out.print("查询票的状态错误！！！");
	   	 }
		return false;
	}
}
