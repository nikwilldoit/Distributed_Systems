package org.domain;


public class Game {
    private String name;
    private BetLevel betLevel;
    private RiskLevel riskLevel;
    private double stars;
    private String provider;

    private boolean isAvailable  = true;


    private double[] lowRiskBet = {0.0, 0.0, 0.0, 0.1,0.5,1.0,1.1,1.3,2.0,2.5 };
    private double[] mediumRiskBet ={0.0, 0.0, 0.0, 0.1,0.5,1.0,1.1,1.3,2.0,2.5 };
    private double[] highRiskBet = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,2.0,6.5};




    public Game(String name, BetLevel betLevel, RiskLevel riskLevel, double stars, String provider) {
        this.name = name;
        this.betLevel = betLevel;
        this.riskLevel = riskLevel;
        this.stars = stars;
//      this.provider = provider;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BetLevel getBetLevel() {
        return betLevel;
    }
    public void setBetLevel(BetLevel betLevel) {
        this.betLevel = betLevel;
    }
    public RiskLevel getRiskLevel() {
        return riskLevel;
    }
    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
    public double getStars() {
        return stars;
    }
    public double getMinimumBet() {
        switch (betLevel) {
            case $ -> {
                return 0.1;
            }
            case $$ -> {
                return 1;
            }
            case $$$ -> {
                return 5;
            }
        }
        return  0.1;
    }

    public double getMaximumBet() {
        switch (betLevel) {
            case $ -> {
                return 10;
            }
            case $$ -> {
                return 100;
            }
            case $$$ -> {
                return 500;
            }
        }
        return  10;
    }

    public double bet(double randomNumber , double bet) {
        //verify bet is within range
        if(! (getMinimumBet() <=bet && bet <= getMaximumBet()) ) {
            throw new IllegalArgumentException("Bet value out of range");
        }
        if(randomNumber/100 == 0.0) {
            switch (getBetLevel()) {
                case $ -> {
                    return bet*10;
                }
                case $$ -> {
                    return bet*20;
                }
                case $$$ -> {
                    return bet*40;
                }
            }
            return bet*10;
        }
        int index = (int) Math.floor(randomNumber*10);
        switch(getBetLevel()) {
            case $ -> {
                return bet*lowRiskBet[index];
            }
            case $$ -> {
                return bet*mediumRiskBet[index];
            }
            case $$$ -> {
                return bet*highRiskBet[index];
            }

        }
        return bet*lowRiskBet[index];
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}






