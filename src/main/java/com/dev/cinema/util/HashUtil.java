package com.dev.cinema.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashUtil {
    private static final Logger LOGGER = LogManager.getLogger(HashUtil.class);
    private static final String CRYPTOGRAPHY = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CRYPTOGRAPHY);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b: digest) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return hashedPassword.toString();
    }
}
