package hellofx.bisnis;

public class Track {

    int id;
    String soundFile;
    String songName;
    String Artist;
    String picturePath;

    //song-length

    public Track(int id, String songName, String artist, String soundFile) {
        this.id = id;
        this.songName = songName;
        this.Artist = artist;
        this.soundFile = soundFile;
    }

    public int getId() {
        return id;
    }

    public String getSoundFile() {
        return soundFile;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return Artist;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
