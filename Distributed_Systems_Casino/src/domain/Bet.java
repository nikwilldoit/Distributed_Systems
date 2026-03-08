package domain;

import java.time.Instant;

public class Bet {

    private final String playerId;
    private final String gameName;
    private final double amount;

    private final int randomNumber;
    private final double multiplier;   // ή 0 για jackpot
    private final double profitOrLoss; // τελικό αποτέλεσμα για τον παίκτη

    private final Instant timestamp;

    public Bet(String playerId,
               String gameName,
               double amount,
               int randomNumber,
               double multiplier,
               double profitOrLoss,
               Instant timestamp) {

        this.playerId = playerId;
        this.gameName = gameName;
        this.amount = amount;
        this.randomNumber = randomNumber;
        this.multiplier = multiplier;
        this.profitOrLoss = profitOrLoss;
        this.timestamp = timestamp;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getGameName() {
        return gameName;
    }

    public double getAmount() {
        return amount;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getProfitOrLoss() {
        return profitOrLoss;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
