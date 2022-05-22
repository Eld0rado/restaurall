package com.example.restaurall.servlets;

import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.RestaurantDao;
import com.example.restaurall.beans.Restaurant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Home")
public class Home extends HttpServlet {

    private static final long serialVersionUID = 8093741760502158473L;
    private RestaurantDao restaurantDao;
    //public Home(){super();  }

    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.restaurantDao = daoFactory.getRestaurantDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        request.setAttribute("restos", restaurantDao.lister());
            String saisie = request.getParameter("id");

        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
          Restaurant test = restaurantDao.getRestoByNom(request.getParameter("nom"));

          if (test.getNom()== null){
            Restaurant resto = new Restaurant();
            resto.setNom(request.getParameter("nom"));
            resto.setAdresse(request.getParameter("adresse"));
            resto.setCp(Integer.parseInt(request.getParameter("cp")));
            resto.setVille(request.getParameter("ville"));
            resto.setType(request.getParameter("type"));


            restaurantDao.ajouter(resto);
        } else {
                throw new DaoException("Restaurant "+request.getParameter("nom")+" existe déjà");
            }
            request.setAttribute("restos", restaurantDao.lister());
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

    }
}
