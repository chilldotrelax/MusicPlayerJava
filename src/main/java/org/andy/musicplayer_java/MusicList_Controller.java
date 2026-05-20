package org.andy.musicplayer_java;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MusicList_Controller {
    Path path = Paths.get("/Users/andyhuang/IdeaProjects/MusicPlayer_Java/src/Songs");

    ObservableList<String> indexFiles = FXCollections.observableArrayList();

    public ObservableList<String> updateList() {
        if (!indexFiles.isEmpty()) {
            indexFiles.clear();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.mp3")) {
                for (Path song_file : stream) {
                    indexFiles.add(String.valueOf(song_file.getFileName()));
                }
            } catch (IOException e) {
                System.out.println("Error!");
                e.printStackTrace();
            }
        } else {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.mp3")) {
                for (Path song_file : stream) {

                    indexFiles.add(String.valueOf(song_file.getFileName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return indexFiles;
    }
}