package com.example.restaurall.servlets;

import com.example.restaurall.beans.Restaurant;
import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.RestaurantDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditRestaurant", value = "/EditRestaurant")
public class EditRestaurant extends HttpServlet {

    private static final long serialVersionUID = 1376718213909039780L;
    private RestaurantDao restaurantDao;

    public void init(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.restaurantDao = daoFactory.getRestaurantDao();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String saisie = request.getParameter("id");
            request.setAttribute("resto", restaurantDao.getRestoById(Integer.parseInt(saisie)));

        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-resto.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant resto = new Restaurant();
        resto.setId(Integer.parseInt(request.getParameter("id")));
        resto.setNom(request.getParameter("nom"));
        resto.setAdresse(request.getParameter("adresse"));
        resto.setCp(Integer.parseInt(request.getParameter("cp")));
        resto.setVille(request.getParameter("ville"));
        resto.setType(request.getParameter("type"));
        try {
            restaurantDao.updateResto(resto, resto.getId());
            request.setAttribute("resto", restaurantDao.getRestoById(resto.getId()));
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-resto.jsp").forward(request, response);

    }
}
