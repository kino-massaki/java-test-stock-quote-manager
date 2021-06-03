package com.stock.stockquotemanager.controller;

import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.service.StockQuotesService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static java.net.URI.create;

@RestController
@RequestMapping("/stocks/quotes")
public class StockQuotesController {

    private final StockQuotesService service;

    public StockQuotesController(StockQuotesService stockQuotesService) {
        this.service = stockQuotesService;
    }

    @GetMapping
    public ResponseEntity<List<StockQuotes>> getStocks() {
        List<StockQuotes> stocks = service.getStocks();
        return ResponseEntity.ok(stocks);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StockQuotes> getStockById(@PathVariable String id) {
        try {
            StockQuotes stockById = service.getStockById(id);
            return ResponseEntity.ok(stockById);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> createStockQuote(RequestEntity<StockQuotes> stockQuotes) {
        final StockQuotes body = stockQuotes.getBody();
        if (body == null || body.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        service.createStockQuote(body);
        URI uri = create(stockQuotes.getUrl().toString().concat("/" + body.getId()));
        return ResponseEntity.created(uri).build();
    }
}
