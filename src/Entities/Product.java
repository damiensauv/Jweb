package Entities;


import java.lang.Exception;
import java.lang.String;
import java.util.Date;

public class    Product extends MyEntity
{
    private int         _id;
    private float       _price;
    private String      _description;
    private String      _name;
    private int         _stock;



    public Product()
    {
        _id = -1;
        _price = -1;
        _description = "";
        _name = "";
        _stock = -1;
    }

    public Product(int id, float price, String description, String name, int qtt)
    {
        _id = id;
        _price = price;
        _description = description;
        _name = name;
        _stock = qtt;
    }

    public Product(float price, String description, String name, int qtt)
    {
        _id = 0;
        _price = price;
        _description = description;
        _name = name;
        _stock = qtt;
    }




    public  boolean     isValid()
    {
        /*
        if (_id == -1)
            throw new Exception("Id of the product is invalid");
        else if (_price == -1)
            throw new Exception("Price of the product must be a non-negative value...");
        else if (_description == -1)
            throw new Exception("Description of the product must be set to something");
        else if (_name == -1)
            throw new Exception("Name of the product must be something...");
        else if (_stock < 0)
            throw new Exception("Quantity of the product is invalid : must be > 0");
    */
            return true;
    }





    public  int         get_id()
    {
        return (_id);
    }

    public  void        set_id(int newId)
    {
        if (_id != newId)
        {
            _hasChanged = true;
        }
        _id = newId;
    }

    public  float         get_price()
    {
        return (_price);
    }

    public  void        set_price(float price)
    {
        if (_price != price)
        {
            _hasChanged = true;
        }
        _price = price;
    }

    public  String      get_description()
    {
        return (_description);
    }

    public  void        set_description(String description)
    {
        if (_description != description)
        {
            _hasChanged = true;
        }
        _description = description;
    }

    public  String      get_name()
    {
        return (_name);
    }

    public  void        set_name(String name)
    {
        if (_name != name)
        {
            _hasChanged = true;
        }
        _name = name;
    }

    public  int         get_stock()
    {
        return (_stock);
    }

    public  void        set_stock(int qtt)
    {
        if (_stock != qtt)
        {
            _hasChanged = true;
        }
        _stock = qtt;
    }
}