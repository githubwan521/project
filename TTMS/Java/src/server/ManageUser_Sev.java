package server;

import Basic.User;
import db.ManageUser_DB;

//�����û�: ����UI����Ϣ���������ݲ�
public class ManageUser_Sev {
	//�û���¼
	public static int login(User user) {
		return ManageUser_DB.login(user);
	}
	//�����û�
	public static boolean add(User user) {
		return ManageUser_DB.add(user);
	}
	//ɾ���û�
	public static boolean delete(User user) {
		return ManageUser_DB.delete(user);
	}
	//��������
	public static boolean reset(int user_id) {
		return ManageUser_DB.reset(user_id);
	}
	//�޸��û���Ϣ
	public static boolean update(User user) {
		return ManageUser_DB.update(user);
	}
	//�޸��û�����
	public static boolean update_pw(User user) {
		return ManageUser_DB.update_pw(user);
	}
	//�õ��û��б�
	public static String[][] get_user_list(){
		return ManageUser_DB.get_user_list();
	}
	public static boolean update_status(User user){
		return ManageUser_DB.update_status(user);
	}
	public static int search_status(int user_id){
		return ManageUser_DB.search_status(user_id);
	}
	
	//�õ���ѯ�б�
	public static String[][] get_user_search(String search){
		return ManageUser_DB.get_user_search(search);
	}
	public static String[] get_user(){
		return ManageUser_DB.get_user();
	}
}
