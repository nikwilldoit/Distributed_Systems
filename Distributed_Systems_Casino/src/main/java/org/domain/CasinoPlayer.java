package org.domain;

public class CasinoPlayer extends User{

    private float tokens;
    private float netProfit;

    public CasinoPlayer(String username, String hash, String salt, String email, float tokens, float netProfit) {
        super(username, hash, salt, email);
        this.tokens = tokens;
        this.netProfit = netProfit;
    }

    public float getTokens() {
        return tokens;
    }

    public void setTokens(float tokens) {
        this.tokens = tokens;
    }

    public float getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(float netProfit) {
        this.netProfit = netProfit;
    }


}
