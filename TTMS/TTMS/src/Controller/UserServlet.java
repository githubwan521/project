package Controller;

import Dao.FactoryDao;
import Dao.UserDao;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/TTMS/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String str = request.getParameter("search_emp_name");
        switch (operation){
            case "find":{
                int currentPage = 1; // 当前页默认为第一页
                String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
                if (strpage != null && !strpage.equals("")) {
                    currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
                }
                UserDao dao = (UserDao) FactoryDao.creatUserDAO();
                ArrayList<User> list  = dao.findUserByPage(currentPage,str);
                int allCount = dao.getAllCount();
                int allPageCount =dao.getAllPageCount();
                currentPage = dao.getCurrentPage();

                // 存入request中
                request.setAttribute("allUser", list);
                request.setAttribute("allCount", allCount);
                request.setAttribute("allPageCount", allPageCount);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("search_emp_name",str);
                break;
            }
        }
        request.getRequestDispatcher("/motian.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}