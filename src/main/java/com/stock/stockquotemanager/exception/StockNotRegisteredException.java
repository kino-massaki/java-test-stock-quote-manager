package com.stock.stockquotemanager.exception;

public class StockNotRegisteredException extends RuntimeException{
    public StockNotRegisteredException() {
    }

    public StockNotRegisteredException(String message) {
        super(message);
    }

    public StockNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockNotRegisteredException(Throwable cause) {
        super(cause);
    }
}
