package com.stock.stockquotemanager.client.stockmanager;

import com.stock.stockquotemanager.exception.StockNotRegisteredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockManagerClientTest {

    @Mock
    private RestTemplate stockManagerRestTemplate;

    private StockManagerClient client;

    @BeforeEach
    public void before() {
        client = new StockManagerClient(stockManagerRestTemplate);
    }

    @Test
    public void shouldReturnStock() {
        when(stockManagerRestTemplate.getForObject(eq("/stock/petr3"), eq(StockResponse.class))).thenReturn(new StockResponse("petr3", "teste"));
        assertDoesNotThrow(() -> client.getStock("petr3"));
        assertTrue(client.getStock("petr3").getId().equalsIgnoreCase("petr3"));
    }

    @Test
    public void shouldThrowWhenNotFound() {
        when(stockManagerRestTemplate.getForObject(eq("/stock/petr3"), eq(StockResponse.class))).thenReturn(null);
        assertThrows(StockNotRegisteredException.class, () -> client.getStock("petr3"));
    }

    @Test
    public void shouldRegister() {
        StockServiceNotificationMessage message = new StockServiceNotificationMessage();
        assertDoesNotThrow(() -> client.registerToNotify(message));
    }

}
