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
    requires mp3agic;

    opens org.andy.musicplayer_java to javafx.fxml;
    exports org.andy.musicplayer_java;
    exports org.andy.musicplayer_java.popupcontrollers;
    opens org.andy.musicplayer_java.popupcontrollers to javafx.fxml;
    exports org.andy.musicplayer_java.backgroundtasks;
    opens org.andy.musicplayer_java.backgroundtasks to javafx.fxml;
    exports org.andy.musicplayer_java.toplevel;
    opens org.andy.musicplayer_java.toplevel to javafx.fxml;
    exports org.andy.musicplayer_java.controllers;
    opens org.andy.musicplayer_java.controllers to javafx.fxml;
}