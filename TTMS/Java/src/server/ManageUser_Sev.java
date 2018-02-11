package server;

import Basic.User;
import db.ManageUser_DB;

//管理用户: 传递UI的信息，调用数据层
public class ManageUser_Sev {
	//用户登录
	public static int login(User user) {
		return ManageUser_DB.login(user);
	}
	//增加用户
	public static boolean add(User user) {
		return ManageUser_DB.add(user);
	}
	//删除用户
	public static boolean delete(User user) {
		return ManageUser_DB.delete(user);
	}
	//重置密码
	public static boolean reset(int user_id) {
		return ManageUser_DB.reset(user_id);
	}
	//修改用户信息
	public static boolean update(User user) {
		return ManageUser_DB.update(user);
	}
	//修改用户密码
	public static boolean update_pw(User user) {
		return ManageUser_DB.update_pw(user);
	}
	//得到用户列表
	public static String[][] get_user_list(){
		return ManageUser_DB.get_user_list();
	}
	public static boolean update_status(User user){
		return ManageUser_DB.update_status(user);
	}
	public static int search_status(int user_id){
		return ManageUser_DB.search_status(user_id);
	}
	
	//得到查询列表
	public static String[][] get_user_search(String search){
		return ManageUser_DB.get_user_search(search);
	}
	public static String[] get_user(){
		return ManageUser_DB.get_user();
	}
}
