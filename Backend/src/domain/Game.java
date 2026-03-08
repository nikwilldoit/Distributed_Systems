package domain;

import java.util.HashMap;
import java.util.InputMismatchException;

public class Game {

    private String username;
    private float stars;
    private int votes;
    private Scale risk;
    private Scale betLimit;
    private float netProfits;



    private static final float[] lowRiskBet = {0.0f , 0.0f , 0.0f , 0.1f , 0.5f , 1.0f , 1.1f , 1.3f , 2.0f , 2.5f};
    private static final float[] mediumRiskBet = {0.0f , 0.0f , 0.0f , 0.0f , 0.0f , 0.5f , 1.0f , 1.5f , 2.5f , 3.5f};
    private static final float[] highRiskBet = {0.0f , 0.0f , 0.0f , 0.0f , 0.0f , 0.0f , 0.0f , 1.0f , 2.0f , 6.5f};



    public Game(String username, float stars, int votes, float minimumBet,
                float maximumBet, Scale risk,Scale betLimit, float netProfits) {
        this.username = username;
        this.stars = stars;
        this.votes = votes;
        this.risk = risk;
        this.betLimit = betLimit;
        this.netProfits = netProfits;
    }

    public float getNetProfits() {return netProfits; }

    public void setNetProfits(float netProfits) { this.netProfits = netProfits; }

    public Scale getRisk() {
        return this.risk;
    }

    public void setRisk(Scale risk) {
        this.risk = risk;
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

    float getMinimumBet() {
        switch (betLimit) {
            case Scale.LOW -> {
                return 0.1f;
            }
            case Scale.MEDIUM -> {
                return 1f;
            }
            case Scale.HIGH -> {
                return 5f;
            }
        }
        //in case of invalid data input default to LOW
        return 0.1f;
    }
    float getMaximumBet() {
        switch (betLimit) {
            case Scale.LOW -> {
                return 100f;
            }
            case Scale.MEDIUM -> {
                return 10000f;
            }
            case Scale.HIGH -> {
                return 50000f;
            }
        }
        //default to low in case of invalid variable
        return 100f;
    }
    private int getJackPot() {
        switch (risk) {
            case LOW -> {
                return 10;
            }
            case MEDIUM -> {
                return 20;
            }
            case HIGH -> {
                return 40;
            }
        }
        return 10;
    }

    float play(float bet, double number) {
        if(! (getMaximumBet() <= bet && bet <= getMaximumBet()) ) {
            throw new InputMismatchException();
        }
        if(number /100 == 0.0) {
            return getJackPot()*bet;
        }
        int arrayVal = Math.toIntExact(Math.round(number * 10));

        switch(risk) {
            case LOW -> {
                return lowRiskBet[arrayVal] * bet;
            }
            case MEDIUM -> {
                return mediumRiskBet[arrayVal] * bet;
            }
            case HIGH -> {
                return highRiskBet[arrayVal] * bet;
            }
        }
        return lowRiskBet[arrayVal] * bet;
    }



}
