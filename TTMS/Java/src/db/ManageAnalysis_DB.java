package db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Basic.Default;

public class ManageAnalysis_DB {

public static String[][] analysisAllPlay(String start_time,String end_time){
	ArrayList<String[]> strlist = new ArrayList<String[]>();
	float total=0;
	try {
        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
    }catch (Exception e) {
        System.out.print("加载MySQL驱动错误!");
    }
   	 try {
   		 //连接数据库
   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");
   		 PreparedStatement Statement=connect.prepareStatement("select  distinct play_id from schedule where "
   		 		+ "schedule_time >=? and "
   		 		+ "schedule_time <=?");
   		 System.out.println("print");
   		Statement.setString(1, start_time);
        Statement.setString(2, end_time);
        
         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
        
   		 while (set.next()){
   			System.out.println("print2");
   			 strlist.add(analysisPlayitem(set.getInt("play_id"),start_time, end_time)[0]);
   			 System.out.println("print1"); 
   		 }
   		 String[][] str = new String[strlist.size()][2];
   		 for(int i=0 ; i<strlist.size() ; i++){
   			 for(int j=0 ; j<2 ; j++){
   				 str[i][j] = strlist.get(i)[j];
   			 }
   		 }
        return str;
   		 
     }catch(SQLException e){
   	  	System.out.print("查询所有剧目的销售统计错误");
     }
     
	return null;
}

public static int returnPlayID(String play_name){
	try{
		Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	    }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }

	   	 try {
	   		 //连接数据库
	   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select play_id from play where play_name=?");
	         Statement.setString(1,play_name);
	        
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         
	         
	            return set.getInt("play_id");
	     }catch(SQLException e){
	   	  	System.out.print("查询剧目错误");
	     }
		return 0;
}
public static String[][] analysisSaler(int user_id,String start_time,String end_time){
	float total=0;
	String[][] str=new String[1][2];
	try {
        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
    }catch (Exception e) {
        System.out.print("加载MySQL驱动错误!");
    }
   	 try {
   		 //连接数据库
   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");

         PreparedStatement  Statement=connect.prepareStatement("select user_name from user where user_id=?");
         Statement.setInt(1, user_id);
         ResultSet set=Statement.executeQuery();
         while(set.next()){
        	 str[0][0]=set.getString("user_name");
         }
         Statement=connect.prepareStatement("select sale_payment ,sale_refund ,sale_status from sale  "
        		 + "where user_id=? and "
        		 + "sale_time >=? and "
        		 + "sale_time <=?");
      
        Statement.setInt(1, user_id);
        Statement.setString(2, start_time);
        Statement.setString(3, end_time);
         set= Statement.executeQuery();//执行已发送的预编译的sql
         while(set.next()){
        	 if(set.getInt("sale_status")==0)
        		 total=total+set.getInt("sale_payment")-set.getInt("sale_refund");
        	 else if(set.getInt("sale_status")==1)
        		 total=total-set.getInt("sale_refund");
         }
         
        
		 str[0][1]=String.valueOf(total);
            return str;
     }catch(SQLException e){
   	  	System.out.print("查询指定售票员的销售统计错误");
     }
	return null;
}

public static String[][] analysisAllSaler(String start_time,String end_time){
	ArrayList<String[]> strlist = new ArrayList<String[]>();
	try{
		Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	    }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }

	   	 try {
	   		 //连接数据库
	   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select user_id from user where user_identity ='售票员';");
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         
	         while(set.next()){
	        	 
	        	 strlist.add(analysisSaler(set.getInt("user_id"),start_time,end_time)[0]);
	        	 System.out.println("yang"+analysisSaler(set.getInt("user_id"),start_time,end_time)[0]);
	         }
	         String[][] str = new String[strlist.size()][2];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<2 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         	
	            return str;
	     }catch(SQLException e){
	   	  	System.out.print("查询所有售票员的销售统计错误");
	     }
		return null;
	}


