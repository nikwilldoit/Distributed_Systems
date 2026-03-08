package domain;

import java.util.Set;

public class GameFilters {

    // αν είναι κενό -> δεν φιλτράρεις με βάση risk
    private Set<RiskLevel> riskLevels;

    // αν είναι κενό -> δεν φιλτράρεις με βάση $
    private Set<BetLevel> betCategories;

    // ελάχιστα αστέρια (π.χ. 3 σημαίνει 3–5)
    private Double minStars;

    // όρια πονταρίσματος που θέλει ο παίκτης
    private Double minBet;   // αν είναι null -> δεν έχει κάτω όριο
    private Double maxBet;   // αν είναι null -> δεν έχει πάνω όριο

    // μελλοντικά μπορείς να προσθέσεις και providerName, gameName pattern κ.λπ.

    public GameFilters() {
    }

    public Set<RiskLevel> getRiskLevels() {
        return riskLevels;
    }

    public void setRiskLevels(Set<RiskLevel> riskLevels) {
        this.riskLevels = riskLevels;
    }

    public Set<BetLevel> getBetCategories() {
        return betCategories;
    }

    public void setBetCategories(Set<BetLevel> betCategories) {
        this.betCategories = betCategories;
    }

    public Double getMinStars() {
        return minStars;
    }

    public void setMinStars(Double minStars) {
        this.minStars = minStars;
    }

    public Double getMinBet() {
        return minBet;
    }

    public void setMinBet(Double minBet) {
        this.minBet = minBet;
    }

    public Double getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(Double maxBet) {
        this.maxBet = maxBet;
    }
}
