package Util;


import java.util.ArrayList;

public class Constant {
    public static final int Success = 20;//登录成功
    public static final int Error = 40;//密码错误
    public static final int Unknown = 80;//不存在用户
    public static final int Administrators = 1;//管理员
    public static final int Manager = 2;//经理
    public static final int Conductor = 3;//售票员

    public static final int HaveAuthority = 30;//有登录权限

    public static final ArrayList<String> list = new ArrayList<String>() {//定义不被拦截的页面
        {
            add("/TTMS/LoginServlet");
            add("/TTMS/LogoutServlet");
            add("/TTMS/RegisterServlet");
            add("/index.jsp");
            add("/login.jsp");
            add("/css/animate.css");
            add("/css/style.css");
            add("/js/modernizr-2.6.2.min.js");
            add("/js/jquery.min.js");
            add("/js/bootstrap.min.js");
            add("/css/bootstrap.min.css");
            add("/js/jquery.placeholder.min.js");
            add("/js/jquery.waypoints.min.js");
            add("/js/main.js");
            add("/TTMS/EmployeeServlet");
        }
    };
}
