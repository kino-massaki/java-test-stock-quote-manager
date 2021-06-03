package com.stock.stockquotemanager.controller;

import com.stock.stockquotemanager.client.stockmanager.StockManagerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockcache")
public class StockNotificationController {

    private final StockManagerService stockManagerService;

    public StockNotificationController(
        StockManagerService stockManagerService) {
        this.stockManagerService = stockManagerService;
    }

    @DeleteMapping
    public void deleteCache() {
        stockManagerService.clearCache();
    }
}
