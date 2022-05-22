package com.example.restaurall.servlets;

import com.example.restaurall.beans.Plat;
import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.PlatDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet relatif aux traitement de la mise à jour d'un plat
 */
@WebServlet(name = "EditPlat", value = "/EditPlat")
public class EditPlat extends HttpServlet {
    private PlatDao platDao;

    /**
     * Initialisation de la DaoFactory et des méthodes relatifs aux plats
     */
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.platDao = daoFactory.getPlatDao();
    }

    /**
     * Affichahe du plat à modifier
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
            Plat plat = platDao.getPlatById(Integer.parseInt(saisie));
            request.setAttribute("saisie", saisie);

            request.setAttribute("plat", plat);
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-plat.jsp").forward(request, response);

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

        Plat plat = new Plat();
        String saisie = request.getParameter("id");
        plat.setNom(request.getParameter("nom"));
        plat.setPrix(Double.parseDouble(request.getParameter("prix")));
        plat.setType(request.getParameter("type"));
        plat.setIdPlat(Integer.parseInt(saisie));
        try {
            platDao.update(plat);
            request.setAttribute("plat", platDao.getPlatById(Integer.parseInt(saisie)));
            // request.setAttribute("plat", plat);
            request.setAttribute("success", "Modification effectuée");

        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit-plat.jsp").forward(request, response);

    }
}
