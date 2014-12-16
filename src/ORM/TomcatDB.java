package ORM;

import Entities.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;

public  class  TomcatDB implements IDataBase
{
    private Connection      connection;
    private String          _url;
    private String          _username;
    private String          _password;

    TomcatDB(String url, String username, String password)
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
        return false;
    }



    /*
    Add
     */
    public boolean      add_user(User obj)
    {
        /*
        Not implemented
         */
        return false;
    }

    public boolean      add_product(Product obj)
    {
        obj = obj;
        return (true);
    }



    /*
    Update
     */
    public boolean      update_user(User obj)
    {
        /*
        Not implemented
         */
        return false;
    }
    public boolean      update_product(Product obj)
    {
        obj = obj;
        return (true);
    }



    /*
    Checks
     */
    public boolean      user_already_exist(String user_mail)
    {
        user_mail = user_mail;
        /*
        get users
         */
        /*
        verify if mail match with any
         */
        /*
        if match return true else return false
         */
        return (false);
    }



    /*
    Get
     */
    public User                     get_specific_user(int id)
    {
        User        user = null;
        /*
        get by id
         */
        return (user);
    }

    public User                     get_user(String email, String password)
    {
        User        user = null;
        /*
        get by email & password
         */
        return (user);
    }

    public List<SimplyfiedUser>     get_user_list()
    {
        List<SimplyfiedUser>        list = null;
        /*
        get user list
         */
        /*
        fill list
         */
        return list;
    }

    public List<Cart>               get_users_carts(int user_id)
    {
        List<Cart>                  list = null;
        /*
        :)
         */
        return (list);
    }

    public List<CartProduct>        get_cart_products(int cart_id)
    {
        List<CartProduct>                  list = null;
        /*
        :)
         */
        return (list);
    }

    public List<Product>            get_products()
    {
        List<Product>                  list = null;
        /*
        :)
         */
        return (list);
    }

    public Product                  get_product(int id)
    {
        Product     product = null;

        id = id;
        return (product);
    }

    public Image                    get_image()
    {
        Image   img = null;

        return (img);
    }

}