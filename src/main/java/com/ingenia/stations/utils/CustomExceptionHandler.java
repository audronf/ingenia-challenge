package com.ingenia.stations.utils;

import com.ingenia.stations.dtos.ErrorDto;
import com.ingenia.stations.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(StationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleStationNotFoundException(StationNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getTimestamp());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PathNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleStationNotFoundException(PathNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getTimestamp());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCostException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleStationNotFoundException(InvalidCostException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getTimestamp());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StationNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleStationNotFoundException(StationNameAlreadyExistsException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getTimestamp());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPathException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleStationNotFoundException(InvalidPathException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), ex.getTimestamp());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
