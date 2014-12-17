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
    }



    /*
    Connection ex : jdbc:mysql://localhost:8080/JWeb
     */
    public boolean     connectToDataBase()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(_url, _username, _password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
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
            Statement stmt = connection.createStatement() ;
            res = stmt.executeQuery(query) ;
        }
        catch (SQLException e)
        {
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
        String          query = "INSERT INTO user (id, email, pseudo, role, newsletter, salt, hashedPassword) VALUES(";

        query += obj.get_id() + ", " + obj.get_email() + ", " + obj.get_pseudo() + ", " + obj.get_role().toString() + ", " + obj.get_newsletter() + ", " + obj.get_salt() + ", " + obj.get_hashedpassword();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_product(Product obj)
    {
        String          query = "INSERT INTO product (id, price, description, name, stock) VALUES(";

        query += obj.get_id() + ", " + obj.get_price() + ", " + obj.get_description() + ", " + obj.get_name() + ", " + obj.get_stock();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_cart(Cart obj)
    {
        String          query = "INSERT INTO cart (id, userId, lastAddedElement, isValidated, validationDate) VALUES(";

        query += obj.get_id() + ", " + obj.get_userId() + ", " + obj.get_lastAddDate() + ", " + obj.get_isValidated() + ", " + obj.get_validationDate();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_cart_product(CartProduct obj)
    {
        String          query = "INSERT INTO cart_product (id, productId, quantity) VALUES(";

        query += obj.get_cartId() + ", " + obj.get_productId() + ", " + obj.get_quantity();
        query += ") ;";
        return (execute_INSERT_query(query));
    }
    public boolean      add_comment(Comment obj)
    {
        String          query = "INSERT INTO comment (userId, productId, comment) VALUES(";

        query += obj.get_userId() + ", " + obj.get_productId() + ", " + obj.get_comment();
        query += ") ;";
        return (execute_INSERT_query(query));
    }



    /*
    Update
     */
    public boolean      update_user(User obj)
    {
        if (!delete_user(obj) || !add_user(obj))
            return false;
        return (true);
    }
    public boolean      update_product(Product obj)
    {
        if (!delete_product(obj) || !add_product(obj))
            return false;
        return (true);
    }



    /*
    Delete
     */
    public boolean      delete_user(User obj)
    {
        String   query  = "DELETE FROM user WHERE id = " + obj.get_id() + " ;";
        if (!execute_DELETE_query(query) || !delete_comment(obj) ||!delete_cart(new Cart(0, obj.get_id(), null, 0, null)))
            return (false);
        return (true);
    }
    public boolean      delete_product(Product obj)
    {
        String   query  = "DELETE FROM product WHERE id = " + obj.get_id() + " ;";
        if (!execute_DELETE_query(query) || !delete_comment(obj))
            return (false);
        return (true);
    }
    public boolean      delete_cart(Cart obj)
    {
        String   query  = "DELETE FROM cart WHERE id = " + obj.get_id() + " ;";
        String   queryCP  = "DELETE FROM cartProduct WHERE cartId = " + obj.get_id() + " ;";
        if (!execute_DELETE_query(query) || !execute_DELETE_query(queryCP))
            return (false);
        return (true);
    }
    public boolean      delete_comment(Product obj)
    {
        String   query  = "DELETE FROM product WHERE productId = " + obj.get_id() + " ;";
        return (execute_DELETE_query(query));
    }
    public boolean      delete_comment(User obj)
    {
        String   query  = "DELETE FROM product WHERE userId = " + obj.get_id() + " ;";
        return (execute_DELETE_query(query));
    }
    public boolean      delete_cart_product(CartProduct obj)
    {
        String   query  = "DELETE FROM cartProduct WHERE ";

        query += "id = " + obj.get_cartId() + "productId = " + obj.get_productId() + " ;";
        return (execute_DELETE_query(query));
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
        String      query = "SELECT * FROM user WHERE id = " + id + " ;";
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
    public User                     get_user(String email, String password)
    {
        String      query = "SELECT * FROM user WHERE email = \'" + email + "\' AND password = \'" + password + "\';";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next()) {
                if (res.getString("email") == email && res.getString("password") == password) {
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
        String      query = "SELECT email, id, pseudo FROM user ;";
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
        String      query = "SELECT * FROM cart WHERE userId = " + user_id + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new Cart(res.getInt("id"), res.getInt("userId"), res.getDate("lastAddedElement"), res.getInt("isValidated"), res.getDate("validationDate")));
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
        String                      query = "SELECT * FROM cart_product WHERE id = " + cart_id + " ;";
        ResultSet                   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new CartProduct(res.getInt("id"), res.getInt("productId"), res.getInt("quantity")));
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
        String      query = "SELECT * FROM product ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            while (res.next())
            {
                list.add(new Product(res.getInt("id"), res.getFloat("price"), res.getString("description"), res.getString("name"), res.getInt("quantity")));
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
        String      query = "SELECT * FROM product WHERE id = " + id + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            return (new Product(res.getInt("id"),
                    res.getFloat("price"),
                    res.getString("description"),
                    res.getString("name"),
                    res.getInt("quantity")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    public Image                    get_image(int productid)
    {
        String      query = "SELECT * FROM image WHERE productId = " + productid + " ;";
        ResultSet   res;

        try
        {
            res = execute_SELECT_query(query);
            return (new Image(res.getInt("id"), res.getString("description")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

}