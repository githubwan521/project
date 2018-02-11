package account;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.print.attribute.standard.RequestingUserName;

import constant.Constant;

public class Account {//�˻���
	private String id,password;//������16λ֮��
	private static DataOutputStream out;
	private static DataInputStream in;
	
	public Account(String id,String password){
		this.id=id;
		this.password = password;
	}
	public static Boolean login(String id,String password) {//��½
		try {
			in = new DataInputStream(new FileInputStream(Constant.getFilePath()));
			 while(in.available() > 0){
	            String username = in.readUTF();
	            if(username.equals(id)){
	            	String key = in.readUTF();
	            	if(key.equals(password)){
	            		return true;//������ȷ
	            	}else{
	            		return false;//�������
	            	}
	            }
	         }
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;//�������û�
	}
	
	public static boolean register(String id,String password1,String password2){//ע���û�
		if(password1.equals(password2)){//����������ͬ
			addAccount(new Account(id,password1));
			return true;
		}
		return false;
	}
	
	public static void addAccount(Account user){//����˻�
		try {
			out = new DataOutputStream(new FileOutputStream(Constant.getFilePath(),true));
			out.writeUTF(user.id);
			out.writeUTF(user.password);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					out = null;
				}
			}
		}
	}
}
