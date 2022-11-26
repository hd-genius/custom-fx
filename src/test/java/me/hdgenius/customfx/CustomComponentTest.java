package me.hdgenius.customfx;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;


public class CustomComponentTest {
    @BeforeAll
    public static void setup() {
        Platform.startup(() -> {});
    }

    @Test
    @DisplayName("test that the view FXML content is attached under the component")
    public void testThatViewIsLoaded() {
        final CustomComponentImpl instance = new CustomComponentImpl();
        Assertions.assertTrue(instance.getChildren().stream()
                .anyMatch(child -> child instanceof Button));
        Assertions.assertTrue(instance.getChildren().stream()
                .anyMatch(child -> child instanceof TextField));
    }

    @Test
    @DisplayName("test that component methods can be called from the view's FXML")
    public void testMethodsFromFxml() {
        final CustomComponentImpl instance = spy(CustomComponentImpl.class);
        final Button button = instance.getChildren().stream()
                .filter(child -> child instanceof Button)
                .findFirst()
                .map(child -> (Button)child)
                .orElseThrow();
        button.getOnMouseClicked().handle(null);
        verify(instance).onClick();
    }

    @Test
    @DisplayName("test that the component can be rendered from an FXML file")
    public void testComponentRenderedInFxml() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/test-component-render.fxml"));
        final Pane pane = new Pane();
        fxmlLoader.setRoot(pane);
        fxmlLoader.load();
    }

//    @Test
    @DisplayName("test that the component throws an InitializationException when the fxml view cannot be read")
    public void testFxmlReadError() {}

    @Test
    @DisplayName("test that the component throws an ConfigurationException when the path to the fxml file is invalid")
    public void testInvalidFxmlPath() {
        Assertions.assertThrows(ConfigurationException.class, CustomComponentInvalidFxmlPath::new);
    }

    @Test
    @DisplayName("test that the component throws an ConfigurationException when the ComponentView annotation is not attached")
    public void testNoViewError() {
        Assertions.assertThrows(ConfigurationException.class, CustomComponentNoFxml::new);
    }
}