public static String[][] analysisSaler_playitem(int user_id ,int play_id,String start_time,String end_time){
	int total=0;
	String[][] str=new String[1][2];
	try{
		Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	    }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }

	   	 try {
	   		 //连接数据库
	   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select play_name from play where play_id=?");
	         Statement.setInt(1, play_id);
	         ResultSet set= Statement.executeQuery();
	         str[0][0]=set.getString("play_name");
	         
	         
	         Statement=connect.prepareStatement("select distinct sale.sale_id ,sale_payment,sale_refund,sale_status  "
	         		+ "from user,sale,sale_item,ticket,schedule ,play "
	         		+ " where user_id=? and "
	         		+ "play.play_id=? and "
	         		+ "schedule.schedule_time >=? and "
	         		+ "schedule.scheudle_time <=? and "
	         		+ "user.user_id=sale.user_id and "
	         		+ "sale.sale_id=sale_item.sale_id and "
	         		+ "sale_item.ticket_id=ticket.ticket_id and "
	         		+ "ticket.schedule_id=schedule.schedule_id and "
	         		+ "play.play_id=schedule.play_id;");
	         Statement.setInt(1,user_id);
	         Statement.setInt(2,play_id);
	         Statement.setString(3, start_time);
	         Statement.setString(4, end_time);
	         
	         set= Statement.executeQuery();//执行已发送的预编译的sql
	         while(set.next()){
	        	 if(set.getInt("sale_status")==0){
	        		 total+=set.getInt("sale_payment")-set.getInt("sale_refund");
	         
	        	 }
	        	 else if (set.getInt("sale_status")==1){
	        	
	        		 total-=set.getInt("sale_refund");
	        	 }
	         }
	         str[0][1]=String.valueOf(total);
	         return str;
	   	 }  
	     catch(SQLException e){
	   	  	System.out.print("查询指定售票员的指定销售统计错误");
	   	  	return null;
	     }
		
}
	
public static String[][] analysisSale_play(int user_id,String string,String string2){
	ArrayList<String[]> strlist = new ArrayList<String[]>();
	try{
		Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	    }catch (Exception e) {
	        System.out.print("加载MySQL驱动错误!");
	    }

	   	 try {
	   		 //连接数据库
	   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select distinct play_id from schedule,sale "
	         		+ "where schedule.schedule_id= sale.schedule_id and "
	         		+ "sale.user_id=?;");
	         Statement.setInt(1,user_id);
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while(set.next()){
	        	 
	        	 strlist.add(analysisSaler_playitem( user_id , set.getInt("play_id"), string, string2)[0]);
	        	 
	         }
	         String[][] str = new String[strlist.size()][2];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<2 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         	
	            return str;
	            
	     }catch(SQLException e){
	   	  	System.out.print("查询指定售票员所有销售统计错误");
	     }
		return null;
}

public static String[][]  analysisPlayitem (int play_id,String start_time,String end_time){
	float total=0;
	String[][] str=new String[1][2];
	try {
        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
    }catch (Exception e) {
        System.out.print("加载MySQL驱动错误!");
    }
   	 try {
   		 //连接数据库
   		 Connection connect = DriverManager.getConnection( Default.Get_Connection_Information(),"root","765885195");

         PreparedStatement  Statement=connect.prepareStatement("select play_name from play where play_id=?");
         Statement.setInt(1, play_id);
         ResultSet set=Statement.executeQuery();
         System.out.println("print10");
         while(set.next()){
         str[0][0]=set.getString("play_name"); 
         }
         System.out.println("print11");
         Statement=connect.prepareStatement("select ticket_price,ticket_id from ticket,schedule "
         		+ "where schedule.play_id =? and "
         		+ "schedule.schedule_id=ticket.schedule_id "
         		+ "and ticket_status='2' and "
        		 + "schedule.schedule_time >= ? and "
          		+ "schedule.schedule_time <= ?");
         Statement.setInt(1, play_id);
         Statement.setString(2, start_time);
         Statement.setString(3, end_time);
         System.out.println("print12");
          set= Statement.executeQuery();//执行已发送的预编译的sql
          
         while(set.next()){
        	 
        		 total+=set.getFloat("ticket_price");
         }
         
         str[0][1]=String.valueOf(total);
        
		
            return str;
     }catch(SQLException e){
   	  	System.out.print("查询剧目id的销售统计错误");
     }
	return null;
}
}
