package server;

import Basic.Schedule;
import db.ManageOrder_DB;

public class ManageOrder_Sev {

	public static String[][] get_order_list(Schedule schedule, int[] ticket) {
		// TODO 自动生成的方法存根
		return ManageOrder_DB.getorder_list( schedule,  ticket);
	}
	
}
