package Controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Entities.User;
import Enum.*;
import ORM.TomcatDB;


public class CreateUser extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/View/CreateUser.jsp" ).forward( request, response );
    }


    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        Map<String, String> error = new HashMap<String, String>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmation");
        String pseudo = request.getParameter("pseudo");
        String news = request.getParameter("news");

        try
        {
            validPassword(password, confirmation);
        }
        catch (Exception e)
        {
            error.put("password", e.getMessage());
        }

        try
        {
            validEmail(email);
        }
        catch (Exception e)
        {
            error.put("email", e.getMessage());
        }

        try
        {
            validPseudo(pseudo);
        }
        catch (Exception e)
        {
            error.put("pseudo", e.getMessage() );
        }



        if (error.isEmpty())
        {
            request.setAttribute("sucess", "ok");

            /**
             * Creation de USer en base avec different check !!
             *
             * Set request --> success a KO si fail bd avec le bon msg, genre deja cree ...
             */

            User user = new User(42, email, pseudo, UserRole.USER, true, "test salt", password);

            TomcatDB db = new TomcatDB("jdbc:mysql://localhost:8088/JWeb", "damien", "azerty");

            db.connectToDataBase();


            db.add_user(user);


        }
        else
        {
            request.setAttribute("sucess", "ko");
            error.put("a_def", "pb user<-->BD EN TEST");
        }

        request.setAttribute("error", error);


        this.getServletContext().getRequestDispatcher("/WEB-INF/View/CreateUser.jsp").forward( request, response );
    }


    private void validPseudo(String pseudo) throws Exception
    {
        if (pseudo == null || pseudo.trim().length() == 0)
            throw new Exception("Met un pseudo Kleba");
    }

    private void validPassword(String password, String confirmation) throws Exception
    {
        if ((password != null && password.trim().length() != 0) || (confirmation != null && confirmation.trim().length() != 0))
        {
            if (password.length() < 4)
            {
                throw new Exception("Mot de passe trop court");
            }
            else if (password.equals(confirmation) == false)
            {
                throw new Exception("Aucune Correspondance");
            }
        }
        else
        {
            throw new Exception("Mets un mot et confirme le");
        }

    }

    private void validEmail(String email) throws Exception
    {
        if (email != null && email.trim().length() != 0)
        {
            if (email.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$") == false)
            {
                throw new Exception("Mets un truc valide Negro");
            }
        }
        else
        {
            throw new Exception("Mets une addresse gros Boulet");
        }
    }


}
