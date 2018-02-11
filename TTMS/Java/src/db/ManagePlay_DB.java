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
	//�õ���Ŀ�б�
	public static String[][] get_play_list() {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		String language = null,type = null;
	         PreparedStatement Statement=connect.prepareStatement("select * from play");
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         while (set.next()) {
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=4 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_type"));//�滻�ʺ�
		         ResultSet tem= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
		         while (tem.next()) {
		        	  type = new String(tem.getString("dict_value"));
		         }
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=3 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_language"));//�滻�ʺ�
	        	 tem= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
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
	   	  	System.out.print("��ȡ��Ŀ�б���󣡣���");
	     }
		return null;
	}

	public static boolean add(Play play) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
          }catch (Exception e) {
             System.out.print("����MySQL��������!");
         }
    	 
    	 try {
    		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	          PreparedStatement Statement=connect.prepareStatement("insert into play(play_type,play_language,play_name,play_introduction,play_duration) values(?,?,?,?,?)");

	          Statement.setInt(1,play.getPlay_type());//�滻�ʺ�
	          Statement.setInt(2,play.getPlay_language());
	          Statement.setString(3,play.getPlay_name());
	          Statement.setString(4,play.getPlay_introduction());
	          Statement.setInt(5,play.getPlay_duration());
		 		 System.out.println(play.getPlay_type());

	          if(1 == Statement.executeUpdate()){//����ɹ�
	        	  return true;
	          }
    	 }catch(SQLException e){
    		 System.out.print("��Ӿ�Ŀ����!!!");
         }
    	 return false;
	}

	public static boolean delete(int play_id) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
          }catch (Exception e) {
             System.out.print("����MySQL��������!");
         }
    	 
    	 try {
    		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	          PreparedStatement Statement=connect.prepareStatement("DELETE FROM play WHERE play_id=?");
	          Statement.setInt(1,play_id);//�滻�ʺ�
	          
	          if(1==Statement.executeUpdate()){//ɾ���ɹ�
	        	  return true;
	          }
	       return false;
    	 }catch(SQLException e){
	    	  System.out.print("ɾ����Ŀ����!!!");
	          e.printStackTrace();
	          return false;
      }
    }
	public static boolean update(Play play) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
        }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	try {
	   		//�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("update play set play_type=?,play_language=?,play_name=?,play_introduction=?,play_duration=? where play_id=?");
	        Statement.setInt(1,play.getPlay_type());
	        Statement.setInt(2,play.getPlay_language());
	        Statement.setString(3,play.getPlay_name());
	        Statement.setString(4,play.getPlay_introduction());
	        Statement.setInt(5,play.getPlay_duration());
	        Statement.setInt(6,play.getPlay_id());
	
	         if(1 == Statement.executeUpdate()){//�޸ĳɹ�
	       	  	return true;
	         }
   		}catch(SQLException e){
   			System.out.print("�޸ľ�Ŀ���ݴ���");
   		}
		return false;
	}

	public static int get_play_id(String play_name) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select play_id FROM play WHERE play_name=?");
	         Statement.setString(1,play_name);//�滻�ʺ�
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         int play_id = 0;
	         while (set.next()) {
	        	 play_id=Integer.valueOf(set.getInt("play_id"));
	         }
	         return play_id;
	     }catch(SQLException e){
	   	  	System.out.print("�õ�ָ��name���ݳ����б���󣡣���");
	     }
		return 0;
	}

	public static String get_play_endtime(int play_id, String start_time) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	         PreparedStatement Statement=connect.prepareStatement("select play_duration from play where play_id=?");
	         Statement.setInt(1,play_id);//�滻�ʺ�
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	 		 int play_duration = 0;
	         while (set.next()) {
	        	 play_duration=set.getInt("play_duration");
	         }
	         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	         System.out.println("��ʼʱ��"+start_time);
	         Date enDate = null;
			try {
				enDate = format.parse(start_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	 		 String end_time;
	         end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(enDate.getTime()+play_duration*60*1000);        	   
//	         System.out.println("����ʱ��"+end_time);
	         return end_time;
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�ݳ��ƻ��б���󣡣�!");
	     }
		return null;
	}

	public static String[][] get_search_play_list(String search) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 String language = null,type = null;
	         PreparedStatement Statement=connect.prepareStatement("select * from play WHERE CONCAT(play_id,play_type,play_language,play_name,play_duration) LIKE ?");
	         Statement.setString(1,"%"+search+"%");//�滻�ʺ�
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         while (set.next()) {
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=4 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_type"));//�滻�ʺ�
		         ResultSet tem= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
		         while (tem.next()) {
		        	  type = new String(tem.getString("dict_value"));
		         }
	        	 Statement = connect.prepareStatement("select dict_value from data_dict where dict_parent_id=3 and dict_index=?");
		         Statement.setInt(1,set.getInt("play_language"));//�滻�ʺ�
	        	 tem= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
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
	   	  	System.out.print("��ȡ������Ŀ�б���󣡣���");
	     }
		return null;
	}
	
}
