package ORM;


import Entities.*;

import java.util.List;

public  interface  IDataBase
{
    /*
    Connection
     */
    boolean     connectToDataBase();



    /*
    Add
     */
    /**
     * Protos avec commentaire : par exemple ça
     * Fonction qui prend un User en parametre et le push en base de donnée
     */
    boolean     add_user(User obj);
    public boolean      add_product(Product obj);



    /*
    Update
     */
    /**
     * Fonction qui prend un User en parametre et l'update en base de donnée
     */
    boolean     update_user(User obj);
    public boolean      update_product(Product obj);



    /*
    Checks
     */
    public boolean      user_already_exist(String user_mail);



    /*
    Get
     */
    public User                     get_specific_user(int id);
    public List<SimplyfiedUser>     get_user_list();
    public List<Cart>               get_users_carts(int user_id);
    public List<CartProduct>        get_cart_products(int cart_id);
    public List<Product>            get_products();
    public Product                  get_product(int id);
    public Image                    get_image();

}