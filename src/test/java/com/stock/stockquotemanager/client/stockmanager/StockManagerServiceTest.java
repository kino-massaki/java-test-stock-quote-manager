package com.stock.stockquotemanager.client.stockmanager;

import com.stock.stockquotemanager.exception.StockNotRegisteredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockManagerServiceTest {

    @Mock
    private StockManagerClient client;

    private StockManagerService service;

    @BeforeEach
    public void before() {
        service = new StockManagerService(client);
    }

    @Test
    public void shouldReturnStock() {
        StockResponse stockResponse = new StockResponse("petr3", "teste");
        when(client.getStock(any())).thenReturn(stockResponse);
        assertDoesNotThrow(() -> service.getStock("petr3"));
        assertTrue(service.getStock("petr3").getId().equalsIgnoreCase("petr3"));
    }

    @Test
    public void shouldThrowWhenNotFound() {
        when(client.getStock(any())).thenThrow(new StockNotRegisteredException("Stock not registered on stock-manager"));
        assertThrows(StockNotRegisteredException.class, () -> service.getStock("petr3"));
    }

    @Test
    public void shouldRegister() {
        StockServiceNotificationMessage message = new StockServiceNotificationMessage();
        assertDoesNotThrow(() -> service.registerToStockManagerNotification("localhost", 9000));
    }
}
