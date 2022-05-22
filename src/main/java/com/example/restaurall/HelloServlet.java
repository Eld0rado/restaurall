package com.example.restaurall;

import com.example.restaurall.connector.RestaurantBDD;
import com.example.restaurall.beans.Restaurant;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private List<Restaurant> restos;

    public void init() {
        message = "Hello World!";
        try {
            restos = RestaurantBDD.getRestaurants();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h3>"+restos+"</h3>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}