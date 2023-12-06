package hellofx;

import java.util.Date;
import java.util.List;

public class Playlist {

    long id;
    String title;
    Date creationDate;
    String coverFile;
    List<Track> tracks;


    public Playlist(long id, String title, Date creationDate, String coverFile, List<Track> tracks) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.coverFile = coverFile;
        this.tracks = tracks;
    }

    int numberOfTracks(){
        return tracks.size();
    }

    public String getTrack(int no){
        return tracks.get(no).soundFile;
    }

}
