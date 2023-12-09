package hellofx;

public class Track {

    int id;
    String soundFile;
    String songName;
    String Artist;


    /*
    Sollte man nicht in Tracks auch die Informationen mitgeben?
    Beim laden müssen ja die Tracks erstellt werden.
     */

    public Track(int id, String songName, String artist, String soundFile) {
        this.id = id;
        this.songName = songName;
        this.Artist = artist;
        this.soundFile = soundFile;
    }
}
