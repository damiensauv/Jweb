import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.tools.hat.internal.util.Comparer;

import java.lang.Exception;
import java.lang.String;
import java.util.Date;

public class    ImageEntity extends MyEntity
{
    private int         _id;
    private String      _url;



    ProductEntity()
    {
        _id = -1;
        _url = null;
    }

    ProductEntity(int id, String url)
    {
        _id = id;
        _url = url;
    }




    public  boolean     isValid()
    {
        if (_id == -1)
            throw new Exception("Id of the product is invalid");
        else if (_url == null)
            throw new Exception("Image must point to some url");
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

    public  String      get_url()
    {
        return (_url);
    }

    public  void        set_url(String url)
    {
        if (_url != url)
        {
            _hasChanged = true;
        }
        _url = url;
    }

}