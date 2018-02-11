package server;

import Basic.Sale;
import db.ManageSale_DB;

public class ManageSale_Sev {
	//����һ������
	public static boolean add(Sale sale, int[] ticket_id) {
		return ManageSale_DB.add(sale,ticket_id);
	}
	//���Ҷ�����¼
	public static String[][] get_sale_list() {
		return ManageSale_DB.get_sale_list();
	}
	//�õ��û������б�
	public static String[][] get_user_sale_list(int user_id) {
		return ManageSale_DB.get_user_sale_list(user_id);
	}
	//�õ��û����������б�
	public static String[][] get_search_user_sale_list(int user_id,String search) {
		return ManageSale_DB.get_search_user_sale_list(user_id,search);
	}
//�õ���ϸ������¼
	public static String[][] get_sale_item_list(int sale_id) {
		return ManageSale_DB.get_sale_item_list(sale_id);	
	}
	
}
