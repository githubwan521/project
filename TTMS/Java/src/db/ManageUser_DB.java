package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import com.sun.jndi.toolkit.ctx.StringHeadTail;

import Basic.Default;
import Basic.User;

public class ManageUser_DB{
    public static boolean add(User user) {
    	 try {
             Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
          }catch (Exception e) {
             System.out.print("����MySQL��������!");
         }
    	 
    	 try {
    		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

 		      PreparedStatement Statement=connect.prepareStatement("INSERT INTO user(user_identity,user_name,user_pass,user_tel_num,user_status) VALUES(?,?,?,?,0)");
	          Statement.setString(1,user.getIdentity());//�滻�ʺ�
	          Statement.setString(2,user.getName());
	          Statement.setString(3,user.getPass());
	          Statement.setString(4,user.getTel());

	          if(1 == Statement.executeUpdate()){//����ɹ�
	        	  return true;
	          }

    	 }catch(SQLException e){
    		 System.out.print("1��ȡ���ݴ���");
         }
    	 return false;
      }
    public static boolean delete(User user) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
          }catch (Exception e) {
             System.out.print("����MySQL��������!");
         }
    	 
    	 try {
    		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	        PreparedStatement Statement=connect.prepareStatement("DELETE FROM user WHERE user_id=?");
	        Statement.setInt(1,user.getId());//�滻�ʺ�
	         if(1 == Statement.executeUpdate()){//ɾ���ɹ�
	        	 return true;
	          }else{
	        	 return false;
	          }
	 
    	 }catch(SQLException e){
	    	  System.out.print("ɾ���û�����!!!");
	          e.printStackTrace();
	          return false;
      }
    }
    public static String[][] get_user_list(){
    	ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		
	         //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

		     PreparedStatement Statement=connect.prepareStatement("select * FROM user");
		     ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
		     while (set.next()) {
	        	 strlist.add(new String[]{String.valueOf(set.getInt("user_id")),set.getString("user_identity"),set.getString("user_name"),set.getString("user_tel_num"),set.getTimestamp("user_time").toString()});
			 }

	         String[][] str = new String[strlist.size()][5];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<5 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }

	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("3��ȡ���ݴ���");
	     }
		return null;
	 }
	public static boolean update(User user) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
        }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	try {
	   		//�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("UPDATE user SET user_identity = ? , user_name=? ,user_tel_num=? WHERE user_id = ?");
	         Statement.setString(1,user.getIdentity());
	         Statement.setString(2,user.getName());
	         Statement.setString(3,user.getTel());//�滻�ʺ�
	         Statement.setInt(4,user.getId());
	        
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	         if(1 == Statement.executeUpdate()){
	        	  return true;
	          }else{
	        	  return false;
	          }
   		}catch(SQLException e){
   			System.out.print("�޸��û���Ϣ����");
   		}
		return false;
	}
	public static int login(User user) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
          }catch (Exception e) {
             System.out.print("����MySQL��������!");
         }
    	 
    	 try {
    		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	          PreparedStatement Statement=connect.prepareStatement("select user_id,user_pass FROM user WHERE (user_identity=? AND user_name=?)");
	          Statement.setString(1,user.getIdentity());//�滻�ʺ�
	          Statement.setString(2,user.getName());

	          ResultSet set=Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	          while(set.next()){
	        	  if(set.getString("user_pass").equals(user.getPass())){//��ѯ�ɹ�
	        		  return set.getInt("user_id");
	          		} 
	          }
    	 }catch(SQLException e){
	    	  System.out.print("5��ȡ���ݴ���");
	          e.printStackTrace();
      }
		return 0;
    }
	public static String[][] get_user_search(String search) {
		ArrayList<String[]> strlist = new ArrayList<String[]>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("SELECT * FROM user WHERE CONCAT(user_id,user_identity,user_name,user_pass,user_tel_num,user_time) LIKE ?");
	         Statement.setString(1,"%"+search+"%");//�滻�ʺ�
	        
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         
	         while (set.next()) {
//	               System.out.println(set.getInt("id")+set.getInt("identity") + set.getString("name")+set.getString("pass") + set.getTimestamp("time"));
	        	 	// ����ѯ����������ӵ�list�У�����userNameΪ���ݿ��е��ֶ�����
	            
          	   strlist.add(new String[]{String.valueOf(set.getInt("user_id")),set.getString("user_identity"),set.getString("user_name"),set.getString("user_tel_num"),set.getTimestamp("user_time").toString()});
	         }
	         String[][] str = new String[strlist.size()][5];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 for(int j=0 ; j<5 ; j++){
	        		 str[i][j] = strlist.get(i)[j];
	        	 }
	         }
	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("6��ȡ���ݴ���");
	     }
		return null;
	}
	public static boolean reset(int user_id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
        }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	try {
	   		//�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("UPDATE user SET user_pass='123456' WHERE user_id = ?");
	         Statement.setInt(1,user_id);
	        
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	         if(1 == Statement.executeUpdate()){//ɾ���ɹ�
	        	  return true;
	          }else{
	        	  return false;
	          }
   		}catch(SQLException e){
   			System.out.print("�޸��û���Ϣ����");
   		}
		return false;
	}
	public static String[] get_user(){
		ArrayList<String> strlist = new ArrayList<String>();
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
         }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	 try {
	   		
	         //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

		     PreparedStatement Statement=connect.prepareStatement("select user_name from user where user_identity='��ƱԱ'");
		     ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
		     while (set.next()) {
	        	 strlist.add(new String(set.getString("user_name")));
			 }

	         String[] str = new String[strlist.size()];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 str[i] = strlist.get(i);
	         }

	         return str;
	     }catch(SQLException e){
	   	  	System.out.print("3��ȡ���ݴ���");
	     }
		return null;
	}
	public static boolean update_pw(User user) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
        }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	try {
	   		//�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("UPDATE user SET user_pass=? ,user_tel_num=? WHERE user_id = ?");
	         Statement.setString(1,user.getPass());
	         Statement.setString(2,user.getTel());//�滻�ʺ�
	         Statement.setInt(3,user.getId());
	        
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	         if(1 == Statement.executeUpdate()){
	        	  return true;
	          }else{
	        	  return false;
	          }
   		}catch(SQLException e){
   			System.out.print("�޸��û���Ϣ����");
   		}
		return false;
	}
	public static boolean update_status(User user) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
        }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	try {
	   		//�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("UPDATE user SET user_status=? WHERE user_id = ?");
	         Statement.setInt(1,user.getStatus());
	         Statement.setInt(2,user.getId());
	        
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	         if(1 == Statement.executeUpdate()){
	        	  return true;
	          }else{
	        	  return false;
	          }
   		}catch(SQLException e){
   			System.out.print("�޸��û���½״̬����");
   		}
		return false;
	}
	public static int search_status(int user_id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
        }catch (Exception e) {
            System.out.print("����MySQL��������!");
        }
	   	try {
	   		//�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
 
	   		PreparedStatement Statement=connect.prepareStatement("select user_status from user where user_id=?");
	         Statement.setInt(1,user_id);
	         Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
		     int status=0;
	         while (set.next()) {
	        	 status=set.getInt("user_status");
			 }
	        return status;
   		}catch(SQLException e){
   			System.out.print("�޸��û���½״̬����");
   		}
		return 0;
	}
}