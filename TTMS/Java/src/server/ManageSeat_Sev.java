package server;

import Basic.Seat;
import Basic.Studio;
import db.ManageSeat_DB;

public class ManageSeat_Sev {
	//�õ��ݳ�������λ
	public static int[][] get_seat_state(Studio studio) {
		return ManageSeat_DB.get_seat_state(studio);
	}

	public static boolean update_seat(Studio studio, int[][] seat_state) {
		return ManageSeat_DB.update_seat(studio,seat_state);
		
	}

}
