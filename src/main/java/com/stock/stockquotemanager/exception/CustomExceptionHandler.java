package com.stock.stockquotemanager.exception;

import com.stock.stockquotemanager.model.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {StockNotRegisteredException.class, InvalidQuotesFormatException.class})
    protected ResponseEntity<Object> handleStockNotRegistered(RuntimeException ex, WebRequest request) {
        CommonResponse commonResponse = new CommonResponse(ex.getMessage());
        return handleExceptionInternal(ex, commonResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
