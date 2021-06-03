package com.stock.stockquotemanager.converter;

import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.repository.entity.Quote;
import com.stock.stockquotemanager.repository.entity.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class StockToStockQuotesConverterTest {
    
    private final StockToStockQuotesConverter converter = new StockToStockQuotesConverter();

    @Test
    public void stockIsOk() {
        Set<Quote> quotes = Set.of(
            new Quote("2019-01-01", "19"),
            new Quote("2019-01-02", "15"));
        Stock stock = new Stock("petr3", quotes);
        StockQuotes stockQuotes = converter.apply(stock);
        assertFalse(stockQuotes.getQuotes().isEmpty());
    }

    @Test
    public void shouldConvertEvenWhenQuotesIsEmpty() {
        Stock stock = new Stock("petr3", Collections.emptySet());
        StockQuotes stockQuotes = converter.apply(stock);
        assertFalse(stockQuotes.getId().isEmpty());
    }

    @Test
    public void shouldFailWhenIdIsNull() {
        Stock stock = new Stock(null, Collections.emptySet());
        assertThrows(RuntimeException.class,() -> converter.apply(stock));
    }
}
