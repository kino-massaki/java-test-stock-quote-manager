package com.stock.stockquotemanager.client.stockmanager;

import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Optional;

@Component
public class StockManagerService {

    private HashMap<String, StockResponse> cache = new HashMap<>();

    private final StockManagerClient client;

    public StockManagerService(StockManagerClient client) {
        this.client = client;
    }

    public StockResponse getStock(String id) {
        String idLowerCased = id.toLowerCase();
        StockResponse cachedResponse = cache.get(idLowerCased);

        return Optional.ofNullable(cachedResponse)
            .orElse(client.getStock(idLowerCased));
    }

    public void registerToStockManagerNotification(String host, int port) throws UnknownHostException {
        StockServiceNotificationMessage stockServiceNotificationMessage =
            new StockServiceNotificationMessage(host, port);
        client.registerToNotify(stockServiceNotificationMessage);
    }

    public void clearCache() {
        cache = new HashMap<>();
    }
}
