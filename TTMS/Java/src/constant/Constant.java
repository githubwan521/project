package constant;

public class Constant {//UI�еĳ����ֶ�
	public static int userLength = -1;//id���Ȳ����Ϲ淶
	
	public static String getFilePath(){//��ɾ��----�ڷ�����
		return "E:\\java\\TTMS\\account\\account.txt";
	}
	public static String getLoginUiImg(){//�õ���¼���ڵ�ͼƬ��ַ
		return "E:\\java\\TTMS\\img\\login\\login.jpg";
	}
	public static String getLoginUiViaImg() {//�õ���½�ɹ�
		return "E:\\java\\TTMS\\img\\login\\loginVia.jpg";
	}
	public static String getLoginUiFailImg(int k) {//�õ���½����1
		return "E:\\java\\TTMS\\img\\login\\loginFail"+k+".jpg";
	}
	public static String getRegisterUiImg(){//�õ�ע�ᴰ�ڵ�ͼƬ��ַ
		return "E:\\java\\TTMS\\img\\register\\register.jpg";
	}
	public static String getRegisterUiViaImg(){//�õ�ע��ɹ�
		return "E:\\java\\TTMS\\img\\register\\registerVia.jpg";
	}
	public static String getRegisterUiFailImg(int k){//�õ�ע�����1
		return "E:\\java\\TTMS\\img\\register\\registerFail"+k+".jpg";
	}
	public static String getUserPerfaceUiImg(int k){//3����ҳ����ͷͼƬ
		return "E:\\java\\TTMS\\img\\user\\preface" + k + ".jpg";
	}
	public static String getUserPerfaceMovieImg(int k){//3����ҳ����ͷ�ĵ�ӰͼƬ
		return "E:\\java\\TTMS\\img\\user\\prefacemovie" + k + ".jpg";
	}
	public static String getUserUiImg(int k){//�û�4��ͼƬ
		return "E:\\java\\TTMS\\img\\user\\user" + k + ".jpg";
	}
	public static String getHotmovieImg(int k){//��ӳ��Ӱ
		return  "E:\\java\\TTMS\\img\\user\\hotmovie"+k+".jpg";
	}
	public static String getHotTicketImg(int k){//��ӳ��Ӱ
		return  "E:\\java\\TTMS\\img\\user\\hotmovieticket"+k+".jpg";
	}
	public static String getSoonmovieImg(int k){//������ӳ
		return  "E:\\java\\TTMS\\img\\user\\soonmovie"+k+".jpg";
	}
	
	
	  
	public static int getLoginUiSizeX(){//�õ���¼���ڴ�С��x
		return 290;
	}
	public static int getLoginUiSizeY(){//�õ���¼���ڴ�С��y
		return 486;
	}
	public static int getRegisterUiSizeX(){//�õ�ע�ᴰ�ڴ�С��x
		return getLoginUiSizeX();
	}
	public static int getRegisterUiSizeY(){//�õ�ע�ᴰ�ڴ�С��y
		return getLoginUiSizeY();
	}
	public static int getUserUiSizeX(){//�õ��û����ڴ�С��x
		return 1050;
	}
	public static int getUserUiSizeY(){
		return 656;
	}
	public static int getTicketUiSizeX(){//�õ��û����ڴ�С��y
		return 750;
	}
	public static int getTicketUiSizeY(){//�õ��û����ڴ�С��y
		return 460;
	}
	
}
