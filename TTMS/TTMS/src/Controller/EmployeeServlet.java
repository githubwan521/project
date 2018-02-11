package Controller;

import Dao.EmployeeDao;
import Dao.FactoryDao;
import Model.Employee;
import Model.User;
import Service.EmployeeService;
import Service.UserService;
import Util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/TTMS/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee emp = new Employee();
        User user = new User();
        String str = request.getParameter("search_emp_name");
        emp.setEmp_no(request.getParameter("AddUserNo"));
        emp.setEmp_name(request.getParameter("AddUserName"));
        emp.setEmp_tel_num(request.getParameter("AddUserTel"));
        emp.setEmp_email(request.getParameter("AddUserEml"));
        emp.setEmp_addr(request.getParameter("AddUserAddr"));//默认头像地址

        String operation = request.getParameter("operation");
        switch (operation){
            case "add":{
                if(EmployeeService.add(emp)){
                    System.out.println("用户表添加成功");
                }
                //有登录权限，添加到登录用户表中
                if(request.getParameter("LoginPermissions").equals(String.valueOf(Constant.HaveAuthority))){
                    if(new UserService().add(user)){
                        System.out.println("登录表添加成功");
                    }
                }
                break;
            }
            case "delete":{

                break;
            }
            case "update":{

                break;
            }
            case "find":{
                int currentPage = 1; // 当前页默认为第一页
                String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
                if (strpage != null && !strpage.equals("")) {
                    currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
                }
                EmployeeDao dao = (EmployeeDao) FactoryDao.creatEmployeeDAO();
                ArrayList<Employee> list  = dao.findEmployeeByPage(currentPage,str);
                int allCount = dao.getAllCount();
                int allPageCount =dao.getAllPageCount();
                currentPage = dao.getCurrentPage();

                // 存入request中
                request.setAttribute("allEmployee", list);
                request.setAttribute("allCount", allCount);
                request.setAttribute("allPageCount", allPageCount);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("search_emp_name", str);
                break;
            }
        }
        request.getRequestDispatcher("/motian.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}