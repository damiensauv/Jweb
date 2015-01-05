package Controller;

import Entities.*;
import Entities.Product;
import ORM.TomcatDB;
import Enum.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminProductAdd extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Entities.User usr = (User) session.getAttribute("session_user");

        if ((usr != null) && (usr.get_role() == UserRole.ADMIN))
        {
            this.getServletContext().getRequestDispatcher( "/WEB-INF/View/AdminProductAdd.jsp" ).forward( request, response );
        }
        else
        {
            request.setAttribute("simple_error", "Vous devez etre connecte en Admin");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        Map<String, String> error = new HashMap<String, String>();

        String img = request.getParameter("img");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String stock = request.getParameter("stock");
        String prix = request.getParameter("prix");

        try
        {
            validImg(img);
        }
        catch (Exception e)
        {
            error.put("img", e.getMessage());
        }
        try
        {
            validName(name);
        }
        catch (Exception e)
        {
            error.put("name", e.getMessage());
        }
        try
        {
            validDescription(description);
        }
        catch (Exception e)
        {
            error.put("description", e.getMessage());
        }
        try
        {
            validStock(stock);
        }
        catch (Exception e)
        {
            error.put("stock", e.getMessage());
        }
        try
        {
            validPrix(prix);
        }
        catch (Exception e)
        {
            error.put("prix", e.getMessage());
        }

        if (error.isEmpty())
        {
            request.setAttribute("sucess", "ok");

            TomcatDB db = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");
            db.connectToDataBase();

            Float p = Float.parseFloat(prix);
            int q = Integer.parseInt(stock);


            Entities.Product product = new Product(p, description, name, q, 0);
            db.add_product(product);

            Entities.Product prod = db.get_product_by_name(name);

            int id = prod.get_id();

            Entities.Image image = new Image(id, img);
            db.add_image(image);
        }
        else
        {
            request.setAttribute("sucess", "ko");
            error.put("gen", "une erreur a été detecte");
        }

       request.setAttribute("error", error);

    this.getServletContext().getRequestDispatcher("/WEB-INF/View/AdminProductAdd.jsp").forward( request, response );
    }

    private void validImg(String str) throws Exception
    {
        if (str == null || str.trim().length() == 0)
            throw new Exception("Met une url");
    }

    private void validName(String str) throws Exception
    {
        if (str == null || str.trim().length() == 0)
            throw new Exception("Met une nom");
    }

    private void validDescription(String str) throws Exception
    {
        if (str == null || str.trim().length() == 0)
            throw new Exception("Met une une description");
    }

    private void validStock(String str) throws Exception
    {
        if (str == null || str.trim().length() == 0)
            throw new Exception("Met un chiffre pour le stock");
    }

    private void validPrix(String str) throws Exception
    {
        if (str == null || str.trim().length() == 0)
            throw new Exception("Donne un prix");
    }

}