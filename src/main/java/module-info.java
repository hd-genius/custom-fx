module me.hdgenius.customfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    exports me.hdgenius.customfx;

    opens me.hdgenius.customfx to javafx.fxml;
}