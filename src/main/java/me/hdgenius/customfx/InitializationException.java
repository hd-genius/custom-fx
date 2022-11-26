package me.hdgenius.customfx;

/**
 * An exception that is thrown when a CustomComponent instance
 * encounters an issue while loading the component's fxml content.
 */
public class InitializationException extends RuntimeException {
    InitializationException(final Exception exception, final String description) {
        super(description, exception);
    }
}
