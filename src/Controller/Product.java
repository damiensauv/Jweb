package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by damien on 16/12/14.
 */
public class Product extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        this.getServletContext().getRequestDispatcher( "/WEB-INF/View/Product.jsp" ).forward( request, response );
    }
}