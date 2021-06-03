package com.stock.stockquotemanager.client.stockmanager;

import com.stock.stockquotemanager.exception.StockNotRegisteredException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockManagerClient {

    private final RestTemplate stockManagerRestTemplate;

    public StockManagerClient(RestTemplate stockManagerRestTemplate) {
        this.stockManagerRestTemplate = stockManagerRestTemplate;
    }

    public StockResponse getStock(String id) {
        StockResponse stockResponse = stockManagerRestTemplate.getForObject("/stock/" + id, StockResponse.class);
        if (stockResponse == null) {
            throw new StockNotRegisteredException("Stock not registered on stock-manager");
        }
        return stockResponse;
    }

    public void registerToNotify(StockServiceNotificationMessage message) {
        stockManagerRestTemplate.postForObject("/notification", message, String.class);
    }
}
