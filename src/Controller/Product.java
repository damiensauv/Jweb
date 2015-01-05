package Controller;

import ORM.TomcatDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import Entities.*;

/**
 * Created by damien on 16/12/14.
 */
public class Product extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String id = request.getParameter("id");


        if (id.isEmpty())
        {
            request.setAttribute("simple_error", "id faux");
            RequestDispatcher rd = request.getRequestDispatcher("/");
            rd.forward(request, response);
        }

        TomcatDB db = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");
        db.connectToDataBase();

        int a;
        int somme;
        somme = 0;

        a = 0;

        a = Integer.parseInt(id);

        Entities.Product p = db.get_product(a);
        if (p == null)
            {
                request.setAttribute("simple_error", "id faux");
                RequestDispatcher rd = request.getRequestDispatcher("/");
                rd.forward(request, response);
            }
        else
            {
                Entities.Image img = db.get_image(a);
                List<Comment> listComment = db.get_comments(p.get_id());

                request.setAttribute("product", p);
                request.setAttribute("db", db);
                request.setAttribute("image", img);
                request.setAttribute("listComment", listComment);

                HttpSession session = request.getSession();

                session.setAttribute("product", p);

                Entities.User usr = (User) session.getAttribute("session_user");

                if ((usr != null)) {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/View/Product.jsp").forward(request, response);
                } else {
                    request.setAttribute("simple_error", "Vous devez etre connecte");
                    RequestDispatcher rd = request.getRequestDispatcher("/login");
                    rd.forward(request, response);
                }
            }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        TomcatDB db = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");
        db.connectToDataBase();

        String coms = request.getParameter("coms");
        String star = request.getParameter("star");

        HttpSession session = request.getSession();

        Entities.Product prod = (Entities.Product) session.getAttribute("product");
        Entities.User usr= (User) session.getAttribute("session_user");


        if (coms != null)
        {
            int s;
            if (star == null)
                s = 0;
            else
                s = Integer.parseInt(star);

            Entities.Comment comment = new Comment(usr.get_id(), prod.get_id(), coms, s);

            db.add_comment(comment);


            List<Comment> tmpcom = db.get_comments(prod.get_id());
            Iterator<Comment> it = tmpcom.iterator();
            Comment tmp;
            int somme = 0;

            while (it.hasNext())
            {
                tmp = it.next();
                somme += tmp.get_stars();
            }
            prod.set_average(somme / tmpcom.size());
            db.update_product(prod);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/Product.jsp").forward(request, response);
    }
}