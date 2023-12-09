package hellofx.bisnis;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import com.mpatric.mp3agic.*;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.imageio.ImageIO;

import java.io.File;

public class PlaylistManager {

    String saveFilePath;
    ArrayList<Playlist> playlistMap = new ArrayList<Playlist>();

    public PlaylistManager() {
        saveFilePath = System.getProperty("user.home") + "/myMp3Playlists.json";
    }

    private void load(){
        File f = new File(saveFilePath);
        if(f.exists() && !f.isDirectory()){

        } else {
            save();
        }
    }

    /*
        Hier werden die Informationen über die PLaylists und Tracks geladen
        Ein JASONObject ist wie eine Map, desswegen kann man Ihr einfach die playlistMap geben.
     */
    private void save(){

        JSONArray jsonArray = new JSONArray();

        for(Playlist playlist : playlistMap) {
            JSONObject jsonPlaylist = new JSONObject();
            jsonPlaylist.put("title", playlist.title);
            jsonPlaylist.put("creation-date", playlist.creationDate.toString());

            JSONArray jsonPlaylistTracks = new JSONArray();
            for(int i = 0; i < playlist.tracks.size(); i++){
                jsonPlaylistTracks.put(i, playlist.tracks.get(i).soundFile);
            }

            jsonPlaylist.put("tracks", jsonPlaylistTracks);
            jsonArray.put(jsonPlaylist);
        }

        try (FileWriter fileWriter = new FileWriter(saveFilePath)) {
            // Write the JSON object to the file
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readID3Tags(String track) {
        try {
            Mp3File mp3file = new Mp3File(track);

            if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                System.out.println("Title: " + id3v1Tag.getTitle());
                System.out.println("Artist: " + id3v1Tag.getArtist());
                System.out.println("Album: " + id3v1Tag.getAlbum());
                // Add more fields as needed
            } else if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                System.out.println("ID3v2 Tag Information:");
                System.out.println("Title: " + id3v2Tag.getTitle());
                System.out.println("Artist: " + id3v2Tag.getArtist());
                System.out.println("Album: " + id3v2Tag.getAlbum());

                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(albumImageData);
                    BufferedImage albumImage = ImageIO.read(inputStream);
                    ImageIO.write(albumImage, "png", new File(System.getProperty("user.home") + "/images"));
                }
                // Add more fields as needed
            }

        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }

    }

    /*
    Liste an Tracks erstellen, die den Filepath beinhalten, danach den rest der Inhalte füllen mit dem lesen der id3tags
     */

    private void loadTracks() {
        for (Playlist playlist : playlistMap) {
            try (FileReader fileReader = new FileReader(saveFilePath)) {
                StringBuilder content = new StringBuilder();
                int character;
                while ((character = fileReader.read()) != -1) {
                    content.append((char) character);
                }

                JSONArray jsonTracks = new JSONArray(content.toString());

                for (int i = 0; i < jsonTracks.length(); i++) {
                    JSONObject jsonTrack = jsonTracks.getJSONObject(i);
                    int id = jsonTrack.getInt("id");
                    String artist = jsonTrack.getString("artist");
                    String songName = jsonTrack.getString("songName");
                    String filePath = jsonTrack.getString("filePath");
                    Track newTrack = new Track(id,songName,artist,filePath);
                    playlist.tracks.add(newTrack);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
