package server;

import Basic.Play;
import db.ManagePlay_DB;

public class ManagePlay_Sev {
	
	public static String[][] get_play_list() {//得到剧目列表
		return ManagePlay_DB.get_play_list();
	}
	public static String[][] get_search_play_list(String search) {//得到剧目列表
		return ManagePlay_DB.get_search_play_list(search);
	}
	public static boolean add(Play play) {
		return ManagePlay_DB.add(play);
	}

	public static boolean delete(int play_id) {
		return ManagePlay_DB.delete(play_id);
	}
	public static boolean update(Play play) {
		return ManagePlay_DB.update(play);
	}
	public static int get_play_id(String play_name) {
		return ManagePlay_DB.get_play_id(play_name);
	}
	public static String get_play_endtime(int play_id,String start_time) {
		return ManagePlay_DB.get_play_endtime(play_id,start_time);
	}
}
