package com.stock.stockquotemanager.exception;

public class InvalidQuotesFormatException extends RuntimeException {
    public InvalidQuotesFormatException() {
    }

    public InvalidQuotesFormatException(String message) {
        super(message);
    }

    public InvalidQuotesFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQuotesFormatException(Throwable cause) {
        super(cause);
    }
}
