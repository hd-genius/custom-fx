package me.hdgenius.customfx;

/**
 * An exception that is thrown when a CustomComponent subclass is incorrectly configured.
 */
public class ConfigurationException extends RuntimeException {
    ConfigurationException(final String description) {
        super(description);
    }
}
