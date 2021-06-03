package com.stock.stockquotemanager.converter;

import com.stock.stockquotemanager.exception.InvalidQuotesFormatException;
import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.repository.entity.Quote;
import com.stock.stockquotemanager.repository.entity.Stock;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Component
public class StockQuotesToStockConverter implements Function<StockQuotes, Stock> {

    @Override
    public Stock apply(StockQuotes stockQuotes) {
        stockQuotesValidate(stockQuotes)
            .ifPresent(message -> {
                throw new InvalidQuotesFormatException(message);
            });

        Set<Quote> quotes = stockQuotes.getQuotes().entrySet()
            .stream()
            .map(entrySet -> new Quote(entrySet.getKey(), entrySet.getValue()))
            .collect(toSet());
        return new Stock(stockQuotes.getId().toUpperCase(), quotes);
    }

    private Optional<String> stockQuotesValidate(StockQuotes stockQuotes) {
        if (stockQuotes.getId() == null)
            throw new IllegalArgumentException("Id must be informed");

        return Stream.of(stockQuotes.getQuotes())
            .map(this::validateQuotes)
            .flatMap(validation -> validation.stream())
            .reduce((s, s2) -> s.concat(", ").concat(s2));
    }

    private List<String> validateQuotes(Map<String, String> quote) {
        List<String> errors = new ArrayList<>();
        quote.forEach((date, price) -> {
            try {
                Date.valueOf(date);
            } catch (Exception e) {
                errors.add("Date " + date + " is invalid");
            }
            try {
                BigDecimal.valueOf(Double.parseDouble(price));
            } catch (Exception e) {
                errors.add("Price " + price+ " is invalid");
            }
        });
        return errors;
    }
}
