package hellofx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import hellofx.Playlist;

public class PlaylistManager {

    String saveFilePath;
    Map<Integer, Playlist> playlistMap;

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

    private void save(){
        JSONObject jsonPlaylists = new JSONObject(playlistMap);

        for(Playlist playlist : playlistMap.values()) {
      
        }
    }
}
