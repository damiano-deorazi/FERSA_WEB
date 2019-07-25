package fersa_web.servlet;

import fersa_web.bean.VisitBean;
import fersa_web.grasp_controller.ModifyVisitController;
import fersa_web.model.ApartmentLessorVisit;
import fersa_web.model.ApartmentRenterVisit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ModifyVisitServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        ModifyVisitController controller = ModifyVisitController.getInstance();
        VisitBean visitBean = new VisitBean();
        int index = Integer.parseInt(request.getParameter("modifyIndex"));
        LocalDate modDate = LocalDate.parse(request.getParameter("modDate"));
        LocalTime modTime = LocalTime.parse(request.getParameter("modTime"));
        if (checkTimeData(modDate, modTime)){
            response.sendRedirect("invalidDateWarn.jsp");
            return;
        }

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
            visitBean.setUsernameLessor(apartment.getUsernameLessor());
            visitBean.setLessor(true);
            visitBean.setModDate(modDate);
            visitBean.setModTime(modTime);
            if (controller.modifyVisit(visitBean)){
                //response.sendRedirect("visitsListServlet");
                RequestDispatcher rd = request.getRequestDispatcher("visitsListServlet");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorVisitOperationWarn.jsp"); /*visualizza problema data non disponibile visita*/
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
            visitBean.setLessor(false);
            visitBean.setModDate(modDate);
            visitBean.setModTime(modTime);
            if (controller.modifyVisit(visitBean)){
                //response.sendRedirect("visitsListServlet");
                RequestDispatcher rd = request.getRequestDispatcher("visitsListServlet");
                rd.forward(request, response);
            } else {
                response.sendRedirect("errorVisitOperationWarn.jsp"); /*visualizza problema data non disponibile visita*/
            }
        }
    }

    private boolean checkTimeData(LocalDate modDate, LocalTime modTime) {
        if (modDate.isBefore(LocalDate.now())) return true;
        return modDate.isEqual(LocalDate.now()) && modTime.isBefore(LocalTime.now());
    }

}

