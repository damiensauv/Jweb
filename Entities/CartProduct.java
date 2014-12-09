import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.tools.hat.internal.util.Comparer;

import java.lang.Exception;
import java.lang.String;
import java.util.Date;

public class    CartProductEntity extends MyEntity
{
    private int         _cartId;
    private int         _productId;
    private Date        _quantity;



    CartProductEntity()
    {
        _cartId = -1;
        _productId = -1;
        _quantity = -1;
    }

    CartProductEntity(int cartId, int productId, int qtt)
    {
        _cartId = cartId;
        _productId = productId;
        _quantity = qtt;
    }




    public  boolean     isValid()
    {
        if (_cartId == -1)
            throw new Exception("Cart id of the cart product is invalid");
        else if (_productId == -1)
            throw new Exception("Product id of the cart product is invalid");
        else if (_quantity < 0)
            throw new Exception("Quantity of the product is invalid : must be > 0");
    }





    public  int         get_cartId()
    {
        return (_cartId);
    }

    public  void        set_cartId(int newId)
    {
        if (_cartId != newId)
        {
            _hasChanged = true;
        }
        _cartId = newId;
    }

    public  int         get_productId()
    {
        return (_productId);
    }

    public  void        set_productId(int newId)
    {
        if (_productId != newId)
        {
            _hasChanged = true;
        }
        _productId = newId;
    }

    public  int         get_quantity()
    {
        return (_cartId);
    }

    public  void        set_quantity(int qtt)
    {
        if (_quantity != qtt)
        {
            _hasChanged = true;
        }
        _quantity = qtt;
    }
}