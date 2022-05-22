package com.example.restaurall.servlets;

import com.example.restaurall.beans.Categ;
import com.example.restaurall.beans.Plat;
import com.example.restaurall.beans.Restaurant;
import com.example.restaurall.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "Restaurant", value = "/Restaurant")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = -3356930090758432979L;
    private RestaurantDao restaurantDao;
    private PlatDao platDao;

    public void init(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.restaurantDao = daoFactory.getRestaurantDao();
        this.platDao = daoFactory.getPlatDao();
       // this.categDao = daoFactory.getPlatDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute("path", request.getContextPath()+getServletName());
            request.setAttribute("search", request.getParameter("id"));
        try {
                String saisie = request.getParameter("id");
                request.setAttribute("resto", restaurantDao.getRestoById(Integer.parseInt(saisie)));
                request.setAttribute("allprix", platDao.lister(Integer.parseInt(saisie)));

          //  List<Plat> lprixes = platDao.lister(Integer.parseInt(saisie));
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/restaurant.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("path", request.getPathInfo());
        request.setAttribute("search", request.getParameter("id"));
        String saisie = request.getParameter("id");
        try {
            Plat plat = new Plat();
            plat.setIdResto(Integer.parseInt(saisie));
            plat.setNom(request.getParameter("nom"));
            plat.setPrix(Double.parseDouble(request.getParameter("prix")));
            plat.setType(request.getParameter("type"));
            platDao.ajouter(plat, Integer.parseInt(saisie));

            request.setAttribute("resto", restaurantDao.getRestoById(Integer.parseInt(saisie)));
            request.setAttribute("allprix", platDao.lister(Integer.parseInt(saisie)));
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/restaurant.jsp").forward(request, response);

    }
}
