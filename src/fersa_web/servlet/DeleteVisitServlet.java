package fersa_web.servlet;

import fersa_web.bean.VisitBean;
import fersa_web.grasp_controller.DeleteVisitController;
import fersa_web.model.ApartmentLessorVisit;
import fersa_web.model.ApartmentRenterVisit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteVisitServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DeleteVisitController controller = DeleteVisitController.getInstance();
        VisitBean visitBean = new VisitBean();
        int index = Integer.parseInt(request.getParameter("deleteIndex"));

        if (index == -1)
            /*non è stato impostato il valore del bottone*/
            response.sendRedirect("visitsListServlet");

        if ((boolean)session.getAttribute("isLessor")){
            ArrayList<ApartmentLessorVisit> apartments = (ArrayList<ApartmentLessorVisit>)session.getAttribute("visitsList");
            ApartmentLessorVisit apartment = apartments.get(index);
            visitBean.setIdApartment(apartment.getId());
            visitBean.setVisitDate(apartment.getDateVisit());
            visitBean.setVisitTime(apartment.getTimeVisit());
            visitBean.setUsernameRenter(apartment.getUsernameRenterVisit());
            if (controller.deleteVisit(visitBean)){
                //response.sendRedirect("visitsListServlet");
                RequestDispatcher rd = request.getRequestDispatcher("visitsListServlet");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorVisitOperationWarn.jsp"); /*visualizza problema cancellazione visita*/
            }

        } else {
            ArrayList<ApartmentRenterVisit> apartments = (ArrayList<ApartmentRenterVisit>)session.getAttribute("visitsList");
            ApartmentRenterVisit apartment = apartments.get(index);
            String usernameRenter = (String) session.getAttribute("username");
            visitBean.setUsernameRenter(usernameRenter);
            visitBean.setIdApartment(apartment.getId());
            visitBean.setUsernameLessor(apartment.getUsernameLessor());
            visitBean.setVisitDate(apartment.getDateVisit());
            visitBean.setVisitTime(apartment.getTimeVisit());
            if (controller.deleteVisit(visitBean)){
                //response.sendRedirect("visitsListServlet");
                RequestDispatcher rd = request.getRequestDispatcher("visitsListServlet");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorVisitOperationWarn.jsp"); /*visualizza problema cancellazione visita*/
            }
        }
    }
}
