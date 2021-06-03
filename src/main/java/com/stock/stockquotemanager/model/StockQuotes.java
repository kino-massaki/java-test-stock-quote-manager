package com.stock.stockquotemanager.model;


import java.util.Map;

public class StockQuotes {

    private String id;

    Map<String, String> quotes;

    public StockQuotes() {
    }

    public StockQuotes(String id, Map<String, String> quotes) {
        this.id = id;
        this.quotes = quotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }
}
