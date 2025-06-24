package com.itmo.prog_lab5_8.common;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Account implements Serializable {
    public String login;
    public String password;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public final String getHash() {
        try {
            var md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            md.update(login.getBytes());
            byte[] hash = md.digest();
            return Arrays.toString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
