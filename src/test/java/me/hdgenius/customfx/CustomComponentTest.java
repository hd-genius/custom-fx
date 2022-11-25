package me.hdgenius.customfx;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class CustomComponentTest {
    @BeforeAll
    public static void setup() {
        Platform.startup(() -> {});
    }

    @Test
    public void testThatViewIsLoaded() {
        final CustomComponent instance = new CustomComponentImpl();
        Assertions.assertTrue(instance.getChildren().size() > 0);
    }
}

@ComponentView("/test-component.fxml")
class CustomComponentImpl extends CustomComponent {
    CustomComponentImpl() {
        super();
    }
}
