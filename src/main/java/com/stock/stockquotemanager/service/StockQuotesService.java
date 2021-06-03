package com.stock.stockquotemanager.service;

import com.stock.stockquotemanager.client.stockmanager.StockManagerService;
import com.stock.stockquotemanager.converter.StockQuotesToStockConverter;
import com.stock.stockquotemanager.converter.StockToStockQuotesConverter;
import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.repository.StockQuoteRepository;
import com.stock.stockquotemanager.repository.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
public class StockQuotesService {

    private final StockQuoteRepository repository;

    private final StockManagerService stockManagerService;

    private final StockQuotesToStockConverter stockQuotesToStockConverter;

    private final StockToStockQuotesConverter stockToStockQuotesConverter;

    public StockQuotesService(StockQuoteRepository repository,
                              StockManagerService stockManagerService,
                              StockQuotesToStockConverter stockQuotesToStockConverter,
                              StockToStockQuotesConverter stockToStockQuotesConverter) {
        this.repository = repository;
        this.stockManagerService = stockManagerService;
        this.stockQuotesToStockConverter = stockQuotesToStockConverter;
        this.stockToStockQuotesConverter = stockToStockQuotesConverter;
    }

    public List<StockQuotes> getStocks() {
        return ofNullable(repository.findAll())
            .orElse(Collections.emptyList())
            .stream()
            .map(stockToStockQuotesConverter::apply)
            .collect(toList());
    }

    public StockQuotes getStockById(String id) {
        Stock stock = repository.findById(id.toUpperCase())
            .orElseThrow(() -> new RuntimeException("Stock not registered."));

        return stockToStockQuotesConverter.apply(stock);
    }

    public void createStockQuote(StockQuotes stockQuotes) {
        stockManagerService.getStock(stockQuotes.getId().toLowerCase());

        Stock stock = stockQuotesToStockConverter.apply(stockQuotes);
        repository.save(stock);
    }
}
