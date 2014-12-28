package Controller;

import Entities.*;
import Entities.Product;
import ORM.TomcatDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 16/12/14.
 */
public class ProductAll extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TomcatDB db = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");

        db.connectToDataBase();
        List<Entities.Product> list = new ArrayList<Product>();

        list = db.get_products();

        request.setAttribute("productAll", list);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/View/ProductAll.jsp" ).forward(request, response);
    }
}