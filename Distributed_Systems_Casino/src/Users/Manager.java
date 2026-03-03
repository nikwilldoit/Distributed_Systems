package Users;

import others.Game;

public class Manager extends User {


    private String name;
    private String surname;
    private String email;
    private String password;
    private String IP;
    private String port;

    public Manager(String name, String surname, String email, String password, String IP, String port) {
        super(name, surname, email, password, IP, port);
    }

    public void addGame(Game game) {
    }
    public Game RemoveGame(){
        return null;
    }
    public double ShowProfitPerGame(Game game){
        return 0.0;
    }
    public double ShowDamagePerGame(Game game){
        return 0.0;
    }
    public double ShowProfitPerPlayer(Player player){
        return 0.0;
    }
    public double ShowDamagePerPlayer(Player player){
        //hashmap getter. idio kai sta panw
        return 0.0;
    }
}
