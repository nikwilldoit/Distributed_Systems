package domain;

public class Game {

    private String username;
    private float stars;
    private int votes;
    private float minimumBet;
    private float maximumBet;
    private Scale risk;






    public Game(String username, float stars, int votes, float minimumBet, float maximumBet, Scale risk) {
        this.username = username;
        this.stars = stars;
        this.votes = votes;
        this.minimumBet = minimumBet;
        this.maximumBet = maximumBet;
        this.risk = risk;
    }

    public Scale getRisk() {
        return this.risk;
    }

    public void setRisk(Scale risk) {
        this.risk = risk;
    }

    public float getMaximumBet() {
        return maximumBet;
    }

    public void setMaximumBet(float maximumBet) {
        this.maximumBet = maximumBet;
    }

    public float getMinimumBet() {
        return minimumBet;
    }

    public void setMinimumBet(float minimumBet) {
        this.minimumBet = minimumBet;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
