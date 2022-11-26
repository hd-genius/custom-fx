package me.hdgenius.customfx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


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
        final CustomComponent instance = new CustomComponentImpl();
        final Button button = instance.getChildren().stream()
                .filter(child -> child instanceof Button)
                .findFirst()
                .map(child -> (Button)child)
                .orElseThrow();
        button.getOnMouseClicked().handle(null);
    }

    @Test
    @DisplayName("test that the component can be rendered from an FXML file")
    public void testComponentRenderedInFxml() {}
}

@ComponentView("/test-component.fxml")
class CustomComponentImpl extends CustomComponent {
    CustomComponentImpl() {
        super();
    }

    @FXML
    public void onClick() {}
}
