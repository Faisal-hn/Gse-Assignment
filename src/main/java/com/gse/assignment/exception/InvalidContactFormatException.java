package com.gse.assignment.exception;

public class InvalidContactFormatException extends RuntimeException {
    public InvalidContactFormatException(String message) {
        super(message);
    }
}