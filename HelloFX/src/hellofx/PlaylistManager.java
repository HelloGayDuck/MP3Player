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
    List<Map<Integer,String>> playlistMap = new ArrayList<Map<Integer,String>>();

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

        JSONArray jasonArray = new JSONArray();
        jasonArray.put("lorem ipsum");

        for(int i = 0; i<playlistMap.size(); i++) {
            JSONObject jasonPlaylist = new JSONObject(playlistMap.get(i));
            jasonArray.put(jasonPlaylist);

        }
    }
}
