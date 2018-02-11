package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Basic.Default;
import Basic.Sale;
import jdk.nashorn.internal.objects.annotations.Where;
import sun.print.resources.serviceui;

public class ManageSale_DB {
	//添加销售订单
	public static boolean add(Sale sale, int[] ticket_id) {
		 try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
         }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	          PreparedStatement Statement=connect.prepareStatement("insert into sale(user_id,sale_payment,sale_refund,sale_status) values(?,?,?,?)");
	          Statement.setInt(1,sale.getUser_id());
	          Statement.setString(2,sale.getSale_payment());
	          Statement.setString(3,sale.getSale_refund());
	          Statement.setInt(4,sale.getSale_status());
	          Statement.executeUpdate();

	          //得到该订单id
	          Statement=connect.prepareStatement("insert into sale(user_id,sale_payment,sale_refund,sale_status) values(?,?,?,?)");
	          String[][] tem=ManageSale_DB.get_sale_list();
	          sale.setSale_id(Integer.valueOf(tem[tem.length-1][0]));
	          //得到票价
	      	  ArrayList<String> strlist = new ArrayList<String>();
	      	  for(int i=0 ; i<ticket_id.length ; i++){

	      		  Statement=connect.prepareStatement("select ticket_price from ticket where ticket_id=?");
		          Statement.setInt(1,ticket_id[i]);
		          ResultSet set = Statement.executeQuery();
		          float price=0;
		          while(set.next()){
		        	  price = set.getFloat("ticket_price");
		          }
		          Statement=connect.prepareStatement("insert into sale_item(ticket_id,sale_id,sale_price) values(?,?,?)");
		          Statement.setInt(1,ticket_id[i]);
		          Statement.setInt(2,sale.getSale_id());
		          Statement.setFloat(3,price);
		          Statement.executeUpdate();
	          }

	          return true;
	   	 }catch(SQLException e){
	   		 System.out.print("生成订单错误！！！");
	     }
	   	 return false;
	}
	//得到订单列表
	public static String[][] get_sale_list() {
 		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select * from sale");
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
          	   strlist.add(new String[]{String.valueOf(set.getInt("sale_id")),String.valueOf(set.getInt("user_id")),String.valueOf(set.getFloat("sale_payment")),String.valueOf(set.getFloat("sale_refund")),String.valueOf(set.getInt("sale_status")),set.getTimestamp("sale_time").toString()});
	         }
	         String[][] str = new String[strlist.size()][6];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<6 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到用户订单数据错误！！！");
	     }
	   	 return null;	
	}
	//得到用户订单列表
	public static String[][] get_user_sale_list(int user_id){
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 ArrayList<String[]> strlist = new ArrayList<String[]>();
	 		 ArrayList<Integer> salelist = new ArrayList<Integer>();
	 		
	 		 //得到订单id
	 		 PreparedStatement Statement=connect.prepareStatement("select sale_id from sale where sale_status=0 and user_id=?");
	         Statement.setInt(1,user_id);
	 		 ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	 		 while (set.next()) {
	 			salelist.add(new Integer(set.getInt("sale_id")));
	 		 }
	         for(int i=0 ; i<salelist.size() ; i++){
	        	 Statement=connect.prepareStatement("select * from sale_item where sale_id = ?");
		         Statement.setInt(1,salelist.get(i));
	        	 set= Statement.executeQuery();//执行已发送的预编译的sql
	        	 int ticketnum=0;
		 		 while (set.next()) {
		 			ticketnum++;
		 		 }
		         Statement=connect.prepareStatement("select sale_payment,sale_refund,sale_time from sale where user_id=? and sale_id=?");
		         Statement.setInt(1,user_id);
		         Statement.setInt(2,salelist.get(i));

		         set= Statement.executeQuery();//执行已发送的预编译的sql
		         while (set.next()) { 
		        	 //"SaleId","TicketNumber","SalePayment","SaleRefund","SaleTime"
	          	      strlist.add(new String[]{String.valueOf(salelist.get(i)),String.valueOf(ticketnum),String.valueOf(set.getFloat("sale_payment")),String.valueOf(set.getFloat("sale_refund")),set.getTimestamp("sale_time").toString()});
		         }
	         
	         }
	     	
	         String[][] str = new String[strlist.size()][5];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<5 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
//	 		 		 System.out.print(str[i][j]+"  ");
	        	 }
