package hellofx.bisnis;

import java.util.Date;
import java.util.List;

public class Playlist {

    int id;
    String title;
    String creationDate;
    String coverFile;
    List<Track> tracks;


    public Playlist(int id, String title, String creationDate, List<Track> tracks) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.tracks = tracks;
    }

    int numberOfTracks(){
        return tracks.size();
    }

    public String getTrack(int no){
        return tracks.get(no).soundFile;
    }

    public String getTitle() {
        return title;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public long getId() {
        return id;
    }
}
