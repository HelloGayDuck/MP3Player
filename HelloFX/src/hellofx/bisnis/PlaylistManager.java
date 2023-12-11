package hellofx.bisnis;

import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mpatric.mp3agic.*;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.imageio.ImageIO;

import java.io.File;
import java.util.List;

public class PlaylistManager {

    String saveFilePath;
    ArrayList<Playlist> playlistMap = new ArrayList<Playlist>();

    public PlaylistManager() {
        saveFilePath = System.getProperty("user.home") + "/myMp3Playlists.json";
    }

    public void load() throws InvalidDataException, UnsupportedTagException, IOException {
        File f = new File(saveFilePath);
        if(f.exists() && !f.isDirectory()){
            loadTracks();
        } else {
            List<Track> beispielTracks = new ArrayList<Track>();

            beispielTracks.add(readID3Tags(1, "C:\\Users\\micha\\MP3Player\\HelloFX\\.songs\\beispiel1.mp3"));
            beispielTracks.add(readID3Tags(2, "C:\\Users\\micha\\MP3Player\\HelloFX\\.songs\\beispiel2.mp3"));
            beispielTracks.add(readID3Tags(3, "C:\\Users\\micha\\MP3Player\\HelloFX\\.songs\\beispiel3.mp3"));

            Playlist neu = new Playlist(0,"Beispiel-Playlist", LocalDate.now().toString(),beispielTracks);
            playlistMap.add(0, neu);
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
            jsonPlaylist.put("creation-date", playlist.creationDate);

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

    private Track readID3Tags(int id, String filePath) throws InvalidDataException, UnsupportedTagException, IOException {
            Mp3File mp3file = new Mp3File(filePath);

            if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();

                String songName = id3v1Tag.getTitle();
                String artist = id3v1Tag.getArtist();

                return new Track(id, songName, artist, filePath);

            } else if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();


                String songName = id3v2Tag.getTitle();
                String artist = id3v2Tag.getArtist();



                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(albumImageData);
                    BufferedImage albumImage = ImageIO.read(inputStream);
                    ImageIO.write(albumImage, "png", new File(System.getProperty("user.home") + "/images"));
                }

                return new Track(id, songName, artist, filePath);
            }
        return null;
    }


    private void loadTracks() {
        for (Playlist playlist : playlistMap) {
            try (FileReader fileReader = new FileReader(saveFilePath)) {
                StringBuilder content = new StringBuilder();
                int character;
                while ((character = fileReader.read()) != -1) {
                    content.append((char) character);
                }

                //JSONArray mit den Playlists
                JSONArray jsonArray = new JSONArray(content.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonTrack = jsonArray.getJSONObject(i);
                    String title = jsonTrack.getString("title");
                    String creation_date = jsonTrack.getString("creation-date");

                    for(int j = 0; j < playlist.tracks.size(); j++) {
                        Track newTrack = readID3Tags(i, playlist.tracks.get(j).soundFile);
                        playlist.tracks.add(newTrack);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidDataException | UnsupportedTagException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Playlist> getPlaylistMap() {
        return playlistMap;
    }
}
