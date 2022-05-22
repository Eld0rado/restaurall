package com.example.restaurall.servlets;

import com.example.restaurall.beans.Restaurant;
import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.RestaurantDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet relatif aux traitement de la mise à jour d'un plat
 */
@WebServlet(name = "EditRestaurant", value = "/EditRestaurant")
public class EditRestaurant extends HttpServlet {

    private static final long serialVersionUID = 1376718213909039780L;
    private RestaurantDao restaurantDao;

    /**
     * Initialisation de la DaoFactory et des méthodes relatifs aux restos
     */
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.restaurantDao = daoFactory.getRestaurantDao();

    }

    /**
     * Affichae du resto à modifier
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * Récupération des infos du formulaire et update
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
            request.setAttribute("success", "Modification effectuée");
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-resto.jsp").forward(request, response);

    }
}
