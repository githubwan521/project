package server;


import Basic.User;
import db.ManageAnalysis_DB;

public class ManageAnalysis_Sev {

	//ָ����Ŀ�����ۼ�¼
	public static String[][] analysisPlayitem_sev(User user,String play_name,String start_time,String end_time){	
		if(user.getIdentity().equals("����"))	{
			return ManageAnalysis_DB.analysisPlayitem( ManageAnalysis_DB.returnPlayID(play_name),start_time,end_time);
		}else{
			return ManageAnalysis_DB.analysisSaler_playitem(user.getId(),ManageAnalysis_DB.returnPlayID(play_name), start_time, end_time);
		}
	}
	//���о�Ŀ�����ۼ�¼
	public static String[][] analysisAllplay_sev(User user ,String start_time,String end_time){
		if (user.getIdentity().equals("����")){
			return ManageAnalysis_DB.analysisAllPlay(start_time, end_time);		
		}else{
			return ManageAnalysis_DB.analysisSale_play(user.getId(), start_time, end_time);
		}
	}
	
	// ��ƱԱ�����ۼ�¼
	public static String[][] analysisAllSaler_sev(User user ,String start_time,String end_time){
		if (user.getIdentity().equals("����")){
			return ManageAnalysis_DB.analysisAllSaler(start_time,end_time);
		}else {
			return ManageAnalysis_DB.analysisSaler( user.getId(),start_time,end_time);
		}
		
	}
}