package me.hdgenius.customfx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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

//    @Test
    @DisplayName("test that the component can be rendered from an FXML file")
    public void testComponentRenderedInFxml() {}

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

@ComponentView("/test-component.fxml")
class CustomComponentImpl extends CustomComponent {
    CustomComponentImpl() {
        super();
    }

    @FXML
    public void onClick() {}
}

@ComponentView("/test-invalid-component.fxml")
class CustomComponentInvalidFxmlPath extends CustomComponent {
    CustomComponentInvalidFxmlPath() {
        super();
    }
}

class CustomComponentNoFxml extends CustomComponent {
    CustomComponentNoFxml() {
        super();
    }
}
