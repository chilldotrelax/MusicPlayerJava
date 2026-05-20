module org.andy.musicplayer_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires javafx.media;

    opens org.andy.musicplayer_java to javafx.fxml;
    exports org.andy.musicplayer_java;
}