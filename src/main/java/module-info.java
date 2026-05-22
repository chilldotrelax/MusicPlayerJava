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
    requires java.logging;

    opens org.andy.musicplayer_java to javafx.fxml;
    exports org.andy.musicplayer_java;
    exports org.andy.musicplayer_java.popup_controllers;
    opens org.andy.musicplayer_java.popup_controllers to javafx.fxml;
    exports org.andy.musicplayer_java.background_tasks;
    opens org.andy.musicplayer_java.background_tasks to javafx.fxml;
    exports org.andy.musicplayer_java.musicplayer_toplevel;
    opens org.andy.musicplayer_java.musicplayer_toplevel to javafx.fxml;
    exports org.andy.musicplayer_java.controllers;
    opens org.andy.musicplayer_java.controllers to javafx.fxml;
}