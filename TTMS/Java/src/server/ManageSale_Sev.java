package server;

import Basic.Sale;
import db.ManageSale_DB;

public class ManageSale_Sev {
	//增加一条订单
	public static boolean add(Sale sale, int[] ticket_id) {
		return ManageSale_DB.add(sale,ticket_id);
	}
	//查找订单记录
	public static String[][] get_sale_list() {
		return ManageSale_DB.get_sale_list();
	}
	//得到用户订单列表
	public static String[][] get_user_sale_list(int user_id) {
		return ManageSale_DB.get_user_sale_list(user_id);
	}
	//得到用户搜索订单列表
	public static String[][] get_search_user_sale_list(int user_id,String search) {
		return ManageSale_DB.get_search_user_sale_list(user_id,search);
	}
//得到详细订单记录
	public static String[][] get_sale_item_list(int sale_id) {
		return ManageSale_DB.get_sale_item_list(sale_id);	
	}
	
}
