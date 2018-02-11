package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Basic.Default;
import Basic.Seat;
import Basic.Studio;

public class ManageStudio_DB {
	public static boolean add(Studio studio) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
          }catch (Exception e) {
             System.out.print("加载MySQL驱动错误!");
         }
    	 
    	 try {
    		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	          PreparedStatement Statement=connect.prepareStatement("INSERT INTO studio(studio_name,studio_row_count,studio_col_count,studio_introduction) VALUES(?,?,?,?)");
	          Statement.setString(1,studio.getName());//替换问号
	          Statement.setInt(2,studio.getRow());
	          Statement.setInt(3,studio.getCol());
	          Statement.setString(4,studio.getIntroduction());
	          if(1 == Statement.executeUpdate()){//插入成功
	        	  String[][] str = get_studio_list();
	        	  studio.setId(Integer.valueOf(str[str.length-1][0]));
	        	  ManageSeat_DB.add_seat(studio);
	        	  return true;
	          }
    	 }catch(SQLException e){
    		 System.out.print("添加演出厅错误!!!");
         }
    	 return false;
     }


	public static boolean delete(Studio studio) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
          }catch (Exception e) {
             System.out.print("加载MySQL驱动错误!");
         }
    	 
    	 try {
    		 //先删除座位
    		 if(ManageSeat_DB.delete_seat(studio)){
		 		  Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
		          PreparedStatement Statement=connect.prepareStatement("DELETE FROM studio WHERE studio_id=?");
		          Statement.setInt(1,studio.getId());//替换问号
		          if(1 == Statement.executeUpdate()){//删除成功
		        	  return true;
		          }
    		 }
    		 return false;
    	 }catch(SQLException e){
	    	  System.out.print("删除演出厅错误!!!");
	          e.printStackTrace();
	          return false;
    	 }
	}    	 
	public static String[][] get_studio_list() {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select * FROM studio");
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 	// 将查询出的内容添加到list中，其中userName为数据库中的字段名称
            	   strlist.add(new String[]{String.valueOf(set.getInt("studio_id")),set.getString("studio_name"),String.valueOf(set.getInt("studio_row_count")),String.valueOf(set.getInt("studio_col_count")),set.getString("studio_introduction")});
	         }
	         String[][] str = new String[strlist.size()][5];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<5 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("获取演出厅列表错误！！！");
	     }
		return null;
	}

	public static String[][] get_studio_search(String search) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("SELECT * FROM studio WHERE CONCAT(studio_id,studio_name,studio_row_count,studio_col_count) LIKE ?");
	         Statement.setString(1,"%"+search+"%");//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
          	   strlist.add(new String[]{String.valueOf(set.getInt("studio_id")),set.getString("studio_name"),String.valueOf(set.getInt("studio_row_count")),String.valueOf(set.getInt("studio_col_count"))});
	         }
	         String[][] str = new String[strlist.size()][4];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<4 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到搜索演出厅列表错误！！！");
	     }
	   	 return null;	
		}
	public static String[] get_studio(int studio_id) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select *FROM studio WHERE studio_id=?");
	         Statement.setInt(1,studio_id);//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
            	   strlist.add(new String[]{String.valueOf(set.getInt("studio_id")),set.getString("studio_name"),String.valueOf(set.getInt("studio_row_count")),String.valueOf(set.getInt("studio_col_count")),set.getString("studio_introduction")});
	         }
	         String[] str = new String[5];
        	 for(int i=0 ; i<5 ; i++){
        		 str[i] = strlist.get(0)[i];
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("得到指定id的演出厅列表错误！！！");
	     }
		return null;
	}

	public static int get_studio_id(String studio_name) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select studio_id FROM studio WHERE studio_name=?");
	         Statement.setString(1,studio_name);//替换问号
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         int studio_id = 0;
	         while (set.next()) {
	        	 studio_id=Integer.valueOf(set.getInt("studio_id"));
	         }
	         return studio_id;
	     }catch(SQLException e){
	   	  	System.out.print("得到指定name的演出厅列表错误！！！");
	     }
		return 0;
	}

}
