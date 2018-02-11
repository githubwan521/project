package server;

import Basic.Schedule;
import db.ManageSchedule_DB;

public class ManageSchedule_Sev {

	public static String[][] get_schedule_list() {
		return ManageSchedule_DB.get_schedule_list();
	}
	public static String[][] get_search_schedule_list(String search) {
		return ManageSchedule_DB.get_search_schedule_list(search);
	}
	public static String[][] get_con_schedule_list() {
		return ManageSchedule_DB.get_con_schedule_list();
	}
	public static String[][] get_con_search_schedule_list(String search) {
		return ManageSchedule_DB.get_con_search_schedule_list(search);
	}
	public static String[] get_schedule(int schedule_id) {
		return ManageSchedule_DB.get_schedule(schedule_id);
	}

	public static boolean add(Schedule schedule) {
		return ManageSchedule_DB.add(schedule);
	}

	public static boolean delete(int schedule_id) {
		return ManageSchedule_DB.delete(schedule_id);
	}

	public static boolean update(Schedule schedule) {
		return ManageSchedule_DB.update(schedule);
	}
	public static String[] get_play() {
		// TODO 自动生成的方法存根
		return ManageSchedule_DB.get_play();
	}


}
