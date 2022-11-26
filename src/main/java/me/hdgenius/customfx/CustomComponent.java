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
        if (annotation == null) {
            throw new ConfigurationException("No ComponentView annotation was found for the class" + getClass().getCanonicalName());
        }
        final String contentPath = annotation.value();
        final URL resource = getClass().getResource(contentPath);

        if (resource == null) {
            throw new ConfigurationException(String.format(
                    "The view resource, %s, does not exist for the component %s",
                    contentPath,
                    getClass().getCanonicalName()));
        }

        return resource;
    }

    private void loadContent(final URL resource) {
        final FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        System.out.println(resource);

        try {
            fxmlLoader.load();
        } catch (final IOException exception) {
            throw new InitializationException(exception, String.format("Encountered an IOException while trying to load the content for %s", getClass().getName()));
        }
    }
}
