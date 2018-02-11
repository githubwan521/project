package Controller;

import Model.User;
import Service.Login;
import Util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TTMS/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("userpass");

        User user = Login.check(username, password);
        switch (user.getUser_state()) {
            case Constant.Success: {
                request.getSession().setAttribute("login", Constant.Success);
                request.getSession().setAttribute("username", username);
                switch (user.getUser_type()) {
                    case Constant.Administrators: {
                        request.getSession().setAttribute("usertype", "Administrators");
                        break;
                    }
                    case Constant.Manager: {
                        request.getSession().setAttribute("usertype", "Manager");
                        break;
                    }
                    case Constant.Conductor: {
                        request.getSession().setAttribute("usertype", "Conductor");
                        break;
                    }
                }
                response.sendRedirect("/motian.jsp");
                return;
            }
            case Constant.Error: {
                request.getSession().setAttribute("login", Constant.Error);
                break;

            }
            case Constant.Unknown: {
                request.getSession().setAttribute("login", Constant.Unknown);
                break;
            }
        }
        response.sendRedirect("/login.jsp");
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
