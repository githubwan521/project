package server;

import Basic.Studio;
import db.ManageStudio_DB;

public class ManageStudio_Sev {
	//得到演出厅列表
	public static String[][] get_studio_list() {
		return ManageStudio_DB.get_studio_list();
	}
	//得到搜索的演出厅列表
	public static String[][] get_studio_search(String search) {
		return ManageStudio_DB.get_studio_search(search);
	}
	//添加演出厅
	public static boolean add(Studio studio) {
		return ManageStudio_DB.add(studio);
	}
	//删除演出厅
	public static boolean delete(Studio studio) {
		return ManageStudio_DB.delete(studio);
	}
	//得到演出厅
	public static String[] get_studio(int studio_id) {
        return  ManageStudio_DB.get_studio(studio_id);
	}
	public static int get_studio_id(String studio_name) {
        return  ManageStudio_DB.get_studio_id(studio_name);
	}
}
