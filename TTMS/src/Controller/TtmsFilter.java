package Controller;

import Util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebFilter("/*")
public class TtmsFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");  //处理字符编码问题
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String path = request.getServletPath();
        if (Constant.list.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        Integer state = (Integer) request.getSession().getAttribute("login");
        if (state != null && state == Constant.Success) {  //登录成功放行
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(path+"\t被拦截");
        response.sendRedirect("/login.jsp");
        return;
    }
}
