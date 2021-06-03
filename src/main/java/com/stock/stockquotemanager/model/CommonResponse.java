package com.stock.stockquotemanager.model;

public class CommonResponse {
    private String details;

    public CommonResponse(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
