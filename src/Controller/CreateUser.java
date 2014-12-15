package Controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateUser extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher( "/View/CreateUser.jsp" ).forward( request, response );
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


        request.setAttribute("error", error);

        /*
        *  Faire un truc genre une redirection vers une Page Succes ou reste sur la page si error
        * */


      this.getServletContext().getRequestDispatcher("/View/CreateUser.jsp").forward( request, response );
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
            if (email.matches("#^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$#") == false)
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
