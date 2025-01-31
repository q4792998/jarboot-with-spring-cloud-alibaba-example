
package com.jarboot.example.stock.server.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockExceptionHandler.class);
    
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getAllExceptionMsg(e));
    }
    
    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getAllExceptionMsg(e));
    }

    private static String getAllExceptionMsg(Throwable e) {
        Throwable cause = e;
        StringBuilder strBuilder = new StringBuilder();

        while (cause != null && !StringUtils.isEmpty(cause.getMessage())) {
            strBuilder.append("caused: ").append(cause.getMessage()).append(";");
            cause = cause.getCause();
        }

        return strBuilder.toString();
    }
}
