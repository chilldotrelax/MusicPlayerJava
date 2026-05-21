//Handles the save behavior of the app.

package org.andy.musicplayer_java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class File_Import_Manager {
    private List<String> songPath_Multi = new ArrayList<>();

    public void openFromFile(String path) {
        Path src = Paths.get(path);
        //TODO Get rid of the hardcoded directory.
        //TODO Handle duplicate files. Hardcode an MP3 only import.
        //TODO Future: Be able to convert files using FFMPEG.
        Path dest = Paths.get("/Users/andyhuang/IdeaProjects/MusicPlayer_Java/src/Songs");
        Path target = dest.resolve(src.getFileName());
        try {
            Files.copy(src, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openFromFolder (List < File > grab_folder_files_from_import) {
        //TODO Get rid of the hardcoded directory.
        Path dest = Paths.get("/Users/andyhuang/IdeaProjects/MusicPlayer_Java/src/Songs");
        for (File individual_song : grab_folder_files_from_import) {
            songPath_Multi.add(individual_song.getAbsolutePath());
        }
        for (String songPath: songPath_Multi){
            Path src = Paths.get(songPath);
            Path target = dest.resolve(src.getFileName());
            try{
                Files.copy(dest,target);
                } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        }
    }

