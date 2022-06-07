package com.company.musicstorecatalog.exception;

public class BadLabelException extends RuntimeException {
    public BadLabelException(String message) { super(message); }
    public BadLabelException() { super(); };
}
