package Entities;

/**
 * Created by Guillaume on 17/12/14.
 */
public class Comment extends MyEntity
{
    private int _userId;
    private int _productId;
    private String _comment;


    public Comment()
    {
        _userId = -1;
        _productId = -1;
        _comment = "";
    }

    public Comment(int userId, int productId, String comment)
    {
        _userId = userId;
        _productId = productId;
        _comment = comment;
    }


    public boolean isValid() {
        /*
        if (_id == -1)
            throw new Exception("Id of the product is invalid");
        else if (_url == null)
            throw new Exception("Image must point to some url");
    */
        return true;
    }


    public int get_userId() {
        return (_userId);
    }

    public int get_productId() {
        return (_productId);
    }

    public String get_comment() {
        return (_comment);
    }

    public void set_userId(int newId) {
        if (_userId != newId) {
            _hasChanged = true;
        }
        _userId = newId;
    }

    public void set_productId(int newId) {
        if (_productId != newId) {
            _hasChanged = true;
        }
        _productId = newId;
    }

    public void set_comment(String comment) {
        if (_comment != comment) {
            _hasChanged = true;
        }
        _comment = comment;
    }
}
