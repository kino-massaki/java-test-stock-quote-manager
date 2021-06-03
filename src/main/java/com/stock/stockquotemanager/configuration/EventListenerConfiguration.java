package com.stock.stockquotemanager.configuration;

import com.stock.stockquotemanager.client.stockmanager.StockManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerConfiguration {
    
    @Value("${server.port}")
    private int port;

    @Value("${server.host:localhost}")
    private String host;

    @Autowired
    private StockManagerService stockManagerService;

    @EventListener(ApplicationReadyEvent.class)
    public void registerOnStockManager() {
        try {
            stockManagerService.registerToStockManagerNotification(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        };
    }
}
