package fersa_web.servlet;

import fersa_web.bean.UserBean;
import fersa_web.grasp_controller.UserController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        UserController userController = UserController.getInstance();
        if (username.length() > 5 && password.length() > 5) {
            if (userController.validateLogin(userBean)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("isLessor", userBean.isLessor());
                if (userBean.isLessor()) {
                    response.sendRedirect("lessorOpt.jsp");
                } else {
                    response.sendRedirect("visitsListServlet");
                    /*RequestDispatcher rd = request.getRequestDispatcher("visitsListServlet");
                    rd.forward(request, response);*/
                }
            } else {
                request.setAttribute("errorMessage", "Dati errati");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("invalidMessage", "Invalid username or password");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
