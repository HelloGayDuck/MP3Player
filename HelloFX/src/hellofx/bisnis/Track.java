package hellofx.bisnis;

public class Track {

    int id;
    String soundFile;
    String songName;
    String Artist;

    //song-length

    public Track(int id, String songName, String artist, String soundFile) {
        this.id = id;
        this.songName = songName;
        this.Artist = artist;
        this.soundFile = soundFile;
    }
}
