package domain;

public class GameStats {

    Game game;
    GameStats(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    private double totalProfitLoss; // κέρδος του "casino" από αυτό το παιχνίδι
    private long totalBets;

    public void recordBet(double profitOrLoss) {
        totalProfitLoss += profitOrLoss;
        totalBets++;
    }
}
