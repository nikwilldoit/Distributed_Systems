package Users;

import others.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends User{
    private int PlayerID;
    private int BalanceTokens;
    private List<Game> games = new ArrayList<>();

    private String name;
    private String surname;
    private String email;
    private String password;
    private String IP;
    private String port;

    private HashMap<Game,Double> BalancePerGamePlayed = new HashMap<>();

    public Player(String name, String surname, String email, String password
            , String IP, String port, int BalanceTokens, int PlayerID){
        super(name, surname, email, password, IP, port);
        this.PlayerID = PlayerID;
        this.BalanceTokens = BalanceTokens;
    }

    private void search(){}

    private void play(){}

    private void addBalance(){}
    private double getBalance(){return BalanceTokens;}

    public HashMap<Game, Double> getBalancePerGamePlayed() {
        return BalancePerGamePlayed;
    }

    public void setBalancePerGamePlayed(HashMap<Game, Double> balancePerGamePlayed) {
        BalancePerGamePlayed = balancePerGamePlayed;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getBalanceTokens() {
        return BalanceTokens;
    }

    public void setBalanceTokens(int balanceTokens) {
        BalanceTokens = balanceTokens;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }


}
