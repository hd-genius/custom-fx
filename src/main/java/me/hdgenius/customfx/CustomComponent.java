package me.hdgenius.customfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract class CustomComponent extends Pane {
    protected CustomComponent() {
        final Class<? extends CustomComponent> componentClass = this.getClass();
        final ComponentView annotation = componentClass.getAnnotation(ComponentView.class);
        final FXMLLoader fxmlLoader = new FXMLLoader(componentClass.getResource(annotation.value()));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
