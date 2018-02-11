package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Basic.Default;
import Basic.Seat;
import Basic.Studio;
import jdk.nashorn.internal.objects.annotations.Where;

public class ManageSeat_DB {
	//批量添加座位
	public static boolean add_seat(Studio studio) {
		 try {
             Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
          }catch (Exception e) {
             System.out.print("加载MySQL驱动错误!");
         }
    	 try {
    		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

    		 for(int i=1 ; i<=9 ; i++){
    			 for(int j=1 ; j<=14 ; j++){	 
    				PreparedStatement Statement=connect.prepareStatement("INSERT INTO seat(seat_row,seat_column,seat_status,studio_id) VALUES(?,?,?,?)");
    			 	Statement.setInt(1,i);
    			 	Statement.setInt(2,j);
    			 	if(i<=studio.getRow() && j<=studio.getCol()){
    			 		Statement.setInt(3,0);
    			 	}else{
    			 		Statement.setInt(3,1);
    			 	}
    			 	Statement.setInt(4,studio.getId());	 
    			 	Statement.executeUpdate();
    			 }
    		 }
    	
	         return true;
    	 }catch(SQLException e){
    		 System.out.print("1获取数据错误！");
         }
    	 return false;	
    }
	//更新座位    -------未解决----------------同时更新票
	public static boolean update_seat(Studio studio, int[][] seat_state) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
        }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	try {
	   		//连接数据库
	   		boolean flag=true,QJ=true;
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");
	 		 int[][][] seat_start_status = new int[10][15][2];
	   		 PreparedStatement Statement=connect.prepareStatement("select * from seat where studio_id=?");
	         Statement.setInt(1,studio.getId());
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         while (set.next()) {
	        	 seat_start_status[set.getInt("seat_row")][set.getInt("seat_column")][0] = set.getInt("seat_status");
	        	 seat_start_status[set.getInt("seat_row")][set.getInt("seat_column")][1] = set.getInt("seat_id");
	         }
	 		 //先查询该座位是否有票然后再改票的状态
	   		 //更新座位
	   		 for(int i=1 ; i<=9 ; i++){
    			 for(int j=1 ; j<=14 ; j++){
    				 //更新座位与票
    				 flag=true;
    				 System.out.println(seat_start_status[i][j][0]+" "+seat_state[i][j] +" "+seat_start_status[i][j][1]);
    				 
    				 if(seat_start_status[i][j][0]>0 && seat_state[i][j]==0){
    					 Statement=connect.prepareStatement("UPDATE seat SET seat_status=0  WHERE seat_id=?");
    			   		 Statement.setInt(1,seat_start_status[i][j][1]);
    			         Statement.executeUpdate();
    			         Statement=connect.prepareStatement("UPDATE ticket SET ticket_status=0  WHERE seat_id=?");
    			   		 Statement.setInt(1,seat_start_status[i][j][1]);
    			         Statement.executeUpdate();
    				 }else if(seat_start_status[i][j][0]==0 && seat_state[i][j]!=0){
    					 //若有票没被使用
    					 Statement=connect.prepareStatement("select ticket_status from ticket where seat_id=?");
    			   		 Statement.setInt(1,seat_start_status[i][j][1]);
    			   		 set=Statement.executeQuery();
    					 while(set.next()){
    						 if(set.getInt("ticket_status")==1 || set.getInt("ticket_status")==2){
    	    			   		 flag=false;
    	    			   		 QJ=false;
    						 }
    					 } 
    					 if(flag){
    						 Statement=connect.prepareStatement("UPDATE ticket SET ticket_status=3  WHERE seat_id=?");
        			   		 Statement.setInt(1,seat_start_status[i][j][1]);
        			         Statement.executeUpdate();
        			         Statement=connect.prepareStatement("UPDATE seat SET seat_status=?  WHERE seat_id=?");
        			   		 Statement.setInt(1,seat_state[i][j]);
        			   		 Statement.setInt(2,seat_start_status[i][j][1]);
        			   		 Statement.executeUpdate();
    					 }
    			     }else if(seat_start_status[i][j][0]!=seat_state[i][j]){
    			    	 Statement=connect.prepareStatement("UPDATE seat SET seat_status=?  WHERE seat_id=?");
    			   		 Statement.setInt(1,seat_state[i][j]);
    			   		 Statement.setInt(2,seat_start_status[i][j][1]);
    			         Statement.executeUpdate();
					 }
    			 }
	   		 }
	   		 
	   		 //在演出厅更新座位
	   		 Statement=connect.prepareStatement("UPDATE studio SET studio_row_count=?,studio_col_count=?  WHERE studio_id=?");
	         Statement.setInt(1,studio.getRow());
	         Statement.setInt(2,studio.getCol());
	   		 Statement.setInt(3,studio.getId());
	         Statement.executeUpdate();

	       return QJ;
   		}catch(SQLException e){
   			System.out.print("更新座位失败");
   		}
		return false;
	}
	//删除座位
	public static boolean delete_seat(Studio studio) {
		try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
        }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	try {
	   		//连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	   		PreparedStatement Statement=connect.prepareStatement("DELETE FROM seat WHERE studio_id=?");
        	Statement.setInt(1,studio.getId());
        	Statement.executeUpdate();
	     
        	return true;
	          
   		}catch(SQLException e){
   			System.out.print("4获取数据错误！");
   		}
		return false;	
		}
	//得到演出厅的座位列表
	public static int[][] get_seat_state(Studio studio) {
    	try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
         }catch (Exception e) {
            System.out.print("加载MySQL驱动错误!");
        }
	   	 try {
	   		 //连接数据库
	 		 Connection connect = DriverManager.getConnection(Default.Get_Connection_Information(),"root","765885195");

	         PreparedStatement Statement=connect.prepareStatement("select * FROM seat WHERE studio_id=?");
	         Statement.setInt(1,studio.getId());
	         ResultSet set= Statement.executeQuery();//执行已发送的预编译的sql
	         int[][] seat_state = new int[10][15];
	         while (set.next()) {
	        	 seat_state[set.getInt("seat_row")][set.getInt("seat_column")] = set.getInt("seat_status");
	         }
	        return seat_state;
	      
	   	 }catch(SQLException e){
			System.out.print("4获取数据错误！");
	   	 }
	   	 return null;
	}
}
