package com.company.musicstorecatalog.exception;

public class BadTrackException extends RuntimeException {
    public BadTrackException(String message) { super(message); }
    public BadTrackException() { super(); }
}
