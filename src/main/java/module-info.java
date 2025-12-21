module game01 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires com.h2database;
    requires javafx.graphics;
    requires java.desktop;
    requires java.security.jgss;
    opens game to javafx.fxml;
    exports game;
}
