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
 * Servlet relatif page home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {

    private static final long serialVersionUID = 8093741760502158473L;
    private RestaurantDao restaurantDao;

    /**
     * Initialisation de la DaoFactory et les méthodes relatifs aux restos
     */
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.restaurantDao = daoFactory.getRestaurantDao();
    }

    /**
     * Affichage des restos en bdd
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * Récupération des champs du formulaire pour l'ajout d'un nouveau resto
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Restaurant test = restaurantDao.getRestoByNom(request.getParameter("nom"));

            if (test.getNom() == null) {
                Restaurant resto = new Restaurant();
                resto.setNom(request.getParameter("nom"));
                resto.setAdresse(request.getParameter("adresse"));
                resto.setCp(Integer.parseInt(request.getParameter("cp")));
                resto.setVille(request.getParameter("ville"));
                resto.setType(request.getParameter("type"));


                restaurantDao.ajouter(resto);
            } else {
                throw new DaoException("Restaurant " + request.getParameter("nom") + " existe déjà");
            }
            request.setAttribute("restos", restaurantDao.lister());
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

    }
}
