package com.company.musicstorerecommendations.exception;

public class BadAlbumException extends RuntimeException {
    public BadAlbumException(String message) { super(message); }
    public BadAlbumException() { super(); }
}
