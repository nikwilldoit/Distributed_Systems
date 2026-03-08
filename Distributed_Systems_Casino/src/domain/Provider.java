package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Provider {
    private String name;
    private List<Game> games = new ArrayList<>();
    private HashMap<Game,Double> BalancePerGamePlayed = new HashMap<>();

    public Provider(String name, List<Game> games){
        this.name = name;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public HashMap<Game, Double> getBalancePerGamePlayed() {
        return BalancePerGamePlayed;
    }

    public void setBalancePerGamePlayed(HashMap<Game, Double> balancePerGamePlayed) {
        BalancePerGamePlayed = balancePerGamePlayed;
    }
}
