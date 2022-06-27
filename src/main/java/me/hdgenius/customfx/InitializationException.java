package me.hdgenius.customfx;

public class InitializationException extends RuntimeException {
    InitializationException(final Exception exception, final String description) {
        super(description, exception);
    }
}
