package org.domain;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private String username;
    private String hash;
    private String salt;
    private String email;

    User(String username, String hash, String salt, String email) {
        this.username = username;
        this.hash = hash;
        this.salt = salt;
        this.email = email;
    }

    public static User createUser(String username, String email,String password) {
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        return new User(username, hash, salt,email);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHash() {
        return hash;
    }
    public String getSalt() {
        return salt;
    }
    public void changePassword(String password) {
        this.hash = BCrypt.hashpw(password, salt);
    }
    public boolean correctPassword(String password) {
        String tempHash = BCrypt.hashpw(password, salt);
        return tempHash.equals(hash);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }








}
