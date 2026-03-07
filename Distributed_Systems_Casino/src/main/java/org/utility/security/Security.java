package org.utility.security;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Security {

    public static byte[] encryptSHA256(byte[] input) {

        try {
            MessageDigest cryptographer = MessageDigest.getInstance("SHA-256");
            return cryptographer.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found");
        }
    }

    public static boolean checkSHA256(byte[] input,byte[] expectedHash) {
        byte[] producedHash = encryptSHA256(input);
        return Arrays.equals(producedHash, expectedHash);

    }


}