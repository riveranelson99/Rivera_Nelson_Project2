package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.exception.BadAlbumException;
import com.company.musicstorecatalog.exception.BadArtistException;
import com.company.musicstorecatalog.exception.BadLabelException;
import com.company.musicstorecatalog.exception.BadTrackException;
import com.company.musicstorecatalog.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = BadAlbumException.class)
    public ResponseEntity<CustomErrorResponse> handleNoGameFound(BadAlbumException e) {
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = BadArtistException.class)
    public ResponseEntity<CustomErrorResponse> handleNoGameFound(BadArtistException e) {
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = BadLabelException.class)
    public ResponseEntity<CustomErrorResponse> handleNoGameFound(BadLabelException e) {
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = BadTrackException.class)
    public ResponseEntity<CustomErrorResponse> handleNoGameFound(BadTrackException e) {
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
