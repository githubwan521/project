package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.jndi.toolkit.ctx.StringHeadTail;

import Basic.Default;
import Basic.Play;
import Basic.User;

public class ManagePlay_DB {
	//得到剧目列表
	public static String[][] get_play_list() {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		String language = null,type = null;
	         PreparedStatement Statement=connect.prepareStatement("select * from play");
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=4 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_type"));//替换问号
		         ResultSet tem= Statement.executeQuery();//执行已发送的预编译的sql
		         while (tem.next()) {
		        	  type = new String(tem.getString("dict_value"));
		         }
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=3 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_language"));//替换问号
	        	 tem= Statement.executeQuery();//执行已发送的预编译的sql
	        	 while (tem.next()) {
	        		  language = new String(tem.getString("dict_value"));
	        	 }
	        	 strlist.add(new String[]{String.valueOf(set.getInt("play_id")),type,language,set.getString("play_name"),String.valueOf(set.getInt("play_duration")),set.getString("play_introduction")});
	        
	         }
	         String[][] str = new String[strlist.size()][6];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<6 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取剧目列表错误！！！");
	     }
		return null;
	}

	public static boolean add(Play play) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
          }catch (Exception e) {
             System.out.print("加载MySQL驱动错误!");
         }
    	 
    	 try {
    		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	          PreparedStatement Statement=connect.prepareStatement("insert into play(play_type,play_language,play_name,play_introduction,play_duration) values(?,?,?,?,?)");

	          Statement.setInt(1,play.getPlay_type());//替换问号
	          Statement.setInt(2,play.getPlay_language());
	          Statement.setString(3,play.getPlay_name());
	          Statement.setString(4,play.getPlay_introduction());
	          Statement.setInt(5,play.getPlay_duration());
		 		 System.out.println(play.getPlay_type());

	          if(1 == Statement.executeUpdate()){//插入成功
	        	  return true;
	          }
    	 }catch(SQLException e){
    		 System.out.print("添加剧目错误!!!");
         }
    	 return false;
	}

	public static boolean delete(int play_id) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
          }catch (Exception e) {
             System.out.print("加载MySQL驱动错误!");
         }
    	 
    	 try {
    		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	          PreparedStatement Statement=connect.prepareStatement("DELETE FROM play WHERE play_id=?");
	          Statement.setInt(1,play_id);//替换问号
	          
	          if(1==Statement.executeUpdate()){//删除成功
	        	  return true;
	          }
	       return false;
    	 }catch(SQLException e){
	    	  System.out.print("删除剧目错误!!!");
	          e.printStackTrace();
	          return false;
      }
    }
	public static boolean update(Play play) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
        }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	try {
	   		//连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("update play set play_type=?,play_language=?,play_name=?,play_introduction=?,play_duration=? where play_id=?");
	        Statement.setInt(1,play.getPlay_type());
	        Statement.setInt(2,play.getPlay_language());
	        Statement.setString(3,play.getPlay_name());
	        Statement.setString(4,play.getPlay_introduction());
	        Statement.setInt(5,play.getPlay_duration());
	        Statement.setInt(6,play.getPlay_id());
	
	         if(1 == Statement.executeUpdate()){//修改成功
	       	  	return true;
	         }
   		}catch(SQLException e){
   			System.out.print("修改剧目数据错误！");
   		}
		return false;
	}

	public static int get_play_id(String play_name) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select play_id FROM play WHERE play_name=?");
	         Statement.setString(1,play_name);//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         int play_id = 0;
	         while (set.next()) {
	        	 play_id=Integer.valueOf(set.getInt("play_id"));
	         }
	         return play_id;
	     }catch(SQLException e){
	   	  	System.out.print("得到指定name的演出厅列表错误！！！");
	     }
		return 0;
	}

	public static String get_play_endtime(int play_id, String start_time) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	         PreparedStatement Statement=connect.prepareStatement("select play_duration from play where play_id=?");
	         Statement.setInt(1,play_id);//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	 		 int play_duration = 0;
	         while (set.next()) {
	        	 play_duration=set.getInt("play_duration");
	         }
	         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	         System.out.println("开始时间"+start_time);
	         Date enDate = null;
			try {
				enDate = format.parse(start_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	 		 String end_time;
	         end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(enDate.getTime()+play_duration*60*1000);        	   
//	         System.out.println("结束时间"+end_time);
	         return end_time;
	     }catch(SQLException e){
	   	  	System.out.print("获取演出计划列表错误！！!");
	     }
		return null;
	}

	public static String[][] get_search_play_list(String search) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 String language = null,type = null;
	         PreparedStatement Statement=connect.prepareStatement("select * from play WHERE CONCAT(play_id,play_type,play_language,play_name,play_duration) LIKE ?");
	         Statement.setString(1,"%"+search+"%");//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=4 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_type"));//替换问号
		         ResultSet tem= Statement.executeQuery();//执行已发送的预编译的sql
		         while (tem.next()) {
		        	  type = new String(tem.getString("dict_value"));
		         }
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=3 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_language"));//替换问号
	        	 tem= Statement.executeQuery();//执行已发送的预编译的sql
	        	 while (tem.next()) {
	        		  language = new String(tem.getString("dict_value"));
	        	 }
	        	 strlist.add(new String[]{String.valueOf(set.getInt("play_id")),type,language,set.getString("play_name"),String.valueOf(set.getInt("play_duration")),set.getString("play_introduction")});
	        
	         }
	         String[][] str = new String[strlist.size()][6];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<6 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取搜索剧目列表错误！！！");
	     }
		return null;
	}
	
}
