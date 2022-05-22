package com.example.restaurall.servlets;

import com.example.restaurall.dao.DaoException;
import com.example.restaurall.dao.DaoFactory;
import com.example.restaurall.dao.PlatDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePlat", value = "/DeletePlat")
public class DeletePlat extends HttpServlet {

    private static final long serialVersionUID = -6254765733792769449L;
    private PlatDao platDao;

    /**
     * initialisation pour la réccupération des méthodes relatif au Plat
     */
    public void init(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.platDao = daoFactory.getPlatDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //réccupère l'id de du plat
        String saisie = request.getParameter("id");
        //ajout de l'attribut recherche par ID
        try {
            request.setAttribute("plat", platDao.getPlatById(Integer.parseInt(saisie)));

        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
         }

        this.getServletContext().getRequestDispatcher("/WEB-INF/delete-plat.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("plat", platDao.getPlatById(Integer.parseInt(request.getParameter("id"))));

            platDao.delete(Integer.parseInt(request.getParameter("id")));
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/delete-plat.jsp").forward(request, response);

    }
}
