package com.stock.stockquotemanager.controller;

import com.stock.stockquotemanager.client.stockmanager.StockManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockNotificationControllerTest {

    @Mock
    private StockManagerService stockManagerService;

    private StockNotificationController controller;

    @BeforeEach
    public void before() {
        controller = new StockNotificationController(stockManagerService);
    }

    @Test
    public void deleteCache() {
        Assertions.assertDoesNotThrow(() -> controller.deleteCache());
    }

}
