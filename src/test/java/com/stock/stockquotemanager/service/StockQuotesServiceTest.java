package com.stock.stockquotemanager.service;

import com.stock.stockquotemanager.client.stockmanager.StockManagerService;
import com.stock.stockquotemanager.client.stockmanager.StockResponse;
import com.stock.stockquotemanager.converter.StockQuotesToStockConverter;
import com.stock.stockquotemanager.converter.StockToStockQuotesConverter;
import com.stock.stockquotemanager.exception.StockNotRegisteredException;
import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.repository.StockQuoteRepository;
import com.stock.stockquotemanager.repository.entity.Quote;
import com.stock.stockquotemanager.repository.entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockQuotesServiceTest {

    @Mock
    private StockQuoteRepository repository;

    @Mock
    private StockManagerService managerService;

    private StockQuotesToStockConverter stockQuotesToStockConverter = new StockQuotesToStockConverter();

    private StockToStockQuotesConverter stockToStockQuotesConverter = new StockToStockQuotesConverter();

    private StockQuotesService service;

    private Stock stock;

    private StockQuotes stockQuotes;

    @BeforeEach
    public void before() {
        service = new StockQuotesService(repository, managerService, stockQuotesToStockConverter, stockToStockQuotesConverter);
        Set<Quote> quotes = Set.of(
            new Quote("2019-01-01", "19"),
            new Quote("2019-01-02", "15"));
        stock = new Stock("petr3", quotes);
        stockQuotes = new StockQuotes("petr3", Map.of("2019-01-01", "19", "2019-01-02", "15"));
    }

    @Test
    public void getEmptyStocksQuotes() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        List<StockQuotes> stocks = service.getStocks();
        assertTrue(stocks.isEmpty());
    }

    @Test
    public void shouldReturnStockQuotes() {
        when(repository.findAll()).thenReturn(Collections.singletonList(stock));
        List<StockQuotes> stocks = service.getStocks();
        assertFalse(stocks.isEmpty());
    }

    @Test
    public void stockQuoteDoesNotExist() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,() -> service.getStockById("petr3"));
    }

    @Test
    public void shouldFindAStockQuote() {
        when(repository.findById(any())).thenReturn(Optional.of(stock));
        StockQuotes stockQuotes = service.getStockById("petr3");
        assertEquals(stockQuotes.getId(), "petr3");
    }

    @Test
    public void shouldCreateAStockQuote() {
        when(managerService.getStock(any())).thenReturn(new StockResponse("petr3", "Petrobras PN"));
        when(repository.save(any())).thenReturn(stock);
        assertDoesNotThrow(() -> service.createStockQuote(stockQuotes));
    }

    @Test
    public void shouldNotExistOnStockManger() {
        when(managerService.getStock(any())).thenThrow(new StockNotRegisteredException("Stock not registered on stock-manager"));
        assertThrows(StockNotRegisteredException.class, () -> service.createStockQuote(stockQuotes));
    }
}
