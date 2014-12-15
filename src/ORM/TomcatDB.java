package ORM;

import Entities.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

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

    public boolean     connectToDataBase()
    {
        try {
            connection = DriverManager.getConnection(_url, _username, _password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean     add_user(User obj)
    {
        /*
        Not implemented
         */
        return false;
    }

    public boolean  update_user(User obj)
    {
        /*
        Not implemented
         */
        return false;
    }
}