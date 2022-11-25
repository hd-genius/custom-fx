package me.hdgenius.customfx;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CustomComponentTest {
    @Test
    public void testThatViewIsLoaded() {
        Platform.startup(() -> {});
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
