package com.stock.stockquotemanager.converter;

import com.stock.stockquotemanager.exception.InvalidQuotesFormatException;
import com.stock.stockquotemanager.exception.StockNotRegisteredException;
import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.repository.entity.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class StockQuotesToStockConverterTest {

    private final StockQuotesToStockConverter converter = new StockQuotesToStockConverter();

    @Test
    public void stockIsOk() {
        StockQuotes stockQuotes = new StockQuotes("petr3", Map.of("2019-01-01", "19", "2019-01-02", "15"));
        Stock stock = converter.apply(stockQuotes);
        assertFalse(stock.getQuotes().isEmpty());
    }

    @Test
    public void shouldConvertEvenWhenQuotesIsEmpty() {
        StockQuotes stockQuotes = new StockQuotes("petr3", Collections.emptyMap());
        Stock stock = converter.apply(stockQuotes);
        assertTrue(stock.getQuotes().isEmpty());
    }

    @Test
    public void shouldFailWhenIdIsNull() {
        StockQuotes stockQuotes = new StockQuotes(null, Map.of("2019-01-01", "19", "2019-01-02", "15"));
        assertThrows(IllegalArgumentException.class,() -> converter.apply(stockQuotes));
    }

    @Test
    public void shouldThrowWhenDateIsInvalid() {
        StockQuotes stockQuotes = new StockQuotes("petr3", Map.of("01-01-2019", "19", "2019-01-02", "15"));
        assertThrows(InvalidQuotesFormatException.class, () -> converter.apply(stockQuotes));
    }

    @Test
    public void shouldThrowWhenPriceIsInvalid() {
        StockQuotes stockQuotes = new StockQuotes("petr3", Map.of("2019-01-01", "19x", "2019-01-02", "15"));
        assertThrows(InvalidQuotesFormatException.class, () -> converter.apply(stockQuotes));
    }

    @Test
    public void shouldThrowWhenDateAndPriceIsInvalid() {
        StockQuotes stockQuotes = new StockQuotes("petr3", Map.of("01-01-2019", "19x", "2019-01-02", "15"));
        assertThrows(InvalidQuotesFormatException.class, () -> converter.apply(stockQuotes));
    }
}
