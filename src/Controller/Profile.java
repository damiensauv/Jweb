package Controller;

import Entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Profile extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        Entities.User usr = (User) session.getAttribute("session_user");

        if ((usr != null))
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/View/Profile.jsp").forward( request, response );
        }
        else
        {
            request.setAttribute("simple_error", "Vous devez etre connecte");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        }

    }
}
