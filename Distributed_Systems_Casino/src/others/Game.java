package others;

public class Game {
    private String name;
    private BetLevel betLevel;
    private RiskLevel riskLevel;
    private double stars;
    private Provider provider;

    public Game(String name, BetLevel betLevel, RiskLevel riskLevel, double stars, Provider provider) {
        this.name = name;
        this.betLevel = betLevel;
        this.riskLevel = riskLevel;
        this.stars = stars;
        this.provider = provider;
    }
}
