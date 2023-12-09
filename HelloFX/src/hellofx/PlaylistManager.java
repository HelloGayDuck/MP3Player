package hellofx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import hellofx.Playlist;

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

    }

    private void displayTrackInfo() {
        if (currentTag != null) {
            String songName = currentTag.getFirst(FieldKey.TITLE);
            String artist = currentTag.getFirst(FieldKey.ARTIST);
            String album = currentTag.getFirst(FieldKey.ALBUM);
            String length = player.length() / 1000 + " seconds";

            // Display the information (You can customize this part based on your UI framework)
            System.out.println("Song Name: " + songName);
            System.out.println("Artist: " + artist);
            System.out.println("Album: " + album);
            System.out.println("Length: " + length);

            // Display album art
            Artwork artwork = currentTag.getFirstArtwork();
            if (artwork != null) {
                byte[] imageData = artwork.getBinaryData();
                Image image = new Image(imageData);
                ImageView imageView = new ImageView(image);
                // You can add the ImageView to your UI
            }
        }
    }
    private void loadTracks() {
        try (FileReader fileReader = new FileReader("tracks.json")) {
            StringBuilder content = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                content.append((char) character);
            }

            JSONArray jsonTracks = new JSONArray(content.toString());
            tracks.clear();

            for (int i = 0; i < jsonTracks.length(); i++) {
                JSONObject jsonTrack = jsonTracks.getJSONObject(i);
                String title = jsonTrack.getString("title");
                String artist = jsonTrack.getString("artist");
                String filePath = jsonTrack.getString("filePath");
                tracks.add(new Track(title, artist, filePath));
            }

            System.out.println("Tracks loaded from tracks.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
