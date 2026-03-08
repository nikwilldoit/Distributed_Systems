package domain;

public class PlayerStats {
    private double totalProfitLoss; // από τη μεριά του παίκτη

    public void recordBet(double profitOrLoss) {
        totalProfitLoss += profitOrLoss;
    }
}
