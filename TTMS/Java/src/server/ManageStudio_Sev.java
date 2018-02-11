package server;

import Basic.Studio;
import db.ManageStudio_DB;

public class ManageStudio_Sev {
	//�õ��ݳ����б�
	public static String[][] get_studio_list() {
		return ManageStudio_DB.get_studio_list();
	}
	//�õ��������ݳ����б�
	public static String[][] get_studio_search(String search) {
		return ManageStudio_DB.get_studio_search(search);
	}
	//����ݳ���
	public static boolean add(Studio studio) {
		return ManageStudio_DB.add(studio);
	}
	//ɾ���ݳ���
	public static boolean delete(Studio studio) {
		return ManageStudio_DB.delete(studio);
	}
	//�õ��ݳ���
	public static String[] get_studio(int studio_id) {
        return  ManageStudio_DB.get_studio(studio_id);
	}
	public static int get_studio_id(String studio_name) {
        return  ManageStudio_DB.get_studio_id(studio_name);
	}
}
