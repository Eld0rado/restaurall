package com.example.restaurall.servlets;

import com.example.restaurall.beans.Plat;
import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.PlatDao;
import com.example.restaurall.dao.RestaurantDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditPlat", value = "/EditPlat")
public class EditPlat extends HttpServlet {
    private PlatDao platDao;

    public void init(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.platDao = daoFactory.getPlatDao();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String saisie = request.getParameter("id");
            //String act = request.getParameter("act");
           // boolean deleted = false;
            Plat plat = platDao.getPlatById(Integer.parseInt(saisie));
           // if (request.getParameterValues("act").equals("delete") && !request.getParameterValues("act").equals(null)){
             //  deleted = platDao.delete(Integer.parseInt(saisie));
            //}
               //request.setAttribute("plat", plat);
            //request.setAttribute("deleted", deleted);
            request.setAttribute("saisie", saisie);

            request.setAttribute("plat", plat);
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-plat.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Plat plat = new Plat();
            String saisie = request.getParameter("id");
          //  String act = request.getParameter("act");
            plat.setNom(request.getParameter("nom"));
            plat.setPrix(Double.parseDouble(request.getParameter("prix")));
            plat.setType(request.getParameter("type"));
            plat.setIdPlat(Integer.parseInt(saisie));
            try {
                platDao.update(plat);
                request.setAttribute("plat", platDao.getPlatById(Integer.parseInt(saisie)));
           // request.setAttribute("plat", plat);

        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-plat.jsp").forward(request, response);

    }
}
