package Controller;

import Enum.UserRole;
import Entities.*;
import Entities.Product;
import ORM.TomcatDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUser extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        TomcatDB db = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");
        db.connectToDataBase();

        List<SimplyfiedUser> list = db.get_user_list();
        request.setAttribute("listUser", list);
        HttpSession session = request.getSession();
        Entities.User usr = (User) session.getAttribute("session_user");

        if ((usr != null) && (usr.get_role() == UserRole.ADMIN))
        {
            this.getServletContext().getRequestDispatcher( "/WEB-INF/View/AdminUser.jsp" ).forward( request, response );
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
        HttpSession session = request.getSession();
        Entities.User usr = (User) session.getAttribute("session_user");

        if (usr != null)
        {
            String useremail = request.getParameter("user");
            String action = request.getParameter("action");

            if (usr.get_role() == UserRole.ADMIN)
            {
                TomcatDB db = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");
                db.connectToDataBase();

                User tmpuser;

                tmpuser = db.get_specific_user_mail(useremail);

                if (tmpuser == null)
                {
                    request.setAttribute("simple_error", "utilisateur inexistant");
                }
                else if ((tmpuser.get_id() == usr.get_id()))
                {
                    request.setAttribute("simple_error", "Action non permise, on peut pas se modifier soit meme");
                }
                else
                {
                    if (action.equals("Up Admin"))
                    {
                        tmpuser.set_role(UserRole.ADMIN);
                        db.update_user(tmpuser);
                    }
                    else if (action.equals("Up Simple Utilisateur"))
                    {
                        tmpuser.set_role(UserRole.USER);
                        db.update_user(tmpuser);
                    }
                    else if (action.equals("Supprimer Utilisateur"))
                    {
                      db.delete_user(tmpuser);
                    }
                }
            }
            else
            {
                request.setAttribute("simple_error", "Vous n'etes pas Admin");
            }

        }
        else
        {
            request.setAttribute("simple_error", "Vous devez etre connecte en Admin");
        }

        this.doGet(request,response);
    }
}