package domain;

public class User {

    private String username;
    private String password;
    private String email;
    private float tokens;
    private float netProfits;

    public User(String username, String password, String email,float tokens) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tokens = tokens;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public  float getTokens() {
        return tokens;
    }
    public void setTokens(float tokens) {
        this.tokens = tokens;
    }
    public float getNetProfits() {
        return netProfits;
    }
    public void setNetProfits(float netProfits) {
        this.netProfits = netProfits;
    }


}
