package com.zzezze.makaogift.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
        super("authentication error");
    }
}
