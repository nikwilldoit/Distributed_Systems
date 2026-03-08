package domain;

import java.util.HashMap;
import java.util.Map;

public class ProviderStats {
    private final String providerName;
    private final Map<String, Double> profitLossPerGame = new HashMap<>();
    private double totalProfitLoss;

    public ProviderStats(String providerName) {
        this.providerName = providerName;
    }

    public void recordBet(String gameName, double profitOrLoss) {
        profitLossPerGame.merge(gameName, profitOrLoss, Double::sum);
        totalProfitLoss += profitOrLoss;
    }

    // getters για map + total
}