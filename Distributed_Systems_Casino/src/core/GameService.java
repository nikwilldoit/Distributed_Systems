package core;

import domain.Bet;
import domain.Game;
import domain.GameFilters;
import domain.RiskLevel;

import java.util.List;

public class GameService {

    private final WorkerState state;

    public GameService(WorkerState state) {
        this.state = state;
    }

    public void addGameFromJson(Game game) { //για manager
    }

    public void updateRiskLevel(String gameName, RiskLevel newLevel) {
    }

    public void removeGame(String gameName) {
    }

    public List<Game> searchGames(GameFilters filters) {
        return null;
    }

    public Bet play(String playerId,
                    String gameName,
                    double amount,
                    int randomNumber) {
        return null;
    }

    public void rateGame(String playerId,
                         String gameName,
                         int stars) {
    }

    public void addBalance(String playerId, double amount) {
    }
}
