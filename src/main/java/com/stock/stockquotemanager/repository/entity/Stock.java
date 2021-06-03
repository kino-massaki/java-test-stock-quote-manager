package com.stock.stockquotemanager.repository.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import java.util.Set;

@Entity
public class Stock {
    @Id
    private String id;

    @ElementCollection
    @MapKeyColumn(name="date")
    @Column(name="price")
    @CollectionTable(name="quotes")
    Set<Quote> quotes;

    public Stock() {

    }

    public Stock(String id, Set<Quote> quotes) {
        this.id = id;
        this.quotes = quotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }
}
