package com.ingenia.stations.exceptions;

import java.time.LocalDateTime;

public class BaseException extends Exception {

    private String message;
    private LocalDateTime timestamp;

    public BaseException(String message) {
        super(message);
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
