package com.stock.stockquotemanager.converter;

import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.repository.entity.Quote;
import com.stock.stockquotemanager.repository.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StockToStockQuotesConverter implements Function<Stock, StockQuotes> {
    @Override
    public StockQuotes apply(Stock stock) {
        if (stock.getId() == null)
            throw new RuntimeException("Id is not present");

        Map<String, String> quotes = stock.getQuotes().stream()
            .collect(Collectors.toMap(Quote::getDate, Quote::getPrice));

        return new StockQuotes(stock.getId().toLowerCase(), quotes);
    }
}
