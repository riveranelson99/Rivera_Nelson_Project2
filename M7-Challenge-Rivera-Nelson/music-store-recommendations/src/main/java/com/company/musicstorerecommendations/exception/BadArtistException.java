package com.company.musicstorerecommendations.exception;

public class BadArtistException extends RuntimeException {
    public BadArtistException(String message) { super(message); }
    public BadArtistException() { super(); }
}
