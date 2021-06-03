package com.stock.stockquotemanager.controller;

import com.stock.stockquotemanager.model.StockQuotes;
import com.stock.stockquotemanager.service.StockQuotesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockQuotesControllerTest {
    
    @Mock
    private StockQuotesService service;
    
    private StockQuotesController controller;

    List<StockQuotes> stockQuotes;

    StockQuotes petr3;

    @BeforeEach
    public void before() {
        controller = new StockQuotesController(service);
        stockQuotes = List.of(
            new StockQuotes("petr3", Map.of("2019-01-01", "19", "2019-01-02", "15")),
            new StockQuotes("petr4", Map.of("2019-01-01", "19", "2019-01-02", "15"))
        );
        petr3 = new StockQuotes("petr3", Map.of("2019-01-01", "19", "2019-01-02", "15"));
    }

    @Test
    public void shouldReturnEmptyStockQuotes() {
        when(service.getStocks()).thenReturn(emptyList());
        assertTrue(controller.getStocks().getBody().isEmpty());
    }

    @Test
    public void shouldReturnStockQuotes() {
        when(service.getStocks()).thenReturn(stockQuotes);
        assertFalse(controller.getStocks().getBody().isEmpty());
    }

    @Test
    public void shouldReturnNotFoundResponse() {
        when(service.getStockById(any())).thenThrow(new RuntimeException("Id should not exist"));
        assertEquals(controller.getStockById("petr3").getStatusCodeValue(), 404);
    }

    @Test
    public void shouldReturnAStockQuote() {
        when(service.getStockById(any())).thenReturn(petr3);
        assertEquals(controller.getStockById("petr3").getBody().getId(),"petr3");
    }

    @Test
    public void shouldCreateQuote() {
        RequestEntity<StockQuotes> requestEntity = new RequestEntity<StockQuotes>(petr3, HttpMethod.POST,
            URI.create("localhost/stocks/quotes"));
        ResponseEntity<Void> response = controller.createStockQuote(requestEntity);
        assertEquals(response.getHeaders().getLocation().toString(), "localhost/stocks/quotes/petr3");
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void shouldReturnBadRequestWhenBodyReturns() {
        RequestEntity<StockQuotes> requestEntity = new RequestEntity<StockQuotes>(new StockQuotes(), HttpMethod.POST,
            URI.create("localhost/stocks/quotes"));
        ResponseEntity<Void> response = controller.createStockQuote(requestEntity);
        assertEquals(400, response.getStatusCodeValue());
    }

}
