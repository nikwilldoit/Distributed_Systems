package core;

import Users.manager.Manager;
import domain.Player;
import domain.Provider;

import java.util.ArrayList;
import java.util.List;

public class Casino {
    private String name;
    private List<Provider> CasinoProviders = new ArrayList<>();
    private List<Player> CasinoPlayers = new ArrayList<>();
    private final Manager CasinoManager;

    public Casino(Manager casinoManager, String name) {
        CasinoManager = casinoManager;
        this.name = name;
    }
    public void RemovePlayer(Player player){
        if(player==null || !CasinoPlayers.contains(player)){
            return;
        }
        CasinoPlayers.remove(player);
    }
    public void AddPlayer(Player player){
        CasinoPlayers.add(player);
    }
}
