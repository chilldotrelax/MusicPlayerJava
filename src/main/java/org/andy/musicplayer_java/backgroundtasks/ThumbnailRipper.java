package org.andy.musicplayer_java.backgroundtasks;

import java.nio.file.Files;
import java.nio.file.Path;
import com.mpatric.mp3agic.*;
import java.io.IOException;
import java.nio.file.Paths;

class ThumbnailRipper {
    private final String filepath;
    private final String songName;
    private final Path writeTo = Paths.get("src/main/resources/Thumbnails");

    public ThumbnailRipper(String filepath, String songName) {
        this.songName = songName.substring(0,songName.length()-4);
        this.filepath = filepath;
    }

    private Mp3File getFile() throws IOException, InvalidDataException, UnsupportedTagException {
        Path song = Paths.get(this.filepath);
        return new Mp3File(song);
    }

    public void grabImageData() {
        try {
//            if (getFile().hasId3v1Tag()) {
//                ID3v1 id3v1Tag = getFile().getId3v1Tag();
//            }
            if (getFile().hasId3v2Tag()) {
                ID3v2 id3v2Tag = getFile().getId3v2Tag();
                byte[] imageData = id3v2Tag.getAlbumImage();
                reconstructImage(imageData);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception @??");
        } catch (InvalidDataException | UnsupportedTagException e) {
            System.out.println("Bad data or unupported tag!");
        }
    }
    private void reconstructImage(byte[] imageAsBytes) throws IOException {
        if (imageAsBytes != null){
            String albumImagePathAsString = "src/main/resources/Thumbnails/"+this.songName+".png";
            Path albumImagePath = Paths.get(albumImagePathAsString);
            Files.write(albumImagePath,imageAsBytes);
        }
        else{
            System.out.println("Bad Image Data!");
        }
    }

}
