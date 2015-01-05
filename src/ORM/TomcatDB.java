package ORM;

import Entities.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;
import Enum.*;

public  class  TomcatDB implements IDataBase
{
    private Connection      connection;
    private String          _url;
    private String          _username;
    private String          _password;

    public                   TomcatDB(String url, String username, String password)
    {
        _url = url;
        _username = username;
        _password = password;
        connection = null;
    }

    /*
    Connection ex : jdbc:mysql://localhost:3306/JWeb
     */
    public boolean     connectToDataBase()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(_url, _username, _password);
            return true;
        }
        catch (SQLException e)
        {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /*
    Query execution
     */
    private ResultSet   execute_SELECT_query(String query) throws SQLException
    {
        ResultSet   res;

        try
        {
            Statement stmt = connection.createStatement();
            res = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            System.out.print(e.getMessage());
            throw (e);
        }
        return (res);
    }

    private boolean     execute_INSERT_query(String query)
    {
        try
        {
            Statement stmt = connection.createStatement() ;
            stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return (false);
        }
        return (true);
    }
    private boolean     execute_DELETE_query(String query)
    {
        try
        {
            Statement stmt = connection.createStatement() ;
            stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return (false);
        }
        return (true);
    }

    /*
    Add
     */
    public boolean      add_user(User obj)
    {
        String          query = "INSERT INTO User (email, pseudo, role, newsletter, salt, password) VALUES('";

        query += obj.get_email() + "', '" + obj.get_pseudo() + "', '" + obj.get_role() + "', " + obj.get_newsletter() + ", '" + obj.get_salt() + "', '" + obj.get_hashedpassword();
        query += "') ;";
        return (execute_INSERT_query(query));
    }

    public boolean      add_product(Product obj)
    {
        String          query = "INSERT INTO Product (price, description, name, stock) VALUES(";

        query += obj.get_price() + ", '" + obj.get_description() + "', '" + obj.get_name() + "', " + obj.get_stock();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_cart(Cart obj)
    {
        String          query = "INSERT INTO Cart (id_user, date_add, is_buy, date_buy) VALUES(";

        query += obj.get_userId() + ", " + obj.get_lastAddDate() + ", " + obj.get_isValidated() + ", " + obj.get_validationDate();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_cart_product(CartProduct obj)
    {
        String          query = "INSERT INTO CartProduct (id_cart, id_productId, quantity) VALUES(";

        query += obj.get_cartId() + ", " + obj.get_productId() + ", " + obj.get_quantity();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_comment(Comment obj)
    {
        String          query = "INSERT INTO Comment (user_id, product_id, comment, stars) VALUES(";

        query += obj.get_userId() + ", " + obj.get_productId() + ", '" + obj.get_comment() + "', " + obj.get_stars();
        query += ") ;";
        return (execute_INSERT_query(query));
    }

    public boolean      add_image(Image obj)
    {
        String          query = "INSERT INTO Image (id_product, url) VALUES(";

        query += obj.get_id() + ", '" + obj.get_url() + "') ;";
        return (execute_INSERT_query(query));
    }

    /*
    Update
     */
    public boolean      update_user(User obj)
    {
        String query = "UPDATE User SET email='" + obj.get_email() + "', pseudo='"+obj.get_pseudo()+"',role='"+obj.get_role()+"';";
        return (execute_INSERT_query(query));
    }
    public boolean update_product(Product obj)
    {
        String query = "UPDATE Product SET price="+obj.get_price() + ",description='"+obj.get_description()+"',name='"+obj.get_name()+"', stock="+obj.get_stock()+",average="+obj.get_average()+";";
        return (execute_INSERT_query(query));
    }

    /*
    Delete
     */
    public boolean      delete_user(User obj)
    {
        String   query  = "DELETE FROM User WHERE id = " + obj.get_id() + " ;";
        if (!delete_comment(obj) ||!delete_cart(new Cart(0, obj.get_id(), null, 0, null)) || !execute_DELETE_query(query))
            return (false);
        return (true);
    }
    public boolean      delete_product(Product obj)
    {
        String   query  = "DELETE FROM Product WHERE id = " + obj.get_id() + " ;";
        if (!delete_comment(obj) || !execute_DELETE_query(query))
            return (false);
        return (true);
    }
    public boolean      delete_cart(Cart obj)
    {
        String   query  = "DELETE FROM Cart WHERE id = " + obj.get_id() + " ;";
        String   queryCP  = "DELETE FROM CartProduct WHERE id_cart = " + obj.get_id() + " ;";
        if (!execute_DELETE_query(query) || !execute_DELETE_query(queryCP))
            return (false);
        return (true);
    }
    public boolean      delete_comment(Product obj)
    {
        String   query  = "DELETE FROM Comment WHERE product_id = " + obj.get_id() + " ;";
        return (execute_DELETE_query(query));
    }
    public boolean      delete_comment(User obj)
    {
        String   query  = "DELETE FROM Comment WHERE user_id = " + obj.get_id() + " ;";
        return (execute_DELETE_query(query));
    }
    public boolean      delete_cart_product(CartProduct obj)
    {
        String   query  = "DELETE FROM CartProduct WHERE ";

        query += "id = " + obj.get_cartId() + "product_id = " + obj.get_productId() + " ;";
        return (execute_DELETE_query (query));
    }

    /*
    Checks
     */
    public boolean      user_already_exist(String user_mail)
    {
        Iterator<SimplyfiedUser>    list = get_user_list().iterator();
        SimplyfiedUser              user;

        if (list != null)
        {
            while (list.hasNext())
            {
                user = list.next();
                if (user.get_email().compareTo(user_mail) == 0)
                    return (true);
            }
            return (false);
        }
        return (true);
    }



    /*
    Get
     */
    public User                     get_specific_user(int id)
    {
        String      query = "SELECT * FROM User WHERE id = " + id + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                if (res.getInt("id") == id)
                {
                    return (new User(res.getInt("id"),
                            res.getString("email"),
                            res.getString("pseudo"),
                            UserRole.valueOf(res.getString("role")),
                            res.getBoolean("newsletter"),
                            res.getString("salt"),
                            res.getString("password")));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public User                     get_specific_user_mail(String email)
    {
        String      query = "SELECT * FROM User WHERE email = '" + email + "' ;";
        ResultSet   res;


        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                if (res.getString("email").equals(email))
                {
                    return (new User(res.getInt("id"),
                            res.getString("email"),
                            res.getString("pseudo"),
                            UserRole.valueOf(res.getString("role")),
                            res.getBoolean("newsletter"),
                            res.getString("salt"),
                            res.getString("password")));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public User                     get_user(String email, String password)
    {
        String      query = "SELECT * FROM User WHERE email = '" + email + "' AND password = '" + password + "';";
        ResultSet   res;


        try
        {
             res = execute_SELECT_query(query);

            while (res.next()) {
                if (res.getString("email").equals(email) && res.getString("password").equals(password))
                {

                    return (new User(res.getInt("id"),
                            res.getString("email"),
                            res.getString("pseudo"),
                            UserRole.valueOf(res.getString("role")),
                            res.getBoolean("newsletter"),
                            res.getString("salt"),
                            res.getString("password")));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    public List<SimplyfiedUser>     get_user_list()
    {
        List<SimplyfiedUser>        list = new ArrayList<SimplyfiedUser>();
        String      query = "SELECT email, id, pseudo FROM User ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new SimplyfiedUser(res.getString("pseudo"), res.getString("email"), res.getInt("id")));
            }
            return (list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    public List<Cart>               get_users_carts(int user_id)
    {
        List<Cart>  list = new ArrayList<Cart>();
        String      query = "SELECT * FROM Cart WHERE user_id = " + user_id + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new Cart(res.getInt("id"), res.getInt("id_user"), res.getDate("date_add"), res.getInt("is_buy"), res.getDate("date_buy")));
            }
            return (list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    public List<CartProduct>        get_cart_products(int cart_id)
    {
        List<CartProduct>           list = new ArrayList<CartProduct>();
        String                      query = "SELECT * FROM CartProduct WHERE id = " + cart_id + " ;";
        ResultSet                   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new CartProduct(res.getInt("id_cart"), res.getInt("id_product"), res.getInt("quantity")));
            }
            return (list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    public List<Product>            get_products()
    {
        List<Product>                  list = new ArrayList<Product>();
        String      query = "SELECT * FROM Product;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new Product(res.getInt("id"), res.getFloat("price"), res.getString("description"), res.getString("name"), res.getInt("stock"), res.getInt("average")));
            }
            return (list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public List<Image>            get_images()
    {
        List<Image>                  list = new ArrayList<Image>();
        String              query = "SELECT * FROM Image;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new Image(res.getInt("id_product"), res.getString("url")));
            }
            return (list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public Product                  get_product(int id)
    {
        String      query = "SELECT * FROM Product WHERE id = " + id + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);

            if (res.next())
                return (new Product(res.getInt("id"), res.getFloat("price"), res.getString("description"), res.getString("name"), res.getInt("stock"), res.getInt("average")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public Product                  get_product_by_name(String name)
    {
        String      query = "SELECT * FROM Product WHERE name = '" + name + "' ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            res.next();
            return (new Product(res.getInt("id"), res.getFloat("price"), res.getString("description"), res.getString("name"), res.getInt("stock"), res.getInt("average")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public Image                    get_image(int productid)
    {
        String      query = "SELECT * FROM Image WHERE id_product = " + productid + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            res.next();
            return new Image(res.getInt("id_product"), res.getString("url"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public List<Comment>            get_comments(int productid)
    {
        List<Comment>                  list = new ArrayList<Comment>();
        String              query = "SELECT * FROM Comment WHERE product_id = " + productid + " ;";

        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new Comment(res.getInt("id"), res.getInt("user_id"), res.getInt("product_id"), res.getString("comment"), res.getInt("stars") ));
            }
            return (list);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
}
