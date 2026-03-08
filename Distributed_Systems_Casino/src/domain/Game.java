package domain;

public class Game {

    private final String gameName;
    private final String providerName;

    private double stars;     // μέσος όρος
    private int noOfVotes;

    private final String logoPath;
    private final double minBet;
    private final double maxBet;

    private RiskLevel riskLevel;
    private final String secretKey; // HashKey από JSON

    private final BetLevel betCategory;
    private final int jackpot;

    private boolean active; // αν ο manager το "αφαιρέσει", γίνεται false

    public Game(String gameName,
                String providerName,
                double stars,
                int noOfVotes,
                String logoPath,
                double minBet,
                double maxBet,
                RiskLevel riskLevel,
                String secretKey) {

        this.gameName = gameName;
        this.providerName = providerName;
        this.stars = stars;
        this.noOfVotes = noOfVotes;
        this.logoPath = logoPath;
        this.minBet = minBet;
        this.maxBet = maxBet;
        this.riskLevel = riskLevel;
        this.secretKey = secretKey;

        this.betCategory = computeBetCategory(minBet);
        this.jackpot = RiskConfig.getJackpot(riskLevel);
        this.active = true;
    }

    private BetLevel computeBetCategory(double minBet) {
        if (minBet >= 5.0) return BetLevel.THREE_DOLLARS;
        if (minBet >= 1.0) return BetLevel.TWO_DOLLARS;
        return BetLevel.ONE_DOLLAR;
    }

    //rating from users
    public void addRating(int starsToAdd) {
        double total = this.stars * this.noOfVotes + starsToAdd;
        this.noOfVotes++;
        this.stars = total / this.noOfVotes;
    }

    public int getProfit(){
        return 0;
    }

    public double getMaxBet() {
        return maxBet;
    }

    public String getGameName() {
        return gameName;
    }

    public String getProviderName() {
        return providerName;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getNoOfVotes() {
        return noOfVotes;
    }

    public void setNoOfVotes(int noOfVotes) {
        this.noOfVotes = noOfVotes;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public double getMinBet() {
        return minBet;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public BetLevel getBetCategory() {
        return betCategory;
    }

    public int getJackpot() {
        return jackpot;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
