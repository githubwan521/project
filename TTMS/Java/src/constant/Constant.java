package constant;

public class Constant {//UI中的常量字段
	public static int userLength = -1;//id长度不符合规范
	
	public static String getFilePath(){//待删除----在服务器
		return "E:\\java\\TTMS\\account\\account.txt";
	}
	public static String getLoginUiImg(){//得到登录窗口的图片地址
		return "E:\\java\\TTMS\\img\\login\\login.jpg";
	}
	public static String getLoginUiViaImg() {//得到登陆成功
		return "E:\\java\\TTMS\\img\\login\\loginVia.jpg";
	}
	public static String getLoginUiFailImg(int k) {//得到登陆错误1
		return "E:\\java\\TTMS\\img\\login\\loginFail"+k+".jpg";
	}
	public static String getRegisterUiImg(){//得到注册窗口的图片地址
		return "E:\\java\\TTMS\\img\\register\\register.jpg";
	}
	public static String getRegisterUiViaImg(){//得到注册成功
		return "E:\\java\\TTMS\\img\\register\\registerVia.jpg";
	}
	public static String getRegisterUiFailImg(int k){//得到注册错误1
		return "E:\\java\\TTMS\\img\\register\\registerFail"+k+".jpg";
	}
	public static String getUserPerfaceUiImg(int k){//3张首页带箭头图片
		return "E:\\java\\TTMS\\img\\user\\preface" + k + ".jpg";
	}
	public static String getUserPerfaceMovieImg(int k){//3张首页带箭头的电影图片
		return "E:\\java\\TTMS\\img\\user\\prefacemovie" + k + ".jpg";
	}
	public static String getUserUiImg(int k){//用户4张图片
		return "E:\\java\\TTMS\\img\\user\\user" + k + ".jpg";
	}
	public static String getHotmovieImg(int k){//热映电影
		return  "E:\\java\\TTMS\\img\\user\\hotmovie"+k+".jpg";
	}
	public static String getHotTicketImg(int k){//热映电影
		return  "E:\\java\\TTMS\\img\\user\\hotmovieticket"+k+".jpg";
	}
	public static String getSoonmovieImg(int k){//即将上映
		return  "E:\\java\\TTMS\\img\\user\\soonmovie"+k+".jpg";
	}
	
	
	  
	public static int getLoginUiSizeX(){//得到登录窗口大小的x
		return 290;
	}
	public static int getLoginUiSizeY(){//得到登录窗口大小的y
		return 486;
	}
	public static int getRegisterUiSizeX(){//得到注册窗口大小的x
		return getLoginUiSizeX();
	}
	public static int getRegisterUiSizeY(){//得到注册窗口大小的y
		return getLoginUiSizeY();
	}
	public static int getUserUiSizeX(){//得到用户窗口大小的x
		return 1050;
	}
	public static int getUserUiSizeY(){
		return 656;
	}
	public static int getTicketUiSizeX(){//得到用户窗口大小的y
		return 750;
	}
	public static int getTicketUiSizeY(){//得到用户窗口大小的y
		return 460;
	}
	
}
