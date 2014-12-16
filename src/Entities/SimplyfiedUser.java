package Entities;

/**
 * Created by Guillaume on 16/12/14.
 */
public class SimplyfiedUser
{
    private String      _name;
    private String      _email;
    private int         _id;

    SimplyfiedUser(String name, String email, int id)
    {
        _name = name;
        _email = email;
        _id = id;
    }

    public  String      get_name()
    {
        return (_name);
    }
    public  String      get_email()
    {
        return (_email);
    }
    public int          get_id()
    {
        return (_id);
    }
}