//	        	 System.out.println();
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到用户订单数据错误！！！");
	     }
	   	 return null;	
	}
	//根据订单id得到详售订单记录
	public static String[][] get_sale_item_list(int sale_id) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 ArrayList<String[]> strlist = new ArrayList<String[]>();
	 		 ArrayList<Integer> ticketlist = new ArrayList<Integer>();

	 		 PreparedStatement Statement=connect.prepareStatement("select ticket_id from sale_item where sale_id=?");
             Statement.setInt(1,sale_id);
            
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) { 
	        	 ticketlist.add(new Integer(set.getString("ticket_id")));
	        	 System.out.println(set.getString("ticket_id"));
	        	 
             }

	 		 for(int i=0 ; i<ticketlist.size() ; i++){
	 			 String flag=new String("F");
	 			 Statement=connect.prepareStatement("select sale_item.ticket_id from sale,sale_item where sale.sale_id=sale_item.sale_id and sale.sale_status=1 and sale_item.ticket_id=?");
	             Statement.setInt(1,ticketlist.get(i));
	             set= Statement.executeQuery();//执行已发送的预编译的sql

		         while (set.next()) { 
		        	if(set.getInt("sale_item.ticket_id") == ticketlist.get(i)){
		        		flag=new String("T");
		        	}
		         }
	 			
	        	 // "SaleItemId","PlayName","StudioName","TicketId","Row","Col","TicketPrice"};	
		 		 Statement=connect.prepareStatement("select sale_item.sale_item_id,play.play_name,studio.studio_name,ticket.ticket_id,seat.seat_row,seat.seat_column,ticket.ticket_price from sale_item,ticket,play,studio,seat,schedule where ticket.ticket_id=sale_item.ticket_id and ticket.seat_id=seat.seat_id and studio.studio_id=schedule.studio_id and play.play_id=schedule.play_id and ticket.schedule_id=schedule.schedule_id and sale_item.sale_id=? and ticket.ticket_id=?");
	             Statement.setInt(1,sale_id);
	             Statement.setInt(2,ticketlist.get(i));

		         set= Statement.executeQuery();//执行已发送的预编译的sql
		         while (set.next()) { 
		        	 //"SaleId","TicketNumber","SalePayment","SaleRefund","SaleTime"
	      	      strlist.add(new String[]{String.valueOf(set.getInt("sale_item.sale_item_id")),set.getString("play.play_name"),
	      	    		  set.getString("studio.studio_name"),String.valueOf(set.getInt("ticket.ticket_id")),
	      	    		String.valueOf(set.getInt("seat.seat_row")),String.valueOf(set.getInt("seat.seat_column")),
	      	    		String.valueOf(set.getFloat("ticket.ticket_price")),String.valueOf(flag)});
		         
	 			 }
	 		}
	         String[][] str = new String[strlist.size()][8];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<8 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
//	 		 		 System.out.print(str[i][j]+"  ");
	        	 }
	        	 System.out.println();
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到用户订单数据错误！！！");
	     }
	   	 return null;	
	}
	public static String[][] get_search_user_sale_list(int user_id, String search) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 ArrayList<String[]> strlist = new ArrayList<String[]>();
	 		 ArrayList<Integer> salelist = new ArrayList<Integer>();
	 		
	 		 //得到订单id
	 		 PreparedStatement Statement=connect.prepareStatement("select sale_id from sale where sale_status=0 and user_id=? and CONCAT(sale_id) LIKE ?");
	         Statement.setInt(1,user_id);
	 		 Statement.setString(2,"%"+search+"%");//替换问号

	 		 ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	 		 while (set.next()) {
	 			salelist.add(new Integer(set.getInt("sale_id")));
	 		 }
	         for(int i=0 ; i<salelist.size() ; i++){
	        	 Statement=connect.prepareStatement("select * from sale_item where sale_id = ?");
		         Statement.setInt(1,salelist.get(i));
	        	 set= Statement.executeQuery();//执行已发送的预编译的sql
	        	 int ticketnum=0;
		 		 while (set.next()) {
		 			ticketnum++;
		 		 }
		         Statement=connect.prepareStatement("select sale_payment,sale_refund,sale_time from sale where user_id=? and sale_id=?");
		         Statement.setInt(1,user_id);
		         Statement.setInt(2,salelist.get(i));

		         set= Statement.executeQuery();//执行已发送的预编译的sql
		         while (set.next()) { 
		        	 //"SaleId","TicketNumber","SalePayment","SaleRefund","SaleTime"
	          	      strlist.add(new String[]{String.valueOf(salelist.get(i)),String.valueOf(ticketnum),String.valueOf(set.getFloat("sale_payment")),String.valueOf(set.getFloat("sale_refund")),set.getTimestamp("sale_time").toString()});
		         }
	         
	         }
	     	
	         String[][] str = new String[strlist.size()][5];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<5 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
//	 		 		 System.out.print(str[i][j]+"  ");
	        	 }
//	        	 System.out.println();
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到用户订单数据错误！！！");
	     }
	   	 return null;	
	}
}
