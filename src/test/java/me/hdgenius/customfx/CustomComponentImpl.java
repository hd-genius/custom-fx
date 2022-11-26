package me.hdgenius.customfx;

import javafx.fxml.FXML;

@ComponentView("/test-component.fxml")
public class CustomComponentImpl extends CustomComponent {
    public CustomComponentImpl() {
        super();
    }

    @FXML
    public void onClick() {}
}