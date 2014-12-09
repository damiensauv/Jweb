import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.String;

public class    UserEntity extends MyEntity
{
    private int         _id;
    private String      _email;
    private String      _pseudo;
    private int         _role;
    private int         _newsletter;
    private String      _salt;
    private String      _hashedpassword;



    UserEntity()
    {
        _id = -1;
        _email = "";
        _pseudo = "";
        _role = UserRole.UNKNOW;
        _newsletter = -1;
        _salt = "42";
        _hashedpassword = "";
    }

    UserEntity(int id, String email, String pseudo, int role, int newsletter, String salt, String hashedpassword)
    {
        _id = id;
        _email = email;
        _pseudo = pseudo;
        _role = role;
        _newsletter = newsletter;
        _salt = salt;
        _hashedpassword = hashedpassword;
    }



    public  boolean     isValid()
    {
        if (_id == -1)
            throw new Exception("id of the user is invalid : " + _id);
        else if (_email == "")
            throw new Exception("Email of the user is not set");
        else if (_pseudo == "")
            throw new Exception("Pseudo of the user is not set");
        else if (_role == UserRole.UNKNOW)
            throw new Exception("Role of the user is not set");
        else if (_newsletter == -1)
            throw new Exception("Newsletter state of the user is not set");
        else if (_salt == "42")
            throw new Exception("Salt of the user is not set");
        else if (_hashedpassword == "")
            throw new Exception("Password of the user is not set");
    }





    public  int         get_id()
    {
        return (_id);
    }

    public  void        set_id(int newId)
    {
        if (_id  != newId)
        {
            _hasChanged = true;
        }
        _id = newId;
    }

    public  String      get_email()
    {
        return (_email)
    }

    public  void        set_email(String newEmail)
    {
        if (_email != newEmail)
        {
            _hasChanged = true;
        }
        _email = newEmail;
    }

    public  String      get_pseudo()
    {
        return (_pseudo)
    }

    public  void        set_pseudo(String newPseudo)
    {
        if (_pseudo != newPseudo)
        {
            _hasChanged = true;
        }
        _pseudo = newPseudo;
    }

    public  int         get_role()
    {
        return (_role);
    }

    public  void        set_role(String newRole)
    {
        if (_role != newRole)
        {
            _hasChanged = true;
        }
        _role = newRole;
    }

    public  int         get_newsletter()
    {
        return (_newsletter);
    }

    public  void        set_newsletter(int newNewsletterState)
    {
        if (_newsletter != newNewsletterState)
        {
            _hasChanged = true;
        }
        _newsletter = newNewsletterState;
    }

    public  String      get_salt()
    {
        return (_salt);
    }

    public  void        set_salt(String newSalt)
    {
        if (_salt != newSalt)
        {
            _hasChanged = true;
        }
        _salt = newSalt;
    }

    public  String      get_hashedpassword()
    {
        return (_hashedpassword);
    }

    public  void        set_hashedpassword(String newHashedpassword)
    {
        if (_hashedpassword != newHashedpassword)
        {
            _hasChanged = true;
        }
        _hashedpassword = newHashedpassword;
    }
}