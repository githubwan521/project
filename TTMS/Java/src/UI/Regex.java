package ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//����ƥ��
public class Regex {
	//5-----�����ַ���֤
	private static String regHx ="[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
	//4----- ������֤����
	private static String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
    //3---------�ֻ�����֤����
	private static String regTx = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
    //2------������֤����
	private static String regPx = "[a-zA-Z0-9]{1,16}";//��ĸ�����ֹ��ɣ����ܳ���16λ��
   //1-----�û�����֤���� 
    private static  String regNx = "[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}";//������ĸ�����»�������ҿ�ͷ��������ĸ�����ܳ���16λ��")
    
	public static boolean RE_matching(String str,int model){
		Pattern pattern;
		Matcher matcher;
		boolean rs = false;
		if(model == 1){
			pattern = Pattern.compile(regNx);
		    // ���Դ�Сд��д��
		    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		     matcher = pattern.matcher(str);
		    // �ַ����Ƿ���������ʽ��ƥ��
		     rs = matcher.matches();
		}else if(model ==2){
			 pattern = Pattern.compile(regPx);
		     matcher = pattern.matcher(str);
		     rs = matcher.matches();
		}else if(model ==3){
			 pattern = Pattern.compile(regTx);
		     matcher = pattern.matcher(str);
		     rs = matcher.matches();
		}else if(model==4){
			pattern = Pattern.compile(regHx);
		     matcher = pattern.matcher(str);
		     rs = matcher.matches();
		}
		return rs;
	}
	public static void main(String[] args){
		System.out.println(RE_matching("",4));
	}
}
