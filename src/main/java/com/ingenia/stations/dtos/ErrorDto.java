package com.ingenia.stations.dtos;

import java.time.LocalDateTime;

public class ErrorDto {

    public String message;

    public LocalDateTime timestamp;

    public ErrorDto(){
    }

    public ErrorDto(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
