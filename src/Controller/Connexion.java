package Controller;

import Entities.User;
import ORM.TomcatDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Connexion extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*
         * Faire requete pour le Top 3 des produit
         */


        this.getServletContext().getRequestDispatcher("/WEB-INF/View/Connexion.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> error = new HashMap<String, String>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            validEmail(email);
        } catch (Exception e) {
            error.put("email", e.getMessage());
        }

        try {
            validPassword(password);
        } catch (Exception e) {
            error.put("password", e.getMessage());
        }

        HttpSession session = request.getSession();

        if (error.isEmpty())
        {
            request.setAttribute("sucess", "ok");

            TomcatDB em = new TomcatDB("jdbc:mysql://localhost:3306/JWeb", "damien", "azerty");
            em.connectToDataBase();

            User user = em.get_user(email, password);
            if (user == null)
            {
                error.put("user_co", "false");
                request.setAttribute("session_user", null);
                session.setAttribute("session_user", null);
            }
            else
            {
                session.setAttribute("session_user", user);
                request.setAttribute("session_user", user);
            }
        }
        else
        {
            session.setAttribute("session_user", null);
            request.setAttribute("error", error);
            request.setAttribute("sucess", "ko");
            request.setAttribute("session_user", null);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/Connexion.jsp").forward(request, response);
    }

    private void validPassword(String password) throws Exception
    {
        if (password == null || password.trim().length() == 0)
        {
            throw new Exception("Mauvais mot de passe");
        }
    }

    private void validEmail(String email) throws Exception
    {
        if (email != null && email.trim().length() != 0)
        {
            if (email.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$") == false)
            {
                throw new Exception("adresse e-mail invalide");
            }
        }
        else
        {
            throw new Exception("adresse e-mail invalide");
        }
    }


}