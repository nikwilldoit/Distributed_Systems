package domain;

public class Player {

    private final String playerId;
    private double balance;

    public Player(String playerId, double initialBalance) {
        this.playerId = playerId;
        this.balance = initialBalance;
    }

    public String getPlayerId() {
        return playerId;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public void applyResult(double profitOrLoss) {
        balance += profitOrLoss;
    }
}

