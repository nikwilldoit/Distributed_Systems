package domain;

public class GameStats {
    private double totalProfitLoss; // κέρδος του "casino" από αυτό το παιχνίδι
    private long totalBets;

    public void recordBet(double profitOrLoss) {
        totalProfitLoss += profitOrLoss;
        totalBets++;
    }
}
