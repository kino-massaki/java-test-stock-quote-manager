package com.stock.stockquotemanager.repository.entity;


import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name="quotes")
public class Quote {

    private String date;

    private String price;

    public Quote() {
    }

    public Quote(String date, String price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}