package fersa_web.servlet;

import fersa_web.bean.MaintenanceBean;
import fersa_web.grasp_controller.MaintenanceController;
import fersa_web.model.MaintenanceRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ManageMaintenanceReqServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int index = Integer.parseInt(request.getParameter("reqIndex"));
        int op = Integer.parseInt(request.getParameter("op"));
        ArrayList<MaintenanceRequest> maintenanceList =
                (ArrayList<MaintenanceRequest>)session.getAttribute("maintenanceList");
        MaintenanceController controller = MaintenanceController.getInstance();

        if (index == -1){
            response.sendRedirect("maintenanceList.jsp");
            return;
        }

        MaintenanceRequest maintenanceRequest = maintenanceList.get(index);
        MaintenanceBean maintenanceBean = new MaintenanceBean();
        maintenanceBean.setUsernameLessor(username);
        maintenanceBean.setIdMaintenanceReq(maintenanceRequest.getId());
        maintenanceBean.setUsernameRenter(maintenanceRequest.getUsernameRenter());
        maintenanceBean.setIdApartment(maintenanceRequest.getIdApartment());
        maintenanceBean.setRequestDate(maintenanceRequest.getDateRequest());
        maintenanceBean.setRequestTime(maintenanceRequest.getTimeRequest());

        if (op == 1){
            maintenanceBean.setAccepted(true);
            if (controller.answerMaintenanceRequest(maintenanceBean)){
                RequestDispatcher rd = request.getRequestDispatcher("maintenanceListServlet");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorRequestOperationWarn.jsp");
            }
        }

        if (op == 0){
            maintenanceBean.setAccepted(false);
            if (controller.answerMaintenanceRequest(maintenanceBean)){
                RequestDispatcher rd = request.getRequestDispatcher("maintenanceListServlet");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorRequestOperationWarn.jsp");
            }
        }
    }
}
