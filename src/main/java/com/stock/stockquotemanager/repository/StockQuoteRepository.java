package com.stock.stockquotemanager.repository;

import com.stock.stockquotemanager.repository.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockQuoteRepository extends JpaRepository<Stock, String> {
}
