package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Basic.Default;

public class ManageDict {
	//���ݸ�id�õ���ֵ�б�
	public static String[] get_value_list(int root) {
		 ArrayList<String> strlist = new ArrayList<String>();
		 try {
           Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
           System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("SELECT dict_value FROM data_dict WHERE dict_parent_id=? order by dict_index");
	         Statement.setInt(1,root);
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         while (set.next()) {
	        	 strlist.add(set.getString("dict_value"));
	         }
	         String[] str =  strlist.toArray(new String[strlist.size()]);
	         return str;
	         
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
	     }
		return null;
	}
	//�ҵ�ͬһ��id��Ӧ���Ӽ�¼id
	public static int[] get_index_list(int root) {
		 ArrayList<Integer> strlist = new ArrayList<Integer>();
		 try {
           Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
           System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("SELECT dict_id FROM data_dict WHERE dict_parent_id=? order by dict_id");

	         Statement.setInt(1,root);

	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         while (set.next()) {
	        	 strlist.add(new Integer(set.getInt("dict_id")));
	         }
	         int[] list =  new int[strlist.size()];
	         for(int i=0 ; i<strlist.size() ; i++){
	        	 list[i]=strlist.get(i);
	         }

	         return list;
	         
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
		   }
			return null;
		}
	//�õ�������¼��ֵ
	public static String get_value(int dict_id) {
		 try {
          Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
          System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("SELECT dict_value FROM data_dict WHERE dict_id=?");
	         Statement.setInt(1,dict_id);
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         String string = null;
	         while (set.next()) {
	        	 string = set.getString("dict_value");
	         }
	        
	         return string;
	         
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
		     }
			return null;
		}
	//������name���Ҹ�id
	public static int get_fid(String name) {
		 try {
          Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
          System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select dict_parent_id from data_dict where dict_value=?");
	         Statement.setString(1,name);
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         int parent_id=0;
	         while (set.next()) {
	        	 parent_id=set.getInt("dict_parent_id");
	         }
	         return parent_id;
	         
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
	     }
		return 0;
	}

	public static boolean add(String name,int f_id){
		try {
			Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
          System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
			 int num = get_value_list(f_id).length;
			 if(f_id!=1){
				 num++;
			 }
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement(" insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (?,?,?,?)");
	         Statement.setInt(1,f_id);
	         Statement.setInt(2,num);
	         Statement.setString(3,name);
	         Statement.setString(4,name);
	         
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	         return true;
	     }catch(SQLException e){
		   	  	System.out.print("��ȡ�����б����");
		   	  	return false;
		 }
	}
	//�õ�������¼��id
	public static int get_id(String name) {
		 try {
	      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
	      System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	
	         PreparedStatement Statement=connect.prepareStatement("SELECT dict_id FROM data_dict WHERE dict_value=?");
	         Statement.setString(1,name);
	         ResultSet set= Statement.executeQuery();//ִ���ѷ��͵�Ԥ�����sql
	         int id=0;
	         while (set.next()) {
	        	 id = set.getInt("dict_id");
	         }
	        
	         return id;
	         
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
		     }
		return 0;
		}
	public static boolean update(String string,String name) {
		try {
	      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
	      System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	
	         PreparedStatement Statement=connect.prepareStatement("update data_dict set dict_value = ? where dict_value=?");
	         Statement.setString(1,name);
	         Statement.setString(2,string);
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	        
	         return true;
	         
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
	   		return false; 
	     }
	
	}
	public static boolean delete(String name) {
		try {
	      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		 }catch (Exception e) {
	      System.out.print("����MySQL��������!");
		 }
		 try {
	   		 //�������ݿ�
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	
	         PreparedStatement Statement=connect.prepareStatement("delete from data_dict where dict_value=?");
	         Statement.setString(1,name);
	         Statement.executeUpdate();//ִ���ѷ��͵�Ԥ�����sql
	         return true;
	     }catch(SQLException e){
	   	  	System.out.print("��ȡ�����б����");
	   	  	return false;
		 }
	}
}
