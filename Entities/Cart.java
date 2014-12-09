import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.tools.hat.internal.util.Comparer;

import java.lang.Exception;
import java.lang.String;
import java.util.Date;

public class    CartEntity extends MyEntity
{
    private int         _id;
    private int         _userId;
    private Date        _lastAddDate;
    private int         _isValidated;
    private Date        _validationDate;





    CartEntity()
    {
        _id = -1;
        _userId = -1;
        _lastAddDate = null;
        _isValidated = -1;
        _validationDate = null;
    }

    CartEntity(int id, int userId, Date lastAddDate, int isValidated, Date validationDate)
    {
        _id = id;
        _userId = userId;
        _lastAddDate = lastAddDate;
        _isValidated = isValidated;
        _validationDate = validationDate;
    }




    public  boolean     isValid()
    {
        if (_id == -1)
            throw new Exception("id of the cart is invalid : " + _id);
        else if (_userId == -1)
            throw new Exception("userId of the cart is invalid : " + _userId);
        else if (_lastAddDate == null)
            throw new Exception("Date of the last added element to the cart is invalid : " + _last);
        else if (_isValidated == -1)
            throw new Exception("isValidated state of the cart is invalid : " + _isValidated);
        else if (_validationDate == null)
            throw new Exception("userId of the cart is invalid : " + _userId);
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

    public  int         get_userId()
    {
        return (_userId);
    }

    public  void        set_userId(int newId)
    {
        if (_userId != newId)
        {
            _hasChanged = true;
        }
        _userId = newId;
    }

    public  Date        get_lastAddDate()
    {
        return (_lastAddDate);
    }

    public  void        set_lastAddDate(Date date)
    {
        if (_lastAddDate != date)
        {
            _hasChanged = true;
        }
        _lastAddDate = date;
    }

    public  int         get_isValidated()
    {
        return (_isValidated);
    }

    public  void        set_isValidated(int state)
    {
        if (_isValidated != state)
        {
            _hasChanged = true;
        }
        _isValidated = state;
    }

    public  Date        get_validationDate()
    {
        return (_validationDate);
    }

    public  void        set_validationDate(Date date)
    {
        if (_validationDate != date)
        {
            _hasChanged = true;
        }
        _validationDate = date;
    }

}