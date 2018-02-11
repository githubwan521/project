package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.ranges.Range;

import Basic.Default;
import Basic.Schedule;
import Basic.Studio;

public class ManageSchedule_DB {
	//添加演出计划(状态i)
	public static boolean add(Schedule schedule) {
		 try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
   	 
   	 try {
   		 //连接数据库
 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	      PreparedStatement Statement=connect.prepareStatement("select *from schedule where studio_id=?;");
	      Statement.setInt(1, schedule.getStudio_id());
	      ResultSet set= Statement.executeQuery();
	      while(set.next()){
		    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		        Date THIS = null,OTHER = null;
		  		try {
		  			THIS = df.parse(schedule.getSchedule_time());
		  			OTHER = df.parse(set.getTimestamp("schedule_time").toString().substring(0, 16));
		  		} catch (ParseException e) {
		  			e.printStackTrace();
		  		}
//		  		System.out.println("添加的开始时间"+THIS.getTime()+"  "+ "表中的时间段时间"+OTHER.getTime()+"----"+(OTHER.getTime()+4*60*60*1000));
		    	if(((OTHER.getTime()+4*60*60*1000)>THIS.getTime()) && (THIS.getTime()>OTHER.getTime())){
		    		return false;
		    	}else if((OTHER.getTime()<(THIS.getTime()+4*60*60*1000)) && ((THIS.getTime()+4*60*60*1000)<(OTHER.getTime()+4*60*60*1000))){
		    		 return false; 
		    	}
	      } 
    	  Statement=connect.prepareStatement("insert into schedule(studio_id,play_id,schedule_time,schedule_price,schedule_status) values(?,?,?,?,?)");
	      Statement.setInt(1,schedule.getStudio_id());
	      Statement.setInt(2,schedule.getPlay_id());
	      Statement.setString(3,schedule.getSchedule_time());
	      Statement.setFloat(4,Float.valueOf(schedule.getSchedule_price()));
	      Statement.setInt(5,schedule.getSchedule_status());
	      //批量生成票
	      if(1 == Statement.executeUpdate()){//插入成功
	    	  if(schedule.getSchedule_status()==1){
	    		  	String[][] str = get_schedule_list();
	    	  		schedule.setSchedule_id(Integer.valueOf(str[str.length-1][0]));
	    	  		ManageTicket_DB.add_ticket(schedule);
	    	  }
	    	  return true;
	      }
   	 }catch(SQLException e){
   		 System.out.print("添加演出计划错误！");
   	   	 return false;
      }
	return false;
	
	}
	//得到演出计划列表
	public static String[][] get_schedule_list() {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select schedule_id,studio_name,play_name,schedule_time,play_duration,schedule_price,schedule_status from schedule,play,studio where play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id order by schedule_id");
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
//	        	 	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(set.getTimestamp("schedule_time").getTime()+set.getInt(("play_duration"))*60*1000)));
	        	 	String flag = null;
	        	 	if(set.getInt("schedule_status")==0){
	        	 		flag=new String("N");
	        	 	}else if(set.getInt("schedule_status")==1){
	        	 		flag=new String("Y");
	        	 	}
	        	 	strlist.add(new String[]{String.valueOf(set.getInt("schedule_id")),set.getString("studio_name"),
            			                    set.getString("play_name"),set.getTimestamp("schedule_time").toString().substring(0,16),
            			                    new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(set.getTimestamp("schedule_time").getTime()+set.getInt(("play_duration"))*60*1000)),String.valueOf(set.getFloat("schedule_price")),flag});        	   
	         }
	         String[][] str = new String[strlist.size()][7];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<7 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取演出计划列表错误！！!");
	     }
		return null;
	}

	public static String[] get_schedule(int schedule_id) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select * FROM schedule WHERE schedule_id=? and schedule_status=1");
	         Statement.setInt(1,schedule_id);//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
          	    strlist.add(new String[]{String.valueOf(set.getInt("schedule_id")),String.valueOf(set.getInt("studio_id")),String.valueOf(set.getInt("play_id")),String.valueOf(set.getInt("schedule_time")),String.valueOf(set.getFloat(("schedule_price")))});        	   
	         }
	         String[] str = new String[5];
        	 for(int i=0 ; i<5 ; i++){
        		 str[i] = strlist.get(0)[i];
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取演出计划列表错误");
	     }
		return null;
	}
	public static boolean delete(int schedule_id) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
          }catch (Exception e) {
             System.out.print("加载MySQL驱动错误!");
         }
    	 boolean flag=false;
    	 try {
    		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	          PreparedStatement Statement=connect.prepareStatement("select schedule_status from schedule where schedule_id=?");
	          Statement.setInt(1,schedule_id);//替换问号
	          ResultSet set= Statement.executeQuery();
	          while (set.next()) {
	        	  if(set.getInt("schedule_status")==1){
	        		  flag= true;
	        	  }
	          }
	          if(flag){//已安排
	     		 if(ManageTicket_DB.delete_ticket(schedule_id)){
	     			  connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
		  	          Statement=connect.prepareStatement("delete from schedule where schedule_id=?");
		  	          Statement.setInt(1,schedule_id);//替换问号
		  	          if(1 == Statement.executeUpdate()){//删除成功
		  	        	  return true;
		  	          }
	     		 }else{
	  	        	  return false;
	  	          }
	          }else{//未安排
	        	  connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	  	          Statement=connect.prepareStatement("delete from schedule where schedule_id=?");
	  	          Statement.setInt(1,schedule_id);//替换问号
	  	          if(1 == Statement.executeUpdate()){//删除成功
	  	        	  return true;
	  	          }
	          }
	 		  
	 	  return false;
    	 }catch(SQLException e){
	    	  System.out.print("删除演出计划错误!!!");
	          e.printStackTrace();
	          return false;
      }
	}
	public static String[][] get_con_schedule_list() {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select schedule_id,studio_name,play_name,schedule_price from schedule,play,studio where play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id and schedule.schedule_status=1 order by schedule_id");
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 	strlist.add(new String[]{String.valueOf(set.getInt("schedule_id")),set.getString("studio_name"),
            			                    set.getString("play_name"),String.valueOf(set.getFloat("schedule_price"))});        	   
	         }
	         String[][] str = new String[strlist.size()][4];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<4 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取演出计划列表错误！！!");
	     }
		return null;
	}
	 public static boolean update(Schedule schedule) {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	         }catch (Exception e) {
	            System.out.print("加载MySQL驱动错误!");
	        }
	   	 
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

		  PreparedStatement Statement=connect.prepareStatement("update schedule set studio_id=?,play_id=?,schedule_time=?,schedule_price=?,schedule_status=? where schedule_id=?");
	      Statement.setInt(1,schedule.getStudio_id());
	      Statement.setInt(2,schedule.getPlay_id());
	      Statement.setString(3,schedule.getSchedule_time());
	      Statement.setFloat(4,Float.valueOf(schedule.getSchedule_price()));
	      Statement.setInt(5,schedule.getSchedule_status());
	      Statement.setInt(6,schedule.getSchedule_id());

	      
	      if(1 == Statement.executeUpdate()){
	    	  if(schedule.getSchedule_status()==1){
	    		  	String[][] str = get_schedule_list();
	    	  		schedule.setSchedule_id(Integer.valueOf(str[str.length-1][0]));
	    	  		ManageTicket_DB.add_ticket(schedule);
	    	  }
	    	  return true;
	      }
  	 }catch(SQLException e){
  		 System.out.print("添加演出计划错误！");
       }
  	 return false;
	}
	public static String[][] get_con_search_schedule_list(String search) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	 		 PreparedStatement Statement=connect.prepareStatement("select schedule_id,studio_name,play_name,schedule_price from schedule,play,studio WHERE play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id and schedule.schedule_status=1 and CONCAT(schedule_id,studio_name,play_name,schedule_price) LIKE ?");
	 		 Statement.setString(1,"%"+search+"%");//替换问号
		     ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 	strlist.add(new String[]{String.valueOf(set.getInt("schedule_id")),set.getString("studio_name"),
            			                    set.getString("play_name"),String.valueOf(set.getFloat("schedule_price"))});        	   
	         }
	         String[][] str = new String[strlist.size()][4];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<4 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取售票员搜索演出计划列表错误！！!");
	     }
		return null;
	}
	public static String[][] get_search_schedule_list(String search) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		PreparedStatement Statement;
	 		 if(search.equals("Y")){
	 			 search = new String("1");
	 			  Statement=connect.prepareStatement("select schedule_id,studio_name,play_name,schedule_time,play_duration,schedule_price,schedule_status from schedule,play,studio where play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id and CONCAT(schedule_status) LIKE ? order by schedule_id");
	 	 		 Statement.setString(1,"%"+search+"%");//替换问号
	 		 }else if(search.equals("N")){
	 			 search = new String("0");
	 			  Statement=connect.prepareStatement("select schedule_id,studio_name,play_name,schedule_time,play_duration,schedule_price,schedule_status from schedule,play,studio where play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id and CONCAT(schedule_status) LIKE ? order by schedule_id");
	 	 		 Statement.setString(1,"%"+search+"%");//替换问号
	 		 }else{
	 			  Statement=connect.prepareStatement("select schedule_id,studio_name,play_name,schedule_time,play_duration,schedule_price,schedule_status from schedule,play,studio where play.play_id=schedule.play_id and studio.studio_id=schedule.studio_id and CONCAT(schedule_id,studio_name,play_name,schedule_time,play_duration,schedule_price,schedule_status) LIKE ? order by schedule_id");
	 			 Statement.setString(1,"%"+search+"%");//替换问号
	 		 }
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(set.getTimestamp("schedule_time").getTime()+set.getInt(("play_duration"))*60*1000)));
	        	 	String flag = null;
	        	 	if(set.getInt("schedule_status")==0){
	        	 		flag=new String("N");
	        	 	}else if(set.getInt("schedule_status")==1){
	        	 		flag=new String("Y");
	        	 	}
	        	 	strlist.add(new String[]{String.valueOf(set.getInt("schedule_id")),set.getString("studio_name"),
            			                    set.getString("play_name"),set.getTimestamp("schedule_time").toString().substring(0,16),
            			                    new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(set.getTimestamp("schedule_time").getTime()+set.getInt(("play_duration"))*60*1000)),String.valueOf(set.getFloat("schedule_price")),flag});        	   
	         }
	 		 System.out.println("123");

	         String[][] str = new String[strlist.size()][7];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<7 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取经理演出计划列表错误！！!");
	     }
		return null;
	}
	public static String[] get_play() {
			ArrayList<String> strlist = new ArrayList<String>();
	    	try {
	            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	         }catch (Exception e) {
	            System.out.print("加载MySQL驱动错误!");
	        }
		   	 try {
		   		 //连接数据库
		 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
		         PreparedStatement Statement=connect.prepareStatement("select distinct play_name from schedule,play where schedule.play_id=play.play_id and schedule.schedule_status=1");
		         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
		         while (set.next()) {
		        	 strlist.add(new String(set.getString("play_name")));
		         }
		         String[] string = new String[strlist.size()];
		         for(int i=0 ; i<string.length ; i++){
		        	 string[i] = strlist.get(i);
		         }
		         return string;
		     }catch(SQLException e){
		   	  	System.out.print("获取剧目列表错误！！！");
		     }
			return null;
	}
}
