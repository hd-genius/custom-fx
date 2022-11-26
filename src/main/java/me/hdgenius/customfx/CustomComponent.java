package me.hdgenius.customfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public abstract class CustomComponent extends Pane {
    protected CustomComponent() {
        loadContent(getContentResource());
    }

    private void loadContent(final URL resource) {
        final FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (final IOException exception) {
            final String errorMessage = String.format("Encountered an IOException while trying to load the content for %s", getClass().getName());
            throw new InitializationException(exception, errorMessage);
        }
    }

    private URL getContentResource() {
        final String contentPath = getContentPath();
        final URL resource = getClass().getResource(contentPath);

        if (resource == null) {
            final String errorMessage = String.format("The view resource, %s, does not exist for the component %s", contentPath, getClass().getCanonicalName());
            throw new ConfigurationException(errorMessage);
        }

        return resource;
    }

    private String getContentPath() {
        final ComponentView annotation = getClass().getAnnotation(ComponentView.class);
        if (annotation == null) {
            final String errorMessage = String.format("No ComponentView annotation was found for the class %s", getClass().getCanonicalName());
            throw new ConfigurationException(errorMessage);
        }
        return annotation.value();
    }
}
