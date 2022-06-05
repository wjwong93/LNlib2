module lib.ln {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.kordamp.ikonli.javafx;

    requires com.jfoenix;

    opens lib.ln to javafx.fxml, javafx.graphics, com.jfoenix;
    exports lib.ln;
}
