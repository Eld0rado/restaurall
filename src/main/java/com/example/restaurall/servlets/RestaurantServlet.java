package com.example.restaurall.servlets;

import com.example.restaurall.beans.Plat;
import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.PlatDao;
import com.example.restaurall.dao.RestaurantDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet pour l'affichage d'une page restaurant et ses plats
 */
@WebServlet(name = "Restaurant", value = "/Restaurant")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = -3356930090758432979L;
    private RestaurantDao restaurantDao;
    private PlatDao platDao;

    /**
     * Initialisation des DAO et leurs méthodes
     */
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.restaurantDao = daoFactory.getRestaurantDao();
        this.platDao = daoFactory.getPlatDao();
    }

    /**
     * Affichage du restaurant et ses plats
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("path", request.getContextPath() + getServletName());
        request.setAttribute("search", request.getParameter("id"));
        request.setAttribute("context", request.getServletContext());

        try {
            String saisie = request.getParameter("id");
            request.setAttribute("resto", restaurantDao.getRestoById(Integer.parseInt(saisie)));
            request.setAttribute("allprix", platDao.lister(Integer.parseInt(saisie)));
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/restaurant.jsp").forward(request, response);


    }

    /**
     * Récupération des champs du formulaire pour l'ajout d'un plat au restaurant
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("path", request.getPathInfo());
        request.setAttribute("context", request.getServletContext());
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
