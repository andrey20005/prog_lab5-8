package com.itmo.prog_lab5_8.common;

public class IncorrectRequestException extends Exception {
    public IncorrectRequestException(String message) {
        super(message);
    }
    public IncorrectRequestException(String message, Exception e) {
        super(message, e);
    }
}
