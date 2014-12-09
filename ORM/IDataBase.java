public  interface   IDatabase
{
    boolean     connectToDataBase();



    /**
     * Protos avec commentaire : par exemple ça
     * Fonction qui prend un User en parametre et le push en base de donnée
     */
    boolean     add_user(User obj);

    /**
     * Fonction qui prend un User en parametre et l'update en base de donnée
     */
    boolean     update_user(User obj);
}