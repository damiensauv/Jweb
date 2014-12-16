package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Connexion extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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

        /*
        Bien Faire avec tout les bon attribut
         */
        if (error.isEmpty()) {
            request.setAttribute("sucess", "ok");

            /*
                Check Si L'user existe & mdp ok
                Si ok --> connexion
             */



            response.sendRedirect(response.encodeRedirectURL("/cart"));

        }
        else
        {

            request.setAttribute("error", error);

            this.getServletContext().getRequestDispatcher("/WEB-INF/View/Connexion.jsp").forward(request, response);
        }
    }

    private void validPassword(String password) throws Exception
    {
        if (password == null || password.trim().length() == 0) /*voir la taille*/
        {
            throw new Exception("PB avec le mot de passe");
        }
    }

    private void validEmail(String email) throws Exception
    {
        if (email != null && email.trim().length() != 0)
        {
            if (email.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$") == false)
            {
                throw new Exception("Fake c pas une addresse");
            }
        }
        else
        {
            throw new Exception("Mets une addresse gros Boulet");
        }
    }


}