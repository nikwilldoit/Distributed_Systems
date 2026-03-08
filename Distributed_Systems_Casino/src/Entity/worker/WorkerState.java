package Entity.worker;

import domain.Player;
import domain.Game;
import domain.GameStats;
import domain.PlayerStats;
import domain.ProviderStats;

import java.util.HashMap;
import java.util.Map;

public class WorkerState {

    // games που ανήκουν σε αυτόν τον worker
    private final Map<String, Game> gamesByName = new HashMap<>();

    // στατιστικά
    private final Map<String, GameStats> gameStatsByName = new HashMap<>();
    private final Map<String, Player> playersById = new HashMap<>();
    private final Map<String, PlayerStats> playerStatsById = new HashMap<>();
    private final Map<String, ProviderStats> providerStatsByName = new HashMap<>();

    public Game getGame(String gameName) {
        return gamesByName.get(gameName);
    }

    public void addGame(Game game) {
        gamesByName.put(game.getGameName(), game);
        gameStatsByName.put(game.getGameName(), new GameStats());

        providerStatsByName.putIfAbsent(
                game.getProviderName(),
                new ProviderStats(game.getProviderName())
        );
    }

    public void removeGame(String gameName) {
        Game g = gamesByName.get(gameName);
        if (g != null) {
            g.setActive(false); // δεν το σβήνουμε, απλά το κρύβουμε από players
        }
    }

    // getters για maps ώστε το service να κάνει search, play κ.λπ.
}
