package fersa_web.servlet;

import fersa_web.bean.MaintenanceBean;
import fersa_web.grasp_controller.MaintenanceController;
import fersa_web.model.MaintenanceRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class MaintenanceServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        MaintenanceController controller = MaintenanceController.getInstance();
        MaintenanceBean maintenanceBean = new MaintenanceBean();
        maintenanceBean.setUsernameLessor(username);

        ArrayList<MaintenanceRequest> maintenanceRequests = controller.searchMaintenanceRequest(maintenanceBean);
        if (maintenanceRequests.size() == 0) {
            response.sendRedirect("emptyMaintenanceList.jsp");
        } else {
            session.setAttribute("maintenanceList", maintenanceRequests);
            response.sendRedirect("maintenanceList.jsp");
        }
    }
}
