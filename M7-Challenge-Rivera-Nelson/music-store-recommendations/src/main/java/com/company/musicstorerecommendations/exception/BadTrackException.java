package com.company.musicstorerecommendations.exception;

public class BadTrackException extends RuntimeException {
    public BadTrackException(String message) { super(message); }
    public BadTrackException() { super(); }
}
