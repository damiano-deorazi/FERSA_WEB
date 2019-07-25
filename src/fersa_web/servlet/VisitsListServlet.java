package fersa_web.servlet;

import fersa_web.bean.VisitBean;
import fersa_web.grasp_controller.DeleteVisitController;
import fersa_web.model.ApartmentLessorVisit;
import fersa_web.model.ApartmentRenterVisit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class VisitsListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        VisitBean visitBean = new VisitBean();
        DeleteVisitController controller = DeleteVisitController.getInstance();
        String username = (String) session.getAttribute("username");

        if ((boolean)session.getAttribute("isLessor")){
            visitBean.setUsernameLessor(username);
            visitBean.setTodayDate(LocalDate.now());
            visitBean.setTodayTime(LocalTime.now());
            visitBean.setLessor(true);
            ArrayList<ApartmentLessorVisit> apartmentLessorVisits = controller.searchApartmentsByLessor(visitBean);
            if (apartmentLessorVisits == null){
                response.sendRedirect("emptyVisitsList.jsp");
            } else {
                session.setAttribute("visitsList", apartmentLessorVisits);
                //request.setAttribute("visitsList", apartmentLessorVisits);
                response.sendRedirect("lessorVisitsList.jsp");
                /*RequestDispatcher rd = request.getRequestDispatcher("lessorVisitsList.jsp");
                rd.forward(request, response);*/ /*lasciare cosi o inoltrare con metodo GET*/
            }
        } else {
            visitBean.setUsernameRenter(username);
            visitBean.setTodayDate(LocalDate.now());
            visitBean.setTodayTime(LocalTime.now());
            visitBean.setLessor(false);
            ArrayList<ApartmentRenterVisit> apartmentRenterVisits = controller.searchApartmentsByRenter(visitBean);
            if (apartmentRenterVisits == null){
                response.sendRedirect("emptyVisitsList.jsp");
            } else {
                session.setAttribute("visitsList", apartmentRenterVisits);
                //request.setAttribute("visitsList", apartmentRenterVisits);
                response.sendRedirect("renterVisitsList.jsp");
                /*RequestDispatcher rd = request.getRequestDispatcher("renterVisitsList.jsp");
                rd.forward(request, response);*/
            }
        }
    }
}
