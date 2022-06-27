package me.hdgenius.customfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public abstract class CustomComponent extends Pane {
    protected CustomComponent() {
        loadContent(getContentResource());
    }

    private URL getContentResource() {
        final ComponentView annotation = getClass().getAnnotation(ComponentView.class);
        return getClass().getResource(annotation.value());
    }

    private void loadContent(final URL resource) {
        final FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (final IOException exception) {
            throw new InitializationException(exception, String.format("Encountered an IOException while trying to load the content for %s", getClass().getName()));
        }
    }
}
